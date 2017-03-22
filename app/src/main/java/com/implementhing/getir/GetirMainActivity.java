package com.implementhing.getir;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.implementhing.getir.ResponseModels.GetirElementsResult;
import com.implementhing.getir.Service.BundleKeys;
import com.implementhing.getir.Service.WebServiceRequest;
import com.implementhing.getir.Service.WebServiceResponseListener;
import com.implementhing.getir.Shapes.Rectangle;

public class GetirMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getir_main);
        Rectangle rectangle = new Rectangle(1, 2, "222333", 1, 1);
        ((TextView) findViewById(R.id.deneme)).setTextColor(rectangle.getColor());

        getGetirElementsWebService();

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

    private WebServiceResponseListener getirResponseListener = new WebServiceResponseListener() {
        @Override
        public void onResponse(String jsonString) {
            if (jsonString != null) {
                Gson gson = new Gson();
                GetirElementsResult elementsResult = gson.fromJson(jsonString, GetirElementsResult.class);

                if (elementsResult != null && elementsResult.getMsg() != null && elementsResult.getMsg().equals("Success")){
                    String s = "";
                    s = "";
                }
            }
        }
    };

}
