// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Context;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.servicemgr.Video;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;

@SuppressLint({ "ViewConstructor" })
public class KidsLoMoViewGroup<V extends View> extends VideoViewGroup<Video, V>
{
    private final boolean shouldIncludePeekDimen;
    
    public KidsLoMoViewGroup(final Context context, final boolean shouldIncludePeekDimen) {
        super(context, shouldIncludePeekDimen);
        this.shouldIncludePeekDimen = shouldIncludePeekDimen;
    }
    
    @Override
    protected V createChildView(final Context context) {
        if (KidsUtils.shouldShowHorizontalImages((NetflixActivity)context)) {
            return (V)new KidsHorizontalVideoView((NetflixActivity)context, this.shouldIncludePeekDimen);
        }
        return (V)new KidsOneToOneVideoView(context, this.shouldIncludePeekDimen);
    }
    
    @Override
    protected int getChildPaddingDimenResId() {
        if (KidsUtils.shouldShowHorizontalImages((NetflixActivity)this.getContext())) {
            return 2131361916;
        }
        return 2131361917;
    }
    
    @Override
    protected boolean shouldApplyPaddingToChildren() {
        return this.shouldIncludePeekDimen;
    }
    
    @Override
    protected void updateViewIds(final V v, final int n, final int n2, final int n3) {
    }
}
