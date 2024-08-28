package com.spacex;

import com.nasa.LandingOnTheMoon;

/**
 * SpaceX实现着陆月球方案
 *
 * @Author: zyy
 * @Date: 2024/8/27 16:32
 * @Version:
 * @Description:
 */
public class SpaceXLand implements LandingOnTheMoon {
    @Override
    public void land() {
        System.out.println("SpaceX着陆方案!");
    }
}
