package com.donizete.android.esteticapp.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.donizete.android.esteticapp.Adapters.User;
import com.donizete.android.esteticapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Donizete on 16/10/2016.
 */

public class UsuarioActivity extends AppCompatActivity{
    /**private userRecyclerViewAdapter recycleViewAdapter;*/
    private List<User> userBook = new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference userBookRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        /**Toolbar toolbar = (Toolbar) findViewById(R.id.userToolbar);
        setSupportActionBar(toolbar);

        recycleViewAdapter = new UserRecyclerViewAdapter(this, userToolbar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.userRecyclerView);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        */
        getUserBook();
    }
    private void getUserBook(){
        database = FirebaseDatabase.getInstance();
        userBookRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userBook.clear();
                for (DataSnapshot c : dataSnapshot.getChildren()){
                    User user = c.getValue(User.class);
                    userBook.add(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UsuarioActivity.this,"Não foi possível recuperar dados remoto", Toast.LENGTH_LONG).show();
            }
        });
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView userNameLabel;
        public TextView userEmailLabel;
        public ImageView userImageView;

        public UserViewHolder(View itemView){
            super(itemView);

            this.userNameLabel = (TextView) itemView.findViewById(R.id.userNameLabel);
            this.userEmailLabel = (TextView) itemView.findViewById(R.id.userEmailLabel);
            this.userImageView = (ImageView) itemView.findViewById(R.id.userImageView);
            itemView.setOnClickListener(this);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemView.getLayoutParams();
            params.setMargins(35,0,0,0);
            itemView.setLayoutParams(params);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserViewHolder>{
        private Context context;
        private List<User> userBook;

        public UserRecyclerViewAdapter(Context context, List<User> userBook){
            this.context = context;
            this.userBook = userBook;
        }
        @Override
        public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(context).inflate(R.layout.layout_lista_users, parent, false);
            return new UserViewHolder(view);
        }
        @Override
        public void onBindViewHolder(final UserViewHolder holder, int position){
            User user = userBook.get(position);

            holder.userNameLabel.setText(user.getName());
            holder.userEmailLabel.setText(user.getEmail());

            byte[] imageByteArray = Base64.decode(user.getImage(), Base64.DEFAULT);
            Glide.with(context)
                    .load(imageByteArray)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .centerCrop()
                    .placeholder(R.drawable.default_user_gray)
                    .into(new BitmapImageViewTarget(holder.userImageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            holder.userImageView.setImageDrawable(circularBitmapDrawable);
                        }
                    });

        }
        @Override
        public int getItemCount() {
            return userBook.size();
        }
    }

}
