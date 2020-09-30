package com.haksoy.mvvmdemo.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.haksoy.mvvmdemo.model.User;
import com.haksoy.mvvmdemo.model.UserRepository;

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
