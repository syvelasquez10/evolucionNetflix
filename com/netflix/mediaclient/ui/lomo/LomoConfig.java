// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.content.res.Resources;
import android.app.Activity;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
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
        initVideosPerPageMapTest6725();
        initLomoFragOffsetRight6725();
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
    
    public static int computeNumVideosToFetchPerBatch(final NetflixActivity netflixActivity, final LoMoType loMoType) {
        if (loMoType == LoMoType.BILLBOARD) {
            return 10;
        }
        final int screenSizeCategory = DeviceUtils.getScreenSizeCategory((Context)netflixActivity);
        if (BrowseExperience.isKubrick()) {
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
                    return Math.max(((SparseIntArray)LomoConfig.numCWVideosPerPageTable.get(1)).get(screenSizeCategory) * ((SparseIntArray)LomoConfig.numCWVideosPerPageTable.get(2)).get(screenSizeCategory), 4);
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
        return (int)(getPeakByOrientation(netflixActivity, DeviceUtils.getBasicScreenOrientation((Context)netflixActivity), DeviceUtils.getScreenSizeCategory((Context)netflixActivity)) * Resources.getSystem().getDisplayMetrics().density);
    }
    
    private static int getNumCWVideosPerPageTableByOrientation(final int n, final int n2) {
        return ((SparseIntArray)LomoConfig.numCWVideosPerPageTable.get(n)).get(n2);
    }
    
    private static int getNumVideosPerPageTableByOrientation(final int n, final int n2) {
        switch (LomoConfig$1.$SwitchMap$com$netflix$mediaclient$ui$experience$BrowseExperience[BrowseExperience.get().ordinal()]) {
            default: {
                return ((SparseIntArray)LomoConfig.numVideosPerPageTable.get(BrowseExperience.STANDARD).get(n)).get(n2);
            }
            case 1:
            case 2:
            case 3:
            case 4: {
                return ((SparseIntArray)LomoConfig.numVideosPerPageTable.get(BrowseExperience.get()).get(n)).get(n2);
            }
        }
    }
    
    private static int getPeakByOrientation(final Activity activity, final int n, final int n2) {
        switch (LomoConfig$1.$SwitchMap$com$netflix$mediaclient$ui$experience$BrowseExperience[BrowseExperience.get().ordinal()]) {
            default: {
                return 8;
            }
            case 5:
            case 6: {
                return 32;
            }
            case 2:
            case 3: {
                return ((SparseIntArray)LomoConfig.lomoFragOffsetRightTable.get(BrowseExperience.get()).get(n)).get(n2);
            }
        }
    }
    
    private static void initLomoFragOffsetRight6725() {
        final SparseArray sparseArray = new SparseArray(2);
        final SparseIntArray sparseIntArray = new SparseIntArray();
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        sparseIntArray.put(3, 120);
        sparseIntArray.put(4, 30);
        sparseArray.put(1, (Object)sparseIntArray);
        sparseIntArray2.put(3, 140);
        sparseIntArray2.put(4, 50);
        sparseArray.put(2, (Object)sparseIntArray2);
        LomoConfig.lomoFragOffsetRightTable.put(BrowseExperience.TEST_LOLOMO_TITLE_ART_6725_CELL_3, (SparseArray<SparseIntArray>)sparseArray);
        final SparseArray sparseArray2 = new SparseArray(2);
        final SparseIntArray sparseIntArray3 = new SparseIntArray();
        final SparseIntArray sparseIntArray4 = new SparseIntArray();
        sparseIntArray3.put(3, 30);
        sparseIntArray3.put(4, 20);
        sparseArray2.put(1, (Object)sparseIntArray3);
        sparseIntArray4.put(3, 30);
        sparseIntArray4.put(4, 20);
        sparseArray2.put(2, (Object)sparseIntArray4);
        LomoConfig.lomoFragOffsetRightTable.put(BrowseExperience.TEST_LOLOMO_TITLE_ART_6725_CELL_4, (SparseArray<SparseIntArray>)sparseArray2);
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
    
    private static void initVideosPerPageMapTest6725() {
        final SparseArray sparseArray = new SparseArray(2);
        final SparseIntArray sparseIntArray = new SparseIntArray();
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        sparseIntArray.put(3, 4);
        sparseIntArray.put(4, 5);
        sparseArray.put(1, (Object)sparseIntArray);
        sparseIntArray2.put(3, 6);
        sparseIntArray2.put(4, 7);
        sparseArray.put(2, (Object)sparseIntArray2);
        LomoConfig.numVideosPerPageTable.put(BrowseExperience.TEST_LOLOMO_TITLE_ART_6725_CELL_2, (SparseArray<SparseIntArray>)sparseArray);
        final SparseArray sparseArray2 = new SparseArray(2);
        final SparseIntArray sparseIntArray3 = new SparseIntArray();
        final SparseIntArray sparseIntArray4 = new SparseIntArray();
        sparseIntArray3.put(3, 2);
        sparseIntArray3.put(4, 3);
        sparseArray2.put(1, (Object)sparseIntArray3);
        sparseIntArray4.put(3, 3);
        sparseIntArray4.put(4, 4);
        sparseArray2.put(2, (Object)sparseIntArray4);
        LomoConfig.numVideosPerPageTable.put(BrowseExperience.TEST_LOLOMO_TITLE_ART_6725_CELL_3, (SparseArray<SparseIntArray>)sparseArray2);
        final SparseArray sparseArray3 = new SparseArray(2);
        final SparseIntArray sparseIntArray5 = new SparseIntArray();
        final SparseIntArray sparseIntArray6 = new SparseIntArray();
        sparseIntArray5.put(3, 3);
        sparseIntArray5.put(4, 4);
        sparseArray3.put(1, (Object)sparseIntArray5);
        sparseIntArray6.put(3, 4);
        sparseIntArray6.put(4, 5);
        sparseArray3.put(2, (Object)sparseIntArray6);
        LomoConfig.numVideosPerPageTable.put(BrowseExperience.TEST_LOLOMO_TITLE_ART_6725_CELL_4, (SparseArray<SparseIntArray>)sparseArray3);
        final SparseArray sparseArray4 = new SparseArray(2);
        final SparseIntArray sparseIntArray7 = new SparseIntArray();
        final SparseIntArray sparseIntArray8 = new SparseIntArray();
        sparseIntArray7.put(3, 3);
        sparseIntArray7.put(4, 4);
        sparseArray4.put(1, (Object)sparseIntArray7);
        sparseIntArray8.put(3, 5);
        sparseIntArray8.put(4, 6);
        sparseArray4.put(2, (Object)sparseIntArray8);
        LomoConfig.numVideosPerPageTable.put(BrowseExperience.TEST_LOLOMO_TITLE_ART_6725_CELL_5, (SparseArray<SparseIntArray>)sparseArray4);
    }
}
