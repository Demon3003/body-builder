package com.zhurawell.base.data.repo.widget;

import com.zhurawell.base.data.model.widget.Widget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface WidgetRepo extends JpaRepository<Widget, BigInteger> {
}
