package com.example.javaparcelableproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import model.Send;
import model.Sent;

public class MainActivity extends AppCompatActivity {
    static final String TAG = MainActivity.class.toString();

    TextView tv_home;//int LAUNCH_DETAIL = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    ActivityResultLauncher<Intent> detailLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Sent sent = new Sent(14, "Zarifjon");
                        Log.d(TAG, "sent");
                        tv_home.setText(sent.toString());
                    }
                }
            });

    void initViews() {
        tv_home = findViewById(R.id.home);

        Button b_detail = findViewById(R.id.b_detail);
        b_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sent sent = new Sent(14, "Zarifjon");
                openDetailActivity(sent);
            }

            ;
        });
       if(getIntent().hasExtra("send")){
        Send send = (Send)getIntent().getParcelableExtra("send");
        Log.d(TAG, send.toString());

        tv_home.setText(send.toString());
    }
    }

    void openDetailActivity(Sent sent){
        Intent intent = new Intent(this, ActivityDetail1.class);
        intent.putExtra("sent", (Parcelable) sent);
        setResult(Activity.RESULT_OK);
        detailLauncher.launch(intent);

    }
}