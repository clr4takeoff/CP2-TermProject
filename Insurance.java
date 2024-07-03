package TermProject.tmp;

import java.io.Serializable;

public class Insurance implements Serializable {
    private String customerId;
    private String details;
    private int price;
    private String insuranceType;
    private int insuranceMonth;
    static final int SP1_CancerMoney = 100000;
    static final int SP2_CancerMoney = 70000;
    static final int SP3_CancerMoney = 50000;

    public Insurance(String customerId, String insuranceType, Integer insuranceMonth) {
        this.customerId = customerId;
        this.insuranceType = insuranceType;
        this.insuranceMonth = insuranceMonth;
        if (insuranceType.equals("type1")) {
            this.price = 5000;
        }

        if (insuranceType.equals("type2")) {
            this.price = 2000;
        }

        if (insuranceType.equals("type3")) {
            this.price = 1000;
        }

    }

    public int totalPrice() {
        return this.price * this.insuranceMonth;
    }

    public String getCustomerid() {
        return this.customerId;
    }

    public void setDetails(String newDetails) {
        this.details = newDetails;
    }

    public String getInsuranceType() {
        return this.insuranceType;
    }

    public int getInsuranceMonth() {
        return this.insuranceMonth;
    }

    public void setPrice(int newPrice) {
        this.price = newPrice;
    }

    public String toString() {
        return "• 가입 보험: " + this.insuranceType + "\n• 가입 개월 수: " + this.insuranceMonth;
    }
}
