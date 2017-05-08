// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.verifyplay;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.View;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup;
import android.app.AlertDialog$Builder;
import android.app.Dialog;
import android.widget.Button;
import android.util.TypedValue;
import android.view.WindowManager$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ProgressBar;
import android.app.AlertDialog;
import android.widget.TextView;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
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
        new AgeDialog$AgeDialogOnVerify$1(this, this.this$0.getNetflixActivity()).run();
        this.this$0.dismissDialog();
        this.this$0.notifyCallerAgeCancelled();
    }
}
