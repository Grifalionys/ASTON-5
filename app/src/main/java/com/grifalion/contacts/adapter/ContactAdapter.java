package com.grifalion.contacts.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.grifalion.contacts.model.Contact;
import com.grifalion.contacts.R;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private List<Contact> contacts = new ArrayList<>();
    private OnItemClickListener listener;

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Contact listContact = contacts.get(position);
        holder.tvFirstName.setText(listContact.getFirstName());
        holder.tvLastName.setText(listContact.getLastName());
        holder.tvNumbersPhone.setText(listContact.getNumbersPhone());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setContacts(List<Contact> contacts){
        this.contacts = contacts;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvFirstName;
        private TextView tvLastName;
        private TextView tvNumbersPhone;
        public ViewHolder(View view) {
            super(view);
            tvFirstName = view.findViewById(R.id.tvFirstName);
            tvLastName = view.findViewById(R.id.tvLastName);
            tvNumbersPhone = view.findViewById(R.id.tvPhone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener !=null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(contacts.get(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(Contact contact);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}