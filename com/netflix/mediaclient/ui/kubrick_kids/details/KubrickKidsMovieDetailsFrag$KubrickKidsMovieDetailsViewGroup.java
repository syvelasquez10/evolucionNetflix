// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.details.KubrickVideoDetailsViewGroup;

class KubrickKidsMovieDetailsFrag$KubrickKidsMovieDetailsViewGroup extends KubrickVideoDetailsViewGroup
{
    private static final float ROW_HEIGHT_LANDSCAPE_SCALE_FACTOR = 0.75f;
    private static final float ROW_HEIGHT_PORTRAIT_SCALE_FACTOR = 1.0f;
    final /* synthetic */ KubrickKidsMovieDetailsFrag this$0;
    
    public KubrickKidsMovieDetailsFrag$KubrickKidsMovieDetailsViewGroup(final KubrickKidsMovieDetailsFrag this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
    }
    
    @Override
    protected void alignHeroImage() {
        final ViewGroup$LayoutParams layoutParams = this.horzDispImg.getLayoutParams();
        final float n = layoutParams.height;
        float n2;
        if (DeviceUtils.isLandscape(this.getContext())) {
            n2 = 0.75f;
        }
        else {
            n2 = 1.0f;
        }
        layoutParams.height = (int)(n2 * n);
    }
    
    @Override
    protected int getlayoutId() {
        return 2130903109;
    }
}
