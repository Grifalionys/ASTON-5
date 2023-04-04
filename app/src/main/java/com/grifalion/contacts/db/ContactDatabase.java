package com.grifalion.contacts.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.grifalion.contacts.model.Contact;

@Database(entities = {Contact.class},version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    private static ContactDatabase instance;

    public abstract ContactDao contactDao();

    public static synchronized ContactDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ContactDatabase.class,
                    "contacts_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static  RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private ContactDao contactDao;

        private PopulateDbAsyncTask(ContactDatabase db){
            contactDao = db.contactDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            contactDao.insert(new Contact(0,"Артем","Савченко","375292358632"));
            contactDao.insert(new Contact(1,"Евгений","Линник","375292321243"));
            contactDao.insert(new Contact(2,"Степа","Новик","375292112913"));
            contactDao.insert(new Contact(3,"Юра","Герасимов","375292112923"));
            return null;
        }
    }
}
