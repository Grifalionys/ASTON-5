package com.grifalion.contacts.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.grifalion.contacts.R;
import com.grifalion.contacts.adapter.ContactAdapter;
import com.grifalion.contacts.model.Contact;
import com.grifalion.contacts.ui.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {
    private MainViewModel mainViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.rcView);
        final ContactAdapter adapter = new ContactAdapter();
        recyclerView.setAdapter(adapter);

        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);

        mainViewModel.getAllContacts().observe(requireActivity(), new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                adapter.setContacts(contacts);
            }
        });

        adapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Contact contact) {
                DetailFragment detailFragment = DetailFragment.newInstance(
                        contact.getId()
                );
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                if(view.findViewById(R.id.fragmentTablet) != null){
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentTablet,detailFragment)
                            .addToBackStack("contacts")
                            .commit();
                } else {
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container,detailFragment)
                            .addToBackStack("contacts")
                            .commit();
                }
            }
        });
    }
}
