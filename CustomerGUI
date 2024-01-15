package TermProject.tmp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CustomerGUI extends JFrame {
    private InsuranceServer server;
    private Customer customer;
    private Account customerAccount;
    private JPanel p1;
    private JPanel p2;
    private JTextField paymentAmountField;
    private JButton payButton;

    public CustomerGUI(InsuranceServer server, Customer customer) {
        this.server = server;
        this.customer = customer;
        this.customerAccount = server.findAccount(customer.getCustomerId());
        this.initialize();
    }

    private void initialize() {
        this.setTitle("고객용 보험 시스템");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(2);
        this.setLocationRelativeTo((Component)null);
        final JTextArea infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);
        infoTextArea.append("<고객 정보>\n\n");
        infoTextArea.append("• 이름: " + this.customer.getName() + "\n");
        infoTextArea.append("• 아이디: " + this.customer.getCustomerId() + "\n");
        infoTextArea.append("• 주소: " + this.customer.getAddress() + "\n");
        infoTextArea.append("• 전화번호: " + this.customer.getPhone() + "\n\n");
        infoTextArea.append("=============================\n\n");
        JButton viewInsuranceButton = new JButton("가입보험상품조회");
        viewInsuranceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Insurance> customerInsurances = CustomerGUI.this.server.getCustomerInsurances(CustomerGUI.this.customer.getCustomerId());
                if (customerInsurances != null && !customerInsurances.isEmpty()) {
                    infoTextArea.append("\n<가입된 보험 상품 목록>\n\n");
                    infoTextArea.append("----------------------------------------------\n");
                } else {
                    infoTextArea.append("가입된 보험 상품이 없습니다.\n");
                    infoTextArea.append("----------------------------------------------\n");
                }

            }
        });
        JButton viewAccountButton = new JButton("계좌 조회");
        viewAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    infoTextArea.append("\n<계좌 조회 기능 수행>\n\n");
                    if (CustomerGUI.this.customerAccount == null) {
                        throw new Exception("계좌 정보가 없습니다.");
                    }

                    infoTextArea.append(CustomerGUI.this.customerAccount.toString() + "\n");
                } catch (Exception var3) {
                    infoTextArea.append("계좌 조회 실패: " + var3.getMessage() + "\n");
                }

                infoTextArea.append("----------------------------------------------\n");
            }
        });
        JButton payInsuranceButton = new JButton("보험금 납입");
        payInsuranceButton.addActionListener((e) -> {
            int totalPayable = 0;
            int alreadyPaid = this.customer.getPayment();
            List<Insurance> customerInsurances = this.server.getCustomerInsurances(this.customer.getCustomerId());
            infoTextArea.append("\n<보험금 납입>\n\n");
            if (customerInsurances != null && !customerInsurances.isEmpty()) {
                Iterator var6 = customerInsurances.iterator();

                while(var6.hasNext()) {
                    Insurance insurance = (Insurance)var6.next();
                    infoTextArea.append("• 가입된 보험 상품: " + insurance.getInsuranceType() + "\n");
                    int toPayAmount = insurance.totalPrice();
                    totalPayable += toPayAmount;
                    infoTextArea.append("• 총 보험 금액: " + toPayAmount + "\n");
                }

                infoTextArea.append("• 지금까지 납입한 총 보험 금액: " + alreadyPaid + "\n");
                if (totalPayable <= alreadyPaid) {
                    infoTextArea.append("내야 할 보험 금액이 없습니다.\n");
                    infoTextArea.append("\n----------------------------------------------\n");
                } else {
                    infoTextArea.append("• 내야 할 보험 금액: " + (totalPayable - alreadyPaid) + "\n");
                    infoTextArea.append("\n----------------------------------------------\n");
                    String paymentAmountString = JOptionPane.showInputDialog(this, "납입할 금액을 입력하세요.", "보험금 납입", 3);
                    if (paymentAmountString == null) {
                        infoTextArea.append("납입이 취소되었습니다.\n\n");
                    } else {
                        try {
                            int paymentAmount = Integer.parseInt(paymentAmountString);
                            if (paymentAmount <= 0) {
                                throw new Exception("유효하지 않은 금액입니다.");
                            }

                            if (paymentAmount > totalPayable - alreadyPaid) {
                                throw new Exception("납입 금액이 남은 보험 금액을 초과합니다.");
                            }

                            Account account = this.server.findAccount(this.customer.getCustomerId());
                            if (account == null || !account.withdraw(paymentAmount)) {
                                throw new Exception("계좌 잔액이 부족합니다.");
                            }

                            this.customer.setPayment(paymentAmount);
                            this.server.updateCustomer(this.customer);
                            infoTextArea.append("보험금 납입이 성공적으로 이루어졌습니다.\n");
                            infoTextArea.append("• 납입 금액: " + paymentAmount + "\n");
                            infoTextArea.append("• 남은 보험금: " + (totalPayable - alreadyPaid - paymentAmount) + "\n");
                        } catch (NumberFormatException var9) {
                            infoTextArea.append("올바른 숫자 형식으로 입력해주세요.\n");
                        } catch (Exception var10) {
                            infoTextArea.append(var10.getMessage() + "\n");
                        }

                        infoTextArea.append("----------------------------------------------\n");
                    }
                }
            } else {
                infoTextArea.append("가입된 보험 상품이 없습니다.\n");
                infoTextArea.append("\n----------------------------------------------\n");
            }
        });
        this.p1 = new JPanel(new FlowLayout());
        this.p2 = new JPanel(new BorderLayout());
        this.p1.add(viewInsuranceButton);
        this.p1.add(viewAccountButton);
        this.p1.add(payInsuranceButton);
        JScrollPane scrollPane = new JScrollPane(infoTextArea);
        this.p2.add(scrollPane, "Center");
        this.add(this.p1, "North");
        this.add(this.p2, "Center");
        this.setVisible(true);
    }
}
