package com.example.intesaralam.dailyexpensemgnr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by intesarAlam on 7/21/2016.
 */
public class DataHandler extends SQLiteOpenHelper {

    private static final int Data_Base_Version=1;
    private static final String Data_Base_Name="ExpenseManage";
    private static final String Expense_Tab="expense_manager";
    private static final String Key_Id="id";
    private static final String Key_Name="itemName";
    private static final String Key_Price="itemPrice";
    private  static final String Key_Desc="Item_Desc";
    private static final String Key_Date="Item_Date";

    public DataHandler(Context context){
           super(context,Data_Base_Name,null,Data_Base_Version);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
          String query="CREATE TABLE "+Expense_Tab+"("+Key_Id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Key_Name+" TEXT,"+Key_Price+" TEXT,"+Key_Desc+" TEXT,"+Key_Date+" DATETIME"+")";
          db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL("DROP TABLE IF EXISTS "+Expense_Tab);
        onCreate(db);
    }

    public boolean addItemDetail(AppData data){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(Key_Name,data.getItemName());
        cv.put(Key_Price,data.getItemPrice());
        cv.put(Key_Desc, data.getItemDesc());
        cv.put(Key_Date, String.valueOf(data.getItemDate()));
        db.insert(Expense_Tab, null, cv);

       return true;
    }

    public List<AppData> getAllItemDetails(){
           List<AppData>  itemList=new ArrayList<AppData>();
            String selectQry="select * FROM "+Expense_Tab;

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQry,null);

        if(cursor.moveToFirst()){

            do{
                AppData ap=new AppData();
                ap.setId(Integer.parseInt(cursor.getString(0)));
                ap.setItemName(cursor.getString(1));
                ap.setItemPrice(Integer.parseInt(cursor.getString(2)));
                ap.setItemDesc(cursor.getString(3));
                Log.d("Name of Item :", cursor.getString(0));
                Log.d("Name of Item :", cursor.getString(1));
                Log.d("Name of price :", cursor.getString(2));
                Log.d("Name of Desc :", cursor.getString(3));
                Log.d("Name of Date :", cursor.getString(4));

                //ap.setItemPrice(Integer.parseInt(cursor.getString(2)));
               // ap.setItemDesc(cursor.getString(3));
                SimpleDateFormat dformat=new SimpleDateFormat( "EEE MMM dd hh:mm:ss 'GMT'z yyyy");
                String datetime=cursor.getString(4);
                 try {
                     ap.setItemDate(dformat.parse(datetime));
               } catch (Exception ex){
                   Log.e("Exception is:", ex.toString());
                }
              itemList.add(ap);
            }while(cursor.moveToNext());
        }

        return  itemList;
    }
}
