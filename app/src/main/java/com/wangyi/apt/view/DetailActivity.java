package com.wangyi.apt.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.OnClick;
import com.wangyi.apt.R;
import com.wangyi.viewbinder.ViewBinder;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ViewBinder.Bind(this);
    }

    @OnClick(R.id.btnBack)
    public void Back(){
        Toast.makeText(this, "finish", Toast.LENGTH_SHORT).show();
        finish();
    }
}
