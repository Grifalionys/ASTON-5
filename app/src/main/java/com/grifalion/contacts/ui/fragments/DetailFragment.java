package com.grifalion.contacts.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.grifalion.contacts.DownloadListenear;
import com.grifalion.contacts.R;
import com.grifalion.contacts.model.Contact;
import com.grifalion.contacts.ui.viewmodel.MainViewModel;

public class DetailFragment extends Fragment implements DownloadListenear {
    private static final String ID_KEY = "ID_KEY";
    private int id;
    TextView firstName;
    TextView lastName;
    TextView numberPhone;

    private MainViewModel model;

    public static DetailFragment newInstance(int id){
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ID_KEY,id);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            id = getArguments().getInt(ID_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        model.getContactByID(id,this);
        editContact();
    }

    public void editContact(){
        Button btnEdit = getView().findViewById(R.id.btnEdit);
        firstName = getView().findViewById(R.id.tvNameFirst);
        lastName = getView().findViewById(R.id.tvNameLast);
        numberPhone = getView().findViewById(R.id.tvPhoneNumber);
        EditText edFirstName = getView().findViewById(R.id.edFirstName);
        EditText edLastName = getView().findViewById(R.id.edLastName);
        EditText edPhoneNumber = getView().findViewById(R.id.edPhoneNumber);
        Button btnSave = getView().findViewById(R.id.btnSave);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstName.setVisibility(View.GONE);
                lastName.setVisibility(View.GONE);
                numberPhone.setVisibility(View.GONE);
                btnEdit.setVisibility(View.GONE);
                edFirstName.setVisibility(View.VISIBLE);
                edLastName.setVisibility(View.VISIBLE);
                edPhoneNumber.setVisibility(View.VISIBLE);
                btnSave.setVisibility(View.VISIBLE);
                edFirstName.setText(firstName.getText().toString());
                edLastName.setText(lastName.getText().toString());
                edPhoneNumber.setText(numberPhone.getText().toString());

            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameFirsts = edFirstName.getText().toString();
                String nameLasts = edLastName.getText().toString();
                String numberPhones = edPhoneNumber.getText().toString();
                model.update(id,nameFirsts,nameLasts,numberPhones);

                firstName.setText(nameFirsts);
                lastName.setText(nameLasts);
                numberPhone.setText(numberPhones);

                firstName.setVisibility(View.VISIBLE);
                lastName.setVisibility(View.VISIBLE);
                numberPhone.setVisibility(View.VISIBLE);
                btnEdit.setVisibility(View.VISIBLE);
                edFirstName.setVisibility(View.GONE);
                edLastName.setVisibility(View.GONE);
                edPhoneNumber.setVisibility(View.GONE);



            }
        });
    }

    @Override
    public void dataDownloaded(Contact contact) {
        firstName.setText(contact.getFirstName());
        lastName.setText(contact.getLastName());
        numberPhone.setText(contact.getNumbersPhone());
    }

    @Override
    public void dataDownloadFailed() {

    }


}
