package ug.co.yo.yopay.yopaymentsandroid;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.concurrent.ExecutionException;

import ug.co.yo.yopay.yopaymentslibrary.YoPay;
import ug.co.yo.yopay.yopaymentslibrary.YoPayFinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ug.co.yo.yopay.yopaymentslibrary.YoPay;
import ug.co.yo.yopay.yopaymentslibrary.YoPaymentsResponse;

public class MainActivity extends AppCompatActivity {

    private Button pay_btn;
    private EditText phone_number, amount, reason;
    private LinearLayout main_layout, success_layout, failure_layout, loading_layout;

    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentManager = getSupportFragmentManager();

        main_layout = (LinearLayout) findViewById(R.id.main_home_layout);
        success_layout = (LinearLayout) findViewById(R.id.layout_success);
        failure_layout = (LinearLayout) findViewById(R.id.layout_error);
        loading_layout = (LinearLayout) findViewById(R.id.layout_loading);

        phone_number = (EditText) findViewById(R.id.pay_input_phone_number);
        amount = (EditText) findViewById(R.id.pay_input_amount);
        reason = (EditText) findViewById(R.id.pay_input_reason);

        pay_btn = (Button) findViewById(R.id.pay_button);

        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("YOPAY", "clicked Button. Going to callYO now...");
                Button payButton = (Button) findViewById(R.id.pay_button);
                payButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("YOPAY", "Here we go...");
                        YoPay yoAPI = new YoPay("YoAPIUsername", "YoAPIPassword");
                        YoPaymentsResponse response = yoAPI.ac_deposit_funds("256712345678", 500, "Reason for payment");
                        if (response.getStatus().equals("OK")) {
                            try {
                                YoPaymentsResponse checkResponse = yoAPI.check_transaction(response.getTransactionReference());
                                if (checkResponse.getTransactionStatus().equals("SUCCEEDED")) {
                                    // The payment was successful.
                                    Log.i("YOPAY", "The payment was successfully transferred to your Yo! Payments Account. " + checkResponse.toString());
                                } else if (checkResponse.getTransactionStatus().equals("FAILED")) {
                                    // The payment failed.
                                    Log.i("YOPAY", "The payment failed. Funds were not transferred to your Yo! Payments Account. " + checkResponse.toString());
                                } else if (checkResponse.getTransactionStatus().equals("PENDING")) {
                                    // The payment is still pending.
                                    Log.i("YOPAY", "The payment is still pending. Please check again later. " + checkResponse.toString());
                                } else {
                                    // The payment is indeterminate.
                                    Log.i("YOPAY", "The payment is indeterminate. Please contact Yo! Payments support. " + checkResponse.toString());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.e("YOPAY", "There was an error transferring funds. Please try again later.");
                        }
                    }
                });
            }
        });
    }
}