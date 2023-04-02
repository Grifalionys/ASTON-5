package com.grifalion.contacts.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.grifalion.contacts.R;

public class DetailFragment extends Fragment {
    private static final String FNAME_KEY = "fNameKey";
    private static final String LNAME_KEY = "lNameKey";
    private static final String NUMBER_PHONE = "nPhone";

    private String fName;
    private String lName;
    private String nPhone;

    public static DetailFragment newInstance(String fName, String lName, String nPhone){
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FNAME_KEY, fName);
        bundle.putString(LNAME_KEY, lName);
        bundle.putString(NUMBER_PHONE, nPhone);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            fName = getArguments().getString(FNAME_KEY);
            lName = getArguments().getString(LNAME_KEY);
            nPhone = getArguments().getString(NUMBER_PHONE);
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
        editContact();
    }

    public void editContact(){
        Button btnEdit = getView().findViewById(R.id.btnEdit);
        TextView firstName = getView().findViewById(R.id.tvNameFirst);
        TextView lastName = getView().findViewById(R.id.tvNameLast);
        TextView numberPhone = getView().findViewById(R.id.tvPhoneNumber);
        firstName.setText(fName);
        lastName.setText(lName);
        numberPhone.setText(nPhone);
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

                edFirstName.setText(fName);
                edLastName.setText(lName);
                edPhoneNumber.setText(nPhone);


            }
        });
    }
}
