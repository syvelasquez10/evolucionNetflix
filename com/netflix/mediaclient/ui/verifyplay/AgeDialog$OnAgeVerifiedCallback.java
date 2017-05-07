// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.verifyplay;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.view.View;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup;
import android.app.AlertDialog$Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.util.TypedValue;
import android.view.WindowManager$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ProgressBar;
import android.app.AlertDialog;
import android.widget.TextView;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class AgeDialog$OnAgeVerifiedCallback extends LoggingManagerCallback
{
    final /* synthetic */ AgeDialog this$0;
    
    public AgeDialog$OnAgeVerifiedCallback(final AgeDialog this$0) {
        this.this$0 = this$0;
        super("nf_age");
    }
    
    @Override
    public void onVerified(final boolean b, final Status status) {
        super.onVerified(b, status);
        Log.d("nf_age", String.format("onVerified mVault:", this.this$0.mVault));
        if (!this.this$0.mActive) {
            Log.d("nf_age", "dialog was cancelled before.. nothing to do");
            return;
        }
        Log.d("nf_age", String.format("onAgeVerified statusCode:%d", status.getStatusCode().getValue()));
        if (status.isSucces() && b) {
            PlayVerifier.verifyPinToContinue((NetflixActivity)this.this$0.getActivity(), this.this$0.mVault);
            this.this$0.dismissDialog();
            return;
        }
        this.this$0.ageVerifyDone();
    }
}
