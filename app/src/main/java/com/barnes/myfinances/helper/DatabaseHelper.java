package com.barnes.myfinances.helper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.barnes.myfinances.model.CD;
import com.barnes.myfinances.model.Checking;
import com.barnes.myfinances.model.Deposit;
import com.barnes.myfinances.model.Loan;

public class DatabaseHelper extends SQLiteOpenHelper  {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "myfinancesdb";

    // Table Names
    private static final String TABLE_CD = "CD_Table";
    private static final String TABLE_Checking = "Checking_Table";
    private static final String TABLE_Loan = "Loan_Table";
    private static final String TABLE_Deposit = "Deposit_Table";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    // CD Table - column nmaes
    private static final String accNumber = "accNumber";
    private static final String initBal = "initBal";
    private static final String currBal = "currBal";
    private static final String intRate = "intRate";

    // Checking Table - column names
    private static final String accNumberChecking = "accNumber";
    private static final String currBalChecking = "currBal";

    // Loan Table - column names
    private static final String accNumberLoan = "accNumber";
    private static final String currBalLoan = "currBal";
    private static final String initBalLoan = "initBal";
    private static final String intRateLoan = "intRate";
    private static final String paymentLoan = "payment";


    // Deposit Table - column names
    private static final String accNumberDeposit = "accNumber";
    private static final String amountDeposit = "amount";
    private static final String imageFront = "imageFront";
    private static final String imageBack = "imageBack";


    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_CD = "CREATE TABLE "
            + TABLE_CD + "(" + KEY_ID + " INTEGER PRIMARY KEY," + accNumber
            + " INTEGER," + initBal + " INTEGER,"  + currBal + " INTEGER,"
            + intRate + " INTEGER" + ")";

    // Tag table create statement
    private static final String CREATE_TABLE_Checking = "CREATE TABLE " + TABLE_Checking
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + accNumberChecking + " INTEGER,"
            + currBalChecking + " INTEGER" + ")";

    // todo_tag table create statement
    private static final String CREATE_TABLE_Loan = "CREATE TABLE "
            + TABLE_Loan + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + accNumberLoan + " INTEGER,"
            + currBalLoan + " INTEGER,"
            + initBalLoan + " INTEGER,"
            + intRateLoan + " INTEGER,"
            + paymentLoan + " INTEGER" + ")";

    // Deposit table create statement
    private static final String CREATE_TABLE_Deposit = "CREATE TABLE "
            + TABLE_Deposit + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + accNumberDeposit + " INTEGER,"
            + amountDeposit + " INTEGER,"
            + imageFront + " blob,"
            + imageBack + " blob" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_CD);
        db.execSQL(CREATE_TABLE_Checking);
        db.execSQL(CREATE_TABLE_Loan);
        db.execSQL(CREATE_TABLE_Deposit);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Checking);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Loan);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Deposit);

        // create new tables
        onCreate(db);
    }


    /*
     * Creating a CD
     */
    public long createCD(CD todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(accNumber, todo.getAccNumber());
        values.put(currBal, todo.getCurrBal());
        values.put(initBal, todo.getInitBal());
        values.put(intRate, todo.getIntRate());

        // insert row
        long cd_id = db.insert(TABLE_CD, null, values);

        /*// assigning tags to todo
        for (long tag_id : tag_ids) {
            createTodoTag(todo_id, tag_id);
        }*/

        return cd_id;
    }

    /*
     * get single todo
     */
    public CD getCD(long cd_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CD + " WHERE "
                + KEY_ID + " = " + cd_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        CD cd = new CD();
        cd.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        cd.setAccNumber((c.getColumnIndex(accNumber)));
        cd.setCurrBal((c.getColumnIndex(currBal)));
        cd.setInitBal((c.getColumnIndex(initBal)));
        cd.setIntRate((c.getColumnIndex(intRate)));


        return cd;
    }


    /*
     * Creating a Checking
     */
    public long createChecking(Checking todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(accNumberChecking, todo.getAccNumber());
        values.put(currBal, todo.getCurrBal());

        // insert row
        long checking_id = db.insert(TABLE_Checking, null, values);

        /*// assigning tags to todo
        for (long tag_id : tag_ids) {
            createTodoTag(todo_id, tag_id);
        }*/

        return checking_id;
    }


    /*
     * Creating a CD
     */
    public long createLoan(Loan todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(accNumber, todo.getAccNumber());
        values.put(currBal, todo.getCurrBal());
        values.put(initBal, todo.getInitBal());
        values.put(intRate, todo.getIntRate());
        values.put(paymentLoan, todo.getPayment());
        // insert row
        long loan_id = db.insert(TABLE_Loan, null, values);

        /*// assigning tags to todo
        for (long tag_id : tag_ids) {
            createTodoTag(todo_id, tag_id);
        }*/

        return loan_id;
    }

    /*
     * Creating a CD
     */
    public long createDeposit(Deposit todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(accNumber, todo.getAccNumber());
        values.put(imageFront, todo.getImgFront());
        values.put(imageBack, todo.getImgBack());

        // insert row
        long deposit_id = db.insert(TABLE_Deposit, null, values);

        /*// assigning tags to todo
        for (long tag_id : tag_ids) {
            createTodoTag(todo_id, tag_id);
        }*/

        return deposit_id;
    }


    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }


}
