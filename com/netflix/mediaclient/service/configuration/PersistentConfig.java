// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import java.util.Iterator;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.persistent.PrefetchLolomoConfig;
import com.netflix.mediaclient.service.configuration.persistent.BrandLoveSurvey;
import com.netflix.mediaclient.service.configuration.persistent.Memento;
import com.netflix.mediaclient.service.configuration.persistent.PhoneOrientation;
import com.netflix.mediaclient.service.configuration.persistent.ContinueWatchingProgBar;
import com.netflix.mediaclient.service.configuration.persistent.PushNotifOptIn;
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
        PersistentConfig.mConfigs.put(PushNotifOptIn.class, new PushNotifOptIn());
        PersistentConfig.mConfigs.put(ContinueWatchingProgBar.class, new ContinueWatchingProgBar());
        PersistentConfig.mConfigs.put(PhoneOrientation.class, new PhoneOrientation());
        PersistentConfig.mConfigs.put(Memento.class, new Memento());
        PersistentConfig.mConfigs.put(BrandLoveSurvey.class, new BrandLoveSurvey());
        PersistentConfig.mConfigs.put(PrefetchLolomoConfig.class, new PrefetchLolomoConfig());
    }
    
    public static void delete(final Context context) {
        final Iterator<PersistentConfigurable> iterator = PersistentConfig.mConfigs.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().delete(context);
        }
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
    
    public static ABTestConfig$Cell getDisplayPageRefreshTestCell(final Context context) {
        return PersistentConfig.mConfigs.get(DisplayPageRefreshConfig.class).getCell(context, ABTestConfig$Cell.CELL_TWO);
    }
    
    public static ABTestConfig$Cell getMemento(final Context context) {
        return PersistentConfig.mConfigs.get(Memento.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getMotionBBTestCell(final Context context) {
        return PersistentConfig.mConfigs.get(MotionBB.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getPhoneOrientation(final Context context) {
        return PersistentConfig.mConfigs.get(PhoneOrientation.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getPrefetchLolomoConfig(final Context context) {
        return PersistentConfig.mConfigs.get(PrefetchLolomoConfig.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getPushNotificationOptIn(final Context context) {
        return PersistentConfig.mConfigs.get(PushNotifOptIn.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getVoiceSearchABTestCell(final Context context) {
        return PersistentConfig.mConfigs.get(VoiceSearch.class).getCell(context);
    }
    
    public static boolean inMementoTest(final Context context) {
        return getMemento(context).ordinal() == ABTestConfig$Cell.CELL_TWO.ordinal() || getMemento(context).ordinal() == ABTestConfig$Cell.CELL_THREE.ordinal();
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
