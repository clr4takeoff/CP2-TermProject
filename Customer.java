//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package TermProject.tmp;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    private String name;
    private String customerId;
    private String password;
    private String address;
    private String phone;
    private int payment;
    private Account account;

    public Customer(String name, String customerId, String password, String address, String phone, int payment) {
        this.name = name;
        this.customerId = customerId;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.payment = payment;
    }

    public String getName() {
        return this.name;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getPassword() {
        return this.password;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhone() {
        return this.phone;
    }

    public int getPayment() {
        return this.payment;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setPayment(int payment) {
        this.payment += payment;
        String customerId = this.getCustomerId();
        this.updateAccountFile(customerId, payment);
        this.updateCustomerFile(customerId, this.payment);
    }

    private synchronized void updateAccountFile(String customerId, int payment) {
        Path path = Paths.get("Account.txt");

        try {
            List<String> fileContent = new ArrayList(Files.readAllLines(path, StandardCharsets.UTF_8));

            for(int i = 0; i < fileContent.size(); ++i) {
                String[] values = ((String)fileContent.get(i)).split(",");
                if (values[0].equals(customerId)) {
                    int currentBalance = Integer.parseInt(values[3]);
                    currentBalance -= payment;
                    values[3] = String.valueOf(currentBalance);
                    fileContent.set(i, String.join(",", values));
                    break;
                }
            }

            Files.write(path, fileContent, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }

    private synchronized void updateCustomerFile(String customerId, int newPayment) {
        Path path = Paths.get("Customer.txt");

        try {
            List<String> fileContent = new ArrayList(Files.readAllLines(path, StandardCharsets.UTF_8));

            for(int i = 0; i < fileContent.size(); ++i) {
                String[] values = ((String)fileContent.get(i)).split(",");
                if (values[1].equals(customerId)) {
                    int afterPay = Integer.parseInt(values[5]);
                    afterPay += newPayment;
                    values[5] = String.valueOf(afterPay);
                    fileContent.set(i, String.join(",", values));
                    break;
                }
            }

            Files.write(path, fileContent, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }
}
