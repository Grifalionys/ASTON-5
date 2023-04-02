package com.grifalion.contacts.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.grifalion.contacts.R;
import com.grifalion.contacts.adapter.ContactAdapter;
import com.grifalion.contacts.model.ListContacts;
import java.util.ArrayList;

public class ListFragment extends Fragment {

    private ArrayList<ListContacts> listContacts = new ArrayList<ListContacts>();
    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private Boolean isReset = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(!isReset){
            listContacts();
            isReset = true;
        }
        recyclerView = view.findViewById(R.id.rcView);
        ContactAdapter.OnContactClickListener OnContactClickListener = new ContactAdapter.OnContactClickListener() {
            @Override
            public void onContactClick(ListContacts contact, int position) {
                DetailFragment detailFragment = DetailFragment.newInstance(
                        contact.getFirstName(),
                        contact.getLastName(),
                        contact.getNumbersPhone()
                );
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,detailFragment)
                        .addToBackStack("contacts")
                        .commit();
            }
        };
        adapter = new ContactAdapter(listContacts,requireActivity(),OnContactClickListener);
        recyclerView.setAdapter(adapter);
    }

    private void listContacts(){
        listContacts.add(new ListContacts("Олег","Газманов","375292358645"));
        listContacts.add(new ListContacts("Курт","Комбайн","375292358645"));
        listContacts.add(new ListContacts("Александр","Невский","375292358645"));
        listContacts.add(new ListContacts("Алексей","Панин","375292358645"));
    }
}
