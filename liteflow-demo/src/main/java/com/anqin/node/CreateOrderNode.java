package com.anqin.node;

import com.anqin.context.ProcessFlowContext;
import com.anqin.entity.Order;
import com.anqin.entity.Product;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建订单节点
 *
 * @author Mr.An
 * @date 2024/03/02
 */
@Slf4j
@Component
@LiteflowComponent(id = "createOrderNode", name = "创建订单节点")
public class CreateOrderNode extends NodeComponent {


    @Override
    public void process() throws Exception {
        // 创建订单的逻辑

        ProcessFlowContext contextBean = this.getContextBean(ProcessFlowContext.class);

        Order order = contextBean.getOrder();
        List<Product> productList = order.getProductList();

        // .... 数据入库操作

        log.atInfo().log("「创建订单节点」创建订单成功：{}", order);
    }
}
