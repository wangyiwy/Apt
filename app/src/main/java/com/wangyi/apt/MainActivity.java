package com.wangyi.apt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.BindView;
import com.example.OnClick;
import com.wangyi.apt.view.DetailActivity;
import com.wangyi.viewbinder.ViewBinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.image)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewBinder.Bind(this);
        imageView.setImageResource(R.mipmap.ic_launcher);
    }

    @OnClick(R.id.text)
    public void Test() {
        Toast.makeText(this, "onClick", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnMain)
    public void Hello() {
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, DetailActivity.class));
    }
}
