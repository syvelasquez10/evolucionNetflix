// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.servicemgr.interface_.offline.WatchState;

public class UserVisibleErrorCodeGenerator
{
    public static String addParenthesisWithPrefixSpace(final String s) {
        return " (" + s + ")";
    }
    
    public static String getOfflineErrorCodeForCompleteDownload(final WatchState watchState) {
        final int intValue = watchState.getIntValue();
        if (intValue < 0) {
            return "DLW." + "N" + intValue * -1;
        }
        return "DLW." + intValue;
    }
    
    public static String getOfflineErrorCodeForStoppedDownload(final StopReason stopReason) {
        final int intValue = stopReason.getIntValue();
        if (intValue < 0) {
            return "DLS." + "N" + intValue * -1;
        }
        return "DLS." + intValue;
    }
    
    public static String getOfflineErrorCodeFromStatusIntValue(final int n) {
        if (n < 0) {
            return "DLST." + "N" + n * -1;
        }
        return "DLST." + n;
    }
}
