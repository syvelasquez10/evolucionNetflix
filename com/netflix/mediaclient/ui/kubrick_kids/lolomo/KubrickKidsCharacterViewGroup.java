// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.lolomo;

import com.netflix.mediaclient.ui.lomo.LoMoUtils$LoMoWidthType;
import android.view.View;
import android.content.Context;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;

@SuppressLint({ "ViewConstructor" })
public class KubrickKidsCharacterViewGroup extends VideoViewGroup<Video, KubrickKidsCharacterView>
{
    public KubrickKidsCharacterViewGroup(final Context context) {
        super(context, true);
    }
    
    @Override
    protected KubrickKidsCharacterView createChildView(final Context context) {
        return new KubrickKidsCharacterView(context);
    }
    
    @Override
    protected LoMoUtils$LoMoWidthType getLomoWidthType() {
        return LoMoUtils$LoMoWidthType.KUBRICK_KIDS_CHARACTER_ROW;
    }
    
    @Override
    protected boolean shouldApplyPaddingToChildren() {
        return false;
    }
    
    @Override
    protected void updateViewIds(final KubrickKidsCharacterView kubrickKidsCharacterView, final int n, final int n2, final int n3) {
    }
}
