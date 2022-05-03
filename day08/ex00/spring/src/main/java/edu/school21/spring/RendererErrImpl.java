package edu.school21.spring;

public class RendererErrImpl implements Renderer{

    private PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void print(String message) {
        System.err.println(preProcessor.changeRegister(message));
    }

    public PreProcessor getPreProcessor() {
        return preProcessor;
    }

    public void setPreProcessor(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }
}
