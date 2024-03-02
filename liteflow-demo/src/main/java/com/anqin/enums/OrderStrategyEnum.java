package com.anqin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单策略枚举
 *
 * @author Mr.An
 * @date 2024/03/02
 */
@Getter
@AllArgsConstructor
public enum OrderStrategyEnum {


    FULL_MINUS("01", "满减"),

    DISCOUNT("02", "打折");

    /**
     * 策略代码
     */
    private final String strategyCode;

    /**
     * 策略名称
     */
    private final String strategyName;
}
