package com.spring.ai_tutorial.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class WeatherTools {

    @Tool(description = "获取指定城市的天气信息")
    public String getWeather(String city) {
        System.out.println(">>> AI 调用了 getWeather，参数：city=" + city);
        // 模拟调用天气 API
        return city + " 今天晴天，气温25°C，湿度60%";
    }

    @Tool(description = "获取当前日期和时间")
    public String getCurrentTime() {
        return "当前时间：" + java.time.LocalDateTime.now();
    }
}