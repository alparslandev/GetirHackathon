package com.implementhing.getir;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.implementhing.getir.ResponseModels.Element;
import com.implementhing.getir.ResponseModels.GetirElementsResult;
import com.implementhing.getir.Service.BundleKeys;
import com.implementhing.getir.Service.WebServiceRequest;
import com.implementhing.getir.Service.WebServiceResponseListener;
import com.implementhing.getir.Shapes.Circle;
import com.implementhing.getir.Shapes.Rectangle;

public class GetirMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getir_main);
        if (isConnectingInternet())
            getGetirElementsWebService();
        else
            Toast.makeText(this, "İnternete bağlı değilsiniz", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        getGetirElementsWebService();
    }

    private void getGetirElementsWebService() {
        WebServiceRequest request = new WebServiceRequest(this, getirResponseListener);
        Bundle bundle = new Bundle();
        bundle.putString(BundleKeys.PARTICIPANT_EMAIL, "alparslan3806@gmail.com");
        bundle.putString(BundleKeys.PARTICIPANT_NAME, "Alpaslan Selçuk DEVELİOĞLU");
        bundle.putString(BundleKeys.PARTICIPANT_GSM, "+90 537 347 3201");
        request.setParams(bundle);
        request.showDialog(true);
        request.execute(WebServiceRequest.GET_ELEMENTS);
    }

    private boolean isConnectingInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    private WebServiceResponseListener getirResponseListener = new WebServiceResponseListener() {
        @Override
        public void onResponse(String jsonString) {
            if (jsonString != null) {
                Gson gson = new Gson();
                GetirElementsResult elementsResult = gson.fromJson(jsonString, GetirElementsResult.class);
                if (elementsResult != null && elementsResult.getMsg() != null && elementsResult.getMsg().equals("Success")){
                    RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_getir_main);
                    relativeLayout.removeAllViews();

                    if (elementsResult.getElements() != null && elementsResult.getElements().size() > 0) {
                        for (Element item : elementsResult.getElements()) {
                            ImageView imgDraw = new ImageView(GetirMainActivity.this);

                            if (item.getType().equals("circle")) {
                                Bitmap bitmap = Bitmap.createBitmap((int) getWindowManager()
                                        .getDefaultDisplay().getWidth(), (int) getWindowManager()
                                        .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
                                imgDraw.setImageBitmap(bitmap);
                                Circle circle = new Circle(item.getXPosition(),item.getYPosition(), item.getColor(), item.getR());
                                circle.draw(imgDraw, GetirMainActivity.this, bitmap);
                            } else {
                                Bitmap bitmap = Bitmap.createBitmap((int) getWindowManager()
                                        .getDefaultDisplay().getWidth(), (int) getWindowManager()
                                        .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
                                imgDraw.setImageBitmap(bitmap);
                                Rectangle rectangle = new Rectangle(item.getXPosition(), item.getYPosition(), item.getColor(), item.getWidth(), item.getHeight());
                                rectangle.draw(imgDraw, GetirMainActivity.this, bitmap);
                            }
                            relativeLayout.addView(imgDraw);
                        }

                    }
                }
            }
        }
    };

}
