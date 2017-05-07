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
        //    21: ldc             "nf_service_useragentproilemap"
        //    23: iconst_3       
        //    24: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //    27: ifeq            55
        //    30: ldc             "nf_service_useragentproilemap"
        //    32: new             Ljava/lang/StringBuilder;
        //    35: dup            
        //    36: invokespecial   java/lang/StringBuilder.<init>:()V
        //    39: ldc             "UserProfileMap json "
        //    41: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    44: aload_1        
        //    45: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    48: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    51: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    54: pop            
        //    55: aload_0        
        //    56: new             Lorg/json/JSONObject;
        //    59: dup            
        //    60: aload_1        
        //    61: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    64: putfield        com/netflix/mediaclient/service/user/UserProfileMap.mProfileMap:Lorg/json/JSONObject;
        //    67: aload_0        
        //    68: getfield        com/netflix/mediaclient/service/user/UserProfileMap.mContext:Landroid/content/Context;
        //    71: ldc             "useragent_esnmigration_flags"
        //    73: ldc             ""
        //    75: invokestatic    com/netflix/mediaclient/util/PreferenceUtils.getStringPref:(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    78: astore_1       
        //    79: ldc             "nf_service_useragentproilemap"
        //    81: iconst_3       
        //    82: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //    85: ifeq            113
        //    88: ldc             "nf_service_useragentproilemap"
        //    90: new             Ljava/lang/StringBuilder;
        //    93: dup            
        //    94: invokespecial   java/lang/StringBuilder.<init>:()V
        //    97: ldc             "mEsnMigrationFlags json "
        //    99: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   102: aload_1        
        //   103: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   106: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   109: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   112: pop            
        //   113: aload_0        
        //   114: new             Lorg/json/JSONObject;
        //   117: dup            
        //   118: aload_1        
        //   119: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   122: putfield        com/netflix/mediaclient/service/user/UserProfileMap.mEsnMigrationFlags:Lorg/json/JSONObject;
        //   125: return         
        //   126: astore_1       
        //   127: aload_0        
        //   128: new             Lorg/json/JSONObject;
        //   131: dup            
        //   132: invokespecial   org/json/JSONObject.<init>:()V
        //   135: putfield        com/netflix/mediaclient/service/user/UserProfileMap.mProfileMap:Lorg/json/JSONObject;
        //   138: goto            67
        //   141: astore_1       
        //   142: aload_0        
        //   143: new             Lorg/json/JSONObject;
        //   146: dup            
        //   147: invokespecial   org/json/JSONObject.<init>:()V
        //   150: putfield        com/netflix/mediaclient/service/user/UserProfileMap.mEsnMigrationFlags:Lorg/json/JSONObject;
        //   153: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  55     67     126    141    Lorg/json/JSONException;
        //  113    125    141    154    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0113:
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
        String optString = this.mProfileMap.optString("currentAcc");
        final String optString2 = this.mProfileMap.optString(optString);
        if ("primaryAcc".equals(optString)) {
            optString = "";
        }
        return (Pair<String, String>)Pair.create((Object)optString, (Object)optString2);
    }
    
    String getPrimaryAccountKey() {
        return this.mProfileMap.optString("primaryAcc");
    }
    
    boolean isCurrentAccountNeedEsnMigration() {
        return this.mEsnMigrationFlags.optBoolean(this.mProfileMap.optString("currentAcc"), false);
    }
    
    void markAllAccountForEsnMigration() {
        if (Log.isLoggable("nf_service_useragentproilemap", 3)) {
            Log.d("nf_service_useragentproilemap", "markAllAccountForEsnMigration");
        }
        if (this.mProfileMap != null) {
            boolean b = false;
            final Iterator keys = this.mProfileMap.keys();
        Block_7_Outer:
            while (true) {
                Label_0090: {
                    if (!keys.hasNext()) {
                        break Label_0090;
                    }
                    final String s = keys.next();
                    if ("currentAcc".equals(s) || "primaryAcc".equals(s)) {
                        continue;
                    }
                    try {
                        this.mEsnMigrationFlags.putOpt(s, (Object)true);
                        b = true;
                        continue Block_7_Outer;
                        // iftrue(Label_0024:, !b)
                        while (true) {
                            Block_6: {
                                break Block_6;
                                Log.d("nf_service_useragentproilemap", "markAllAccountForEsnMigration " + this.mEsnMigrationFlags);
                                Label_0131: {
                                    PreferenceUtils.putStringPref(this.mContext, "useragent_esnmigration_flags", this.mEsnMigrationFlags.toString());
                                }
                                return;
                            }
                            continue;
                        }
                    }
                    // iftrue(Label_0131:, !Log.isLoggable("nf_service_useragentproilemap", 3))
                    catch (JSONException ex) {}
                }
            }
        }
        Label_0024:;
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
