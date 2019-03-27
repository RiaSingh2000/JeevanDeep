package com.example.jeevandeepassociates;

public class SelectedItems {
    String product;
    int qty;
    double amt;

    public SelectedItems(String product, int qty, double amt) {
        this.product = product;
        this.qty = qty;
        this.amt = amt;
    }


    public String getProduct() {
        return product;
    }

    public int getQty() {
        return qty;
    }

    public double getAmt() {
        return amt;
    }
}
