package com.medical.store.main;

import java.awt.*;

public class Printer {
    public static void main(String[] args) {
        new PrintGraphics();
    }
}

class PrintGraphics extends Frame {
    PrintGraphics() {
        PrintJob p = getToolkit().getPrintJob(this, "Print graphics", null);
        Graphics g = p.getGraphics();
        g.drawRect(1, 1, 40, 40);
        g.drawLine(1, 1, 40, 40);
        g.dispose();
        p.end();
    }

}
        

