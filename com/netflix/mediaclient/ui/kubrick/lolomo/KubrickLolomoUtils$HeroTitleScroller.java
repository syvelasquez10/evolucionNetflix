// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lolomo;

import com.netflix.mediaclient.ui.lolomo.BaseLoLoMoAdapter$LoMoRowContent;
import android.widget.TextView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.lolomo.BaseLoLoMoAdapter$RowHolder;
import com.netflix.mediaclient.Log;
import android.widget.AbsListView;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.View;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;

class KubrickLolomoUtils$HeroTitleScroller
{
    private static final boolean LOG_VERBOSE = false;
    private static final String TAG = "HeroTitleScroller";
    private final LoLoMoFrag frag;
    
    public KubrickLolomoUtils$HeroTitleScroller(final LoLoMoFrag frag) {
        this.frag = frag;
    }
    
    private int computeScrollingThreshPx(final KubrickLolomoUtils$KubrickRowHolder kubrickLolomoUtils$KubrickRowHolder, final View view) {
        return this.frag.getNetflixActivity().getActionBarHeight() - view.getPaddingTop() - ViewUtils.getHeightIfVisible(kubrickLolomoUtils$KubrickRowHolder.topSpacer);
    }
    
    private int computeTransYMax(final KubrickLolomoUtils$KubrickRowHolder kubrickLolomoUtils$KubrickRowHolder, final View view) {
        return view.getHeight() - view.getPaddingTop() - kubrickLolomoUtils$KubrickRowHolder.title.getHeight() - ViewUtils.getHeightIfVisible(kubrickLolomoUtils$KubrickRowHolder.topSpacer);
    }
    
    public void onScroll(final AbsListView absListView) {
        for (int i = 0; i < absListView.getChildCount(); ++i) {
            final View child = absListView.getChildAt(i);
            final KubrickLolomoUtils$KubrickRowHolder kubrickLolomoUtils$KubrickRowHolder = (KubrickLolomoUtils$KubrickRowHolder)child.getTag();
            if (kubrickLolomoUtils$KubrickRowHolder != null && kubrickLolomoUtils$KubrickRowHolder.title.getVisibility() == 0) {
                final int computeScrollingThreshPx = this.computeScrollingThreshPx(kubrickLolomoUtils$KubrickRowHolder, child);
                if (i <= 1 && child.getTop() < computeScrollingThreshPx) {
                    int n = computeScrollingThreshPx - child.getTop();
                    final int computeTransYMax = this.computeTransYMax(kubrickLolomoUtils$KubrickRowHolder, child);
                    if (n > computeTransYMax) {
                        n = computeTransYMax;
                    }
                    kubrickLolomoUtils$KubrickRowHolder.title.setTranslationY((float)n);
                }
                else {
                    kubrickLolomoUtils$KubrickRowHolder.title.setTranslationY(0.0f);
                }
            }
            else if (kubrickLolomoUtils$KubrickRowHolder == null && Log.isLoggable()) {
                Log.d("HeroTitleScroller", "No row holder available for view at row: " + i);
            }
        }
    }
}
