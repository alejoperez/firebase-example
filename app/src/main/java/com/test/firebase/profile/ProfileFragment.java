package com.test.firebase.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.test.firebase.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends Fragment implements IProfileView, OnCompleteListener<Void> {

    @Bind(R.id.profile_edit_text_name)
    EditText editTextName;

    @Bind(R.id.profile_edit_text_email)
    EditText editTextEmail;

    private FirebaseUser user;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        loadData();
        loadView();
        return view;
    }

    @Override
    public void loadData() {
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void loadView() {
        editTextName.setText(user.getDisplayName());
        editTextEmail.setText(user.getEmail());
    }

    @Override
    public String getName() {
        return editTextName.getText().toString();
    }

    @Override
    public String getEmail() {
        return editTextEmail.getText().toString();
    }

    @Override
    public boolean hasProfileChanged() {
        return !getName().equals(user.getDisplayName());
    }

    @OnClick(R.id.profile_update_button)
    public void upadateProfile() {
        if (hasProfileChanged()) {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(getName())
                    .build();

            user.updateProfile(profileUpdates).addOnCompleteListener(this);
        }
    }

    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if (task.isSuccessful()) {
            Toast.makeText(getContext(),R.string.profile_updated,Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.profile_logout_button)
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        getActivity().finish();
    }
}
