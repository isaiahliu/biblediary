package org.trinity.biblediary.wechat.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.trinity.biblediary.common.message.dto.domain.UserDto;
import org.trinity.biblediary.common.message.lookup.AccessRight;
import org.trinity.biblediary.common.message.lookup.FlagStatus;
import org.trinity.biblediary.process.controller.base.IUserProcessController;
import org.trinity.biblediary.process.controller.base.IWechatProcessController;
import org.trinity.common.exception.IException;

public class SignatureFilter extends OncePerRequestFilter {
    private static final String RESOURCE_PATHS[] = { "/static/" };
    private static final String COOKIE_NAME = "TRINITY_BIBLEDIARY";
    private static Logger logger = LogManager.getLogger(SignatureFilter.class);

    @Autowired
    private IWechatProcessController wechatProcessController;

    @Autowired
    private IUserProcessController userProcessController;

    private boolean isResourcePath(final String path) {
        for (final String resourcePath : RESOURCE_PATHS) {
            if (path.toLowerCase().startsWith(resourcePath)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
            throws ServletException, IOException {
        logger.debug(request.getServletPath());
        if (isResourcePath(request.getServletPath())) {
            filterChain.doFilter(request, response);
            return;
        }

        final String signature = request.getParameter("signature");
        final String timestamp = request.getParameter("timestamp");
        final String nonce = request.getParameter("nonce");
        final String openid = request.getParameter("openid");

        final String code = request.getParameter("code");

        String session = null;
        if (request.getCookies() != null) {
            for (final Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(COOKIE_NAME)) {
                    session = cookie.getValue();
                    break;
                }
            }
        }

        try {
            UserDto user = null;
            if (signature != null) {
                wechatProcessController.verify(signature, timestamp, nonce);
                if (openid != null) {
                    user = userProcessController.getWechatUser(openid);
                } else {
                    user = new UserDto();
                }
            }

            if (code != null) {
                if (session == null) {
                    session = UUID.randomUUID().toString();
                    final Cookie cookie = new Cookie(COOKIE_NAME, session);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }

                user = userProcessController.authenticateWechatUserByCode(code, session);
            } else if (session != null) {
                user = userProcessController.getWechatUserBySession(session);
            }

            if (user != null) {
                final boolean isAdmin = user.getAdmin() != null && FlagStatus.YES.getMessageCode().equals(user.getAdmin().getCode());

                final List<AccessRight> accessRights = new ArrayList<>();
                if (isAdmin) {
                    accessRights.add(AccessRight.SUPER_USER);
                }
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, user, accessRights));
            }
            filterChain.doFilter(request, response);
        } catch (final IException e) {
            throw new ServletException();
        }
    }
}
