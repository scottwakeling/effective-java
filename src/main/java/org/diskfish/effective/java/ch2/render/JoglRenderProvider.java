package org.diskfish.effective.java.ch2.render;

class JoglRenderProvider implements RenderProvider {
    @Override
    public RenderService newRender() {
        return new JoglRenderService();
    }
}
