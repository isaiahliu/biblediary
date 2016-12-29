package org.trinity.biblediary.wechat.util;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.trinity.biblediary.common.message.dto.domain.UserDto;
import org.trinity.biblediary.process.controller.base.IUserProcessController;
import org.trinity.biblediary.process.controller.base.IWechatProcessController;
import org.trinity.common.exception.IException;

public class SignatureFilter extends OncePerRequestFilter {
	@Autowired
	private IWechatProcessController wechatProcessController;

	@Autowired
	private IUserProcessController userProcessController;

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
			throws ServletException, IOException {
		final String signature = request.getParameter("signature");
		final String timestamp = request.getParameter("timestamp");
		final String nonce = request.getParameter("nonce");
		final String openid = request.getParameter("openid");

		try {
			if (signature != null) {
				wechatProcessController.verify(signature, timestamp, nonce);
				UserDto user;
				if (openid != null) {
					user = userProcessController.getWechatUser(openid);
				} else {
					user = new UserDto();
				}
				SecurityContextHolder.getContext()
						.setAuthentication(new UsernamePasswordAuthenticationToken(user, user, Collections.emptyList()));
			}
			filterChain.doFilter(request, response);
		} catch (final IException e) {
			throw new ServletException();
		}
	}
}
