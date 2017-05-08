// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.content.res.Resources;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.CWTestUtil;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import android.content.Context;
import java.util.HashMap;
import android.util.SparseIntArray;
import android.util.SparseArray;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import java.util.Map;

public final class LomoConfig
{
    public static final int NUM_BILLBOARDS_TO_FETCH_PER_BATCH = 10;
    private static final Map<BrowseExperience, SparseArray<SparseIntArray>> lomoFragOffsetRightTable;
    private static final SparseArray<SparseIntArray> numCWVideosPerPageTable;
    private static final Map<BrowseExperience, SparseArray<SparseIntArray>> numVideosPerPageTable;
    
    static {
        numVideosPerPageTable = new HashMap<BrowseExperience, SparseArray<SparseIntArray>>();
        lomoFragOffsetRightTable = new HashMap<BrowseExperience, SparseArray<SparseIntArray>>();
        initVideosPerPageMapStandard();
        numCWVideosPerPageTable = new SparseArray(2);
        final SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(1, 1);
        sparseIntArray.put(2, 1);
        sparseIntArray.put(3, 2);
        sparseIntArray.put(4, 2);
        LomoConfig.numCWVideosPerPageTable.put(1, (Object)sparseIntArray);
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        sparseIntArray2.put(1, 2);
        sparseIntArray2.put(2, 2);
        sparseIntArray2.put(3, 3);
        sparseIntArray2.put(4, 3);
        LomoConfig.numCWVideosPerPageTable.put(2, (Object)sparseIntArray2);
    }
    
    public static int computeNumVideosToFetchPerBatch(final Context context, final LoMoType loMoType) {
        if (loMoType == LoMoType.BILLBOARD) {
            return 10;
        }
        final int screenSizeCategory = DeviceUtils.getScreenSizeCategory(context);
        if (BrowseExperience.isDisplayPageRefresh()) {
            if (loMoType == LoMoType.CONTINUE_WATCHING) {
                return 6;
            }
            return 12;
        }
        else {
            switch (LomoConfig$1.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$LoMoType[loMoType.ordinal()]) {
                default: {
                    return getNumVideosPerPageTableByOrientation(2, screenSizeCategory) * getNumVideosPerPageTableByOrientation(1, screenSizeCategory);
                }
                case 1: {
                    return CWTestUtil.computeNumVideosToFetchPerBatch(context, Math.max(((SparseIntArray)LomoConfig.numCWVideosPerPageTable.get(1)).get(screenSizeCategory) * ((SparseIntArray)LomoConfig.numCWVideosPerPageTable.get(2)).get(screenSizeCategory), 4));
                }
            }
        }
    }
    
    public static int computeStandardNumVideosPerPage(final NetflixActivity netflixActivity, final boolean b) {
        final int basicScreenOrientation = DeviceUtils.getBasicScreenOrientation((Context)netflixActivity);
        final int screenSizeCategory = DeviceUtils.getScreenSizeCategory((Context)netflixActivity);
        if (b) {
            return getNumCWVideosPerPageTableByOrientation(basicScreenOrientation, screenSizeCategory);
        }
        return getNumVideosPerPageTableByOrientation(basicScreenOrientation, screenSizeCategory);
    }
    
    public static int getLomoFragOffsetRightPx(final NetflixActivity netflixActivity) {
        DeviceUtils.getBasicScreenOrientation((Context)netflixActivity);
        DeviceUtils.getScreenSizeCategory((Context)netflixActivity);
        return (int)(getPeak() * Resources.getSystem().getDisplayMetrics().density);
    }
    
    private static int getNumCWVideosPerPageTableByOrientation(final int n, final int n2) {
        return ((SparseIntArray)LomoConfig.numCWVideosPerPageTable.get(n)).get(n2);
    }
    
    public static int getNumVideosPerPageTableByOrientation(final int n, final int n2) {
        return ((SparseIntArray)LomoConfig.numVideosPerPageTable.get(BrowseExperience.STANDARD).get(n)).get(n2);
    }
    
    private static int getPeak() {
        return 8;
    }
    
    private static void initVideosPerPageMapStandard() {
        final SparseArray sparseArray = new SparseArray(2);
        final SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(1, 2);
        sparseIntArray.put(2, 3);
        sparseIntArray.put(3, 4);
        sparseIntArray.put(4, 4);
        sparseArray.put(1, (Object)sparseIntArray);
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        sparseIntArray2.put(1, 3);
        sparseIntArray2.put(2, 4);
        sparseIntArray2.put(3, 5);
        sparseIntArray2.put(4, 6);
        sparseArray.put(2, (Object)sparseIntArray2);
        LomoConfig.numVideosPerPageTable.put(BrowseExperience.STANDARD, (SparseArray<SparseIntArray>)sparseArray);
    }
}
