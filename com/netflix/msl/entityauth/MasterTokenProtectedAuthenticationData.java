// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.util.Base64;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.MslMasterTokenException;
import com.netflix.msl.MslEntityAuthException;
import com.netflix.msl.MslError;
import com.netflix.msl.MslConstants;
import com.netflix.msl.crypto.SessionCryptoContext;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.MslContext;
import com.netflix.msl.tokens.MasterToken;

public class MasterTokenProtectedAuthenticationData extends EntityAuthenticationData
{
    protected static final String KEY_AUTHENTICATION_DATA = "authdata";
    protected static final String KEY_MASTER_TOKEN = "mastertoken";
    protected static final String KEY_SIGNATURE = "signature";
    private final EntityAuthenticationData authdata;
    private final byte[] ciphertext;
    private final MasterToken masterToken;
    private final byte[] signature;
    
    public MasterTokenProtectedAuthenticationData(final MslContext p0, final JSONObject p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/netflix/msl/entityauth/EntityAuthenticationScheme.MT_PROTECTED:Lcom/netflix/msl/entityauth/EntityAuthenticationScheme;
        //     4: invokespecial   com/netflix/msl/entityauth/EntityAuthenticationData.<init>:(Lcom/netflix/msl/entityauth/EntityAuthenticationScheme;)V
        //     7: aload_0        
        //     8: new             Lcom/netflix/msl/tokens/MasterToken;
        //    11: dup            
        //    12: aload_1        
        //    13: aload_2        
        //    14: ldc             "mastertoken"
        //    16: invokevirtual   com/netflix/android/org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONObject;
        //    19: invokespecial   com/netflix/msl/tokens/MasterToken.<init>:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/android/org/json/JSONObject;)V
        //    22: putfield        com/netflix/msl/entityauth/MasterTokenProtectedAuthenticationData.masterToken:Lcom/netflix/msl/tokens/MasterToken;
        //    25: aload_0        
        //    26: aload_2        
        //    27: ldc             "authdata"
        //    29: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    32: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //    35: putfield        com/netflix/msl/entityauth/MasterTokenProtectedAuthenticationData.ciphertext:[B
        //    38: aload_0        
        //    39: aload_2        
        //    40: ldc             "signature"
        //    42: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    45: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //    48: putfield        com/netflix/msl/entityauth/MasterTokenProtectedAuthenticationData.signature:[B
        //    51: aload_1        
        //    52: invokevirtual   com/netflix/msl/util/MslContext.getMslStore:()Lcom/netflix/msl/util/MslStore;
        //    55: aload_0        
        //    56: getfield        com/netflix/msl/entityauth/MasterTokenProtectedAuthenticationData.masterToken:Lcom/netflix/msl/tokens/MasterToken;
        //    59: invokeinterface com/netflix/msl/util/MslStore.getCryptoContext:(Lcom/netflix/msl/tokens/MasterToken;)Lcom/netflix/msl/crypto/ICryptoContext;
        //    64: astore_3       
        //    65: aload_3        
        //    66: ifnull          294
        //    69: aload_3        
        //    70: aload_0        
        //    71: getfield        com/netflix/msl/entityauth/MasterTokenProtectedAuthenticationData.ciphertext:[B
        //    74: aload_0        
        //    75: getfield        com/netflix/msl/entityauth/MasterTokenProtectedAuthenticationData.signature:[B
        //    78: invokeinterface com/netflix/msl/crypto/ICryptoContext.verify:([B[B)Z
        //    83: ifne            323
        //    86: new             Lcom/netflix/msl/MslEntityAuthException;
        //    89: dup            
        //    90: getstatic       com/netflix/msl/MslError.ENTITYAUTH_VERIFICATION_FAILED:Lcom/netflix/msl/MslError;
        //    93: new             Ljava/lang/StringBuilder;
        //    96: dup            
        //    97: invokespecial   java/lang/StringBuilder.<init>:()V
        //   100: ldc             "master token protected authdata "
        //   102: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   105: aload_2        
        //   106: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   109: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   112: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   115: invokespecial   com/netflix/msl/MslEntityAuthException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   118: athrow         
        //   119: astore_1       
        //   120: new             Lcom/netflix/msl/MslEncodingException;
        //   123: dup            
        //   124: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   127: new             Ljava/lang/StringBuilder;
        //   130: dup            
        //   131: invokespecial   java/lang/StringBuilder.<init>:()V
        //   134: ldc             "master token protected authdata "
        //   136: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   139: aload_2        
        //   140: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   143: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   146: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   149: aload_1        
        //   150: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   153: athrow         
        //   154: astore_1       
        //   155: new             Lcom/netflix/msl/MslEntityAuthException;
        //   158: dup            
        //   159: getstatic       com/netflix/msl/MslError.ENTITYAUTH_MASTERTOKEN_INVALID:Lcom/netflix/msl/MslError;
        //   162: new             Ljava/lang/StringBuilder;
        //   165: dup            
        //   166: invokespecial   java/lang/StringBuilder.<init>:()V
        //   169: ldc             "master token protected authdata "
        //   171: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   174: aload_2        
        //   175: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   178: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   181: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   184: aload_1        
        //   185: invokespecial   com/netflix/msl/MslEntityAuthException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   188: athrow         
        //   189: astore_1       
        //   190: new             Lcom/netflix/msl/MslEncodingException;
        //   193: dup            
        //   194: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   197: new             Ljava/lang/StringBuilder;
        //   200: dup            
        //   201: invokespecial   java/lang/StringBuilder.<init>:()V
        //   204: ldc             "master token protected authdata "
        //   206: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   209: aload_2        
        //   210: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   213: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   216: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   219: aload_1        
        //   220: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   223: athrow         
        //   224: astore_1       
        //   225: new             Lcom/netflix/msl/MslEntityAuthException;
        //   228: dup            
        //   229: getstatic       com/netflix/msl/MslError.ENTITYAUTH_CIPHERTEXT_INVALID:Lcom/netflix/msl/MslError;
        //   232: new             Ljava/lang/StringBuilder;
        //   235: dup            
        //   236: invokespecial   java/lang/StringBuilder.<init>:()V
        //   239: ldc             "master token protected authdata "
        //   241: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   244: aload_2        
        //   245: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   248: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   251: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   254: aload_1        
        //   255: invokespecial   com/netflix/msl/MslEntityAuthException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   258: athrow         
        //   259: astore_1       
        //   260: new             Lcom/netflix/msl/MslEntityAuthException;
        //   263: dup            
        //   264: getstatic       com/netflix/msl/MslError.ENTITYAUTH_SIGNATURE_INVALID:Lcom/netflix/msl/MslError;
        //   267: new             Ljava/lang/StringBuilder;
        //   270: dup            
        //   271: invokespecial   java/lang/StringBuilder.<init>:()V
        //   274: ldc             "master token protected authdata "
        //   276: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   279: aload_2        
        //   280: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   283: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   286: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   289: aload_1        
        //   290: invokespecial   com/netflix/msl/MslEntityAuthException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   293: athrow         
        //   294: new             Lcom/netflix/msl/crypto/SessionCryptoContext;
        //   297: dup            
        //   298: aload_1        
        //   299: aload_0        
        //   300: getfield        com/netflix/msl/entityauth/MasterTokenProtectedAuthenticationData.masterToken:Lcom/netflix/msl/tokens/MasterToken;
        //   303: invokespecial   com/netflix/msl/crypto/SessionCryptoContext.<init>:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/tokens/MasterToken;)V
        //   306: astore_3       
        //   307: goto            69
        //   310: astore_1       
        //   311: new             Lcom/netflix/msl/MslEntityAuthException;
        //   314: dup            
        //   315: getstatic       com/netflix/msl/MslError.ENTITYAUTH_MASTERTOKEN_NOT_DECRYPTED:Lcom/netflix/msl/MslError;
        //   318: aload_1        
        //   319: invokespecial   com/netflix/msl/MslEntityAuthException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/Throwable;)V
        //   322: athrow         
        //   323: aload_0        
        //   324: aload_1        
        //   325: new             Lcom/netflix/android/org/json/JSONObject;
        //   328: dup            
        //   329: new             Ljava/lang/String;
        //   332: dup            
        //   333: aload_3        
        //   334: aload_0        
        //   335: getfield        com/netflix/msl/entityauth/MasterTokenProtectedAuthenticationData.ciphertext:[B
        //   338: invokeinterface com/netflix/msl/crypto/ICryptoContext.decrypt:([B)[B
        //   343: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //   346: invokespecial   java/lang/String.<init>:([BLjava/nio/charset/Charset;)V
        //   349: invokespecial   com/netflix/android/org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   352: invokestatic    com/netflix/msl/entityauth/EntityAuthenticationData.create:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/android/org/json/JSONObject;)Lcom/netflix/msl/entityauth/EntityAuthenticationData;
        //   355: putfield        com/netflix/msl/entityauth/MasterTokenProtectedAuthenticationData.authdata:Lcom/netflix/msl/entityauth/EntityAuthenticationData;
        //   358: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  7      25     154    189    Lcom/netflix/msl/MslException;
        //  7      25     189    224    Lcom/netflix/android/org/json/JSONException;
        //  25     38     224    259    Ljava/lang/IllegalArgumentException;
        //  25     38     189    224    Lcom/netflix/android/org/json/JSONException;
        //  38     51     259    294    Ljava/lang/IllegalArgumentException;
        //  38     51     189    224    Lcom/netflix/android/org/json/JSONException;
        //  51     65     310    323    Lcom/netflix/msl/MslMasterTokenException;
        //  69     119    119    154    Lcom/netflix/android/org/json/JSONException;
        //  155    189    189    224    Lcom/netflix/android/org/json/JSONException;
        //  225    259    189    224    Lcom/netflix/android/org/json/JSONException;
        //  260    294    189    224    Lcom/netflix/android/org/json/JSONException;
        //  294    307    310    323    Lcom/netflix/msl/MslMasterTokenException;
        //  323    358    119    154    Lcom/netflix/android/org/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 164, Size: 164
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
    
    public MasterTokenProtectedAuthenticationData(final MslContext mslContext, final MasterToken masterToken, final EntityAuthenticationData authdata) {
        super(EntityAuthenticationScheme.MT_PROTECTED);
        this.masterToken = masterToken;
        this.authdata = authdata;
        try {
            ICryptoContext cryptoContext = mslContext.getMslStore().getCryptoContext(masterToken);
            if (cryptoContext == null) {
                cryptoContext = new SessionCryptoContext(mslContext, masterToken);
            }
            this.ciphertext = cryptoContext.encrypt(authdata.toJSONString().getBytes(MslConstants.DEFAULT_CHARSET));
            this.signature = cryptoContext.sign(this.ciphertext);
        }
        catch (MslMasterTokenException ex) {
            throw new MslEntityAuthException(MslError.ENTITYAUTH_MASTERTOKEN_NOT_DECRYPTED, ex);
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MasterTokenProtectedAuthenticationData)) {
            return false;
        }
        final MasterTokenProtectedAuthenticationData masterTokenProtectedAuthenticationData = (MasterTokenProtectedAuthenticationData)o;
        return super.equals(o) && this.masterToken.equals(masterTokenProtectedAuthenticationData.masterToken) && this.authdata.equals(masterTokenProtectedAuthenticationData.authdata);
    }
    
    @Override
    public JSONObject getAuthData() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("mastertoken", this.masterToken);
            jsonObject.put("authdata", Base64.encode(this.ciphertext));
            jsonObject.put("signature", Base64.encode(this.signature));
            return new JSONObject(jsonObject.toString());
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_ENCODE_ERROR, "master token protected authdata", ex);
        }
    }
    
    public EntityAuthenticationData getEncapsulatedAuthdata() {
        return this.authdata;
    }
    
    @Override
    public String getIdentity() {
        return this.authdata.getIdentity();
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() ^ this.masterToken.hashCode() ^ this.authdata.hashCode();
    }
}
