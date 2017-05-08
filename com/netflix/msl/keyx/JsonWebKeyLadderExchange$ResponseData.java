// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import com.netflix.msl.util.Base64;
import java.util.Arrays;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.tokens.MasterToken;

public class JsonWebKeyLadderExchange$ResponseData extends KeyResponseData
{
    private static final String KEY_ENCRYPTION_KEY = "encryptionkey";
    private static final String KEY_HMAC_KEY = "hmackey";
    private static final String KEY_WRAPDATA = "wrapdata";
    private static final String KEY_WRAP_KEY = "wrapkey";
    private final byte[] encryptionKey;
    private final byte[] hmacKey;
    private final byte[] wrapKey;
    private final byte[] wrapdata;
    
    public JsonWebKeyLadderExchange$ResponseData(final MasterToken p0, final JSONObject p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: getstatic       com/netflix/msl/keyx/KeyExchangeScheme.JWK_LADDER:Lcom/netflix/msl/keyx/KeyExchangeScheme;
        //     5: invokespecial   com/netflix/msl/keyx/KeyResponseData.<init>:(Lcom/netflix/msl/tokens/MasterToken;Lcom/netflix/msl/keyx/KeyExchangeScheme;)V
        //     8: aload_0        
        //     9: aload_2        
        //    10: ldc             "wrapkey"
        //    12: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    15: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //    18: putfield        com/netflix/msl/keyx/JsonWebKeyLadderExchange$ResponseData.wrapKey:[B
        //    21: aload_0        
        //    22: aload_2        
        //    23: ldc             "wrapdata"
        //    25: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    28: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //    31: putfield        com/netflix/msl/keyx/JsonWebKeyLadderExchange$ResponseData.wrapdata:[B
        //    34: aload_0        
        //    35: aload_2        
        //    36: ldc             "encryptionkey"
        //    38: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    41: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //    44: putfield        com/netflix/msl/keyx/JsonWebKeyLadderExchange$ResponseData.encryptionKey:[B
        //    47: aload_0        
        //    48: aload_2        
        //    49: ldc             "hmackey"
        //    51: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    54: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //    57: putfield        com/netflix/msl/keyx/JsonWebKeyLadderExchange$ResponseData.hmacKey:[B
        //    60: return         
        //    61: astore_1       
        //    62: new             Lcom/netflix/msl/MslKeyExchangeException;
        //    65: dup            
        //    66: getstatic       com/netflix/msl/MslError.KEYX_INVALID_WRAPPING_KEY:Lcom/netflix/msl/MslError;
        //    69: new             Ljava/lang/StringBuilder;
        //    72: dup            
        //    73: invokespecial   java/lang/StringBuilder.<init>:()V
        //    76: ldc             "keydata "
        //    78: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    81: aload_2        
        //    82: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //    85: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    88: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    91: aload_1        
        //    92: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //    95: athrow         
        //    96: astore_1       
        //    97: new             Lcom/netflix/msl/MslEncodingException;
        //   100: dup            
        //   101: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   104: new             Ljava/lang/StringBuilder;
        //   107: dup            
        //   108: invokespecial   java/lang/StringBuilder.<init>:()V
        //   111: ldc             "keydata "
        //   113: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   116: aload_2        
        //   117: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   120: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   123: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   126: aload_1        
        //   127: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   130: athrow         
        //   131: astore_1       
        //   132: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   135: dup            
        //   136: getstatic       com/netflix/msl/MslError.KEYX_INVALID_WRAPDATA:Lcom/netflix/msl/MslError;
        //   139: new             Ljava/lang/StringBuilder;
        //   142: dup            
        //   143: invokespecial   java/lang/StringBuilder.<init>:()V
        //   146: ldc             "keydata "
        //   148: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   151: aload_2        
        //   152: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   155: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   158: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   161: aload_1        
        //   162: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   165: athrow         
        //   166: astore_1       
        //   167: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   170: dup            
        //   171: getstatic       com/netflix/msl/MslError.KEYX_INVALID_ENCRYPTION_KEY:Lcom/netflix/msl/MslError;
        //   174: new             Ljava/lang/StringBuilder;
        //   177: dup            
        //   178: invokespecial   java/lang/StringBuilder.<init>:()V
        //   181: ldc             "keydata "
        //   183: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   186: aload_2        
        //   187: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   190: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   193: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   196: aload_1        
        //   197: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   200: athrow         
        //   201: astore_1       
        //   202: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   205: dup            
        //   206: getstatic       com/netflix/msl/MslError.KEYX_INVALID_HMAC_KEY:Lcom/netflix/msl/MslError;
        //   209: new             Ljava/lang/StringBuilder;
        //   212: dup            
        //   213: invokespecial   java/lang/StringBuilder.<init>:()V
        //   216: ldc             "keydata "
        //   218: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   221: aload_2        
        //   222: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   225: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   228: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   231: aload_1        
        //   232: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   235: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  8      21     61     96     Ljava/lang/IllegalArgumentException;
        //  8      21     96     131    Lcom/netflix/android/org/json/JSONException;
        //  21     34     131    166    Ljava/lang/IllegalArgumentException;
        //  21     34     96     131    Lcom/netflix/android/org/json/JSONException;
        //  34     47     166    201    Ljava/lang/IllegalArgumentException;
        //  34     47     96     131    Lcom/netflix/android/org/json/JSONException;
        //  47     60     201    236    Ljava/lang/IllegalArgumentException;
        //  47     60     96     131    Lcom/netflix/android/org/json/JSONException;
        //  62     96     96     131    Lcom/netflix/android/org/json/JSONException;
        //  132    166    96     131    Lcom/netflix/android/org/json/JSONException;
        //  167    201    96     131    Lcom/netflix/android/org/json/JSONException;
        //  202    236    96     131    Lcom/netflix/android/org/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 109, Size: 109
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
    
    public JsonWebKeyLadderExchange$ResponseData(final MasterToken masterToken, final byte[] wrapKey, final byte[] wrapdata, final byte[] encryptionKey, final byte[] hmacKey) {
        super(masterToken, KeyExchangeScheme.JWK_LADDER);
        this.wrapKey = wrapKey;
        this.wrapdata = wrapdata;
        this.encryptionKey = encryptionKey;
        this.hmacKey = hmacKey;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof JsonWebKeyLadderExchange$ResponseData)) {
            return false;
        }
        final JsonWebKeyLadderExchange$ResponseData jsonWebKeyLadderExchange$ResponseData = (JsonWebKeyLadderExchange$ResponseData)o;
        return super.equals(o) && Arrays.equals(this.wrapKey, jsonWebKeyLadderExchange$ResponseData.wrapKey) && Arrays.equals(this.wrapdata, jsonWebKeyLadderExchange$ResponseData.wrapdata) && Arrays.equals(this.encryptionKey, jsonWebKeyLadderExchange$ResponseData.encryptionKey) && Arrays.equals(this.hmacKey, jsonWebKeyLadderExchange$ResponseData.hmacKey);
    }
    
    public byte[] getEncryptionKey() {
        return this.encryptionKey;
    }
    
    public byte[] getHmacKey() {
        return this.hmacKey;
    }
    
    @Override
    protected JSONObject getKeydata() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("wrapkey", Base64.encode(this.wrapKey));
        jsonObject.put("wrapdata", Base64.encode(this.wrapdata));
        jsonObject.put("encryptionkey", Base64.encode(this.encryptionKey));
        jsonObject.put("hmackey", Base64.encode(this.hmacKey));
        return jsonObject;
    }
    
    public byte[] getWrapKey() {
        return this.wrapKey;
    }
    
    public byte[] getWrapdata() {
        return this.wrapdata;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() ^ Arrays.hashCode(this.wrapKey) ^ Arrays.hashCode(this.wrapdata) ^ Arrays.hashCode(this.encryptionKey) ^ Arrays.hashCode(this.hmacKey);
    }
}
