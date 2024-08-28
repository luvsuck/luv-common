package com.nasa;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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

    /**
     * 使用java提供的ServiceLoader加载服务提供者
     */
//    private LandingOnTheMoonLoader() {
//        ServiceLoader<LandingOnTheMoon> loader = ServiceLoader.load(LandingOnTheMoon.class);
//        List<LandingOnTheMoon> list = new ArrayList<>();
//        for (LandingOnTheMoon landing : loader) {
//            list.add(landing);
//        }
//        landingOnTheMoons = list;
//        landingOnTheMoon = list.isEmpty() ? null : list.get(0);
//    }

    /**
     * 使用模拟的ServiceLoader加载服务提供者
     */
    private LandingOnTheMoonLoader() {
        ServiceProviderMock<LandingOnTheMoon> loader = ServiceProviderMock.load(LandingOnTheMoon.class);
        LinkedHashMap<String, LandingOnTheMoon> providers = loader.getProviders();
        providers.forEach((k, v) -> {
            System.out.println(k);
            landingOnTheMoons = new ArrayList<>();
            landingOnTheMoons.add(v);
        });
        landingOnTheMoon = landingOnTheMoons.isEmpty() ? null : landingOnTheMoons.get(0);
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
            LandingOnTheMoon landingOn = landingOnTheMoons.get(0);
            landingOn.land();
        }
    }

}
