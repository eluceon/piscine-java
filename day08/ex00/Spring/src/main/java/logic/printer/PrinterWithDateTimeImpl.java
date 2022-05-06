package logic.printer;

import logic.renderer.Renderer;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer{
    private Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String msg) {
        renderer.output(LocalDateTime.now().toString() + " " + msg);
    }
}
