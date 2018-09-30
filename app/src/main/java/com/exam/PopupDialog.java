package com.exam;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

public class PopupDialog extends AlertDialog implements DialogInterface.OnClickListener{

    public PopupDialog(@NonNull Context context, String msg) {
        super(context);
        setCancelable(true);
        setTitle("提示");
        setMessage(msg);
        setButton(BUTTON_POSITIVE, "確定", this);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dismiss();
    }
}
