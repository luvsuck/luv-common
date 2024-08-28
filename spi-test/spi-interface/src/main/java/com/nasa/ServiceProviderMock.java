package com.nasa;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * @Author: zyy
 * @Date: 2024/8/28 15:31
 * @Version:
 * @Description:
 */
public class ServiceProviderMock<T> {
    private static final String PREFIX = "META-INF/services/";
    private final Class<T> service;
    private final ClassLoader loader;

    private final LinkedHashMap<String, T> providers = new LinkedHashMap<>();

    private ServiceProviderMock(Class<T> svc, ClassLoader cl) {
        service = Objects.requireNonNull(svc, "Service interface cannot be null");
        loader = (cl == null) ? ClassLoader.getSystemClassLoader() : cl;
        reload();
    }

    public static <T> ServiceProviderMock<T> load(Class<T> service) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        return new ServiceProviderMock<>(service, contextClassLoader);
    }

    private void reload() {
        String fullName = PREFIX + service.getName();

        try {
            Enumeration<URL> resources = loader.getResources(fullName);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                InputStream ins = url.openStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(ins, StandardCharsets.UTF_8));
                String name = reader.readLine();
                Class<?> clazz = Class.forName(name, false, loader);
                if (service.isAssignableFrom(clazz)) {
                    T cast = service.cast(clazz.newInstance());
                    providers.put(name, cast);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to load service: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public LinkedHashMap<String, T> getProviders() {
        return providers;
    }
}
