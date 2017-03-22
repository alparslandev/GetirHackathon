package com.implementhing.getir.Service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by AlparslanSelçuk on 22.03.2017.
 */

public class WebServiceRequest extends AsyncTask<Integer, Void, String> {
    private final String USER_AGENT = "Mozilla/5.0";
    private WebServiceResponseListener responseListener;
    private String msg = "Lütfen Bekleyiniz...";
    private ProgressDialog serviceProgress;
    private boolean showDialog = false;
    private Activity activity;
    Bundle bundle;

    public static final int GET_ELEMENTS = 0;

    public WebServiceRequest(Activity activity, WebServiceResponseListener responseListener) {
        this.activity = activity;
        this.responseListener = responseListener;
    }

    private String getQuery(List<KeyValuePair> params) {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (KeyValuePair pair : params) {
            try {
                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(pair.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }

    private String getResponse(List<KeyValuePair> paramsList) {
        String url = "https://getir-bitaksi-hackathon.herokuapp.com/getElements";
        URL obj = null;
        try {
            obj = new URL(url);

            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            String params = getQuery(paramsList);
            wr.writeBytes(params);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            Log.i("Response Code : ", String.valueOf(responseCode));

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String doInBackground(Integer... integers) {
        switch (integers[0]) {
            case GET_ELEMENTS:
                List<KeyValuePair> paramsList = new ArrayList<>();
                paramsList.add(new KeyValuePair(BundleKeys.PARTICIPANT_GSM, bundle.getString(BundleKeys.PARTICIPANT_GSM)));
                paramsList.add(new KeyValuePair(BundleKeys.PARTICIPANT_EMAIL, bundle.getString(BundleKeys.PARTICIPANT_EMAIL)));
                paramsList.add(new KeyValuePair(BundleKeys.PARTICIPANT_NAME, bundle.getString(BundleKeys.PARTICIPANT_NAME)));
                return getResponse(paramsList);
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        if (showDialog) {
            try {
                serviceProgress = new ProgressDialog(activity);
                serviceProgress = ProgressDialog.show(activity, null, msg, true);
                serviceProgress.setCancelable(true);
                serviceProgress.setCanceledOnTouchOutside(false);
                serviceProgress.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                });
            } catch (Exception e) {
                Log.e("Error Web Service call", e.getMessage());
            }
        }
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            if (serviceProgress != null && serviceProgress.isShowing()) {
                serviceProgress.dismiss();
            }
        } catch (final IllegalArgumentException e) {
            // Handle or log or ignore
        } catch (final Exception e) {
            // Handle or log or ignore
        } finally {
            this.serviceProgress = null;
        }

        if (responseListener != null)
            responseListener.onResponse(result);
    }

    public void setParams(Bundle bundle) {
        this.bundle = bundle;
    }

    public void showDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
