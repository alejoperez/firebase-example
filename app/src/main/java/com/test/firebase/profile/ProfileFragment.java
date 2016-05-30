package com.test.firebase.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.test.firebase.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends Fragment implements IProfileView {

    @Bind(R.id.profile_edit_text_name)
    EditText editTextName;

    @Bind(R.id.profile_edit_text_email)
    EditText editTextEmail;

    private IProfilePresenter profilePresenter;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        loadData();
        return view;
    }

    @Override
    public void loadData() {
        profilePresenter = new ProfilePresenter(this);
        profilePresenter.loadUserProfile();
    }

    @Override
    public void loadView(String name, String email) {
        editTextName.setText(name);
        editTextEmail.setText(email);
    }

    @Override
    public String getName() {
        return editTextName.getText().toString();
    }

    @OnClick(R.id.profile_update_button)
    public void upadateProfile() {
        if (profilePresenter.hasProfileChanged(getName())) {
            profilePresenter.updateProfile(getName());
        }
    }

    @Override
    public void onUpdateProfileSuccess() {
        Toast.makeText(getContext(), R.string.profile_updated_success,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateProfileFail() {
        Toast.makeText(getContext(), R.string.profile_updated_error,Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.profile_logout_button)
    public void logout() {
        profilePresenter.logout();
        getActivity().finish();
    }
}
