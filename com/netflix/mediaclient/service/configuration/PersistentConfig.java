// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import java.util.Collection;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import java.util.Iterator;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.persistent.LolomoPayloadABTestConfig;
import com.netflix.mediaclient.service.configuration.persistent.KidsParityPhone;
import com.netflix.mediaclient.service.configuration.persistent.KidsParityTablet;
import com.netflix.mediaclient.service.configuration.persistent.AimLowTextPlaceholderConfig;
import com.netflix.mediaclient.service.configuration.persistent.DPPrefetchABTestConfig;
import com.netflix.mediaclient.service.configuration.persistent.PrefetchLolomoConfig;
import com.netflix.mediaclient.service.configuration.persistent.BrandLoveSurvey;
import com.netflix.mediaclient.service.configuration.persistent.OfflineTutorial;
import com.netflix.mediaclient.service.configuration.persistent.OnRamp;
import com.netflix.mediaclient.service.configuration.persistent.ContinueWatchingProgBar;
import com.netflix.mediaclient.service.configuration.persistent.DisplayPageRefreshConfig;
import com.netflix.mediaclient.service.configuration.persistent.MotionBB;
import com.netflix.mediaclient.service.configuration.persistent.CoppolaTwo;
import com.netflix.mediaclient.service.configuration.persistent.CoppolaOne;
import com.netflix.mediaclient.service.configuration.persistent.VoiceSearch;
import com.netflix.mediaclient.service.configuration.persistent.PersistentConfigurable;
import java.util.HashMap;

public final class PersistentConfig
{
    private static final String TAG = "PersistentConfig";
    private static HashMap<Class<? extends PersistentConfigurable>, PersistentConfigurable> sConfigs;
    
    static {
        (PersistentConfig.sConfigs = new HashMap<Class<? extends PersistentConfigurable>, PersistentConfigurable>()).put(VoiceSearch.class, new VoiceSearch());
        PersistentConfig.sConfigs.put(CoppolaOne.class, new CoppolaOne());
        PersistentConfig.sConfigs.put(CoppolaTwo.class, new CoppolaTwo());
        PersistentConfig.sConfigs.put(MotionBB.class, new MotionBB());
        PersistentConfig.sConfigs.put(DisplayPageRefreshConfig.class, new DisplayPageRefreshConfig());
        PersistentConfig.sConfigs.put(ContinueWatchingProgBar.class, new ContinueWatchingProgBar());
        PersistentConfig.sConfigs.put(OnRamp.class, new OnRamp());
        PersistentConfig.sConfigs.put(OfflineTutorial.class, new OfflineTutorial());
        PersistentConfig.sConfigs.put(BrandLoveSurvey.class, new BrandLoveSurvey());
        PersistentConfig.sConfigs.put(PrefetchLolomoConfig.class, new PrefetchLolomoConfig());
        PersistentConfig.sConfigs.put(DPPrefetchABTestConfig.class, new DPPrefetchABTestConfig());
        PersistentConfig.sConfigs.put(AimLowTextPlaceholderConfig.class, new AimLowTextPlaceholderConfig());
        PersistentConfig.sConfigs.put(KidsParityTablet.class, new KidsParityTablet());
        PersistentConfig.sConfigs.put(KidsParityPhone.class, new KidsParityPhone());
        PersistentConfig.sConfigs.put(LolomoPayloadABTestConfig.class, new LolomoPayloadABTestConfig());
    }
    
    public static void delete(final Context context) {
        final Iterator<PersistentConfigurable> iterator = PersistentConfig.sConfigs.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().delete(context);
        }
    }
    
    public static ABTestConfig$Cell getCellForTest(final Class<? extends PersistentConfigurable> clazz, final Context context) {
        return getConfigForTest(clazz).getCell(context);
    }
    
    public static int getCellIdForTest(final Class<? extends PersistentConfigurable> clazz, final Context context) {
        return getConfigForTest(clazz).getCell(context).getCellId();
    }
    
    @Deprecated
    public static int getCellOrdinalForTest(final Class<? extends PersistentConfigurable> clazz, final Context context) {
        return getConfigForTest(clazz).getCell(context).ordinal();
    }
    
    public static <T extends PersistentConfigurable> T getConfigForTest(final Class<T> clazz) {
        final PersistentConfigurable persistentConfigurable = PersistentConfig.sConfigs.get(clazz);
        if (persistentConfigurable == null) {}
        return (T)persistentConfigurable;
    }
    
    public static Collection<PersistentConfigurable> getConfigValues() {
        return PersistentConfig.sConfigs.values();
    }
    
    public static boolean isGuidanceTutorial(final Context context) {
        final ABTestConfig$Cell cellForTest = getCellForTest(OfflineTutorial.class, context);
        return cellForTest == ABTestConfig$Cell.CELL_ONE || cellForTest == ABTestConfig$Cell.CELL_THREE || cellForTest == ABTestConfig$Cell.CELL_FOUR;
    }
    
    public static boolean isLaunchTutorial(final Context context) {
        final ABTestConfig$Cell cellForTest = getCellForTest(OfflineTutorial.class, context);
        return cellForTest == ABTestConfig$Cell.CELL_ONE || cellForTest == ABTestConfig$Cell.CELL_THREE || cellForTest == ABTestConfig$Cell.CELL_FIVE;
    }
    
    public static boolean isOnRampTest(final Context context) {
        return getCellForTest(OnRamp.class, context) == ABTestConfig$Cell.CELL_TWO;
    }
    
    public static void refresh() {
        final Iterator<PersistentConfigurable> iterator = PersistentConfig.sConfigs.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().refresh();
        }
    }
    
    public static void update(final Context context, final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        if (serviceAgent$ConfigurationAgentInterface == null) {
            if (Log.isLoggable()) {
                Log.w("PersistentConfig", "ConfigAgent is null. Returning without updating config.");
            }
        }
        else {
            final Iterator<PersistentConfigurable> iterator = PersistentConfig.sConfigs.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().update(context, serviceAgent$ConfigurationAgentInterface);
            }
        }
    }
}
