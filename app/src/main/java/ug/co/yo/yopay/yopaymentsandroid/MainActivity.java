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
                Log.i("YOPAY","clicked Button. Going to callYO now...");
            }
        });
    }
}
