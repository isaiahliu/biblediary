package org.trinity.biblediary.common.message.dto.domain;

import java.util.List;

import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.LookupDto;

public class UserDto extends AbstractBusinessDto {
    private LookupDto admin;

    private String cellphone;

    private String nickName;

    private String wechat;

    private List<ChurchDto> churches;

    private List<PlanDto> plans;

    private String session;

    private LookupDto timeZone;

    public LookupDto getAdmin() {
        return admin;
    }

    public String getCellphone() {
        return cellphone;
    }

    public List<ChurchDto> getChurches() {
        return churches;
    }

    public String getNickName() {
        return nickName;
    }

    public List<PlanDto> getPlans() {
        return plans;
    }

    public String getSession() {
        return session;
    }

    public LookupDto getTimeZone() {
        return timeZone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setAdmin(final LookupDto admin) {
        this.admin = admin;
    }

    public void setCellphone(final String cellphone) {
        this.cellphone = cellphone;
    }

    public void setChurches(final List<ChurchDto> churches) {
        this.churches = churches;
    }

    public void setNickName(final String nickName) {
        this.nickName = nickName;
    }

    public void setPlans(final List<PlanDto> plans) {
        this.plans = plans;
    }

    public void setSession(final String session) {
        this.session = session;
    }

    public void setTimeZone(final LookupDto timeZone) {
        this.timeZone = timeZone;
    }

    public void setWechat(final String wechat) {
        this.wechat = wechat;
    }

}
