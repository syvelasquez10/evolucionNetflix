// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lolomo;

import com.netflix.mediaclient.util.ViewUtils;
import android.widget.TextView;
import android.view.View;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.ui.lolomo.LoLoMoAdapter;

public class KubrickHighDensityLoLoMoAdapter extends LoLoMoAdapter
{
    public KubrickHighDensityLoLoMoAdapter(final LoLoMoFrag loLoMoFrag) {
        super(loLoMoFrag);
    }
    
    @Override
    protected TextView initTitleView(final View view) {
        final TextView initTitleView = super.initTitleView(view);
        ViewUtils.setTextViewSizeByRes(initTitleView, 2131362304);
        return initTitleView;
    }
}
