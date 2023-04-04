package com.grifalion.contacts;

import com.grifalion.contacts.model.Contact;

public interface DownloadListenear {
    public void dataDownloaded(Contact contact);
    public void dataDownloadFailed();
}
