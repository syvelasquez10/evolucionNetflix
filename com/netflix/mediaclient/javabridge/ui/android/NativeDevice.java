// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.android;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.repository.UserLocale;
import java.util.Locale;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.javabridge.ui.Callback;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.CallbackEvent;
import com.netflix.mediaclient.javabridge.ui.android.device.FactoryResetCompleteCommand;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.Bridge;
import com.netflix.mediaclient.javabridge.ui.Device;

public final class NativeDevice extends NativeNrdObject implements Device
{
    public static final String METHOD_factoryReset = "factoryReset";
    public static final String PROPERTY_UILanguages = "UILanguages";
    public static final String PROPERTY_UIVersion = "UIVersion";
    public static final String SDK_VERSION = "12.1";
    private static final String TAG = "nf-bridge";
    private String[] uiLanguages;
    private String uiVersion;
    
    public NativeDevice(final Bridge bridge) {
        super(bridge);
    }
    
    private int handleEvent(final JSONObject jsonObject) {
        final JSONObject jsonObject2 = this.getJSONObject(jsonObject, "data", null);
        final String string = this.getString(jsonObject, "name", null);
        if (jsonObject2 != null && jsonObject2.has("idx") && "factoryReset".equalsIgnoreCase(string)) {
            return this.handleCallback(new FactoryResetCompleteCommand(jsonObject2));
        }
        return 1;
    }
    
    private int handlePropertyUpdate(final JSONObject jsonObject) {
        if (this.getJSONObject(jsonObject, "properties", null) == null) {
            Log.w("nf-bridge", "handlePropertyUpdate:: properties does not exist");
            return 0;
        }
        Log.d("nf-bridge", "Not updating properties for now. That may change");
        return 1;
    }
    
    @Override
    public void factoryReset(final Callback callback) {
        this.invokeMethodWithCallback("device", "factoryReset", null, callback);
    }
    
    @Override
    public String getCertificationVersion() {
        final String trim = this.getSoftwareVersion().trim();
        if (Log.isLoggable()) {
            Log.d("nf-bridge", "SV: " + trim);
        }
        final int index = trim.indexOf(" ");
        String substring = trim;
        if (index > 0) {
            substring = trim.substring(0, index);
        }
        if (Log.isLoggable()) {
            Log.d("nf-bridge", "CV: " + substring);
        }
        return substring;
    }
    
    @Override
    public String getDeviceModel() {
        final EsnProvider esnProvider = this.bridge.getEsnProvider();
        if (esnProvider == null) {
            return "N/A";
        }
        return esnProvider.getModelId();
    }
    
    @Override
    public String getESN() {
        final EsnProvider esnProvider = this.bridge.getEsnProvider();
        if (esnProvider == null) {
            return "N/A";
        }
        return esnProvider.getEsn();
    }
    
    @Override
    public String getESNPrefix() {
        return EsnProvider.ESN_PREFIX;
    }
    
    @Override
    public String getFriendlyName() {
        return "Android";
    }
    
    @Override
    public String getLanguage() {
        String language;
        if (this.bridge == null) {
            Log.d("nf-bridge", "bridge is gone, probably destroyed. Ignoring");
            language = Locale.ENGLISH.getLanguage();
        }
        else {
            final Locale deviceLocale = this.bridge.getDeviceLocale();
            String userLocale = "en";
            if (deviceLocale != null) {
                userLocale = UserLocale.toUserLocale(deviceLocale);
            }
            else {
                Log.e("nf-bridge", "Device locale can not be null! Default to 'en'.");
            }
            language = userLocale;
            if (Log.isLoggable()) {
                Log.d("nf-bridge", "Current device locale as raw user locale: " + userLocale);
                return userLocale;
            }
        }
        return language;
    }
    
    @Override
    public String getName() {
        return "device";
    }
    
    @Override
    public String getPath() {
        return "nrdp.device";
    }
    
    @Override
    public String getSDKVersion() {
        return "12.1";
    }
    
    @Override
    public String getSoftwareVersion() {
        String softwareVersion;
        if ((softwareVersion = this.bridge.getSoftwareVersion()) == null) {
            softwareVersion = "N/A";
        }
        return softwareVersion;
    }
    
    @Override
    public String[] getUILanguages() {
        return this.uiLanguages;
    }
    
    @Override
    public String getUIVersion() {
        return this.uiVersion;
    }
    
    @Override
    public int processUpdate(final JSONObject jsonObject) {
        try {
            final String string = this.getString(jsonObject, "type", null);
            if (Log.isLoggable()) {
                Log.d("nf-bridge", "processUpdate: handle type " + string);
            }
            if ("PropertyUpdate".equalsIgnoreCase(string)) {
                if (jsonObject != null && Log.isLoggable()) {
                    Log.d("nf-bridge", "processUpdate: handle prop update " + jsonObject.toString());
                }
                return this.handlePropertyUpdate(jsonObject);
            }
            Log.d("nf-bridge", "processUpdate: handle event");
            return this.handleEvent(jsonObject);
        }
        catch (Exception ex) {
            Log.e("nf-bridge", "Failed with JSON", ex);
            return 1;
        }
    }
    
    @Override
    public void setUILanguages(final String[] uiLanguages) {
        this.uiLanguages = uiLanguages;
        this.bridge.getNrdProxy().setProperty("device", "UILanguages", StringUtils.joinArray(uiLanguages));
    }
    
    @Override
    public void setUIVersion(final String uiVersion) {
        this.uiVersion = uiVersion;
        this.bridge.getNrdProxy().setProperty("device", "UIVersion", uiVersion);
    }
}
