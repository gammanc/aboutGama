package com.gamma.about;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCircular();
        setLinks();
    }

    private void setLinks(){
        TextView txtinsta = (TextView) findViewById(R.id.instagram);
        TextView txtgit = (TextView) findViewById(R.id.github);
        TextView txtmail = (TextView) findViewById(R.id.mail);
        TextView txtphone = (TextView) findViewById(R.id.phone);
        txtinsta.setMovementMethod(LinkMovementMethod.getInstance());
        txtgit.setMovementMethod(LinkMovementMethod.getInstance());
        txtmail.setMovementMethod(LinkMovementMethod.getInstance());
        txtphone.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setCircular(){
        ImageView img = (ImageView) findViewById(R.id.profile);
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.profile);
        RoundedBitmapDrawable r = RoundedBitmapDrawableFactory.create(getResources(), b);
        r.setCircular(true);
        img.setImageDrawable(r);
    }


}
