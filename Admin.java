package TermProject.tmp;

import java.io.Serializable;

public class Admin implements Serializable {
    private String adminId;
    private String adminPassword;

    public Admin(String adminId, String adminPassword) {
        this.adminId = adminId;
        this.adminPassword = adminPassword;
    }

    public String getAdminId() {
        return this.adminId;
    }

    public String getAdminPassword() {
        return this.adminPassword;
    }
}
