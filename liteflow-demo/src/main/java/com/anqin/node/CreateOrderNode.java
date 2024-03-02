package com.anqin.node;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

/**
 * 创建订单节点
 *
 * @author Mr.An
 * @date 2024/03/02
 */
@Component
@LiteflowComponent(id = "createOrderNode", name = "创建订单节点")
public class CreateOrderNode extends NodeComponent {


    @Override
    public void process() throws Exception {
    }
}
