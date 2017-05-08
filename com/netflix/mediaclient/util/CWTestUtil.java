// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.content.Context;

public class CWTestUtil
{
    public static final int NUMBER_OF_CW_VIDEOS_RELATIVE_TO_CONTROL = 3;
    
    public static int computeNumVideosToFetchPerBatch(final Context context, final int n) {
        int n2 = n;
        if (isInTest(context)) {
            n2 = n * 3;
        }
        return n2;
    }
    
    public static boolean isDirectToPlayback(final Context context) {
        return PersistentConfig.getCWProgressBar(context) == ABTestConfig$Cell.CELL_TWO;
    }
    
    public static boolean isInTest(final Context context) {
        return PersistentConfig.getCWProgressBar(context) != ABTestConfig$Cell.CELL_ONE;
    }
}
