// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.model.leafs.OnRampEligibility;

class UserAgent$DoOnRampEligibilityAction$1 extends SimpleUserAgentWebCallback
{
    final /* synthetic */ UserAgent$DoOnRampEligibilityAction this$1;
    
    UserAgent$DoOnRampEligibilityAction$1(final UserAgent$DoOnRampEligibilityAction this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onOnRampEligibilityAction(final OnRampEligibility onRampEligibility, final Status status) {
        this.this$1.this$0.getMainHandler().post((Runnable)new UserAgent$DoOnRampEligibilityAction$1$1(this, onRampEligibility, status));
    }
}
