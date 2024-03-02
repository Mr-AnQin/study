package com.anqin.node;

import com.anqin.context.ProcessFlowContext;
import com.anqin.entity.Order;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 确认邮费节点
 *
 * @author Mr.An
 * @date 2024/03/02
 */
@Component
@Slf4j
@LiteflowComponent(id = "confirmPostageNode", name = "确认邮费节点")
public class ConfirmPostageNode extends NodeComponent {
    @Override
    public void process() throws Exception {
        // 满 300 减去 邮费

        // 1.获取上下文
        ProcessFlowContext contextBean = this.getContextBean(ProcessFlowContext.class);

        Order order = contextBean.getOrder();

        // 2. 根据用户实际支付价格 计算邮费
        BigDecimal actualPayPrice = order.getActualPayPrice();

        // 3.满 300 免邮
        if (actualPayPrice.compareTo(BigDecimal.valueOf(300)) >= 0) {
            order.setFreightPrice(BigDecimal.ZERO);
            log.atInfo().log("「确认邮费节点」满 300 免邮");
        } else {
            order.setFreightPrice(BigDecimal.valueOf(10));
            log.atInfo().log("「确认邮费节点」不满 300 需要邮费 10 元");
        }

        // 4.更新订单
        contextBean.setOrder(order);
    }
}
