package com.grifalion.contacts.model;

public class ListContacts {

    private String firstName;
    private String lastName;
    private String numbersPhone;

    public ListContacts(String firstName, String lastName, String numbersPhone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.numbersPhone = numbersPhone;
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
