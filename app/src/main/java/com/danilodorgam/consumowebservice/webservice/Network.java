package com.danilodorgam.consumowebservice.webservice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by danil on 20/05/2017.
 */

public class Network {
    private static final int CONECT_TIMEOUT = 2000;
    private static final int READ_CONNECT = 2000;
    private static final String REQUEST_METHOD = "GET";
    public static String getAdressByCep(String cep){

        String result = null;
        try{
            URL endPoint = new URL(cep);
            HttpURLConnection httpURLConnection;
            InputStream inputStream;
            httpURLConnection = (HttpURLConnection) endPoint.openConnection();
            httpURLConnection.setRequestMethod(REQUEST_METHOD);
            httpURLConnection.setReadTimeout(CONECT_TIMEOUT);
            httpURLConnection.setConnectTimeout(READ_CONNECT);
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode() == 200){
                inputStream = httpURLConnection.getInputStream();
                result = parseToString(inputStream);
            }

        }catch (Exception e){

        }

        return result;
    }

    private static String parseToString(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        String row = null;
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((row = bufferedReader.readLine()) != null) {
                stringBuilder.append(row);
            }

        }catch(Exception e){

        }
        return stringBuilder.toString();
    }
}
