// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.content.Context;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import android.view.View;
import android.view.View$OnClickListener;

class KubrickMovieDetailsFrag$1 implements View$OnClickListener
{
    final /* synthetic */ KubrickMovieDetailsFrag this$0;
    
    KubrickMovieDetailsFrag$1(final KubrickMovieDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        DetailsActivity.finishAllDetailsActivities((Context)this.this$0.getActivity());
    }
}