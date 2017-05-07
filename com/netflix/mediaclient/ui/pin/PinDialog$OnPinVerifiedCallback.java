// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.pin;

import android.app.AlertDialog;
import android.text.method.MovementMethod;
import android.view.View$OnKeyListener;
import android.widget.TextView$OnEditorActionListener;
import android.view.ViewGroup;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.util.TypedValue;
import android.view.WindowManager$LayoutParams;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import android.content.Context;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import android.view.inputmethod.InputMethodManager;
import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.annotation.TargetApi;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class PinDialog$OnPinVerifiedCallback extends LoggingManagerCallback
{
    final /* synthetic */ PinDialog this$0;
    
    public PinDialog$OnPinVerifiedCallback(final PinDialog this$0) {
        this.this$0 = this$0;
        super("nf_pin");
    }
    
    @Override
    public void onPinVerified(final boolean b, final Status status) {
        super.onPinVerified(b, status);
        this.this$0.showProgress(false);
        if (Log.isLoggable("nf_pin", 3)) {
            Log.d("nf_pin", String.format("onPinVerified isPinValid:%b, statusCode:%d", b, status.getStatusCode().getValue()));
        }
        if (status.isSucces() && !b) {
            this.this$0.retryOnPinFailure();
            return;
        }
        this.this$0.dismissDialog();
        if (status.isSucces()) {
            PinVerifier.getInstance().registerPinVerifyEvent();
            PinDialog.notifyCallerPinVerified((NetflixActivity)this.this$0.getActivity(), this.this$0.mVault);
            return;
        }
        showConnectivityErrorDialog((NetflixActivity)this.this$0.getActivity(), status);
    }
}
