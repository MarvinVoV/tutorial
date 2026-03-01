package com.spring.ai_tutorial.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class OrderTools {

    @Tool(description = "根据订单号查询订单状态")
    public String queryOrder(
            @ToolParam(description = "订单号，格式如 ORD-12345") String orderId) {
        // 模拟查询
        return "订单 " + orderId + " 状态：已发货，预计明天送达";
    }

    @Tool(description = "查询用户的所有订单列表")
    public String listOrders(
            @ToolParam(description = "用户ID") String userId,
            @ToolParam(description = "查询条数，默认10") int limit) {
        return "用户 " + userId + " 最近 " + limit + " 条订单：ORD-001, ORD-002...";
    }
}