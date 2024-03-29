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
package com.apzda.cloud.uc.domain.entity;

import com.apzda.cloud.gsvc.domain.AuditableEntity;
import com.apzda.cloud.gsvc.domain.SnowflakeIdGenerator;
import com.apzda.cloud.gsvc.model.SoftDeletable;
import com.apzda.cloud.uc.domain.vo.Gender;
import com.apzda.cloud.uc.domain.vo.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.val;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;

/**
 * @author fengz (windywany@gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 **/
@Getter
@Setter
@ToString
@Entity
@Table(name = "uc_user")
public class User extends AuditableEntity<Long, String, Long> implements SoftDeletable {

    @Id
    @GeneratedValue(generator = SnowflakeIdGenerator.NAME, strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "deleted")
    private boolean deleted;

    @Size(max = 32)
    @NotNull
    @Column(name = "username", nullable = false, length = 32)
    private String username;

    @Size(max = 64)
    @Column(name = "nickname", length = 64)
    private String nickname;

    @Size(max = 128)
    @Column(name = "first_name", length = 128)
    private String firstName;

    @Size(max = 128)
    @Column(name = "last_name", length = 128)
    private String lastName;

    @Size(max = 20)
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Size(max = 10)
    @Column(name = "phone_prefix", length = 10)
    private String phonePrefix;

    @Size(max = 64)
    @Column(name = "email", length = 64)
    private String email;

    @Size(max = 512)
    @NotNull
    @Column(name = "passwd", nullable = false, length = 512)
    private String passwd;

    @Size(max = 1024)
    @Column(name = "avatar", length = 1024)
    private String avatar;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;

    @Size(max = 10)
    @Column(name = "realm", length = 10)
    private String realm;

    @NotNull
    @Column(name = "referrer_id", nullable = false)
    private Long referrerId;

    @Size(max = 256)
    @Column(name = "referrers", length = 256)
    private String referrers;

    @Column(name = "referrer_level", nullable = false, columnDefinition = "tinyint unsigned")
    private Short referrerLevel;

    @Size(max = 32)
    @Column(name = "recommend_code", length = 32)
    private String recommendCode;

    @Size(max = 16)
    @Column(name = "channel", length = 16)
    private String channel;

    @Size(max = 256)
    @NotNull
    @Column(name = "ip", nullable = false, length = 256)
    private String ip;

    @Size(max = 24)
    @Column(name = "device", length = 24)
    private String device;

    @Size(max = 255)
    @Column(name = "remark")
    private String remark;

    @ManyToMany
    @JoinTable(name = "uc_user_role", joinColumns = @JoinColumn(name = "uid", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "role"))
    @ToString.Exclude
    private List<Role> roles;

    @OneToMany
    @JoinColumn(name = "uid", referencedColumnName = "id")
    @ToString.Exclude
    private List<UserMeta> metas;

    @NonNull
    public List<Role> getAllRoles(@Nullable String tenantId) {
        val rs = new HashSet<Role>();
        val roles1 = getRoles();
        if (!CollectionUtils.isEmpty(roles1)) {
            for (Role role : roles1) {
                if (rs.contains(role)) {
                    continue;
                }
                rs.add(role);
                val children = role.getChildren();
            }
        }
        return rs.stream().toList();
    }

    @NonNull
    public List<Role> getAllRoles() {
        return getAllRoles(null);
    }

}
