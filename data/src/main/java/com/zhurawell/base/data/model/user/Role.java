package com.zhurawell.base.data.model.user;

import com.zhurawell.base.data.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Set;


@Entity
@Setter
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "role_generator", sequenceName = "role_seq", allocationSize = 10, schema = "public")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "entity.role")
public class Role extends BaseEntity {

    public Role(BigInteger id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(generator = "role_generator")
    private BigInteger id;

    @Column(name = "name")
    private String name;

//    @Fetch(value = FetchMode.SELECT) // SELECT we use for LAZY  fetchType
//    @BatchSize(size = 4) // this annotation should be used with LAZY fetchType.
    @ManyToMany(fetch = FetchType.LAZY)         // It tells hibernate to load "permissions" objects not only for current object when we call getter but also for 4 other Role objects (if we have others objects in context)
    @JoinTable(
        name = "role_permission",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "collection.role.permission")
    private Set<Permission> permissions;

    public Set<Permission> getPermissions() {
        return this.permissions;
    }

    public void addPermission(Permission permission) {
        this.permissions.add(permission);
    }

    public void addPermissions(Collection<Permission> permissions) {
        this.permissions.addAll(permissions);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}