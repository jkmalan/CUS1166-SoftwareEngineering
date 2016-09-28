package com.jkmalan.BankServer;

/**
 * A simple class that manages and stores a name and balance
 *
 * @author jkmalan (aka John Malandrakis)
 */
public class Account {

    private final String name;
    private double balance = 0;

    /**
     * Constructs a new Account
     *
     * @param name The account holder's name
     * @param balance The account holder's balance
     */
    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    /**
     * Gets the account holder's name
     *
     * @return The account name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the account holder's balance
     *
     * @return The account balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Adds money to the account holder's balance
     *
     * @param amount The money to add
     * @return The account balance
     */
    public double deposit(double amount) {
        balance += amount;
        return balance;
    }

    /**
     * Subtracts money from the account holder's balance
     *
     * @param amount The money to subtracts
     * @return The account balance
     */
    public double withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        }
        return balance;
    }

}
