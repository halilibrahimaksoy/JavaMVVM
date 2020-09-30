
package com.haksoy.mvvmdemo.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import com.haksoy.mvvmdemo.R;
import com.haksoy.mvvmdemo.adapter.UserAdapter;
import com.haksoy.mvvmdemo.model.User;


public class MainActivity extends AppCompatActivity {
    Fragment userListFragment;
    Fragment userDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (getIntent() != null) {
//            String userName = getIntent().getStringExtra("username");
//            Toast.makeText(getApplicationContext(), "Welcome " + userName, Toast.LENGTH_SHORT).show();
//        }
        userListFragment = new UserListFragment(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User item) {
                userSelected(item);
            }
        });
        setActiveFragment(userListFragment, false);
    }

    private void userSelected(User item) {
        userDetailFragment = new UserDetailFragment(item);
        setTitle(item.getUsername());
        setActiveFragment(userDetailFragment, true);

    }

    private void setActiveFragment(Fragment fragment, boolean isBackStack) {

        if (isBackStack) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frmMainContainer, fragment).addToBackStack(fragment.getTag()).commit();

        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.frmMainContainer, fragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setTitle(R.string.app_name);
    }
}
