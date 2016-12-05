package com.ch.wchhuangya.exampe_01;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ch.wchhuangya.exampe_01.databinding.ActivityBinding;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity);
        ActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.activity);
        binding.setText(new MainVM(this));

//        initUI();
//        fillData();
    }

    /*private void fillData() {
        mTextView.setText(getTextData());
    }

    private String getTextData() {
        return "这只是一个示例，你不用觉得可惜……";
    }*/

    /*private void initUI() {
        mTextView = (TextView) findViewById(R.id.main_tv);
    }*/
}
