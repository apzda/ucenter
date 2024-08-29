package com.apzda.cloud.uc.domain.entity;

import com.apzda.cloud.gsvc.domain.SnowflakeIdGenerator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "uc_role_children")
public class RoleChild {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = SnowflakeIdGenerator.NAME, strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "created_at")
    private Long createdAt;

    @Size(max = 32)
    @Column(name = "created_by", length = 32)
    private String createdBy;

    @Column(name = "updated_at")
    private Long updatedAt;

    @Size(max = 32)
    @Column(name = "updated_by", length = 32)
    private String updatedBy;

    @NotNull
    @ColumnDefault("'0'")
    @Column(name = "tenant_id", nullable = false)
    private Long tenantId;

    @NotNull
    @ColumnDefault("b'0'")
    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    @NotNull
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @NotNull
    @Column(name = "child_id", nullable = false)
    private Long childId;

}