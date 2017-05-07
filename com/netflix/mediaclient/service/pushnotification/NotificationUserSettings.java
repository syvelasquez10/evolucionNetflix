// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import com.netflix.mediaclient.util.PreferenceUtils;
import org.json.JSONArray;
import android.content.Context;
import org.json.JSONException;
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
    
    static NotificationUserSettings load(final JSONObject jsonObject) throws JSONException {
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
    
    static Map<String, NotificationUserSettings> loadSettings(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "nf_push"
        //     2: ldc             "load Notification settings start..."
        //     4: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //     7: pop            
        //     8: aload_0        
        //     9: ldc             "notification_settings"
        //    11: aconst_null    
        //    12: invokestatic    com/netflix/mediaclient/util/PreferenceUtils.getStringPref:(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    15: astore_0       
        //    16: aload_0        
        //    17: ifnonnull       28
        //    20: new             Ljava/util/HashMap;
        //    23: dup            
        //    24: invokespecial   java/util/HashMap.<init>:()V
        //    27: areturn        
        //    28: new             Lorg/json/JSONArray;
        //    31: dup            
        //    32: aload_0        
        //    33: invokespecial   org/json/JSONArray.<init>:(Ljava/lang/String;)V
        //    36: astore_0       
        //    37: new             Ljava/util/HashMap;
        //    40: dup            
        //    41: aload_0        
        //    42: invokevirtual   org/json/JSONArray.length:()I
        //    45: invokespecial   java/util/HashMap.<init>:(I)V
        //    48: astore_2       
        //    49: iconst_0       
        //    50: istore_1       
        //    51: iload_1        
        //    52: aload_0        
        //    53: invokevirtual   org/json/JSONArray.length:()I
        //    56: if_icmpge       139
        //    59: aload_0        
        //    60: iload_1        
        //    61: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //    64: invokestatic    com/netflix/mediaclient/service/pushnotification/NotificationUserSettings.load:(Lorg/json/JSONObject;)Lcom/netflix/mediaclient/service/pushnotification/NotificationUserSettings;
        //    67: astore_3       
        //    68: ldc             "nf_push"
        //    70: iconst_3       
        //    71: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //    74: ifeq            102
        //    77: ldc             "nf_push"
        //    79: new             Ljava/lang/StringBuilder;
        //    82: dup            
        //    83: invokespecial   java/lang/StringBuilder.<init>:()V
        //    86: ldc             "User setttings found: "
        //    88: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    91: aload_3        
        //    92: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    95: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    98: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   101: pop            
        //   102: aload_2        
        //   103: aload_3        
        //   104: getfield        com/netflix/mediaclient/service/pushnotification/NotificationUserSettings.accountOwnerToken:Ljava/lang/String;
        //   107: aload_3        
        //   108: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   113: pop            
        //   114: iload_1        
        //   115: iconst_1       
        //   116: iadd           
        //   117: istore_1       
        //   118: goto            51
        //   121: astore_0       
        //   122: ldc             "nf_push"
        //   124: ldc             "Failed to load settings"
        //   126: aload_0        
        //   127: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   130: pop            
        //   131: new             Ljava/util/HashMap;
        //   134: dup            
        //   135: invokespecial   java/util/HashMap.<init>:()V
        //   138: areturn        
        //   139: ldc             "nf_push"
        //   141: ldc             "load Notification settings end."
        //   143: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   146: pop            
        //   147: aload_2        
        //   148: areturn        
        //   149: astore_0       
        //   150: goto            122
        //    Signature:
        //  (Landroid/content/Context;)Ljava/util/Map<Ljava/lang/String;Lcom/netflix/mediaclient/service/pushnotification/NotificationUserSettings;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  8      16     121    122    Ljava/lang/Throwable;
        //  20     28     121    122    Ljava/lang/Throwable;
        //  28     49     121    122    Ljava/lang/Throwable;
        //  51     102    149    153    Ljava/lang/Throwable;
        //  102    114    149    153    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0051:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
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
    
    private JSONObject toJson() throws JSONException {
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
