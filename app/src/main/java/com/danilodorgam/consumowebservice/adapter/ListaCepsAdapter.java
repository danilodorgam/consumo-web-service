package com.danilodorgam.consumowebservice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danilodorgam.consumowebservice.R;
import com.danilodorgam.consumowebservice.interfaces.RecyclerViewOnClickListenHack;
import com.danilodorgam.consumowebservice.model.Cep;

import java.util.List;

/**
 * Created by danil on 21/05/2017.
 */

public class ListaCepsAdapter extends RecyclerView.Adapter<ListaCepsAdapter.MyViewHolder> {
    private List<Cep> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenHack mRecyclerViewOnClickListenHack;

    public ListaCepsAdapter(Context context, List<Cep> lista){
        mList = lista;
        mLayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_cep,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.cep.setText(mList.get(position).getCep());
        holder.bairro.setText(mList.get(position).getBairro());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public void setCepList(Cep cep, int position){
        mList.add(cep);
        notifyItemInserted(position);
    }

    public void setRecyclerViewOnClickListenHack(RecyclerViewOnClickListenHack r){
        mRecyclerViewOnClickListenHack = r;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView bairro;
        private TextView cep;
        public MyViewHolder(View view){
            super(view);
            bairro = (TextView) view.findViewById(R.id.bairroViewContent);
            cep = (TextView) view.findViewById(R.id.cepViewContent);
        }

        @Override
        public void onClick(View v) {
            if(mRecyclerViewOnClickListenHack!=null){
                mRecyclerViewOnClickListenHack.onClickListen(v,getAdapterPosition());
            }
        }
    }
}
