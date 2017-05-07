// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.ui.player.MDXControllerActivity;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import com.netflix.mediaclient.javabridge.ui.LogArguments;
import com.netflix.mediaclient.javabridge.ui.LogArguments$LogLevel;
import com.netflix.mediaclient.Log;
import android.content.Context;
import android.app.AlertDialog$Builder;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class MdxErrorHandler
{
    private static final String TYPE_MDX = "mdx";
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
                return new AlertDialog$Builder((Context)this.activity, 2131558720).setMessage((CharSequence)this.getErrorMessage(n, s)).setPositiveButton(2131493003, (DialogInterface$OnClickListener)null);
            }
            case 100: {
                return new AlertDialog$Builder((Context)this.activity, 2131558720).setMessage((CharSequence)this.getErrorMessage(n, s)).setPositiveButton(2131493003, (DialogInterface$OnClickListener)null).setNegativeButton(2131493354, this.kblaunch_16001);
            }
            case 105: {
                return new AlertDialog$Builder((Context)this.activity, 2131558720).setMessage((CharSequence)this.getErrorMessage(n, s)).setPositiveButton(2131493003, (DialogInterface$OnClickListener)null).setNegativeButton(2131493354, this.kblaunch_16003);
            }
        }
    }
    
    private String getErrorMessage(final int n, final String s) {
        switch (n) {
            default: {
                if (Log.isLoggable()) {
                    Log.w(this.tag, "We do not have official error message for error code " + n);
                    return s;
                }
                return s;
            }
            case 300: {
                return s;
            }
            case 100: {
                return this.activity.getString(2131493229);
            }
            case 104: {
                return this.activity.getString(2131493230);
            }
            case 105: {
                return this.activity.getString(2131493231);
            }
            case 200: {
                return this.activity.getString(2131493232);
            }
            case 106: {
                return String.format(this.activity.getString(2131493234), s);
            }
            case 201: {
                return this.activity.getString(2131493233);
            }
        }
    }
    
    private void handleError(final int n, final String s) {
        this.activity.displayDialog(this.getDialogBuilder(n, s));
        this.reportErrorAsLogblob(n);
    }
    
    private void reportErrorAsLogblob(final int n) {
        while (true) {
            String s = null;
            switch (n) {
                default: {
                    return;
                }
                case 100: {
                    s = "16001";
                    break;
                }
                case 104: {
                    s = "16002";
                    break;
                }
                case 105: {
                    s = "16003";
                    break;
                }
                case 106: {
                    s = "16004";
                    break;
                }
            }
            try {
                this.activity.getServiceManager().getClientLogging().NrdpLog(new LogArguments(LogArguments$LogLevel.ERROR.getLevelInString(), s, "mdx", null));
                return;
            }
            catch (Exception ex) {
                Log.e(this.tag, "Unable to log error" + ex);
                return;
            }
            continue;
        }
    }
    
    private void sendToast(final int n, final String s) {
    }
    
    private boolean shouldShowErrorMessage() {
        return this.activity instanceof FragmentHostActivity || this.activity instanceof MDXControllerActivity;
    }
    
    public void handleMdxError(final int n, final String s) {
        if (Log.isLoggable()) {
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
