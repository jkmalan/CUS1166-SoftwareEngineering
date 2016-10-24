package com.jkmalan.BankServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Manages input from a BankClient in order to manage bank accounts
 *
 * @author jkmalan (aka John Malandrakis)
 */
public class BankServer implements Runnable {

    /**
     * A Runnable method that begins the server process on port 8008
     */
    public void run() {
        Map<String, Double> accounts = new HashMap<>();

        // Initialize some accounts for the sake of simplicity
        accounts.put("john", 1100.0);
        accounts.put("brian", 600.0);
        accounts.put("uche", 200.0);
        accounts.put("celeste", 700.0);
        accounts.put("kamila", 50.0);
        accounts.put("nicholas", 300.0);
        accounts.put("james", 1600.0);
        accounts.put("ying", 32000.0);
        accounts.put("jason", 0.50);
        accounts.put("ralph", 100.0);

        try {
            ServerSocket server = new ServerSocket(8008);

            while (true) {
                Socket client = server.accept();

                InputStream is = client.getInputStream();
                OutputStream os = client.getOutputStream();

                Scanner input = new Scanner(is);
                String command = input.nextLine();
                String[] args = command.split(" ");

                String data;
                try {
                    if (args.length == 2 && args[0].equalsIgnoreCase("balance")) {

                        // If argument 1 is an asterisk, list all account balances
                        if (args[1].equalsIgnoreCase("*")) {

                            data = "\n- - Accounts - -";
                            for (Map.Entry<String, Double> account : accounts.entrySet()) {
                                data += "\n" + account.getKey() + ": $" + account.getValue();
                            }

                        // If argument 1 is a valid account name, display account balance
                        } else if (accounts.containsKey(args[1])) {

                            data = "\n- - Account - -";
                            data += "\n" + args[1] + ": $" + accounts.get(args[1]);

                        // If argument 1 is an invalid argument, display input error
                        } else {

                            data = "Invalid input; Argument 1 must be an existing account name!";

                        }

                    } else if (args.length == 3 && args[0].equalsIgnoreCase("deposit")) {

                        // If argument 1 is a valid account name, deposit the amount of argument 2 into the account
                        if (accounts.containsKey(args[1])) {

                            double temp = accounts.get(args[1]) + Double.parseDouble(args[2]);
                            accounts.put(args[1], temp);

                            data = "\n- - Account - -";
                            data += "\n" + args[1] + ": $" + accounts.get(args[1]);

                        // If argument 1 is an invalid argument, display input error
                        } else {

                            data = "Invalid input; Argument 1 must be an existing account name!";

                        }

                    } else if (args.length == 3 && args[0].equalsIgnoreCase("withdraw")) {

                        // If argument 1 is a valid account name, withdraw the amount of argument 2 from the account
                        if (accounts.containsKey(args[1])) {

                            double newBal = accounts.get(args[1]) - Double.parseDouble(args[2]);
                            if (newBal >= 0) {
                                accounts.put(args[1], newBal);

                                data = "\n- - Account - -";
                                data += "\n" + args[1] + ": $" + accounts.get(args[1]);
                            } else {
                                data = "The specified amount exceeds available funds!";
                            }

                        // If argument 1 is an invalid argument, display input error
                        } else {

                            data = "Invalid input; Argument 1 must be an existing account name!";

                        }

                    } else if (args.length == 3 && args[0].equalsIgnoreCase("create")) {

                        // If argument 1 is a non-existent account name, create a new account with argument 2 as the balance
                        if (!accounts.containsKey(args[1])) {
                            accounts.put(args[1], Double.parseDouble(args[2]));
                            data = "Created account [" + args[1] + "] with an initial balance of $" + accounts.get(args[1]).toString();

                        // If argument 1 is an existing account name, display input error
                        } else {
                            data = "Invalid input; Argument 1 is already an existing account name!";
                        }

                    } else {
                        data = "- - - - - Help Menu - - - - -" +
                                "\n balance [account]" +
                                "\n deposit [account] [amount]" +
                                "\n withdraw [account] [amount]" +
                                "\n create [account] [amount]";
                    }
                } catch (NumberFormatException e) {

                    data = "Invalid input; Argument 2 must be a valid number!";

                }

                PrintWriter output = new PrintWriter(os);
                output.println(data + "\n");
                output.flush();

                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
