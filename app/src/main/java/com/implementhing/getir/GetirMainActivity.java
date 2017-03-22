package com.implementhing.getir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

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
                    ImageView imgDraw = (ImageView) findViewById(R.id.img_draw_shapes);

                    if (elementsResult.getElements() != null && elementsResult.getElements().size() > 0) {
                        for (Element item : elementsResult.getElements()) {
                            if (item.getType().equals("circle")) {
                                Circle circle = new Circle(item.getXPosition(),item.getYPosition(), item.getColor(), item.getR());
                                circle.draw(imgDraw, GetirMainActivity.this);
                            } else {
                                Rectangle rectangle = new Rectangle(item.getXPosition(), item.getYPosition(), item.getColor(), item.getWidth(), item.getHeight());
                                rectangle.draw(imgDraw, GetirMainActivity.this);
                            }
                        }

                    }
                }
            }
        }
    };

}
