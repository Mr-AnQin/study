package com.anqin.node.strategy;

import com.anqin.config.enums.FullMinusConfigStrategyEnum;
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
 * 满减法策略
 *
 * @author Mr.An
 * @date 2024/03/02
 */
@Component
@Slf4j
@LiteflowComponent(id = "fullMinusStrategy", name = "执行满减法策略")
public class FullMinusStrategy extends NodeComponent {


    /**
     * 实现 满减策略
     *
     * @throws Exception 例外
     */
    @Override
    public void process() throws Exception {

        // 1. 获取上下文对象,并添加默认数据
        ProcessFlowContext contextBean = this.getContextBean(ProcessFlowContext.class);
        Order order = contextBean.getOrder().setOrderStrategy(OrderStrategyEnum.FULL_MINUS);


        // 2.获取所有商品的总金额
        BigDecimal totalPrice = order.getTotalPrice();

        // 3. 获取商品的满减规则
        FullMinusConfigStrategyEnum bargainPrice = FullMinusConfigStrategyEnum.BARGAIN_PRICE;


        // 4. 执行满减策略
        if (totalPrice.compareTo(bargainPrice.getFull()) < 0) {
            log.atWarn().log("「执行满减法策略」商品总价{}元未达到满减条件", totalPrice);
            return;
        }

        BigDecimal minus = bargainPrice.getMinus();

        order.setDiscountPrice(minus);


        log.atInfo().log("「执行满减法策略」执行完毕 共折扣{}元", minus);


        // 5. 添加策略
        contextBean.getStrategyList()
                .add(new StrategyEntity(OrderStrategyEnum.FULL_MINUS, totalPrice.subtract(minus), minus));
    }
}
