package org.trinity.biblediary.process.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.trinity.biblediary.common.message.dto.domain.PlanProgressDetailDto;
import org.trinity.biblediary.common.message.dto.domain.PlanProgressDto;
import org.trinity.biblediary.common.message.dto.domain.UserDto;
import org.trinity.biblediary.common.message.dto.domain.UserSearchingDto;
import org.trinity.biblediary.common.message.dto.response.AccessTokenResponse;
import org.trinity.biblediary.common.message.exception.ErrorMessage;
import org.trinity.biblediary.common.message.exception.InfoMessage;
import org.trinity.biblediary.common.message.lookup.BibleVolume;
import org.trinity.biblediary.common.message.lookup.FlagStatus;
import org.trinity.biblediary.common.message.lookup.RecordStatus;
import org.trinity.biblediary.common.message.lookup.SystemAttributeKey;
import org.trinity.biblediary.common.message.lookup.TimeZone;
import org.trinity.biblediary.common.message.lookup.UserStatus;
import org.trinity.biblediary.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.biblediary.process.controller.base.ISystemAttributeProcessController;
import org.trinity.biblediary.process.controller.base.IUserProcessController;
import org.trinity.biblediary.repository.business.dataaccess.IChurchRepository;
import org.trinity.biblediary.repository.business.dataaccess.IPlanProgressRepository;
import org.trinity.biblediary.repository.business.dataaccess.IPlanRepository;
import org.trinity.biblediary.repository.business.dataaccess.ISignOffRepository;
import org.trinity.biblediary.repository.business.dataaccess.IUserRepository;
import org.trinity.biblediary.repository.business.entity.Church;
import org.trinity.biblediary.repository.business.entity.Plan;
import org.trinity.biblediary.repository.business.entity.PlanProgress;
import org.trinity.biblediary.repository.business.entity.PlanProgressDetail;
import org.trinity.biblediary.repository.business.entity.SignOff;
import org.trinity.biblediary.repository.business.entity.User;
import org.trinity.common.exception.IException;
import org.trinity.message.IMessageResolverChain;
import org.trinity.message.LookupParser;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.process.converter.IObjectConverter.CopyPolicy;

@Service
public class UserProcessController extends AbstractAutowiredCrudProcessController<User, UserDto, UserSearchingDto, IUserRepository>
		implements IUserProcessController {
	@Autowired
	private ISystemAttributeProcessController systemAttributeProcessController;

	@Autowired
	private ISignOffRepository signOffRepository;

	@Autowired
	private IPlanProgressRepository planProgressRepository;

	@Autowired
	private IPlanRepository planRepository;

	@Autowired
	private IObjectConverter<PlanProgressDetail, PlanProgressDetailDto> planProgressDetailConverter;

	@Autowired
	private IObjectConverter<PlanProgress, PlanProgressDto> planProgressConverter;

	@Autowired
	private IMessageResolverChain messageResolver;

	@Autowired
	private IChurchRepository churchRepository;

	@Override
	public UserDto authenticateWechatUserByCode(final String code, final String session) throws IException {
		final User oldUser = getDomainEntityRepository().findOneBySession(session);

		final String appId = systemAttributeProcessController.getValue(SystemAttributeKey.APP_ID);
		final String appSec = systemAttributeProcessController.getValue(SystemAttributeKey.APP_SEC);

		final List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

		final List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.TEXT_PLAIN);
		supportedMediaTypes.add(MediaType.APPLICATION_JSON);

		converter.setSupportedMediaTypes(supportedMediaTypes);
		messageConverters.add(converter);

		final RestTemplate restTemplate = new RestTemplate(messageConverters);

		final String url = String.format(
				"https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", appId, appSec,
				code);
		final AccessTokenResponse response = restTemplate.getForEntity(url, AccessTokenResponse.class).getBody();

		User newUser = getDomainEntityRepository().findOneByWechat(response.getOpenid());
		if (newUser == null) {
			if (oldUser != null) {
				oldUser.setSession(null);
				getDomainEntityRepository().save(oldUser);
			}

			newUser = new User();
			newUser.setAdmin(FlagStatus.NO);
			newUser.setCellphone(null);
			newUser.setChurch(null);
			newUser.setNickName(null);
			newUser.setWechat(response.getOpenid());
			newUser.setStatus(UserStatus.GUEST);
			newUser.setSession(session);
			newUser.setTimeZone(TimeZone.UTC_EAST_8);

			getDomainEntityRepository().save(newUser);
		} else {
			if (oldUser != null) {
				if (!newUser.getId().equals(oldUser.getId())) {
					oldUser.setSession(null);
					getDomainEntityRepository().save(oldUser);
				}

				newUser.setSession(session);
				getDomainEntityRepository().save(newUser);
			}
		}
		return getDomainObjectConverter().convert(newUser);
	}

	@Override
	public UserDto getMe(final UserSearchingDto searchingDto) {
		final User user = getDomainEntityRepository().findOneByWechat(getCurrentUsername());
		final UserDto userDto = getDomainObjectConverter().convert(user, searchingDto.generateRelationship());
		userDto.setSession(null);
		return userDto;
	}

	@Override
	public String getProgress() throws IException {
		final User user = getDomainEntityRepository().findOneByWechat(getCurrentUsername());

		if (user.getPlans().isEmpty()) {
			return messageResolver.getMessage(InfoMessage.YOU_HAVE_NO_PLANS);
		}

		final Date now = user.getTimeZone().getLocalTime();

		Date lastSignOffDate = signOffRepository.getMaxLastForDate(user);
		if (lastSignOffDate == null) {
			lastSignOffDate = new Date(0);
		}

		final List<String> results = new ArrayList<>();

		results.add(messageResolver.getMessage(InfoMessage.YOUR_PROGRESS_IS, user.getNickName()));
		for (final Plan plan : user.getPlans()) {
			final StringBuilder result = new StringBuilder();
			result.append(messageResolver.getMessage(plan.getName())).append(":\r\n");
			try {
				final PlanProgressDto progressDto = getProgress(plan, now, lastSignOffDate);

				result.append(messageResolver.getMessage(InfoMessage.PROGRESS_DATE,
						new SimpleDateFormat("yyyy/MM/dd").format(progressDto.getDate()))).append("\r\n");

				final String progress = String.join("\r\n", progressDto.getDetails().stream().sorted((a, b) -> a.getPart() - b.getPart())
						.map(item -> String.format("  %s%s:%s-%s%s%s",
								messageResolver.getMessage(LookupParser.parse(BibleVolume.class, item.getFromVolume().getCode())) + " ",
								item.getFromChapter(), item.getFromVerse(), item.getFromVolume() == item.getFromVolume() ? ""
										: (messageResolver.getMessage(LookupParser.parse(BibleVolume.class, item.getToVolume().getCode()))
												+ " "),
								(item.getFromVolume() == item.getFromVolume() && item.getFromChapter() == item.getToChapter()) ? ""
										: (item.getToChapter() + ":"),
								item.getToVerse()))
						.toArray(String[]::new));

				result.append(messageResolver.getMessage(InfoMessage.PLAN_PROGRESS, progress));
			} catch (final IException e) {
				switch ((ErrorMessage) e.getErrorMessages().get(0).getItem1()) {
					case YOUR_NEXT_PROGRESS_WILL_START_ON:
					case PLAN_HAS_FINISHED:
						result.append(e.getMessage());
						break;
					default:
						continue;
				}
			}

			results.add(result.toString());
		}

		return String.join("\r\n", results);
	}

	@Override
	@Transactional(rollbackOn = IException.class)
	public UserDto getWechatUser(final String openid) throws IException {
		User user = getDomainEntityRepository().findOneByWechat(openid);
		if (user == null) {
			user = new User();
			user.setAdmin(FlagStatus.NO);
			user.setCellphone(null);
			user.setChurch(null);
			user.setNickName(null);
			user.setWechat(openid);
			user.setTimeZone(TimeZone.UTC_EAST_8);
			user.setStatus(UserStatus.GUEST);

			getDomainEntityRepository().save(user);
		}
		return getDomainObjectConverter().convert(user);
	}

	@Override
	public UserDto getWechatUserBySession(final String session) throws IException {
		final User user = getDomainEntityRepository().findOneBySession(session);
		if (user == null) {
			return null;
		}

		return getDomainObjectConverter().convert(user);
	}

	@Override
	@Transactional(rollbackOn = IException.class)
	public void joinPlan(final Long planId) throws IException {
		final Plan plan = planRepository.findOne(planId);

		final User user = getDomainEntityRepository().findOneByWechat(getCurrentUsername());
		user.getPlans().add(plan);

		getDomainEntityRepository().save(user);
	}

	@Override
	@Transactional(rollbackOn = IException.class)
	public String signup() throws IException {
		final User user = getDomainEntityRepository().findOneByWechat(getCurrentUsername());

		if (user.getPlans().isEmpty()) {
			return messageResolver.getMessage(InfoMessage.YOU_HAVE_NO_PLANS);
		}
		final TimeZone timeZone = user.getTimeZone();

		Date lastSignOffDate = signOffRepository.getMaxLastForDate(user);
		if (lastSignOffDate == null) {
			lastSignOffDate = new Date(0);
		}

		final Date nextProgressDate = planProgressRepository.getNextForDate(user, lastSignOffDate, RecordStatus.ACTIVE);

		final Date now = timeZone.getLocalTime();

		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(nextProgressDate);

		calendar.add(Calendar.HOUR_OF_DAY, 4);

		final DateFormat format = new SimpleDateFormat("yyyy/MM/dd");

		if (calendar.getTime().after(now)) {
			throw getExceptionFactory().createException(ErrorMessage.YOUR_NEXT_PROGRESS_WILL_START_ON, format.format(nextProgressDate));
		}

		final SignOff signOff = new SignOff();

		signOff.setForDate(nextProgressDate);
		signOff.setLocalTimestamp(now);
		signOff.setLocalTimeZone(timeZone);
		signOff.setUser(user);
		signOff.setStatus(RecordStatus.ACTIVE);

		final int exceedDays = (int) ((now.getTime() - calendar.getTimeInMillis()) / 86400000);

		signOff.setOverrideExceedDays(exceedDays);
		signOff.setExceedDays(exceedDays);

		signOffRepository.save(signOff);

		if (exceedDays > 0) {
			return messageResolver.getMessage(InfoMessage.BACKDATED_SIGN_UP_DONE, String.valueOf(exceedDays));
		} else {
			return messageResolver.getMessage(InfoMessage.SIGN_UP_DONE);
		}
	}

	@Override
	@Transactional(rollbackOn = IException.class)
	public void update(final UserDto userDto) throws IException {
		final User user = getDomainEntityRepository().findOne(userDto.getId());

		getDomainObjectConverter().convertBack(userDto, user, CopyPolicy.SOURCE_IS_NOT_NULL);

		if (userDto.getChurch() != null) {
			final Church church = churchRepository.findOne(userDto.getChurch().getId());

			if (church.getUsers().isEmpty()) {
				user.setAdmin(FlagStatus.YES);
			}

			user.setChurch(church);
		}

		getDomainEntityRepository().save(user);
	}

	private PlanProgressDto getProgress(final Plan plan, final Date now, final Date lastSignOffDate) throws IException {
		final Pageable paging = new PageRequest(0, 1);
		final List<PlanProgress> planProgresses = planProgressRepository
				.getNextPlanProgress(plan, lastSignOffDate, RecordStatus.ACTIVE, paging).getContent();

		if (planProgresses.isEmpty()) {
			throw getExceptionFactory().createException(ErrorMessage.PLAN_HAS_FINISHED);
		}

		final PlanProgress planProgress = planProgresses.get(0);
		final Calendar nextProgressDate = Calendar.getInstance();

		nextProgressDate.setTime(planProgress.getDate());

		nextProgressDate.add(Calendar.HOUR_OF_DAY, 4);

		final DateFormat format = new SimpleDateFormat("yyyy/MM/dd");

		if (nextProgressDate.getTime().after(now)) {
			throw getExceptionFactory().createException(ErrorMessage.YOUR_NEXT_PROGRESS_WILL_START_ON,
					format.format(planProgress.getDate()));
		}

		final PlanProgressDto result = planProgressConverter.convert(planProgress);
		result.setDetails(planProgressDetailConverter.convert(planProgress.getDetails()));
		return result;
	}
}
