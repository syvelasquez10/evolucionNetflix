// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lolomo;

import android.widget.TextView;
import android.util.Log;
import android.widget.AbsListView;
import android.view.View;
import android.content.res.Resources;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;

class KubrickLolomoUtils$HeroTitleScroller
{
    private static final boolean LOG_VERBOSE = false;
    private static final String TAG = "HeroTitleScroller";
    private final LoLoMoFrag frag;
    private final int textPadding;
    private final int titleMarginTopPx;
    
    public KubrickLolomoUtils$HeroTitleScroller(final LoLoMoFrag frag) {
        this.frag = frag;
        final Resources resources = frag.getResources();
        this.titleMarginTopPx = resources.getDimensionPixelSize(2131361994);
        this.textPadding = resources.getDimensionPixelOffset(2131361877);
    }
    
    private int computeScrollingThreshPx(final KubrickLolomoUtils$KubrickRowHolder kubrickLolomoUtils$KubrickRowHolder, final View view) {
        return this.frag.getNetflixActivity().getActionBarHeight() - view.getPaddingTop() - kubrickLolomoUtils$KubrickRowHolder.topSpacer.getHeight();
    }
    
    private int computeTransYMax(final KubrickLolomoUtils$KubrickRowHolder kubrickLolomoUtils$KubrickRowHolder, final View view) {
        return view.getHeight() - view.getPaddingTop() - kubrickLolomoUtils$KubrickRowHolder.kubrickHeroTitle.getHeight() - kubrickLolomoUtils$KubrickRowHolder.topSpacer.getHeight() - this.titleMarginTopPx - this.textPadding;
    }
    
    public void onScroll(final AbsListView absListView) {
        for (int i = 0; i < absListView.getChildCount(); ++i) {
            final View child = absListView.getChildAt(i);
            final KubrickLolomoUtils$KubrickRowHolder kubrickLolomoUtils$KubrickRowHolder = (KubrickLolomoUtils$KubrickRowHolder)child.getTag();
            if (kubrickLolomoUtils$KubrickRowHolder != null && kubrickLolomoUtils$KubrickRowHolder.kubrickHeroTitle.getVisibility() == 0) {
                final TextView kubrickHeroTitle = kubrickLolomoUtils$KubrickRowHolder.kubrickHeroTitle;
                final int computeScrollingThreshPx = this.computeScrollingThreshPx(kubrickLolomoUtils$KubrickRowHolder, child);
                if (i <= 1 && child.getTop() < computeScrollingThreshPx) {
                    int n = computeScrollingThreshPx - child.getTop();
                    final int computeTransYMax = this.computeTransYMax(kubrickLolomoUtils$KubrickRowHolder, child);
                    if (n > computeTransYMax) {
                        n = computeTransYMax;
                    }
                    kubrickHeroTitle.setTranslationY((float)n);
                }
                else {
                    kubrickHeroTitle.setTranslationY(0.0f);
                }
            }
            else if (kubrickLolomoUtils$KubrickRowHolder == null && Log.isLoggable("HeroTitleScroller", 3)) {
                Log.d("HeroTitleScroller", "No row holder available for view at row: " + i);
            }
        }
    }
}
