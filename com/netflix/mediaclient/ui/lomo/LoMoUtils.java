// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.app.Activity;
import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class LoMoUtils
{
    private static final String TAG = "LoMoUtils";
    
    public static void applyContentOverlapPadding(final NetflixActivity netflixActivity, final View view, final LoMoUtils$LoMoWidthType loMoUtils$LoMoWidthType) {
        view.setPadding(getLomoFragOffsetLeftPx(netflixActivity), 0, getLomoFragOffsetRightPx(netflixActivity, loMoUtils$LoMoWidthType), 0);
    }
    
    public static int getKidsLomoGenreNumColumns(final Activity activity) {
        if (BrowseExperience.useLolomoBoxArt()) {
            return activity.getResources().getInteger(2131427330);
        }
        return activity.getResources().getInteger(2131427329);
    }
    
    public static int getLomoFragImageOffsetLeftPx(final NetflixActivity netflixActivity) {
        return getLomoFragOffsetLeftPx(netflixActivity) + netflixActivity.getResources().getDimensionPixelOffset(2131296535);
    }
    
    public static int getLomoFragOffsetLeftPx(final Activity activity) {
        return activity.getResources().getDimensionPixelOffset(BrowseExperience.getLomoFragOffsetLeftDimenId());
    }
    
    public static int getLomoFragOffsetRightPx(final NetflixActivity netflixActivity, final LoMoUtils$LoMoWidthType loMoUtils$LoMoWidthType) {
        if (Log.isLoggable()) {
            Log.v("LoMoUtils", "getLomoFragOffsetRightPx, activity: " + netflixActivity.getClass().getSimpleName() + ", widthType: " + loMoUtils$LoMoWidthType);
        }
        switch (LoMoUtils$1.$SwitchMap$com$netflix$mediaclient$ui$lomo$LoMoUtils$LoMoWidthType[loMoUtils$LoMoWidthType.ordinal()]) {
            default: {
                return LomoConfig.getLomoFragOffsetRightPx(netflixActivity);
            }
            case 1: {
                return netflixActivity.getResources().getDimensionPixelOffset(2131296514);
            }
            case 2: {
                int n;
                if (DeviceUtils.isLandscape((Context)netflixActivity)) {
                    n = 2131296518;
                }
                else {
                    n = 2131296524;
                }
                return netflixActivity.getResources().getDimensionPixelOffset(n);
            }
            case 3: {
                return netflixActivity.getResources().getDimensionPixelOffset(2131296489);
            }
        }
    }
}
