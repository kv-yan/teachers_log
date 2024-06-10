package com.varda.table.helper.mail;

import android.content.Context;

import com.varda.table.mail.JavaMailAPI;

public class MailHelper {
    public static void send(Context context, String parentsEmail, String subject, String msgContent) {
        JavaMailAPI mailAPI = new JavaMailAPI(context, parentsEmail, subject, msgContent);
        mailAPI.execute();
    }
}
