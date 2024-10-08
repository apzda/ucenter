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
package com.apzda.cloud.uc.domain.vo;

import org.apache.commons.lang3.StringUtils;

/**
 * @author fengz (windywany@gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 **/
public enum UserStatus {

    PENDING, ACTIVATED, LOCKED, DISABLED, EXPIRED;

    public static UserStatus fromName(String name) {
        if (StringUtils.isBlank(name)) {
            return DISABLED;
        }

        return switch (name.toUpperCase()) {
            case "PENDING" -> PENDING;
            case "ACTIVATED" -> ACTIVATED;
            case "LOCKED" -> LOCKED;
            case "EXPIRED" -> EXPIRED;
            default -> DISABLED;
        };

    }

}
