// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lolomo;

import com.netflix.mediaclient.ui.lolomo.BasePaginatedLoLoMoAdapter;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import android.widget.AbsListView;
import com.netflix.mediaclient.ui.lolomo.BaseLoLoMoAdapter$RowHolder;
import com.netflix.mediaclient.ui.lolomo.BaseLoLoMoAdapter$LoMoRowContent;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.View;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.ui.lolomo.GenreLoLoMoAdapter;

public class KubrickGenreLoLoMoAdapter extends GenreLoLoMoAdapter
{
    private static final int NUM_DUPLICATED_ROWS = 1;
    private final KubrickLolomoUtils$HeroTitleScroller heroTitleScroller;
    
    public KubrickGenreLoLoMoAdapter(final LoLoMoFrag loLoMoFrag, final String s) {
        super(loLoMoFrag, s);
        this.heroTitleScroller = new KubrickLolomoUtils$HeroTitleScroller(loLoMoFrag);
    }
    
    @Override
    protected BaseLoLoMoAdapter$RowHolder createHolder(final View view, final LinearLayout linearLayout, final TextView textView, final BaseLoLoMoAdapter$LoMoRowContent baseLoLoMoAdapter$LoMoRowContent, final View view2) {
        return KubrickLolomoUtils.createHolder(this.activity, view, linearLayout, textView, baseLoLoMoAdapter$LoMoRowContent, view2);
    }
    
    @Override
    protected int getViewLayoutId() {
        return 2130903119;
    }
    
    @Override
    public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
        this.heroTitleScroller.onScroll(absListView);
    }
    
    @Override
    protected boolean shouldShowRowTitle(final Genre genre) {
        return super.shouldShowRowTitle(genre) && !KubrickLolomoUtils.isDuplicateRow(genre);
    }
    
    @Override
    protected void updateLoMoData(final List<Genre> list) {
        super.updateLoMoData(KubrickLolomoUtils.createDuplicateRows(this, list, 1));
    }
    
    @Override
    protected void updateRowViews(final BaseLoLoMoAdapter$RowHolder baseLoLoMoAdapter$RowHolder, final Genre genre, final int n) {
        super.updateRowViews(baseLoLoMoAdapter$RowHolder, genre, n);
        KubrickLolomoUtils.updateRowViews(baseLoLoMoAdapter$RowHolder, genre, n);
    }
}
