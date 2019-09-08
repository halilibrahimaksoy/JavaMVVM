package com.haksoy.veloxitydemo.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.haksoy.veloxitydemo.model.User;
import com.haksoy.veloxitydemo.model.UserRepository;

import java.util.List;

public class UserListViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public UserListViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository();
    }

    public LiveData<List<User>> getAllUser() {
        return userRepository.getMutableLiveData();
    }
    public void loadMoreUser(){
        userRepository.addMutableLiveData();
    }
}
