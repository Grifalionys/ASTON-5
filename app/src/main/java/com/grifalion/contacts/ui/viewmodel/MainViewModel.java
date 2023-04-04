package com.grifalion.contacts.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grifalion.contacts.DownloadListenear;
import com.grifalion.contacts.model.Contact;
import com.grifalion.contacts.repository.ContactRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private ContactRepository repository;
    private LiveData<List<Contact>> allContacts;
    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactRepository(application);
        allContacts = repository.getAllContacts();
    }

    public void insert(Contact contact){
        repository.insert(contact);
    }

    public void update(int id, String firstName, String lastName, String numbersPhone){
        repository.update(id,firstName,lastName,numbersPhone);
    }
    public void getContactByID(int id, DownloadListenear downloadListenear){
        repository.getContactById(id,downloadListenear);
    }

    public LiveData<List<Contact>> getAllContacts(){
        return allContacts;
    }
}
