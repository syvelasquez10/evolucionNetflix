// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import com.netflix.msl.util.Base64;
import java.util.Arrays;
import com.netflix.android.org.json.JSONObject;
import java.security.PublicKey;
import java.security.PrivateKey;

public class AsymmetricWrappedExchange$RequestData extends KeyRequestData
{
    private static final String KEY_KEY_PAIR_ID = "keypairid";
    private static final String KEY_MECHANISM = "mechanism";
    private static final String KEY_PUBLIC_KEY = "publickey";
    private final String keyPairId;
    private final AsymmetricWrappedExchange$RequestData$Mechanism mechanism;
    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    
    public AsymmetricWrappedExchange$RequestData(final JSONObject p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/netflix/msl/keyx/KeyExchangeScheme.ASYMMETRIC_WRAPPED:Lcom/netflix/msl/keyx/KeyExchangeScheme;
        //     4: invokespecial   com/netflix/msl/keyx/KeyRequestData.<init>:(Lcom/netflix/msl/keyx/KeyExchangeScheme;)V
        //     7: aload_0        
        //     8: aload_1        
        //     9: ldc             "keypairid"
        //    11: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    14: putfield        com/netflix/msl/keyx/AsymmetricWrappedExchange$RequestData.keyPairId:Ljava/lang/String;
        //    17: aload_1        
        //    18: ldc             "mechanism"
        //    20: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    23: astore_2       
        //    24: aload_0        
        //    25: aload_2        
        //    26: invokestatic    com/netflix/msl/keyx/AsymmetricWrappedExchange$RequestData$Mechanism.valueOf:(Ljava/lang/String;)Lcom/netflix/msl/keyx/AsymmetricWrappedExchange$RequestData$Mechanism;
        //    29: putfield        com/netflix/msl/keyx/AsymmetricWrappedExchange$RequestData.mechanism:Lcom/netflix/msl/keyx/AsymmetricWrappedExchange$RequestData$Mechanism;
        //    32: aload_1        
        //    33: ldc             "publickey"
        //    35: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    38: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //    41: astore_2       
        //    42: getstatic       com/netflix/msl/keyx/AsymmetricWrappedExchange$1.$SwitchMap$com$netflix$msl$keyx$AsymmetricWrappedExchange$RequestData$Mechanism:[I
        //    45: aload_0        
        //    46: getfield        com/netflix/msl/keyx/AsymmetricWrappedExchange$RequestData.mechanism:Lcom/netflix/msl/keyx/AsymmetricWrappedExchange$RequestData$Mechanism;
        //    49: invokevirtual   com/netflix/msl/keyx/AsymmetricWrappedExchange$RequestData$Mechanism.ordinal:()I
        //    52: iaload         
        //    53: tableswitch {
        //                2: 225
        //                3: 225
        //                4: 225
        //                5: 225
        //                6: 225
        //          default: 321
        //        }
        //    88: new             Lcom/netflix/msl/MslCryptoException;
        //    91: dup            
        //    92: getstatic       com/netflix/msl/MslError.UNSUPPORTED_KEYX_MECHANISM:Lcom/netflix/msl/MslError;
        //    95: aload_0        
        //    96: getfield        com/netflix/msl/keyx/AsymmetricWrappedExchange$RequestData.mechanism:Lcom/netflix/msl/keyx/AsymmetricWrappedExchange$RequestData$Mechanism;
        //    99: invokevirtual   com/netflix/msl/keyx/AsymmetricWrappedExchange$RequestData$Mechanism.name:()Ljava/lang/String;
        //   102: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   105: athrow         
        //   106: astore_2       
        //   107: new             Lcom/netflix/msl/MslCryptoException;
        //   110: dup            
        //   111: getstatic       com/netflix/msl/MslError.INVALID_PUBLIC_KEY:Lcom/netflix/msl/MslError;
        //   114: new             Ljava/lang/StringBuilder;
        //   117: dup            
        //   118: invokespecial   java/lang/StringBuilder.<init>:()V
        //   121: ldc             "keydata "
        //   123: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   126: aload_1        
        //   127: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   130: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   133: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   136: aload_2        
        //   137: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   140: athrow         
        //   141: astore_3       
        //   142: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   145: dup            
        //   146: getstatic       com/netflix/msl/MslError.UNIDENTIFIED_KEYX_MECHANISM:Lcom/netflix/msl/MslError;
        //   149: aload_2        
        //   150: aload_3        
        //   151: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   154: athrow         
        //   155: astore_2       
        //   156: new             Lcom/netflix/msl/MslEncodingException;
        //   159: dup            
        //   160: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   163: new             Ljava/lang/StringBuilder;
        //   166: dup            
        //   167: invokespecial   java/lang/StringBuilder.<init>:()V
        //   170: ldc             "keydata "
        //   172: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   175: aload_1        
        //   176: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   179: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   182: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   185: aload_2        
        //   186: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   189: athrow         
        //   190: astore_2       
        //   191: new             Lcom/netflix/msl/MslCryptoException;
        //   194: dup            
        //   195: getstatic       com/netflix/msl/MslError.KEYX_INVALID_PUBLIC_KEY:Lcom/netflix/msl/MslError;
        //   198: new             Ljava/lang/StringBuilder;
        //   201: dup            
        //   202: invokespecial   java/lang/StringBuilder.<init>:()V
        //   205: ldc             "keydata "
        //   207: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   210: aload_1        
        //   211: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   214: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   217: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   220: aload_2        
        //   221: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   224: athrow         
        //   225: aload_0        
        //   226: ldc             "RSA"
        //   228: invokestatic    com/netflix/msl/crypto/CryptoCache.getKeyFactory:(Ljava/lang/String;)Ljava/security/KeyFactory;
        //   231: new             Ljava/security/spec/X509EncodedKeySpec;
        //   234: dup            
        //   235: aload_2        
        //   236: invokespecial   java/security/spec/X509EncodedKeySpec.<init>:([B)V
        //   239: invokevirtual   java/security/KeyFactory.generatePublic:(Ljava/security/spec/KeySpec;)Ljava/security/PublicKey;
        //   242: putfield        com/netflix/msl/keyx/AsymmetricWrappedExchange$RequestData.publicKey:Ljava/security/PublicKey;
        //   245: aload_0        
        //   246: aconst_null    
        //   247: putfield        com/netflix/msl/keyx/AsymmetricWrappedExchange$RequestData.privateKey:Ljava/security/PrivateKey;
        //   250: return         
        //   251: astore_2       
        //   252: new             Lcom/netflix/msl/MslCryptoException;
        //   255: dup            
        //   256: getstatic       com/netflix/msl/MslError.UNSUPPORTED_KEYX_MECHANISM:Lcom/netflix/msl/MslError;
        //   259: new             Ljava/lang/StringBuilder;
        //   262: dup            
        //   263: invokespecial   java/lang/StringBuilder.<init>:()V
        //   266: ldc             "keydata "
        //   268: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   271: aload_1        
        //   272: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   275: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   278: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   281: aload_2        
        //   282: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   285: athrow         
        //   286: astore_2       
        //   287: new             Lcom/netflix/msl/MslCryptoException;
        //   290: dup            
        //   291: getstatic       com/netflix/msl/MslError.INVALID_PUBLIC_KEY:Lcom/netflix/msl/MslError;
        //   294: new             Ljava/lang/StringBuilder;
        //   297: dup            
        //   298: invokespecial   java/lang/StringBuilder.<init>:()V
        //   301: ldc             "keydata "
        //   303: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   306: aload_1        
        //   307: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   310: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   313: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   316: aload_2        
        //   317: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   320: athrow         
        //   321: goto            88
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  7      24     155    190    Lcom/netflix/android/org/json/JSONException;
        //  24     32     141    155    Ljava/lang/IllegalArgumentException;
        //  24     32     155    190    Lcom/netflix/android/org/json/JSONException;
        //  32     42     190    225    Ljava/lang/IllegalArgumentException;
        //  32     42     155    190    Lcom/netflix/android/org/json/JSONException;
        //  42     88     106    141    Ljava/lang/NullPointerException;
        //  42     88     251    286    Ljava/security/NoSuchAlgorithmException;
        //  42     88     286    321    Ljava/security/spec/InvalidKeySpecException;
        //  88     106    106    141    Ljava/lang/NullPointerException;
        //  88     106    251    286    Ljava/security/NoSuchAlgorithmException;
        //  88     106    286    321    Ljava/security/spec/InvalidKeySpecException;
        //  142    155    155    190    Lcom/netflix/android/org/json/JSONException;
        //  191    225    155    190    Lcom/netflix/android/org/json/JSONException;
        //  225    245    106    141    Ljava/lang/NullPointerException;
        //  225    245    251    286    Ljava/security/NoSuchAlgorithmException;
        //  225    245    286    321    Ljava/security/spec/InvalidKeySpecException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0088:
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
    
    public AsymmetricWrappedExchange$RequestData(final String keyPairId, final AsymmetricWrappedExchange$RequestData$Mechanism mechanism, final PublicKey publicKey, final PrivateKey privateKey) {
        super(KeyExchangeScheme.ASYMMETRIC_WRAPPED);
        this.keyPairId = keyPairId;
        this.mechanism = mechanism;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof AsymmetricWrappedExchange$RequestData)) {
                return false;
            }
            final AsymmetricWrappedExchange$RequestData asymmetricWrappedExchange$RequestData = (AsymmetricWrappedExchange$RequestData)o;
            boolean b;
            if (this.privateKey == asymmetricWrappedExchange$RequestData.privateKey || (this.privateKey != null && asymmetricWrappedExchange$RequestData.privateKey != null && Arrays.equals(this.privateKey.getEncoded(), asymmetricWrappedExchange$RequestData.privateKey.getEncoded()))) {
                b = true;
            }
            else {
                b = false;
            }
            if (!super.equals(o) || !this.keyPairId.equals(asymmetricWrappedExchange$RequestData.keyPairId) || !this.mechanism.equals(asymmetricWrappedExchange$RequestData.mechanism) || !Arrays.equals(this.publicKey.getEncoded(), asymmetricWrappedExchange$RequestData.publicKey.getEncoded()) || !b) {
                return false;
            }
        }
        return true;
    }
    
    public String getKeyPairId() {
        return this.keyPairId;
    }
    
    @Override
    protected JSONObject getKeydata() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("keypairid", this.keyPairId);
        jsonObject.put("mechanism", this.mechanism.name());
        jsonObject.put("publickey", Base64.encode(this.publicKey.getEncoded()));
        return jsonObject;
    }
    
    public AsymmetricWrappedExchange$RequestData$Mechanism getMechanism() {
        return this.mechanism;
    }
    
    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }
    
    public PublicKey getPublicKey() {
        return this.publicKey;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.privateKey != null) {
            hashCode = Arrays.hashCode(this.privateKey.getEncoded());
        }
        else {
            hashCode = 0;
        }
        return hashCode ^ (super.hashCode() ^ this.keyPairId.hashCode() ^ this.mechanism.hashCode() ^ Arrays.hashCode(this.publicKey.getEncoded()));
    }
}
