// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import java.util.Iterator;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.persistent.AimLowTextPlaceholderConfig;
import com.netflix.mediaclient.service.configuration.persistent.DPPrefetchABTestConfig;
import com.netflix.mediaclient.service.configuration.persistent.PrefetchLolomoConfig;
import com.netflix.mediaclient.service.configuration.persistent.BrandLoveSurvey;
import com.netflix.mediaclient.service.configuration.persistent.OfflineTutorial;
import com.netflix.mediaclient.service.configuration.persistent.OnRamp;
import com.netflix.mediaclient.service.configuration.persistent.Memento2;
import com.netflix.mediaclient.service.configuration.persistent.Memento;
import com.netflix.mediaclient.service.configuration.persistent.PhoneOrientation;
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
    private static HashMap<Class<? extends PersistentConfigurable>, PersistentConfigurable> mConfigs;
    
    static {
        (PersistentConfig.mConfigs = new HashMap<Class<? extends PersistentConfigurable>, PersistentConfigurable>()).put(VoiceSearch.class, new VoiceSearch());
        PersistentConfig.mConfigs.put(CoppolaOne.class, new CoppolaOne());
        PersistentConfig.mConfigs.put(CoppolaTwo.class, new CoppolaTwo());
        PersistentConfig.mConfigs.put(MotionBB.class, new MotionBB());
        PersistentConfig.mConfigs.put(DisplayPageRefreshConfig.class, new DisplayPageRefreshConfig());
        PersistentConfig.mConfigs.put(ContinueWatchingProgBar.class, new ContinueWatchingProgBar());
        PersistentConfig.mConfigs.put(PhoneOrientation.class, new PhoneOrientation());
        PersistentConfig.mConfigs.put(Memento.class, new Memento());
        PersistentConfig.mConfigs.put(Memento2.class, new Memento2());
        PersistentConfig.mConfigs.put(OnRamp.class, new OnRamp());
        PersistentConfig.mConfigs.put(OfflineTutorial.class, new OfflineTutorial());
        PersistentConfig.mConfigs.put(BrandLoveSurvey.class, new BrandLoveSurvey());
        PersistentConfig.mConfigs.put(PrefetchLolomoConfig.class, new PrefetchLolomoConfig());
        PersistentConfig.mConfigs.put(DPPrefetchABTestConfig.class, new DPPrefetchABTestConfig());
        PersistentConfig.mConfigs.put(AimLowTextPlaceholderConfig.class, new AimLowTextPlaceholderConfig());
    }
    
    public static void delete(final Context context) {
        final Iterator<PersistentConfigurable> iterator = PersistentConfig.mConfigs.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().delete(context);
        }
    }
    
    public static ABTestConfig$Cell getAimLowTextPlaceholderConfig(final Context context) {
        return PersistentConfig.mConfigs.get(AimLowTextPlaceholderConfig.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getBrandLoveSurveyTestCell(final Context context) {
        return PersistentConfig.mConfigs.get(BrandLoveSurvey.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getCWProgressBar(final Context context) {
        return PersistentConfig.mConfigs.get(ContinueWatchingProgBar.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getCoppola1ABTestCell(final Context context) {
        return PersistentConfig.mConfigs.get(CoppolaOne.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getCoppola2ABTestCell(final Context context) {
        return PersistentConfig.mConfigs.get(CoppolaTwo.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getDPPrefetchABTestConfig(final Context context) {
        return PersistentConfig.mConfigs.get(DPPrefetchABTestConfig.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getDisplayPageRefreshTestCell(final Context context) {
        return PersistentConfig.mConfigs.get(DisplayPageRefreshConfig.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getMemento(final Context context) {
        return PersistentConfig.mConfigs.get(Memento.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getMemento2(final Context context) {
        return PersistentConfig.mConfigs.get(Memento2.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getMotionBBTestCell(final Context context) {
        return PersistentConfig.mConfigs.get(MotionBB.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getOfflineTutorial(final Context context) {
        return PersistentConfig.mConfigs.get(OfflineTutorial.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getPhoneOrientation(final Context context) {
        return PersistentConfig.mConfigs.get(PhoneOrientation.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getPrefetchLolomoConfig(final Context context) {
        return PersistentConfig.mConfigs.get(PrefetchLolomoConfig.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getVoiceSearchABTestCell(final Context context) {
        return PersistentConfig.mConfigs.get(VoiceSearch.class).getCell(context);
    }
    
    public static boolean inAnyMementoTest(final Context context) {
        return getMemento(context) == ABTestConfig$Cell.CELL_TWO || getMemento(context) == ABTestConfig$Cell.CELL_THREE || getMemento2(context) == ABTestConfig$Cell.CELL_TWO || getMemento2(context) == ABTestConfig$Cell.CELL_THREE;
    }
    
    public static boolean inMemento2Test(final Context context) {
        return getMemento2(context) == ABTestConfig$Cell.CELL_TWO || getMemento2(context) == ABTestConfig$Cell.CELL_THREE;
    }
    
    public static boolean inMementoTest(final Context context) {
        return getMemento(context) == ABTestConfig$Cell.CELL_TWO || getMemento(context) == ABTestConfig$Cell.CELL_THREE;
    }
    
    public static boolean isGuidanceTutorial(final Context context) {
        return getOfflineTutorial(context) == ABTestConfig$Cell.CELL_ONE || getOfflineTutorial(context) == ABTestConfig$Cell.CELL_THREE || getOfflineTutorial(context) == ABTestConfig$Cell.CELL_FOUR;
    }
    
    public static boolean isLaunchTutorial(final Context context) {
        return getOfflineTutorial(context) == ABTestConfig$Cell.CELL_ONE || getOfflineTutorial(context) == ABTestConfig$Cell.CELL_THREE || getOfflineTutorial(context) == ABTestConfig$Cell.CELL_FIVE;
    }
    
    public static boolean isOnRampTest(final Context context) {
        return PersistentConfig.mConfigs.get(OnRamp.class).getCell(context) == ABTestConfig$Cell.CELL_TWO;
    }
    
    public static void refresh() {
        final Iterator<PersistentConfigurable> iterator = PersistentConfig.mConfigs.values().iterator();
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
            final Iterator<PersistentConfigurable> iterator = PersistentConfig.mConfigs.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().update(context, serviceAgent$ConfigurationAgentInterface);
            }
        }
    }
}
