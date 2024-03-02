package com.anqin.config.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * 折扣配置策略
 *
 * @author Mr.An
 * @date 2024/03/02
 */
@Getter
@AllArgsConstructor
public enum DiscountConfigStrategy {



    /**
     * 百分比折扣
     */
    PERCENTAGE_DISCOUNT_20(BigDecimal.valueOf(0.2)),
    PERCENTAGE_DISCOUNT_50(BigDecimal.valueOf(0.5)),
    PERCENTAGE_DISCOUNT_90(BigDecimal.valueOf(0.9));

    private final BigDecimal discount;
}
