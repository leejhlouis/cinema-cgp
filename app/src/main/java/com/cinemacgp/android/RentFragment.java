package com.cinemacgp.android;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cinemacgp.models.Rent;
import com.google.android.material.tabs.TabLayout;

public class RentFragment extends Fragment {
    private EditText nameEditText;
    private EditText phoneEditText;
    private Spinner cinemaSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rent, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameEditText = getView().findViewById(R.id.inp_name);
        phoneEditText = getView().findViewById(R.id.inp_phone);
        cinemaSpinner = (Spinner) getView().findViewById(R.id.inp_cinema);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.cinema_branches, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cinemaSpinner.setAdapter(adapter);

        Button btn = getView().findViewById(R.id.btn_submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameEditText.getText().toString().isEmpty() ||
                        phoneEditText.getText().toString().isEmpty() ||
                        cinemaSpinner.getSelectedItem().toString().isEmpty()
                ){
                    Toast.makeText(getActivity(), "Invalid input. Please try again!", Toast.LENGTH_SHORT).show();
                } else {
                    ((MainActivity) getActivity()).setFragment(new RentSuccessFragment());
                }
            }
        });
    }
}