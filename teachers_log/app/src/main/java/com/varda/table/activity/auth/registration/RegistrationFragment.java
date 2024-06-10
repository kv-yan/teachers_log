package com.varda.table.activity.auth.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.varda.table.activity.main.MainActivity;
import com.varda.table.databinding.FragmentRegistrationBinding;
import com.varda.table.helper.auth.LoginHelper;
import com.varda.table.helper.auth.RegistrationHelper;

public class RegistrationFragment extends Fragment {
    private FragmentRegistrationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false);

        binding.btnRegistration.setOnClickListener(registrationClick());

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private View.OnClickListener registrationClick() {
        RegistrationHelper helper = new RegistrationHelper(requireContext());

        String errorMsg = "Դաշտը չպետք է դատարկ լինի";
        return view -> {
            String email = binding.textEmail.getText().toString();
            String pass = binding.textEmail.getText().toString();

            if (email.isEmpty()) {
                binding.textEmail.setError(errorMsg);
            } else if (pass.isEmpty()) {
                binding.textPass.setError(errorMsg);
            } else {
                if (helper.registerUser(email, pass)) {
                    Intent mainFlow = new Intent(getContext(), MainActivity.class);
                    mainFlow.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(mainFlow);
                }
            }
        };
    }
}