package com.cinemacgp.android;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cinemacgp.models.Rent;

import org.w3c.dom.Text;

public class RentSuccessFragment extends Fragment {
    private TextView titleView;
    private TextView nameView;
    private TextView phoneView;
    private TextView cinemaView;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Rent");
        return inflater.inflate(R.layout.fragment_rent_success, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.titleView = getView().findViewById(R.id.title);
        this.nameView = getView().findViewById(R.id.name);
        this.phoneView = getView().findViewById(R.id.phone);
        this.cinemaView = getView().findViewById(R.id.studio);

        titleView.setText("Your reservation details");
        nameView.setText("Name\t\t: " + Rent.getInstance(null, null, null).getName());
        phoneView.setText("Phone\t\t: " + Rent.getInstance(null, null, null).getPhone());
        cinemaView.setText("Studio\t\t: " + Rent.getInstance(null, null, null).getCinema());

        this.button = getView().findViewById(R.id.btn_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Reservation cancelled!", Toast.LENGTH_SHORT).show();

                Rent.cancelRent();

                ((MainActivity) getActivity()).setFragment(new RentFragment());
            }
        });
    }
}