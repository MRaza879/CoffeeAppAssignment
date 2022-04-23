package com.example.coffeeappassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etAddress;

    CheckBox cbxChocolate;
    CheckBox cbxCream;

    Button btnPlus;
    Button btnMinus;
    Button btnCheckOrder;
    Button btnSendOrder;

    TextView tvQuantity;
    TextView tvCost;
    TextView tvSummary;

    //variables
    int numberOfCups=1;
    int totalCost=5;
    int cupPrice=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewsReference();
        setViewsListeners();

    }

    private void setViewsReference(){
        etName=findViewById(R.id.et_user_name);
        etAddress=findViewById(R.id.et_user_address);

        cbxChocolate=findViewById(R.id.cbx_chocolate);
        cbxCream=findViewById(R.id.cbx_cream);

        btnPlus=findViewById(R.id.btn_quantity_plus);
        btnMinus=findViewById(R.id.btn_quantity_minus);
        btnCheckOrder=findViewById(R.id.btn_check_order);
        btnSendOrder=findViewById(R.id.btn_send_order);

        tvQuantity=findViewById(R.id.tv_quantity);
        tvCost=findViewById(R.id.tv_cost);
        tvSummary=findViewById(R.id.tv_order_summary);
    }
    private void setViewsListeners(){
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numberOfCups<10){
                    numberOfCups++;
                    tvQuantity.setText(numberOfCups + "");
                    totalCost=cupPrice*numberOfCups;
                    tvCost.setText(totalCost+".00$");
                }else{
                    String s="You can order maximum 10 cups at a time";
                    Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numberOfCups>1){
                numberOfCups--;
                tvQuantity.setText(Integer.toString(numberOfCups) + "");
                totalCost=cupPrice*numberOfCups;
                tvCost.setText(totalCost+".00$");
            }else{
                    String s="minimum number of cups is 1";
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                }
            }
        });
        cbxChocolate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true){
                cupPrice=cupPrice+3;
                totalCost=numberOfCups*cupPrice;
                tvCost.setText(totalCost+".00$");
                }else{
                    cupPrice=cupPrice-3;
                    totalCost=numberOfCups*cupPrice;
                    tvCost.setText(totalCost+".00$");
                }
            }
        });
        cbxCream.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true){
                    cupPrice=cupPrice+2;
                    totalCost=numberOfCups*cupPrice;
                    tvCost.setText(totalCost+".00$");
                }else{
                    cupPrice=cupPrice-2;
                    totalCost=numberOfCups*cupPrice;
                    tvCost.setText(totalCost+".00$");
                }
            }
        });
        btnCheckOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderSumaryString="Name : "+etName.getText().toString()+"\n"
                        + "Address : "+etAddress.getText().toString()+"\n"
                        +"Number of Cups : "+numberOfCups+"\n"
                        +"Total Cost : "+totalCost+".00$";
                tvSummary.setText(orderSumaryString);
            }
        });
        btnSendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=tvSummary.getText().toString();
                sendEmail(s);
            }
        });
    }
    private void sendEmail(String emailText){
        String emails[]={"mrazabng125@gmail.com"};
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" +
                ""));
        intent.putExtra(Intent.EXTRA_SUBJECT,"This is a new Coffee Order");
        intent.putExtra(Intent.EXTRA_TEXT,emailText);
        intent.putExtra(Intent.EXTRA_EMAIL,emails);

        startActivity(intent);
    }

}