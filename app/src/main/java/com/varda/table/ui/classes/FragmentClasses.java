package com.varda.table.ui.classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.varda.table.adapter.ClassesAdapter;
import com.varda.table.databinding.FragmentClassesBinding;

import java.util.ArrayList;

public class FragmentClasses extends Fragment {

    private FragmentClassesBinding binding;
    private ClassesViewModel classesViewModel;
    private ClassesAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentClassesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        classesViewModel = new ViewModelProvider(this).get(ClassesViewModel.class);

        adapter = new ClassesAdapter(new ArrayList<>());
        binding.rvClasses.setAdapter(adapter);
        binding.rvClasses.setLayoutManager(new LinearLayoutManager(getContext()));

        classesViewModel.getClasses().observe(getViewLifecycleOwner(), classes -> {
            adapter.setClasses(classes);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
