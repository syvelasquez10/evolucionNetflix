// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import com.netflix.msl.util.Base64;
import java.util.Arrays;
import com.netflix.msl.MslInternalException;
import com.netflix.android.org.json.JSONObject;

public class JsonWebKeyLadderExchange$RequestData extends KeyRequestData
{
    private static final String KEY_MECHANISM = "mechanism";
    private static final String KEY_WRAPDATA = "wrapdata";
    private final JsonWebKeyLadderExchange$Mechanism mechanism;
    private final byte[] wrapdata;
    
    public JsonWebKeyLadderExchange$RequestData(final JSONObject p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/netflix/msl/keyx/KeyExchangeScheme.JWK_LADDER:Lcom/netflix/msl/keyx/KeyExchangeScheme;
        //     4: invokespecial   com/netflix/msl/keyx/KeyRequestData.<init>:(Lcom/netflix/msl/keyx/KeyExchangeScheme;)V
        //     7: aload_1        
        //     8: ldc             "mechanism"
        //    10: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    13: astore_2       
        //    14: aload_0        
        //    15: aload_2        
        //    16: invokestatic    com/netflix/msl/keyx/JsonWebKeyLadderExchange$Mechanism.valueOf:(Ljava/lang/String;)Lcom/netflix/msl/keyx/JsonWebKeyLadderExchange$Mechanism;
        //    19: putfield        com/netflix/msl/keyx/JsonWebKeyLadderExchange$RequestData.mechanism:Lcom/netflix/msl/keyx/JsonWebKeyLadderExchange$Mechanism;
        //    22: getstatic       com/netflix/msl/keyx/JsonWebKeyLadderExchange$1.$SwitchMap$com$netflix$msl$keyx$JsonWebKeyLadderExchange$Mechanism:[I
        //    25: aload_0        
        //    26: getfield        com/netflix/msl/keyx/JsonWebKeyLadderExchange$RequestData.mechanism:Lcom/netflix/msl/keyx/JsonWebKeyLadderExchange$Mechanism;
        //    29: invokevirtual   com/netflix/msl/keyx/JsonWebKeyLadderExchange$Mechanism.ordinal:()I
        //    32: iaload         
        //    33: tableswitch {
        //                2: 164
        //                3: 158
        //          default: 260
        //        }
        //    56: new             Lcom/netflix/msl/MslCryptoException;
        //    59: dup            
        //    60: getstatic       com/netflix/msl/MslError.UNSUPPORTED_KEYX_MECHANISM:Lcom/netflix/msl/MslError;
        //    63: aload_0        
        //    64: getfield        com/netflix/msl/keyx/JsonWebKeyLadderExchange$RequestData.mechanism:Lcom/netflix/msl/keyx/JsonWebKeyLadderExchange$Mechanism;
        //    67: invokevirtual   com/netflix/msl/keyx/JsonWebKeyLadderExchange$Mechanism.name:()Ljava/lang/String;
        //    70: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //    73: athrow         
        //    74: astore_2       
        //    75: new             Lcom/netflix/msl/MslEncodingException;
        //    78: dup            
        //    79: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //    82: new             Ljava/lang/StringBuilder;
        //    85: dup            
        //    86: invokespecial   java/lang/StringBuilder.<init>:()V
        //    89: ldc             "keydata "
        //    91: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    94: aload_1        
        //    95: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //    98: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   101: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   104: aload_2        
        //   105: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   108: athrow         
        //   109: astore_3       
        //   110: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   113: dup            
        //   114: getstatic       com/netflix/msl/MslError.UNIDENTIFIED_KEYX_MECHANISM:Lcom/netflix/msl/MslError;
        //   117: aload_2        
        //   118: aload_3        
        //   119: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   122: athrow         
        //   123: astore_2       
        //   124: new             Lcom/netflix/msl/MslEncodingException;
        //   127: dup            
        //   128: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   131: new             Ljava/lang/StringBuilder;
        //   134: dup            
        //   135: invokespecial   java/lang/StringBuilder.<init>:()V
        //   138: ldc             "keydata "
        //   140: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   143: aload_1        
        //   144: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   147: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   150: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   153: aload_2        
        //   154: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   157: athrow         
        //   158: aload_0        
        //   159: aconst_null    
        //   160: putfield        com/netflix/msl/keyx/JsonWebKeyLadderExchange$RequestData.wrapdata:[B
        //   163: return         
        //   164: aload_0        
        //   165: aload_1        
        //   166: ldc             "wrapdata"
        //   168: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   171: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //   174: putfield        com/netflix/msl/keyx/JsonWebKeyLadderExchange$RequestData.wrapdata:[B
        //   177: aload_0        
        //   178: getfield        com/netflix/msl/keyx/JsonWebKeyLadderExchange$RequestData.wrapdata:[B
        //   181: ifnull          192
        //   184: aload_0        
        //   185: getfield        com/netflix/msl/keyx/JsonWebKeyLadderExchange$RequestData.wrapdata:[B
        //   188: arraylength    
        //   189: ifne            163
        //   192: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   195: dup            
        //   196: getstatic       com/netflix/msl/MslError.KEYX_WRAPPING_KEY_MISSING:Lcom/netflix/msl/MslError;
        //   199: new             Ljava/lang/StringBuilder;
        //   202: dup            
        //   203: invokespecial   java/lang/StringBuilder.<init>:()V
        //   206: ldc             "keydata "
        //   208: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   211: aload_1        
        //   212: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   215: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   218: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   221: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   224: athrow         
        //   225: astore_2       
        //   226: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   229: dup            
        //   230: getstatic       com/netflix/msl/MslError.KEYX_INVALID_WRAPDATA:Lcom/netflix/msl/MslError;
        //   233: new             Ljava/lang/StringBuilder;
        //   236: dup            
        //   237: invokespecial   java/lang/StringBuilder.<init>:()V
        //   240: ldc             "keydata "
        //   242: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   245: aload_1        
        //   246: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   249: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   252: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   255: aload_2        
        //   256: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   259: athrow         
        //   260: goto            56
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  7      14     123    158    Lcom/netflix/android/org/json/JSONException;
        //  14     22     109    123    Ljava/lang/IllegalArgumentException;
        //  14     22     123    158    Lcom/netflix/android/org/json/JSONException;
        //  22     56     74     109    Lcom/netflix/android/org/json/JSONException;
        //  56     74     74     109    Lcom/netflix/android/org/json/JSONException;
        //  110    123    123    158    Lcom/netflix/android/org/json/JSONException;
        //  158    163    74     109    Lcom/netflix/android/org/json/JSONException;
        //  164    177    225    260    Ljava/lang/IllegalArgumentException;
        //  164    177    74     109    Lcom/netflix/android/org/json/JSONException;
        //  177    192    74     109    Lcom/netflix/android/org/json/JSONException;
        //  192    225    74     109    Lcom/netflix/android/org/json/JSONException;
        //  226    260    74     109    Lcom/netflix/android/org/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0164:
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
    
    public JsonWebKeyLadderExchange$RequestData(final JsonWebKeyLadderExchange$Mechanism mechanism, final byte[] wrapdata) {
        super(KeyExchangeScheme.JWK_LADDER);
        this.mechanism = mechanism;
        switch (JsonWebKeyLadderExchange$1.$SwitchMap$com$netflix$msl$keyx$JsonWebKeyLadderExchange$Mechanism[mechanism.ordinal()]) {
            default: {
                this.wrapdata = null;
            }
            case 1: {
                if (wrapdata == null) {
                    throw new MslInternalException("Previous wrapping key based key exchange requires the previous wrapping key data and ID.");
                }
                this.wrapdata = wrapdata;
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof JsonWebKeyLadderExchange$RequestData)) {
            return false;
        }
        final JsonWebKeyLadderExchange$RequestData jsonWebKeyLadderExchange$RequestData = (JsonWebKeyLadderExchange$RequestData)o;
        final boolean equals = Arrays.equals(this.wrapdata, jsonWebKeyLadderExchange$RequestData.wrapdata);
        return super.equals(o) && this.mechanism.equals(jsonWebKeyLadderExchange$RequestData.mechanism) && equals;
    }
    
    @Override
    protected JSONObject getKeydata() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("mechanism", this.mechanism.name());
        if (this.wrapdata != null) {
            jsonObject.put("wrapdata", Base64.encode(this.wrapdata));
        }
        return jsonObject;
    }
    
    public JsonWebKeyLadderExchange$Mechanism getMechanism() {
        return this.mechanism;
    }
    
    public byte[] getWrapdata() {
        return this.wrapdata;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.wrapdata != null) {
            hashCode = Arrays.hashCode(this.wrapdata);
        }
        else {
            hashCode = 0;
        }
        return hashCode ^ (super.hashCode() ^ this.mechanism.hashCode());
    }
}
