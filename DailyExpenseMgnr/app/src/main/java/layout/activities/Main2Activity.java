package layout.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intesaralam.dailyexpensemgnr.AppData;
import com.example.intesaralam.dailyexpensemgnr.DataHandler;
import com.example.intesaralam.dailyexpensemgnr.R;

import java.util.List;

public class Main2Activity extends AppCompatActivity {
    TableLayout table_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        table_layout = (TableLayout) findViewById(R.id.tableLayout1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BuildTable();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void BuildTable() {
        TableRow row1 = new TableRow(this);
        row1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        TextView tv1 = new TextView(this);
        tv1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        tv1.setPadding(5, 5, 5, 5);
        tv1.setTypeface(Typeface.DEFAULT_BOLD);
        tv1.setText("Name");
        row1.addView(tv1);
        tv1=new TextView(this);
        tv1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        tv1.setPadding(5, 5, 5, 5);
        tv1.setTypeface(Typeface.DEFAULT_BOLD);
        tv1.setText("Price");
        row1.addView(tv1);
        table_layout.addView(row1);
        // outer for loop

        DataHandler dh = new DataHandler(Main2Activity.this);
        List<AppData> lst = dh.getAllItemDetails();
        int total=0;
        for (AppData ap : lst) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));

            // inner for loop
            for (int j = 1; j <= 2; j++) {

                TextView tv = new TextView(this);
                tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT));
                //tv.setBackgroundResource(R.drawable.cell_shape);
                tv.setPadding(5, 5, 5, 5);
               // tv.setText("items");
                if(j==1){
                    tv.setText(ap.getItemName());
                }
                else{
                    tv.setText(String.valueOf(ap.getItemPrice()));
                    total=total+ap.getItemPrice();
                }
                row.addView(tv);

            //String log = "Id: " + ap.getId() + " Name: " + ap.getItemName() + " Price: " + ap.getItemPrice() + " Desc: " + ap.getItemDesc() + " Date: " + ap.getItemDate();
            //Toast.makeText(getApplicationContext(), "Data: " + log, Toast.LENGTH_LONG).show();
        }
      /*  for (int i = 1; i <= 6; i++) {

            TableRow row = new TableRow(this);
            row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));


            // inner for loop
            for (int j = 1; j <= 2; j++) {

                TextView tv = new TextView(this);
                tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT));
                //tv.setBackgroundResource(R.drawable.cell_shape);
                tv.setPadding(5, 5, 5, 5);
                tv.setText("R " + i + ", C" + j);

                row.addView(tv);

            }*/

            table_layout.addView(row);


        }

        TableRow row2 = new TableRow(this);
        row1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        TextView tv2 = new TextView(this);
        tv2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        tv2.setPadding(5, 5, 5, 5);
        tv2.setTypeface(Typeface.DEFAULT_BOLD);
        tv2.setText("Total");
        row2.addView(tv2);
        tv2=new TextView(this);
        tv2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        tv2.setPadding(5, 5, 5, 5);
        tv2.setTypeface(Typeface.DEFAULT_BOLD);
        tv2.setText(String.valueOf(total));
        row2.addView(tv2);
        table_layout.addView(row2);
    }

}
