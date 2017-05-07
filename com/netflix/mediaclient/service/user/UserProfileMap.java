// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.util.StringUtils;
import java.util.Iterator;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import android.util.Pair;
import com.netflix.mediaclient.util.PreferenceUtils;
import org.json.JSONObject;
import android.content.Context;

class UserProfileMap
{
    private static final String CURRENT_ACCOUNT_MAPKEY = "currentAcc";
    private static final String PRIMARY_ACCOUNT_MAPKEY = "primaryAcc";
    private static final String TAG = "nf_service_useragentproilemap";
    private Context mContext;
    private JSONObject mEsnMigrationFlags;
    private boolean mMapChanaged;
    private JSONObject mProfileMap;
    
    UserProfileMap(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   java/lang/Object.<init>:()V
        //     4: aload_0        
        //     5: aload_1        
        //     6: putfield        com/netflix/mediaclient/service/user/UserProfileMap.mContext:Landroid/content/Context;
        //     9: aload_0        
        //    10: getfield        com/netflix/mediaclient/service/user/UserProfileMap.mContext:Landroid/content/Context;
        //    13: ldc             "useragent_profilemap"
        //    15: ldc             ""
        //    17: invokestatic    com/netflix/mediaclient/util/PreferenceUtils.getStringPref:(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    20: astore_1       
        //    21: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    24: ifeq            52
        //    27: ldc             "nf_service_useragentproilemap"
        //    29: new             Ljava/lang/StringBuilder;
        //    32: dup            
        //    33: invokespecial   java/lang/StringBuilder.<init>:()V
        //    36: ldc             "UserProfileMap json "
        //    38: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    41: aload_1        
        //    42: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    45: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    48: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    51: pop            
        //    52: aload_0        
        //    53: new             Lorg/json/JSONObject;
        //    56: dup            
        //    57: aload_1        
        //    58: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    61: putfield        com/netflix/mediaclient/service/user/UserProfileMap.mProfileMap:Lorg/json/JSONObject;
        //    64: aload_0        
        //    65: getfield        com/netflix/mediaclient/service/user/UserProfileMap.mContext:Landroid/content/Context;
        //    68: ldc             "useragent_esnmigration_flags"
        //    70: ldc             ""
        //    72: invokestatic    com/netflix/mediaclient/util/PreferenceUtils.getStringPref:(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    75: astore_1       
        //    76: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    79: ifeq            107
        //    82: ldc             "nf_service_useragentproilemap"
        //    84: new             Ljava/lang/StringBuilder;
        //    87: dup            
        //    88: invokespecial   java/lang/StringBuilder.<init>:()V
        //    91: ldc             "mEsnMigrationFlags json "
        //    93: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    96: aload_1        
        //    97: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   100: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   103: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   106: pop            
        //   107: aload_0        
        //   108: new             Lorg/json/JSONObject;
        //   111: dup            
        //   112: aload_1        
        //   113: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   116: putfield        com/netflix/mediaclient/service/user/UserProfileMap.mEsnMigrationFlags:Lorg/json/JSONObject;
        //   119: return         
        //   120: astore_1       
        //   121: aload_0        
        //   122: new             Lorg/json/JSONObject;
        //   125: dup            
        //   126: invokespecial   org/json/JSONObject.<init>:()V
        //   129: putfield        com/netflix/mediaclient/service/user/UserProfileMap.mProfileMap:Lorg/json/JSONObject;
        //   132: goto            64
        //   135: astore_1       
        //   136: aload_0        
        //   137: new             Lorg/json/JSONObject;
        //   140: dup            
        //   141: invokespecial   org/json/JSONObject.<init>:()V
        //   144: putfield        com/netflix/mediaclient/service/user/UserProfileMap.mEsnMigrationFlags:Lorg/json/JSONObject;
        //   147: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  52     64     120    135    Lorg/json/JSONException;
        //  107    119    135    148    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0107:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    
    void clear() {
        this.mProfileMap = new JSONObject();
        PreferenceUtils.putStringPref(this.mContext, "useragent_profilemap", this.mProfileMap.toString());
        this.mEsnMigrationFlags = new JSONObject();
        PreferenceUtils.putStringPref(this.mContext, "useragent_esnmigration_flags", this.mEsnMigrationFlags.toString());
    }
    
    void clearEsnMigrationForCurrentAccount() {
        final String optString = this.mProfileMap.optString("currentAcc");
        if (this.mEsnMigrationFlags.optBoolean(optString, false)) {
            this.mEsnMigrationFlags.remove(optString);
            PreferenceUtils.putStringPref(this.mContext, "useragent_esnmigration_flags", this.mEsnMigrationFlags.toString());
        }
    }
    
    String getAcccountKeyFromProfileId(final String s) {
        return this.mProfileMap.optString(s);
    }
    
    Pair<String, String> getCurrentProfileIdAndKey() {
        final String optString = this.mProfileMap.optString("currentAcc");
        final String optString2 = this.mProfileMap.optString(optString);
        String s = optString;
        if ("primaryAcc".equals(optString)) {
            s = "";
        }
        return (Pair<String, String>)Pair.create((Object)s, (Object)optString2);
    }
    
    String getPrimaryAccountKey() {
        return this.mProfileMap.optString("primaryAcc");
    }
    
    boolean isCurrentAccountNeedEsnMigration() {
        return this.mEsnMigrationFlags.optBoolean(this.mProfileMap.optString("currentAcc"), false);
    }
    
    void markAllAccountForEsnMigration() {
        if (Log.isLoggable()) {
            Log.d("nf_service_useragentproilemap", "markAllAccountForEsnMigration");
        }
        if (this.mProfileMap != null) {
            final Iterator keys = this.mProfileMap.keys();
            boolean b = false;
        Label_0084_Outer:
            while (keys.hasNext()) {
                final String s = keys.next();
                if (!"currentAcc".equals(s) && !"primaryAcc".equals(s)) {
                    while (true) {
                        try {
                            this.mEsnMigrationFlags.putOpt(s, (Object)true);
                            b = true;
                            continue Label_0084_Outer;
                        }
                        catch (JSONException ex) {
                            continue;
                        }
                        break;
                    }
                    break;
                }
            }
            if (b) {
                if (Log.isLoggable()) {
                    Log.d("nf_service_useragentproilemap", "markAllAccountForEsnMigration " + this.mEsnMigrationFlags);
                }
                PreferenceUtils.putStringPref(this.mContext, "useragent_esnmigration_flags", this.mEsnMigrationFlags.toString());
            }
        }
    }
    
    void setCurrentAccount(final String s, final String s2) {
        String s3 = s;
        if (StringUtils.isEmpty(s)) {
            s3 = "primaryAcc";
        }
        while (true) {
            try {
                this.mProfileMap.putOpt("currentAcc", (Object)s3);
                if (StringUtils.isEmpty(s2)) {
                    this.mProfileMap.remove(s3);
                }
                else {
                    this.mProfileMap.putOpt(s3, (Object)s2);
                }
                this.mMapChanaged = true;
                if (this.mMapChanaged) {
                    PreferenceUtils.putStringPref(this.mContext, "useragent_profilemap", this.mProfileMap.toString());
                }
            }
            catch (JSONException ex) {
                this.mMapChanaged = false;
                continue;
            }
            break;
        }
    }
}
