package com.jkmalan.CalculatorServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class CalculatorServer implements Runnable {

    public void run() {
        try {
            ServerSocket server = new ServerSocket(8800);
            while (true) {
                Socket client = server.accept();

                InputStream is = client.getInputStream();
                OutputStream os = client.getOutputStream();

                Scanner input = new Scanner(is);
                String calculation = input.nextLine();

                String result = calculation.replaceAll(" ", "");
                System.out.println("Server: " + result);

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
