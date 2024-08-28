package com.nasa;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @Author: zyy
 * @Date: 2024/8/27 16:16
 * @Version:
 * @Description: 具体的服务加载类
 */
public class LandingOnTheMoonLoader {
    private static volatile LandingOnTheMoonLoader LOADER;
    private final LandingOnTheMoon landingOnTheMoon;
    private List<LandingOnTheMoon> landingOnTheMoons;

    private LandingOnTheMoonLoader() {
        ServiceLoader<LandingOnTheMoon> loader = ServiceLoader.load(LandingOnTheMoon.class);
        List<LandingOnTheMoon> list = new ArrayList<>();
        for (LandingOnTheMoon landing : loader) {
            list.add(landing);
        }
        landingOnTheMoons = list;
        landingOnTheMoon = list.isEmpty() ? null : list.get(0);
    }

    public static LandingOnTheMoonLoader getLOADER() {
        if (LOADER == null) {
            synchronized (LandingOnTheMoonLoader.class) {
                if (LOADER == null)
                    LOADER = new LandingOnTheMoonLoader();
            }
        }
        return LOADER;
    }

    public void land() {
        if (landingOnTheMoons.isEmpty()) {
            System.out.println("着陆月球服务未加载!");
        } else {
            LandingOnTheMoon landingOnTheMoon = landingOnTheMoons.get(0);
            landingOnTheMoon.land();
        }
    }

}
