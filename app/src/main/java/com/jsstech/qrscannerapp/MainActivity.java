package com.jsstech.qrscannerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
Button btScaner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btScaner=findViewById(R.id.bt_scanner);

        btScaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator intentIntegrator=new IntentIntegrator(MainActivity.this);

                intentIntegrator.setPrompt(" Scan QR Code");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(CaptureAct.class);
                intentIntegrator.initiateScan();

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        IntentResult intentResult=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if (intentResult.getContents() != null){

            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Result");
            builder.setMessage(intentResult.getContents());
            builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface,int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        }
        else {
            Toast.makeText(getApplicationContext(),"not scann",Toast.LENGTH_SHORT).show();
        }
    }
}