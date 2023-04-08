package com.medical.store.datechooser;

import javax.swing.*;
import java.awt.*;

public final class Months extends JPanel {

    private Event event;
    private int m;

    public Months() {
        initComponents();
    }

    private void addEvent() {
        for (int i = 0; i < getComponentCount(); i++) {
            ((Button) getComponent(i)).setEvent(event);
        }
    }

    private void initComponents() {
        Button cmd1 = new Button();
        Button cmd2 = new Button();
        Button cmd3 = new Button();
        Button cmd4 = new Button();
        Button cmd5 = new Button();
        Button cmd6 = new Button();
        Button cmd7 = new Button();
        Button cmd8 = new Button();
        Button cmd9 = new Button();
        Button cmd10 = new Button();
        Button cmd11 = new Button();
        Button cmd12 = new Button();

        setBackground(new Color(255, 255, 255));
        setLayout(new GridLayout(4, 4));

        cmd1.setBackground(new Color(255, 255, 255));
        cmd1.setForeground(new Color(75, 75, 75));
        cmd1.setText("January");
        cmd1.setName("1"); // NOI18N
        cmd1.setOpaque(true);
        add(cmd1);

        cmd2.setBackground(new Color(255, 255, 255));
        cmd2.setForeground(new Color(75, 75, 75));
        cmd2.setText("February");
        cmd2.setName("2"); // NOI18N
        cmd2.setOpaque(true);
        add(cmd2);

        cmd3.setBackground(new Color(255, 255, 255));
        cmd3.setForeground(new Color(75, 75, 75));
        cmd3.setText("March");
        cmd3.setName("3"); // NOI18N
        cmd3.setOpaque(true);
        add(cmd3);

        cmd4.setBackground(new Color(255, 255, 255));
        cmd4.setForeground(new Color(75, 75, 75));
        cmd4.setText("April");
        cmd4.setName("4"); // NOI18N
        cmd4.setOpaque(true);
        add(cmd4);

        cmd5.setBackground(new Color(255, 255, 255));
        cmd5.setForeground(new Color(75, 75, 75));
        cmd5.setText("May");
        cmd5.setName("5"); // NOI18N
        cmd5.setOpaque(true);
        add(cmd5);

        cmd6.setBackground(new Color(255, 255, 255));
        cmd6.setForeground(new Color(75, 75, 75));
        cmd6.setText("June");
        cmd6.setName("6"); // NOI18N
        cmd6.setOpaque(true);
        add(cmd6);

        cmd7.setBackground(new Color(255, 255, 255));
        cmd7.setForeground(new Color(75, 75, 75));
        cmd7.setText("July");
        cmd7.setName("7"); // NOI18N
        cmd7.setOpaque(true);
        add(cmd7);

        cmd8.setBackground(new Color(255, 255, 255));
        cmd8.setForeground(new Color(75, 75, 75));
        cmd8.setText("August");
        cmd8.setName("8"); // NOI18N
        cmd8.setOpaque(true);
        add(cmd8);

        cmd9.setBackground(new Color(255, 255, 255));
        cmd9.setForeground(new Color(75, 75, 75));
        cmd9.setText("September");
        cmd9.setName("9"); // NOI18N
        cmd9.setOpaque(true);
        add(cmd9);

        cmd10.setBackground(new Color(255, 255, 255));
        cmd10.setForeground(new Color(75, 75, 75));
        cmd10.setText("October");
        cmd10.setName("10"); // NOI18N
        cmd10.setOpaque(true);
        add(cmd10);

        cmd11.setBackground(new Color(255, 255, 255));
        cmd11.setForeground(new Color(75, 75, 75));
        cmd11.setText("November");
        cmd11.setName("11"); // NOI18N
        cmd11.setOpaque(true);
        add(cmd11);

        cmd12.setBackground(new Color(255, 255, 255));
        cmd12.setForeground(new Color(75, 75, 75));
        cmd12.setText("December");
        cmd12.setName("12"); // NOI18N
        cmd12.setOpaque(true);
        add(cmd12);
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
        addEvent();
    }

}
