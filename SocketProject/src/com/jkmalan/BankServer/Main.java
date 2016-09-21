package com.jkmalan.BankServer;

public class Main {

    public static void main(String[] args) {

        BankServer server = new BankServer();
        Thread thread = new Thread(server);
        thread.start();

        while (true) {
            BankClient.connect();
        }

    }
}
