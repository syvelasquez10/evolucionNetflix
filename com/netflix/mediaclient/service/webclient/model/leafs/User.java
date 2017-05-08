// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.NetflixApplication;
import org.json.JSONObject;

public class User implements com.netflix.mediaclient.servicemgr.interface_.user.User
{
    private static final String FIELD_AGE_VERIFIED = "ageVerified";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_FIRST_NAME = "firstName";
    private static final String FIELD_LAST_NAME = "lastName";
    private static final String FIELD_USER_TOKEN = "userId";
    private static final String TAG = "User";
    public EogAlert eogAlert;
    public SubtitlePreference subtitleDefaults;
    public User$Summary summary;
    private UmaAlert umaAlert;
    
    public User() {
        this.summary = new User$Summary(this);
    }
    
    public User(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokespecial   java/lang/Object.<init>:()V
        //     4: aload_0        
        //     5: new             Lcom/netflix/mediaclient/service/webclient/model/leafs/User$Summary;
        //     8: dup            
        //     9: aload_0        
        //    10: invokespecial   com/netflix/mediaclient/service/webclient/model/leafs/User$Summary.<init>:(Lcom/netflix/mediaclient/service/webclient/model/leafs/User;)V
        //    13: putfield        com/netflix/mediaclient/service/webclient/model/leafs/User.summary:Lcom/netflix/mediaclient/service/webclient/model/leafs/User$Summary;
        //    16: aload_1        
        //    17: invokestatic    com/netflix/mediaclient/util/StringUtils.isEmpty:(Ljava/lang/String;)Z
        //    20: ifeq            243
        //    23: new             Lorg/json/JSONObject;
        //    26: dup            
        //    27: invokespecial   org/json/JSONObject.<init>:()V
        //    30: astore_2       
        //    31: aload_0        
        //    32: getfield        com/netflix/mediaclient/service/webclient/model/leafs/User.summary:Lcom/netflix/mediaclient/service/webclient/model/leafs/User$Summary;
        //    35: aload_2        
        //    36: ldc             "userId"
        //    38: aconst_null    
        //    39: invokestatic    com/netflix/mediaclient/util/JsonUtils.getString:(Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    42: invokestatic    com/netflix/mediaclient/service/webclient/model/leafs/User$Summary.access$302:(Lcom/netflix/mediaclient/service/webclient/model/leafs/User$Summary;Ljava/lang/String;)Ljava/lang/String;
        //    45: pop            
        //    46: aload_0        
        //    47: getfield        com/netflix/mediaclient/service/webclient/model/leafs/User.summary:Lcom/netflix/mediaclient/service/webclient/model/leafs/User$Summary;
        //    50: aload_2        
        //    51: ldc             "email"
        //    53: aconst_null    
        //    54: invokestatic    com/netflix/mediaclient/util/JsonUtils.getString:(Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    57: invokestatic    com/netflix/mediaclient/service/webclient/model/leafs/User$Summary.access$002:(Lcom/netflix/mediaclient/service/webclient/model/leafs/User$Summary;Ljava/lang/String;)Ljava/lang/String;
        //    60: pop            
        //    61: aload_0        
        //    62: getfield        com/netflix/mediaclient/service/webclient/model/leafs/User.summary:Lcom/netflix/mediaclient/service/webclient/model/leafs/User$Summary;
        //    65: aload_2        
        //    66: ldc             "firstName"
        //    68: aconst_null    
        //    69: invokestatic    com/netflix/mediaclient/util/JsonUtils.getString:(Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    72: invokestatic    com/netflix/mediaclient/service/webclient/model/leafs/User$Summary.access$102:(Lcom/netflix/mediaclient/service/webclient/model/leafs/User$Summary;Ljava/lang/String;)Ljava/lang/String;
        //    75: pop            
        //    76: aload_0        
        //    77: getfield        com/netflix/mediaclient/service/webclient/model/leafs/User.summary:Lcom/netflix/mediaclient/service/webclient/model/leafs/User$Summary;
        //    80: aload_2        
        //    81: ldc             "lastName"
        //    83: aconst_null    
        //    84: invokestatic    com/netflix/mediaclient/util/JsonUtils.getString:(Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    87: invokestatic    com/netflix/mediaclient/service/webclient/model/leafs/User$Summary.access$202:(Lcom/netflix/mediaclient/service/webclient/model/leafs/User$Summary;Ljava/lang/String;)Ljava/lang/String;
        //    90: pop            
        //    91: aload_0        
        //    92: getfield        com/netflix/mediaclient/service/webclient/model/leafs/User.summary:Lcom/netflix/mediaclient/service/webclient/model/leafs/User$Summary;
        //    95: aload_2        
        //    96: ldc             "ageVerified"
        //    98: iconst_0       
        //    99: invokestatic    com/netflix/mediaclient/util/JsonUtils.getBoolean:(Lorg/json/JSONObject;Ljava/lang/String;Z)Z
        //   102: invokestatic    com/netflix/mediaclient/service/webclient/model/leafs/User$Summary.access$402:(Lcom/netflix/mediaclient/service/webclient/model/leafs/User$Summary;Z)Z
        //   105: pop            
        //   106: aload_2        
        //   107: ldc             "subtitleOverride"
        //   109: aconst_null    
        //   110: invokestatic    com/netflix/mediaclient/util/JsonUtils.getString:(Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   113: astore_3       
        //   114: aload_3        
        //   115: invokestatic    com/netflix/mediaclient/util/StringUtils.isEmpty:(Ljava/lang/String;)Z
        //   118: ifeq            255
        //   121: aconst_null    
        //   122: astore_3       
        //   123: aload_0        
        //   124: aload_3        
        //   125: putfield        com/netflix/mediaclient/service/webclient/model/leafs/User.subtitleDefaults:Lcom/netflix/mediaclient/service/webclient/model/leafs/SubtitlePreference;
        //   128: aload_2        
        //   129: ldc             "umaAlert"
        //   131: aconst_null    
        //   132: invokestatic    com/netflix/mediaclient/util/JsonUtils.getString:(Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   135: astore          4
        //   137: aload           4
        //   139: invokestatic    com/netflix/mediaclient/util/StringUtils.isEmpty:(Ljava/lang/String;)Z
        //   142: ifeq            303
        //   145: aconst_null    
        //   146: astore_3       
        //   147: aload_0        
        //   148: aload_3        
        //   149: putfield        com/netflix/mediaclient/service/webclient/model/leafs/User.umaAlert:Lcom/netflix/mediaclient/service/webclient/model/leafs/UmaAlert;
        //   152: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   155: ifeq            186
        //   158: ldc             "User"
        //   160: new             Ljava/lang/StringBuilder;
        //   163: dup            
        //   164: invokespecial   java/lang/StringBuilder.<init>:()V
        //   167: ldc             "UMA loaded from disk : "
        //   169: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   172: aload_0        
        //   173: getfield        com/netflix/mediaclient/service/webclient/model/leafs/User.umaAlert:Lcom/netflix/mediaclient/service/webclient/model/leafs/UmaAlert;
        //   176: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   179: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   182: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   185: pop            
        //   186: aload_2        
        //   187: ldc             "eogAlert"
        //   189: aconst_null    
        //   190: invokestatic    com/netflix/mediaclient/util/JsonUtils.getString:(Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   193: astore_2       
        //   194: aload_2        
        //   195: invokestatic    com/netflix/mediaclient/util/StringUtils.isEmpty:(Ljava/lang/String;)Z
        //   198: ifeq            359
        //   201: aconst_null    
        //   202: astore_2       
        //   203: aload_0        
        //   204: aload_2        
        //   205: putfield        com/netflix/mediaclient/service/webclient/model/leafs/User.eogAlert:Lcom/netflix/mediaclient/service/webclient/model/leafs/EogAlert;
        //   208: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   211: ifeq            242
        //   214: ldc             "User"
        //   216: new             Ljava/lang/StringBuilder;
        //   219: dup            
        //   220: invokespecial   java/lang/StringBuilder.<init>:()V
        //   223: ldc             "EOG loaded from disk : "
        //   225: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   228: aload_0        
        //   229: getfield        com/netflix/mediaclient/service/webclient/model/leafs/User.umaAlert:Lcom/netflix/mediaclient/service/webclient/model/leafs/UmaAlert;
        //   232: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   235: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   238: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   241: pop            
        //   242: return         
        //   243: new             Lorg/json/JSONObject;
        //   246: dup            
        //   247: aload_1        
        //   248: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   251: astore_2       
        //   252: goto            31
        //   255: new             Lcom/netflix/mediaclient/service/webclient/model/leafs/SubtitlePreference;
        //   258: dup            
        //   259: aload_3        
        //   260: invokespecial   com/netflix/mediaclient/service/webclient/model/leafs/SubtitlePreference.<init>:(Ljava/lang/String;)V
        //   263: astore_3       
        //   264: goto            123
        //   267: astore_2       
        //   268: ldc             "User"
        //   270: new             Ljava/lang/StringBuilder;
        //   273: dup            
        //   274: invokespecial   java/lang/StringBuilder.<init>:()V
        //   277: ldc             "failed to create json string="
        //   279: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   282: aload_1        
        //   283: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   286: ldc             " exception ="
        //   288: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   291: aload_2        
        //   292: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   295: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   298: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   301: pop            
        //   302: return         
        //   303: invokestatic    com/netflix/mediaclient/NetflixApplication.getGson:()Lcom/google/gson/Gson;
        //   306: aload           4
        //   308: ldc             Lcom/netflix/mediaclient/service/webclient/model/leafs/UmaAlert;.class
        //   310: invokevirtual   com/google/gson/Gson.fromJson:(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
        //   313: checkcast       Lcom/netflix/mediaclient/service/webclient/model/leafs/UmaAlert;
        //   316: astore_3       
        //   317: goto            147
        //   320: astore_3       
        //   321: ldc             "User"
        //   323: new             Ljava/lang/StringBuilder;
        //   326: dup            
        //   327: invokespecial   java/lang/StringBuilder.<init>:()V
        //   330: ldc             "failed to parse uma="
        //   332: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   335: aload           4
        //   337: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   340: ldc             " exception ="
        //   342: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   345: aload_3        
        //   346: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   349: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   352: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   355: pop            
        //   356: goto            186
        //   359: invokestatic    com/netflix/mediaclient/NetflixApplication.getGson:()Lcom/google/gson/Gson;
        //   362: aload_2        
        //   363: ldc             Lcom/netflix/mediaclient/service/webclient/model/leafs/EogAlert;.class
        //   365: invokevirtual   com/google/gson/Gson.fromJson:(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
        //   368: checkcast       Lcom/netflix/mediaclient/service/webclient/model/leafs/EogAlert;
        //   371: astore_2       
        //   372: goto            203
        //   375: astore_2       
        //   376: ldc             "User"
        //   378: new             Ljava/lang/StringBuilder;
        //   381: dup            
        //   382: invokespecial   java/lang/StringBuilder.<init>:()V
        //   385: ldc             "failed to parse uma="
        //   387: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   390: aload           4
        //   392: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   395: ldc             " exception ="
        //   397: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   400: aload_2        
        //   401: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   404: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   407: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   410: pop            
        //   411: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                 
        //  -----  -----  -----  -----  -------------------------------------
        //  16     31     267    303    Lorg/json/JSONException;
        //  31     121    267    303    Lorg/json/JSONException;
        //  123    137    267    303    Lorg/json/JSONException;
        //  137    145    320    359    Lcom/google/gson/JsonSyntaxException;
        //  137    145    267    303    Lorg/json/JSONException;
        //  147    186    320    359    Lcom/google/gson/JsonSyntaxException;
        //  147    186    267    303    Lorg/json/JSONException;
        //  186    194    267    303    Lorg/json/JSONException;
        //  194    201    375    412    Lcom/google/gson/JsonSyntaxException;
        //  194    201    267    303    Lorg/json/JSONException;
        //  203    242    375    412    Lcom/google/gson/JsonSyntaxException;
        //  203    242    267    303    Lorg/json/JSONException;
        //  243    252    267    303    Lorg/json/JSONException;
        //  255    264    267    303    Lorg/json/JSONException;
        //  303    317    320    359    Lcom/google/gson/JsonSyntaxException;
        //  303    317    267    303    Lorg/json/JSONException;
        //  321    356    267    303    Lorg/json/JSONException;
        //  359    372    375    412    Lcom/google/gson/JsonSyntaxException;
        //  359    372    267    303    Lorg/json/JSONException;
        //  376    411    267    303    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 200, Size: 200
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
    
    @Override
    public String getEmail() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.email;
    }
    
    @Override
    public String getFirstName() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.firstName;
    }
    
    @Override
    public String getLastName() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.lastName;
    }
    
    public SubtitlePreference getSubtitleDefaults() {
        return this.subtitleDefaults;
    }
    
    public UmaAlert getUmaAlert() {
        return this.umaAlert;
    }
    
    @Override
    public String getUserToken() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.userToken;
    }
    
    @Override
    public boolean isAgeVerified() {
        return this.summary != null && this.summary.isAgeVerified;
    }
    
    public void setUmaAlert(final UmaAlert umaAlert) {
        this.umaAlert = umaAlert;
    }
    
    @Override
    public String toString() {
        final JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                jsonObject.put("userId", (Object)this.getUserToken());
                jsonObject.put("email", (Object)this.getEmail());
                jsonObject.put("firstName", (Object)this.getFirstName());
                jsonObject.put("lastName", (Object)this.getLastName());
                jsonObject.put("ageVerified", this.isAgeVerified());
                if (this.subtitleDefaults != null) {
                    jsonObject.put("subtitleOverride", (Object)this.subtitleDefaults.toString());
                }
                if (this.umaAlert != null && !this.umaAlert.isConsumed()) {
                    jsonObject.put("umaAlert", (Object)NetflixApplication.getGson().toJson(this.umaAlert));
                    if (Log.isLoggable()) {
                        Log.d("User", "UMA saved from disk : " + this.umaAlert);
                    }
                }
                if (this.eogAlert != null && !this.eogAlert.isDirty) {
                    jsonObject.put("eogAlert", (Object)NetflixApplication.getGson().toJson(this.eogAlert));
                    if (Log.isLoggable()) {
                        Log.d("User", "EOG saved from disk : " + this.eogAlert);
                    }
                }
                Log.d("User", "user string=" + jsonObject.toString());
                return jsonObject.toString();
            }
            catch (JSONException ex) {
                Log.d("User", "failed in json string " + ex);
                continue;
            }
            break;
        }
    }
}
