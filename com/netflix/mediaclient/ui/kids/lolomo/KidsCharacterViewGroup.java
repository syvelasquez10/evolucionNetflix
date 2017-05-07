// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import android.view.View;
import android.content.Context;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;

@SuppressLint({ "ViewConstructor" })
public class KidsCharacterViewGroup extends VideoViewGroup<Video, KidsCharacterView>
{
    private final boolean shouldIncludePeekDimen;
    
    public KidsCharacterViewGroup(final Context context, final boolean shouldIncludePeekDimen) {
        super(context, shouldIncludePeekDimen);
        this.shouldIncludePeekDimen = shouldIncludePeekDimen;
    }
    
    @Override
    protected KidsCharacterView createChildView(final Context context) {
        return new KidsCharacterView(context, this.shouldIncludePeekDimen);
    }
    
    @Override
    protected boolean shouldApplyPaddingToChildren() {
        return this.shouldIncludePeekDimen;
    }
    
    @Override
    protected void updateViewIds(final KidsCharacterView kidsCharacterView, final int n, final int n2, final int n3) {
    }
}
