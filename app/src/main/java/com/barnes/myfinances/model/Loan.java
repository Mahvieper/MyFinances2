package com.barnes.myfinances.model;

public class Loan {
    int id;
    int accNumber;
    int initBal;
    int currBal;
    int intRate;
    int payment;

    public Loan(int accNumber, int initBal,int currBal,int intRate,int payment) {

        this.accNumber = accNumber;
        this.initBal = initBal;
        this.currBal = currBal;
        this.intRate = intRate;
        this.payment = payment;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setAccNumber(int accNumber) {
        this.accNumber = accNumber;
    }

    public void setInitBal(int initBal) {
        this.initBal = initBal;
    }

    public void setCurrBal(int currBal){
        this.currBal = currBal;
    }

    public void setIntRate(int intRate){
        this.intRate = intRate;
    }

    public void setPayment(int payment){
        this.payment = payment;
    }


    // getters
    public int getAccNumber() {
        return this.accNumber;
    }

    public int getInitBal() {
        return this.initBal;
    }

    public int getCurrBal() {
        return this.currBal;
    }

    public int getIntRate() {
        return this.intRate;
    }

    public int getPayment(){
       return this.payment;
    }

}
