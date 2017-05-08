// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import com.netflix.msl.MslMasterTokenException;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.crypto.SessionCryptoContext;
import com.netflix.msl.crypto.JsonWebKey;
import com.netflix.msl.crypto.JsonWebKey$Algorithm;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.entityauth.EntityAuthenticationFactory;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import com.netflix.msl.entityauth.EntityAuthenticationData;
import com.netflix.msl.entityauth.EntityAuthenticationScheme;
import com.netflix.msl.entityauth.PresharedAuthenticationData;
import com.netflix.msl.MslKeyExchangeException;
import com.netflix.msl.MslError;
import com.netflix.msl.util.MslContext;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import com.netflix.msl.util.AuthenticationUtils;
import java.nio.charset.Charset;
import com.netflix.msl.crypto.JsonWebKey$KeyOp;
import java.util.Set;

public class JsonWebKeyLadderExchange extends KeyExchangeFactory
{
    private static final Set<JsonWebKey$KeyOp> ENCRYPT_DECRYPT;
    private static final Set<JsonWebKey$KeyOp> SIGN_VERIFY;
    private static final Charset UTF_8;
    private static final String WRAP_KEY_ID = "wrapKeyId";
    private static final Set<JsonWebKey$KeyOp> WRAP_UNWRAP;
    private final AuthenticationUtils authutils;
    private final WrapCryptoContextRepository repository;
    
    static {
        UTF_8 = Charset.forName("UTF-8");
        ENCRYPT_DECRYPT = new HashSet<JsonWebKey$KeyOp>(Arrays.asList(JsonWebKey$KeyOp.encrypt, JsonWebKey$KeyOp.decrypt));
        WRAP_UNWRAP = new HashSet<JsonWebKey$KeyOp>(Arrays.asList(JsonWebKey$KeyOp.wrapKey, JsonWebKey$KeyOp.unwrapKey));
        SIGN_VERIFY = new HashSet<JsonWebKey$KeyOp>(Arrays.asList(JsonWebKey$KeyOp.sign, JsonWebKey$KeyOp.verify));
    }
    
    public JsonWebKeyLadderExchange(final WrapCryptoContextRepository repository, final AuthenticationUtils authutils) {
        super(KeyExchangeScheme.JWK_LADDER);
        this.repository = repository;
        this.authutils = authutils;
    }
    
    private static JsonWebKeyLadderExchange$JwkCryptoContext createCryptoContext(final MslContext mslContext, final JsonWebKeyLadderExchange$Mechanism jsonWebKeyLadderExchange$Mechanism, final byte[] array, final String s) {
        switch (JsonWebKeyLadderExchange$1.$SwitchMap$com$netflix$msl$keyx$JsonWebKeyLadderExchange$Mechanism[jsonWebKeyLadderExchange$Mechanism.ordinal()]) {
            default: {
                throw new MslKeyExchangeException(MslError.UNSUPPORTED_KEYX_MECHANISM, jsonWebKeyLadderExchange$Mechanism.name());
            }
            case 2: {
                final PresharedAuthenticationData presharedAuthenticationData = new PresharedAuthenticationData(s);
                final EntityAuthenticationFactory entityAuthenticationFactory = mslContext.getEntityAuthenticationFactory(EntityAuthenticationScheme.PSK);
                if (entityAuthenticationFactory == null) {
                    throw new MslKeyExchangeException(MslError.UNSUPPORTED_KEYX_MECHANISM, jsonWebKeyLadderExchange$Mechanism.name());
                }
                return new JsonWebKeyLadderExchange$AesKwJwkCryptoContext(entityAuthenticationFactory.getCryptoContext(mslContext, presharedAuthenticationData));
            }
            case 1: {
                final byte[] unwrap = mslContext.getMslCryptoContext().unwrap(array);
                if (unwrap == null || unwrap.length == 0) {
                    throw new MslKeyExchangeException(MslError.KEYX_WRAPPING_KEY_MISSING);
                }
                return new JsonWebKeyLadderExchange$AesKwJwkCryptoContext(new SecretKeySpec(unwrap, "AES"));
            }
        }
    }
    
    @Override
    protected KeyRequestData createRequestData(final MslContext mslContext, final JSONObject jsonObject) {
        return new JsonWebKeyLadderExchange$RequestData(jsonObject);
    }
    
    @Override
    protected KeyResponseData createResponseData(final MslContext mslContext, final MasterToken masterToken, final JSONObject jsonObject) {
        return new JsonWebKeyLadderExchange$ResponseData(masterToken, jsonObject);
    }
    
    @Override
    public KeyExchangeFactory$KeyExchangeData generateResponse(final MslContext mslContext, final KeyRequestData keyRequestData, final EntityAuthenticationData entityAuthenticationData) {
        if (!(keyRequestData instanceof JsonWebKeyLadderExchange$RequestData)) {
            throw new MslInternalException("Key request data " + keyRequestData.getClass().getName() + " was not created by this factory.");
        }
        final JsonWebKeyLadderExchange$RequestData jsonWebKeyLadderExchange$RequestData = (JsonWebKeyLadderExchange$RequestData)keyRequestData;
        final String identity = entityAuthenticationData.getIdentity();
        if (!this.authutils.isSchemePermitted(identity, this.getScheme())) {
            throw new MslKeyExchangeException(MslError.KEYX_INCORRECT_DATA, "Authentication scheme for entity not permitted " + identity + ":" + this.getScheme()).setEntityAuthenticationData(entityAuthenticationData);
        }
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
        final byte[] wrap2 = createCryptoContext(mslContext, jsonWebKeyLadderExchange$RequestData.getMechanism(), jsonWebKeyLadderExchange$RequestData.getWrapdata(), identity).wrap(new JsonWebKey(JsonWebKeyLadderExchange.WRAP_UNWRAP, JsonWebKey$Algorithm.A128KW, false, "wrapKeyId", secretKeySpec).toJSONString().getBytes(JsonWebKeyLadderExchange.UTF_8));
        final JsonWebKeyLadderExchange$AesKwJwkCryptoContext jsonWebKeyLadderExchange$AesKwJwkCryptoContext = new JsonWebKeyLadderExchange$AesKwJwkCryptoContext(secretKeySpec);
        final JsonWebKey jsonWebKey = new JsonWebKey(JsonWebKeyLadderExchange.ENCRYPT_DECRYPT, JsonWebKey$Algorithm.A128CBC, false, null, secretKeySpec2);
        final JsonWebKey jsonWebKey2 = new JsonWebKey(JsonWebKeyLadderExchange.SIGN_VERIFY, JsonWebKey$Algorithm.HS256, false, null, secretKeySpec3);
        final byte[] wrap3 = jsonWebKeyLadderExchange$AesKwJwkCryptoContext.wrap(jsonWebKey.toJSONString().getBytes(JsonWebKeyLadderExchange.UTF_8));
        final byte[] wrap4 = jsonWebKeyLadderExchange$AesKwJwkCryptoContext.wrap(jsonWebKey2.toJSONString().getBytes(JsonWebKeyLadderExchange.UTF_8));
        final MasterToken masterToken = mslContext.getTokenFactory().createMasterToken(mslContext, entityAuthenticationData, secretKeySpec2, secretKeySpec3, null);
        return new KeyExchangeFactory$KeyExchangeData(new JsonWebKeyLadderExchange$ResponseData(masterToken, wrap2, wrap, wrap3, wrap4), new SessionCryptoContext(mslContext, masterToken));
    }
    
    @Override
    public KeyExchangeFactory$KeyExchangeData generateResponse(final MslContext mslContext, final KeyRequestData keyRequestData, MasterToken renewMasterToken) {
        if (!(keyRequestData instanceof JsonWebKeyLadderExchange$RequestData)) {
            throw new MslInternalException("Key request data " + keyRequestData.getClass().getName() + " was not created by this factory.");
        }
        final JsonWebKeyLadderExchange$RequestData jsonWebKeyLadderExchange$RequestData = (JsonWebKeyLadderExchange$RequestData)keyRequestData;
        if (!renewMasterToken.isVerified()) {
            throw new MslMasterTokenException(MslError.MASTERTOKEN_UNTRUSTED, renewMasterToken);
        }
        final JsonWebKeyLadderExchange$Mechanism mechanism = jsonWebKeyLadderExchange$RequestData.getMechanism();
        final byte[] wrapdata = jsonWebKeyLadderExchange$RequestData.getWrapdata();
        final String identity = renewMasterToken.getIdentity();
        if (!this.authutils.isSchemePermitted(identity, this.getScheme())) {
            throw new MslKeyExchangeException(MslError.KEYX_INCORRECT_DATA, "Authentication scheme for entity not permitted " + identity + ":" + this.getScheme()).setMasterToken(renewMasterToken);
        }
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
        final byte[] wrap2 = createCryptoContext(mslContext, mechanism, wrapdata, identity).wrap(new JsonWebKey(JsonWebKeyLadderExchange.WRAP_UNWRAP, JsonWebKey$Algorithm.A128KW, false, "wrapKeyId", secretKeySpec).toJSONString().getBytes(JsonWebKeyLadderExchange.UTF_8));
        final JsonWebKeyLadderExchange$AesKwJwkCryptoContext jsonWebKeyLadderExchange$AesKwJwkCryptoContext = new JsonWebKeyLadderExchange$AesKwJwkCryptoContext(secretKeySpec);
        final JsonWebKey jsonWebKey = new JsonWebKey(JsonWebKeyLadderExchange.ENCRYPT_DECRYPT, JsonWebKey$Algorithm.A128CBC, false, null, secretKeySpec2);
        final JsonWebKey jsonWebKey2 = new JsonWebKey(JsonWebKeyLadderExchange.SIGN_VERIFY, JsonWebKey$Algorithm.HS256, false, null, secretKeySpec3);
        final byte[] wrap3 = jsonWebKeyLadderExchange$AesKwJwkCryptoContext.wrap(jsonWebKey.toJSONString().getBytes(JsonWebKeyLadderExchange.UTF_8));
        final byte[] wrap4 = jsonWebKeyLadderExchange$AesKwJwkCryptoContext.wrap(jsonWebKey2.toJSONString().getBytes(JsonWebKeyLadderExchange.UTF_8));
        renewMasterToken = mslContext.getTokenFactory().renewMasterToken(mslContext, renewMasterToken, secretKeySpec2, secretKeySpec3, null);
        return new KeyExchangeFactory$KeyExchangeData(new JsonWebKeyLadderExchange$ResponseData(renewMasterToken, wrap2, wrap, wrap3, wrap4), new SessionCryptoContext(mslContext, renewMasterToken));
    }
    
    @Override
    public ICryptoContext getCryptoContext(final MslContext p0, final KeyRequestData p1, final KeyResponseData p2, final MasterToken p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: instanceof      Lcom/netflix/msl/keyx/JsonWebKeyLadderExchange$RequestData;
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
        //    46: checkcast       Lcom/netflix/msl/keyx/JsonWebKeyLadderExchange$RequestData;
        //    49: astore          4
        //    51: aload_3        
        //    52: instanceof      Lcom/netflix/msl/keyx/JsonWebKeyLadderExchange$ResponseData;
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
        //    98: checkcast       Lcom/netflix/msl/keyx/JsonWebKeyLadderExchange$ResponseData;
        //   101: astore          5
        //   103: aload           4
        //   105: invokevirtual   com/netflix/msl/keyx/JsonWebKeyLadderExchange$RequestData.getMechanism:()Lcom/netflix/msl/keyx/JsonWebKeyLadderExchange$Mechanism;
        //   108: astore_2       
        //   109: aload           4
        //   111: invokevirtual   com/netflix/msl/keyx/JsonWebKeyLadderExchange$RequestData.getWrapdata:()[B
        //   114: astore          6
        //   116: aload_1        
        //   117: aconst_null    
        //   118: invokevirtual   com/netflix/msl/util/MslContext.getEntityAuthenticationData:(Lcom/netflix/msl/util/MslContext$ReauthCode;)Lcom/netflix/msl/entityauth/EntityAuthenticationData;
        //   121: astore          4
        //   123: aload           4
        //   125: invokevirtual   com/netflix/msl/entityauth/EntityAuthenticationData.getIdentity:()Ljava/lang/String;
        //   128: astore          7
        //   130: getstatic       com/netflix/msl/keyx/JsonWebKeyLadderExchange$1.$SwitchMap$com$netflix$msl$keyx$JsonWebKeyLadderExchange$Mechanism:[I
        //   133: aload_2        
        //   134: invokevirtual   com/netflix/msl/keyx/JsonWebKeyLadderExchange$Mechanism.ordinal:()I
        //   137: iaload         
        //   138: tableswitch {
        //                2: 434
        //                3: 180
        //          default: 160
        //        }
        //   160: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   163: dup            
        //   164: getstatic       com/netflix/msl/MslError.UNSUPPORTED_KEYX_MECHANISM:Lcom/netflix/msl/MslError;
        //   167: aload_2        
        //   168: invokevirtual   com/netflix/msl/keyx/JsonWebKeyLadderExchange$Mechanism.name:()Ljava/lang/String;
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
        //   212: invokevirtual   com/netflix/msl/keyx/JsonWebKeyLadderExchange$Mechanism.name:()Ljava/lang/String;
        //   215: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   218: aload           4
        //   220: invokevirtual   com/netflix/msl/MslKeyExchangeException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslKeyExchangeException;
        //   223: athrow         
        //   224: new             Lcom/netflix/msl/keyx/JsonWebKeyLadderExchange$AesKwJwkCryptoContext;
        //   227: dup            
        //   228: aload           8
        //   230: aload_1        
        //   231: aload_3        
        //   232: invokevirtual   com/netflix/msl/entityauth/EntityAuthenticationFactory.getCryptoContext:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/crypto/ICryptoContext;
        //   235: invokespecial   com/netflix/msl/keyx/JsonWebKeyLadderExchange$AesKwJwkCryptoContext.<init>:(Lcom/netflix/msl/crypto/ICryptoContext;)V
        //   238: astore_2       
        //   239: new             Ljava/lang/String;
        //   242: dup            
        //   243: aload_2        
        //   244: aload           5
        //   246: invokevirtual   com/netflix/msl/keyx/JsonWebKeyLadderExchange$ResponseData.getWrapKey:()[B
        //   249: invokeinterface com/netflix/msl/crypto/ICryptoContext.unwrap:([B)[B
        //   254: getstatic       com/netflix/msl/keyx/JsonWebKeyLadderExchange.UTF_8:Ljava/nio/charset/Charset;
        //   257: invokespecial   java/lang/String.<init>:([BLjava/nio/charset/Charset;)V
        //   260: astore_2       
        //   261: new             Lcom/netflix/msl/crypto/JsonWebKey;
        //   264: dup            
        //   265: new             Lcom/netflix/android/org/json/JSONObject;
        //   268: dup            
        //   269: aload_2        
        //   270: invokespecial   com/netflix/android/org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   273: invokespecial   com/netflix/msl/crypto/JsonWebKey.<init>:(Lcom/netflix/android/org/json/JSONObject;)V
        //   276: astore_3       
        //   277: new             Lcom/netflix/msl/keyx/JsonWebKeyLadderExchange$AesKwJwkCryptoContext;
        //   280: dup            
        //   281: aload_3        
        //   282: invokevirtual   com/netflix/msl/crypto/JsonWebKey.getSecretKey:()Ljavax/crypto/SecretKey;
        //   285: invokespecial   com/netflix/msl/keyx/JsonWebKeyLadderExchange$AesKwJwkCryptoContext.<init>:(Ljavax/crypto/SecretKey;)V
        //   288: astore_2       
        //   289: aload_2        
        //   290: aload           5
        //   292: invokevirtual   com/netflix/msl/keyx/JsonWebKeyLadderExchange$ResponseData.getEncryptionKey:()[B
        //   295: invokeinterface com/netflix/msl/crypto/ICryptoContext.unwrap:([B)[B
        //   300: astore_3       
        //   301: aload_2        
        //   302: aload           5
        //   304: invokevirtual   com/netflix/msl/keyx/JsonWebKeyLadderExchange$ResponseData.getHmacKey:()[B
        //   307: invokeinterface com/netflix/msl/crypto/ICryptoContext.unwrap:([B)[B
        //   312: astore          8
        //   314: new             Ljava/lang/String;
        //   317: dup            
        //   318: aload_3        
        //   319: getstatic       com/netflix/msl/keyx/JsonWebKeyLadderExchange.UTF_8:Ljava/nio/charset/Charset;
        //   322: invokespecial   java/lang/String.<init>:([BLjava/nio/charset/Charset;)V
        //   325: astore_3       
        //   326: new             Ljava/lang/String;
        //   329: dup            
        //   330: aload           8
        //   332: getstatic       com/netflix/msl/keyx/JsonWebKeyLadderExchange.UTF_8:Ljava/nio/charset/Charset;
        //   335: invokespecial   java/lang/String.<init>:([BLjava/nio/charset/Charset;)V
        //   338: astore          8
        //   340: new             Lcom/netflix/msl/crypto/JsonWebKey;
        //   343: dup            
        //   344: new             Lcom/netflix/android/org/json/JSONObject;
        //   347: dup            
        //   348: aload_3        
        //   349: invokespecial   com/netflix/android/org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   352: invokespecial   com/netflix/msl/crypto/JsonWebKey.<init>:(Lcom/netflix/android/org/json/JSONObject;)V
        //   355: astore          9
        //   357: new             Lcom/netflix/msl/crypto/JsonWebKey;
        //   360: dup            
        //   361: new             Lcom/netflix/android/org/json/JSONObject;
        //   364: dup            
        //   365: aload           8
        //   367: invokespecial   com/netflix/android/org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   370: invokespecial   com/netflix/msl/crypto/JsonWebKey.<init>:(Lcom/netflix/android/org/json/JSONObject;)V
        //   373: astore_3       
        //   374: aload           5
        //   376: invokevirtual   com/netflix/msl/keyx/JsonWebKeyLadderExchange$ResponseData.getWrapdata:()[B
        //   379: astore          4
        //   381: aload_0        
        //   382: getfield        com/netflix/msl/keyx/JsonWebKeyLadderExchange.repository:Lcom/netflix/msl/keyx/WrapCryptoContextRepository;
        //   385: aload           4
        //   387: aload_2        
        //   388: invokeinterface com/netflix/msl/keyx/WrapCryptoContextRepository.addCryptoContext:([BLcom/netflix/msl/crypto/ICryptoContext;)V
        //   393: aload           6
        //   395: ifnull          409
        //   398: aload_0        
        //   399: getfield        com/netflix/msl/keyx/JsonWebKeyLadderExchange.repository:Lcom/netflix/msl/keyx/WrapCryptoContextRepository;
        //   402: aload           6
        //   404: invokeinterface com/netflix/msl/keyx/WrapCryptoContextRepository.removeCryptoContext:([B)V
        //   409: new             Lcom/netflix/msl/crypto/SessionCryptoContext;
        //   412: dup            
        //   413: aload_1        
        //   414: aload           5
        //   416: invokevirtual   com/netflix/msl/keyx/JsonWebKeyLadderExchange$ResponseData.getMasterToken:()Lcom/netflix/msl/tokens/MasterToken;
        //   419: aload           7
        //   421: aload           9
        //   423: invokevirtual   com/netflix/msl/crypto/JsonWebKey.getSecretKey:()Ljavax/crypto/SecretKey;
        //   426: aload_3        
        //   427: invokevirtual   com/netflix/msl/crypto/JsonWebKey.getSecretKey:()Ljavax/crypto/SecretKey;
        //   430: invokespecial   com/netflix/msl/crypto/SessionCryptoContext.<init>:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;Ljava/lang/String;Ljavax/crypto/SecretKey;Ljavax/crypto/SecretKey;)V
        //   433: areturn        
        //   434: aload_0        
        //   435: getfield        com/netflix/msl/keyx/JsonWebKeyLadderExchange.repository:Lcom/netflix/msl/keyx/WrapCryptoContextRepository;
        //   438: aload           6
        //   440: invokeinterface com/netflix/msl/keyx/WrapCryptoContextRepository.getCryptoContext:([B)Lcom/netflix/msl/crypto/ICryptoContext;
        //   445: astore_3       
        //   446: aload_3        
        //   447: astore_2       
        //   448: aload_3        
        //   449: ifnonnull       239
        //   452: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   455: dup            
        //   456: getstatic       com/netflix/msl/MslError.KEYX_WRAPPING_KEY_MISSING:Lcom/netflix/msl/MslError;
        //   459: aload           6
        //   461: invokestatic    com/netflix/msl/util/Base64.encode:([B)Ljava/lang/String;
        //   464: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   467: aload           4
        //   469: invokevirtual   com/netflix/msl/MslKeyExchangeException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslKeyExchangeException;
        //   472: athrow         
        //   473: astore_1       
        //   474: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   477: dup            
        //   478: getstatic       com/netflix/msl/MslError.INVALID_JWK:Lcom/netflix/msl/MslError;
        //   481: aload_2        
        //   482: aload_1        
        //   483: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   486: aload           4
        //   488: invokevirtual   com/netflix/msl/MslKeyExchangeException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslKeyExchangeException;
        //   491: athrow         
        //   492: astore_1       
        //   493: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   496: dup            
        //   497: getstatic       com/netflix/msl/MslError.INVALID_JWK:Lcom/netflix/msl/MslError;
        //   500: aload_3        
        //   501: aload_1        
        //   502: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   505: aload           4
        //   507: invokevirtual   com/netflix/msl/MslKeyExchangeException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslKeyExchangeException;
        //   510: athrow         
        //   511: astore_1       
        //   512: new             Lcom/netflix/msl/MslKeyExchangeException;
        //   515: dup            
        //   516: getstatic       com/netflix/msl/MslError.INVALID_JWK:Lcom/netflix/msl/MslError;
        //   519: aload           8
        //   521: aload_1        
        //   522: invokespecial   com/netflix/msl/MslKeyExchangeException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   525: aload           4
        //   527: invokevirtual   com/netflix/msl/MslKeyExchangeException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslKeyExchangeException;
        //   530: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  261    277    473    492    Lcom/netflix/android/org/json/JSONException;
        //  340    357    492    511    Lcom/netflix/android/org/json/JSONException;
        //  357    374    511    531    Lcom/netflix/android/org/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 234, Size: 234
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
