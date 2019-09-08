package com.haksoy.veloxitydemo.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haksoy.veloxitydemo.R;
import com.haksoy.veloxitydemo.adapter.UserAdapter;
import com.haksoy.veloxitydemo.model.User;
import com.haksoy.veloxitydemo.viewModel.UserListViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;


public class UserListFragment extends Fragment {

    private UserListViewModel mViewModel;
    private UserAdapter userAdapter;

    private UserAdapter.OnItemClickListener onNotifyListener;
    private boolean isLoading;

    public UserListFragment(UserAdapter.OnItemClickListener onNotifyListener) {
        this.onNotifyListener = onNotifyListener;
    }

    public UserListFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_list_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.viewUsers);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        userAdapter = new UserAdapter(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User item) {
                if (onNotifyListener != null)
                    onNotifyListener.onItemClick(item);
            }
        });
        recyclerView.setAdapter(userAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();

                if (!isLoading) {
                    if ((visibleItemCount + firstVisibleItemPosition) >=
                            totalItemCount && firstVisibleItemPosition >= 0) {
                        loadMoreItems();
                    }
                }

            }
        });
        return view;
    }

    private void loadMoreItems() {
        isLoading = true;
        mViewModel.loadMoreUser();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UserListViewModel.class);
        getAllUser();
    }

    private void getAllUser() {
        mViewModel.getAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> userList) {
                userAdapter.setUserList((ArrayList<User>) userList);
                isLoading=false;
            }
        });
    }


}
