// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import android.os.Build$VERSION;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.content.Context;

public class Config_Ab8204 extends PersistentConfigurable
{
    public static long getLolomoExpiry(final Context context) {
        if (context != null) {
            switch (Config_Ab8204$1.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[PersistentConfig.getCellForTest(Config_Ab8204.class, context).ordinal()]) {
                case 1: {
                    return TimeUnit.MINUTES.toMillis(5L);
                }
                case 2: {
                    return TimeUnit.HOURS.toMillis(8L);
                }
                case 3: {
                    return TimeUnit.HOURS.toMillis(16L);
                }
            }
        }
        return 0L;
    }
    
    public static boolean isCacheEnabled(final Context context) {
        return Build$VERSION.SDK_INT > 19 && context != null && PersistentConfig.getCellForTest(Config_Ab8204.class, context) != ABTestConfig$Cell.CELL_ONE;
    }
    
    @Override
    public int getCellCountForAllocateABTestActivity() {
        return 4;
    }
    
    @Override
    public CharSequence getCellFriendlyTextForAllocateABTestActivity(final ABTestConfig$Cell abTestConfig$Cell) {
        switch (Config_Ab8204$1.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[abTestConfig$Cell.ordinal()]) {
            default: {
                return "Not activated";
            }
            case 1: {
                return "Activated, short expiry for testing";
            }
            case 2: {
                return "Activated, 8h expiry";
            }
            case 3: {
                return "Activated, 16h expiry";
            }
        }
    }
    
    @Override
    public CharSequence getFriendlyTextForAllocateABTestActivity() {
        return "Falkor Cache";
    }
    
    @Override
    public String getPrefKey() {
        return "persistent_ab8204_key";
    }
    
    @Override
    public String getTestId() {
        return "8204";
    }
}
