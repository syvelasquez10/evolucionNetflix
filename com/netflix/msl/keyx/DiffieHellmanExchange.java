// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import com.netflix.msl.util.MslContext$ReauthCode;
import java.math.BigInteger;
import com.netflix.msl.tokens.TokenFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.InvalidKeySpecException;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.crypto.SessionCryptoContext;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import java.security.spec.KeySpec;
import javax.crypto.spec.DHPublicKeySpec;
import com.netflix.msl.MslKeyExchangeException;
import com.netflix.msl.MslMasterTokenException;
import com.netflix.msl.MslError;
import com.netflix.msl.entityauth.EntityAuthenticationData;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.MslContext;
import java.security.MessageDigest;
import javax.crypto.KeyAgreement;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import com.netflix.msl.MslInternalException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import com.netflix.msl.crypto.CryptoCache;
import javax.crypto.spec.DHParameterSpec;
import java.security.PrivateKey;
import java.security.PublicKey;
import com.netflix.msl.util.AuthenticationUtils;

public class DiffieHellmanExchange extends KeyExchangeFactory
{
    private static final String KEY_PARAMETERS_ID = "parametersid";
    private static final String KEY_PUBLIC_KEY = "publickey";
    private final AuthenticationUtils authutils;
    private final DiffieHellmanParameters params;
    
    public DiffieHellmanExchange(final DiffieHellmanParameters params, final AuthenticationUtils authutils) {
        super(KeyExchangeScheme.DIFFIE_HELLMAN);
        this.params = params;
        this.authutils = authutils;
    }
    
    private static byte[] correctNullBytes(final byte[] array) {
        int n = 0;
        int n2 = 0;
        while (n < array.length && array[n] == 0) {
            ++n2;
            ++n;
        }
        if (n2 == 1) {
            return array;
        }
        final int n3 = array.length - n2;
        final byte[] array2 = new byte[n3 + 1];
        array2[0] = 0;
        System.arraycopy(array, n2, array2, 1, n3);
        return array2;
    }
    
    private static DiffieHellmanExchange$SessionKeys deriveSessionKeys(final PublicKey publicKey, final PrivateKey privateKey, final DHParameterSpec dhParameterSpec) {
        byte[] correctNullBytes;
        try {
            final KeyAgreement keyAgreement = CryptoCache.getKeyAgreement("DiffieHellman");
            keyAgreement.init(privateKey, dhParameterSpec);
            keyAgreement.doPhase(publicKey, true);
            correctNullBytes = correctNullBytes(keyAgreement.generateSecret());
            final String s = "SHA-384";
            final MessageDigest messageDigest = CryptoCache.getMessageDigest(s);
            final MessageDigest messageDigest2 = messageDigest;
            final byte[] array = correctNullBytes;
            final byte[] array2 = messageDigest2.digest(array);
            final int n = 16;
            final byte[] array3 = new byte[n];
            final byte[] array4 = array2;
            final int n2 = 0;
            final byte[] array5 = array3;
            final int n3 = 0;
            final byte[] array6 = array3;
            final int n4 = array6.length;
            System.arraycopy(array4, n2, array5, n3, n4);
            final int n5 = 32;
            final byte[] array7 = new byte[n5];
            final byte[] array8 = array2;
            final byte[] array9 = array3;
            final int n6 = array9.length;
            final byte[] array10 = array7;
            final int n7 = 0;
            final byte[] array11 = array7;
            final int n8 = array11.length;
            System.arraycopy(array8, n6, array10, n7, n8);
            final byte[] array12 = array3;
            final String s2 = "AES";
            final SecretKeySpec secretKeySpec = new SecretKeySpec(array12, s2);
            final byte[] array13 = array7;
            final String s3 = "HmacSHA256";
            final SecretKeySpec secretKeySpec2 = new SecretKeySpec(array13, s3);
            return new DiffieHellmanExchange$SessionKeys(secretKeySpec, secretKeySpec2);
        }
        catch (NoSuchAlgorithmException ex) {
            throw new MslInternalException("DiffieHellman algorithm not found.", ex);
        }
        catch (InvalidKeyException ex2) {
            throw new MslInternalException("Diffie-Hellman private key or generated public key rejected by Diffie-Hellman key agreement.", ex2);
        }
        catch (InvalidAlgorithmParameterException ex3) {
            throw new MslInternalException("Diffie-Hellman algorithm parameters rejected by Diffie-Hellman key agreement.", ex3);
        }
        try {
            final String s = "SHA-384";
            final MessageDigest messageDigest2;
            final MessageDigest messageDigest = messageDigest2 = CryptoCache.getMessageDigest(s);
            final byte[] array = correctNullBytes;
            final byte[] array2 = messageDigest2.digest(array);
            final int n = 16;
            final byte[] array3 = new byte[n];
            final byte[] array4 = array2;
            final int n2 = 0;
            final byte[] array5 = array3;
            final int n3 = 0;
            final byte[] array6 = array3;
            final int n4 = array6.length;
            System.arraycopy(array4, n2, array5, n3, n4);
            final int n5 = 32;
            final byte[] array7 = new byte[n5];
            final byte[] array8 = array2;
            final byte[] array9 = array3;
            final int n6 = array9.length;
            final byte[] array10 = array7;
            final int n7 = 0;
            final byte[] array11 = array7;
            final int n8 = array11.length;
            System.arraycopy(array8, n6, array10, n7, n8);
            final byte[] array12 = array3;
            final String s2 = "AES";
            final SecretKeySpec secretKeySpec = new SecretKeySpec(array12, s2);
            final byte[] array13 = array7;
            final String s3 = "HmacSHA256";
            final SecretKeySpec secretKeySpec2 = new SecretKeySpec(array13, s3);
            return new DiffieHellmanExchange$SessionKeys(secretKeySpec, secretKeySpec2);
        }
        catch (NoSuchAlgorithmException ex4) {
            throw new MslInternalException("SHA-384 algorithm not found.", ex4);
        }
    }
    
    @Override
    protected KeyRequestData createRequestData(final MslContext mslContext, final JSONObject jsonObject) {
        return new DiffieHellmanExchange$RequestData(jsonObject);
    }
    
    @Override
    protected KeyResponseData createResponseData(final MslContext mslContext, final MasterToken masterToken, final JSONObject jsonObject) {
        return new DiffieHellmanExchange$ResponseData(masterToken, jsonObject);
    }
    
    @Override
    public KeyExchangeFactory$KeyExchangeData generateResponse(final MslContext p0, final KeyRequestData p1, final EntityAuthenticationData p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: instanceof      Lcom/netflix/msl/keyx/DiffieHellmanExchange$RequestData;
        //     4: ifne            45
        //     7: new             Lcom/netflix/msl/MslInternalException;
        //    10: dup            
        //    11: new             Ljava/lang/StringBuilder;
        //    14: dup            
        //    15: invokespecial   java/lang/StringBuilder.<init>:()V
        //    18: ldc             "Key request data "
        //    20: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    23: aload_2        
        //    24: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    27: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //    30: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    33: ldc             " was not created by this factory."
        //    35: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    38: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    41: invokespecial   com/netflix/msl/MslInternalException.<init>:(Ljava/lang/String;)V
        //    44: athrow         
        //    45: aload_2        
        //    46: checkcast       Lcom/netflix/msl/keyx/DiffieHellmanExchange$RequestData;
        //    49: astore          4
        //    51: aload_3        
        //    52: invokevirtual   com/netflix/msl/entityauth/EntityAuthenticationData.getIdentity:()Ljava/lang/String;
        //    55: astore_2       
        //    56: aload_0        
        //    57: getfield        com/netflix/msl/keyx/DiffieHellmanExchange.authutils:Lcom/netflix/msl/util/AuthenticationUtils;
        //    60: aload_2        
        //    61: aload_0        
        //    62: invokevirtual   com/netflix/msl/keyx/DiffieHellmanExchange.getScheme:()Lcom/netflix/msl/keyx/KeyExchangeScheme;
        //    65: invokeinterface com/netflix/msl/util/AuthenticationUtils.isSchemePermitted:(Ljava/lang/String;Lcom/netflix/msl/keyx/KeyExchangeScheme;)Z
        //    70: ifne            119
        //    73: new             Lcom/netflix/msl/MslKeyExchangeException;
        //    76: dup            
        //    77: getstatic       com/netflix/msl/MslError.KEYX_INCORRECT_DATA:Lcom/netflix/msl/MslError;
        //    80: new             Ljava/lang/StringBuilder;
        //    83: dup            
        //    84: invokespecial   java/lang/StringBuilder.<init>:()V
        //    87: ldc             "Authentication scheme for entity not permitted "
        //    89: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    92: aload_2        
        //    93: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    96: ldc             ":"
        //    98: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   101: aload_0        
        //   102: invokevirtual   com/netflix/msl/keyx/DiffieHellmanExchange.getScheme:()Lcom/netflix/msl/keyx/KeyExchangeScheme;
        //   105: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   108: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   111: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   114: aload_3        
        //   115: invokevirtual   com/netflix/msl/MslKeyExchangeException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslKeyExchangeException;
        //   118: athrow         
        //   119: aload           4
        //   121: invokevirtual   com/netflix/msl/keyx/DiffieHellmanExchange$RequestData.getParametersId:()Ljava/lang/String;
        //   124: astore_2       
        //   125: aload_0        
        //   126: getfield        com/netflix/msl/keyx/DiffieHellmanExchange.params:Lcom/netflix/msl/keyx/DiffieHellmanParameters;
        //   129: aload_2        
        //   130: invokeinterface com/netflix/msl/keyx/DiffieHellmanParameters.getParameterSpec:(Ljava/lang/String;)Ljavax/crypto/spec/DHParameterSpec;
        //   135: astore          5
        //   137: aload           5
        //   139: ifnonnull       158
        //   142: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   145: dup            
        //   146: getstatic       com/netflix/msl/MslError.UNKNOWN_KEYX_PARAMETERS_ID:Lcom/netflix/msl/MslError;
        //   149: aload_2        
        //   150: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   153: aload_3        
        //   154: invokevirtual   com/netflix/msl/MslKeyExchangeException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslKeyExchangeException;
        //   157: athrow         
        //   158: ldc             "DiffieHellman"
        //   160: invokestatic    com/netflix/msl/crypto/CryptoCache.getKeyFactory:(Ljava/lang/String;)Ljava/security/KeyFactory;
        //   163: new             Ljavax/crypto/spec/DHPublicKeySpec;
        //   166: dup            
        //   167: aload           4
        //   169: invokevirtual   com/netflix/msl/keyx/DiffieHellmanExchange$RequestData.getPublicKey:()Ljava/math/BigInteger;
        //   172: aload           5
        //   174: invokevirtual   javax/crypto/spec/DHParameterSpec.getP:()Ljava/math/BigInteger;
        //   177: aload           5
        //   179: invokevirtual   javax/crypto/spec/DHParameterSpec.getG:()Ljava/math/BigInteger;
        //   182: invokespecial   javax/crypto/spec/DHPublicKeySpec.<init>:(Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;)V
        //   185: invokevirtual   java/security/KeyFactory.generatePublic:(Ljava/security/spec/KeySpec;)Ljava/security/PublicKey;
        //   188: astore          6
        //   190: ldc             "DH"
        //   192: invokestatic    java/security/KeyPairGenerator.getInstance:(Ljava/lang/String;)Ljava/security/KeyPairGenerator;
        //   195: astore          4
        //   197: aload           4
        //   199: aload           5
        //   201: invokevirtual   java/security/KeyPairGenerator.initialize:(Ljava/security/spec/AlgorithmParameterSpec;)V
        //   204: aload           4
        //   206: invokevirtual   java/security/KeyPairGenerator.generateKeyPair:()Ljava/security/KeyPair;
        //   209: astore          7
        //   211: aload           7
        //   213: invokevirtual   java/security/KeyPair.getPublic:()Ljava/security/PublicKey;
        //   216: checkcast       Ljavax/crypto/interfaces/DHPublicKey;
        //   219: astore          4
        //   221: aload           7
        //   223: invokevirtual   java/security/KeyPair.getPrivate:()Ljava/security/PrivateKey;
        //   226: checkcast       Ljavax/crypto/interfaces/DHPrivateKey;
        //   229: astore          7
        //   231: aload           6
        //   233: aload           7
        //   235: aload           5
        //   237: invokestatic    com/netflix/msl/keyx/DiffieHellmanExchange.deriveSessionKeys:(Ljava/security/PublicKey;Ljava/security/PrivateKey;Ljavax/crypto/spec/DHParameterSpec;)Lcom/netflix/msl/keyx/DiffieHellmanExchange$SessionKeys;
        //   240: astore          5
        //   242: aload_1        
        //   243: invokevirtual   com/netflix/msl/util/MslContext.getTokenFactory:()Lcom/netflix/msl/tokens/TokenFactory;
        //   246: aload_1        
        //   247: aload_3        
        //   248: aload           5
        //   250: getfield        com/netflix/msl/keyx/DiffieHellmanExchange$SessionKeys.encryptionKey:Ljavax/crypto/SecretKey;
        //   253: aload           5
        //   255: getfield        com/netflix/msl/keyx/DiffieHellmanExchange$SessionKeys.hmacKey:Ljavax/crypto/SecretKey;
        //   258: aconst_null    
        //   259: invokeinterface com/netflix/msl/tokens/TokenFactory.createMasterToken:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/entityauth/EntityAuthenticationData;Ljavax/crypto/SecretKey;Ljavax/crypto/SecretKey;Lcom/netflix/android/org/json/JSONObject;)Lcom/netflix/msl/tokens/MasterToken;
        //   264: astore_3       
        //   265: new             Lcom/netflix/msl/crypto/SessionCryptoContext;
        //   268: dup            
        //   269: aload_1        
        //   270: aload_3        
        //   271: invokespecial   com/netflix/msl/crypto/SessionCryptoContext.<init>:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //   274: astore_1       
        //   275: new             Lcom/netflix/msl/keyx/KeyExchangeFactory$KeyExchangeData;
        //   278: dup            
        //   279: new             Lcom/netflix/msl/keyx/DiffieHellmanExchange$ResponseData;
        //   282: dup            
        //   283: aload_3        
        //   284: aload_2        
        //   285: aload           4
        //   287: invokeinterface javax/crypto/interfaces/DHPublicKey.getY:()Ljava/math/BigInteger;
        //   292: invokespecial   com/netflix/msl/keyx/DiffieHellmanExchange$ResponseData.<init>:(Lcom/netflix/msl/tokens/MasterToken;Ljava/lang/String;Ljava/math/BigInteger;)V
        //   295: aload_1        
        //   296: invokespecial   com/netflix/msl/keyx/KeyExchangeFactory$KeyExchangeData.<init>:(Lcom/netflix/msl/keyx/KeyResponseData;Lcom/netflix/msl/crypto/ICryptoContext;)V
        //   299: areturn        
        //   300: astore_1       
        //   301: new             Lcom/netflix/msl/MslInternalException;
        //   304: dup            
        //   305: ldc             "DiffieHellman algorithm not found."
        //   307: aload_1        
        //   308: invokespecial   com/netflix/msl/MslInternalException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   311: athrow         
        //   312: astore_1       
        //   313: new             Lcom/netflix/msl/MslInternalException;
        //   316: dup            
        //   317: ldc_w           "Diffie-Hellman public key specification rejected by Diffie-Hellman key factory."
        //   320: aload_1        
        //   321: invokespecial   com/netflix/msl/MslInternalException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   324: athrow         
        //   325: astore_1       
        //   326: new             Lcom/netflix/msl/MslInternalException;
        //   329: dup            
        //   330: ldc             "DiffieHellman algorithm not found."
        //   332: aload_1        
        //   333: invokespecial   com/netflix/msl/MslInternalException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   336: athrow         
        //   337: astore_1       
        //   338: new             Lcom/netflix/msl/MslInternalException;
        //   341: dup            
        //   342: ldc             "Diffie-Hellman algorithm parameters rejected by Diffie-Hellman key agreement."
        //   344: aload_1        
        //   345: invokespecial   com/netflix/msl/MslInternalException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   348: athrow         
        //   349: astore_1       
        //   350: new             Lcom/netflix/msl/MslInternalException;
        //   353: dup            
        //   354: ldc_w           "Master token constructed by token factory is not trusted."
        //   357: aload_1        
        //   358: invokespecial   com/netflix/msl/MslInternalException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   361: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                              
        //  -----  -----  -----  -----  --------------------------------------------------
        //  158    190    300    312    Ljava/security/NoSuchAlgorithmException;
        //  158    190    312    325    Ljava/security/spec/InvalidKeySpecException;
        //  190    231    325    337    Ljava/security/NoSuchAlgorithmException;
        //  190    231    337    349    Ljava/security/InvalidAlgorithmParameterException;
        //  265    275    349    362    Lcom/netflix/msl/MslMasterTokenException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 169, Size: 169
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
    public KeyExchangeFactory$KeyExchangeData generateResponse(final MslContext mslContext, final KeyRequestData keyRequestData, MasterToken masterToken) {
        if (!(keyRequestData instanceof DiffieHellmanExchange$RequestData)) {
            throw new MslInternalException("Key request data " + keyRequestData.getClass().getName() + " was not created by this factory.");
        }
        final DiffieHellmanExchange$RequestData diffieHellmanExchange$RequestData = (DiffieHellmanExchange$RequestData)keyRequestData;
        if (!masterToken.isVerified()) {
            throw new MslMasterTokenException(MslError.MASTERTOKEN_UNTRUSTED, masterToken);
        }
        final String identity = masterToken.getIdentity();
        if (!this.authutils.isSchemePermitted(identity, this.getScheme())) {
            throw new MslKeyExchangeException(MslError.KEYX_INCORRECT_DATA, "Authentication scheme for entity not permitted " + identity + ":" + this.getScheme()).setMasterToken(masterToken);
        }
        final String parametersId = diffieHellmanExchange$RequestData.getParametersId();
        final DHParameterSpec parameterSpec = this.params.getParameterSpec(parametersId);
        if (parameterSpec == null) {
            throw new MslKeyExchangeException(MslError.UNKNOWN_KEYX_PARAMETERS_ID, parametersId);
        }
        PublicKey generatePublic;
        try {
            generatePublic = CryptoCache.getKeyFactory("DiffieHellman").generatePublic(new DHPublicKeySpec(diffieHellmanExchange$RequestData.getPublicKey(), parameterSpec.getP(), parameterSpec.getG()));
            final String s = "DH";
            final KeyPairGenerator keyPairGenerator = CryptoCache.getKeyPairGenerator(s);
            final KeyPairGenerator keyPairGenerator3;
            final KeyPairGenerator keyPairGenerator2 = keyPairGenerator3 = keyPairGenerator;
            final DHParameterSpec dhParameterSpec = parameterSpec;
            keyPairGenerator3.initialize(dhParameterSpec);
            final KeyPairGenerator keyPairGenerator4 = keyPairGenerator2;
            final KeyPair keyPair = keyPairGenerator4.generateKeyPair();
            final KeyPair keyPair3;
            final KeyPair keyPair2 = keyPair3 = keyPair;
            final PublicKey publicKey = keyPair3.getPublic();
            final DHPublicKey dhPublicKey = (DHPublicKey)publicKey;
            final KeyPair keyPair4 = keyPair2;
            final PrivateKey privateKey = keyPair4.getPrivate();
            final DHPrivateKey dhPrivateKey = (DHPrivateKey)privateKey;
            final PublicKey publicKey2 = generatePublic;
            final DHPrivateKey dhPrivateKey2 = dhPrivateKey;
            final DHParameterSpec dhParameterSpec2 = parameterSpec;
            final DiffieHellmanExchange$SessionKeys diffieHellmanExchange$SessionKeys = deriveSessionKeys(publicKey2, dhPrivateKey2, dhParameterSpec2);
            final MslContext mslContext2 = mslContext;
            final TokenFactory tokenFactory = mslContext2.getTokenFactory();
            final MslContext mslContext3 = mslContext;
            final MasterToken masterToken2 = masterToken;
            final DiffieHellmanExchange$SessionKeys diffieHellmanExchange$SessionKeys2 = diffieHellmanExchange$SessionKeys;
            final SecretKey secretKey = diffieHellmanExchange$SessionKeys2.encryptionKey;
            final DiffieHellmanExchange$SessionKeys diffieHellmanExchange$SessionKeys3 = diffieHellmanExchange$SessionKeys;
            final SecretKey secretKey2 = diffieHellmanExchange$SessionKeys3.hmacKey;
            final JSONObject jsonObject = null;
            masterToken = tokenFactory.renewMasterToken(mslContext3, masterToken2, secretKey, secretKey2, jsonObject);
            final MslContext mslContext4 = mslContext;
            final MasterToken masterToken3 = masterToken;
            final SessionCryptoContext sessionCryptoContext = new SessionCryptoContext(mslContext4, masterToken3);
            final MasterToken masterToken4 = masterToken;
            final String s2 = parametersId;
            final DHPublicKey dhPublicKey2 = dhPublicKey;
            final BigInteger bigInteger = dhPublicKey2.getY();
            final DiffieHellmanExchange$ResponseData diffieHellmanExchange$ResponseData = new DiffieHellmanExchange$ResponseData(masterToken4, s2, bigInteger);
            final SessionCryptoContext sessionCryptoContext2 = sessionCryptoContext;
            return new KeyExchangeFactory$KeyExchangeData(diffieHellmanExchange$ResponseData, sessionCryptoContext2);
        }
        catch (NoSuchAlgorithmException ex) {
            throw new MslInternalException("DiffieHellman algorithm not found.", ex);
        }
        catch (InvalidKeySpecException ex2) {
            throw new MslInternalException("Diffie-Hellman public key specification rejected by Diffie-Hellman key factory.", ex2);
        }
        try {
            final String s = "DH";
            final KeyPairGenerator keyPairGenerator = CryptoCache.getKeyPairGenerator(s);
            final KeyPairGenerator keyPairGenerator3;
            final KeyPairGenerator keyPairGenerator2 = keyPairGenerator3 = keyPairGenerator;
            final DHParameterSpec dhParameterSpec = parameterSpec;
            keyPairGenerator3.initialize(dhParameterSpec);
            final KeyPairGenerator keyPairGenerator4 = keyPairGenerator2;
            final KeyPair keyPair = keyPairGenerator4.generateKeyPair();
            final KeyPair keyPair3;
            final KeyPair keyPair2 = keyPair3 = keyPair;
            final PublicKey publicKey = keyPair3.getPublic();
            final DHPublicKey dhPublicKey = (DHPublicKey)publicKey;
            final KeyPair keyPair4 = keyPair2;
            final PrivateKey privateKey = keyPair4.getPrivate();
            final DHPrivateKey dhPrivateKey = (DHPrivateKey)privateKey;
            final PublicKey publicKey2 = generatePublic;
            final DHPrivateKey dhPrivateKey2 = dhPrivateKey;
            final DHParameterSpec dhParameterSpec2 = parameterSpec;
            final DiffieHellmanExchange$SessionKeys diffieHellmanExchange$SessionKeys = deriveSessionKeys(publicKey2, dhPrivateKey2, dhParameterSpec2);
            final MslContext mslContext2 = mslContext;
            final TokenFactory tokenFactory = mslContext2.getTokenFactory();
            final MslContext mslContext3 = mslContext;
            final MasterToken masterToken2 = masterToken;
            final DiffieHellmanExchange$SessionKeys diffieHellmanExchange$SessionKeys2 = diffieHellmanExchange$SessionKeys;
            final SecretKey secretKey = diffieHellmanExchange$SessionKeys2.encryptionKey;
            final DiffieHellmanExchange$SessionKeys diffieHellmanExchange$SessionKeys3 = diffieHellmanExchange$SessionKeys;
            final SecretKey secretKey2 = diffieHellmanExchange$SessionKeys3.hmacKey;
            final JSONObject jsonObject = null;
            masterToken = tokenFactory.renewMasterToken(mslContext3, masterToken2, secretKey, secretKey2, jsonObject);
            final MslContext mslContext4 = mslContext;
            final MasterToken masterToken3 = masterToken;
            final SessionCryptoContext sessionCryptoContext = new SessionCryptoContext(mslContext4, masterToken3);
            final MasterToken masterToken4 = masterToken;
            final String s2 = parametersId;
            final DHPublicKey dhPublicKey2 = dhPublicKey;
            final BigInteger bigInteger = dhPublicKey2.getY();
            final DiffieHellmanExchange$ResponseData diffieHellmanExchange$ResponseData = new DiffieHellmanExchange$ResponseData(masterToken4, s2, bigInteger);
            final SessionCryptoContext sessionCryptoContext2 = sessionCryptoContext;
            return new KeyExchangeFactory$KeyExchangeData(diffieHellmanExchange$ResponseData, sessionCryptoContext2);
        }
        catch (NoSuchAlgorithmException ex3) {
            throw new MslInternalException("DiffieHellman algorithm not found.", ex3);
        }
        catch (InvalidAlgorithmParameterException ex4) {
            throw new MslInternalException("Diffie-Hellman algorithm parameters rejected by Diffie-Hellman key agreement.", ex4);
        }
    }
    
    @Override
    public ICryptoContext getCryptoContext(final MslContext mslContext, final KeyRequestData keyRequestData, final KeyResponseData keyResponseData, final MasterToken masterToken) {
        if (!(keyRequestData instanceof DiffieHellmanExchange$RequestData)) {
            throw new MslInternalException("Key request data " + keyRequestData.getClass().getName() + " was not created by this factory.");
        }
        final DiffieHellmanExchange$RequestData diffieHellmanExchange$RequestData = (DiffieHellmanExchange$RequestData)keyRequestData;
        if (!(keyResponseData instanceof DiffieHellmanExchange$ResponseData)) {
            throw new MslInternalException("Key response data " + keyResponseData.getClass().getName() + " was not created by this factory.");
        }
        final DiffieHellmanExchange$ResponseData diffieHellmanExchange$ResponseData = (DiffieHellmanExchange$ResponseData)keyResponseData;
        final String parametersId = diffieHellmanExchange$RequestData.getParametersId();
        final String parametersId2 = diffieHellmanExchange$ResponseData.getParametersId();
        if (!parametersId.equals(parametersId2)) {
            throw new MslKeyExchangeException(MslError.KEYX_RESPONSE_REQUEST_MISMATCH, "request " + parametersId + "; response " + parametersId2).setMasterToken(masterToken);
        }
        final DHPrivateKey privateKey = diffieHellmanExchange$RequestData.getPrivateKey();
        if (privateKey == null) {
            throw new MslKeyExchangeException(MslError.KEYX_PRIVATE_KEY_MISSING, "request Diffie-Hellman private key").setMasterToken(masterToken);
        }
        final DHParameterSpec params = privateKey.getParams();
        try {
            final PublicKey generatePublic = CryptoCache.getKeyFactory("DiffieHellman").generatePublic(new DHPublicKeySpec(diffieHellmanExchange$ResponseData.getPublicKey(), params.getP(), params.getG()));
            final String identity = mslContext.getEntityAuthenticationData(null).getIdentity();
            final DiffieHellmanExchange$SessionKeys deriveSessionKeys = deriveSessionKeys(generatePublic, privateKey, params);
            return new SessionCryptoContext(mslContext, diffieHellmanExchange$ResponseData.getMasterToken(), identity, deriveSessionKeys.encryptionKey, deriveSessionKeys.hmacKey);
        }
        catch (NoSuchAlgorithmException ex) {
            throw new MslInternalException("DiffieHellman algorithm not found.", ex);
        }
        catch (InvalidKeySpecException ex2) {
            throw new MslKeyExchangeException(MslError.KEYX_INVALID_PUBLIC_KEY, "Diffie-Hellman public key specification rejected by Diffie-Hellman key factory.", ex2);
        }
    }
}
