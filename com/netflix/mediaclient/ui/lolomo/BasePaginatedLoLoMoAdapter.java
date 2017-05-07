// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import com.viewpagerindicator.android.osp.ViewPager;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.Context;
import com.viewpagerindicator.CirclePageIndicator;
import android.view.View;
import android.widget.LinearLayout;
import java.util.Iterator;
import java.util.HashSet;
import com.netflix.mediaclient.ui.lomo.LoMoViewPager;
import java.util.Set;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;

public abstract class BasePaginatedLoLoMoAdapter<T extends BasicLoMo> extends BaseLoLoMoAdapter<T>
{
    private final Set<LoMoViewPager> pagerSet;
    
    public BasePaginatedLoLoMoAdapter(final LoLoMoFrag loLoMoFrag, final String s) {
        super(loLoMoFrag, s);
        this.pagerSet = new HashSet<LoMoViewPager>();
    }
    
    private boolean isAnyPagerLoading() {
        final Iterator<LoMoViewPager> iterator = this.pagerSet.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().isLoading()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    protected LoMoRowContent createRowContent(final LinearLayout linearLayout, final View view) {
        final CirclePageIndicator circlePageIndicator = new CirclePageIndicator((Context)this.activity);
        final LoMoViewPager viewPager = new LoMoViewPager(this.frag, this.manager, circlePageIndicator, this.viewRecycler, view, this.isGenreList());
        this.pagerSet.add(viewPager);
        viewPager.setFocusable(false);
        linearLayout.addView((View)viewPager);
        circlePageIndicator.setRadius(AndroidUtils.dipToPixels((Context)this.activity, 4));
        circlePageIndicator.setPageColor(1627389951);
        circlePageIndicator.setStrokeColor(0);
        circlePageIndicator.setStrokeWidth(0.0f);
        circlePageIndicator.setOnPageChangeListener(viewPager.getOnPageChangeListener());
        circlePageIndicator.setViewPager(viewPager);
        circlePageIndicator.setVisibility(8);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-1, -2);
        linearLayout$LayoutParams.topMargin = (int)(2.0f * circlePageIndicator.getRadius() + circlePageIndicator.getPaddingTop() + circlePageIndicator.getPaddingBottom() + 1.0f) * -2;
        linearLayout.addView((View)circlePageIndicator, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        return viewPager;
    }
    
    @Override
    public boolean isLoadingData() {
        return super.isLoadingData() || this.isAnyPagerLoading();
    }
    
    public void onDestroyView() {
        final Iterator<LoMoViewPager> iterator = this.pagerSet.iterator();
        while (iterator.hasNext()) {
            iterator.next().destroy();
        }
    }
    
    @Override
    public void onPause() {
        final Iterator<LoMoViewPager> iterator = this.pagerSet.iterator();
        while (iterator.hasNext()) {
            iterator.next().onPause();
        }
    }
    
    @Override
    public void onResume() {
        final Iterator<LoMoViewPager> iterator = this.pagerSet.iterator();
        while (iterator.hasNext()) {
            iterator.next().onResume();
        }
    }
}
