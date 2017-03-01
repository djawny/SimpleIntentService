package com.example.http.simpleintentservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;


public class SimpleIntentService extends IntentService {

    public SimpleIntentService() {
        super("SimpleIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            double firstValue;
            double secondValue;
            double result;
            if ("GET_SUM".equals(action)) {
                firstValue = intent.getDoubleExtra("FIRST_VALUE", 0);
                secondValue = intent.getDoubleExtra("SECOND_VALUE", 0);
                result = add(firstValue, secondValue);

            } else if ("GET_SUBTRACTION".equals(action)) {
                firstValue = intent.getDoubleExtra("FIRST_VALUE", 0);
                secondValue = intent.getDoubleExtra("SECOND_VALUE", 0);
                result = subtract(firstValue, secondValue);

            } else if ("GET_MULTIPLICATION".equals(action)) {
                firstValue = intent.getDoubleExtra("FIRST_VALUE", 0);
                secondValue = intent.getDoubleExtra("SECOND_VALUE", 0);
                result = multiply(firstValue, secondValue);

            } else if ("GET_DIVISION".equals(action)) {
                firstValue = intent.getDoubleExtra("FIRST_VALUE", 0);
                secondValue = intent.getDoubleExtra("SECOND_VALUE", 1);
                result = divide(firstValue, secondValue);

            }
        }
    }

    private void broadcastResult(double result) {
        Intent intent = new Intent();
        intent.setAction("action.CALCULATION_RESULT");
        intent.putExtra("extra.VALUE", result);

        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.sendBroadcast(intent);
    }

    private double add(double a, double b) {
        return a + b;
    }

    private double subtract(double a, double b) {
        return a - b;
    }

    private double multiply(double a, double b) {
        return a * b;
    }

    private double divide(double a, double b) {
        return a / b;
    }
}
