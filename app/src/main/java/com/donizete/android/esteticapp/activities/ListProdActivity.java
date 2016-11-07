package com.donizete.android.esteticapp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.donizete.android.esteticapp.R;
import com.donizete.android.esteticapp.model.Produto;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static android.util.Base64.DEFAULT;

public class ListProdActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ProdutoRecyclerViewAdapter recycleViewAdapter;
    private List<Produto> produtoList = new ArrayList<>();

    ListView listView;


    private String stringRecebida;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_prod);

        Toolbar toolbar = (Toolbar) findViewById(R.id.prodToolbar);
        setSupportActionBar(toolbar);

        recycleViewAdapter = new ProdutoRecyclerViewAdapter(this, produtoList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.produtoRecyclerView);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intRecebido = getIntent();
        if (intRecebido != null) {
            Bundle parametroRecebido = intRecebido.getExtras();
            if (parametroRecebido != null) {
                stringRecebida = parametroRecebido.getString("nomeProd");
            }
        }
        getProdutoList();

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    /*@Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

         Toast.makeText(ListProdActivity.this, "Voce clicou no item: " + (position), Toast.LENGTH_LONG).show();

            // TODO Auto-generated method stub
            TextView listText = (TextView) view.findViewById(R.id.listText);
            String text = listText.getText().toString();

            // create intent to start another activity
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            // add the selected text item to our intent.
            intent.putExtra("selected-item", text);
            startActivity(intent);

            if( i==1){
            Intent n = new Intent(view.getContext(), ProdutoActivity.class);
            startActivity(n);
            }


    }*/

    private void getProdutoList() {
        final DatabaseReference produtoListRef = database.getReference().child("Produtos");

        Query p = produtoListRef.orderByChild("nomeProd").startAt(stringRecebida, "nomeProd").endAt(stringRecebida + "\uf8ff", "nomeProd");

        if (p != null) {
            p.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        produtoList.clear();
                        for (DataSnapshot c : dataSnapshot.getChildren()) {
                            Produto produto = c.getValue(Produto.class);
                            produtoList.add(produto);
                        }
                        recycleViewAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(ListProdActivity.this, "Não tem registro com esse parametro", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(ListProdActivity.this, "Não foi possível recuperar dados do banco remoto", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(ListProdActivity.this, "Não tem registro com esse parametro", Toast.LENGTH_LONG).show();
            finish();

        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListProdActivity.this, "Voce clicou no item: " + (position), Toast.LENGTH_LONG).show();
            }
        };

    }

    public class ProdutoViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnItemClickListener {
        public TextView prodNomeLabel;
        public TextView prodFornecedorLabel;
        public TextView prodPrecoLabel;
        public TextView prodQtdLabel;
        public ImageView imgProduto;
        public Produto produto;

        public ProdutoViewHolder(View listView) {
            super(listView);
            this.prodNomeLabel = (TextView) listView.findViewById(R.id.prodNomeLabel);
            this.prodFornecedorLabel = (TextView) listView.findViewById(R.id.prodFornecedorLabel);
            this.prodPrecoLabel = (TextView) listView.findViewById(R.id.prodPrecoLabel);
            this.prodQtdLabel = (TextView) listView.findViewById(R.id.prodQtdLabel);
            this.imgProduto = (ImageView) listView.findViewById(R.id.imgProduct);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) listView.getLayoutParams();
            params.setMargins(35, 0, 0, 0);
            listView.setLayoutParams(params);

        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(ListProdActivity.this, "Voce clicou no item: " + (position), Toast.LENGTH_LONG).show();
        }
    }

    public class ProdutoRecyclerViewAdapter extends RecyclerView.Adapter<ProdutoViewHolder>  {
        private Context context;
        private List<Produto> produtoList;

        public ProdutoRecyclerViewAdapter(Context context, List<Produto> produtoList) {
            this.context = context;
            this.produtoList = produtoList;
        }

        @Override
        public ProdutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.produto_list_view,parent, false);
            return new ProdutoViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ProdutoViewHolder holder, int position) {
            Produto produto = produtoList.get(position);

            //DecimalFormat fmt = new DecimalFormat("R$ #,##0.00");

            holder.prodNomeLabel.setText(produto.getNomeProd());
            holder.prodFornecedorLabel.setText(produto.getFornecedor());
            holder.prodPrecoLabel.setText(Double.toString(produto.getVlProfissional()));
            holder.prodQtdLabel.setText(Integer.toString(produto.getQtdEstoque()));

            /*byte[] imageByteArray = Base64.decode(produto.getImgProduto(), Base64.DEFAULT);

            Glide.with(context)
                    .load(imageByteArray)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .centerCrop()
                    .placeholder(R.drawable.default_user_gray)
                    .into(new BitmapImageViewTarget(holder.imgProduto) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            holder.imgProduto.setImageDrawable(circularBitmapDrawable);
                        }
                    });*/

            holder.produto = produto;
        }

        @Override
        public int getItemCount() {
            return produtoList.size();
        }

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ListProd Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
