// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.push_notify;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
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
import android.app.Activity;
import android.view.View;
import android.view.ViewTreeObserver$OnPreDrawListener;

class PushNotifOptInDialogFrag$1 implements ViewTreeObserver$OnPreDrawListener
{
    final /* synthetic */ PushNotifOptInDialogFrag this$0;
    final /* synthetic */ View val$view;
    
    PushNotifOptInDialogFrag$1(final PushNotifOptInDialogFrag this$0, final View val$view) {
        this.this$0 = this$0;
        this.val$view = val$view;
    }
    
    public boolean onPreDraw() {
        this.this$0.mContent.bind(this.val$view, this.this$0, this.this$0.mHandler);
        this.this$0.adjustModalWidthIfApplicable();
        this.val$view.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        return true;
    }
}
