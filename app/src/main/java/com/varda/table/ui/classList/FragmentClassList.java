package com.varda.table.ui.classList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.varda.table.R;
import com.varda.table.databinding.FragmentClassListBinding;
import com.varda.table.dialog.AddStartEndTimeDialogHelper;
import com.varda.table.factory.ClassListViewModelFactory;
import com.varda.table.helper.time.TimePrefHelper;
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

        initView();

        setActionBarTitle();
        setHasOptionsMenu(true);
        return root;
    }

    private void initView(){
        dayNamesList = Arrays.asList("Երկ", "Երք", "Չրք", "Հնգ", "Ուրբ", "Շբթ");
        List<String> startEndTime = TimePrefHelper.getTimeList(requireContext());

        classListViewModel.getClassList().observe(getViewLifecycleOwner(), item -> {
            binding.classListLayout.removeAllViews();
            for (int i = 0; i < item.size(); i++) {
                List<String> items = item.get(i);
                ClassListView viewItem = new ClassListView(getContext());
                viewItem.setViewModel(classListViewModel);
                viewItem.setTimes(items, startEndTime);

                String dayName = getDayName(i);
                viewItem.setDayName(dayName);

                viewItem.setDayId(i);

                binding.classListLayout.addView(viewItem);
            }
        });
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

    private void setActionBarTitle() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.title_class_list);
        }
    }

    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        // Inflate the menu for the fragment
        inflater.inflate(R.menu.class_list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_start_end_time) {
            AddStartEndTimeDialogHelper.showAddClassDialog(requireContext(), new AddStartEndTimeDialogHelper.DialogCallback() {
                @Override
                public void onSave(List<String> list) {
                    TimePrefHelper.setTimeList(requireContext(),list);
                    initView();
                }



                @Override
                public void onCancel() {
                }
            });
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}