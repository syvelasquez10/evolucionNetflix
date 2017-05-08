// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.content.Context;

public class LolomoPayloadABTestConfig extends PersistentConfigurable
{
    public static final int DEFAULT_NUM_LOMOS_TO_FETCH_PER_BATCH = 20;
    private static final String PERSISTENT_REDUCED_LOLOMO_PAYLOAD_CONFIG_PREFS_KEY = "persistent_reduced_lolomo_payload_experience_key";
    
    public static int getNumberRowsToFetch(final Context context) {
        switch (LolomoPayloadABTestConfig$1.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[PersistentConfig.getCellForTest(LolomoPayloadABTestConfig.class, context).ordinal()]) {
            default: {
                return 20;
            }
            case 1: {
                return 6;
            }
            case 2: {
                return 10;
            }
        }
    }
    
    public static int getNumberVideosToFetchPerRow(final Context context, final int n) {
        switch (LolomoPayloadABTestConfig$1.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[PersistentConfig.getCellForTest(LolomoPayloadABTestConfig.class, context).ordinal()]) {
            default: {
                return LomoConfig.getNumVideosPerPageTableByOrientation(2, n) * LomoConfig.getNumVideosPerPageTableByOrientation(1, n);
            }
            case 1:
            case 2: {
                return LomoConfig.getNumVideosPerPageTableByOrientation(DeviceUtils.getBasicScreenOrientation(context), n) * 2;
            }
        }
    }
    
    public static int getStandardNumberVideosToPrefetch(final Context context) {
        final int screenSizeCategory = DeviceUtils.getScreenSizeCategory(context);
        switch (LolomoPayloadABTestConfig$1.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[PersistentConfig.getCellForTest(LolomoPayloadABTestConfig.class, context).ordinal()]) {
            default: {
                return LomoConfig.computeNumVideosToFetchPerBatch(context, LoMoType.STANDARD);
            }
            case 1:
            case 2: {
                return LomoConfig.getNumVideosPerPageTableByOrientation(2, screenSizeCategory) * 2;
            }
        }
    }
    
    public static String getSummaryNodeKey(final Context context) {
        if (PersistentConfig.getCellForTest(LolomoPayloadABTestConfig.class, context) == ABTestConfig$Cell.CELL_ONE) {
            return "legacySummary";
        }
        return "summary";
    }
    
    public static boolean isInTest(final Context context) {
        return PersistentConfig.getCellForTest(LolomoPayloadABTestConfig.class, context) != ABTestConfig$Cell.CELL_TWO;
    }
    
    @Override
    public ABTestConfig$Cell getDefaultCell() {
        return ABTestConfig$Cell.CELL_TWO;
    }
    
    @Override
    public String getPrefKey() {
        return "persistent_reduced_lolomo_payload_experience_key";
    }
    
    @Override
    public String getTestId() {
        return "7984";
    }
}
