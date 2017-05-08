// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.ums;

import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.leafs.EogAlert;

public final class EogUtils
{
    private static final int CELL_WITH_IMAGES = 5;
    private static final int CELL_WITH_ONLY_PLAN_PAGE = 4;
    public static final int INVALID_PLAN_INDEX = -1;
    public static final int LANDING_PAGE_INDEX = 0;
    private static final int PLAN_PAGE_INDEX = 1;
    private static final String TAG = "eog_utils";
    
    public static boolean isPlanOnlyCell(final EogAlert eogAlert) {
        return eogAlert.abTestCell == 4;
    }
    
    public static boolean isSmallSizeTablet(final Context context) {
        if (DeviceUtils.isTabletByContext(context) && DeviceUtils.getScreenSizeCategory(context) < 4) {
            if (Log.isLoggable()) {
                Log.d("eog_utils", String.format(" tablet in portarit - small ? screenSize:%s", DeviceUtils.getScreenSizeCategory(context)));
            }
            return true;
        }
        return false;
    }
    
    public static boolean shouldGoToLandingPage(final int n, final EogAlert eogAlert) {
        final boolean b = n == 1 && !isPlanOnlyCell(eogAlert);
        if (Log.isLoggable()) {
            Log.d("eog_utils", String.format("shouldGoToLandingPage: %b, currentPage:%d, testCell:%d", b, n, eogAlert.abTestCell));
        }
        return b;
    }
    
    public static boolean shouldGoToPlanPage(final int n, final EogAlert eogAlert) {
        final boolean b = n > 0 || isPlanOnlyCell(eogAlert);
        if (Log.isLoggable()) {
            Log.d("eog_utils", String.format("shouldGoToPlanPage: %b, currentPage:%d, testCell:%d", b, n, eogAlert.abTestCell));
        }
        return b;
    }
    
    public static boolean shouldShowEogAlert(final ServiceManager serviceManager) {
        final boolean b = serviceManager.getEndOfGrandfatheringAlert() != null && !serviceManager.getEndOfGrandfatheringAlert().isDirty && !KidsUtils.isKidsProfile(serviceManager.getCurrentProfile());
        if (Log.isLoggable() && serviceManager.getEndOfGrandfatheringAlert() != null) {
            Log.d("eog_utils", String.format(" shouldShow:%b - eogAlredyShown:%b, isKidsProfile:%b, ", b, serviceManager.getEndOfGrandfatheringAlert().isDirty, KidsUtils.isKidsProfile(serviceManager.getCurrentProfile())));
        }
        return b;
    }
    
    public static boolean shouldUseLayoutWithImages(final EogAlert eogAlert) {
        return eogAlert.abTestCell == 5;
    }
}
