package com.jkmalan.BankServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BankClient {

    public static void connect() {
        try {
            Socket socket = new Socket("127.0.0.1", 8008);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            Scanner input = new Scanner(is);
            PrintWriter output = new PrintWriter(os);

            Scanner user = new Scanner(System.in);
            System.out.println("Please input a command or type 'help' for help...");
            String expression = user.nextLine();
            output.println(expression);
            output.flush();

            while (input.hasNextLine()) {
                System.out.println(input.nextLine());
            }

            socket.close();
        } catch (IOException e) {
            System.err.println("Something went wrong!");
            e.printStackTrace();
        }
    }

}
