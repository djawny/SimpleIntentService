package com.example.http.simpleintentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.first_value)
    EditText mFirstValue;

    @BindView(R.id.second_value)
    EditText mSecondValue;

    @BindView(R.id.result)
    TextView mResult;

    LocalBroadcastManager localBroadcastManager;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO odbierz odpowiedź i wyświetl wynik na ekranie
            // double result = intent.getDoubleExtra("NAZWA_POLA", 0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        IntentFilter filter = new IntentFilter();
        // filter.addAction("NAZWA_AKCJI"); TODO: wstaw nazwę akcji którą chcesz odebrać

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(broadcastReceiver, filter);


        // TODO: Wysyłanie parametrów po kliknięciu na guzik
//        Intent intent = new Intent();
//        intent.setAction("NAZWA_AKCJI");
//        intent.putExtra("NAZWA_PARAMETRU", wartoscA);
//        intent.putExtra("NAZWA_PARAMETRU", wartoscB);
//        localBroadcastManager.sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        localBroadcastManager.unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    @OnClick({R.id.button_plus, R.id.button_minus, R.id.button_multiply, R.id.button_divide})
    public void getButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.button_plus:
                Intent intent1 = new Intent(this, SimpleIntentService.class)
                        .setAction("GET_SUM")
                        .putExtra("FIRST_VALUE", mFirstValue.getText().toString())
                        .putExtra("SECOND_VALUE", mSecondValue.getText().toString());
                startService(intent1);
                break;
            case R.id.button_minus:
                Intent intent2 = new Intent(this, SimpleIntentService.class)
                        .setAction("GET_SUBTRACTION")
                        .putExtra("FIRST_VALUE", mFirstValue.getText().toString())
                        .putExtra("SECOND_VALUE", mSecondValue.getText().toString());
                startService(intent2);
                break;
            case R.id.button_multiply:
                Intent intent3 = new Intent(this, SimpleIntentService.class)
                        .setAction("GET_MULTIPLICATION")
                        .putExtra("FIRST_VALUE", mFirstValue.getText().toString())
                        .putExtra("SECOND_VALUE", mSecondValue.getText().toString());
                startService(intent3);
                break;
            case R.id.button_divide:
                Intent intent4 = new Intent(this, SimpleIntentService.class)
                        .setAction("GET_DIVISION")
                        .putExtra("FIRST_VALUE", mFirstValue.getText().toString())
                        .putExtra("SECOND_VALUE", mSecondValue.getText().toString());
                startService(intent4);
                break;
        }
    }
}
