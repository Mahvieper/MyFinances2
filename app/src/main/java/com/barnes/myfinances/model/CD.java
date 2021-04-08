package com.barnes.myfinances.model;

public class CD {
    int id;
    int accNumber;
    int initBal;
    int currBal;
    int intRate;

    public CD() {

    }

    public CD(int accNumber, int initBal,int currBal,int intRate) {

        this.accNumber = accNumber;
        this.initBal = initBal;
        this.currBal = currBal;
        this.intRate = intRate;
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

}
