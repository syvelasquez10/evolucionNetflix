// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.content.Context;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import android.view.View;
import android.view.View$OnClickListener;

class OldSlidingMenu$4 implements View$OnClickListener
{
    final /* synthetic */ OldSlidingMenu this$0;
    
    OldSlidingMenu$4(final OldSlidingMenu this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.activity.startActivity(ProfileSelectionActivity.createStartIntent((Context)this.this$0.activity));
    }
}
