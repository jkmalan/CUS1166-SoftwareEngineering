package com.jkmalan.CalculatorServer;

public class Main {

    public static void main(String[] args) {

        CalculatorServer server = new CalculatorServer();
        Thread thread = new Thread(server);
        thread.start();

        while (true) {
            CalculatorClient.connect();
        }

    }

}
