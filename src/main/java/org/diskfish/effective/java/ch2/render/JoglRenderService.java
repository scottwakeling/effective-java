package org.diskfish.effective.java.ch2.render;

class JoglRenderService implements RenderService {
    @Override
    public void describe() {
        System.out.println("I am a JOGL renderer. My implementation is private.");
    }
}
