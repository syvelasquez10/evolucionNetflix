// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.details.KubrickVideoDetailsViewGroup;

class KubrickKidsMovieDetailsFrag$KubrickKidsMovieDetailsViewGroup extends KubrickVideoDetailsViewGroup
{
    final /* synthetic */ KubrickKidsMovieDetailsFrag this$0;
    
    public KubrickKidsMovieDetailsFrag$KubrickKidsMovieDetailsViewGroup(final KubrickKidsMovieDetailsFrag this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
    }
    
    @Override
    protected int calculateImageHeight() {
        return (int)(KidsUtils.getDetailsPageContentWidth(this.getContext()) * 0.5625f);
    }
    
    @Override
    protected int getlayoutId() {
        return 2130903130;
    }
}
