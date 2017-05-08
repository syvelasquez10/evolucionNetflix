// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.lomo;

import com.netflix.mediaclient.util.CWTestUtil;
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
    protected KubrickHighDensityCwView createChildView(final Context context) {
        if (CWTestUtil.isInTest(context)) {
            return new KubrickKidsCwTestView(context);
        }
        return new KubrickKidsCwView(context);
    }
}
