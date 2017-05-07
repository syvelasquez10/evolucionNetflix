// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import org.json.JSONArray;
import java.util.HashMap;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import java.util.Iterator;
import java.util.Map;

class NotificationUserSettings
{
    static final int NOTIFICATION_SOUND_PREFERENCE_DISABLED = 2;
    static final int NOTIFICATION_SOUND_PREFERENCE_ENABLED = 1;
    static final int NOTIFICATION_SOUND_PREFERENCE_NOT_SAVED = 0;
    private static String PARAM_ACCOUNT_OWNER_TOKEN;
    private static String PARAM_CURRENT_PROFILE_TOKEN;
    private static String PARAM_OLD_APP_VERSION;
    private static String PARAM_OPT_IN;
    private static String PARAM_OPT_IN_DISPLAYED;
    private static String PARAM_SOUND_ENABLED;
    private static String PARAM_TIMESTAMP;
    private static final String TAG = "nf_push";
    public String accountOwnerToken;
    public boolean current;
    public String currentProfileToken;
    public int oldAppVersion;
    public boolean optInDisplayed;
    public boolean optedIn;
    public int soundEnabled;
    public long timestamp;
    
    static {
        NotificationUserSettings.PARAM_ACCOUNT_OWNER_TOKEN = "userId";
        NotificationUserSettings.PARAM_OLD_APP_VERSION = "oldAppVersion";
        NotificationUserSettings.PARAM_OPT_IN = "optIn";
        NotificationUserSettings.PARAM_SOUND_ENABLED = "soundEnabled";
        NotificationUserSettings.PARAM_OPT_IN_DISPLAYED = "optInDisplayed";
        NotificationUserSettings.PARAM_TIMESTAMP = "ts";
        NotificationUserSettings.PARAM_CURRENT_PROFILE_TOKEN = "currentUserId";
    }
    
    NotificationUserSettings() {
        this.oldAppVersion = Integer.MIN_VALUE;
        this.soundEnabled = 0;
    }
    
    static NotificationUserSettings getCurrent(final Map<String, NotificationUserSettings> map) {
        for (final NotificationUserSettings notificationUserSettings : map.values()) {
            if (notificationUserSettings.current) {
                return notificationUserSettings;
            }
        }
        return null;
    }
    
    static NotificationUserSettings load(final JSONObject jsonObject) {
        final NotificationUserSettings notificationUserSettings = new NotificationUserSettings();
        notificationUserSettings.accountOwnerToken = jsonObject.getString(NotificationUserSettings.PARAM_ACCOUNT_OWNER_TOKEN);
        notificationUserSettings.optedIn = jsonObject.getBoolean(NotificationUserSettings.PARAM_OPT_IN);
        notificationUserSettings.soundEnabled = jsonObject.getInt(NotificationUserSettings.PARAM_SOUND_ENABLED);
        notificationUserSettings.oldAppVersion = jsonObject.getInt(NotificationUserSettings.PARAM_OLD_APP_VERSION);
        notificationUserSettings.timestamp = JsonUtils.getLong(jsonObject, NotificationUserSettings.PARAM_TIMESTAMP, 0L);
        if (jsonObject.has(NotificationUserSettings.PARAM_OPT_IN_DISPLAYED)) {
            notificationUserSettings.optInDisplayed = jsonObject.getBoolean(NotificationUserSettings.PARAM_OPT_IN_DISPLAYED);
        }
        else {
            notificationUserSettings.optInDisplayed = true;
        }
        notificationUserSettings.currentProfileToken = jsonObject.getString(NotificationUserSettings.PARAM_CURRENT_PROFILE_TOKEN);
        if (Log.isLoggable("nf_push", 3)) {
            Log.d("nf_push", "Loaded " + notificationUserSettings);
        }
        return notificationUserSettings;
    }
    
    static Map<String, NotificationUserSettings> loadSettings(final Context context) {
        Log.d("nf_push", "load Notification settings start...");
        HashMap hashMap;
        try {
            final String stringPref = PreferenceUtils.getStringPref(context, "notification_settings", null);
            if (stringPref == null) {
                return new HashMap<String, NotificationUserSettings>();
            }
            final JSONArray jsonArray = new JSONArray(stringPref);
            hashMap = new HashMap<String, NotificationUserSettings>(jsonArray.length());
            for (int i = 0; i < jsonArray.length(); ++i) {
                final NotificationUserSettings load = load(jsonArray.getJSONObject(i));
                if (Log.isLoggable("nf_push", 3)) {
                    Log.d("nf_push", "User setttings found: " + load);
                }
                hashMap.put(load.accountOwnerToken, load);
            }
        }
        catch (Throwable t) {
            Log.e("nf_push", "Failed to load settings", t);
            return new HashMap<String, NotificationUserSettings>();
        }
        Log.d("nf_push", "load Notification settings end.");
        return (Map<String, NotificationUserSettings>)hashMap;
    }
    
    static void saveSettings(final Context context, final Map<String, NotificationUserSettings> map) {
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray();
            final Iterator<NotificationUserSettings> iterator = map.values().iterator();
            while (iterator.hasNext()) {
                jsonArray.put((Object)iterator.next().toJson());
            }
        }
        catch (Throwable t) {
            Log.e("nf_push", "Failed to save settings", t);
            return;
        }
        final String string = jsonArray.toString();
        if (Log.isLoggable("nf_push", 3)) {
            Log.d("nf_push", "Saving " + string);
        }
        PreferenceUtils.putStringPref(context, "notification_settings", string);
    }
    
    private JSONObject toJson() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put(NotificationUserSettings.PARAM_ACCOUNT_OWNER_TOKEN, (Object)this.accountOwnerToken);
        jsonObject.put(NotificationUserSettings.PARAM_OLD_APP_VERSION, this.oldAppVersion);
        jsonObject.put(NotificationUserSettings.PARAM_OPT_IN, this.optedIn);
        jsonObject.put(NotificationUserSettings.PARAM_SOUND_ENABLED, this.soundEnabled);
        jsonObject.put(NotificationUserSettings.PARAM_OPT_IN_DISPLAYED, this.optInDisplayed);
        if (this.timestamp <= 0L) {
            this.timestamp = System.currentTimeMillis();
        }
        jsonObject.put(NotificationUserSettings.PARAM_TIMESTAMP, this.timestamp);
        jsonObject.put(NotificationUserSettings.PARAM_CURRENT_PROFILE_TOKEN, (Object)this.currentProfileToken);
        return jsonObject;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof NotificationUserSettings)) {
                return false;
            }
            final NotificationUserSettings notificationUserSettings = (NotificationUserSettings)o;
            if (this.accountOwnerToken == null) {
                if (notificationUserSettings.accountOwnerToken != null) {
                    return false;
                }
            }
            else if (!this.accountOwnerToken.equals(notificationUserSettings.accountOwnerToken)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.accountOwnerToken == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.accountOwnerToken.hashCode();
        }
        return hashCode + 31;
    }
    
    @Override
    public String toString() {
        return "NotificationUserSettings [userId=" + this.accountOwnerToken + ", current=" + this.current + ", optedIn=" + this.optedIn + ", optInDisplayed=" + this.optInDisplayed + ", oldAppVersion=" + this.oldAppVersion + ", soundEnabled=" + this.soundEnabled + ", timestamp=" + this.timestamp + ", currentUserId=" + this.currentProfileToken + "]";
    }
}
