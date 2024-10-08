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
package com.apzda.cloud.uc.server;

import com.apzda.cloud.audit.server.EnableAuditServer;
import com.apzda.cloud.captcha.server.EnableCaptchaServer;
import com.apzda.cloud.config.server.EnableConfigServer;
import com.apzda.cloud.sms.server.EnableSmsServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fengz (windywany@gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 **/
@SpringBootApplication
@EnableAuditServer
@EnableCaptchaServer
@EnableConfigServer
@EnableSmsServer
@EnableUCenterServer
public class UCenterMonoServer {

    public static void main(String[] args) {
        SpringApplication.run(UCenterMonoServer.class, args);
    }

}
