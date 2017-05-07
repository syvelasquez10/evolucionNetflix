// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.lolomo;

import android.widget.TextView;
import android.view.View;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.ui.lolomo.GenreLoLoMoAdapter;

public class KubrickKidsGenreLoLoMoAdapter extends GenreLoLoMoAdapter
{
    public KubrickKidsGenreLoLoMoAdapter(final LoLoMoFrag loLoMoFrag, final String s, final boolean b) {
        super(loLoMoFrag, s);
    }
    
    @Override
    protected TextView initTitleView(final View view) {
        final TextView initTitleView = super.initTitleView(view);
        KubrickKidsLoLoMoAdapter.updateTitleStyle(initTitleView);
        return initTitleView;
    }
}
