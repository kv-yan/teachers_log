package com.varda.table.ui.about;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.varda.table.R;
import com.varda.table.databinding.FragmentAboutBinding;
import com.varda.table.utils.ClipboardUtil;

public class FragmentAbout extends Fragment {
    FragmentAboutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setActionBarTitle();

        binding = FragmentAboutBinding.inflate(getLayoutInflater());

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        String emailText = "Էլ հասցե՝ forclassnote@gmail.com\nՀարգանքով՝ClassNote";

        SpannableString emailSpannable = new SpannableString(emailText);

        int emailStartIndex = emailText.indexOf("forclassnote@gmail.com");
        int emailEndIndex = emailStartIndex + "forclassnote@gmail.com".length();

        emailSpannable.setSpan(new ForegroundColorSpan(Color.BLUE), emailStartIndex, emailEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        stringBuilder.append(emailSpannable);


        binding.emailText.setText(stringBuilder);

        binding.emailText.setOnLongClickListener(view -> {
            ClipboardUtil.copyToClipboard(getContext(), "forclassnote@gmail.com", "Էլ հասցեն պատճենվել է");
            return false;
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void setActionBarTitle() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.title_about);
        }
    }


}