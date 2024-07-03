package TermProject.tmp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InsuranceServer {
    private List<Customer> customerList = new ArrayList();
    private List<Account> accountList = new ArrayList();
    private List<Insurance> insuranceList = new ArrayList();
    private List<Admin> AdminList = new ArrayList();
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    public InsuranceServer() {
        this.loadCustomerData("Customer.txt");
        this.loadAccountData("Account.txt");
        this.loadInsuranceData("Insurance.txt");
        this.loadAdminData("Admin.txt");
    }

    public Customer authenticateUser(String customerId, String password) {
        Iterator var3 = this.customerList.iterator();

        Customer customer;
        do {
            if (!var3.hasNext()) {
                return null;
            }

            customer = (Customer)var3.next();
        } while(!customer.getCustomerId().equals(customerId) || !customer.getPassword().equals(password));

        return customer;
    }

    public Boolean authenticateAdmin(String AdminId, String password) {
        Iterator var3 = this.AdminList.iterator();

        Admin admin;
        do {
            if (!var3.hasNext()) {
                return false;
            }

            admin = (Admin)var3.next();
        } while(!admin.getAdminId().equals(AdminId) || !admin.getAdminPassword().equals(password));

        return true;
    }

    public void loadAdminData(String FileName) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FileName), "UTF-8"));

            String line;
            try {
                while((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 2) {
                        Admin admin = new Admin(data[0], data[1]);
                        this.AdminList.add(admin);
                    }
                }
            } catch (Throwable var7) {
                try {
                    reader.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }

                throw var7;
            }

            reader.close();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }

    public void loadCustomerData(String FileName) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FileName), "UTF-8"));

            String line;
            try {
                while((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 6) {
                        Customer customer = new Customer(data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5]));
                        this.customerList.add(customer);
                    }
                }
            } catch (Throwable var7) {
                try {
                    reader.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }

                throw var7;
            }

            reader.close();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }

    public synchronized void saveCustomerData(String FileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileName), "UTF-8"));

            try {
                Iterator var3 = this.customerList.iterator();

                while(var3.hasNext()) {
                    Customer customer = (Customer)var3.next();
                    String var10000 = customer.getName();
                    String data = var10000 + "," + customer.getCustomerId() + "," + customer.getPassword() + "," + customer.getAddress() + "," + customer.getPhone() + "," + customer.getPayment();
                    writer.write(data);
                    writer.newLine();
                }
            } catch (Throwable var7) {
                try {
                    writer.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }

                throw var7;
            }

            writer.close();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }

    public void loadAccountData(String FileName) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FileName), "UTF-8"));

            String line;
            try {
                while((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 4) {
                        Account account = new Account(data[0], data[1], data[2], Integer.parseInt(data[3]));
                        this.accountList.add(account);
                    }
                }
            } catch (Throwable var7) {
                try {
                    reader.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }

                throw var7;
            }

            reader.close();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }

    public void addAccount(Account account) {
        this.accountList.add(account);
        this.saveAccountData("Account.txt");
    }

    public synchronized void saveAccountData(String FileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileName), "UTF-8"));

            try {
                Iterator var3 = this.accountList.iterator();

                while(var3.hasNext()) {
                    Account account = (Account)var3.next();
                    String var10000 = account.getCustomerId();
                    String data = var10000 + "," + account.getAccountNumber() + "," + account.getPassword() + "," + account.getBalance();
                    writer.write(data);
                    writer.newLine();
                }
            } catch (Throwable var7) {
                try {
                    writer.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }

                throw var7;
            }

            writer.close();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }

    public void loadInsuranceData(String FileName) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FileName), "UTF-8"));

            String line;
            try {
                while((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 3) {
                        Insurance insurance = new Insurance(data[0], data[1], Integer.parseInt(data[2]));
                        this.insuranceList.add(insurance);
                    }
                }
            } catch (Throwable var7) {
                try {
                    reader.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }

                throw var7;
            }

            reader.close();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }

    public synchronized void saveInsuranceData(String FileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileName), "UTF-8"));

            try {
                Iterator var3 = this.insuranceList.iterator();

                while(var3.hasNext()) {
                    Insurance insurance = (Insurance)var3.next();
                    String var10000 = insurance.getCustomerid();
                    String data = var10000 + "," + insurance.getInsuranceType() + "," + insurance.getInsuranceMonth();
                    writer.write(data);
                    writer.newLine();
                }
            } catch (Throwable var7) {
                try {
                    writer.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }

                throw var7;
            }

            writer.close();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }

    public synchronized void addCustomer(Customer customer) {
        this.customerList.add(customer);
    }

    public synchronized void deleteCustomer(Customer customer) {
        this.customerList.remove(customer);
    }

    public synchronized void deleteAccount(Account account) {
        this.accountList.remove(account);
    }

    public synchronized Customer findCustomer(String customerId) {
        Iterator var2 = this.customerList.iterator();

        Customer customer;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            customer = (Customer)var2.next();
        } while(!customer.getCustomerId().equals(customerId));

        return customer;
    }

    public synchronized void addInsurance(Insurance insurance) {
        this.insuranceList.add(insurance);
    }

    public synchronized void deleteInsurance(Insurance insurance) {
        this.insuranceList.remove(insurance);
    }

    public synchronized Insurance findInsurance(String customerId, String type) {
        Iterator var3 = this.insuranceList.iterator();

        Insurance insurance;
        do {
            if (!var3.hasNext()) {
                return null;
            }

            insurance = (Insurance)var3.next();
        } while(!insurance.getCustomerid().equals(customerId) || !insurance.getInsuranceType().equals(type));

        return insurance;
    }

    public boolean compensateCustomer(String customerId, int compensationAmount) {
        Customer customerToCompensate = this.findCustomer(customerId);
        if (customerToCompensate != null) {
            Iterator var4 = this.accountList.iterator();

            Account acc;
            do {
                if (!var4.hasNext()) {
                    return false;
                }

                acc = (Account)var4.next();
            } while(!acc.getCustomerId().equals(customerToCompensate.getCustomerId()));

            acc.deposit(compensationAmount);
            this.saveAccountData("Account.txt");
            return true;
        } else {
            return false;
        }
    }

    public synchronized void updateCustomer(Customer updatedCustomer) {
        for(int i = 0; i < this.customerList.size(); ++i) {
            if (((Customer)this.customerList.get(i)).getCustomerId().equals(updatedCustomer.getCustomerId())) {
                this.customerList.set(i, updatedCustomer);
                this.saveCustomerData("Customer.txt");
                break;
            }
        }

    }

    public List<Customer> getAllCustomers() {
        return new ArrayList(this.customerList);
    }

    public synchronized Account findAccount(String customerId) {
        Iterator var2 = this.accountList.iterator();

        Account account;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            account = (Account)var2.next();
        } while(!account.getCustomerId().equals(customerId));

        return account;
    }

    public synchronized int getCustomerPaymentDetails(String customerId) {
        Iterator var2 = this.customerList.iterator();

        Customer customer;
        do {
            if (!var2.hasNext()) {
                return -1;
            }

            customer = (Customer)var2.next();
        } while(!customer.getCustomerId().equals(customerId));

        return customer.getPayment();
    }

    public static void main(String[] args) {
        InsuranceServer server = new InsuranceServer();
        LoginGUI loginGUI = new LoginGUI();
        loginGUI.setLoginCallback((userId, password) -> {
            Future<Customer> result = server.executorService.submit(() -> {
                return server.authenticateUser(userId, password);
            });

            try {
                Customer customer = (Customer)result.get();
                if (customer != null) {
                    new CustomerGUI(server, customer);
                    System.out.println("로그인 성공");
                } else {
                    boolean isAdmin = server.authenticateAdmin(userId, password);
                    if (isAdmin) {
                        new AdminGUI(server);
                        System.out.println("관리자 로그인 성공");
                    } else {
                        System.out.println("로그인 실패");
                    }
                }
            } catch (Exception var7) {
                var7.printStackTrace();
            }

        });
    }

    public synchronized List<Insurance> getCustomerInsurances(String customerId) {
        Customer customer = this.findCustomer(customerId);
        List<Insurance> returnList = new ArrayList();
        if (customer != null) {
            Iterator var4 = this.insuranceList.iterator();

            while(var4.hasNext()) {
                Insurance insurance = (Insurance)var4.next();
                if (insurance.getCustomerid().equals(customerId)) {
                    returnList.add(insurance);
                }
            }

            return returnList;
        } else {
            return null;
        }
    }
}
