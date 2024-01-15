package TermProject.tmp;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class LoginGUI extends JFrame {
    private JTextField userIdField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private LoginCallback loginCallback;

    public LoginGUI() {
        this.initialize();
    }

    private void initialize() {
        this.setTitle("Login");
        this.setDefaultCloseOperation(3);
        this.setSize(300, 150);
        this.setLocationRelativeTo((Component)null);
        JLabel welcomeLabel = new JLabel("     자바 보험에 오신 것을 환영합니다.     ");
        welcomeLabel.setFont(new Font("맑은고딕", 1, 20));
        welcomeLabel.setHorizontalAlignment(0);
        this.userIdField = new JTextField(20);
        this.passwordField = new JPasswordField(20);
        this.loginButton = new JButton("Login");
        this.loginButton.addActionListener((e) -> {
            String enteredUserId = this.userIdField.getText();
            String enteredPassword = new String(this.passwordField.getPassword());
            if (this.loginCallback != null) {
                this.loginCallback.onLogin(enteredUserId, enteredPassword);
            }

        });
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = 10;
        panel.add(welcomeLabel, gbc);
        panel.add(welcomeLabel, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("사용자 아이디:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(this.userIdField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("비밀번호:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(this.passwordField, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = 10;
        panel.add(this.loginButton, gbc);
        this.add(panel);
        this.pack();
        this.setVisible(true);
    }

    public void setLoginCallback(LoginCallback loginCallback) {
        this.loginCallback = loginCallback;
    }

    public interface LoginCallback {
        void onLogin(String var1, String var2);
    }
}
