// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.model.leafs.OnRampEligibility;

class UserAgent$DoOnRampEligibilityAction$1$1 implements Runnable
{
    final /* synthetic */ UserAgent$DoOnRampEligibilityAction$1 this$2;
    final /* synthetic */ OnRampEligibility val$eligibility;
    final /* synthetic */ Status val$res;
    
    UserAgent$DoOnRampEligibilityAction$1$1(final UserAgent$DoOnRampEligibilityAction$1 this$2, final OnRampEligibility val$eligibility, final Status val$res) {
        this.this$2 = this$2;
        this.val$eligibility = val$eligibility;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.getCallback().onOnRampEligibilityActionComplete(this.val$eligibility, this.val$res);
    }
}
