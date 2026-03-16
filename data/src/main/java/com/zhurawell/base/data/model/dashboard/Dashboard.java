package com.zhurawell.base.data.model.dashboard;

import com.zhurawell.base.data.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@Table(name = "dashboard")
@NoArgsConstructor
@SequenceGenerator(name = "dashboard_generator", schema = "public", sequenceName = "dashboard_sequence",
        initialValue = 5,
        allocationSize = 20)
public class Dashboard extends BaseEntity {

    @Id
    @GeneratedValue(generator = "dashboard_generator")
    private BigInteger id;

    @Column(name = "name")
    private String name;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "description")
    private String description;

    @Column(name = "type_id")
    private int typeId;

    @JoinColumn(name = "parent_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Dashboard parent;

}
