//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package TermProject.tmp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AdminGUI {
    private JFrame frame;
    private JTextField customerNameField;
    private JTextField customerIdField;
    private JPasswordField passwordField;
    private JTextField addressTextField;
    private JTextField phoneField;
    private JTextArea logArea;
    private InsuranceServer server;

    public AdminGUI(final InsuranceServer server) {
        this.server = server;
        this.frame = new JFrame("관리자용 보험 시스템");
        this.frame.setDefaultCloseOperation(2);
        this.frame.setSize(800, 600);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));
        JLabel nameLabel = new JLabel("고객 이름:");
        this.customerNameField = new JTextField();
        JLabel idLabel = new JLabel("고객 ID:");
        this.customerIdField = new JTextField();
        JLabel passwordLabel = new JLabel("비밀번호:");
        this.passwordField = new JPasswordField();
        JLabel addressLabel = new JLabel("주소");
        this.addressTextField = new JTextField();
        JLabel phoneLabel = new JLabel("전화번호");
        this.phoneField = new JTextField();
        JButton createCustomerButton = new JButton("신규 고객 추가");
        createCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = AdminGUI.this.customerNameField.getText();
                String id = AdminGUI.this.customerIdField.getText();
                char[] passwordChars = AdminGUI.this.passwordField.getPassword();
                String password = new String(passwordChars);
                String address = AdminGUI.this.addressTextField.getText();
                String phone = AdminGUI.this.phoneField.getText();
                if (!name.isEmpty() && !id.isEmpty() && !password.isEmpty() && !address.isEmpty() && !phone.isEmpty()) {
                    if (server.findCustomer(id) != null) {
                        JOptionPane.showMessageDialog(AdminGUI.this.frame, "이미 등록된 ID입니다. 다른 ID를 사용하세요.", "오류", 0);
                    } else {
                        int payment = 0;
                        server.addCustomer(new Customer(name, id, password, address, phone, payment));
                        server.saveCustomerData("Customer.txt");
                        String accountNumber = Account.generateAccountNumber();
                        Account newAccount = new Account(id, accountNumber, password, 0);
                        server.addAccount(newAccount);
                        AdminGUI.this.logArea.append("<고객 추가>\n" + name + " (" + id + ")\n\n");
                        AdminGUI.this.customerNameField.setText("");
                        AdminGUI.this.customerIdField.setText("");
                        AdminGUI.this.passwordField.setText("");
                        AdminGUI.this.addressTextField.setText("");
                        AdminGUI.this.phoneField.setText("");
                        AdminGUI.this.logArea.append("---------------------------------------\n");
                    }
                } else {
                    JOptionPane.showMessageDialog(AdminGUI.this.frame, "모든 필드를 채워주세요.", "오류", 0);
                }
            }
        });
        JButton viewCustomerDetailsButton = new JButton("고객 열람");
        viewCustomerDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String customerIdToView = JOptionPane.showInputDialog(AdminGUI.this.frame, "열람할 고객의 ID를 입력하세요:");
                Customer customerToView = server.findCustomer(customerIdToView);
                Account accountToView = server.findAccount(customerIdToView);
                if (customerToView != null) {
                    AdminGUI.this.logArea.append("<고객 정보>\n\n");
                    AdminGUI.this.logArea.append("• 고객 이름: " + customerToView.getName() + "\n");
                    AdminGUI.this.logArea.append("• 고객 ID: " + customerToView.getCustomerId() + "\n");
                    AdminGUI.this.logArea.append("• 주소: " + customerToView.getAddress() + "\n");
                    AdminGUI.this.logArea.append("• 전화번호: " + customerToView.getPhone() + "\n");
                    if (accountToView != null) {
                        AdminGUI.this.logArea.append("• 계좌 잔고: " + accountToView.getBalance() + "\n");
                    } else {
                        AdminGUI.this.logArea.append("계좌 정보가 없습니다.\n");
                    }

                    List<Insurance> subscribedInsurances = server.getCustomerInsurances(customerToView.getCustomerId());
                    AdminGUI.this.logArea.append("가입한 보험 목록:\n");
                    if (subscribedInsurances != null && !subscribedInsurances.isEmpty()) {
                        Iterator var6 = subscribedInsurances.iterator();

                        while(var6.hasNext()) {
                            Insurance insurance = (Insurance)var6.next();
                            JTextArea var10000 = AdminGUI.this.logArea;
                            String var10001 = insurance.getInsuranceType();
                            var10000.append("•  " + var10001 + "가입 개월 수: " + insurance.getInsuranceMonth() + "\n");
                        }
                    } else {
                        AdminGUI.this.logArea.append("고객이 가입한 보험이 없습니다.\n");
                    }

                    AdminGUI.this.logArea.append("\n---------------------------------------\n");
                } else {
                    JOptionPane.showMessageDialog(AdminGUI.this.frame, "입력한 ID의 고객을 찾을 수 없습니다.", "오류", 0);
                }

            }
        });
        JButton deleteCustomerButton = new JButton("고객 삭제");
        deleteCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String customerIdToDelete = JOptionPane.showInputDialog(AdminGUI.this.frame, "삭제할 고객의 ID를 입력하세요:");
                Customer customerToDelete = server.findCustomer(customerIdToDelete);
                if (customerToDelete != null) {
                    server.deleteCustomer(customerToDelete);
                    server.saveCustomerData("Customer.txt");
                    Account accountToDelete = server.findAccount(customerToDelete.getCustomerId());
                    server.deleteAccount(accountToDelete);
                    server.saveAccountData("Account.txt");
                    Insurance insuranceToDelete1 = server.findInsurance(customerIdToDelete, "type1");
                    Insurance insuranceToDelete2 = server.findInsurance(customerIdToDelete, "type2");
                    Insurance insuranceToDelete3 = server.findInsurance(customerIdToDelete, "type3");
                    if (insuranceToDelete1 != null) {
                        server.deleteInsurance(insuranceToDelete1);
                    }

                    if (insuranceToDelete2 != null) {
                        server.deleteInsurance(insuranceToDelete2);
                    }

                    if (insuranceToDelete3 != null) {
                        server.deleteInsurance(insuranceToDelete3);
                    }

                    server.saveInsuranceData("Insurance.txt");
                    JTextArea var10000 = AdminGUI.this.logArea;
                    String var10001 = customerToDelete.getName();
                    var10000.append("고객 삭제: " + var10001 + " (" + customerToDelete.getCustomerId() + ")\n");
                } else {
                    JOptionPane.showMessageDialog(AdminGUI.this.frame, "입력한 ID의 고객을 찾을 수 없습니다.", "오류", 0);
                }

            }
        });
        JButton addInsuranceButton = new JButton("보험 상품 추가");
        addInsuranceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String customerId = JOptionPane.showInputDialog(AdminGUI.this.frame, "고객 ID를 입력하세요:");
                if (customerId != null && !customerId.trim().isEmpty()) {
                    String insuranceType = JOptionPane.showInputDialog(AdminGUI.this.frame, "보험 종류를 입력하세요:");
                    if (insuranceType.equals("type1") && server.findInsurance(customerId, "type1") != null) {
                        JOptionPane.showMessageDialog(AdminGUI.this.frame, "이미 가입돼 있는 상품입니다.", "입력 오류", 0);
                    } else {
                        if (insuranceType.equals("type2")) {
                            if (server.findInsurance(customerId, "type1") == null) {
                                JOptionPane.showMessageDialog(AdminGUI.this.frame, "type1을 필수적으로 가입해야 합니다.", "입력 오류", 0);
                                return;
                            }

                            if (server.findInsurance(customerId, "type2") != null) {
                                JOptionPane.showMessageDialog(AdminGUI.this.frame, "이미 가입돼 있는 상품입니다.", "입력 오류", 0);
                                return;
                            }
                        } else if (insuranceType.equals("type3")) {
                            if (server.findInsurance(customerId, "type1") == null) {
                                JOptionPane.showMessageDialog(AdminGUI.this.frame, "type1을 필수적으로 가입해야 합니다.", "입력 오류", 0);
                                return;
                            }

                            if (server.findInsurance(customerId, "type3") != null) {
                                JOptionPane.showMessageDialog(AdminGUI.this.frame, "이미 가입돼 있는 상품입니다.", "입력 오류", 0);
                                return;
                            }
                        }

                        if (insuranceType != null && !insuranceType.trim().isEmpty()) {
                            String insuranceMonth = JOptionPane.showInputDialog(AdminGUI.this.frame, "가입 개월 수를 입력하세요:");
                            if (insuranceMonth != null && !insuranceMonth.trim().isEmpty()) {
                                Customer customer = server.findCustomer(customerId);
                                if (customer == null) {
                                    JOptionPane.showMessageDialog(AdminGUI.this.frame, "입력한 ID의 고객을 찾을 수 없습니다.", "오류", 0);
                                } else {
                                    try {
                                        int insuranceMonthValue = Integer.parseInt(insuranceMonth);
                                        Insurance newInsurance = new Insurance(customerId, insuranceType, insuranceMonthValue);
                                        server.addInsurance(newInsurance);
                                        server.saveInsuranceData("Insurance.txt");
                                        AdminGUI.this.logArea.append("<보험 상품 추가>\n\n고객 ID: " + customerId + "\n가입 보험명: " + insuranceType + "\n가입 기간: " + insuranceMonth + "개월\n\n");
                                        AdminGUI.this.logArea.append("---------------------------------------\n");
                                    } catch (NumberFormatException var8) {
                                        JOptionPane.showMessageDialog(AdminGUI.this.frame, "가입 개월 수를 올바르게 입력하세요.", "오류", 0);
                                    }

                                }
                            } else {
                                JOptionPane.showMessageDialog(AdminGUI.this.frame, "가입 개월 수를 올바르게 입력하세요.", "입력 오류", 0);
                            }
                        } else {
                            JOptionPane.showMessageDialog(AdminGUI.this.frame, "보험 종류를 올바르게 입력하세요.", "입력 오류", 0);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(AdminGUI.this.frame, "고객 ID를 올바르게 입력하세요.", "입력 오류", 0);
                }
            }
        });
        JButton changeInsurancePriceButton = new JButton("보험 상품 가격 변경");
        changeInsurancePriceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String customerId = JOptionPane.showInputDialog(AdminGUI.this.frame, "고객 ID를 입력하세요:");
                    String insuranceType = JOptionPane.showInputDialog(AdminGUI.this.frame, "변경할 보험 종류를 입력하세요:");

                    int newInsurancePrice;
                    try {
                        newInsurancePrice = Integer.parseInt(JOptionPane.showInputDialog(AdminGUI.this.frame, "새로운 가격을 입력하세요:"));
                    } catch (NumberFormatException var6) {
                        JOptionPane.showMessageDialog(AdminGUI.this.frame, "가격은 숫자로 입력해야 합니다.", "입력 오류", 0);
                        return;
                    }

                    Insurance insuranceToChange = server.findInsurance(customerId, insuranceType);
                    if (insuranceToChange != null) {
                        insuranceToChange.setPrice(newInsurancePrice);
                        server.saveInsuranceData("Insurance.txt");
                        AdminGUI.this.logArea.append("\n<보험 가격 변경>\n\n");
                        AdminGUI.this.logArea.append("보험 종류: " + insuranceType + ", 새로운 가격: " + newInsurancePrice + "\n");
                        AdminGUI.this.logArea.append("---------------------------------------\n");
                    } else {
                        JOptionPane.showMessageDialog(AdminGUI.this.frame, "입력한 정보와 일치하는 보험 상품을 찾을 수 없습니다.", "오류", 0);
                    }
                } catch (Exception var7) {
                    JOptionPane.showMessageDialog(AdminGUI.this.frame, "오류가 발생했습니다: " + var7.getMessage(), "오류", 0);
                }

            }
        });
        JButton changeInsuranceDetailsButton = new JButton("보험 상품 내용 변경");
        changeInsuranceDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String customerId = JOptionPane.showInputDialog(AdminGUI.this.frame, "고객 ID를 입력하세요:");
                Customer customer = server.findCustomer(customerId);
                if (customer == null) {
                    JOptionPane.showMessageDialog(AdminGUI.this.frame, "입력한 ID의 고객을 찾을 수 없습니다.", "오류", 0);
                } else {
                    String insuranceType = JOptionPane.showInputDialog(AdminGUI.this.frame, "내용을 변경할 보험 종류를 입력하세요:");
                    String newInsuranceDetails = JOptionPane.showInputDialog(AdminGUI.this.frame, "새로운 내용을 입력하세요:");
                    Insurance insuranceToChange = server.findInsurance(customerId, insuranceType);
                    if (insuranceToChange != null) {
                        insuranceToChange.setDetails(newInsuranceDetails);
                        server.saveInsuranceData("Insurance.txt");
                        AdminGUI.this.logArea.append("보험 상품 내용 변경: " + customerId + ", " + insuranceType + ", 새로운 내용: " + newInsuranceDetails + "\n");
                        AdminGUI.this.logArea.append("---------------------------------------\n");
                    } else {
                        JOptionPane.showMessageDialog(AdminGUI.this.frame, "입력한 정보와 일치하는 보험 상품을 찾을 수 없습니다.", "오류", 0);
                    }

                }
            }
        });
        JButton viewAllCustomersButton = new JButton("모든 고객과 납입금 정보 출력");
        viewAllCustomersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Customer> allCustomers = server.getAllCustomers();
                AdminGUI.this.logArea.append("\n< 모든 고객과 납입금 정보 >\n\n");
                Iterator var3 = allCustomers.iterator();

                while(var3.hasNext()) {
                    Customer customer = (Customer)var3.next();
                    Insurance insurance1 = server.findInsurance(customer.getCustomerId(), "type1");
                    Insurance insurance2 = server.findInsurance(customer.getCustomerId(), "type2");
                    Insurance insurance3 = server.findInsurance(customer.getCustomerId(), "type3");
                    int TOPAYTOTAL = 0;
                    if (insurance1 != null) {
                        TOPAYTOTAL += insurance1.totalPrice();
                        if (insurance2 != null) {
                            TOPAYTOTAL += insurance2.totalPrice();
                        }

                        if (insurance3 != null) {
                            TOPAYTOTAL += insurance3.totalPrice();
                        }
                    }

                    JTextArea var10000 = AdminGUI.this.logArea;
                    String var10001 = customer.getName();
                    var10000.append("고객 이름: " + var10001 + ", ID: " + customer.getCustomerId() + ", 납입금: " + customer.getPayment() + ", 납입해야 될 총 금액 : " + TOPAYTOTAL + "\n\n");
                }

                AdminGUI.this.logArea.append("---------------------------------------\n");
            }
        });
        JButton compensateButton = new JButton("신청 내역에 대한 보상금 지급");
        compensateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String customerIdToCompensate = JOptionPane.showInputDialog(AdminGUI.this.frame, "보상금을 지급할 고객의 ID를 입력하세요:");
                if (customerIdToCompensate != null && !customerIdToCompensate.trim().isEmpty()) {
                    String compensationDetails = JOptionPane.showInputDialog(AdminGUI.this.frame, "보상 내용을 입력하세요:");
                    if (compensationDetails != null && !compensationDetails.trim().isEmpty()) {
                        boolean SP1 = false;
                        boolean SP2 = false;
                        boolean SP3 = false;
                        Insurance insurance1 = server.findInsurance(customerIdToCompensate, "type1");
                        if (insurance1 != null) {
                            SP1 = true;
                        }

                        Insurance insurance2 = server.findInsurance(customerIdToCompensate, "type2");
                        if (insurance2 != null) {
                            SP2 = true;
                        }

                        Insurance insurance3 = server.findInsurance(customerIdToCompensate, "type3");
                        if (insurance3 != null) {
                            SP3 = true;
                        }

                        if (insurance1 == null && insurance2 == null && insurance3 == null) {
                            JOptionPane.showMessageDialog(AdminGUI.this.frame, "해당 고객의 가입된 보험 상품이 없습니다.", "입력 오류", 0);
                        } else {
                            int compensationAmount = 0;
                            if (SP1) {
                                compensationAmount += 100000;
                                if (SP2) {
                                    compensationAmount += 70000;
                                }

                                if (SP3) {
                                    compensationAmount += 50000;
                                }
                            }

                            try {
                                boolean success = server.compensateCustomer(customerIdToCompensate, compensationAmount);
                                if (success) {
                                    AdminGUI.this.logArea.append("<보상금 지급>\n\n• 고객: " + customerIdToCompensate + "\n• 내용: " + compensationDetails + "\n• 금액: " + compensationAmount + "\n");
                                    AdminGUI.this.logArea.append("\n---------------------------------------\n");
                                } else {
                                    JOptionPane.showMessageDialog(AdminGUI.this.frame, "입력한 ID의 고객을 찾을 수 없거나 보상금 지급에 실패했습니다.", "오류", 0);
                                }
                            } catch (NumberFormatException var12) {
                                JOptionPane.showMessageDialog(AdminGUI.this.frame, "보상할 금액을 올바르게 입력하세요.", "오류", 0);
                            }

                        }
                    } else {
                        JOptionPane.showMessageDialog(AdminGUI.this.frame, "보상 내용을 올바르게 입력하세요.", "입력 오류", 0);
                    }
                } else {
                    JOptionPane.showMessageDialog(AdminGUI.this.frame, "고객ID를 올바르게 입력하세요.", "입력 오류", 0);
                }
            }
        });
        JButton viewPaymentButton = new JButton("총 납입금 조회 (개인)");
        viewPaymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String customerId = JOptionPane.showInputDialog(AdminGUI.this.frame, "열람할 고객의 ID를 입력하세요:");
                if (customerId != null && !customerId.isEmpty()) {
                    int paymentDetails = server.getCustomerPaymentDetails(customerId);
                    if (paymentDetails != -1) {
                        JOptionPane.showMessageDialog(AdminGUI.this.frame, customerId + "님의 총 납입금은 " + paymentDetails + "원 입니다.");
                    } else {
                        JOptionPane.showMessageDialog(AdminGUI.this.frame, "Customer not found.");
                    }
                } else if (customerId != null) {
                    JOptionPane.showMessageDialog(AdminGUI.this.frame, "Please enter a customer ID.");
                }

            }
        });
        inputPanel.setLayout(new GridLayout(0, 2, 10, 10));
        inputPanel.add(nameLabel);
        inputPanel.add(this.customerNameField);
        inputPanel.add(idLabel);
        inputPanel.add(this.customerIdField);
        inputPanel.add(passwordLabel);
        inputPanel.add(this.passwordField);
        inputPanel.add(addressLabel);
        inputPanel.add(this.addressTextField);
        inputPanel.add(phoneLabel);
        inputPanel.add(this.phoneField);
        inputPanel.add(createCustomerButton);
        inputPanel.add(viewCustomerDetailsButton);
        inputPanel.add(deleteCustomerButton);
        inputPanel.add(addInsuranceButton);
        inputPanel.add(changeInsurancePriceButton);
        inputPanel.add(viewAllCustomersButton);
        inputPanel.add(compensateButton);
        inputPanel.add(viewPaymentButton);
        this.logArea = new JTextArea();
        this.logArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(this.logArea);
        mainPanel.add(inputPanel, "North");
        mainPanel.add(logScrollPane, "Center");
        this.frame.add(mainPanel);
        this.frame.setVisible(true);
    }
}
