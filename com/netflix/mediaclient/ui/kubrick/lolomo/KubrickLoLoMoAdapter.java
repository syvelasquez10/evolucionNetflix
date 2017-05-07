// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lolomo;

import com.netflix.mediaclient.ui.lolomo.BaseLoLoMoAdapter;
import java.util.List;
import android.widget.AbsListView;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import com.netflix.mediaclient.ui.lolomo.BaseLoLoMoAdapter$RowHolder;
import com.netflix.mediaclient.ui.lolomo.BaseLoLoMoAdapter$LoMoRowContent;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.View;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.ui.lolomo.LoLoMoAdapter;

public class KubrickLoLoMoAdapter extends LoLoMoAdapter
{
    private static final int NUM_DUPLICATED_ROWS = 10;
    private final KubrickLolomoUtils$HeroTitleScroller heroTitleScroller;
    
    public KubrickLoLoMoAdapter(final LoLoMoFrag loLoMoFrag) {
        super(loLoMoFrag);
        this.heroTitleScroller = new KubrickLolomoUtils$HeroTitleScroller(loLoMoFrag);
    }
    
    @Override
    protected BaseLoLoMoAdapter$RowHolder createHolder(final View view, final LinearLayout linearLayout, final TextView textView, final BaseLoLoMoAdapter$LoMoRowContent baseLoLoMoAdapter$LoMoRowContent, final View view2) {
        return KubrickLolomoUtils.createHolder(this.activity, view, linearLayout, textView, baseLoLoMoAdapter$LoMoRowContent, view2);
    }
    
    @Override
    protected int getShelfVisibility(final LoMo loMo, final int n) {
        return 8;
    }
    
    @Override
    protected int getViewLayoutId() {
        return 2130903133;
    }
    
    @Override
    public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
        this.heroTitleScroller.onScroll(absListView);
    }
    
    @Override
    protected void updateLoMoData(final List<LoMo> list) {
        super.updateLoMoData(KubrickLolomoUtils.createDuplicateRows(this, list, 10));
    }
    
    @Override
    protected void updateRowViews(final BaseLoLoMoAdapter$RowHolder baseLoLoMoAdapter$RowHolder, final LoMo loMo, final int n) {
        super.updateRowViews(baseLoLoMoAdapter$RowHolder, loMo, n);
        KubrickLolomoUtils.updateRowViews(baseLoLoMoAdapter$RowHolder, loMo, n);
    }
}
