package com.example.intesaralam.dailyexpensemgnr;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import layout.activities.Main2Activity;

public class MainActivity extends AppCompatActivity {
    final Calendar clndr=Calendar.getInstance();
    private InputMethodManager im ;
    private  EditText ettime;
    private TimePicker tpkr;
    private  int hour;
    private  int minute;
    static final  int Time_Dialog_Id=999;
    private Button btn;
    boolean dataflag=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
        addListenerOnButton();

        final DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year,int monthOfYear,int dayOfMonth){
                clndr.set(Calendar.YEAR,year);
                clndr.set(Calendar.MONTH,monthOfYear);
                clndr.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateDate();

            };
        };

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        EditText et=(EditText)findViewById(R.id.editText6);
        et.setInputType(InputType.TYPE_NULL);
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, date, clndr.get(Calendar.YEAR), clndr.get(Calendar.MONTH), clndr.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ettime=(EditText)findViewById(R.id.editText7);
        ettime.setInputType(InputType.TYPE_NULL);
        ettime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(Time_Dialog_Id);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
   public void  addListenerOnButton(){
       btn=(Button)findViewById(R.id.button2);
       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String iname=null;
               int price=0;
               String desc=null;
               Date dt=null;
               EditText et;
               et=(EditText)findViewById(R.id.editText);

               if(et.getText().toString().length()==0){
                   dataflag=true;
                   et.setError("Item Name Required");
               }
               else{
                   iname=et.getText().toString();
               }
               et=(EditText)findViewById(R.id.editText4);

               if(et.getText().toString().length()==0){
                   dataflag=true;
                   et.setError("Item Price Required");

               }
               else{
                   price= Integer.parseInt(et.getText().toString());

               }
               et=(EditText)findViewById(R.id.editText5);

               if(et.getText().toString().length()==0){
                   dataflag=true;
                   et.setError("Item Description Required");

               }
               else{
                   desc=et.getText().toString();

               }

               et=(EditText)findViewById(R.id.editText6);
               String date_str=null;

                   date_str=et.getText().toString();

               SimpleDateFormat df=new SimpleDateFormat("MM/dd/yy HH:mm");
               et=(EditText)findViewById(R.id.editText7);
               String time_str=null;

                   time_str=et.getText().toString();



               try {
                   dt = df.parse(date_str+" "+time_str);
               }catch (Exception ex){

                  Log.e("Error:",ex.toString());
               }

               if( dt==null ){
                   dataflag=true;
                   Log.d("Error","1" );
               }



               if(!dataflag) {
                   DataHandler dh=new DataHandler(MainActivity.this);
                   try {
                       boolean chkStatus=dh.addItemDetail(new AppData(iname,price,desc,dt));
                       //boolean chkStatus=AppData.saveItemData();
                       Toast.makeText(getApplicationContext(), iname + " " + price + " " + desc+ " " + dt+chkStatus, Toast.LENGTH_SHORT).show();

                   }
                   catch (Exception ex){
                       Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
                       Log.e("Exception is:", ex.toString());
                   }



               }
               else{
                   //Toast.makeText(getApplicationContext(), "Error:"+ap.itemName + " " + ap.itemPrice + " " + ap.itemDesc + " " + ap.itemDate, Toast.LENGTH_SHORT).show();
                   Toast.makeText(getApplicationContext(),"All fields are required", Toast.LENGTH_SHORT).show();
                   dataflag=false;
                   //Toast.makeText(getApplicationContext(), "All Fields are Required!", Toast.LENGTH_SHORT).show();
               }


           }
       });

       btn=(Button)findViewById(R.id.button);
       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DataHandler dh = new DataHandler(MainActivity.this);
               List<AppData> lst = dh.getAllItemDetails();
               for (AppData ap : lst) {
                   String log = "Id: " + ap.getId() + " Name: " + ap.getItemName() + " Price: " + ap.getItemPrice() + " Desc: " + ap.getItemDesc() + " Date: " + ap.getItemDate();
                   Toast.makeText(getApplicationContext(), "Data: " + log, Toast.LENGTH_LONG).show();
               }
               if(lst.size()>0){
                   Intent intent=new Intent(getApplicationContext(), Main2Activity.class);
                   startActivity(intent);
               }
               else{
                   Toast.makeText(getApplicationContext(), "No items please add first.", Toast.LENGTH_LONG).show();
               }

           }
       });

   }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Toast.makeText(getApplicationContext(),"Setting selected",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(id==R.id.item_detail){
            Intent intent=new Intent(getApplicationContext(), Main2Activity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    public void updateDate(){
        String dateFormat="MM/dd/yy";
        EditText et=(EditText)findViewById(R.id.editText6);
        SimpleDateFormat sdf=new SimpleDateFormat(dateFormat, Locale.US);
        et.setText(sdf.format(clndr.getTime()));

    }

    public  void setTimeOnView(){
        ettime=(EditText)findViewById(R.id.editText7);
        //tpkr=(TimePicker)findViewById(R.id.timePicker1);
        final Calendar c=Calendar.getInstance();

        hour=c.get(Calendar.HOUR);
        minute=c.get(Calendar.MINUTE);



        tpkr.setCurrentHour(hour);
        tpkr.setCurrentMinute(minute);
        ///ettime.setText(pad(hour))+":"+pad(minute));


    }
    @Override
    protected Dialog onCreateDialog(int id){
        switch (id){

            case  Time_Dialog_Id:
                return  new TimePickerDialog(this,timePickerListener,hour,minute,false);
        }
     return null;
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener=new TimePickerDialog.OnTimeSetListener(){

        public void onTimeSet(TimePicker view, int selectedHour,int selectedMinute){
          hour=selectedHour;
            minute=selectedMinute;

                ettime.setText(pad(hour) + ":" + pad(minute));

            //tpkr.setCurrentHour(hour);
            //tpkr.setCurrentMinute(minute);


        }



    };
    private static String pad(int c){

      if(c>=10){

          return  String.valueOf(c);
      }
        else {

          return "0"+String.valueOf(c);
      }
    }
}
