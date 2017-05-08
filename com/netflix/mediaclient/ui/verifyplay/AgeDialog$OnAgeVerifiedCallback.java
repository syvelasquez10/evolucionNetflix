// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.verifyplay;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.View;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup;
import android.support.v7.app.AlertDialog$Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.util.TypedValue;
import android.view.WindowManager$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import android.os.Parcelable;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.support.v7.app.AlertDialog;
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
        Log.d("nf_age", String.format("onVerified mVault:%s", this.this$0.mVault));
        if (!this.this$0.mActive) {
            Log.d("nf_age", "dialog was cancelled before.. nothing to do");
            return;
        }
        Log.d("nf_age", String.format("onAgeVerified statusCode:%d", status.getStatusCode().getValue()));
        if (status.isSucces() && b) {
            this.this$0.dismissDialog();
            PinAndAgeVerifier.verifyPinToContinue((NetflixActivity)this.this$0.getActivity(), this.this$0.mVault, this.this$0.mCallback);
            return;
        }
        this.this$0.ageVerifyDone();
    }
}
