package com.apzda.cloud.uc.domain.entity;

import com.apzda.cloud.gsvc.domain.AuditableEntity;
import com.apzda.cloud.gsvc.domain.SnowflakeIdGenerator;
import com.apzda.cloud.gsvc.model.SoftDeletable;
import com.apzda.cloud.gsvc.model.Tenantable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "uc_job")
@ToString
public class Job extends AuditableEntity<Long, String, Long> implements Tenantable<Long>, SoftDeletable {

    @Id
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

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @NotNull
    @Column(name = "tenant_id", nullable = false)
    private Long tenantId;

    @NotNull
    @JoinColumn(name = "org_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Organization organization;

    @NotNull
    @JoinColumn(name = "depart_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Department department;

    @Size(max = 64)
    @NotNull
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id", nullable = false)
    @ToString.Exclude
    private JobLevel level;

    @Size(max = 128)
    @Column(name = "icon", length = 128)
    private String icon;

    @Column(name = "remark")
    private String remark;

}
