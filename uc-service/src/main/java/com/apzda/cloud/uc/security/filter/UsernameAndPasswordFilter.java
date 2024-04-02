/*
 * Copyright (C) 2023-2024 Fengz Ning (windywany@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.apzda.cloud.uc.security.filter;

import com.apzda.cloud.gsvc.security.authentication.DeviceAwareAuthenticationProcessingFilter;
import com.apzda.cloud.gsvc.security.token.JwtAuthenticationToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

/**
 * 用户名密码方式登录.
 * 
 * @author fengz (windywany@gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 **/
@Slf4j
public class UsernameAndPasswordFilter extends DeviceAwareAuthenticationProcessingFilter {

    public UsernameAndPasswordFilter(String url, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(url, "POST"), authenticationManager);
        log.debug("用户名/密码登录: POST({})", url);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        val param = readRequestBody(request, UsernameAndPassword.class);
        val username = param.getUsername();
        val password = param.getPassword();
        val token = JwtAuthenticationToken.unauthenticated(username, password);

        setDetails(request, token);
        // 开始认证
        return this.getAuthenticationManager().authenticate(token);
    }

    @Data
    static class UsernameAndPassword {

        private String password;// 用户名

        private String username;// 密码

    }

}
