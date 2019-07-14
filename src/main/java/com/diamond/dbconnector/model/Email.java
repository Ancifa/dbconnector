package com.diamond.dbconnector.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class Email {
    private int id;
    private int listingId;
    private String email;
    private int isSent;
    private Date sendDate;
    private String result;

    @Override
    public String toString() {
        return "id = " + id
                + "listingId = " + listingId
                + "email = " + email
                + "isSent = " + isSent
                + "sendDate = " + sendDate
                + "result = " + result;
    }
}
