// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.Log;
import android.view.KeyEvent;
import android.content.DialogInterface$OnCancelListener;
import android.content.Context;
import android.app.AlertDialog;

public class UpdateDialog extends AlertDialog
{
    private static final String TAG = "Update";
    
    public UpdateDialog(final Context context) {
        super(context);
    }
    
    public UpdateDialog(final Context context, final int n) {
        super(context, n);
    }
    
    public UpdateDialog(final Context context, final boolean b, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        super(context, b, dialogInterface$OnCancelListener);
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        Log.i("Update", "Key " + n);
        if (n == 84) {
            Log.i("Update", "Ignore search key");
            return true;
        }
        if (n == 4) {
            Log.i("Update", "Ignore back key");
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    public boolean onSearchRequested() {
        return false;
    }
}
