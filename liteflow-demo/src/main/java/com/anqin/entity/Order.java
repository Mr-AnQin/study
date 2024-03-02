package com.anqin.entity;

import com.anqin.enums.OrderStrategyEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单
 *
 * @author Mr.An
 * @date 2024/03/02
 */
@Data
@Accessors(chain = true)
public class Order {

    /**
     * 订单编号
     */
    private String orderId;


    /**
     * 用户 ID
     */
    private String userId;


    /**
     * 产品列表
     */
    private List<Product> productList;

    /**
     * 订单总金额
     */
    private BigDecimal totalPrice;

    /**
     * 折扣金额
     */
    private BigDecimal discountPrice;

    /**
     * 实际支付价格
     */
    private BigDecimal actualPayPrice;

    /**
     * 订单策略
     */
    private OrderStrategyEnum orderStrategy;

    /**
     * 运费金额
     */
    private BigDecimal freightPrice;

    /**
     * 购买数量
     */
    private Integer productQuantity;
}
