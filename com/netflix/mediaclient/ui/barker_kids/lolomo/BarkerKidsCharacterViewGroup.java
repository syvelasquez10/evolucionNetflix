// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker_kids.lolomo;

import com.netflix.mediaclient.ui.lomo.LoMoUtils$LoMoWidthType;
import android.view.View;
import android.content.Context;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;

@SuppressLint({ "ViewConstructor" })
public class BarkerKidsCharacterViewGroup extends VideoViewGroup<Video, BarkerKidsCharacterView>
{
    public BarkerKidsCharacterViewGroup(final Context context) {
        super(context, true);
    }
    
    protected BarkerKidsCharacterView createChildView(final Context context) {
        return new BarkerKidsCharacterView(context);
    }
    
    protected LoMoUtils$LoMoWidthType getLomoWidthType() {
        return LoMoUtils$LoMoWidthType.KUBRICK_KIDS_CHARACTER_ROW;
    }
    
    protected boolean shouldApplyPaddingToChildren() {
        return false;
    }
    
    protected void updateViewIds(final BarkerKidsCharacterView barkerKidsCharacterView, final int n, final int n2, final int n3) {
    }
}
