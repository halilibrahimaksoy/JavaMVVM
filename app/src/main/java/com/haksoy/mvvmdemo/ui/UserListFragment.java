package com.haksoy.mvvmdemo.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haksoy.mvvmdemo.R;
import com.haksoy.mvvmdemo.adapter.UserAdapter;
import com.haksoy.mvvmdemo.model.User;
import com.haksoy.mvvmdemo.viewModel.UserListViewModel;

import java.util.ArrayList;
import java.util.List;


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
