// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.content.Context;

public class BarkerHelper
{
    public static final int FULL_SCREEN_COLUMNS = 12;
    
    public static boolean isInTest(final Context context) {
        return context != null && PersistentConfig.getDisplayPageRefreshTestCell(context) != ABTestConfig$Cell.CELL_TWO && DeviceUtils.isTabletByContext(context);
    }
}
