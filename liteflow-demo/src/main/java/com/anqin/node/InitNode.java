package com.anqin.node;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.anqin.context.ProcessFlowContext;
import com.anqin.entity.Order;
import com.anqin.entity.Product;
import com.anqin.utils.CollectionUtil;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;

/**
 * 初始化节点
 *
 * @author Mr.An
 * @date 2024/03/02
 */
@Component
@Slf4j
@LiteflowComponent(id = "initNode", name = "流程启动节点")
public class InitNode extends NodeComponent {

    @Override
    public void process() throws Exception {
        // 流程启动节点，可以做一些初始化操作 如设置默认值

        // 1.获取上下文对象
        ProcessFlowContext contextBean = this.getContextBean(ProcessFlowContext.class);

        // 2.设置默认值
        Order order = new Order()
                .setOrderId(UUID.fastUUID().toString(true))
                .setUserId(RandomUtil.randomNumbers(6))
                .setProductList(contextBean.getProductList())
                .setProductQuantity(contextBean.getProductList().size())
                // 商品总金额
                .setTotalPrice(
                        CollectionUtil.mappingList(contextBean.getProductList(), Product::getProductPrice)
                                .stream().reduce(BigDecimal.ZERO, BigDecimal::add));

        // 3.设置默认商品总金额
        contextBean.setOrder(order);

        log.atInfo().log("「流程启动节点」执行流程启动节点，设置默认值: {}", order);
    }
}
