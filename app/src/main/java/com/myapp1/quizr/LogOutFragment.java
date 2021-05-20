package com.myapp1.quizr;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class LogOutFragment extends Fragment {
    private boolean ok = false;

    private void setOk(boolean ok) {
        this.ok = ok;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.log_out_fragment, container, false);
        TextView result = view.findViewById(R.id.logOutResult);


        AuthUI.getInstance()
                .signOut(this.getContext())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // user is now signed out
                        setOk(true);
                        result.setText("You've been successfully logged out");
                    }
                });
        if(!ok){
            result.setText("Something went wrong");
        }
        return view;
    }
}
