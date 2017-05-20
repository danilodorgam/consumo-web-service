package com.danilodorgam.consumowebservice.webservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.danilodorgam.consumowebservice.interfaces.RetornoWbInterface;

/**
 * Created by danil on 20/05/2017.
 */

public class ConsultarCep extends AsyncTask<String, Void, String> {
    private ProgressDialog mProgressDialog;
    private Context mContext;
    private static final String URL_PRE = "http://viacep.com.br/ws/";
    private static final String URL_POS = "/json";
    private RetornoWbInterface retornoWbInterface;

    public ConsultarCep(Context context, RetornoWbInterface retornoWbInterface){
        mContext = context;
        this.retornoWbInterface = retornoWbInterface;
    }


    protected void onPreExecute(){
        mProgressDialog = ProgressDialog.show(mContext,"Aguarde","Só um minuto");
    }
    protected void onPostExecute(String posExecute){
        if(posExecute!= null){
            retornoWbInterface.retornoByText(posExecute.toString());
        }else{
            retornoWbInterface.retornoByText("falha!");
        }
        mProgressDialog.dismiss();
    }
    @Override
    protected String doInBackground(String... params) {
        return Network.getAdressByCep(URL_PRE+params[0]+URL_POS);
    }

    public ProgressDialog getmProgressDialog() {
        return mProgressDialog;
    }

}
