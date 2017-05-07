// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.android;

import com.netflix.mediaclient.util.StringUtils;
import java.util.Iterator;
import org.json.JSONException;
import com.netflix.mediaclient.javabridge.ui.Callback;
import org.json.JSONArray;
import com.netflix.mediaclient.event.nrdp.registration.DeactivatedEvent;
import com.netflix.mediaclient.event.nrdp.registration.ActivateEvent;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.event.nrdp.BindEvent;
import com.netflix.mediaclient.javabridge.ui.android.registration.SelectedAccountCompleteCommand;
import com.netflix.mediaclient.javabridge.ui.android.registration.CreateAccountCompleteCommand;
import com.netflix.mediaclient.event.CallbackEvent;
import com.netflix.mediaclient.javabridge.ui.android.registration.DeactivateCompleteCommand;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import java.util.ArrayList;
import com.netflix.mediaclient.javabridge.Bridge;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.javabridge.ui.DeviceAccount;
import java.util.List;
import com.netflix.mediaclient.javabridge.ui.Registration;

public final class NativeRegistration extends NativeNrdObject implements Registration
{
    public static final String CMD_RESULT_EVENT_activateComplete = "activateComplete";
    public static final String CMD_RESULT_EVENT_bindComplete = "bindComplete";
    public static final String CMD_RESULT_EVENT_createAccountComplete = "createdAccount";
    public static final String CMD_RESULT_EVENT_deactivateComplete = "deactivate";
    public static final String CMD_RESULT_EVENT_selectAccountComplete = "selectedAccount";
    public static final String METHOD_createDeviceAccount = "createDeviceAccount";
    public static final String METHOD_deactivate = "deactivate";
    public static final String METHOD_deactivateAll = "deactivateAll";
    public static final String METHOD_emailActivate = "emailActivate";
    public static final String METHOD_esnMigration = "esnMigration";
    public static final String METHOD_getDeviceTokens = "getDeviceTokens";
    public static final String METHOD_massDeactivationCheck = "massDeactivationCheck";
    public static final String METHOD_ping = "ping";
    public static final String METHOD_selectDeviceAccount = "selectDeviceAccount";
    public static final String METHOD_setActivationTokens = "setActivationTokens";
    public static final String METHOD_setUILanguages = "setUILanguages";
    public static final String METHOD_tokenActivate = "tokenActivate";
    public static final String METHOD_unselectDeviceAccount = "unselectDeviceAccount";
    private static final String TAG = "nf_reg";
    private List<DeviceAccount> accounts;
    private ActivationTokens activationTokens;
    private String currentDeviceAccount;
    private boolean registered;
    
    public NativeRegistration(final Bridge bridge) {
        super(bridge);
        this.accounts = new ArrayList<DeviceAccount>();
    }
    
    private int handleEvent(final JSONObject jsonObject) {
        final JSONObject jsonObject2 = this.getJSONObject(jsonObject, "data", null);
        final String string = this.getString(jsonObject, "name", null);
        if (jsonObject2 != null && jsonObject2.has("idx")) {
            Log.w("nf_reg", "handleEvent data !null");
            if ("deactivate".equals(string)) {
                return this.handleCallback(new DeactivateCompleteCommand(jsonObject2));
            }
            if ("createdAccount".equals(string)) {
                return this.handleCallback(new CreateAccountCompleteCommand(jsonObject2));
            }
            if ("selectedAccount".equals(string)) {
                return this.handleCallback(new SelectedAccountCompleteCommand(jsonObject2));
            }
        }
        if (jsonObject.has("name")) {
            return this.handleEventByName(jsonObject);
        }
        Log.w("nf_reg", "Nobody to handle!");
        return 1;
    }
    
    private int handleEventByName(final JSONObject jsonObject) {
        final JSONObject jsonObject2 = this.getJSONObject(jsonObject, "data", null);
        final String string = this.getString(jsonObject, "name", null);
        if ((!"bind".equals(string) && !"activate".endsWith(string)) || 1 != this.handleNccpEvent(string, jsonObject2)) {
            Log.d("nf_reg", "Received a event: " + string);
            if ("bind".equals(string)) {
                return this.handleListener(string, new BindEvent(jsonObject2));
            }
            if ("activateComplete".equals(string) || "activate".equals(string)) {
                return this.handleListener(string, new ActivateEvent(jsonObject2));
            }
            if ("deactivated".equals(string)) {
                return this.handleListener(string, new DeactivatedEvent(jsonObject2));
            }
            if (Log.isLoggable("nf_reg", 5)) {
                Log.w("nf_reg", "Nobody to handle by name " + string);
                return 1;
            }
        }
        return 1;
    }
    
    private int handlePropertyUpdate(final JSONObject jsonObject) {
        int i = 0;
        final JSONObject jsonObject2 = this.getJSONObject(jsonObject, "properties", null);
        if (jsonObject2 == null) {
            Log.w("nf_reg", "handlePropertyUpdate:: properties does not exist");
            return 0;
        }
        if (jsonObject2.has("registered")) {
            this.registered = jsonObject2.getBoolean("registered");
        }
        if (jsonObject2.has("currentDeviceAccount")) {
            this.currentDeviceAccount = this.getString(jsonObject2, "currentDeviceAccount", null);
            if (this.currentDeviceAccount != null && jsonObject2.has("registered")) {
                this.registered = this.getBoolean(jsonObject2, "registered", false);
            }
        }
        synchronized (this.accounts) {
            if (jsonObject2.has("deviceAccounts")) {
                this.accounts.clear();
                for (JSONArray jsonArray = this.getJSONArray(jsonObject2, "deviceAccounts"); i < jsonArray.length(); ++i) {
                    final DeviceAccount deviceAccount = new DeviceAccount(jsonArray.getJSONObject(i));
                    if (Log.isLoggable("nf_reg", 3)) {
                        Log.d("nf_reg", "Account " + i + ": " + deviceAccount);
                    }
                    this.accounts.add(deviceAccount);
                }
            }
            // monitorexit(this.accounts)
            if (jsonObject2.has("appResetRequired")) {
                Log.d("nf_reg", "handlePropertyUpdate:: appResetRequired");
                this.handleListener("appResetRequired", null);
            }
            return 1;
        }
    }
    
    @Override
    public void createDeviceAccount(final Callback callback) {
        this.invokeMethodWithCallback("registration", "createDeviceAccount", null, callback);
    }
    
    @Override
    public void deactivate(final DeviceAccount deviceAccount, final Callback callback) {
        if (deviceAccount == null || deviceAccount.getAccountKey() == null) {
            throw new IllegalArgumentException("Dak is null!");
        }
        this.bridge.getNrdp().getStorage().clear(deviceAccount.getAccountKey());
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("key", (Object)deviceAccount.getAccountKey());
            this.invokeMethodWithCallback("registration", "deactivate", jsonObject, callback);
        }
        catch (JSONException ex) {
            Log.e("nf_reg", "Deactivate device account failed because of ", (Throwable)ex);
        }
    }
    
    @Override
    public void deactivateAll(final Callback callback) {
        this.bridge.getNrdp().getDevice().factoryReset(callback);
    }
    
    @Override
    public void emailActivate(final String s, final String s2) {
        if (this.currentDeviceAccount != null) {
            try {
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("email", (Object)s);
                jsonObject.put("passwd", (Object)s2);
                this.bridge.getNrdProxy().invokeMethod("registration", "emailActivate", jsonObject.toString());
                return;
            }
            catch (JSONException ex) {
                Log.e("nf_reg", "emailActivate failed with ", (Throwable)ex);
                return;
            }
        }
        Log.e("nf_reg", "Failed with token activate. Current device account is NOT set!");
    }
    
    @Override
    public void esnMigration() {
        this.bridge.getNrdProxy().invokeMethod("registration", "esnMigration", null);
    }
    
    @Override
    public ActivationTokens getActivationTokens() {
        return this.activationTokens;
    }
    
    @Override
    public DeviceAccount getCurrentDeviceAccount() {
        final String currentDeviceAccount = this.currentDeviceAccount;
        if (currentDeviceAccount != null && !"".equals(currentDeviceAccount.trim())) {
            for (final DeviceAccount deviceAccount : this.accounts) {
                if (currentDeviceAccount.equals(deviceAccount.getAccountKey())) {
                    final DeviceAccount deviceAccount2 = deviceAccount;
                    if (Log.isLoggable("nf_reg", 3)) {
                        Log.d("nf_reg", "Selected device account " + deviceAccount);
                        return deviceAccount;
                    }
                    return deviceAccount2;
                }
            }
            if (Log.isLoggable("nf_reg", 5)) {
                Log.w("nf_reg", "Account is not found for key " + currentDeviceAccount);
            }
            return null;
        }
        Log.d("nf_reg", "Device account is NOT selected!");
        return null;
    }
    
    @Override
    public DeviceAccount[] getDeviceAccounts() {
        return this.accounts.toArray(new DeviceAccount[this.accounts.size()]);
    }
    
    @Override
    public void getDeviceTokens() {
        this.bridge.getNrdProxy().invokeMethod("registration", "getDeviceTokens", null);
    }
    
    @Override
    public String getName() {
        return "registration";
    }
    
    @Override
    public String getPath() {
        return "nrdp.registration";
    }
    
    @Override
    public String[] getUILanguages() {
        final DeviceAccount currentDeviceAccount = this.getCurrentDeviceAccount();
        if (currentDeviceAccount != null && currentDeviceAccount.getLanguages() != null) {
            return currentDeviceAccount.getLanguages();
        }
        return this.bridge.getNrdp().getDevice().getUILanguages();
    }
    
    @Override
    protected int handleNccpEvent(final String s, final JSONObject jsonObject) {
        if (Log.isLoggable("nf_reg", 3)) {
            Log.d("nf_reg", "NCCP event " + s);
        }
        if (jsonObject.has("origin") && jsonObject.getString("origin").equalsIgnoreCase("complete")) {
            Log.d("nf_reg", "NCCP event with origin equal complete. Ignore.");
            return 1;
        }
        Log.d("nf_reg", "NCCP event: handle by implementation");
        return 0;
    }
    
    @Override
    public boolean isRegistered() {
        return this.registered;
    }
    
    @Override
    public void massDeactivationCheck() {
        this.bridge.getNrdProxy().invokeMethod("registration", "massDeactivationCheck", null);
    }
    
    @Override
    public void ping() {
        this.bridge.getNrdProxy().invokeMethod("registration", "ping", null);
    }
    
    @Override
    public int processUpdate(final JSONObject jsonObject) {
        try {
            final String string = this.getString(jsonObject, "type", null);
            if (Log.isLoggable("nf_reg", 3)) {
                Log.d("nf_reg", "processUpdate: handle type " + string);
            }
            if ("PropertyUpdate".equalsIgnoreCase(string)) {
                if (jsonObject != null && Log.isLoggable("nf_reg", 3)) {
                    Log.d("nf_reg", "processUpdate: handle prop update " + jsonObject.toString());
                }
                return this.handlePropertyUpdate(jsonObject);
            }
            Log.d("nf_reg", "processUpdate: handle event");
            return this.handleEvent(jsonObject);
        }
        catch (Exception ex) {
            Log.e("nf_reg", "Failed with JSON", ex);
            return 1;
        }
    }
    
    @Override
    public void selectDeviceAccount(final DeviceAccount deviceAccount, final Callback callback) {
        if (deviceAccount == null || deviceAccount.getAccountKey() == null) {
            throw new IllegalArgumentException("Dak is null!");
        }
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("key", (Object)deviceAccount.getAccountKey());
            this.invokeMethodWithCallback("registration", "selectDeviceAccount", jsonObject, callback);
        }
        catch (JSONException ex) {
            Log.e("nf_reg", "Create device account failed because of ", (Throwable)ex);
        }
    }
    
    @Override
    public void setActivationTokens(final ActivationTokens activationTokens) {
        this.activationTokens = activationTokens;
        if (activationTokens == null) {
            Log.w("nf_reg", "setActivationTokens:: tokens are null!");
            return;
        }
        this.bridge.getNrdProxy().invokeMethod("registration", "setActivationTokens", activationTokens.toJSON().toString());
    }
    
    @Override
    public void setUILanguages(final String[] languages) {
        final DeviceAccount currentDeviceAccount = this.getCurrentDeviceAccount();
        if (currentDeviceAccount == null) {
            Log.w("nf_reg", "Current account is NULL! UI languages can not be set!");
            return;
        }
        currentDeviceAccount.setLanguages(languages);
        this.bridge.getNrdProxy().invokeMethod("registration", "setUILanguages", "{languages:" + StringUtils.joinArray(languages) + "}");
    }
    
    @Override
    public void tokenActivate(final ActivationTokens activationTokens) {
        if (this.currentDeviceAccount != null) {
            this.bridge.getNrdProxy().invokeMethod("registration", "tokenActivate", activationTokens.toJSON().toString());
            return;
        }
        Log.e("nf_reg", "Failed with token activate. Current device account is NOT set!");
    }
    
    @Override
    public void unselectDeviceAccount(final Callback callback) {
        this.invokeMethodWithCallback("registration", "unselectDeviceAccount", null, callback);
    }
}
