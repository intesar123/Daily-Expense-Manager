package com.example.intesaralam.dailyexpensemgnr;


import android.app.Activity;

import android.widget.Toast;

import java.util.Date;

/**
 * Created by intesarAlam on 7/17/2016.
 */
public class AppData extends Activity {
    public int id;
    public String itemName;
    public int itemPrice;
    public String itemDesc;
    public Date itemDate;
    public  AppData(){}
    public AppData(String name,int price,String desc, Date date ){

          this.itemName=name;
          this.itemPrice=price;
          this.itemDesc=desc;
          this.itemDate=date;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Date getItemDate() {
        return itemDate;
    }

    public void setItemDate(Date itemDate) {
        this.itemDate = itemDate;
    }

    public static boolean saveItemData(){


       // Toast.makeText(getApplicationContext(),"Testing",Toast.LENGTH_SHORT).show();
        return  true;
    }

}
