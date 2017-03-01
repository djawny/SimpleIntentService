package com.example.http.simpleintentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
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
//            double result = intent.getDoubleExtra("extra.RESULT", 0);
//            mResult.setText(String.valueOf(result));
            SharedPreferences preferences = getSharedPreferences("action_result", MODE_PRIVATE);
            float result = preferences.getFloat("result", 0);
            mResult.setText(String.valueOf(result));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("action.CALCULATION_RESULT");
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(broadcastReceiver, filter);
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
                        .setAction("action.GET_SUM")
                        .putExtra("extra.FIRST_VALUE", Double.parseDouble(mFirstValue.getText().toString()))
                        .putExtra("extra.SECOND_VALUE", Double.parseDouble(mSecondValue.getText().toString()));
                startService(intent1);
                break;
            case R.id.button_minus:
                Intent intent2 = new Intent(this, SimpleIntentService.class)
                        .setAction("action.GET_SUBTRACTION")
                        .putExtra("extra.FIRST_VALUE", Double.parseDouble(mFirstValue.getText().toString()))
                        .putExtra("extra.SECOND_VALUE", Double.parseDouble(mSecondValue.getText().toString()));
                startService(intent2);
                break;
            case R.id.button_multiply:
                Intent intent3 = new Intent(this, SimpleIntentService.class)
                        .setAction("action.GET_MULTIPLICATION")
                        .putExtra("extra.FIRST_VALUE", Double.parseDouble(mFirstValue.getText().toString()))
                        .putExtra("extra.SECOND_VALUE", Double.parseDouble(mSecondValue.getText().toString()));
                startService(intent3);
                break;
            case R.id.button_divide:
                Intent intent4 = new Intent(this, SimpleIntentService.class)
                        .setAction("action.GET_DIVISION")
                        .putExtra("extra.FIRST_VALUE", Double.parseDouble(mFirstValue.getText().toString()))
                        .putExtra("extra.SECOND_VALUE", Double.parseDouble(mSecondValue.getText().toString()));
                startService(intent4);
                break;
        }
    }
}
