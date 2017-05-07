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
    
    public static int getLomoFragImageOffsetLeftPx(final NetflixActivity netflixActivity) {
        return getLomoFragOffsetLeftPx(netflixActivity) + netflixActivity.getResources().getDimensionPixelOffset(2131296384);
    }
    
    public static int getLomoFragOffsetLeftPx(final Activity activity) {
        return activity.getResources().getDimensionPixelOffset(BrowseExperience.getLomoFragOffsetLeftDimenId());
    }
    
    public static int getLomoFragOffsetRightPx(final NetflixActivity netflixActivity, final LoMoUtils$LoMoWidthType loMoUtils$LoMoWidthType) {
        if (Log.isLoggable()) {
            Log.v("LoMoUtils", "getLomoFragOffsetRightPx, activity: " + netflixActivity.getClass().getSimpleName() + ", widthType: " + loMoUtils$LoMoWidthType);
        }
        int lomoFragOffsetRightDimenId = 0;
        switch (LoMoUtils$1.$SwitchMap$com$netflix$mediaclient$ui$lomo$LoMoUtils$LoMoWidthType[loMoUtils$LoMoWidthType.ordinal()]) {
            default: {
                lomoFragOffsetRightDimenId = BrowseExperience.getLomoFragOffsetRightDimenId();
                break;
            }
            case 1: {
                lomoFragOffsetRightDimenId = 2131296474;
                break;
            }
            case 2: {
                if (DeviceUtils.isLandscape((Context)netflixActivity)) {
                    lomoFragOffsetRightDimenId = 2131296475;
                    break;
                }
                lomoFragOffsetRightDimenId = 2131296444;
                break;
            }
            case 3: {
                lomoFragOffsetRightDimenId = 2131296451;
                break;
            }
        }
        return netflixActivity.getResources().getDimensionPixelOffset(lomoFragOffsetRightDimenId);
    }
}
