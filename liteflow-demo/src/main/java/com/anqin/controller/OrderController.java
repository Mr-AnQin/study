package com.anqin.controller;

import cn.hutool.core.lang.UUID;
import com.anqin.common.Result;
import com.anqin.context.ProcessFlowContext;
import com.anqin.entity.Product;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 订单控制器
 *
 * @author Mr.An
 * @date 2024/03/02
 */
@RestController
@RequestMapping("/order")
public class OrderController {


    @Resource
    private FlowExecutor flowExecutor;

    /**
     * 创建订单
     *
     * @return {@link Result}<{@link ?}>
     */
    @PostMapping("/createOrder")
    public Result<?> createOrder() {

        // 1.创建上下文
        Product mac = new Product(UUID.fastUUID().toString(true), "笔记本", "Mac M1", BigDecimal.valueOf(15600));
        Product windows = new Product(UUID.fastUUID().toString(true), "笔记本", "Windows", BigDecimal.valueOf(15600));

        ProcessFlowContext flowContext = new ProcessFlowContext().setProductList(Arrays.asList(mac, windows));

        // 2.执行
        LiteflowResponse response = flowExecutor.execute2Resp("normalCreateOrder", null, flowContext);

        // 3.获取上下文中对象
        ProcessFlowContext contextBean = response.getContextBean(ProcessFlowContext.class);
        if (response.isSuccess()) {
            return Result.ok(contextBean.getOrder());
        }
        return Result.error("执行失败！");
    }
}
