package com.anqin.context;

import com.anqin.entity.Order;
import com.anqin.entity.Product;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.LinkedList;
import java.util.List;

/**
 * 流程上下文
 *
 * @author Mr.An
 * @date 2024/03/02
 */
@Data
@Accessors(chain = true)
public class ProcessFlowContext {


    /**
     * 订单
     */
    private Order order;

    /**
     * 商品
     */
    private List<Product> productList;


    /**
     * 策略计算结果 集合
     */
    private List<StrategyEntity> strategyList = new LinkedList<>();

}
