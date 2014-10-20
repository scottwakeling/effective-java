package org.diskfish.effective.java.ch2.render;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RenderServices {
    
    private static final Map<String, RenderProvider> providers = new ConcurrentHashMap<>();
    public static final String DEFAULT_RENDERER = "JOGL";
    
    static {
        RenderServices.registerDefault(new JoglRenderProvider());
    }

    private RenderServices() { /* ILB */ }

    public static void registerDefault(RenderProvider rp) {
        registerRenderProvider(DEFAULT_RENDERER, rp);
    }

    public static void registerRenderProvider(String name, RenderProvider rp) {
        providers.put(name, rp);
    }
    
    public static RenderService newInstance() {
        return newInstance(DEFAULT_RENDERER);
    }
    
    public static RenderService newInstance(String name) {
        RenderProvider rp = providers.get(name);
        if (rp == null) {
            throw new IllegalArgumentException("No render provider registered with name: " + name);
        }
        return rp.newRender();
    }

}
