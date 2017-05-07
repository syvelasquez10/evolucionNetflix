// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.util.SparseIntArray;
import android.util.SparseArray;

public final class LomoConfig
{
    public static final int NUM_BILLBOARDS_TO_FETCH_PER_BATCH = 10;
    private static final SparseArray<SparseIntArray> numCWVideosPerPageTable;
    private static final SparseArray<SparseIntArray> numVideosPerPageTable;
    
    static {
        numVideosPerPageTable = new SparseArray(2);
        final SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(1, 2);
        sparseIntArray.put(2, 3);
        sparseIntArray.put(3, 4);
        sparseIntArray.put(4, 4);
        LomoConfig.numVideosPerPageTable.put(1, (Object)sparseIntArray);
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        sparseIntArray2.put(1, 3);
        sparseIntArray2.put(2, 4);
        sparseIntArray2.put(3, 5);
        sparseIntArray2.put(4, 6);
        LomoConfig.numVideosPerPageTable.put(2, (Object)sparseIntArray2);
        numCWVideosPerPageTable = new SparseArray(2);
        final SparseIntArray sparseIntArray3 = new SparseIntArray();
        sparseIntArray3.put(1, 1);
        sparseIntArray3.put(2, 1);
        sparseIntArray3.put(3, 2);
        sparseIntArray3.put(4, 2);
        LomoConfig.numCWVideosPerPageTable.put(1, (Object)sparseIntArray3);
        final SparseIntArray sparseIntArray4 = new SparseIntArray();
        sparseIntArray4.put(1, 2);
        sparseIntArray4.put(2, 2);
        sparseIntArray4.put(3, 3);
        sparseIntArray4.put(4, 3);
        LomoConfig.numCWVideosPerPageTable.put(2, (Object)sparseIntArray4);
    }
    
    public static int computeNumVideosToFetchPerBatch(final NetflixActivity netflixActivity, final LoMoType loMoType) {
        if (loMoType == LoMoType.BILLBOARD) {
            return 10;
        }
        final int screenSizeCategory = DeviceUtils.getScreenSizeCategory((Context)netflixActivity);
        if (BrowseExperience.isKubrick() || BrowseExperience.isKubrickKids()) {
            if (loMoType == LoMoType.CONTINUE_WATCHING) {
                return 6;
            }
            return 12;
        }
        else {
            switch (LomoConfig$1.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$LoMoType[loMoType.ordinal()]) {
                default: {
                    return ((SparseIntArray)LomoConfig.numVideosPerPageTable.get(1)).get(screenSizeCategory) * ((SparseIntArray)LomoConfig.numVideosPerPageTable.get(2)).get(screenSizeCategory);
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
            return ((SparseIntArray)LomoConfig.numCWVideosPerPageTable.get(basicScreenOrientation)).get(screenSizeCategory);
        }
        return ((SparseIntArray)LomoConfig.numVideosPerPageTable.get(basicScreenOrientation)).get(screenSizeCategory);
    }
}
