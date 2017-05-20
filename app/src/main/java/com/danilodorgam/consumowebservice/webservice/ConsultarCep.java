package com.danilodorgam.consumowebservice.webservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.danilodorgam.consumowebservice.interfaces.RetornoWbInterface;
import com.danilodorgam.consumowebservice.model.Cep;

import org.json.JSONException;
import org.json.JSONObject;

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
            retornoWbInterface.retornoByCep(parseToCep(posExecute));
        }else{
            retornoWbInterface.retornoByCep(null);
        }
        mProgressDialog.dismiss();
    }
    @Override
    protected String doInBackground(String... params) {
        return Network.getAdressByCep(URL_PRE+params[0]+URL_POS);
    }

    private Cep parseToCep(String json){
        JSONObject jsonObject;
        Cep cep = new Cep();
        try {
            jsonObject = new JSONObject(json);
            if(!jsonObject.has("erro")){
                cep.setCep(jsonObject.getString("cep"));
                cep.setLogradouro(jsonObject.getString("logradouro"));
                cep.setComplemento(jsonObject.getString("complemento"));
                cep.setBairro(jsonObject.getString("bairro"));
                cep.setLocalidade(jsonObject.getString("localidade"));
                cep.setUf(jsonObject.getString("uf"));
                cep.setUnidade(jsonObject.getString("unidade"));
                cep.setIbge(jsonObject.getString("ibge"));
                cep.setGia(jsonObject.getString("gia"));
            }else{
                return  null;
            }
            return cep;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
