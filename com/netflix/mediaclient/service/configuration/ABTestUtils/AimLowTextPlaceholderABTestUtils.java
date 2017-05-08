// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.ABTestUtils;

import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.configuration.persistent.PersistentConfigurable;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import com.netflix.mediaclient.service.configuration.persistent.AimLowTextPlaceholderConfig;
import android.content.Context;

public final class AimLowTextPlaceholderABTestUtils
{
    public static boolean isInTest(final Context context) {
        return context != null && PersistentConfig.getCellForTest(AimLowTextPlaceholderConfig.class, context) != ABTestConfig$Cell.CELL_ONE && AndroidUtils.getAndroidVersion() >= 21;
    }
    
    public static boolean shouldShowTextOnPlaceholder(final Context context) {
        return isInTest(context);
    }
}
