package com.haksoy.mvvmdemo.model;

import androidx.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class User {
    private String gender;
    private Name name;
    private String email;
    private String phone;
    private String cell;
    private Picture picture;

    public String getGender() {
        return gender;
    }

    public Name getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCell() {
        return cell;
    }

    public Picture getPicture() {
        return picture;
    }

    @BindingAdapter({"avatar"})
    public static void loadImage(ImageView imageView, String imageURL) {
        Picasso.get().load(imageURL).into(imageView);
    }

    public String getAvatar() {
        return picture.getMedium();
    }

    public String getProfilimage() {
        return picture.getLarge();
    }


    public String getUsername() {
        return name.toString();
    }
}
