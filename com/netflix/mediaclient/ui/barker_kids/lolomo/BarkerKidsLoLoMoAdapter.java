// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker_kids.lolomo;

import android.view.View;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.util.ViewUtils;
import android.widget.TextView;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.ui.lolomo.LoLoMoAdapter;

public class BarkerKidsLoLoMoAdapter extends LoLoMoAdapter
{
    public BarkerKidsLoLoMoAdapter(final LoLoMoFrag loLoMoFrag) {
        super(loLoMoFrag);
    }
    
    static void updateTitleStyle(final TextView textView) {
        ViewUtils.setTextViewColor(textView, 2131755149);
        ViewUtils.setTextViewSizeByRes(textView, 2131427422);
        ViewUtils.setTextViewToBold(textView);
        ViewUtils.removeShadow(textView);
        KidsUtils.setTextColorIfApplicable(textView);
    }
    
    protected TextView initTitleView(final View view) {
        final TextView initTitleView = super.initTitleView(view);
        updateTitleStyle(initTitleView);
        return initTitleView;
    }
}
