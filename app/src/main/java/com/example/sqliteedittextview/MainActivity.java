package com.example.sqliteedittextview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button button;
    LinearLayout linearLayout;
    HashMap<Integer,EditText> marks = new HashMap<>();
    ArrayList<String> rollno = new ArrayList<>();
    ArrayList<String> marksgot = new ArrayList<>();

    HashMap<Integer,String> hmap = new HashMap<>();

    AllDropDownDB database;
    int num = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.ll_edittext);
        button = findViewById(R.id.buttonPanel);



        database = AllDropDownDB.getInstance(MainActivity.this);
        database.open();

        for(int i = 1 ; i <= 10 ; i++){
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            View view = inflater.inflate(R.layout.layout_view,null,false);
            TextView textView = view.findViewById(R.id.textView);
            textView.setText(i + "");
            final EditText editText = view.findViewById(R.id.editText);
            final int finalI = i;


            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {

                    try {
                        if(finalI == 5){
                            editText.setText("Absent");
                        } else if(Integer.valueOf(editText.getText().toString()) < num) {

                            hmap.put(finalI, editText.getText().toString());
                        } else {
                            editText.setText("");
                            Toast.makeText(MainActivity.this, "Marks grater than the total Marks", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            });

            linearLayout.addView(view);

        }


       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               for(Map.Entry m : hmap.entrySet())
                   if(m.getValue().equals("")){

                   } else {
                       database.insertIntoDB(String.valueOf(m.getKey()),String.valueOf(m.getValue()));
                   }


           }
       });

    }
}
