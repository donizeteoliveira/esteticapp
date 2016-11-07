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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.donizete.android.esteticapp.R;
import com.donizete.android.esteticapp.model.Funcionario;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.vision.text.Text;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListFuncActivity extends AppCompatActivity {

    private FuncionarioRecyclerViewAdapter recycleViewAdapter;
    private List<Funcionario> funcionarioList = new ArrayList<>();

    private String stringRecebida;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_func);

        Toolbar toolbar = (Toolbar) findViewById(R.id.funcToolbar);
        setSupportActionBar(toolbar);

        recycleViewAdapter = new FuncionarioRecyclerViewAdapter(this, funcionarioList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.funcionarioRecyclerView);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intRecebido = getIntent();
        if (intRecebido != null) {
            Bundle parametroRecebido = intRecebido.getExtras();
            if (parametroRecebido != null) {
                stringRecebida = parametroRecebido.getString("nome");
            }
        }
        getFuncionarioList();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void getFuncionarioList() {

        final DatabaseReference funcionarioListRootRef = database.getReference().child("Funcionarios");
        //
        //Verificar com o Keniston como e onde validar quando não achar um registro compatível
        //
        Query q = funcionarioListRootRef.orderByChild("nome").startAt(stringRecebida, "nome").endAt(stringRecebida + "\uf8ff", "nome");
        if (q != null) {
            q.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    funcionarioList.clear();
                    for (DataSnapshot c : dataSnapshot.getChildren()) {
                        Funcionario funcionario = c.getValue(Funcionario.class);
                        funcionarioList.add(funcionario);
                    }
                    recycleViewAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(ListFuncActivity.this, R.string.error_realtime_get_data, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(ListFuncActivity.this, R.string.error_realtime_get_data, Toast.LENGTH_LONG).show();
        }

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ListFunc Page") // TODO: Define a title for the content shown.
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

    public class FuncionarioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView funcNomeLabel;
        public TextView funcEmailLabel;
        public ImageView funcImgView;
        public Funcionario funcionario;

        public FuncionarioViewHolder(View listView) {
            super(listView);
            this.funcNomeLabel = (TextView) listView.findViewById(R.id.funcNomeLabel);
            this.funcEmailLabel = (TextView) listView.findViewById(R.id.funcEmailLabel);
            this.funcImgView = (ImageView) listView.findViewById(R.id.funcImgView);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) listView.getLayoutParams();
            params.setMargins(35, 0, 0, 0);
            listView.setLayoutParams(params);
        }

        @Override
        public void onClick(View v) {
        }
    }

    public class FuncionarioRecyclerViewAdapter extends RecyclerView.Adapter<FuncionarioViewHolder> {
        private Context context;
        private List<Funcionario> funcionarioList;

        public FuncionarioRecyclerViewAdapter(Context context, List<Funcionario> funcionarioList) {
            this.context = context;
            this.funcionarioList = funcionarioList;
        }

        @Override
        public FuncionarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.func_list_view, parent, false);
            return new FuncionarioViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final FuncionarioViewHolder holder, int position) {
            Funcionario funcionario = funcionarioList.get(position);

            holder.funcNomeLabel.setText(funcionario.getNome());
            holder.funcEmailLabel.setText(funcionario.getEmail());

            byte[] imageByteArray = Base64.decode(funcionario.getImgfunc(), Base64.DEFAULT);

            Glide.with(context)
                    .load(imageByteArray)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .centerCrop()
                    .placeholder(R.drawable.default_user_gray)
                    .into(new BitmapImageViewTarget(holder.funcImgView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            holder.funcImgView.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            holder.funcionario = funcionario;
        }

        @Override
        public int getItemCount() {
            return funcionarioList.size();
        }
    }

}


