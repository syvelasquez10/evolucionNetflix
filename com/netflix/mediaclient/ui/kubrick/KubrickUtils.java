// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick;

import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.ui.lomo.LoMoUtils$LoMoWidthType;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.util.SparseIntArray;
import android.util.SparseArray;

public class KubrickUtils
{
    private static final int LARGE_DETAIL_PAGE_THRESHOLD_DP = 1024;
    public static final int NUM_CW_VIDEOS_LANDSCAPE = 3;
    public static final int NUM_CW_VIDEOS_PORTRAIT = 2;
    public static final int NUM_LARGE_VIDEOS_LANDSCAPE = 3;
    public static final int NUM_LARGE_VIDEOS_PORTRAIT = 2;
    public static final int NUM_VIDEOS_LANDSCAPE = 4;
    public static final int NUM_VIDEOS_PORTRAIT = 3;
    private static final SparseArray<SparseIntArray> numCharactersPerPageTable;
    
    static {
        numCharactersPerPageTable = new SparseArray(2);
        final SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(3, 3);
        sparseIntArray.put(4, 4);
        KubrickUtils.numCharactersPerPageTable.put(1, (Object)sparseIntArray);
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        sparseIntArray2.put(3, 5);
        sparseIntArray2.put(4, 6);
        KubrickUtils.numCharactersPerPageTable.put(2, (Object)sparseIntArray2);
    }
    
    public static int computeNumCharactersPerPage(final NetflixActivity netflixActivity) {
        return ((SparseIntArray)KubrickUtils.numCharactersPerPageTable.get(DeviceUtils.getBasicScreenOrientation((Context)netflixActivity))).get(DeviceUtils.getScreenSizeCategory((Context)netflixActivity));
    }
    
    public static LoMoUtils$LoMoWidthType getCwGalleryWidthType(final NetflixActivity netflixActivity) {
        if (BrowseExperience.get() == BrowseExperience.KUBRICK_AB_TEST_HIGH_DENSITY) {
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
        if (DeviceUtils.getScreenWidthInDPs(context) >= 1024) {
            return Math.min(DeviceUtils.getScreenHeightInPixels(context), DeviceUtils.getScreenWidthInPixels(context));
        }
        return DeviceUtils.getScreenWidthInPixels(context);
    }
    
    public static boolean shouldShowKidsEntryInMenu(final NetflixActivity netflixActivity) {
        return true;
    }
}
