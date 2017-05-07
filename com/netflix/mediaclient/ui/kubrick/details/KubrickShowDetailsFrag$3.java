// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.app.Activity;
import android.view.View;
import android.view.View$OnClickListener;

class KubrickShowDetailsFrag$3 implements View$OnClickListener
{
    final /* synthetic */ KubrickShowDetailsFrag this$0;
    
    KubrickShowDetailsFrag$3(final KubrickShowDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        final Activity activity = this.this$0.getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }
}
