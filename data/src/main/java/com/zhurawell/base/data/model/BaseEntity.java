package com.zhurawell.base.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.math.BigInteger;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class BaseEntity {

    @Id
    private BigInteger id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BaseEntity entity = (BaseEntity) o;

        return getId() != null && getId().equals(entity.getId());
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }
}
