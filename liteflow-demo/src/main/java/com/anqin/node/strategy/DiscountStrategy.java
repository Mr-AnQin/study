package com.anqin.node.strategy;

import com.anqin.config.enums.DiscountConfigStrategy;
import com.anqin.context.ProcessFlowContext;
import com.anqin.context.StrategyEntity;
import com.anqin.entity.Order;
import com.anqin.enums.OrderStrategyEnum;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 折扣策略
 *
 * @author Mr.An
 * @date 2024/03/02
 */
@Component
@Slf4j
@LiteflowComponent(id = "discountStrategy", name = "执行折扣策略")
public class DiscountStrategy extends NodeComponent {

    /**
     * 实现 满减策略
     *
     * @throws Exception 例外
     */
    @Override
    public void process() throws Exception {

        // 1. 获取上下文对象,并添加默认数据
        ProcessFlowContext contextBean = this.getContextBean(ProcessFlowContext.class);
        Order order = contextBean.getOrder().setOrderStrategy(OrderStrategyEnum.DISCOUNT);


        // 2.获取所有商品的总金额
        BigDecimal totalPrice = order.getTotalPrice();

        // 3. 获取商品的满减规则
        BigDecimal discount = DiscountConfigStrategy.PERCENTAGE_DISCOUNT_50.getDiscount();


        // 4. 执行满减策略
        BigDecimal actualPrice = totalPrice.multiply(discount);
        order.setDiscountPrice(actualPrice);

        log.atInfo().log("「折扣策略」执行完毕 共折扣{}元", order.getDiscountPrice());

        // 5. 添加策略
        contextBean.getStrategyList()
                .add(new StrategyEntity(OrderStrategyEnum.DISCOUNT,actualPrice, totalPrice.subtract(actualPrice)));
    }
}
