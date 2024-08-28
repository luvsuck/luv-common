package com.blue;

import com.nasa.LandingOnTheMoon;

/**
 * @Author: zyy
 * @Date: 2024/8/27 16:41
 * @Version:
 * @Description: BlueOrigin提供的着陆方案
 */
public class BlueOriginLand implements LandingOnTheMoon {
    @Override
    public void land() {
        System.out.println("BlueOrigin月球着陆!");
    }
}
