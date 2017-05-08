// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.lomo;

import com.netflix.mediaclient.ui.kubrick.lomo.KubrickHighDensityCwView;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickHighDensityCwViewGroup;

public class KubrickKidsCwViewGroup extends KubrickHighDensityCwViewGroup
{
    public KubrickKidsCwViewGroup(final Context context) {
        super(context);
    }
    
    @Override
    protected KubrickKidsCwView createChildView(final Context context) {
        return new KubrickKidsCwView(context);
    }
}
