package edu.school21.spring;

public class RendererStandardImpl implements Renderer{

    private PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void print(String message) {
        System.out.println(preProcessor.changeRegister(message));
    }

    public PreProcessor getPreProcessor() {
        return preProcessor;
    }

    public void setPreProcessor(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }
}
