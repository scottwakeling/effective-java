package com.diskfish.eff.java.ch2.i1.render;

class JoglRenderProvider implements RenderProvider {
    @Override
    public RenderService newRender() {
        return new JoglRenderService();
    }
}
