package com.rsc.aaronjoseph.numberinsert;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class NumberFragment extends Fragment {

    private static TextView numberTextView;


    NumberListener activityCommander;

    public interface  NumberListener {
        public void AddNumber(String AddNumber);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            activityCommander = (NumberListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException((activity.toString()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.number_fragment, container);

        numberTextView = (TextView) view.findViewById(R.id.numberTextView);
        final Button button = (Button) view.findViewById(R.id.AddNumberButton);

        button.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(v);
                    }
                }
        );

        return view;
    }

    public void setNumberTextView(String AddNumber) {
        numberTextView.setText(AddNumber);
    }

    public void buttonClicked(View view) {
        activityCommander.AddNumber(numberTextView.getText().toString());
    }


}
