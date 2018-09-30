package com.exam;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.exam.callback.SubmitCallback;
import com.exam.constant.BundleKey;
import com.exam.databinding.ActivityMainBinding;
import com.exam.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_START = 000;
    public static final int RESULT_RESTART = 100;

    ActivityMainBinding binding;
    MainViewModel model;
    SubmitCallback callback = new SubmitCallback() {
        @Override
        public void onSubmit(int column, int row) {
            Intent intent = new Intent(MainActivity.this, GridActivity.class);
            intent.putExtra(BundleKey.COLUMN, column);
            intent.putExtra(BundleKey.ROW, row);
            startActivityForResult(intent, REQUEST_START);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        model = new MainViewModel(this, callback);
        binding.setModel(model);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_RESTART:
                model.column.set("");
                model.row.set("");
                break;
            default:
                break;
        }
    }
}
