// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.content.Context;

public final class VoiceSearchABTestUtils
{
    public static boolean showSoftKeyboard(final Context context) {
        return PersistentConfig.getVoiceSearchABTestCell(context) != ABTestConfig$Cell.CELL_FOUR;
    }
    
    public static boolean showVoiceSearchInActionBar(final Context context) {
        return PersistentConfig.getVoiceSearchABTestCell(context) == ABTestConfig$Cell.CELL_ONE;
    }
    
    public static boolean showVoiceSearchInLayout(final Context context) {
        switch (VoiceSearchABTestUtils$1.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[PersistentConfig.getVoiceSearchABTestCell(context).ordinal()]) {
            default: {
                return false;
            }
            case 1:
            case 2:
            case 3: {
                return true;
            }
        }
    }
    
    public static boolean startVoiceSearch(final Context context) {
        return PersistentConfig.getVoiceSearchABTestCell(context) == ABTestConfig$Cell.CELL_FIVE;
    }
}
