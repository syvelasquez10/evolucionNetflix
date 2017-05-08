// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import com.netflix.msl.util.Base64;
import java.util.Arrays;
import com.netflix.msl.MslInternalException;
import com.netflix.android.org.json.JSONObject;
import java.math.BigInteger;
import javax.crypto.interfaces.DHPrivateKey;

public class AuthenticatedDiffieHellmanRequestData extends KeyRequestData
{
    private static final String KEY_MECHANISM = "mechanism";
    private static final String KEY_PARAMETERS_ID = "parametersid";
    private static final String KEY_PUBLIC_KEY = "publickey";
    private static final String KEY_WRAPDATA = "wrapdata";
    private final AbstractAuthenticatedDiffieHellmanExchange$Mechanism mechanism;
    private final String parametersId;
    private final DHPrivateKey privateKey;
    private final BigInteger publicKey;
    private final byte[] wrapdata;
    
    public AuthenticatedDiffieHellmanRequestData(final JSONObject p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/netflix/msl/keyx/NetflixKeyExchangeScheme.AUTHENTICATED_DH:Lcom/netflix/msl/keyx/KeyExchangeScheme;
        //     4: invokespecial   com/netflix/msl/keyx/KeyRequestData.<init>:(Lcom/netflix/msl/keyx/KeyExchangeScheme;)V
        //     7: aload_1        
        //     8: ldc             "mechanism"
        //    10: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    13: astore_2       
        //    14: aload_0        
        //    15: aload_2        
        //    16: invokestatic    com/netflix/msl/keyx/AbstractAuthenticatedDiffieHellmanExchange$Mechanism.valueOf:(Ljava/lang/String;)Lcom/netflix/msl/keyx/AbstractAuthenticatedDiffieHellmanExchange$Mechanism;
        //    19: putfield        com/netflix/msl/keyx/AuthenticatedDiffieHellmanRequestData.mechanism:Lcom/netflix/msl/keyx/AbstractAuthenticatedDiffieHellmanExchange$Mechanism;
        //    22: getstatic       com/netflix/msl/keyx/AuthenticatedDiffieHellmanRequestData$1.$SwitchMap$com$netflix$msl$keyx$AbstractAuthenticatedDiffieHellmanExchange$Mechanism:[I
        //    25: aload_0        
        //    26: getfield        com/netflix/msl/keyx/AuthenticatedDiffieHellmanRequestData.mechanism:Lcom/netflix/msl/keyx/AbstractAuthenticatedDiffieHellmanExchange$Mechanism;
        //    29: invokevirtual   com/netflix/msl/keyx/AbstractAuthenticatedDiffieHellmanExchange$Mechanism.ordinal:()I
        //    32: iaload         
        //    33: tableswitch {
        //                2: 206
        //                3: 162
        //                4: 162
        //          default: 375
        //        }
        //    60: new             Lcom/netflix/msl/MslKeyExchangeException;
        //    63: dup            
        //    64: getstatic       com/netflix/msl/MslError.UNSUPPORTED_KEYX_MECHANISM:Lcom/netflix/msl/MslError;
        //    67: aload_0        
        //    68: getfield        com/netflix/msl/keyx/AuthenticatedDiffieHellmanRequestData.mechanism:Lcom/netflix/msl/keyx/AbstractAuthenticatedDiffieHellmanExchange$Mechanism;
        //    71: invokevirtual   com/netflix/msl/keyx/AbstractAuthenticatedDiffieHellmanExchange$Mechanism.name:()Ljava/lang/String;
        //    74: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //    77: athrow         
        //    78: astore_2       
        //    79: new             Lcom/netflix/msl/MslEncodingException;
        //    82: dup            
        //    83: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //    86: new             Ljava/lang/StringBuilder;
        //    89: dup            
        //    90: invokespecial   java/lang/StringBuilder.<init>:()V
        //    93: ldc             "keydata "
        //    95: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    98: aload_1        
        //    99: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   102: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   105: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   108: aload_2        
        //   109: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   112: athrow         
        //   113: astore_3       
        //   114: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   117: dup            
        //   118: getstatic       com/netflix/msl/MslError.UNIDENTIFIED_KEYX_MECHANISM:Lcom/netflix/msl/MslError;
        //   121: aload_2        
        //   122: aload_3        
        //   123: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   126: athrow         
        //   127: astore_2       
        //   128: new             Lcom/netflix/msl/MslEncodingException;
        //   131: dup            
        //   132: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   135: new             Ljava/lang/StringBuilder;
        //   138: dup            
        //   139: invokespecial   java/lang/StringBuilder.<init>:()V
        //   142: ldc             "keydata "
        //   144: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   147: aload_1        
        //   148: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   151: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   154: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   157: aload_2        
        //   158: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   161: athrow         
        //   162: aload_0        
        //   163: aconst_null    
        //   164: putfield        com/netflix/msl/keyx/AuthenticatedDiffieHellmanRequestData.wrapdata:[B
        //   167: aload_0        
        //   168: aload_1        
        //   169: ldc             "parametersid"
        //   171: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   174: putfield        com/netflix/msl/keyx/AuthenticatedDiffieHellmanRequestData.parametersId:Ljava/lang/String;
        //   177: aload_0        
        //   178: new             Ljava/math/BigInteger;
        //   181: dup            
        //   182: aload_1        
        //   183: ldc             "publickey"
        //   185: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   188: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //   191: invokestatic    com/netflix/msl/keyx/AbstractAuthenticatedDiffieHellmanExchange.correctNullBytes:([B)[B
        //   194: invokespecial   java/math/BigInteger.<init>:([B)V
        //   197: putfield        com/netflix/msl/keyx/AuthenticatedDiffieHellmanRequestData.publicKey:Ljava/math/BigInteger;
        //   200: aload_0        
        //   201: aconst_null    
        //   202: putfield        com/netflix/msl/keyx/AuthenticatedDiffieHellmanRequestData.privateKey:Ljavax/crypto/interfaces/DHPrivateKey;
        //   205: return         
        //   206: aload_0        
        //   207: aload_1        
        //   208: ldc             "wrapdata"
        //   210: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   213: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //   216: putfield        com/netflix/msl/keyx/AuthenticatedDiffieHellmanRequestData.wrapdata:[B
        //   219: aload_0        
        //   220: getfield        com/netflix/msl/keyx/AuthenticatedDiffieHellmanRequestData.wrapdata:[B
        //   223: ifnull          234
        //   226: aload_0        
        //   227: getfield        com/netflix/msl/keyx/AuthenticatedDiffieHellmanRequestData.wrapdata:[B
        //   230: arraylength    
        //   231: ifne            167
        //   234: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   237: dup            
        //   238: getstatic       com/netflix/msl/MslError.KEYX_WRAPPING_KEY_MISSING:Lcom/netflix/msl/MslError;
        //   241: new             Ljava/lang/StringBuilder;
        //   244: dup            
        //   245: invokespecial   java/lang/StringBuilder.<init>:()V
        //   248: ldc             "keydata "
        //   250: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   253: aload_1        
        //   254: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   257: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   260: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   263: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   266: athrow         
        //   267: astore_2       
        //   268: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   271: dup            
        //   272: getstatic       com/netflix/msl/MslError.KEYX_WRAPPING_KEY_MISSING:Lcom/netflix/msl/MslError;
        //   275: new             Ljava/lang/StringBuilder;
        //   278: dup            
        //   279: invokespecial   java/lang/StringBuilder.<init>:()V
        //   282: ldc             "keydata "
        //   284: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   287: aload_1        
        //   288: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   291: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   294: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   297: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   300: athrow         
        //   301: astore_2       
        //   302: new             Lcom/netflix/msl/MslEncodingException;
        //   305: dup            
        //   306: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   309: new             Ljava/lang/StringBuilder;
        //   312: dup            
        //   313: invokespecial   java/lang/StringBuilder.<init>:()V
        //   316: ldc             "keydata "
        //   318: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   321: aload_1        
        //   322: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   325: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   328: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   331: aload_2        
        //   332: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   335: athrow         
        //   336: astore_2       
        //   337: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   340: dup            
        //   341: getstatic       com/netflix/msl/MslError.KEYX_INVALID_PUBLIC_KEY:Lcom/netflix/msl/MslError;
        //   344: new             Ljava/lang/StringBuilder;
        //   347: dup            
        //   348: invokespecial   java/lang/StringBuilder.<init>:()V
        //   351: ldc             "keydata "
        //   353: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   356: aload_1        
        //   357: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   360: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   363: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   366: aload_2        
        //   367: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   370: athrow         
        //   371: astore_2       
        //   372: goto            337
        //   375: goto            60
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  7      14     127    162    Lcom/netflix/android/org/json/JSONException;
        //  14     22     113    127    Ljava/lang/IllegalArgumentException;
        //  14     22     127    162    Lcom/netflix/android/org/json/JSONException;
        //  22     60     78     113    Lcom/netflix/android/org/json/JSONException;
        //  60     78     78     113    Lcom/netflix/android/org/json/JSONException;
        //  114    127    127    162    Lcom/netflix/android/org/json/JSONException;
        //  162    167    78     113    Lcom/netflix/android/org/json/JSONException;
        //  167    200    301    336    Lcom/netflix/android/org/json/JSONException;
        //  167    200    371    375    Ljava/lang/IllegalArgumentException;
        //  167    200    336    337    Ljava/lang/NullPointerException;
        //  206    219    267    301    Ljava/lang/IllegalArgumentException;
        //  206    219    78     113    Lcom/netflix/android/org/json/JSONException;
        //  219    234    78     113    Lcom/netflix/android/org/json/JSONException;
        //  234    267    78     113    Lcom/netflix/android/org/json/JSONException;
        //  268    301    78     113    Lcom/netflix/android/org/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0167:
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
    
    public AuthenticatedDiffieHellmanRequestData(final AbstractAuthenticatedDiffieHellmanExchange$Mechanism mechanism, final String parametersId, final BigInteger publicKey, final DHPrivateKey privateKey, final byte[] wrapdata) {
        super(NetflixKeyExchangeScheme.AUTHENTICATED_DH);
        this.mechanism = mechanism;
        switch (AuthenticatedDiffieHellmanRequestData$1.$SwitchMap$com$netflix$msl$keyx$AbstractAuthenticatedDiffieHellmanExchange$Mechanism[mechanism.ordinal()]) {
            default: {
                this.wrapdata = null;
                break;
            }
            case 1: {
                if (wrapdata == null) {
                    throw new MslInternalException("Previous wrapping key based key exchange requires the previous wrapping key data.");
                }
                this.wrapdata = wrapdata;
                break;
            }
        }
        this.parametersId = parametersId;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof AuthenticatedDiffieHellmanRequestData)) {
                return false;
            }
            final AuthenticatedDiffieHellmanRequestData authenticatedDiffieHellmanRequestData = (AuthenticatedDiffieHellmanRequestData)o;
            final boolean equals = Arrays.equals(this.wrapdata, authenticatedDiffieHellmanRequestData.wrapdata);
            boolean b;
            if (this.privateKey == authenticatedDiffieHellmanRequestData.privateKey || (this.privateKey != null && authenticatedDiffieHellmanRequestData.privateKey != null && Arrays.equals(this.privateKey.getEncoded(), authenticatedDiffieHellmanRequestData.privateKey.getEncoded()))) {
                b = true;
            }
            else {
                b = false;
            }
            if (!super.equals(o) || !this.mechanism.equals(authenticatedDiffieHellmanRequestData.mechanism) || !equals || !this.parametersId.equalsIgnoreCase(authenticatedDiffieHellmanRequestData.parametersId) || !this.publicKey.equals(authenticatedDiffieHellmanRequestData.publicKey) || !b) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    protected JSONObject getKeydata() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("mechanism", this.mechanism.name());
        if (this.wrapdata != null) {
            jsonObject.put("wrapdata", Base64.encode(this.wrapdata));
        }
        jsonObject.put("parametersid", this.parametersId);
        jsonObject.put("publickey", Base64.encode(AbstractAuthenticatedDiffieHellmanExchange.correctNullBytes(this.publicKey.toByteArray())));
        return jsonObject;
    }
    
    public AbstractAuthenticatedDiffieHellmanExchange$Mechanism getMechanism() {
        return this.mechanism;
    }
    
    public String getParametersId() {
        return this.parametersId;
    }
    
    public DHPrivateKey getPrivateKey() {
        return this.privateKey;
    }
    
    public BigInteger getPublicKey() {
        return this.publicKey;
    }
    
    public byte[] getWrapdata() {
        return this.wrapdata;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.wrapdata != null) {
            hashCode2 = Arrays.hashCode(this.wrapdata);
        }
        else {
            hashCode2 = 0;
        }
        if (this.privateKey != null) {
            hashCode = Arrays.hashCode(this.privateKey.getEncoded());
        }
        return hashCode2 ^ (super.hashCode() ^ this.mechanism.hashCode()) ^ this.parametersId.hashCode() ^ this.publicKey.hashCode() ^ hashCode;
    }
}
