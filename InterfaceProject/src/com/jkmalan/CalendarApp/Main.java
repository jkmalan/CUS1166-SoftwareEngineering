package com.jkmalan.CalendarApp;

import com.jkmalan.CalendarApp.gui.CalendarFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame calendarApp = new CalendarFrame(2016, 10);
        calendarApp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        calendarApp.setVisible(true);

    }
}
