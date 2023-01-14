package com.dam.android.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dam.android.R;
import com.dam.android.modelos.Actividad;
import com.dam.android.modelos.Departamento;

import java.util.List;

public class ActividadAdapter extends BaseAdapter {
    private LayoutInflater layoutinflater;
    private int numFilas = 0;
    private List<Actividad> listActividades;
    private Context context;

    public void setListActividades(List<Actividad> listActividades) {
        this.listActividades = listActividades;
    }

    public ActividadAdapter(Context context, List<Actividad> customizedList) {
        this.context = context;
        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listActividades = customizedList;
    }

    @Override
    public int getCount() {
        return listActividades.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int getAdapterItemPosition(View v)
    {
        int res = -1;
        if (v != null && v.getParent() != null) {
            View vp = (View) v.getParent();
            if (vp.getTag() != null) {
                ActividadViewHolder listViewHolder = (ActividadViewHolder) vp.getTag();
                res = listViewHolder.position;
            }
        }
        return res;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ActividadViewHolder listViewHolder;
        if(convertView == null) {
            listViewHolder = new ActividadViewHolder();
            convertView = layoutinflater.inflate(R.layout.actividad_list_item, parent, false);
            numFilas++;
            listViewHolder.tvIdActividad = (TextView) convertView.findViewById(R.id.tvIdActividad);
            listViewHolder.tvDeptoActividad = (TextView) convertView.findViewById(R.id.tvDeptoActividad);
            listViewHolder.tvLugarActividad = (TextView) convertView.findViewById(R.id.tvLugarActividad);
            listViewHolder.tvTituloActividad = (TextView) convertView.findViewById(R.id.tvTituloActividad);
            convertView.setTag(listViewHolder);
        } else {
            listViewHolder = (ActividadViewHolder)convertView.getTag();
        }

        listViewHolder.tvIdActividad.setText(listActividades.get(position).getId().toString());
        Departamento d = listActividades.get(position).getDepartamento();
        String nombreDepto = d != null ? d.getNombre() : "";
        listViewHolder.tvDeptoActividad.setText(nombreDepto);
        listViewHolder.tvLugarActividad.setText(listActividades.get(position).getLugar());
        listViewHolder.tvTituloActividad.setText(listActividades.get(position).getTitulo());
        listViewHolder.position = position;

        return convertView;
    }

    static class ActividadViewHolder {
        TextView tvIdActividad;
        TextView tvDeptoActividad;
        TextView tvLugarActividad;
        TextView tvTituloActividad;
        int position;
    }
}

