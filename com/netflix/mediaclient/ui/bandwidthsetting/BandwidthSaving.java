// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.bandwidthsetting;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfigData;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfigData$Cell;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.DataSaveConfigData;
import android.content.Context;

public final class BandwidthSaving
{
    public static final int MAX_TIMES_TO_SHOW_QUICK_ACTION = 2;
    public static final String TAG = "nf_bw_saving";
    
    public static int getMaxBandwidth(final Context context, final DataSaveConfigData dataSaveConfigData) {
        final boolean dataSavingEnabled = isDataSavingEnabled(context, dataSaveConfigData);
        final boolean b = PreferenceUtils.getIntPref(context, "user_bw_hd_override", -1) > 0;
        int n;
        if (dataSavingEnabled) {
            n = dataSaveConfigData.bitrateCapSaveOn;
        }
        else if (b) {
            n = dataSaveConfigData.bitrateCapHdOn;
        }
        else {
            n = dataSaveConfigData.bitrateCapSaveOff;
        }
        Log.d("nf_bw_saving", String.format("getMaxBandwidth cell: %s, dataSavingEnabled:%b, hdEnabled:%b - maxBandwidth: %d", dataSaveConfigData.abTestConfig_6733.getCell(), dataSavingEnabled, b, n));
        return n;
    }
    
    public static boolean isBWSavingEnabledForPlay(final Context context, final DataSaveConfigData dataSaveConfigData, final boolean b) {
        if (!ConnectivityUtils.isNetworkTypeCellular(context) && !b) {
            Log.d("nf_bw_saving", String.format("isNetworkTypeCellular: %b, dataSaveConfigData: %s", ConnectivityUtils.isNetworkTypeCellular(context), dataSaveConfigData));
            return false;
        }
        return isBWSavingEnabledForUser(dataSaveConfigData);
    }
    
    public static boolean isBWSavingEnabledForUser(final DataSaveConfigData dataSaveConfigData) {
        if (dataSaveConfigData == null) {
            return false;
        }
        final ABTestConfigData abTestConfig_6733 = dataSaveConfigData.abTestConfig_6733;
        if (abTestConfig_6733 == null || abTestConfig_6733.getCell().equals(ABTestConfigData$Cell.CELL_ONE)) {
            Log.d("nf_bw_saving", "testConfig:" + abTestConfig_6733);
            return false;
        }
        return true;
    }
    
    public static boolean isDataSavingEnabled(final Context context, final DataSaveConfigData dataSaveConfigData) {
        final boolean dataSaveOn = dataSaveConfigData.dataSaveOn;
        final int intPref = PreferenceUtils.getIntPref(context, "user_bw_override", -1);
        if (intPref == -1) {
            return dataSaveOn;
        }
        return intPref > 0;
    }
    
    public static boolean isHdEnabledDevice(final ServiceManager serviceManager) {
        return serviceManager != null && serviceManager.getConfiguration().isWidevineL1Enabled();
    }
}
