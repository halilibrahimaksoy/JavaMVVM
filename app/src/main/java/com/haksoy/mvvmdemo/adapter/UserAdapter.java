package com.haksoy.mvvmdemo.adapter;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haksoy.mvvmdemo.R;
import com.haksoy.mvvmdemo.databinding.ItemUserAutocompleteBinding;
import com.haksoy.mvvmdemo.model.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserListViewHolder> {

    private ArrayList<User> users;
    private final OnItemClickListener listener;

    public UserAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemUserAutocompleteBinding userListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_user_autocomplete, viewGroup, false);
        return new UserListViewHolder(userListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder userListViewHolder, int i) {
        User currentStudent = users.get(i);
        userListViewHolder.userListItemBinding.setUser(currentStudent);
    }


    @Override
    public int getItemCount() {
        if (users != null) {
            return users.size();
        } else {
            return 0;
        }
    }

    public void setUserList(ArrayList<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    class UserListViewHolder extends RecyclerView.ViewHolder {

        private ItemUserAutocompleteBinding userListItemBinding;

        public UserListViewHolder(@NonNull final ItemUserAutocompleteBinding userListItemBinding) {
            super(userListItemBinding.getRoot());
            this.userListItemBinding = userListItemBinding;
            this.userListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(userListItemBinding.getUser());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(User item);
    }
}