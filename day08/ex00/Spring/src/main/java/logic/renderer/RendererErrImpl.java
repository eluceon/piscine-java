package logic.renderer;

import logic.preprocessor.PreProcessor;

public class RendererErrImpl implements Renderer{
    private PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void output(String msg) {
        System.err.println(preProcessor.translate(msg));
    }
}
