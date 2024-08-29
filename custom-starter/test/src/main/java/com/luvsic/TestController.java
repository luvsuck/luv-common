package com.luvsic;

import com.luvsic.annotation.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zyy
 * @Date: 2024/8/29 10:58
 * @Version:
 * @Description:
 */
@Slf4j
@RestController
public class TestController {
    @GetMapping("/test")
    @Log(desc = "测试log注解")
    public String ok() {
        StringBuilder msg = new StringBuilder(MDC.get("TRACE_ID") + "---success----");
        for (int i = 0; i < (int) (Math.random() * 3000); i++) {
            msg.append((char) i);
        }
        return msg.toString();
    }
}
