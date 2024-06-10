package com.varda.table.activity.auth.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.varda.table.R;
import com.varda.table.activity.main.MainActivity;
import com.varda.table.databinding.FragmentLoginBinding;
import com.varda.table.helper.auth.LoginHelper;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        binding.btnRegistration.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_auth);
            navController.navigate(R.id.navigation_registration);

        });

        binding.btnLogin.setOnClickListener(loginClick());

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private View.OnClickListener loginClick() {
        LoginHelper helper = new LoginHelper(requireContext());
        String errorMsg = "Պարտադիր լրացվող դաշտ";
        return view -> {
            String email = binding.textEmail.getText().toString();
            String pass = binding.textEmail.getText().toString();

            if (email.isEmpty()) {
                binding.textEmail.setError(errorMsg);
            } else if (pass.isEmpty()) {
                binding.textPass.setError(errorMsg);
            } else {
                if (helper.loginUser(email, pass)) {
                    binding.errorMsg.setVisibility(View.INVISIBLE);
                    Intent mainFlow = new Intent(getContext(), MainActivity.class);
                    mainFlow.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(mainFlow);
                } else {
                    binding.errorMsg.setVisibility(View.VISIBLE);
                }
            }
        };
    }
}