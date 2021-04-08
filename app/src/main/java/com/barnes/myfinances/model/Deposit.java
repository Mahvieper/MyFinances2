package com.barnes.myfinances.model;

public class Deposit {
    int id;
    int accNumber;
    int amount;
    byte[] imgFront;
    byte[] imgBack;

    public Deposit() {

    }

    public Deposit(int accNumber, int amount,byte[] imgFront,byte[] imgBack) {
        this.accNumber = accNumber;
       this.amount = amount;
       this.imgFront = imgFront;
       this.imgBack = imgBack;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setAccNumber(int accNumber) {
        this.accNumber = accNumber;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setImgFront(byte[] imgFront) {
        this.imgFront = imgFront;
    }

    public void setImgBack(byte[] imgBack) {
        this.imgBack = imgBack;
    }

    // getters
    public int getAccNumber() {
        return this.accNumber;
    }
    public int getAmount() {
        return this.amount;
    }
    public byte[] getImgBack() {
        return this.imgBack;
    }

    public byte[] getImgFront() {
        return this.imgFront;
    }

}
