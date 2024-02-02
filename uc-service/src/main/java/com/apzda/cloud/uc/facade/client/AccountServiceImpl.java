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
package com.apzda.cloud.uc.facade.client;

import com.apzda.cloud.uc.client.AccountService;
import com.apzda.cloud.uc.client.Request;
import com.apzda.cloud.uc.client.UserInfo;
import com.apzda.cloud.uc.domain.entity.UserMeta;
import com.apzda.cloud.uc.domain.service.UserManager;
import com.apzda.cloud.uc.domain.vo.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author fengz (windywany@gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final UserManager userManager;

    @Override
    @Transactional(timeout = 3, readOnly = true)
    public UserInfo getUserInfo(Request request) {
        val builder = UserInfo.newBuilder();
        builder.setErrCode(0);
        val username = request.getUsername();
        if (StringUtils.isBlank(username)) {
            builder.setErrCode(404);
            builder.setErrMsg("user not found");
        }
        else {
            val user = userManager.getUserByUsername(username);
            if (user == null) {
                builder.setErrCode(404);
                builder.setErrMsg("user not found");
            }
            else {
                builder.setUid(username);
                builder.setUsername(username);
                val status = user.getStatus();
                builder.setEnabled(status == UserStatus.ACTIVATED || status == UserStatus.PENDING);
                builder.setAccountNonLocked(status != UserStatus.LOCKED);
                builder.setAccountNonExpired(status != UserStatus.EXPIRED);
                builder.setCredentialsNonExpired(userManager.isCredentialsExpired(user.getId()));
                val metas = userManager.getUserMetas(user.getId());

                for (UserMeta meta : metas) {
                    builder.addMeta(meta.convert());
                }

                val roles = user.getAllRoles();
                // todo load privileges

            }
        }

        return builder.build();
    }

}
