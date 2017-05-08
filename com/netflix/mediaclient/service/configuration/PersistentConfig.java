// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.persistent.PhoneOrientation;
import com.netflix.mediaclient.service.configuration.persistent.PushNotifOptIn;
import com.netflix.mediaclient.service.configuration.persistent.MotionBB;
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
        PersistentConfig.mConfigs.put(MotionBB.class, new MotionBB());
        PersistentConfig.mConfigs.put(PushNotifOptIn.class, new PushNotifOptIn());
        PersistentConfig.mConfigs.put(PhoneOrientation.class, new PhoneOrientation());
    }
    
    public static ABTestConfig$Cell getCoppola1ABTestCell(final Context context) {
        return PersistentConfig.mConfigs.get(CoppolaOne.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getMotionBBTestCell(final Context context) {
        return PersistentConfig.mConfigs.get(MotionBB.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getPhoneOrientation(final Context context) {
        return PersistentConfig.mConfigs.get(PhoneOrientation.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getPushNotificationOptIn(final Context context) {
        return PersistentConfig.mConfigs.get(PushNotifOptIn.class).getCell(context);
    }
    
    public static ABTestConfig$Cell getVoiceSearchABTestCell(final Context context) {
        return PersistentConfig.mConfigs.get(VoiceSearch.class).getCell(context);
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
