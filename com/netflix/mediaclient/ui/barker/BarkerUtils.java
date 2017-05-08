// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker;

import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.ui.barker.details.RelatedTitleState;
import java.util.Stack;
import com.netflix.mediaclient.servicemgr.interface_.details.Similarable;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.ui.barker.details.BarkerHelper$BarkerBars;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.barker.details.BarkerHelper;
import com.netflix.mediaclient.ui.lomo.LoMoUtils$LoMoWidthType;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class BarkerUtils
{
    private static final int LARGE_DETAIL_PAGE_THRESHOLD_DP = 1024;
    public static final int NUM_CW_VIDEOS_LANDSCAPE = 3;
    public static final int NUM_CW_VIDEOS_PORTRAIT = 2;
    public static final int NUM_LARGE_VIDEOS_LANDSCAPE = 3;
    public static final int NUM_LARGE_VIDEOS_PORTRAIT = 2;
    public static final int NUM_VIDEOS_LANDSCAPE = 4;
    public static final int NUM_VIDEOS_PORTRAIT = 3;
    private static final String TAG = "BarkerUtils";
    
    public static LoMoUtils$LoMoWidthType getCwGalleryWidthType(final NetflixActivity netflixActivity) {
        if (BarkerHelper.isInTest((Context)netflixActivity)) {
            return LoMoUtils$LoMoWidthType.STANDARD;
        }
        if (DeviceUtils.getScreenSizeCategory((Context)netflixActivity) == 3 && DeviceUtils.isPortrait((Context)netflixActivity)) {
            return LoMoUtils$LoMoWidthType.KUBRICK_EXTENDED_CW_GALLERY_ROW;
        }
        if (DeviceUtils.getScreenSizeCategory((Context)netflixActivity) == 4 && DeviceUtils.isLandscape((Context)netflixActivity)) {
            return LoMoUtils$LoMoWidthType.KUBRICK_EXTENDED_CW_GALLERY_ROW;
        }
        return LoMoUtils$LoMoWidthType.STANDARD;
    }
    
    public static int getDetailsPageContentWidth(final Context context) {
        if (BrowseExperience.isDisplayPageRefresh()) {
            return new BarkerHelper$BarkerBars(context).getModalWidth();
        }
        return getDetailsPageContentWidthLegacy(context);
    }
    
    private static int getDetailsPageContentWidthLegacy(final Context context) {
        if (DeviceUtils.getScreenWidthInDPs(context) >= 1024) {
            return Math.min(DeviceUtils.getScreenHeightInPixels(context), DeviceUtils.getScreenWidthInPixels(context));
        }
        return DeviceUtils.getScreenWidthInPixels(context);
    }
    
    public static void updateTrackId(final DetailsActivity detailsActivity, final Similarable similarable, final Stack<RelatedTitleState> stack, final RelatedTitleState relatedTitleState) {
        final PlayContextImp playContextImp = (PlayContextImp)detailsActivity.getPlayContext();
        final boolean b = false;
        int n;
        if (relatedTitleState == null) {
            n = (b ? 1 : 0);
            if (!stack.isEmpty()) {
                n = similarable.getSimilarsTrackId();
            }
        }
        else {
            n = (b ? 1 : 0);
            if (stack.isEmpty()) {
                n = (b ? 1 : 0);
                if (relatedTitleState.getPlayContext() != null) {
                    n = relatedTitleState.getPlayContext().getTrackId();
                }
            }
        }
        if (n > 0) {
            detailsActivity.setPlayContext(playContextImp.cloneWithNewTrackId(n));
        }
    }
}
