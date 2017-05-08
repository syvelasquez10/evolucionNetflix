// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.content.Context;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import android.view.View;
import android.view.View$OnClickListener;

class BarkerShowDetailsFrag$1 implements View$OnClickListener
{
    final /* synthetic */ BarkerShowDetailsFrag this$0;
    
    BarkerShowDetailsFrag$1(final BarkerShowDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        DetailsActivity.finishAllDetailsActivities((Context)this.this$0.getActivity());
    }
}
