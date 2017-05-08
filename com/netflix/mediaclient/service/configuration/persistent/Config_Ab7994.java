// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.content.Context;

public class Config_Ab7994 extends PersistentConfigurable
{
    public static boolean isInTest(final Context context) {
        return context != null && PersistentConfig.getCellForTest(Config_Ab7994.class, context) != ABTestConfig$Cell.CELL_ONE && !DeviceUtils.isTabletByContext(context);
    }
    
    public static boolean shouldRenderDPWithBifs(final Context context) {
        return isInTest(context);
    }
    
    public static boolean shouldRenderTabs(final Context context) {
        return isInTest(context) && PersistentConfig.getCellForTest(Config_Ab7994.class, context) == ABTestConfig$Cell.CELL_THREE;
    }
    
    @Override
    public String getPrefKey() {
        return "persistent_phone_dp_redux_key";
    }
    
    @Override
    public String getTestId() {
        return "7994";
    }
}
