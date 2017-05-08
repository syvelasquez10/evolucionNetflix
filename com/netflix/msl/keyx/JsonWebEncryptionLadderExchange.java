// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import com.netflix.msl.MslMasterTokenException;
import com.netflix.msl.crypto.SessionCryptoContext;
import com.netflix.msl.crypto.JsonWebKey;
import com.netflix.msl.crypto.JsonWebKey$Algorithm;
import com.netflix.msl.crypto.JsonWebKey$Usage;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.entityauth.EntityAuthenticationFactory;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import com.netflix.msl.crypto.JsonWebEncryptionCryptoContext$CekCryptoContext;
import com.netflix.msl.crypto.JsonWebEncryptionCryptoContext;
import com.netflix.msl.crypto.JsonWebEncryptionCryptoContext$Format;
import com.netflix.msl.crypto.JsonWebEncryptionCryptoContext$Encryption;
import com.netflix.msl.crypto.JsonWebEncryptionCryptoContext$AesKwCryptoContext;
import com.netflix.msl.entityauth.EntityAuthenticationData;
import com.netflix.msl.entityauth.EntityAuthenticationScheme;
import com.netflix.msl.entityauth.PresharedAuthenticationData;
import com.netflix.msl.MslKeyExchangeException;
import com.netflix.msl.MslError;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.util.MslContext;
import com.netflix.msl.util.AuthenticationUtils;
import java.nio.charset.Charset;

public class JsonWebEncryptionLadderExchange extends KeyExchangeFactory
{
    private static final Charset UTF_8;
    private final AuthenticationUtils authutils;
    private final WrapCryptoContextRepository repository;
    
    static {
        UTF_8 = Charset.forName("UTF-8");
    }
    
    public JsonWebEncryptionLadderExchange(final WrapCryptoContextRepository repository, final AuthenticationUtils authutils) {
        super(KeyExchangeScheme.JWE_LADDER);
        this.repository = repository;
        this.authutils = authutils;
    }
    
    private static ICryptoContext createCryptoContext(final MslContext mslContext, final JsonWebEncryptionLadderExchange$Mechanism jsonWebEncryptionLadderExchange$Mechanism, final byte[] array, final String s) {
        switch (JsonWebEncryptionLadderExchange$1.$SwitchMap$com$netflix$msl$keyx$JsonWebEncryptionLadderExchange$Mechanism[jsonWebEncryptionLadderExchange$Mechanism.ordinal()]) {
            default: {
                throw new MslKeyExchangeException(MslError.UNSUPPORTED_KEYX_MECHANISM, jsonWebEncryptionLadderExchange$Mechanism.name());
            }
            case 2: {
                final PresharedAuthenticationData presharedAuthenticationData = new PresharedAuthenticationData(s);
                final EntityAuthenticationFactory entityAuthenticationFactory = mslContext.getEntityAuthenticationFactory(EntityAuthenticationScheme.PSK);
                if (entityAuthenticationFactory == null) {
                    throw new MslKeyExchangeException(MslError.UNSUPPORTED_KEYX_MECHANISM, jsonWebEncryptionLadderExchange$Mechanism.name());
                }
                return new JsonWebEncryptionCryptoContext(mslContext, new JsonWebEncryptionCryptoContext$AesKwCryptoContext(entityAuthenticationFactory.getCryptoContext(mslContext, presharedAuthenticationData)), JsonWebEncryptionCryptoContext$Encryption.A128GCM, JsonWebEncryptionCryptoContext$Format.JWE_JS);
            }
            case 1: {
                final byte[] unwrap = mslContext.getMslCryptoContext().unwrap(array);
                if (unwrap == null || unwrap.length == 0) {
                    throw new MslKeyExchangeException(MslError.KEYX_WRAPPING_KEY_MISSING);
                }
                return new JsonWebEncryptionCryptoContext(mslContext, new JsonWebEncryptionCryptoContext$AesKwCryptoContext(new SecretKeySpec(unwrap, "AES")), JsonWebEncryptionCryptoContext$Encryption.A128GCM, JsonWebEncryptionCryptoContext$Format.JWE_JS);
            }
        }
    }
    
    @Override
    protected KeyRequestData createRequestData(final MslContext mslContext, final JSONObject jsonObject) {
        return new JsonWebEncryptionLadderExchange$RequestData(jsonObject);
    }
    
    @Override
    protected KeyResponseData createResponseData(final MslContext mslContext, final MasterToken masterToken, final JSONObject jsonObject) {
        return new JsonWebEncryptionLadderExchange$ResponseData(masterToken, jsonObject);
    }
    
    @Override
    public KeyExchangeFactory$KeyExchangeData generateResponse(final MslContext mslContext, final KeyRequestData keyRequestData, final EntityAuthenticationData entityAuthenticationData) {
        if (!(keyRequestData instanceof JsonWebEncryptionLadderExchange$RequestData)) {
            throw new MslInternalException("Key request data " + keyRequestData.getClass().getName() + " was not created by this factory.");
        }
        final JsonWebEncryptionLadderExchange$RequestData jsonWebEncryptionLadderExchange$RequestData = (JsonWebEncryptionLadderExchange$RequestData)keyRequestData;
        final String identity = entityAuthenticationData.getIdentity();
        if (!this.authutils.isSchemePermitted(identity, this.getScheme())) {
            throw new MslKeyExchangeException(MslError.KEYX_INCORRECT_DATA, "Authentication Sscheme for entity not permitted " + identity + ":" + this.getScheme()).setEntityAuthenticationData(entityAuthenticationData);
        }
        final long nextLong = mslContext.getRandom().nextLong();
        final byte[] array = new byte[16];
        mslContext.getRandom().nextBytes(array);
        final SecretKeySpec secretKeySpec = new SecretKeySpec(array, "AES");
        final byte[] wrap = mslContext.getMslCryptoContext().wrap(array);
        final byte[] array2 = new byte[16];
        final byte[] array3 = new byte[32];
        mslContext.getRandom().nextBytes(array2);
        mslContext.getRandom().nextBytes(array3);
        final SecretKeySpec secretKeySpec2 = new SecretKeySpec(array2, "AES");
        final SecretKeySpec secretKeySpec3 = new SecretKeySpec(array3, "HmacSHA256");
        final byte[] wrap2 = createCryptoContext(mslContext, jsonWebEncryptionLadderExchange$RequestData.getMechanism(), jsonWebEncryptionLadderExchange$RequestData.getWrapdata(), identity).wrap(new JsonWebKey(JsonWebKey$Usage.wrap, JsonWebKey$Algorithm.A128KW, false, String.valueOf(nextLong), secretKeySpec).toJSONString().getBytes(JsonWebEncryptionLadderExchange.UTF_8));
        final JsonWebEncryptionCryptoContext jsonWebEncryptionCryptoContext = new JsonWebEncryptionCryptoContext(mslContext, new JsonWebEncryptionCryptoContext$AesKwCryptoContext(secretKeySpec), JsonWebEncryptionCryptoContext$Encryption.A128GCM, JsonWebEncryptionCryptoContext$Format.JWE_JS);
        final JsonWebKey jsonWebKey = new JsonWebKey(JsonWebKey$Usage.enc, JsonWebKey$Algorithm.A128CBC, false, null, secretKeySpec2);
        final JsonWebKey jsonWebKey2 = new JsonWebKey(JsonWebKey$Usage.sig, JsonWebKey$Algorithm.HS256, false, null, secretKeySpec3);
        final byte[] wrap3 = jsonWebEncryptionCryptoContext.wrap(jsonWebKey.toJSONString().getBytes(JsonWebEncryptionLadderExchange.UTF_8));
        final byte[] wrap4 = jsonWebEncryptionCryptoContext.wrap(jsonWebKey2.toJSONString().getBytes(JsonWebEncryptionLadderExchange.UTF_8));
        final MasterToken masterToken = mslContext.getTokenFactory().createMasterToken(mslContext, entityAuthenticationData, secretKeySpec2, secretKeySpec3, null);
        return new KeyExchangeFactory$KeyExchangeData(new JsonWebEncryptionLadderExchange$ResponseData(masterToken, wrap2, wrap, wrap3, wrap4), new SessionCryptoContext(mslContext, masterToken));
    }
    
    @Override
    public KeyExchangeFactory$KeyExchangeData generateResponse(final MslContext mslContext, final KeyRequestData keyRequestData, MasterToken renewMasterToken) {
        if (!(keyRequestData instanceof JsonWebEncryptionLadderExchange$RequestData)) {
            throw new MslInternalException("Key request data " + keyRequestData.getClass().getName() + " was not created by this factory.");
        }
        final JsonWebEncryptionLadderExchange$RequestData jsonWebEncryptionLadderExchange$RequestData = (JsonWebEncryptionLadderExchange$RequestData)keyRequestData;
        if (!renewMasterToken.isVerified()) {
            throw new MslMasterTokenException(MslError.MASTERTOKEN_UNTRUSTED, renewMasterToken);
        }
        final String identity = renewMasterToken.getIdentity();
        if (!this.authutils.isSchemePermitted(identity, this.getScheme())) {
            throw new MslKeyExchangeException(MslError.KEYX_INCORRECT_DATA, "Authentication scheme for entity not permitted " + identity + ":" + this.getScheme()).setMasterToken(renewMasterToken);
        }
        final long nextLong = mslContext.getRandom().nextLong();
        final byte[] array = new byte[16];
        mslContext.getRandom().nextBytes(array);
        final SecretKeySpec secretKeySpec = new SecretKeySpec(array, "AES");
        final byte[] wrap = mslContext.getMslCryptoContext().wrap(array);
        final byte[] array2 = new byte[16];
        final byte[] array3 = new byte[32];
        mslContext.getRandom().nextBytes(array2);
        mslContext.getRandom().nextBytes(array3);
        final SecretKeySpec secretKeySpec2 = new SecretKeySpec(array2, "AES");
        final SecretKeySpec secretKeySpec3 = new SecretKeySpec(array3, "HmacSHA256");
        final byte[] wrap2 = createCryptoContext(mslContext, jsonWebEncryptionLadderExchange$RequestData.getMechanism(), jsonWebEncryptionLadderExchange$RequestData.getWrapdata(), identity).wrap(new JsonWebKey(JsonWebKey$Usage.wrap, JsonWebKey$Algorithm.A128KW, false, String.valueOf(nextLong), secretKeySpec).toJSONString().getBytes(JsonWebEncryptionLadderExchange.UTF_8));
        final JsonWebEncryptionCryptoContext jsonWebEncryptionCryptoContext = new JsonWebEncryptionCryptoContext(mslContext, new JsonWebEncryptionCryptoContext$AesKwCryptoContext(secretKeySpec), JsonWebEncryptionCryptoContext$Encryption.A128GCM, JsonWebEncryptionCryptoContext$Format.JWE_JS);
        final JsonWebKey jsonWebKey = new JsonWebKey(JsonWebKey$Usage.enc, JsonWebKey$Algorithm.A128CBC, false, null, secretKeySpec2);
        final JsonWebKey jsonWebKey2 = new JsonWebKey(JsonWebKey$Usage.sig, JsonWebKey$Algorithm.HS256, false, null, secretKeySpec3);
        final byte[] wrap3 = jsonWebEncryptionCryptoContext.wrap(jsonWebKey.toJSONString().getBytes(JsonWebEncryptionLadderExchange.UTF_8));
        final byte[] wrap4 = jsonWebEncryptionCryptoContext.wrap(jsonWebKey2.toJSONString().getBytes(JsonWebEncryptionLadderExchange.UTF_8));
        renewMasterToken = mslContext.getTokenFactory().renewMasterToken(mslContext, renewMasterToken, secretKeySpec2, secretKeySpec3, null);
        return new KeyExchangeFactory$KeyExchangeData(new JsonWebEncryptionLadderExchange$ResponseData(renewMasterToken, wrap2, wrap, wrap3, wrap4), new SessionCryptoContext(mslContext, renewMasterToken));
    }
    
    @Override
    public ICryptoContext getCryptoContext(final MslContext p0, final KeyRequestData p1, final KeyResponseData p2, final MasterToken p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: instanceof      Lcom/netflix/msl/keyx/JsonWebEncryptionLadderExchange$RequestData;
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
        //    46: checkcast       Lcom/netflix/msl/keyx/JsonWebEncryptionLadderExchange$RequestData;
        //    49: astore          4
        //    51: aload_3        
        //    52: instanceof      Lcom/netflix/msl/keyx/JsonWebEncryptionLadderExchange$ResponseData;
        //    55: ifne            97
        //    58: new             Lcom/netflix/msl/MslInternalException;
        //    61: dup            
        //    62: new             Ljava/lang/StringBuilder;
        //    65: dup            
        //    66: invokespecial   java/lang/StringBuilder.<init>:()V
        //    69: ldc_w           "Key response data "
        //    72: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    75: aload_3        
        //    76: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    79: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //    82: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    85: ldc             " was not created by this factory."
        //    87: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    90: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    93: invokespecial   com/netflix/msl/MslInternalException.<init>:(Ljava/lang/String;)V
        //    96: athrow         
        //    97: aload_3        
        //    98: checkcast       Lcom/netflix/msl/keyx/JsonWebEncryptionLadderExchange$ResponseData;
        //   101: astore          5
        //   103: aload           4
        //   105: invokevirtual   com/netflix/msl/keyx/JsonWebEncryptionLadderExchange$RequestData.getMechanism:()Lcom/netflix/msl/keyx/JsonWebEncryptionLadderExchange$Mechanism;
        //   108: astore_2       
        //   109: aload           4
        //   111: invokevirtual   com/netflix/msl/keyx/JsonWebEncryptionLadderExchange$RequestData.getWrapdata:()[B
        //   114: astore          6
        //   116: aload_1        
        //   117: aconst_null    
        //   118: invokevirtual   com/netflix/msl/util/MslContext.getEntityAuthenticationData:(Lcom/netflix/msl/util/MslContext$ReauthCode;)Lcom/netflix/msl/entityauth/EntityAuthenticationData;
        //   121: astore          4
        //   123: aload           4
        //   125: invokevirtual   com/netflix/msl/entityauth/EntityAuthenticationData.getIdentity:()Ljava/lang/String;
        //   128: astore          7
        //   130: getstatic       com/netflix/msl/keyx/JsonWebEncryptionLadderExchange$1.$SwitchMap$com$netflix$msl$keyx$JsonWebEncryptionLadderExchange$Mechanism:[I
        //   133: aload_2        
        //   134: invokevirtual   com/netflix/msl/keyx/JsonWebEncryptionLadderExchange$Mechanism.ordinal:()I
        //   137: iaload         
        //   138: tableswitch {
        //                2: 462
        //                3: 180
        //          default: 160
        //        }
        //   160: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   163: dup            
        //   164: getstatic       com/netflix/msl/MslError.UNSUPPORTED_KEYX_MECHANISM:Lcom/netflix/msl/MslError;
        //   167: aload_2        
        //   168: invokevirtual   com/netflix/msl/keyx/JsonWebEncryptionLadderExchange$Mechanism.name:()Ljava/lang/String;
        //   171: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   174: aload           4
        //   176: invokevirtual   com/netflix/msl/MslKeyExchangeException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslKeyExchangeException;
        //   179: athrow         
        //   180: new             Lcom/netflix/msl/entityauth/PresharedAuthenticationData;
        //   183: dup            
        //   184: aload           7
        //   186: invokespecial   com/netflix/msl/entityauth/PresharedAuthenticationData.<init>:(Ljava/lang/String;)V
        //   189: astore_3       
        //   190: aload_1        
        //   191: getstatic       com/netflix/msl/entityauth/EntityAuthenticationScheme.PSK:Lcom/netflix/msl/entityauth/EntityAuthenticationScheme;
        //   194: invokevirtual   com/netflix/msl/util/MslContext.getEntityAuthenticationFactory:(Lcom/netflix/msl/entityauth/EntityAuthenticationScheme;)Lcom/netflix/msl/entityauth/EntityAuthenticationFactory;
        //   197: astore          8
        //   199: aload           8
        //   201: ifnonnull       224
        //   204: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   207: dup            
        //   208: getstatic       com/netflix/msl/MslError.UNSUPPORTED_KEYX_MECHANISM:Lcom/netflix/msl/MslError;
        //   211: aload_2        
        //   212: invokevirtual   com/netflix/msl/keyx/JsonWebEncryptionLadderExchange$Mechanism.name:()Ljava/lang/String;
        //   215: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   218: aload           4
        //   220: invokevirtual   com/netflix/msl/MslKeyExchangeException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslKeyExchangeException;
        //   223: athrow         
        //   224: new             Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext;
        //   227: dup            
        //   228: aload_1        
        //   229: new             Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$AesKwCryptoContext;
        //   232: dup            
        //   233: aload           8
        //   235: aload_1        
        //   236: aload_3        
        //   237: invokevirtual   com/netflix/msl/entityauth/EntityAuthenticationFactory.getCryptoContext:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/crypto/ICryptoContext;
        //   240: invokespecial   com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$AesKwCryptoContext.<init>:(Lcom/netflix/msl/crypto/ICryptoContext;)V
        //   243: getstatic       com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption.A128GCM:Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption;
        //   246: getstatic       com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Format.JWE_JS:Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Format;
        //   249: invokespecial   com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.<init>:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$CekCryptoContext;Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption;Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Format;)V
        //   252: astore_2       
        //   253: new             Ljava/lang/String;
        //   256: dup            
        //   257: aload_2        
        //   258: aload           5
        //   260: invokevirtual   com/netflix/msl/keyx/JsonWebEncryptionLadderExchange$ResponseData.getWrapKey:()[B
        //   263: invokeinterface com/netflix/msl/crypto/ICryptoContext.unwrap:([B)[B
        //   268: getstatic       com/netflix/msl/keyx/JsonWebEncryptionLadderExchange.UTF_8:Ljava/nio/charset/Charset;
        //   271: invokespecial   java/lang/String.<init>:([BLjava/nio/charset/Charset;)V
        //   274: astore_2       
        //   275: new             Lcom/netflix/msl/crypto/JsonWebKey;
        //   278: dup            
        //   279: new             Lcom/netflix/android/org/json/JSONObject;
        //   282: dup            
        //   283: aload_2        
        //   284: invokespecial   com/netflix/android/org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   287: invokespecial   com/netflix/msl/crypto/JsonWebKey.<init>:(Lcom/netflix/android/org/json/JSONObject;)V
        //   290: astore_3       
        //   291: new             Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext;
        //   294: dup            
        //   295: aload_1        
        //   296: new             Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$AesKwCryptoContext;
        //   299: dup            
        //   300: aload_3        
        //   301: invokevirtual   com/netflix/msl/crypto/JsonWebKey.getSecretKey:()Ljavax/crypto/SecretKey;
        //   304: invokespecial   com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$AesKwCryptoContext.<init>:(Ljavax/crypto/SecretKey;)V
        //   307: getstatic       com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption.A128GCM:Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption;
        //   310: getstatic       com/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Format.JWE_JS:Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Format;
        //   313: invokespecial   com/netflix/msl/crypto/JsonWebEncryptionCryptoContext.<init>:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$CekCryptoContext;Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Encryption;Lcom/netflix/msl/crypto/JsonWebEncryptionCryptoContext$Format;)V
        //   316: astore_2       
        //   317: aload_2        
        //   318: aload           5
        //   320: invokevirtual   com/netflix/msl/keyx/JsonWebEncryptionLadderExchange$ResponseData.getEncryptionKey:()[B
        //   323: invokeinterface com/netflix/msl/crypto/ICryptoContext.unwrap:([B)[B
        //   328: astore_3       
        //   329: aload_2        
        //   330: aload           5
        //   332: invokevirtual   com/netflix/msl/keyx/JsonWebEncryptionLadderExchange$ResponseData.getHmacKey:()[B
        //   335: invokeinterface com/netflix/msl/crypto/ICryptoContext.unwrap:([B)[B
        //   340: astore          8
        //   342: new             Ljava/lang/String;
        //   345: dup            
        //   346: aload_3        
        //   347: getstatic       com/netflix/msl/keyx/JsonWebEncryptionLadderExchange.UTF_8:Ljava/nio/charset/Charset;
        //   350: invokespecial   java/lang/String.<init>:([BLjava/nio/charset/Charset;)V
        //   353: astore_3       
        //   354: new             Ljava/lang/String;
        //   357: dup            
        //   358: aload           8
        //   360: getstatic       com/netflix/msl/keyx/JsonWebEncryptionLadderExchange.UTF_8:Ljava/nio/charset/Charset;
        //   363: invokespecial   java/lang/String.<init>:([BLjava/nio/charset/Charset;)V
        //   366: astore          8
        //   368: new             Lcom/netflix/msl/crypto/JsonWebKey;
        //   371: dup            
        //   372: new             Lcom/netflix/android/org/json/JSONObject;
        //   375: dup            
        //   376: aload_3        
        //   377: invokespecial   com/netflix/android/org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   380: invokespecial   com/netflix/msl/crypto/JsonWebKey.<init>:(Lcom/netflix/android/org/json/JSONObject;)V
        //   383: astore          9
        //   385: new             Lcom/netflix/msl/crypto/JsonWebKey;
        //   388: dup            
        //   389: new             Lcom/netflix/android/org/json/JSONObject;
        //   392: dup            
        //   393: aload           8
        //   395: invokespecial   com/netflix/android/org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   398: invokespecial   com/netflix/msl/crypto/JsonWebKey.<init>:(Lcom/netflix/android/org/json/JSONObject;)V
        //   401: astore_3       
        //   402: aload           5
        //   404: invokevirtual   com/netflix/msl/keyx/JsonWebEncryptionLadderExchange$ResponseData.getWrapdata:()[B
        //   407: astore          4
        //   409: aload_0        
        //   410: getfield        com/netflix/msl/keyx/JsonWebEncryptionLadderExchange.repository:Lcom/netflix/msl/keyx/WrapCryptoContextRepository;
        //   413: aload           4
        //   415: aload_2        
        //   416: invokeinterface com/netflix/msl/keyx/WrapCryptoContextRepository.addCryptoContext:([BLcom/netflix/msl/crypto/ICryptoContext;)V
        //   421: aload           6
        //   423: ifnull          437
        //   426: aload_0        
        //   427: getfield        com/netflix/msl/keyx/JsonWebEncryptionLadderExchange.repository:Lcom/netflix/msl/keyx/WrapCryptoContextRepository;
        //   430: aload           6
        //   432: invokeinterface com/netflix/msl/keyx/WrapCryptoContextRepository.removeCryptoContext:([B)V
        //   437: new             Lcom/netflix/msl/crypto/SessionCryptoContext;
        //   440: dup            
        //   441: aload_1        
        //   442: aload           5
        //   444: invokevirtual   com/netflix/msl/keyx/JsonWebEncryptionLadderExchange$ResponseData.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   447: aload           7
        //   449: aload           9
        //   451: invokevirtual   com/netflix/msl/crypto/JsonWebKey.getSecretKey:()Ljavax/crypto/SecretKey;
        //   454: aload_3        
        //   455: invokevirtual   com/netflix/msl/crypto/JsonWebKey.getSecretKey:()Ljavax/crypto/SecretKey;
        //   458: invokespecial   com/netflix/msl/crypto/SessionCryptoContext.<init>:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;Ljava/lang/String;Ljavax/crypto/SecretKey;Ljavax/crypto/SecretKey;)V
        //   461: areturn        
        //   462: aload_0        
        //   463: getfield        com/netflix/msl/keyx/JsonWebEncryptionLadderExchange.repository:Lcom/netflix/msl/keyx/WrapCryptoContextRepository;
        //   466: aload           6
        //   468: invokeinterface com/netflix/msl/keyx/WrapCryptoContextRepository.getCryptoContext:([B)Lcom/netflix/msl/crypto/ICryptoContext;
        //   473: astore_3       
        //   474: aload_3        
        //   475: astore_2       
        //   476: aload_3        
        //   477: ifnonnull       253
        //   480: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   483: dup            
        //   484: getstatic       com/netflix/msl/MslError.KEYX_WRAPPING_KEY_MISSING:Lcom/netflix/msl/MslError;
        //   487: aload           6
        //   489: invokestatic    com/netflix/msl/util/Base64.encode:([B)Ljava/lang/String;
        //   492: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   495: aload           4
        //   497: invokevirtual   com/netflix/msl/MslKeyExchangeException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslKeyExchangeException;
        //   500: athrow         
        //   501: astore_1       
        //   502: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   505: dup            
        //   506: getstatic       com/netflix/msl/MslError.INVALID_JWK:Lcom/netflix/msl/MslError;
        //   509: aload_2        
        //   510: aload_1        
        //   511: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   514: aload           4
        //   516: invokevirtual   com/netflix/msl/MslKeyExchangeException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslKeyExchangeException;
        //   519: athrow         
        //   520: astore_1       
        //   521: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   524: dup            
        //   525: getstatic       com/netflix/msl/MslError.INVALID_JWK:Lcom/netflix/msl/MslError;
        //   528: aload_3        
        //   529: aload_1        
        //   530: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   533: aload           4
        //   535: invokevirtual   com/netflix/msl/MslKeyExchangeException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslKeyExchangeException;
        //   538: athrow         
        //   539: astore_1       
        //   540: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   543: dup            
        //   544: getstatic       com/netflix/msl/MslError.INVALID_JWK:Lcom/netflix/msl/MslError;
        //   547: aload           8
        //   549: aload_1        
        //   550: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   553: aload           4
        //   555: invokevirtual   com/netflix/msl/MslKeyExchangeException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslKeyExchangeException;
        //   558: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  275    291    501    520    Lcom/netflix/android/org/json/JSONException;
        //  368    385    520    539    Lcom/netflix/android/org/json/JSONException;
        //  385    402    539    559    Lcom/netflix/android/org/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 246, Size: 246
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
}
