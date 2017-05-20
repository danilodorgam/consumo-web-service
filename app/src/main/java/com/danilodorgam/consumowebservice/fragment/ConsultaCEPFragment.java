package com.danilodorgam.consumowebservice.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.danilodorgam.consumowebservice.R;
import com.danilodorgam.consumowebservice.interfaces.RetornoWbInterface;
import com.danilodorgam.consumowebservice.webservice.ConsultarCep;

public class ConsultaCEPFragment extends Fragment implements RetornoWbInterface, View.OnClickListener {

    private Button submitButton;
    private EditText cepText;
    private TextView resultadoText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_consulta_cep, container, false);

        cepText = (EditText) view.findViewById(R.id.cepText);
        resultadoText = (TextView) view.findViewById(R.id.resultado);
        submitButton = (Button) view.findViewById(R.id.consultaButton);
        submitButton.setOnClickListener(this);



        return view;
    }

    @Override
    public void retornoByText(String string) {
        resultadoText.setText(string);
    }

    @Override
    public void onClick(View v) {
        ConsultarCep consultarCep = new ConsultarCep(getActivity(),this);
        consultarCep.execute(cepText.getText().toString());
    }
}
