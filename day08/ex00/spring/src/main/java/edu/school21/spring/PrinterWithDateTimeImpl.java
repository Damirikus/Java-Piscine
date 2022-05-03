package edu.school21.spring;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer{

    private Renderer renderer;
    private LocalDateTime localDateTime;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
        this.localDateTime = LocalDateTime.now();
    }

    @Override
    public void printWith(String message) {
        renderer.print(message + " " + localDateTime);
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }
}
