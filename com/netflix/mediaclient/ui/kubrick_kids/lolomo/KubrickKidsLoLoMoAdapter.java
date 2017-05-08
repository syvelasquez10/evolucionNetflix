// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.lolomo;

import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.widget.TextView;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.ui.lolomo.LoLoMoAdapter;

public class KubrickKidsLoLoMoAdapter extends LoLoMoAdapter
{
    public KubrickKidsLoLoMoAdapter(final LoLoMoFrag loLoMoFrag) {
        super(loLoMoFrag);
    }
    
    static void updateTitleStyle(final TextView textViewToBold) {
        ViewUtils.setTextViewColor(textViewToBold, 2131624053);
        ViewUtils.setTextViewSizeByRes(textViewToBold, 2131361882);
        ViewUtils.setTextViewToBold(textViewToBold);
        ViewUtils.removeShadow(textViewToBold);
    }
    
    @Override
    protected TextView initTitleView(final View view) {
        final TextView initTitleView = super.initTitleView(view);
        updateTitleStyle(initTitleView);
        return initTitleView;
    }
}
