package com.haksoy.veloxitydemo.ui;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haksoy.veloxitydemo.R;
import com.haksoy.veloxitydemo.databinding.FragmentUserDetailBinding;
import com.haksoy.veloxitydemo.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailFragment extends Fragment {

    User currentUser;

    public UserDetailFragment() {
        // Required empty public constructor
    }

    public UserDetailFragment(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentUserDetailBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_user_detail, container, false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        binding.setUserDetail(currentUser);
        return view;
    }

}
