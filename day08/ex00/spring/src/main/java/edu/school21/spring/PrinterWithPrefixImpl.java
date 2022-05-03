package edu.school21.spring;

public class PrinterWithPrefixImpl implements Printer{

    private Renderer renderer;
    private String prefix;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
        this.prefix = "prefix";
    }

    @Override
    public void printWith(String message) {
        renderer.print(prefix + " " + message);
    }


    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
