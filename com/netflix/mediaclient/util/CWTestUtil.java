// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.ui.lomo.CwTestVTwoView;
import com.netflix.mediaclient.ui.lomo.CwTestView;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import com.netflix.mediaclient.ui.lomo.CwView;
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
    
    public static CwView createCWViewForTest(final Context context) {
        switch (CWTestUtil$1.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[PersistentConfig.getCWProgressBar(context).ordinal()]) {
            default: {
                return new CwView(context);
            }
            case 2:
            case 3: {
                return new CwTestView(context);
            }
            case 4: {
                return new CwTestVTwoView(context, true);
            }
            case 5: {
                return new CwTestVTwoView(context, false);
            }
        }
    }
    
    public static Class getCWViewClass(final Context context) {
        switch (CWTestUtil$1.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[PersistentConfig.getCWProgressBar(context).ordinal()]) {
            default: {
                return CwView.class;
            }
            case 2:
            case 3: {
                return CwTestView.class;
            }
            case 4:
            case 5: {
                return CwTestVTwoView.class;
            }
        }
    }
    
    public static boolean isDirectToPlayback(final Context context) {
        return PersistentConfig.getCWProgressBar(context) != ABTestConfig$Cell.CELL_THREE;
    }
    
    public static boolean isInTest(final Context context) {
        return PersistentConfig.getCWProgressBar(context) != ABTestConfig$Cell.CELL_ONE;
    }
}
