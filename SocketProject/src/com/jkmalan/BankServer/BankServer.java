package com.jkmalan.BankServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankServer implements Runnable {

    public void run() {
        Map<String, Double> accounts = new HashMap<>();

        try {
            ServerSocket server = new ServerSocket(8008);
            while (true) {
                Socket client = server.accept();

                InputStream is = client.getInputStream();
                OutputStream os = client.getOutputStream();

                Scanner input = new Scanner(is);
                String command = input.nextLine();

                // Handle data input
                String result = "";
                if (command.equalsIgnoreCase("create")) {

                } else if (command.equalsIgnoreCase("deposit")) {

                } else if (command.equalsIgnoreCase("withdraw")) {

                } else if (command.equalsIgnoreCase("balance")) {

                } else {

                }

                // Handle data output
                PrintWriter output = new PrintWriter(os);
                output.println(result);
                output.flush();

                client.close();
            }
        } catch (IOException e) {
            System.err.println("Something went wrong!");
            e.printStackTrace();
        }
    }

}
