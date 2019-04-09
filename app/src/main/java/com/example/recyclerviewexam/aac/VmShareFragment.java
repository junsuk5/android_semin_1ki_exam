package com.example.recyclerviewexam.aac;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.recyclerviewexam.R;
import com.example.recyclerviewexam.databinding.FragmentVmShareABinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class VmShareFragment extends Fragment {


    public VmShareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vm_share_a, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 액티비티가 주인인 ViewModel
        VmShareViewModel viewModel = ViewModelProviders.of(requireActivity()).get(VmShareViewModel.class);

        FragmentVmShareABinding binding = DataBindingUtil.bind(view);
        binding.setLifecycleOwner(requireActivity());

        binding.setViewModel(viewModel);


        // SeekBar 변경 이벤트
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewModel.progress.setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.button.setOnClickListener(v -> {
            viewModel.value = 50;
        });



    }
}
