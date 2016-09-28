package com.jkmalan.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class CalculatorFrame extends JFrame {

    private final int FRAME_HEIGHT = 450;
    private final int FRAME_WIDTH = 300;

    private JPanel panel;

    private JTextArea display;
    private List<JButton> buttons = new LinkedList<>();

    private double result = 0;

    public CalculatorFrame() {

        createDisplay();
        createButtons();
        createPanel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public void createDisplay() {
        display = new JTextArea(4, 24);
        display.setEditable(false);
        display.setText("");
    }

    public void createButtons() {

        JButton button1 = new JButton("1");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.append("1\n");
            }
        });
        buttons.add(button1);

        JButton button2 = new JButton("2");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.append("2\n");
            }
        });
        buttons.add(button2);

        JButton button3 = new JButton("3");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.append("3\n");
            }
        });
        buttons.add(button3);

        JButton button4 = new JButton("4");
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.append("4\n");
            }
        });
        buttons.add(button4);

        JButton button5 = new JButton("5");
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.append("5\n");
            }
        });
        buttons.add(button5);

        JButton button6 = new JButton("6");
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.append("6\n");
            }
        });
        buttons.add(button6);

        JButton button7 = new JButton("7");
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.append("7\n");
            }
        });
        buttons.add(button7);

        JButton button8 = new JButton("8");
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.append("8\n");
            }
        });
        buttons.add(button8);

        JButton button9 = new JButton("9");
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.append("9\n");
            }
        });
        buttons.add(button9);

        JButton button0 = new JButton("0");
        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.append("0\n");
            }
        });
        buttons.add(button0);

        JButton buttonMul = new JButton("*");
        buttonMul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.append("*\n");
            }
        });
        buttons.add(buttonMul);

        JButton buttonDiv = new JButton("/");
        buttonDiv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.append("/\n");
            }
        });
        buttons.add(buttonDiv);

        JButton buttonAdd = new JButton("+");
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.append("+\n");
            }
        });
        buttons.add(buttonAdd);

        JButton buttonSub = new JButton("-");
        buttonSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.append("-\n");
            }
        });
        buttons.add(buttonSub);

        JButton buttonMod = new JButton("%");
        buttonMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.append("%\n");
            }
        });
        buttons.add(buttonMod);

        JButton buttonCle = new JButton("CE");
        buttonCle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.append("CE\n");
            }
        });
        buttons.add(buttonCle);

        JButton buttonEnt = new JButton("=");
        buttonEnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.append("=\n");
            }
        });
        buttons.add(buttonEnt);



    }

    public void createPanel() {

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 3));
        for (JButton button : buttons) {
            buttonPanel.add(button);
        }
        panel.add(buttonPanel, BorderLayout.CENTER);

        JScrollPane scroll = new JScrollPane(display);
        panel.add(scroll, BorderLayout.NORTH);
        add(panel);

    }

}
