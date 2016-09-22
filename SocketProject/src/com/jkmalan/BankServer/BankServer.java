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

            boolean killServer = false;
            while (!killServer) {
                Socket client = server.accept();

                InputStream is = client.getInputStream();
                OutputStream os = client.getOutputStream();

                Scanner input = new Scanner(is);
                String command = input.nextLine();
                String[] args = command.split(" ");

                String result;
                try {
                    if (args.length == 2 && args[0].equalsIgnoreCase("balance")) {
                        if (args[1].equalsIgnoreCase("*")) {
                            result = "- - - - - Accounts - - - - -";
                            for (Map.Entry<String, Double> account : accounts.entrySet()) {
                                result += "\n" + account.getKey() + ": $" + account.getValue();
                            }
                        } else if (accounts.containsKey(args[1])) {
                            result = "The balance for account [" + args[1] + "] is $" + accounts.get(args[1]).toString();
                        } else {
                            result = "The specified account does not exist!";
                        }
                    } else if (args.length == 3 && args[0].equalsIgnoreCase("deposit")) {
                        if (accounts.containsKey(args[1])) {
                            double temp = accounts.get(args[1]) + Double.parseDouble(args[2]);
                            accounts.put(args[1], temp);
                            result = "The balance for account [" + args[1] + "] is $" + accounts.get(args[1]).toString();
                        } else {
                            result = "The specified account does not exist!";
                        }
                    } else if (args.length == 3 && args[0].equalsIgnoreCase("withdraw")) {
                        if (accounts.containsKey(args[1])) {
                            double temp = accounts.get(args[1]) - Double.parseDouble(args[2]);
                            if (temp >= 0) {
                                accounts.put(args[1], temp);
                                result = "The balance for account [" + args[1] + "] is $" + accounts.get(args[1]).toString();
                            } else {
                                result = "The specified amount exceeds available funds!";
                            }
                        } else {
                            result = "The specified account does not exist!";
                        }
                    } else if (args.length == 3 && args[0].equalsIgnoreCase("create")) {
                        if (!accounts.containsKey(args[1])) {
                            accounts.put(args[1], Double.parseDouble(args[2]));
                            result = "The balance for account [" + args[1] + "] is $" + accounts.get(args[1]).toString();
                        } else {
                            result = "The specified account already exists!";
                        }
                    } else if (args.length > 0 && args[0].equalsIgnoreCase("kill")) {
                        result = "Killing the server process...";
                        killServer = true;
                    } else {
                        result = "- - - - - Help Menu - - - - -" +
                                "\n balance [account]" +
                                "\n deposit [account] [amount]" +
                                "\n withdraw [account] [amount]" +
                                "\n create [account] [amount]";
                    }
                } catch (NumberFormatException e) {
                    result = "Invalid argument! Please make sure the amount argument is a number!";
                }

                // Handle data output
                PrintWriter output = new PrintWriter(os);
                output.println(result + "\n");
                output.flush();

                client.close();
            }
        } catch (IOException e) {
            System.err.println("Something went wrong!");
            e.printStackTrace();
        }
    }

}
