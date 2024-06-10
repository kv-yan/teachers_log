package com.varda.table.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

public class ClipboardUtil {

    public static void copyToClipboard(Context context, String text , String toastMsg) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        
        ClipData clip = ClipData.newPlainText("Copied Text", text);
        
        clipboard.setPrimaryClip(clip);

        Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show();
    }
}
