package logic.printer;

import logic.renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer {
    private Renderer renderer;
    private String prefix;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String msg) {
        renderer.output(prefix + msg);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
