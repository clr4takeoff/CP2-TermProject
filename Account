package TermProject.tmp;

import java.io.Serializable;
import java.util.Random;

public class Account implements Serializable {
    private String accountNumber;
    private String customerId;
    private String password;
    private int balance;

    public Account(String customerId, String accountNumber, String password, int balance) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
    }

    public Account(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return this.balance;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public boolean withdraw(int amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public static String generateAccountNumber() {
        Random random = new Random();
        int partOne = random.nextInt(1000000);
        int partTwo = random.nextInt(1000000);
        return String.format("%06d-%06d", partOne, partTwo);
    }

    public String toString() {
        return "• 계좌 번호: " + this.accountNumber + "\n• 잔액: " + this.balance + "원\n";
    }
}
