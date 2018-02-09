package com.example.medet.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.zxing.Result;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class qrScanner extends Activity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView scannerView;
    FrameLayout frameLayout;
    ArrayList<String> goods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);

        frameLayout = findViewById(R.id.frameLayout);
        scannerView = new ZXingScannerView(this);
        frameLayout.addView(scannerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        String res = result.getText();
        Toast.makeText(this,res,Toast.LENGTH_SHORT).show();
        goods.add(res);

        scannerView.resumeCameraPreview(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }

//    public void goToPay(View v){
//        scannerView.stopCamera();
//        Intent intent = new Intent();
//        intent.putStringArrayListExtra("goods",goods);
//        setResult(RESULT_OK, intent);
//        finish();
//    }
}
