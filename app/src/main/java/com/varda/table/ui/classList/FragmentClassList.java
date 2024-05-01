package com.varda.table.ui.classList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.ActionBar;

import com.varda.table.R;
import com.varda.table.databinding.FragmentClassListBinding;
import com.varda.table.factory.ClassListViewModelFactory;
import com.varda.table.ui.classList.view.ClassListView;

import java.util.Arrays;
import java.util.List;

public class FragmentClassList extends Fragment {

    private FragmentClassListBinding binding;
    private ClassListViewModel classListViewModel;

    private List<String> dayNamesList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ClassListViewModelFactory factory = new ClassListViewModelFactory(requireActivity().getApplication());
        classListViewModel = new ViewModelProvider(this, factory).get(ClassListViewModel.class);

        binding = FragmentClassListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        dayNamesList = Arrays.asList("Երկ", "Երք", "Չրք", "Հնգ", "Ուրբ", "Շբթ");

        classListViewModel.getClassList().observe(getViewLifecycleOwner(), item -> {
            for (int i = 0; i < item.size(); i++) {
                List<String> items = item.get(i);
                ClassListView viewItem = new ClassListView(getContext());
                viewItem.setViewModel(classListViewModel);
                viewItem.setTimes(items);

                String dayName = getDayName(i);
                viewItem.setDayName(dayName);

                viewItem.setDayId(i);

                binding.classListLayout.addView(viewItem);
            }
        });

        setActionBarTitle();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private String getDayName(int dayId) {
        if (dayId >= 0 && dayId < dayNamesList.size()) {
            return dayNamesList.get(dayId);
        } else {
            return "Error";
        }
    }

    private void setActionBarTitle(){
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.title_class_list);
        }
    }
}