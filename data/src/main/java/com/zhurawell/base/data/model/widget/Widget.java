package com.zhurawell.base.data.model.widget;

import com.zhurawell.base.data.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@Setter
@Entity
@Table(name = "widget")
@NoArgsConstructor
@SequenceGenerator(name = "widget_generator", sequenceName = "widget_seq", allocationSize = 20, initialValue = 5, schema = "public")
public class Widget extends BaseEntity {

    @Id
    @GeneratedValue(generator = "widget_generator")
    private BigInteger id;

    @Column(name = "name")
    private String name;

    @Column(name = "type_id")
    private int typeId;

    @Column(name = "data")
    @Type(type = "StringJsonUserType")
    private String data;

    @Column
    private BigInteger parentId;

    @Override
    public String toString() {
        return "Widget{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", parentId=" + parentId +
                '}';
    }
}
