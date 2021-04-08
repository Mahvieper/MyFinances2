package com.barnes.myfinances.model;

public class Checking {
    int id;
    int accNumber;
    int currBal;

    public Checking(int accNumber,int currBal) {

        this.accNumber = accNumber;
        this.currBal = currBal;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setAccNumber(int accNumber) {
        this.accNumber = accNumber;
    }

    public void setCurrBal(int currBal){
        this.currBal = currBal;
    }

    // getters
    public int getAccNumber() {
        return this.accNumber;
    }


    public int getCurrBal() {
        return this.currBal;
    }



}
