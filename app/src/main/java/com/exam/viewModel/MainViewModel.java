package com.exam.viewModel;

import android.content.Context;
import android.databinding.ObservableField;

import com.exam.PopupDialog;
import com.exam.callback.SubmitCallback;

public class MainViewModel{

    public ObservableField<String> column = new ObservableField<>();
    public ObservableField<String> row = new ObservableField<>();
    Context context;
    SubmitCallback callback;

    public MainViewModel(Context context, SubmitCallback callBack){
        this.context = context;
        this.callback = callBack;
        column.set("");
        row.set("");
    }

    public void onClickSubmit() {
        if (column.get().equals("") || column.get().equals("0") || row.get().equals("") || row.get().equals("0")){
            new PopupDialog(context, "請輸入正確行列值").show();
        }else{
            callback.onSubmit(Integer.valueOf(column.get()), Integer.valueOf(row.get()));
        }
    }
}
