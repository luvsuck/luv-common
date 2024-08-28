package com.luvsic;

import com.nasa.LandingOnTheMoonLoader;

/**
 * @Author: zyy
 * @Date: 2024/8/27 17:10
 * @Version:
 * @Description: 着陆测试
 */
public class LandingTest {
    public static void main(String[] args) {
        //在pom里加载不同的服务依赖来切换不同的着陆方案
        LandingOnTheMoonLoader service = LandingOnTheMoonLoader.getLOADER();
        service.land();
    }
}
