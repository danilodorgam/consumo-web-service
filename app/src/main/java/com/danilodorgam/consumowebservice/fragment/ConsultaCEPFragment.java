package com.danilodorgam.consumowebservice.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.danilodorgam.consumowebservice.R;
import com.danilodorgam.consumowebservice.dao.CepDao;
import com.danilodorgam.consumowebservice.interfaces.RetornoWbInterface;
import com.danilodorgam.consumowebservice.model.Cep;
import com.danilodorgam.consumowebservice.webservice.ConsultarCep;

import org.json.JSONException;
import org.json.JSONObject;

public class ConsultaCEPFragment extends Fragment implements RetornoWbInterface, View.OnClickListener {

    private Button submitButton;
    private EditText cepText;
    private RelativeLayout resultadoLayout;
    private TextView cepViewContent;
    private  TextView logradouroViewContent;
    private TextView bairroViewContent;
    private TextView localidadeViewContent;
    private TextView ufViewContent;
    private TextView ibgeViewContent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_consulta_cep, container, false);
        initResultado(view);
        cepText = (EditText) view.findViewById(R.id.cepText);
        submitButton = (Button) view.findViewById(R.id.consultaButton);
        submitButton.setOnClickListener(this);



        return view;
    }

    @Override
    public void retornoByCep(Cep cep) {
       if(cep!= null){
           resultadoLayout.setVisibility(View.VISIBLE);
           cepViewContent.setText(cep.getCep());
           logradouroViewContent.setText(cep.getLogradouro());
           bairroViewContent.setText(cep.getBairro());
           localidadeViewContent.setText(cep.getLocalidade());
           ufViewContent.setText(cep.getUf());
           ibgeViewContent.setText(cep.getIbge());

           //insere no banco
           CepDao cepDao = new CepDao(getActivity());
           if(cepDao.insertCep(cep)) {
               Toast.makeText(getActivity(), "Endereço Salvo com Sucesso", Toast.LENGTH_LONG).show();
           }else {
               Toast.makeText(getActivity(), "tivemos aglum problema", Toast.LENGTH_LONG).show();
           }
       }else {
           resultadoLayout.setVisibility(View.INVISIBLE);
       }

    }

    private void initResultado(View view){
        resultadoLayout = (RelativeLayout) view.findViewById(R.id.relaviveLayoutResult);
        cepViewContent=  (TextView) view.findViewById(R.id.cepViewContent);
        logradouroViewContent = (TextView) view.findViewById(R.id.logradouroViewContent);
        bairroViewContent = (TextView) view.findViewById(R.id.bairroViewContent);
        localidadeViewContent = (TextView) view.findViewById(R.id.localidadeViewContent);
        ufViewContent = (TextView) view.findViewById(R.id.ufViewContent);
        ibgeViewContent = (TextView) view.findViewById(R.id.ibgeViewContent);

    }

    @Override
    public void onClick(View v) {
        ConsultarCep consultarCep = new ConsultarCep(getActivity(),this);
        consultarCep.execute(cepText.getText().toString());
    }
}
