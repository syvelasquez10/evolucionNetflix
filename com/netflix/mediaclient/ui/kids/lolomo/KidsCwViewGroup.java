// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Context;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.servicemgr.CWVideo;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;

@SuppressLint({ "ViewConstructor" })
public class KidsCwViewGroup<V extends View> extends VideoViewGroup<CWVideo, V>
{
    private final boolean shouldIncludePeekDimen;
    
    public KidsCwViewGroup(final Context context, final boolean shouldIncludePeekDimen) {
        super(context, shouldIncludePeekDimen);
        this.shouldIncludePeekDimen = shouldIncludePeekDimen;
    }
    
    @Override
    protected V createChildView(final Context context) {
        if (KidsUtils.shouldShowHorizontalImages((NetflixActivity)context)) {
            return (V)new KidsHorizontalCwView(context, this.shouldIncludePeekDimen);
        }
        return (V)new KidsOneToOneCwView(context, this.shouldIncludePeekDimen);
    }
    
    @Override
    protected boolean shouldApplyPaddingToChildren() {
        return true;
    }
    
    @Override
    protected void updateViewIds(final V v, final int n, final int n2, final int n3) {
    }
}
