// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.model.leafs.OnRampEligibility;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

final class OnRampActivity$1 extends SimpleManagerCallback
{
    final /* synthetic */ Activity val$activity;
    
    OnRampActivity$1(final Activity val$activity) {
        this.val$activity = val$activity;
    }
    
    @Override
    public void onOnRampEligibilityAction(final OnRampEligibility onRampEligibility, final Status status) {
        if (status != null && status.isSucces() && onRampEligibility != null && onRampEligibility.isEligible()) {
            this.val$activity.startActivity(new Intent((Context)this.val$activity, (Class)OnRampActivity.class));
        }
    }
}
