// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.ui.player.MDXControllerActivity;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import com.netflix.mediaclient.Log;
import android.content.Context;
import android.app.AlertDialog$Builder;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class MdxErrorHandler
{
    private static final String kbHelpUrl_16001 = "https://help.netflix.com/en/node/12407";
    private static final String kbHelpUrl_16003 = "https://help.netflix.com/en/node/13590";
    private final NetflixActivity activity;
    private DialogInterface$OnClickListener kblaunch_16001;
    private DialogInterface$OnClickListener kblaunch_16003;
    private final String tag;
    
    public MdxErrorHandler(final String tag, final NetflixActivity activity) {
        this.kblaunch_16001 = (DialogInterface$OnClickListener)new MdxErrorHandler$1(this);
        this.kblaunch_16003 = (DialogInterface$OnClickListener)new MdxErrorHandler$2(this);
        this.tag = tag;
        this.activity = activity;
    }
    
    private AlertDialog$Builder getDialogBuilder(final int n, final String s) {
        switch (n) {
            default: {
                return new AlertDialog$Builder((Context)this.activity, 2131558696).setMessage((CharSequence)this.getErrorMessage(n, s)).setPositiveButton(2131492988, (DialogInterface$OnClickListener)null);
            }
            case 100: {
                return new AlertDialog$Builder((Context)this.activity, 2131558696).setMessage((CharSequence)this.getErrorMessage(n, s)).setPositiveButton(2131492988, (DialogInterface$OnClickListener)null).setNegativeButton(2131493332, this.kblaunch_16001);
            }
            case 105: {
                return new AlertDialog$Builder((Context)this.activity, 2131558696).setMessage((CharSequence)this.getErrorMessage(n, s)).setPositiveButton(2131492988, (DialogInterface$OnClickListener)null).setNegativeButton(2131493332, this.kblaunch_16003);
            }
        }
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
                return this.activity.getString(2131493212);
            }
            case 104: {
                return this.activity.getString(2131493213);
            }
            case 105: {
                return this.activity.getString(2131493214);
            }
            case 200: {
                return this.activity.getString(2131493215);
            }
            case 106: {
                return String.format(this.activity.getString(2131493217), s);
            }
            case 201: {
                return this.activity.getString(2131493216);
            }
        }
    }
    
    private void handleError(final int n, final String s) {
        this.activity.displayDialog(this.getDialogBuilder(n, s));
    }
    
    private void sendToast(final int n, final String s) {
    }
    
    private boolean shouldShowErrorMessage() {
        return this.activity instanceof FragmentHostActivity || this.activity instanceof MDXControllerActivity;
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
                return;
            }
            Log.d(this.tag, "Not MDX related activity, do not show toast");
        }
        else {
            if (this.shouldShowErrorMessage()) {
                Log.d(this.tag, "Showing toast msg");
                this.sendToast(n, s);
                return;
            }
            Log.d(this.tag, "Not MDX related activity, do not show toast");
        }
    }
}
