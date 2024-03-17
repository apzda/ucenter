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
package com.apzda.cloud.uc.security.token;

import cn.hutool.core.bean.BeanUtil;
import com.apzda.cloud.gsvc.security.token.JwtToken;
import com.apzda.cloud.gsvc.security.token.JwtTokenCustomizer;
import lombok.val;
import org.springframework.core.Ordered;
import org.springframework.security.core.Authentication;

/**
 * @author fengz (windywany@gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 **/
public class TokenCustomizer implements JwtTokenCustomizer {
    @Override
    public JwtToken customize(Authentication authentication, JwtToken token) {
        val userToken = BeanUtil.copyProperties(token, UserToken.class);
        return userToken;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
