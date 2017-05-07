// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.view.View$OnSystemUiVisibilityChangeListener;
import android.content.Context;

class MovieDetailsFrag$MovieDetailVideoDetailsViewGroup extends VideoDetailsViewGroup
{
    final /* synthetic */ MovieDetailsFrag this$0;
    
    public MovieDetailsFrag$MovieDetailVideoDetailsViewGroup(final MovieDetailsFrag this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
        this.ratingBar.setOnSystemUiVisibilityChangeListener((View$OnSystemUiVisibilityChangeListener)new MovieDetailsFrag$MovieDetailVideoDetailsViewGroup$1(this, this$0));
    }
}
