package com.varda.table.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

public class ClipboardUtil {

    // Function to copy text to clipboard
    public static void copyToClipboard(Context context, String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        
        ClipData clip = ClipData.newPlainText("Copied Text", text);
        
        clipboard.setPrimaryClip(clip);

        Toast.makeText(context, "Տեքստը պատճենվել է", Toast.LENGTH_SHORT).show();
    }
}
