package com.sample.pooja.sampleapplication.activity.ItemList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.pooja.sampleapplication.R;
import com.sample.pooja.sampleapplication.database.AppDatabase;
import com.sample.pooja.sampleapplication.database.ItemsDao;
import com.sample.pooja.sampleapplication.database.ItemsListsEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemListActivity extends AppCompatActivity {

    @BindView(R.id.rv_items)
    RecyclerView rvItemRecycler;
    RecycleAdapter recycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvItemRecycler.setLayoutManager(linearLayoutManager);
        rvItemRecycler.setHasFixedSize(true);
        ItemsDao messageDao = AppDatabase.getInstance(getApplicationContext()).getItems();
        //Using live data to fetch the items from database
        messageDao.getAllMessage().observe(this, (List<ItemsListsEntity> message) -> {
            recycleAdapter = new RecycleAdapter(ItemListActivity.this, message);
            rvItemRecycler.setAdapter(recycleAdapter);
        });

    }

    //Adapter to render the data
    public class RecycleAdapter extends RecyclerView.Adapter<RoomViewHolder> {
        private List<ItemsListsEntity> messageList;
        private Context mContext;

        public RecycleAdapter(Context context, List<ItemsListsEntity> messageList) {
            this.messageList = messageList;
            this.mContext = context;
        }

        @Override
        public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_layout, parent, false);
            return new RoomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RoomViewHolder holder, int position) {
            ItemsListsEntity message = messageList.get(position);
            holder.email.setText(message.getEmail());
            holder.name.setText(message.getFname() + " " +message.getLname());
            Picasso.get()
                    .load(message.getImageUrl())
                    .placeholder(R.drawable.ic_default_user)
                    .error(R.drawable.ic_default_user)
                    .into(holder.ivItem);
        }

        @Override
        public int getItemCount() {
            return messageList.size();
        }
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder {
        public TextView email,name;
        public ImageView ivItem;

        public RoomViewHolder(View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.tvEmail);
            name = itemView.findViewById(R.id.tvfname);
            ivItem = itemView.findViewById(R.id.iv_url);
        }
    }
}
