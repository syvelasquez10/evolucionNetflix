// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lolomo;

import com.netflix.mediaclient.util.ViewUtils;
import android.widget.TextView;
import android.view.View;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.ui.lolomo.GenreLoLoMoAdapter;

public class KubrickHighDensityGenreLoLoMoAdapter extends GenreLoLoMoAdapter
{
    public KubrickHighDensityGenreLoLoMoAdapter(final LoLoMoFrag loLoMoFrag, final String s) {
        super(loLoMoFrag, s);
    }
    
    @Override
    protected TextView initTitleView(final View view) {
        final TextView initTitleView = super.initTitleView(view);
        ViewUtils.setTextViewSizeByRes(initTitleView, 2131296638);
        return initTitleView;
    }
}
