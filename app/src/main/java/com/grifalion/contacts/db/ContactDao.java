package com.grifalion.contacts.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.grifalion.contacts.model.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void insert(Contact contact);

    @Query("SELECT * FROM contact_table WHERE id =:id")
    Contact getContact(int id);

    @Query("UPDATE contact_table SET firstName=:firstName, lastname=:lastName, numbersPhone=:numbersPhone WHERE id=:id")
    void update(int id, String firstName, String lastName, String numbersPhone);

    @Query("SELECT * FROM contact_table")
    LiveData<List<Contact>> getAllNotes();
}
