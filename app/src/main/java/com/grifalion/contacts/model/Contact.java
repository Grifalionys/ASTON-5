package com.grifalion.contacts.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "contact_table")
public class Contact {

    @PrimaryKey(autoGenerate = false)
    private int id;

    private String firstName;
    private String lastName;
    private String numbersPhone;

    public Contact(int id,String firstName, String lastName, String numbersPhone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.numbersPhone = numbersPhone;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getNumbersPhone() {
        return numbersPhone;
    }

    public void setNumbersPhone(String numbersPhone) {
        this.numbersPhone = numbersPhone;
    }


}
