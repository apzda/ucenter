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
package com.apzda.cloud.uc.domain.repository;

import com.apzda.cloud.uc.domain.entity.Oauth;
import com.apzda.cloud.uc.domain.entity.OauthSession;
import com.apzda.cloud.uc.domain.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author fengz (windywany@gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 **/
@Repository
public interface OauthSessionRepository extends CrudRepository<OauthSession, Long> {

    @NonNull
    Optional<OauthSession> getFirstByOauthOrderByCreatedAtDesc(@NonNull Oauth oauth);

    @NonNull
    Optional<OauthSession> getFirstByUserOrderByCreatedAtDesc(@NonNull User user);

    @NonNull
    List<OauthSession> findAllByUserOrderByCreatedAtDesc(@NonNull User user, @NonNull Pageable pagination);

}