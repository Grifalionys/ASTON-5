package com.grifalion.contacts.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.grifalion.contacts.model.ListContacts;
import com.grifalion.contacts.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    public interface OnContactClickListener{
        void onContactClick(ListContacts contact, int position);
    }
    private List<ListContacts> listContacts;
    private LayoutInflater inflater;
    private OnContactClickListener onContactClickListener;

    public ContactAdapter(List<ListContacts> listContacts, Context context, OnContactClickListener onContactClickListener) {
        this.listContacts = listContacts;
        this.inflater = LayoutInflater.from(context);
        this.onContactClickListener = onContactClickListener;
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ListContacts listContact = listContacts.get(position);
        holder.tvFirstName.setText(listContact.getFirstName());
        holder.tvLastName.setText(listContact.getLastName());
        holder.tvNumbersPhone.setText(listContact.getNumbersPhone());
        holder.itemView.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onContactClickListener.onContactClick(listContact,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        final TextView tvFirstName;
        final TextView tvLastName;
        final TextView tvNumbersPhone;
        public ViewHolder(View view) {
            super(view);
            tvFirstName = view.findViewById(R.id.tvFirstName);
            tvLastName = view.findViewById(R.id.tvLastName);
            tvNumbersPhone = view.findViewById(R.id.tvPhone);
        }
    }
}