package ug.co.yo.yopay.yopaymentsandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import ug.co.yo.yopay.yopaymentslibrary.YoPayFinal;
import ug.co.yo.yopay.yopaymentslibrary.YoPaymentsResponse;

public class MainActivity extends AppCompatActivity {

    private EditText phone_number;
    private EditText amount;
    private EditText reason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone_number = (EditText) findViewById(R.id.pay_input_phone_number);
        amount = (EditText) findViewById(R.id.pay_input_amount);
        reason = (EditText) findViewById(R.id.pay_input_reason);
        Button pay_btn = (Button) findViewById(R.id.pay_button);

        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("YOPAY", "Here we go...");
                Log.i("YOPAY", "Clicked Button. Going to callYO now...");
                YoPayFinal yoPay = new YoPayFinal("username", "password");
                yoPay.setMsisdn("256712256785");
                yoPay.setAmount(500);
                yoPay.setNarrative("Reason for deposit of funds");
                try {
                    YoPaymentsResponse response = yoPay.execute("acdepositfunds").get();
                    Log.i("YOPAY", "" + response.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}