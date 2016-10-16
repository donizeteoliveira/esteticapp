package com.donizete.android.esteticapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.donizete.android.esteticapp.R;
import com.donizete.android.esteticapp.activities.Produto;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Donizete on 14/10/2016.
 */
public class ProdutosAdapter extends ArrayAdapter<Produto> {
    private LayoutInflater mInflater;
    private List<Produto> listaItens;

    public ProdutosAdapter(Context context, int resource, List<Produto> itensDaLista) {
        super(context, resource, itensDaLista);
        mInflater = LayoutInflater.from(context);
        listaItens = itensDaLista;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View formatoItemLista = mInflater.inflate(R.layout.layout_lista_produto, null);
        Produto p = listaItens.get(position);

        TextView txtLayoutName = (TextView) formatoItemLista.findViewById(R.id.txtLName);
        txtLayoutName.setText(p.getTxtNome());

        TextView txtLayoutFornecedor = (TextView) formatoItemLista.findViewById(R.id.txtLFornecedor);
        txtLayoutFornecedor.setText(p.getTxtFornecedor());

        TextView txtLayoutPriceClient = (TextView) formatoItemLista.findViewById(R.id.txtLPrice);
        DecimalFormat fmt = new DecimalFormat("R$ #,##0.00");
        txtLayoutPriceClient.setText(fmt.format(p.getVlCliente()));

        TextView txtLayoutQuantidfade = (TextView) formatoItemLista.findViewById(R.id.txtLQuantidade);
        txtLayoutQuantidfade.setText(p.getTxtFornecedor());

        return formatoItemLista;

    }
}