// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.verifyplay;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.android.app.Status;
import android.view.View;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup;
import android.app.AlertDialog$Builder;
import android.app.Dialog;
import android.widget.Button;
import android.util.TypedValue;
import android.view.WindowManager$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ProgressBar;
import android.app.AlertDialog;
import android.widget.TextView;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.net.Uri;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class AgeDialog$AgeDialogOnVerify implements DialogInterface$OnClickListener
{
    final /* synthetic */ AgeDialog this$0;
    
    private AgeDialog$AgeDialogOnVerify(final AgeDialog this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        Log.d("nf_age", "verifyButton clicked - going to webview");
        final Intent setData = new Intent("android.intent.action.VIEW").setData(Uri.parse("http://www.netflix.com/verifyage"));
        while (true) {
            try {
                this.this$0.startActivityForResult(setData, 0);
                this.this$0.dismissDialog();
                this.this$0.notifyCallerAgeCancelled();
            }
            catch (Exception ex) {
                Log.e("nf_age", "unable to launch age verification in browser", ex);
                this.this$0.getServiceManager().getClientLogging().getErrorLogging().logHandledException("unable to launch age verification in browser");
                continue;
            }
            break;
        }
    }
}
