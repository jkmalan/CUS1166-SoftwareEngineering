package com.jkmalan.CalendarApp.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarFrame extends JFrame {

    private int[] DAYS_PER_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int[] DAY_OF_MONTH = {6, 2, 3, 6, 1, 4, 6, 2, 5, 7, 3, 5};

    private JPanel calendarPanel;

    private Map<Integer, List<String>> eventsMap = new HashMap<>();

    public CalendarFrame(int year, int month) {
        calendarPanel = new JPanel();
        calendarPanel.setLayout(new BorderLayout());

        JTextPane header = new JTextPane();
        header.setEditable(false);
        header.setText(Month.of(month).name());
        header.setSize(30, 30);

        JButton eventButton = new JButton("Display Events");
        eventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String displayOutput = "Event List: \n";
                for (Map.Entry<Integer, List<String>> event : eventsMap.entrySet()) {
                    int day = event.getKey();
                    List<String> events = event.getValue();

                    displayOutput += "Day " + day + ": \n";
                    for (String nameDescription : events) {
                        displayOutput += "    " + nameDescription + "\n";
                    }
                }

                JOptionPane.showInputDialog(null, displayOutput);
            }
        });

        calendarPanel.add(BorderLayout.NORTH, header);
        calendarPanel.add(BorderLayout.CENTER, createMonth(month));
        calendarPanel.add(BorderLayout.SOUTH, eventButton);

        add(calendarPanel);
        setSize(1024, 768);
    }

    public JPanel createMonth(int month) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 7));

        int days = DAYS_PER_MONTH[month - 1];
        int start = DAY_OF_MONTH[month - 1];
        for (int i = 1; i < 43; i++) {
            if (i < start || i >= start + days) {
                buttonPanel.add(createBlank());
            } else {
                buttonPanel.add(createDay(i - (start - 1)));
            }
        }

        return buttonPanel;
    }

    public JButton createDay(int day) {
        JButton button = new JButton(Integer.toString(day));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = JOptionPane.showInputDialog("Name your new event: ");
                if (!eventName.isEmpty()) {
                    String eventDescription = JOptionPane.showInputDialog("Describe your event: ");
                    if (!eventDescription.isEmpty()) {
                        if (!eventsMap.containsKey(day)) {
                            eventsMap.put(day, new ArrayList<>());
                        }

                        List<String> eventsList = eventsMap.get(day);
                        eventsList.add(eventName + ": " + eventDescription);
                        eventsMap.put(day, eventsList);
                    }
                }
            }
        });

        return button;
    }

    public JButton createBlank() {
        return new JButton();
    }

}
