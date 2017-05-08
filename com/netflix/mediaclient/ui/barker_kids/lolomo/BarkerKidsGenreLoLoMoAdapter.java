// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker_kids.lolomo;

import android.widget.TextView;
import android.view.View;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.ui.lolomo.GenreLoLoMoAdapter;

public class BarkerKidsGenreLoLoMoAdapter extends GenreLoLoMoAdapter
{
    public BarkerKidsGenreLoLoMoAdapter(final LoLoMoFrag loLoMoFrag, final String s, final boolean b) {
        super(loLoMoFrag, s);
    }
    
    protected TextView initTitleView(final View view) {
        final TextView initTitleView = super.initTitleView(view);
        BarkerKidsLoLoMoAdapter.updateTitleStyle(initTitleView);
        return initTitleView;
    }
}
