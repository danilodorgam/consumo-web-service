package com.danilodorgam.consumowebservice.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.danilodorgam.consumowebservice.DetalhesCepActivity;
import com.danilodorgam.consumowebservice.R;
import com.danilodorgam.consumowebservice.adapter.ListaCepsAdapter;
import com.danilodorgam.consumowebservice.dao.CepDao;
import com.danilodorgam.consumowebservice.interfaces.RecyclerViewOnClickListenHack;
import com.danilodorgam.consumowebservice.model.Cep;

import java.util.List;

public class ListaCepsFragment extends Fragment implements RecyclerViewOnClickListenHack {
    private RecyclerView mRecyclerView;
    private List<Cep> mList;
    private CepDao mCepDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_lista_ceps, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                ListaCepsAdapter listaCepsAdapter = (ListaCepsAdapter) mRecyclerView.getAdapter();
                if(mList.size() == linearLayoutManager.findLastVisibleItemPosition()+1){
                    List<Cep> listAux;
                    if(mCepDao != null){
                        listAux = mCepDao.getCeps(mList.size());
                    }else{
                        mCepDao = new CepDao(getActivity());
                        listAux = mCepDao.getCeps(mList.size());
                    }
                    for (int i = 0; i < listAux.size(); i++){
                        listaCepsAdapter.setCepList(listAux.get(i),mList.size());
                    }
                }
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mCepDao = new CepDao(getActivity());
        mList = mCepDao.getCeps(0);
        ListaCepsAdapter listaCepsAdapter = new ListaCepsAdapter(getActivity(),mList);

        listaCepsAdapter.setRecyclerViewOnClickListenHack(this);
        mRecyclerView.setAdapter(listaCepsAdapter);

        return view;
    }

    @Override
    public void onClickListen(View view, int position) {
        Toast.makeText(getActivity(),"Posicao"+position,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), DetalhesCepActivity.class);
        startActivity(intent);

    }
}
