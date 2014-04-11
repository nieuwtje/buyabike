package com.is.buyabike.mail;

import java.io.Serializable;

public interface IMailManager extends Serializable{
    void sendMail(String mailFrom,String mailTo,String subject,String mailBody);
}
