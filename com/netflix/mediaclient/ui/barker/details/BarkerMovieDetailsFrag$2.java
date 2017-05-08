// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker.details;

import android.content.Context;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import android.view.View;
import android.view.View$OnClickListener;

class BarkerMovieDetailsFrag$2 implements View$OnClickListener
{
    final /* synthetic */ BarkerMovieDetailsFrag this$0;
    
    BarkerMovieDetailsFrag$2(final BarkerMovieDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        DetailsActivity.finishAllDetailsActivities((Context)this.this$0.getActivity());
    }
}
