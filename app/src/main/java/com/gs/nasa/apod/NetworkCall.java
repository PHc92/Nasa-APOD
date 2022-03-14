package com.gs.nasa.apod;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class NetworkCall {

    private int responseCode;
    private JSONObject json;
    private Bitmap bitmap;

    Object makeServiceCall(String url, String value) {
        HttpURLConnection connection = null;
        InputStream is = null;

        try {
            URL imgUrl = new URL(url);
            connection = (HttpURLConnection) imgUrl.openConnection();
            responseCode = connection.getResponseCode();
            is = connection.getInputStream();
            Log.e("RESPONSE CODE",""+ responseCode);
            if (value.equals("Json")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder total = new StringBuilder(is.available());
                String line;
                while ((line = reader.readLine()) != null) {
                    total.append(line).append('\n');
                }
                String output = total.toString();
                json = new JSONObject(output);
            } else {
                bitmap = BitmapFactory.decodeStream(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    connection.disconnect();
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (value.equals("Json"))
            return json;
        else
            return bitmap;
    }


    public int getResponseCode() {
        return responseCode;
    }
}
