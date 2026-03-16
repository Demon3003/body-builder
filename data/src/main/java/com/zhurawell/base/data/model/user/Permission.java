package com.zhurawell.base.data.model.user;

import com.zhurawell.base.data.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Setter
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "permission_generator", sequenceName = "permission_seq", schema = "public", allocationSize = 10)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "entity.permission")
public class Permission extends BaseEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "permission_generator")
    private BigInteger id;

    @Column(name = "permission_name")
    private String permissionName;

    @Transient // to ignore field for hibernate
    private String comment;

    Permission(String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public String getAuthority() {
        return permissionName;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permission='" + permissionName + '\'' +
                '}';
    }
}