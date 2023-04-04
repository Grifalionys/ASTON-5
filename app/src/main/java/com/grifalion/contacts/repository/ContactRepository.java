package com.grifalion.contacts.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.grifalion.contacts.DownloadListenear;
import com.grifalion.contacts.db.ContactDao;
import com.grifalion.contacts.db.ContactDatabase;
import com.grifalion.contacts.model.Contact;

import java.util.List;

public class ContactRepository {
    private ContactDao contactDao;
    private LiveData<List<Contact>> allContacts;

    public ContactRepository(Application application){
        ContactDatabase database = ContactDatabase.getInstance(application);
        contactDao = database.contactDao();
        allContacts = contactDao.getAllNotes();

    }
    public void insert(Contact contact){
        new InsertNoteAsyncTask(contactDao).execute(contact);
    }

    public void update(int id, String firstName, String lastName, String numbersPhone){
        new UpdateNoteAsyncTask(contactDao,id,firstName,lastName,numbersPhone).execute();
    }

    public LiveData<List<Contact>> getAllContacts(){
        return allContacts;
    }

    public void getContactById(int id,DownloadListenear downloadListenear){
        new GetContactByIdNoteAsyncTask(contactDao,downloadListenear).execute(id);
    }

    public class GetContactByIdNoteAsyncTask extends AsyncTask<Integer,Void,Contact>{
        private ContactDao contactDao;
        private DownloadListenear downloadListenear;

        private GetContactByIdNoteAsyncTask(ContactDao contactDao, DownloadListenear downloadListenear){
            this.contactDao = contactDao;
            this.downloadListenear = downloadListenear;
        }

        @Override
        protected Contact doInBackground(Integer... integers) {
            return contactDao.getContact(integers[0]);
        }

        @Override
        protected void onPostExecute(Contact contact) {
           if(contact == null){
               downloadListenear.dataDownloadFailed();
           } else {
               downloadListenear.dataDownloaded(contact);
           }
        }
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Contact,Void,Void>{
        private ContactDao contactDao;
        private InsertNoteAsyncTask(ContactDao contactDao){
            this.contactDao = contactDao;
        }
        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.insert(contacts[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Void,Void,Void>{
        private ContactDao contactDao;
        private int id;
        private String firstName;
        private String lastName;
        private String phoneNumbers;

        private UpdateNoteAsyncTask(ContactDao contactDao, int id, String firstName, String lastName, String numbersPhone){
            this.contactDao = contactDao;
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumbers = numbersPhone;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            contactDao.update(id,firstName,lastName,phoneNumbers);
            return null;
        }
    }
}


