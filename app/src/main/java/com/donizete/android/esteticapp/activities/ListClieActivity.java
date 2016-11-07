package com.donizete.android.esteticapp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.donizete.android.esteticapp.R;
import com.donizete.android.esteticapp.model.Cliente;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListClieActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListClieActivity.ClienteRecyclerViewAdapter recycleViewAdapter;
    private List<Cliente> clienteList = new ArrayList<>();

    private String stringRecebida;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private GoogleApiClient client;
    public static final int CADASTRO_REQUEST=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_clie);


        Toolbar toolbar = (Toolbar) findViewById(R.id.funcToolbar);
        setSupportActionBar(toolbar);

       recycleViewAdapter = new ClienteRecyclerViewAdapter(this, clienteList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.clienteRecyclerView);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // REMOVE:   LISTADECLIENTE.REMOVE(OBJ) NO LUGAR DO OBJETO COLOCAR O INDICE OU BUSCAR POSITION

        Intent intRecebido = getIntent();
        if (intRecebido != null) {
            Bundle parametroRecebido = intRecebido.getExtras();
            if (parametroRecebido != null) {
                stringRecebida = parametroRecebido.getString("nome");
            }
        }

        getClienteList();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ListClieActivity.this, "Voce clicou no item: " + (position+
                1), Toast.LENGTH_LONG).show();

    }

    private void getClienteList() {

        final DatabaseReference funcionarioListRootRef = database.getReference().child("Funcionarios");
        //
        //Verificar com o Keniston como e onde validar quando não achar um registro compatível
        //
        Query q = funcionarioListRootRef.orderByChild("nome").startAt(stringRecebida, "nome").endAt(stringRecebida + "\uf8ff", "nome");
        if (q != null) {
            q.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    clienteList.clear();
                    for (DataSnapshot c : dataSnapshot.getChildren()) {
                        Cliente funcionario = c.getValue(Cliente.class);
                        clienteList.add(funcionario);
                    }
                    recycleViewAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(ListClieActivity.this, R.string.error_realtime_get_data, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(ListClieActivity.this, R.string.error_realtime_get_data, Toast.LENGTH_LONG).show();
        }

    }




    public class ClienteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mClieNomeLabel;
        public TextView mClieEmailLabel;
        public ImageView mClieImgView;
        public Cliente cliente;

        public ClienteViewHolder(View listView) {
            super(listView);
            this.mClieNomeLabel = (TextView) listView.findViewById(R.id.clieNomeLabel);
            this.mClieEmailLabel = (TextView) listView.findViewById(R.id.clieEmailLabel);
            this.mClieImgView = (ImageView) listView.findViewById(R.id.clieImgView);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) listView.getLayoutParams();
            params.setMargins(35, 0, 0, 0);
            listView.setLayoutParams(params);
        }

        @Override
        public void onClick(View v) {
        }
    }

    public class ClienteRecyclerViewAdapter extends RecyclerView.Adapter<ClienteViewHolder> {
        private Context context;
        private List<Cliente> clienteList;

        public ClienteRecyclerViewAdapter(Context context, List<Cliente> clienteList) {
            this.context = context;
            this.clienteList = clienteList;
        }

        @Override
        public ClienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.cliente_list_view, parent, false);
            return new ClienteViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ClienteViewHolder holder, int position) {
            Cliente cliente = clienteList.get(position);

            holder.mClieNomeLabel.setText(cliente.getNome());
            holder.mClieEmailLabel.setText(cliente.getEmail());

            byte[] imageByteArray = Base64.decode(cliente.getImgCliente(), Base64.DEFAULT);

            Glide.with(context)
                    .load(imageByteArray)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .centerCrop()
                    .placeholder(R.drawable.default_user_gray)
                    .into(new BitmapImageViewTarget(holder.mClieImgView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            holder.mClieImgView.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            holder.cliente = cliente;
        }

        @Override
        public int getItemCount() {
            return clienteList.size();
        }
    }

}
