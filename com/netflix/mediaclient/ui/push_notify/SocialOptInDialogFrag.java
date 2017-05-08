// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.push_notify;

import android.app.Activity;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.content.Context;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.Log;
import android.content.DialogInterface;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class SocialOptInDialogFrag extends NetflixDialogFrag
{
    private static final String TAG = "social";
    HomeActivity handler;
    private final AtomicBoolean mClicked;
    
    public SocialOptInDialogFrag() {
        this.mClicked = new AtomicBoolean(false);
    }
    
    public static SocialOptInDialogFrag newInstance() {
        return new SocialOptInDialogFrag();
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        Log.d("social", "User cancelled!");
        UIViewLogUtils.reportUIViewCommand((Context)this.handler, UIViewLogging$UIViewCommandName.optInButton, IClientLogging$ModalView.optInDialog, this.handler.getDataContext(), "cancel");
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        final Activity activity = this.getActivity();
        if (!(activity instanceof HomeActivity)) {
            Log.e("social", "Activity is not OptInResponseHandler! This should not happen!");
            return null;
        }
        this.handler = (HomeActivity)activity;
        return (Dialog)new AlertDialog$Builder((Context)this.getActivity(), 2131427451).setTitle(2131231196).setMessage(2131231187).setPositiveButton(2131231193, (DialogInterface$OnClickListener)new SocialOptInDialogFrag$2(this)).setNegativeButton(2131231190, (DialogInterface$OnClickListener)new SocialOptInDialogFrag$1(this)).create();
    }
}
