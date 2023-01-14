package com.dam.android.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dam.android.R;
import com.dam.android.dialogos.OnSubmitSimpleListener;
import com.dam.android.modelos.Departamento;

import java.util.List;

/**
 * Created by Rodrigo on 28/05/2017.
 */

public class DepartamentoAdapter extends BaseAdapter {
    private LayoutInflater layoutinflater;
    private int numFilas = 0;
    private List<Departamento> listDepartamentos;
    private Context context;
    private OnSubmitSimpleListener onclickBorrarDepto;
    private OnSubmitSimpleListener onclickEditarDepto;

    public void setListDepartamentos(List<Departamento> listDepartamentos) {
        this.listDepartamentos = listDepartamentos;
    }

    public DepartamentoAdapter(Context context, List<Departamento> customizedList,
                               OnSubmitSimpleListener onclickBorrarDepto,
                               OnSubmitSimpleListener onclickEditarDepto) {
        this.context = context;
        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listDepartamentos = customizedList;
        this.onclickBorrarDepto = onclickBorrarDepto;
        this.onclickEditarDepto = onclickEditarDepto;
    }

    @Override
    public int getCount() {
        return listDepartamentos.size();
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
                DepartamentoViewHolder listViewHolder = (DepartamentoViewHolder) vp.getTag();
                res = listViewHolder.position;
            }
        }
        return res;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DepartamentoViewHolder listViewHolder;
        if(convertView == null) {
            listViewHolder = new DepartamentoViewHolder();
            convertView = layoutinflater.inflate(R.layout.departamento_list_item, parent, false);
            numFilas++;
            // Listener para borrar departamento
            listViewHolder.tvBorrarDepto = (TextView) convertView.findViewById(R.id.tvDeptoEliminar);
            listViewHolder.tvBorrarDepto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onclickBorrarDepto.submit(view);
                }
            });
            // Listener para editar departamento
            listViewHolder.tvEditarDepto = (TextView) convertView.findViewById(R.id.tvDeptoEditar);
            listViewHolder.tvEditarDepto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onclickEditarDepto.submit(view);
                }
            });

            listViewHolder.tvIdDepto = (TextView) convertView.findViewById(R.id.tvIdDepto);
            listViewHolder.tvNombreDepto = (TextView) convertView.findViewById(R.id.tvNombreDepto);
            convertView.setTag(listViewHolder);
        } else {
            listViewHolder = (DepartamentoViewHolder)convertView.getTag();
        }

        listViewHolder.tvIdDepto.setText(listDepartamentos.get(position).getId().toString());
        listViewHolder.tvNombreDepto.setText(listDepartamentos.get(position).getNombre());
        listViewHolder.position = position;

        return convertView;
    }

    static class DepartamentoViewHolder {
        TextView tvIdDepto;
        TextView tvNombreDepto;
        TextView tvBorrarDepto;
        TextView tvEditarDepto;
        int position;
    }
}

