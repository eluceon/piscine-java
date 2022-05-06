package logic.renderer;

import logic.preprocessor.PreProcessor;

public class RendererStandardImpl implements Renderer{
    private PreProcessor preProcessor;


    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void output(String msg) {
        System.out.println(preProcessor.translate(msg));
    }
}
