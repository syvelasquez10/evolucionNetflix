// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.widget.TextView;
import java.util.List;
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
    
    public static int getGalleryLomoGenreNumColumns(final NetflixActivity netflixActivity) {
        return LomoConfig.computeStandardNumVideosPerPage(netflixActivity, false);
    }
    
    public static int getLomoFragImageOffsetLeftPx(final NetflixActivity netflixActivity) {
        return getLomoFragOffsetLeftPx(netflixActivity) + netflixActivity.getResources().getDimensionPixelOffset(2131362223);
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
                return netflixActivity.getResources().getDimensionPixelOffset(2131361950);
            }
            case 2: {
                int n;
                if (DeviceUtils.isLandscape((Context)netflixActivity)) {
                    n = 2131362205;
                }
                else {
                    n = 2131362211;
                }
                return netflixActivity.getResources().getDimensionPixelOffset(n);
            }
            case 3: {
                return netflixActivity.getResources().getDimensionPixelOffset(2131362173);
            }
        }
    }
    
    static String getTextForCTA(final Context context, final String s, final String s2) {
        switch (s) {
            default: {
                return context.getResources().getString(2131230910);
            }
            case "play": {
                return context.getResources().getString(2131230910);
            }
            case "playSeason": {
                return context.getResources().getString(2131230913, new Object[] { s2 });
            }
            case "playEpisode": {
                return context.getResources().getString(2131230906);
            }
            case "rewatchShow": {
                return context.getResources().getString(2131230912);
            }
            case "continueWatching": {
                return context.getResources().getString(2131230905);
            }
            case "playTrailer": {
                return context.getResources().getString(2131230917);
            }
            case "listEpisodes": {
                return context.getResources().getString(2131230908);
            }
        }
    }
    
    public static void toggleEpisodeBadge(final List<String> list, final TextView textView) {
        if (textView == null) {
            return;
        }
        if (list.size() > 0 && list.get(0).equalsIgnoreCase(LoMoUtils$SupportedBadge.NEW.toString())) {
            textView.setText((CharSequence)textView.getResources().getString(2131231068));
            textView.setVisibility(0);
            return;
        }
        textView.setVisibility(8);
    }
}
