package com.anqin.node;

import cn.hutool.core.collection.CollUtil;
import com.anqin.context.ProcessFlowContext;
import com.anqin.context.StrategyEntity;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

/**
 * 确认策略节点
 *
 * @author Mr.An
 * @date 2024/03/02
 */
@Component
@Slf4j
@LiteflowComponent(id = "confirmStrategyNode", name = "确认策略节点")
public class ConfirmStrategyNode extends NodeComponent {

    @Override
    public void process() throws Exception {
        // 该节点用户算出最低的价格


        // 1. 获取上下文对象
        ProcessFlowContext contextBean = this.getContextBean(ProcessFlowContext.class);

        // 2. 获取最小的优惠最小的策略
        List<StrategyEntity> strategyList = contextBean.getStrategyList();

        if (CollUtil.isEmpty(strategyList)) {
            log.atWarn().log("「确认策略节点」没有找到使用的策略！");
            return;
        }

        strategyList.stream()
                .min(Comparator.comparing(StrategyEntity::getActualPayPrice))
                .ifPresentOrElse(entry -> {
                    log.atInfo().log("确认策略节点执行完毕，确认的策略是：{},用户付款最低的价格是：{}，折扣的价格是：{}",
                            entry.getOrderStrategy().getStrategyName(),
                            entry.getActualPayPrice(),
                            entry.getDiscountPrice());

                    contextBean.getOrder()
                            .setActualPayPrice(entry.getActualPayPrice())
                            .setDiscountPrice(entry.getDiscountPrice())
                            .setOrderStrategy(entry.getOrderStrategy());
                }, () -> log.atWarn().log("「确认策略节点」没有找到使用的策略！"));
    }
}
