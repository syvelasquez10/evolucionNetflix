// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.push_notify;

import android.view.ViewTreeObserver$OnPreDrawListener;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.os.Bundle;
import android.content.DialogInterface;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class PushNotifOptInDialogFrag extends NetflixDialogFrag implements OptInResponseHandler
{
    private static final float DIALOG_SCREEN_WIDTH_RATIO = 0.85f;
    private static final String TAG = "PushNotifOptInDialogFrag";
    private PushOptInContentBinder mContent;
    private HomeActivity mHandler;
    
    private void adjustModalWidthIfApplicable() {
        if (!this.mContent.isTakeover()) {
            final int measuredWidth = this.mContent.getRoot().getMeasuredWidth();
            final int n = (int)(DeviceUtils.getScreenWidthInPixels((Context)this.mHandler) * 0.85f);
            if (n < measuredWidth) {
                this.getDialog().getWindow().setLayout(n, -2);
            }
        }
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    public void onAccept() {
        Log.d("PushNotifOptInDialogFrag", "User opted In!");
        this.mHandler.onAccept();
        this.dismiss();
        UIViewLogUtils.reportUIViewCommand((Context)this.mHandler, UIViewLogging$UIViewCommandName.optInButton, IClientLogging$ModalView.optInDialog, this.mHandler.getDataContext(), "yes");
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        Log.d("PushNotifOptInDialogFrag", "User cancelled!");
        this.mHandler.onDecline();
        UIViewLogUtils.reportUIViewCommand((Context)this.mHandler, UIViewLogging$UIViewCommandName.optInButton, IClientLogging$ModalView.optInDialog, this.mHandler.getDataContext(), "cancel");
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.mContent = new PushOptInContentBinder((Context)this.getActivity(), PersistentConfig.getPushNotificationOptIn((Context)this.getActivity()));
        if (this.mContent.isTakeover()) {
            this.setStyle(0, 16973840);
        }
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(this.mContent.getLayoutId(), viewGroup, false);
        this.mHandler = (HomeActivity)this.getActivity();
        this.getDialog().getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(0));
        final View viewById = this.getDialog().findViewById(this.getActivity().getResources().getIdentifier("android:id/titleDivider", (String)null, (String)null));
        if (viewById != null) {
            viewById.setBackgroundColor(this.getResources().getColor(2131558596));
        }
        inflate.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new PushNotifOptInDialogFrag$1(this, inflate));
        return inflate;
    }
    
    @Override
    public void onExit() {
        Log.d("PushNotifOptInDialogFrag", "User exited!");
        this.mHandler.onDecline();
        this.dismiss();
        UIViewLogUtils.reportUIViewCommand((Context)this.mHandler, UIViewLogging$UIViewCommandName.optInButton, IClientLogging$ModalView.optInDialog, this.mHandler.getDataContext(), "exit");
    }
    
    @Override
    public void onSkip() {
        Log.d("PushNotifOptInDialogFrag", "User opted out!");
        this.mHandler.onDecline();
        this.dismiss();
        UIViewLogUtils.reportUIViewCommand((Context)this.mHandler, UIViewLogging$UIViewCommandName.optInButton, IClientLogging$ModalView.optInDialog, this.mHandler.getDataContext(), "skip");
    }
}
