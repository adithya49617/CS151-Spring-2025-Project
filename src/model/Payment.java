package model;

public class Payment {
    private String paymentId;
    private String userId;
    private String subscriptionType;
    private String paymentAmount;
    private String paymentDate;

    public Payment(String paymentId, String userId, String subscriptionType, String paymentAmount, String paymentDate) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.subscriptionType = subscriptionType;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return paymentId +
                "," + userId +
                "," + subscriptionType +
                "," + paymentAmount +
                "," + paymentDate;
    }

    public void displayPaymentInfo() {
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Payer: " + userId);
        System.out.println("Subscription Type: " + subscriptionType);
        System.out.println("Amount: $" + paymentAmount);
        System.out.println("Date: " + paymentDate);
    }
}
