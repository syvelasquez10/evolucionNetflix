// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.Collection;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import org.json.JSONObject;
import java.util.Vector;

public final class n extends s
{
    public n(final m m) {
        this(m.a() + "/android_v2/handle_app_loads");
    }
    
    private n(final String a) {
        this.a = a;
        this.b = new Vector();
    }
    
    private static n a(final String ex) {
        final n n = new n((String)ex);
        JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                final SharedPreferences m = l.i().m();
                final JSONObject jsonObject2 = new JSONObject(m.getString(h(), new JSONObject().toString()));
                Label_0113: {
                    try {
                        final SharedPreferences$Editor edit = m.edit();
                        edit.remove(h());
                        jsonObject = jsonObject2;
                        if (!edit.commit()) {
                            throw new Exception("failed to remove app loads from Shared Preferences");
                        }
                        break Label_0113;
                    }
                    catch (Exception ex3) {}
                    new StringBuilder("Exception in AppLoads.readFromDisk(): ").append(jsonObject.getClass().getName());
                    jsonObject = jsonObject2;
                    try {
                        return a(jsonObject, (String)ex);
                    }
                    catch (Exception ex) {
                        return n;
                    }
                }
            }
            catch (Exception ex2) {
                final JSONObject jsonObject2 = jsonObject;
                jsonObject = (JSONObject)ex2;
                continue;
            }
            break;
        }
    }
    
    private static n a(final JSONObject p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcrittercism/android/n;
        //     3: dup            
        //     4: aload_1        
        //     5: invokespecial   crittercism/android/n.<init>:(Ljava/lang/String;)V
        //     8: astore          4
        //    10: new             Lorg/json/JSONObject;
        //    13: dup            
        //    14: invokespecial   org/json/JSONObject.<init>:()V
        //    17: astore_1       
        //    18: new             Lorg/json/JSONArray;
        //    21: dup            
        //    22: invokespecial   org/json/JSONArray.<init>:()V
        //    25: astore_3       
        //    26: aload_0        
        //    27: ldc             "requestData"
        //    29: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    32: ifeq            42
        //    35: aload_0        
        //    36: ldc             "requestData"
        //    38: invokevirtual   org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //    41: astore_1       
        //    42: aload_1        
        //    43: ldc             "app_loads"
        //    45: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    48: ifeq            136
        //    51: aload_1        
        //    52: ldc             "app_loads"
        //    54: invokevirtual   org/json/JSONObject.getJSONArray:(Ljava/lang/String;)Lorg/json/JSONArray;
        //    57: astore_0       
        //    58: iconst_0       
        //    59: istore_2       
        //    60: iload_2        
        //    61: aload_0        
        //    62: invokevirtual   org/json/JSONArray.length:()I
        //    65: if_icmpge       133
        //    68: aload           4
        //    70: aload_0        
        //    71: iload_2        
        //    72: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //    75: invokevirtual   crittercism/android/n.a:(Ljava/lang/Object;)V
        //    78: iload_2        
        //    79: iconst_1       
        //    80: iadd           
        //    81: istore_2       
        //    82: goto            60
        //    85: astore_0       
        //    86: new             Lorg/json/JSONObject;
        //    89: dup            
        //    90: invokespecial   org/json/JSONObject.<init>:()V
        //    93: astore_1       
        //    94: goto            42
        //    97: astore_0       
        //    98: new             Lorg/json/JSONArray;
        //   101: dup            
        //   102: invokespecial   org/json/JSONArray.<init>:()V
        //   105: astore_0       
        //   106: goto            58
        //   109: astore_1       
        //   110: new             Ljava/lang/StringBuilder;
        //   113: dup            
        //   114: ldc             "Exception in AppLoads.fromJSON: "
        //   116: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   119: aload_1        
        //   120: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   123: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   126: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   129: pop            
        //   130: goto            78
        //   133: aload           4
        //   135: areturn        
        //   136: aload_3        
        //   137: astore_0       
        //   138: goto            58
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  26     42     85     97     Ljava/lang/Exception;
        //  42     58     97     109    Ljava/lang/Exception;
        //  68     78     109    133    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 73, Size: 73
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
    
    private static String h() {
        new String();
        try {
            final String a = l.i().a();
            return "critter_pendingapploads_" + a;
        }
        catch (Exception ex) {
            final String a = new String();
            return "critter_pendingapploads_" + a;
        }
    }
    
    public final void a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/json/JSONObject;
        //     3: dup            
        //     4: invokespecial   org/json/JSONObject.<init>:()V
        //     7: astore_2       
        //     8: new             Lorg/json/JSONObject;
        //    11: dup            
        //    12: invokespecial   org/json/JSONObject.<init>:()V
        //    15: pop            
        //    16: invokestatic    crittercism/android/l.i:()Lcrittercism/android/l;
        //    19: getfield        crittercism/android/l.c:Lcrittercism/android/i;
        //    22: iconst_1       
        //    23: newarray        Z
        //    25: dup            
        //    26: iconst_0       
        //    27: iconst_0       
        //    28: bastore        
        //    29: invokevirtual   crittercism/android/i.a:([Z)Lorg/json/JSONObject;
        //    32: astore_1       
        //    33: aload_1        
        //    34: ldc             "ts"
        //    36: getstatic       crittercism/android/au.a:Lcrittercism/android/au;
        //    39: invokevirtual   crittercism/android/au.a:()Ljava/lang/String;
        //    42: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    45: pop            
        //    46: aload_2        
        //    47: ldc             "app_state"
        //    49: aload_1        
        //    50: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    53: pop            
        //    54: aload_2        
        //    55: ldc             "type"
        //    57: getstatic       crittercism/android/s.g:Ljava/lang/String;
        //    60: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    63: pop            
        //    64: aload_2        
        //    65: astore_1       
        //    66: aload_1        
        //    67: ifnull          81
        //    70: aload_0        
        //    71: getfield        crittercism/android/n.b:Ljava/util/Collection;
        //    74: aload_1        
        //    75: invokeinterface java/util/Collection.add:(Ljava/lang/Object;)Z
        //    80: pop            
        //    81: return         
        //    82: astore_1       
        //    83: new             Lorg/json/JSONObject;
        //    86: dup            
        //    87: invokespecial   org/json/JSONObject.<init>:()V
        //    90: astore_1       
        //    91: goto            46
        //    94: astore_1       
        //    95: aconst_null    
        //    96: astore_1       
        //    97: goto            66
        //   100: astore_1       
        //   101: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  16     46     82     94     Ljava/lang/Exception;
        //  46     64     94     100    Ljava/lang/Exception;
        //  70     81     100    102    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 56, Size: 56
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
    
    @Override
    public final void a(final JSONObject p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          5
        //     3: new             Ljava/lang/StringBuilder;
        //     6: dup            
        //     7: ldc             "responseObject = "
        //     9: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    12: aload_1        
        //    13: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //    16: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    19: pop            
        //    20: aload_0        
        //    21: getfield        crittercism/android/n.b:Ljava/util/Collection;
        //    24: invokeinterface java/util/Collection.size:()I
        //    29: istore_2       
        //    30: iload_2        
        //    31: ifne            36
        //    34: return         
        //    35: astore_3       
        //    36: aload_0        
        //    37: aload_1        
        //    38: invokespecial   crittercism/android/s.a:(Lorg/json/JSONObject;)V
        //    41: invokestatic    crittercism/android/l.i:()Lcrittercism/android/l;
        //    44: astore          6
        //    46: aload_1        
        //    47: ldc             "success"
        //    49: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    52: ifeq            306
        //    55: aload_1        
        //    56: ldc             "success"
        //    58: invokevirtual   org/json/JSONObject.getInt:(Ljava/lang/String;)I
        //    61: iconst_1       
        //    62: if_icmpne       73
        //    65: aload           6
        //    67: getfield        crittercism/android/l.c:Lcrittercism/android/i;
        //    70: invokevirtual   crittercism/android/i.i:()V
        //    73: new             Lorg/json/JSONObject;
        //    76: dup            
        //    77: invokespecial   org/json/JSONObject.<init>:()V
        //    80: pop            
        //    81: aload_1        
        //    82: ldc             "me"
        //    84: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    87: ifeq            561
        //    90: aload_1        
        //    91: ldc             "me"
        //    93: invokevirtual   org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //    96: astore_3       
        //    97: aload_3        
        //    98: ldc             "username"
        //   100: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   103: ifeq            345
        //   106: new             Lcrittercism/android/as;
        //   109: dup            
        //   110: aload_3        
        //   111: ldc             "username"
        //   113: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   116: invokespecial   crittercism/android/as.<init>:(Ljava/lang/String;)V
        //   119: astore          4
        //   121: aload           4
        //   123: ifnull          159
        //   126: aload           6
        //   128: aload           4
        //   130: putfield        crittercism/android/l.d:Lcrittercism/android/as;
        //   133: aload           6
        //   135: getfield        crittercism/android/l.e:Lcrittercism/android/at;
        //   138: invokevirtual   crittercism/android/at.e:()Lcrittercism/android/aq;
        //   141: astore          4
        //   143: aload_3        
        //   144: ldc             "notify"
        //   146: invokevirtual   org/json/JSONObject.optJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //   149: astore_3       
        //   150: aload_3        
        //   151: ifnonnull       413
        //   154: aload           4
        //   156: invokevirtual   crittercism/android/aq.c:()V
        //   159: aload_1        
        //   160: ldc             "apm"
        //   162: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   165: ifeq            192
        //   168: aload_1        
        //   169: ldc             "apm"
        //   171: invokevirtual   org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //   174: astore_3       
        //   175: new             Ljava/lang/StringBuilder;
        //   178: dup            
        //   179: ldc             "apmSettings = "
        //   181: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   184: aload_3        
        //   185: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   188: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   191: pop            
        //   192: aload_1        
        //   193: ldc             "app_settings"
        //   195: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   198: ifeq            34
        //   201: aload_1        
        //   202: ldc             "app_settings"
        //   204: invokevirtual   org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //   207: astore_1       
        //   208: aload_1        
        //   209: ldc             "settings"
        //   211: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   214: ifeq            34
        //   217: aload_1        
        //   218: ldc             "settings"
        //   220: invokevirtual   org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //   223: astore_3       
        //   224: aload_3        
        //   225: ldc             "need_pkg"
        //   227: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   230: ifeq            34
        //   233: new             Ljava/lang/StringBuilder;
        //   236: dup            
        //   237: ldc             "settings need_pkg = "
        //   239: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   242: astore          4
        //   244: aload_3        
        //   245: ldc             "need_pkg"
        //   247: invokevirtual   org/json/JSONObject.getInt:(Ljava/lang/String;)I
        //   250: iconst_1       
        //   251: if_icmpne       547
        //   254: ldc             "true"
        //   256: astore_1       
        //   257: aload           4
        //   259: aload_1        
        //   260: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   263: pop            
        //   264: aload_3        
        //   265: ldc             "need_pkg"
        //   267: invokevirtual   org/json/JSONObject.getInt:(Ljava/lang/String;)I
        //   270: iconst_1       
        //   271: if_icmpne       34
        //   274: aload           6
        //   276: getfield        crittercism/android/l.c:Lcrittercism/android/i;
        //   279: invokevirtual   crittercism/android/i.h:()Ljava/lang/String;
        //   282: pop            
        //   283: return         
        //   284: astore_1       
        //   285: new             Ljava/lang/StringBuilder;
        //   288: dup            
        //   289: ldc             "Exception setting app settings in AppLoads$handleResponseObject: "
        //   291: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   294: aload_1        
        //   295: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   298: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   301: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   304: pop            
        //   305: return         
        //   306: aload           6
        //   308: getfield        crittercism/android/l.c:Lcrittercism/android/i;
        //   311: invokevirtual   crittercism/android/i.i:()V
        //   314: goto            73
        //   317: astore_3       
        //   318: goto            73
        //   321: astore_3       
        //   322: new             Ljava/lang/StringBuilder;
        //   325: dup            
        //   326: ldc             "Exception in AppLoads$handleResponseObject: "
        //   328: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   331: aload_3        
        //   332: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   335: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   338: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   341: pop            
        //   342: goto            73
        //   345: new             Lcrittercism/android/as;
        //   348: dup            
        //   349: invokespecial   crittercism/android/as.<init>:()V
        //   352: astore          4
        //   354: goto            121
        //   357: astore          4
        //   359: new             Ljava/lang/StringBuilder;
        //   362: dup            
        //   363: ldc             "Exception getting user object in AppLoads$handleResponseObject: "
        //   365: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   368: aload           4
        //   370: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   373: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   376: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   379: pop            
        //   380: aload           5
        //   382: astore          4
        //   384: goto            121
        //   387: astore          4
        //   389: new             Ljava/lang/StringBuilder;
        //   392: dup            
        //   393: ldc             "Exception setting user object in AppLoads$handleResponseObject: "
        //   395: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   398: aload           4
        //   400: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   403: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   406: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   409: pop            
        //   410: goto            133
        //   413: aload_3        
        //   414: ldc             "type"
        //   416: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //   419: ifnull          159
        //   422: aload_3        
        //   423: ldc             "type"
        //   425: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //   428: ldc             "rate_my_app"
        //   430: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   433: ifeq            159
        //   436: new             Lcrittercism/android/aq;
        //   439: dup            
        //   440: aload           4
        //   442: iconst_0       
        //   443: invokespecial   crittercism/android/aq.<init>:(Lcrittercism/android/aq;B)V
        //   446: astore          4
        //   448: aload           4
        //   450: aload_3        
        //   451: ldc_w           "rate_after_load_num"
        //   454: iconst_5       
        //   455: invokevirtual   org/json/JSONObject.optInt:(Ljava/lang/String;I)I
        //   458: putfield        crittercism/android/aq.d:I
        //   461: aload           4
        //   463: aload_3        
        //   464: ldc_w           "remind_after_load_num"
        //   467: iconst_5       
        //   468: invokevirtual   org/json/JSONObject.optInt:(Ljava/lang/String;I)I
        //   471: putfield        crittercism/android/aq.e:I
        //   474: aload           4
        //   476: aload_3        
        //   477: ldc_w           "message"
        //   480: ldc_w           "Would you mind taking a second to rate my app?  I would really appreciate it!"
        //   483: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   486: putfield        crittercism/android/aq.f:Ljava/lang/String;
        //   489: aload           4
        //   491: aload_3        
        //   492: ldc_w           "title"
        //   495: ldc_w           "Rate My App"
        //   498: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   501: putfield        crittercism/android/aq.g:Ljava/lang/String;
        //   504: aload           4
        //   506: invokevirtual   crittercism/android/aq.a:()V
        //   509: aload           6
        //   511: getfield        crittercism/android/l.e:Lcrittercism/android/at;
        //   514: aload           4
        //   516: invokevirtual   crittercism/android/at.a:(Lcrittercism/android/aq;)V
        //   519: goto            159
        //   522: astore_3       
        //   523: new             Ljava/lang/StringBuilder;
        //   526: dup            
        //   527: ldc_w           "Exception getting apm settings in AppLoads$handleResponseObject: "
        //   530: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   533: aload_3        
        //   534: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   537: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   540: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   543: pop            
        //   544: goto            192
        //   547: ldc_w           "false"
        //   550: astore_1       
        //   551: goto            257
        //   554: astore          4
        //   556: aconst_null    
        //   557: astore_3       
        //   558: goto            359
        //   561: aconst_null    
        //   562: astore_3       
        //   563: aload           5
        //   565: astore          4
        //   567: goto            121
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  20     30     35     36     Ljava/lang/Exception;
        //  46     73     317    321    Lorg/json/JSONException;
        //  46     73     321    345    Ljava/lang/Exception;
        //  81     97     554    561    Ljava/lang/Exception;
        //  97     121    357    359    Ljava/lang/Exception;
        //  126    133    387    413    Ljava/lang/Exception;
        //  159    192    522    547    Ljava/lang/Exception;
        //  192    254    284    306    Ljava/lang/Exception;
        //  257    283    284    306    Ljava/lang/Exception;
        //  306    314    317    321    Lorg/json/JSONException;
        //  306    314    321    345    Ljava/lang/Exception;
        //  345    354    357    359    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 264, Size: 264
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3417)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
    
    @Override
    public final JSONObject b() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/json/JSONObject;
        //     3: dup            
        //     4: invokespecial   org/json/JSONObject.<init>:()V
        //     7: astore_3       
        //     8: new             Lorg/json/JSONObject;
        //    11: dup            
        //    12: invokespecial   org/json/JSONObject.<init>:()V
        //    15: pop            
        //    16: new             Lorg/json/JSONArray;
        //    19: dup            
        //    20: invokespecial   org/json/JSONArray.<init>:()V
        //    23: pop            
        //    24: new             Lorg/json/JSONArray;
        //    27: dup            
        //    28: aload_0        
        //    29: getfield        crittercism/android/n.b:Ljava/util/Collection;
        //    32: invokespecial   org/json/JSONArray.<init>:(Ljava/util/Collection;)V
        //    35: astore_1       
        //    36: invokestatic    crittercism/android/l.i:()Lcrittercism/android/l;
        //    39: getfield        crittercism/android/l.c:Lcrittercism/android/i;
        //    42: invokevirtual   crittercism/android/i.d:()Lorg/json/JSONObject;
        //    45: astore_2       
        //    46: aload_2        
        //    47: ldc             "app_loads"
        //    49: aload_1        
        //    50: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    53: pop            
        //    54: aload_2        
        //    55: astore_1       
        //    56: aload_3        
        //    57: ldc_w           "requestUrl"
        //    60: aload_0        
        //    61: getfield        crittercism/android/n.a:Ljava/lang/String;
        //    64: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    67: pop            
        //    68: aload_3        
        //    69: ldc             "requestData"
        //    71: aload_1        
        //    72: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    75: pop            
        //    76: aload_3        
        //    77: areturn        
        //    78: astore_1       
        //    79: new             Lorg/json/JSONArray;
        //    82: dup            
        //    83: invokespecial   org/json/JSONArray.<init>:()V
        //    86: astore_1       
        //    87: goto            36
        //    90: astore_1       
        //    91: new             Lorg/json/JSONObject;
        //    94: dup            
        //    95: invokespecial   org/json/JSONObject.<init>:()V
        //    98: astore_1       
        //    99: goto            56
        //   102: astore_1       
        //   103: new             Lorg/json/JSONObject;
        //   106: dup            
        //   107: invokespecial   org/json/JSONObject.<init>:()V
        //   110: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  24     36     78     90     Ljava/lang/Exception;
        //  36     54     90     102    Ljava/lang/Exception;
        //  56     76     102    111    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 59, Size: 59
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
    
    @Override
    public final void c() {
        try {
            final SharedPreferences$Editor edit = l.i().m().edit();
            edit.remove(h());
            edit.putString(h(), this.b().toString());
            if (!edit.commit()) {
                throw new Exception("commit failed");
            }
        }
        catch (Exception ex) {}
    }
    
    @Override
    public final JSONObject d() {
        return super.d();
    }
    
    @Override
    protected final void e() {
        final n a = a(this.a);
        a.a(this.b);
        this.b = a.b;
    }
}
