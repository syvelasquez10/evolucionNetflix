// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.ui.player.MDXControllerActivity;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import android.app.DialogFragment;
import com.netflix.mediaclient.ui.common.NetflixAlertDialog;
import android.content.Context;
import com.netflix.mediaclient.ui.common.NetflixAlertDialog$AlertDialogDescriptor;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class MdxErrorHandler
{
    private static final String ERROR_DIALOG_ID = "mdx_error_dialog_id";
    private final NetflixActivity activity;
    private final MdxErrorHandler$ErrorHandlerCallbacks callbacks;
    private final String tag;
    
    public MdxErrorHandler(final String tag, final NetflixActivity activity, final MdxErrorHandler$ErrorHandlerCallbacks callbacks) {
        this.tag = tag;
        this.activity = activity;
        this.callbacks = callbacks;
    }
    
    private String getErrorMessage(final int n, final String s) {
        switch (n) {
            default: {
                if (Log.isLoggable(this.tag, 5)) {
                    Log.w(this.tag, "We do not have official error message for error code " + n);
                    return s;
                }
                return s;
            }
            case 300: {
                return s;
            }
            case 100: {
                return this.activity.getString(2131493203);
            }
            case 104: {
                return this.activity.getString(2131493204);
            }
            case 105: {
                return this.activity.getString(2131493205);
            }
            case 200: {
                return this.activity.getString(2131493206);
            }
            case 106: {
                return String.format(this.activity.getString(2131493208), s);
            }
            case 201: {
                return this.activity.getString(2131493207);
            }
        }
    }
    
    private void handleError(final int n, final String s) {
        this.activity.showDialog(NetflixAlertDialog.newInstance(new NetflixAlertDialog$AlertDialogDescriptor((Context)this.activity, "mdx_error_dialog_id", this.getErrorMessage(n, s), false, false)));
    }
    
    private void sendToast(final int n, final String s) {
    }
    
    private boolean shouldShowErrorMessage() {
        return this.activity instanceof FragmentHostActivity || this.activity instanceof MDXControllerActivity;
    }
    
    public boolean handleDialogButton(final String s, final String s2) {
        if (Log.isLoggable(this.tag, 3)) {
            Log.d(this.tag, "User pressed button " + s2 + " for dialog " + s);
        }
        if ("mdx_error_dialog_id".equals(s)) {
            Log.d(this.tag, "User accepted error message. Exit activity");
            this.callbacks.destroy();
            return true;
        }
        return false;
    }
    
    public boolean handleDialogCancel(final String s) {
        if (Log.isLoggable(this.tag, 3)) {
            Log.d(this.tag, "User canceled error message for dialog " + s);
        }
        if ("mdx_error_dialog_id".equals(s)) {
            Log.d(this.tag, "User canceled error message. Exit activity");
            this.callbacks.destroy();
        }
        return false;
    }
    
    public void handleMdxError(final int n, final String s) {
        if (Log.isLoggable(this.tag, 3)) {
            Log.d(this.tag, "Error received. Code: " + n + ", message: " + s);
        }
        if (this.activity.destroyed()) {
            return;
        }
        if (n >= 100 && n < 200) {
            Log.d(this.tag, "Displaying error dialog");
            this.handleError(n, s);
            return;
        }
        if (n >= 200 && n < 300) {
            if (this.shouldShowErrorMessage()) {
                Log.d(this.tag, "Showing toast msg");
                this.sendToast(n, s);
            }
            else {
                Log.d(this.tag, "Not MDX related activity, do not show toast");
            }
            this.callbacks.destroy();
            return;
        }
        if (this.shouldShowErrorMessage()) {
            Log.d(this.tag, "Showing toast msg");
            this.sendToast(n, s);
            return;
        }
        Log.d(this.tag, "Not MDX related activity, do not show toast");
    }
}
