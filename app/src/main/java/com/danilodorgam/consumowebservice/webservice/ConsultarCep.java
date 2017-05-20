package com.danilodorgam.consumowebservice.webservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by danil on 20/05/2017.
 */

public class ConsultarCep extends AsyncTask<String, Void, String> {
    private ProgressDialog mProgressDialog;
    private Context mContext;
    private String result;

    public ConsultarCep(Context context){
        mContext = context;
    }
    protected void onPreExecute(){
        mProgressDialog = ProgressDialog.show(mContext,"Aguarde","Só um minuto");
    }
    protected void onPostExecute(String posExecute){
        result = posExecute.toString();
        mProgressDialog.dismiss();
    }
    @Override
    protected String doInBackground(String... params) {
        return Network.getAdressByCep(params[0]);
    }

    public ProgressDialog getmProgressDialog() {
        return mProgressDialog;
    }
    public String toString(){
        return result;
    }
}
