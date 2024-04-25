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
import com.varda.table.dialog.AddNewClassDialogHelper;
import com.varda.table.factory.ClassesViewModelFactory;
import com.varda.table.model.Classes;

import java.util.Collections;

public class FragmentClasses extends Fragment {

    private FragmentClassesBinding binding;
    private ClassesViewModel classesViewModel;
    private ClassesAdapter classesAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentClassesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ClassesViewModelFactory factory = new ClassesViewModelFactory(requireActivity().getApplication());
        classesViewModel = new ViewModelProvider(this, factory).get(ClassesViewModel.class);
        classesAdapter = new ClassesAdapter(Collections.emptyList());

        classesViewModel.getAllClasses().observe(getViewLifecycleOwner(), classes -> {
            classesAdapter.setClasses(classes);
        });

        binding.addNewClass.setOnClickListener(v -> AddNewClassDialogHelper.showAddClassDialog(requireContext(), new AddNewClassDialogHelper.DialogCallback() {
            @Override
            public void onSave(String inputText) {
                Classes newClass = new Classes(inputText);
                newClass.setStudents("");
                long id = classesViewModel.addClass(newClass);

                classesViewModel.getAllClasses().observe(getViewLifecycleOwner(), classes -> {
                    classesAdapter.setClasses(classes);
                });
            }

            @Override
            public void onUpdate(String inputText) {
            }

            @Override
            public void onDelete(String inputText) {
            }

            @Override
            public void onCancel() {
            }
        }));

        binding.rvClasses.setAdapter(classesAdapter);
        binding.rvClasses.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}


