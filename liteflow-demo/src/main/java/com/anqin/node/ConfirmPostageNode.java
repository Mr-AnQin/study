package com.anqin.node;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

/**
 * 确认邮费节点
 *
 * @author Mr.An
 * @date 2024/03/02
 */
@Component
@LiteflowComponent(id = "confirmPostageNode", name = "确认邮费节点")
public class ConfirmPostageNode extends NodeComponent {
    @Override
    public void process() throws Exception {
        //
    }
}
