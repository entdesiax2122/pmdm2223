package es.rodrigo.learning.pmdm.ejemplodialogos.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import es.rodrigo.learning.pmdm.ejemplodialogos.R;
import es.rodrigo.learning.pmdm.ejemplodialogos.dialogos.OnSubmitSimpleListener;
import es.rodrigo.learning.pmdm.ejemplodialogos.modelos.Departamento;


/**
 * Created by Rodrigo on 28/05/2017.
 */

public class DepartamentoAdapter extends BaseAdapter {
    private LayoutInflater layoutinflater;
    private int numFilas = 0;
    private List<Departamento> listDepartamentos;
    private Context context;
    private OnSubmitSimpleListener onclickBorrarDepto;

    public void setListDepartamentos(List<Departamento> listDepartamentos) {
        this.listDepartamentos = listDepartamentos;
    }

    public DepartamentoAdapter(Context context, List<Departamento> customizedList,
                               OnSubmitSimpleListener onclickBorrarDepto) {
        this.context = context;
        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listDepartamentos = customizedList;
        this.onclickBorrarDepto = onclickBorrarDepto;
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
            listViewHolder.tvBorrarDepto = (TextView) convertView.findViewById(R.id.tvDeptoEliminar);
            listViewHolder.tvBorrarDepto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onclickBorrarDepto.submit(view);
                }
            });
            listViewHolder.tvIdDepto = (TextView) convertView.findViewById(R.id.tvIdDepto);
            listViewHolder.tvNombreDepto = (TextView) convertView.findViewById(R.id.tvNombreDepto);
            listViewHolder.position = position;
            convertView.setTag(listViewHolder);
        } else {
            listViewHolder = (DepartamentoViewHolder)convertView.getTag();
        }

        listViewHolder.tvIdDepto.setText(listDepartamentos.get(position).getId().toString());
        listViewHolder.tvNombreDepto.setText(listDepartamentos.get(position).getNombre());

        return convertView;
    }

    static class DepartamentoViewHolder {
        TextView tvIdDepto;
        TextView tvNombreDepto;
        TextView tvBorrarDepto;
        int position;
    }
}

