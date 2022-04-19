package com.example.javaparcelableproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import model.Send;
import model.Sent;

public class ActivityDetail1 extends AppCompatActivity {

    static final String TAG = ActivityDetail1.class.toString();

    TextView tv_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail1);
        initViews();
    }

    ActivityResultLauncher<Intent> detailLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent m = result.getData();
                        Send send = new Send(1.4,  "Big");
                        Log.d(TAG, "send");
                        tv_detail.setText(send.toString());
                    }
                }
            });

    void initViews(){
        tv_detail = findViewById(R.id.tv_detail);

        Button b_exit = findViewById(R.id.b_exit);
        b_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Send send = new Send(3, "Big");
                backToFinish(send);
            }
        });
        Sent sent = (Sent)getIntent().getParcelableExtra("sent");
        Log.d(TAG, sent.toString());

        tv_detail.setText(sent.toString());
    }

    void backToFinish(Send send){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("send", send);
        setResult(Activity.RESULT_OK, intent);
        detailLauncher.launch(intent);
        finish();
    }
}