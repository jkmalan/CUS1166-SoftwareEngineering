package com.jkmalan.BankServer;

/**
 * The Main class of the BankServer program
 *
 * In order to avoid manually running two separate processes,
 * this class utilizes threading to run the server process in
 * the background, and the client in the foreground
 *
 * @author jkmalan (aka John Malandrakis)
 */
public class Main {

    public static void main(String[] args) {

        // Create a new BankServer thread
        BankServer server = new BankServer();
        Thread thread = new Thread(server);
        thread.start();

        // Begin sending and receiving data from the BankServer
        while (true) {
            BankClient.connect();
        }

    }
}
