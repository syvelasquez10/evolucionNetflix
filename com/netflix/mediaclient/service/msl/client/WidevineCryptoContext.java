// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl.client;

import com.netflix.msl.MslEncodingException;
import com.netflix.msl.crypto.MslSignatureEnvelope;
import java.util.Random;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.crypto.MslCiphertextEnvelope;
import com.netflix.msl.crypto.MslCiphertextEnvelope$Version;
import com.netflix.msl.MslConstants;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslError;
import com.netflix.mediaclient.util.CryptoUtils;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.keyx.WidevineKeyResponseData;
import com.netflix.msl.keyx.WidevineKeyRequestData;
import com.netflix.mediaclient.service.configuration.crypto.CryptoManagerRegistry;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.android.org.json.JSONObject;
import com.netflix.mediaclient.service.configuration.crypto.CryptoManager$KeyId;
import com.netflix.msl.util.MslContext;
import com.netflix.mediaclient.service.configuration.crypto.CryptoManager$CryptoSession;
import com.netflix.msl.crypto.ICryptoContext;

public class WidevineCryptoContext implements ICryptoContext
{
    private static final int AES_IV_SIZE = 16;
    private static final String KEY_ENCRYPTION_KEY_ID = "encryptionKeyId";
    private static final String KEY_ENVELOPE_ID = "envelopeId";
    private static final String KEY_HMAC_KEY_ID = "hmacKeyId";
    private static final String KEY_SET_ID = "keySetId";
    private static final String TAG = "nf_msl";
    private transient CryptoManager$CryptoSession cryptoSession;
    private MslContext ctx;
    private CryptoManager$KeyId encryptionKeyId;
    private String envelopeId;
    private CryptoManager$KeyId hmacKeyId;
    
    private WidevineCryptoContext(final MslContext ctx, final JSONObject jsonObject) {
        final String string = jsonObject.getString("encryptionKeyId");
        final String optString = jsonObject.optString("hmacKeyId");
        final String optString2 = jsonObject.optString("envelopeId");
        final String optString3 = jsonObject.optString("keySetId");
        Log.d("nf_msl", "WidevineCryptoContex:: restoring crypto session....");
        if (ctx == null) {
            throw new IllegalStateException("MSL context is null!");
        }
        if (StringUtils.isEmpty(optString2)) {
            throw new IllegalStateException("envelopeId is null!");
        }
        this.ctx = ctx;
        this.encryptionKeyId = new CryptoManager$KeyId(string);
        this.hmacKeyId = new CryptoManager$KeyId(optString);
        this.envelopeId = optString2;
        this.cryptoSession = CryptoManagerRegistry.getCryptoManager().restoreCryptoSession(new CryptoManager$KeyId(optString3));
        if (this.cryptoSession == null) {
            throw new IllegalStateException("Unable to restore crypto session!");
        }
    }
    
    public WidevineCryptoContext(final MslContext ctx, final String s, final WidevineKeyRequestData widevineKeyRequestData, final WidevineKeyResponseData widevineKeyResponseData, final MasterToken masterToken) {
        Log.d("nf_msl", "WidevineCryptoContex::");
        if (ctx == null) {
            throw new IllegalStateException("MSL context is null!");
        }
        if (widevineKeyRequestData == null) {
            throw new IllegalStateException("CDM request is null!");
        }
        if (widevineKeyResponseData == null) {
            throw new IllegalStateException("CDM response is null!");
        }
        this.ctx = ctx;
        this.encryptionKeyId = new CryptoManager$KeyId(widevineKeyResponseData.getEncryptionKeyId());
        this.hmacKeyId = new CryptoManager$KeyId(widevineKeyResponseData.getHmacKeyId());
        this.envelopeId = s + "_" + widevineKeyResponseData.getMasterToken().getSequenceNumber();
        final String keyResponse = widevineKeyResponseData.getKeyResponse();
        if (StringUtils.isEmpty(keyResponse)) {
            throw new IllegalArgumentException("KeyRespone is empty!");
        }
        this.cryptoSession = CryptoManagerRegistry.getCryptoManager().updateKeyResponse(widevineKeyRequestData, CryptoUtils.decode(keyResponse), this.encryptionKeyId, this.hmacKeyId);
    }
    
    public static WidevineCryptoContext restoreWidevineCryptoContext(final MslContext mslContext, final JSONObject jsonObject) {
        return new WidevineCryptoContext(mslContext, jsonObject);
    }
    
    public byte[] decrypt(byte[] array) {
        if (this.encryptionKeyId == null) {
            throw new MslCryptoException(MslError.DECRYPT_NOT_SUPPORTED, "no encryption/decryption key");
        }
        MslCiphertextEnvelope mslCiphertextEnvelope;
        try {
            mslCiphertextEnvelope = new MslCiphertextEnvelope(new JSONObject(new String(array, MslConstants.DEFAULT_CHARSET)), MslCiphertextEnvelope$Version.V1);
            if (!mslCiphertextEnvelope.getKeyId().equals(this.envelopeId)) {
                throw new MslCryptoException(MslError.ENVELOPE_KEY_ID_MISMATCH);
            }
        }
        catch (Throwable t) {
            throw new MslInternalException("WidevineCryptoContext::decrypt failed.", t);
        }
        array = mslCiphertextEnvelope.getCiphertext();
        if (array.length == 0) {
            return new byte[0];
        }
        array = CryptoManagerRegistry.getCryptoManager().decrypt(this.cryptoSession, this.encryptionKeyId, array, mslCiphertextEnvelope.getIv());
        return array;
    }
    
    public byte[] encrypt(byte[] encrypt) {
        if (this.encryptionKeyId == null) {
            throw new MslCryptoException(MslError.ENCRYPT_NOT_SUPPORTED, "no encryption/decryption key");
        }
        try {
            final Random random = this.ctx.getRandom();
            final byte[] array = new byte[16];
            random.nextBytes(array);
            if (encrypt.length != 0) {
                encrypt = CryptoManagerRegistry.getCryptoManager().encrypt(this.cryptoSession, this.encryptionKeyId, encrypt, array);
            }
            else {
                encrypt = new byte[0];
            }
            return new MslCiphertextEnvelope(this.envelopeId, array, encrypt).toJSONString().getBytes(MslConstants.DEFAULT_CHARSET);
        }
        catch (Throwable t) {
            throw new MslInternalException("WidevineCryptoContext::encrypt failed.", t);
        }
    }
    
    public CryptoManager$KeyId getEncryptionKeyId() {
        return this.encryptionKeyId;
    }
    
    public CryptoManager$KeyId getHmacKeyId() {
        return this.hmacKeyId;
    }
    
    public void release() {
        Log.d("nf_msl", "Widevine crypto context, release crypto session!");
        CryptoManagerRegistry.getCryptoManager().closeCryptoSession(this.cryptoSession);
    }
    
    public byte[] sign(byte[] bytes) {
        if (this.hmacKeyId == null) {
            throw new MslCryptoException(MslError.SIGN_NOT_SUPPORTED, "No signature key.");
        }
        try {
            bytes = new MslSignatureEnvelope(CryptoManagerRegistry.getCryptoManager().sign(this.cryptoSession, this.hmacKeyId, bytes)).getBytes();
            return bytes;
        }
        catch (Throwable t) {
            throw new MslInternalException("WidevineCryptoContext::sign failed.", t);
        }
    }
    
    public JSONObject toJSONObject() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("encryptionKeyId", this.encryptionKeyId.getAsBase64EncodedString());
        jsonObject.put("hmacKeyId", this.hmacKeyId.getAsBase64EncodedString());
        jsonObject.put("envelopeId", this.envelopeId);
        jsonObject.put("keySetId", this.cryptoSession.keySetId.getAsBase64EncodedString());
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "WidevineCryptoContext{encryptionKeyId='" + this.encryptionKeyId + '\'' + ", hmacKeyId='" + this.hmacKeyId + '\'' + ", ctx=" + this.ctx + ", envelopeId='" + this.envelopeId + '\'' + ", cryptoSession='" + this.cryptoSession + '\'' + '}';
    }
    
    public byte[] unwrap(final byte[] array) {
        throw new MslCryptoException(MslError.UNWRAP_NOT_SUPPORTED, "no wrap/unwrap key");
    }
    
    public boolean verify(final byte[] array, final byte[] array2) {
        if (this.hmacKeyId == null) {
            throw new MslCryptoException(MslError.VERIFY_NOT_SUPPORTED, "No signature key.");
        }
        try {
            return CryptoManagerRegistry.getCryptoManager().verify(this.cryptoSession, this.hmacKeyId, array, MslSignatureEnvelope.parse(array2).getSignature());
        }
        catch (MslEncodingException ex) {
            throw new MslCryptoException(MslError.SIGNATURE_ENVELOPE_PARSE_ERROR, (Throwable)ex);
        }
        catch (MslCryptoException ex2) {
            throw ex2;
        }
        catch (Throwable t) {
            throw new MslInternalException("WidevineCryptoContext::verify failed.", t);
        }
    }
    
    public byte[] wrap(final byte[] array) {
        throw new MslCryptoException(MslError.WRAP_NOT_SUPPORTED, "no wrap/unwrap key");
    }
}
