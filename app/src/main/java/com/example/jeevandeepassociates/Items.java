package com.example.jeevandeepassociates;

public class Items {
    private String Code,Product,Packing;
    private int Qty,NetRate;

    public Items(String code, String product, String packing, int qty, int netRate) {
        this.Code = code;
        this.Product = product;
        this.Packing = packing;
        this.Qty = qty;
        this.NetRate = netRate;
    }

    public String getCode() {
        return Code;
    }

    public String getProduct() {
        return Product;
    }

    public String getPacking() {
        return Packing;
    }

    public int getQty() {
        return Qty;
    }

    public int getNetRate() {
        return NetRate;
    }
}
