package com.example.applicationanalylstic.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

//import com.google.firebase.firestore.auth.User;

import com.example.applicationanalylstic.Home;
import com.example.applicationanalylstic.Model.notes;
import com.example.applicationanalylstic.R;

import java.util.List;



public class adapterShowNote extends RecyclerView.Adapter<adapterShowNote.ViewHolder> {

    private List<notes> mData;
    private LayoutInflater mInflater;
    private ItemClickListener2 itemClickListener2;


    public adapterShowNote(Context context, List<notes> data , ItemClickListener2 onClick2) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
         this.itemClickListener2 = onClick2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

//        holder.note.setText(mData.get(position).getText());
        holder.header.setText(mData.get(position).getHeader());


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener2.onItemClick2(holder.getAdapterPosition(), mData.get(position).getId());

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        public TextView note;
        public TextView header;

        public ImageView delete;
        public CardView card;

        ViewHolder(View itemView) {
            super(itemView);
//            this.note = itemView.findViewById(R.id.note);
            this.header = itemView.findViewById(R.id.header);
             this.card = itemView.findViewById(R.id.card2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

    }

    notes getItem(int id) {
        return mData.get(id);
    }



    public interface ItemClickListener2{
        void onItemClick2(int position, String id);
    }
}