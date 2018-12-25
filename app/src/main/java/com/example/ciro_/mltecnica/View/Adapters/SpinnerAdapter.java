package com.example.ciro_.mltecnica.View.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ciro_.mltecnica.Model.Banco;
import com.example.ciro_.mltecnica.Model.MetodoDePago;
import com.example.ciro_.mltecnica.R;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {

    private List<String> listaDeTexto;
    private Activity activity;
    private LayoutInflater layoutInflater;
    private List<MetodoDePago> listaMetodosDePago;
    private List<Banco> listaDeBancos;

    public SpinnerAdapter(List<String> listaDeTexto, Activity activity, List<MetodoDePago> listaMetodosDePago) {
        this.listaDeTexto = listaDeTexto;
        this.activity = activity;
        this.layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaMetodosDePago = listaMetodosDePago;
    }


    @Override
    public int getCount() {
        return listaDeTexto.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (convertView == null){
            view = layoutInflater.inflate(R.layout.spinner_custom,null);
        }
        TextView textView = view.findViewById(R.id.textViewSpinner);
        textView.setText(listaDeTexto.get(position));

        ImageView imageView = view.findViewById(R.id.imgViewSpinner);
        Glide.with(view)
                .load(listaMetodosDePago.get(position).getThumbnail())
                .into(imageView);

        return view;
    }
}
