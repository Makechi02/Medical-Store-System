package com.medical.store.datechooser;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class Dates extends JPanel {

    private Event event;
    private final int MONTH;
    private final int YEAR;
    private final int DAY;
    private int m;
    private int y;
    private int selectDay = 0;
    private int startDate;
    private int max_of_month;

    public Dates() {
        initComponents();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String toDay = df.format(date);
        DAY = Integer.parseInt(toDay.split("-")[0]);
        MONTH = Integer.parseInt(toDay.split("-")[1]);
        YEAR = Integer.parseInt(toDay.split("-")[2]);
    }

    public void showDate(int month, int year, SelectedDate select) {
        m = month;
        y = year;
        // selectDay = 0;
        Calendar cd = Calendar.getInstance();
        cd.set(year, month - 1, 1);
        int start = cd.get(Calendar.DAY_OF_WEEK);
        max_of_month = cd.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (start == 1) {
            start += 7;
        }
        clear();
        start += 5;
        startDate = start;
        for (int i = 1; i <= max_of_month; i++) {
            Button cmd = (Button) getComponent(start);
            cmd.setColorSelected(getForeground());
            cmd.setText(String.valueOf(i));
            if (i == DAY && month == MONTH && year == YEAR) {
                cmd.setBackground(new Color(224, 214, 229));
            } else {
                cmd.setBackground(Color.WHITE);
            }
            if (i == select.getDay() && month == select.getMonth() && year == select.getYear()) {
                cmd.setBackground(getForeground());
                cmd.setForeground(new Color(255, 255, 255));
            }
            start++;
        }
    }

    private void clear() {
        for (int i = 7; i < getComponentCount(); i++) {
            ((JButton) getComponent(i)).setText("");
        }
    }

    public void clearSelected() {
        for (int i = 7; i < getComponentCount(); i++) {
            JButton cmd = (JButton) getComponent(i);
            if (MONTH == m && y == YEAR && !cmd.getText().equals("") && Integer.parseInt(cmd.getText()) == DAY) {
                cmd.setBackground(new Color(224, 214, 229));
                cmd.setForeground(new Color(75, 75, 75));
            } else {
                cmd.setBackground(Color.WHITE);
                cmd.setForeground(new Color(75, 75, 75));
            }
        }
        selectDay = 0;
    }

    private void addEvent() {
        for (int i = 7; i < getComponentCount(); i++) {
            ((Button) getComponent(i)).setEvent(event);
        }
    }

    public void setSelected(int index) {
        selectDay = index;
    }


    private void initComponents() {
        Button cmdMo = new Button();
        Button cmdTu = new Button();
        Button cmdWe = new Button();
        Button cmdTh = new Button();
        Button cmdFr = new Button();
        Button cmdSa = new Button();
        Button cmdSu = new Button();
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
        Button cmd13 = new Button();
        Button cmd14 = new Button();
        Button cmd15 = new Button();
        Button cmd16 = new Button();
        Button cmd17 = new Button();
        Button cmd18 = new Button();
        Button cmd19 = new Button();
        Button cmd20 = new Button();
        Button cmd21 = new Button();
        Button cmd22 = new Button();
        Button cmd23 = new Button();
        Button cmd24 = new Button();
        Button cmd25 = new Button();
        Button cmd26 = new Button();
        Button cmd27 = new Button();
        Button cmd28 = new Button();
        Button cmd29 = new Button();
        Button cmd30 = new Button();
        Button cmd31 = new Button();
        Button cmd32 = new Button();
        Button cmd33 = new Button();
        Button cmd34 = new Button();
        Button cmd35 = new Button();
        Button cmd36 = new Button();
        Button cmd37 = new Button();
        Button cmd38 = new Button();
        Button cmd39 = new Button();
        Button cmd40 = new Button();
        Button cmd41 = new Button();
        Button cmd42 = new Button();

        setBackground(new Color(255, 255, 255));
        setLayout(new GridLayout(7, 7));

        cmdMo.setBorder(BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdMo.setForeground(new Color(118, 118, 118));
        cmdMo.setText("Mo");
        add(cmdMo);

        cmdTu.setBorder(BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdTu.setForeground(new Color(118, 118, 118));
        cmdTu.setText("Tu");
        add(cmdTu);

        cmdWe.setBorder(BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdWe.setForeground(new Color(118, 118, 118));
        cmdWe.setText("We");
        add(cmdWe);

        cmdTh.setBorder(BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdTh.setForeground(new Color(118, 118, 118));
        cmdTh.setText("Th");
        add(cmdTh);

        cmdFr.setBorder(BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdFr.setForeground(new Color(118, 118, 118));
        cmdFr.setText("Fr");
        add(cmdFr);

        cmdSa.setBorder(BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdSa.setForeground(new Color(118, 118, 118));
        cmdSa.setText("Sa");
        add(cmdSa);

        cmdSu.setBorder(BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdSu.setForeground(new Color(255, 1, 1));
        cmdSu.setText("Su");
        add(cmdSu);

        cmd1.setBackground(new Color(255, 255, 255));
        cmd1.setForeground(new Color(75, 75, 75));
        cmd1.setName("day"); // NOI18N
        add(cmd1);

        cmd2.setBackground(new Color(255, 255, 255));
        cmd2.setForeground(new Color(75, 75, 75));
        cmd2.setName("day"); // NOI18N
        add(cmd2);

        cmd3.setBackground(new Color(255, 255, 255));
        cmd3.setForeground(new Color(75, 75, 75));
        cmd3.setText("1");
        cmd3.setName("day"); // NOI18N
        add(cmd3);

        cmd4.setBackground(new Color(255, 255, 255));
        cmd4.setForeground(new Color(75, 75, 75));
        cmd4.setText("2");
        cmd4.setName("day"); // NOI18N
        add(cmd4);

        cmd5.setBackground(new Color(255, 255, 255));
        cmd5.setForeground(new Color(75, 75, 75));
        cmd5.setText("3");
        cmd5.setName("day"); // NOI18N
        add(cmd5);

        cmd6.setBackground(new Color(255, 255, 255));
        cmd6.setForeground(new Color(75, 75, 75));
        cmd6.setText("4");
        cmd6.setName("day"); // NOI18N
        add(cmd6);

        cmd7.setBackground(new Color(255, 255, 255));
        cmd7.setForeground(new Color(75, 75, 75));
        cmd7.setText("5");
        cmd7.setName("day"); // NOI18N
        add(cmd7);

        cmd8.setBackground(new Color(255, 255, 255));
        cmd8.setForeground(new Color(75, 75, 75));
        cmd8.setText("6");
        cmd8.setName("day"); // NOI18N
        add(cmd8);

        cmd9.setBackground(new Color(255, 255, 255));
        cmd9.setForeground(new Color(75, 75, 75));
        cmd9.setText("7");
        cmd9.setName("day"); // NOI18N
        add(cmd9);

        cmd10.setBackground(new Color(255, 255, 255));
        cmd10.setForeground(new Color(75, 75, 75));
        cmd10.setText("8");
        cmd10.setName("day"); // NOI18N
        add(cmd10);

        cmd11.setBackground(new Color(255, 255, 255));
        cmd11.setForeground(new Color(75, 75, 75));
        cmd11.setText("9");
        cmd11.setName("day"); // NOI18N
        add(cmd11);

        cmd12.setBackground(new Color(255, 255, 255));
        cmd12.setForeground(new Color(75, 75, 75));
        cmd12.setText("10");
        cmd12.setName("day"); // NOI18N
        add(cmd12);

        cmd13.setBackground(new Color(255, 255, 255));
        cmd13.setForeground(new Color(75, 75, 75));
        cmd13.setText("11");
        cmd13.setName("day"); // NOI18N
        add(cmd13);

        cmd14.setBackground(new Color(255, 255, 255));
        cmd14.setForeground(new Color(75, 75, 75));
        cmd14.setText("12");
        cmd14.setName("day"); // NOI18N
        add(cmd14);

        cmd15.setBackground(new Color(255, 255, 255));
        cmd15.setForeground(new Color(75, 75, 75));
        cmd15.setText("13");
        cmd15.setName("day"); // NOI18N
        add(cmd15);

        cmd16.setBackground(new Color(255, 255, 255));
        cmd16.setForeground(new Color(75, 75, 75));
        cmd16.setText("14");
        cmd16.setName("day"); // NOI18N
        add(cmd16);

        cmd17.setBackground(new Color(255, 255, 255));
        cmd17.setForeground(new Color(75, 75, 75));
        cmd17.setText("15");
        cmd17.setName("day"); // NOI18N
        add(cmd17);

        cmd18.setBackground(new Color(255, 255, 255));
        cmd18.setForeground(new Color(75, 75, 75));
        cmd18.setText("16");
        cmd18.setName("day"); // NOI18N
        add(cmd18);

        cmd19.setBackground(new Color(255, 255, 255));
        cmd19.setForeground(new Color(75, 75, 75));
        cmd19.setText("17");
        cmd19.setName("day"); // NOI18N
        add(cmd19);

        cmd20.setBackground(new Color(255, 255, 255));
        cmd20.setForeground(new Color(75, 75, 75));
        cmd20.setText("18");
        cmd20.setName("day"); // NOI18N
        add(cmd20);

        cmd21.setBackground(new Color(255, 255, 255));
        cmd21.setForeground(new Color(75, 75, 75));
        cmd21.setText("19");
        cmd21.setName("day"); // NOI18N
        add(cmd21);

        cmd22.setBackground(new Color(255, 255, 255));
        cmd22.setForeground(new Color(75, 75, 75));
        cmd22.setText("20");
        cmd22.setName("day"); // NOI18N
        add(cmd22);

        cmd23.setBackground(new Color(255, 255, 255));
        cmd23.setForeground(new Color(75, 75, 75));
        cmd23.setText("21");
        cmd23.setName("day"); // NOI18N
        add(cmd23);

        cmd24.setBackground(new Color(255, 255, 255));
        cmd24.setForeground(new Color(75, 75, 75));
        cmd24.setText("22");
        cmd24.setName("day"); // NOI18N
        add(cmd24);

        cmd25.setBackground(new Color(255, 255, 255));
        cmd25.setForeground(new Color(75, 75, 75));
        cmd25.setText("23");
        cmd25.setName("day"); // NOI18N
        add(cmd25);

        cmd26.setBackground(new Color(255, 255, 255));
        cmd26.setForeground(new Color(75, 75, 75));
        cmd26.setText("24");
        cmd26.setName("day"); // NOI18N
        add(cmd26);

        cmd27.setBackground(new Color(255, 255, 255));
        cmd27.setForeground(new Color(75, 75, 75));
        cmd27.setText("25");
        cmd27.setName("day"); // NOI18N
        add(cmd27);

        cmd28.setBackground(new Color(255, 255, 255));
        cmd28.setForeground(new Color(75, 75, 75));
        cmd28.setText("26");
        cmd28.setName("day"); // NOI18N
        add(cmd28);

        cmd29.setBackground(new Color(255, 255, 255));
        cmd29.setForeground(new Color(75, 75, 75));
        cmd29.setText("27");
        cmd29.setName("day"); // NOI18N
        add(cmd29);

        cmd30.setBackground(new Color(255, 255, 255));
        cmd30.setForeground(new Color(75, 75, 75));
        cmd30.setText("28");
        cmd30.setName("day"); // NOI18N
        add(cmd30);

        cmd31.setBackground(new Color(255, 255, 255));
        cmd31.setForeground(new Color(75, 75, 75));
        cmd31.setText("29");
        cmd31.setName("day"); // NOI18N
        add(cmd31);

        cmd32.setBackground(new Color(255, 255, 255));
        cmd32.setForeground(new Color(75, 75, 75));
        cmd32.setText("30");
        cmd32.setName("day"); // NOI18N
        add(cmd32);

        cmd33.setBackground(new Color(255, 255, 255));
        cmd33.setForeground(new Color(75, 75, 75));
        cmd33.setText("31");
        cmd33.setName("day"); // NOI18N
        add(cmd33);

        cmd34.setBackground(new Color(255, 255, 255));
        cmd34.setForeground(new Color(75, 75, 75));
        cmd34.setName("day"); // NOI18N
        add(cmd34);

        cmd35.setBackground(new Color(255, 255, 255));
        cmd35.setForeground(new Color(75, 75, 75));
        cmd35.setName("day"); // NOI18N
        add(cmd35);

        cmd36.setBackground(new Color(255, 255, 255));
        cmd36.setForeground(new Color(75, 75, 75));
        cmd36.setName("day"); // NOI18N
        add(cmd36);

        cmd37.setBackground(new Color(255, 255, 255));
        cmd37.setForeground(new Color(75, 75, 75));
        cmd37.setName("day"); // NOI18N
        add(cmd37);

        cmd38.setBackground(new Color(255, 255, 255));
        cmd38.setForeground(new Color(75, 75, 75));
        cmd38.setName("day"); // NOI18N
        add(cmd38);

        cmd39.setBackground(new Color(255, 255, 255));
        cmd39.setForeground(new Color(75, 75, 75));
        cmd39.setName("day"); // NOI18N
        add(cmd39);

        cmd40.setBackground(new Color(255, 255, 255));
        cmd40.setForeground(new Color(75, 75, 75));
        cmd40.setName("day"); // NOI18N
        add(cmd40);

        cmd41.setBackground(new Color(255, 255, 255));
        cmd41.setForeground(new Color(75, 75, 75));
        cmd41.setName("day"); // NOI18N
        add(cmd41);

        cmd42.setBackground(new Color(255, 255, 255));
        cmd42.setForeground(new Color(75, 75, 75));
        cmd42.setName("day"); // NOI18N
        add(cmd42);
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
        addEvent();
    }

    public void next() {
        if (selectDay == max_of_month) {
            selectDay = 0;
        }
        JButton cmd = (JButton) getComponent(startDate - 1 + selectDay + 1);
        String n = cmd.getText();
        if (!n.equals("") && Integer.parseInt(n) <= max_of_month) {
            selectDay++;
            event.execute(null, selectDay);
            cmd.setBackground(new Color(206, 110, 245));
        }
    }

    public void back() {
        if (selectDay <= 1) {
            selectDay = max_of_month + 1;
        }
        JButton cmd = (JButton) getComponent(startDate - 1 + selectDay - 1);
        String n = cmd.getText();
        if (!n.equals("") && cmd.getName() != null) {
            selectDay--;
            event.execute(null, selectDay);
            cmd.setBackground(new Color(206, 110, 245));
        }
    }

    public void up() {
        JButton cmd = (JButton) getComponent(startDate - 1 + selectDay - 7);
        String n = cmd.getText();
        if (!n.equals("") && cmd.getName() != null) {
            selectDay -= 7;
            event.execute(null, selectDay);
            cmd.setBackground(new Color(206, 110, 245));
        }
    }

    public void down() {
        if (getComponents().length > startDate - 1 + selectDay + 7) {
            JButton cmd = (JButton) getComponent(startDate - 1 + selectDay + 7);
            String n = cmd.getText();
            if (!n.equals("") && cmd.getName() != null) {
                selectDay += 7;
                event.execute(null, selectDay);
                cmd.setBackground(new Color(206, 110, 245));
            }
        }
    }

}
