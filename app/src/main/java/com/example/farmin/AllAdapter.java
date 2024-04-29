package com.example.farmin;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AllAdapter extends RecyclerView.Adapter<AllAdapter.viewHolder> {
    private List<addingUploads> uploads;
    private Context context;
    private int depends;
    private clickInterface clickInterface;

    public AllAdapter(Context context, int depends, List<addingUploads> uploads, clickInterface clickInterface) {
        this.uploads = uploads;
        this.context = context;
        this.depends = depends;
        this.clickInterface = clickInterface;
    }

    @NonNull
    @Override
    public AllAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.all_item, parent, false);
        return new viewHolder(v , clickInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull AllAdapter.viewHolder holder, int position) {
        addingUploads currentUploads = uploads.get(position);
        holder.tvName.setText(currentUploads.getName());
        holder.tvType.setText("Type: "+currentUploads.getType());
        holder.tvDescrip.setText("Description: "+currentUploads.getDescrip());
        holder.tvNote.setText("Notes: "+currentUploads.getNotes());
        String imageUrl = currentUploads.getImageurl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .fitCenter()
                    .into(holder.ivImage);
        } else {
            Toast.makeText(context, "Image URL is empty", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        Log.d("size", String.valueOf(uploads.size()));
        return uploads.size();

    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView tvNote, tvDescrip, tvType, tvName;
        private ImageView ivImage;
        public viewHolder(@NonNull View itemView, clickInterface clickInterface) {
            super(itemView);
            tvNote = itemView.findViewById(R.id.tvNote);
            tvDescrip = itemView.findViewById(R.id.tvDescrip);
            tvType = itemView.findViewById(R.id.tvType);
            tvName = itemView.findViewById(R.id.tvName);
            ivImage = itemView.findViewById(R.id.ivImage);
            itemView.setOnClickListener(view -> {
                int pos = getAdapterPosition();
                if(depends == 1) {
                    addingUploads addingUploads = uploads.get(pos);
                    Intent intent = new Intent(context, AddingActivity.class);
                    intent.putExtra("Name",addingUploads.getName());
                    intent.putExtra("Descrip",addingUploads.getDescrip());
                    intent.putExtra("Type",addingUploads.getType());
                    intent.putExtra("Notes",addingUploads.getNotes());
                    intent.putExtra("Image",addingUploads.getImageurl());
                    intent.putExtra("depends", 2);
                    context.startActivity(intent);
                } else if (depends == 2) {
                    addingUploads addingUploads = uploads.get(pos);
                    Intent intent = new Intent(context, displaying.class);
                    intent.putExtra("Name",addingUploads.getName());
                    intent.putExtra("Descrip",addingUploads.getDescrip());
                    intent.putExtra("Type",addingUploads.getType());
                    intent.putExtra("Notes",addingUploads.getNotes());
                    intent.putExtra("Image",addingUploads.getImageurl());
                    intent.putExtra("qrCode", addingUploads.getQrcode());
                    context.startActivity(intent);
                }
            });
        }
    }
}
