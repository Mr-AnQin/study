package com.anqin.context;


import com.anqin.enums.OrderStrategyEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 策略实体
 *
 * @author Mr.An
 * @date 2024/03/02
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class StrategyEntity {

    /**
     * 策略名称
     */
    private OrderStrategyEnum orderStrategy;

    /**
     * 实际支付价格
     */
    private BigDecimal actualPayPrice;

    /**
     * 折扣价
     */
    private BigDecimal discountPrice;
}
