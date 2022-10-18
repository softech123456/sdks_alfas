package com.softech.alfasdk.Fragments.accountOpening;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alfasdk.R;
import com.softech.alfasdk.Util.Util;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class State8Fragment extends Fragment {

    TextInputLayout tilQ1;
    TextInputLayout tilQ2;
    TextInputLayout tilQ3;

    private ImageView ivBack;
    private TextView tvTitle;
    private TextInputEditText etQ1;
    private TextInputEditText etQ2;
    private TextInputEditText etQ3;
    private Button btnNext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_state8, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);

        ivBack.setOnClickListener(view1 -> {
            requireActivity().onBackPressed();
        });

        btnNext.setOnClickListener(view1 -> {
            Util.performNavigation(requireActivity(), R.id.action_state1Fragment_to_state2Fragment);
        });

    }

    private void initViews(View view) {

        tilQ1 = view.findViewById(R.id.tilQ1);
        tilQ2 = view.findViewById(R.id.tilQ2);
        tilQ3 = view.findViewById(R.id.tilQ3);

        ivBack = view.findViewById(R.id.ivBack);
        tvTitle = view.findViewById(R.id.tvTitle);
        etQ1 = view.findViewById(R.id.etQ1);
        etQ2 = view.findViewById(R.id.etQ2);
        etQ3 = view.findViewById(R.id.etQ3);
        btnNext = view.findViewById(R.id.btnNext);

    }


}