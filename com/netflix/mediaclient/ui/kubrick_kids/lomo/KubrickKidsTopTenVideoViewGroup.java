// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.lomo;

import com.netflix.mediaclient.ui.lomo.LoMoUtils$LoMoWidthType;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickVideoView;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickLoMoViewGroup;

public class KubrickKidsTopTenVideoViewGroup extends KubrickLoMoViewGroup
{
    public KubrickKidsTopTenVideoViewGroup(final Context context) {
        super(context);
    }
    
    @Override
    protected KubrickVideoView createChildView(final Context context) {
        return new KubrickKidsTopTenVideoView(context);
    }
    
    @Override
    protected LoMoUtils$LoMoWidthType getLomoWidthType() {
        return LoMoUtils$LoMoWidthType.KUBRICK_KIDS_TOP_TEN_ROW;
    }
}
