package com.softech.alfasdk.Util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Developed by Hasham.Tahir on 1/27/2016.
 */
public class Loading {

    ProgressDialog pd;

    public Loading(Context context, String msg) {
        pd = new ProgressDialog(context);
        pd.setMessage(msg);
        pd.setCancelable(false);
        pd.setIndeterminate(true);
    }

    public void show() {
        if(!pd.isShowing()){
            pd.show();
        }
    }

    public void dismiss() {
        if(pd.isShowing()){
            try {
                pd.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void cancel() {
        try {
            pd.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
