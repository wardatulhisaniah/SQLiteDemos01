package id.ac.poliban.mi.atul.sqlitedemos01.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import id.ac.poliban.mi.atul.sqlitedemos01.DetailActivity;
import id.ac.poliban.mi.atul.sqlitedemos01.R;
import id.ac.poliban.mi.atul.sqlitedemos01.dao.impl.FriendDaoImplSQLite;
import id.ac.poliban.mi.atul.sqlitedemos01.domain.Friend;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {
    private List<Friend> data;
    private FriendDaoImplSQLite db;
    public FriendAdapter(List<Friend> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_friend, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        //populate item
        Friend friend = data.get(position);
        Glide.with(holder.itemView.getContext())
                .load(friend.getPhoto())
                .apply(new RequestOptions().override(65, 65))
                .into(holder.imgPhoto);
        holder.tvName.setText(friend.getName());
        holder.tvPhone.setText(friend.getPhone());
        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(holder.itemView.getContext())
                    .setTitle("Delete Confirmation")
                    .setMessage("Hapus Data Ini")
                    .setNegativeButton("NO", null)
                    .setPositiveButton("YES", (dialog, which) -> {
                        db = new FriendDaoImplSQLite(holder.itemView.getContext());
                        db.delete(friend.getId());
                        data.remove(position);
                        notifyDataSetChanged();
                        db.close();
                    }).show();
            return true;
        });
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("friend", data.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FriendViewHolder extends RecyclerView.ViewHolder{
            ImageView imgPhoto;
            TextView tvName, tvPhone;
private FriendViewHolder(@NonNull View itemView) {
                super(itemView);
                imgPhoto = itemView.findViewById(R.id.img_photo);
                tvName = itemView.findViewById(R.id.tv_name);
                tvPhone = itemView.findViewById(R.id.tv_phone);
            }
        }
    }
