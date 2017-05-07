// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lolomo;

import com.netflix.mediaclient.ui.lolomo.BaseLoLoMoAdapter;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.servicemgr.interface_.genre.Genre;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import android.widget.AbsListView;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
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
    private final boolean showHeroImages;
    
    public KubrickGenreLoLoMoAdapter(final LoLoMoFrag loLoMoFrag, final String s, final boolean showHeroImages) {
        super(loLoMoFrag, s);
        this.showHeroImages = showHeroImages;
        this.heroTitleScroller = new KubrickLolomoUtils$HeroTitleScroller(loLoMoFrag);
    }
    
    @Override
    protected BaseLoLoMoAdapter$RowHolder createHolder(final View view, final LinearLayout linearLayout, final TextView textView, final BaseLoLoMoAdapter$LoMoRowContent baseLoLoMoAdapter$LoMoRowContent, final View view2) {
        return KubrickLolomoUtils.createHolder(this.activity, view, linearLayout, textView, baseLoLoMoAdapter$LoMoRowContent, view2);
    }
    
    @Override
    protected int getViewLayoutId() {
        return 2130903122;
    }
    
    @Override
    protected TextView initTitleView(final View view) {
        final TextView textViewToBold = (TextView)view.findViewById(2131427597);
        if (BrowseExperience.isKubrickKids()) {
            textViewToBold.setTextColor(this.activity.getResources().getColor(2131230864));
            textViewToBold.setTextSize(0, (float)this.activity.getResources().getDimensionPixelSize(2131296349));
            ViewUtils.setTextViewToBold(textViewToBold);
        }
        return textViewToBold;
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
        int n;
        if (this.showHeroImages) {
            n = 1;
        }
        else {
            n = 0;
        }
        super.updateLoMoData((List<Genre>)KubrickLolomoUtils.createDuplicateRows(this, list, n));
    }
    
    @Override
    protected void updateRowViews(final BaseLoLoMoAdapter$RowHolder baseLoLoMoAdapter$RowHolder, final Genre genre, final int n) {
        super.updateRowViews(baseLoLoMoAdapter$RowHolder, genre, n);
        KubrickLolomoUtils.updateRowViews(baseLoLoMoAdapter$RowHolder, genre, n);
    }
}
