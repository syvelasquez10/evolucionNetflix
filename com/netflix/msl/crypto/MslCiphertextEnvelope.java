// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import com.netflix.msl.MslInternalException;
import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.util.Base64;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslError;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.MslConstants$CipherSpec;
import com.netflix.android.org.json.JSONString;

public class MslCiphertextEnvelope implements JSONString
{
    private static final String KEY_CIPHERSPEC = "cipherspec";
    private static final String KEY_CIPHERTEXT = "ciphertext";
    private static final String KEY_IV = "iv";
    private static final String KEY_KEY_ID = "keyid";
    private static final String KEY_SHA256 = "sha256";
    private static final String KEY_VERSION = "version";
    private MslConstants$CipherSpec cipherSpec;
    private final byte[] ciphertext;
    private final byte[] iv;
    private final String keyId;
    private final MslCiphertextEnvelope$Version version;
    
    public MslCiphertextEnvelope(final JSONObject jsonObject) {
        this(jsonObject, getVersion(jsonObject));
    }
    
    public MslCiphertextEnvelope(final JSONObject jsonObject, final MslCiphertextEnvelope$Version mslCiphertextEnvelope$Version) {
        final byte[] array = null;
        switch (MslCiphertextEnvelope$1.$SwitchMap$com$netflix$msl$crypto$MslCiphertextEnvelope$Version[mslCiphertextEnvelope$Version.ordinal()]) {
            default: {
                throw new MslCryptoException(MslError.UNSUPPORTED_CIPHERTEXT_ENVELOPE, "ciphertext envelope version " + mslCiphertextEnvelope$Version);
            }
            case 1: {
                try {
                    this.version = MslCiphertextEnvelope$Version.V1;
                    this.keyId = jsonObject.getString("keyid");
                    this.cipherSpec = null;
                    byte[] decode = array;
                    try {
                        if (jsonObject.has("iv")) {
                            decode = Base64.decode(jsonObject.getString("iv"));
                        }
                        this.iv = decode;
                        final MslCiphertextEnvelope mslCiphertextEnvelope = this;
                        final JSONObject jsonObject2 = jsonObject;
                        final String s = "ciphertext";
                        final String s2 = jsonObject2.getString(s);
                        final byte[] array2 = Base64.decode(s2);
                        mslCiphertextEnvelope.ciphertext = array2;
                        final JSONObject jsonObject3 = jsonObject;
                        final String s3 = "sha256";
                        jsonObject3.getString(s3);
                        return;
                    }
                    catch (IllegalArgumentException ex) {
                        throw new MslCryptoException(MslError.INVALID_IV, "ciphertext envelope " + jsonObject.toString(), ex);
                    }
                }
                catch (JSONException ex2) {
                    throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "ciphertext envelope " + jsonObject.toString(), ex2);
                }
                try {
                    final MslCiphertextEnvelope mslCiphertextEnvelope = this;
                    final JSONObject jsonObject2 = jsonObject;
                    final String s = "ciphertext";
                    final String s2 = jsonObject2.getString(s);
                    final byte[] array2 = Base64.decode(s2);
                    mslCiphertextEnvelope.ciphertext = array2;
                    final JSONObject jsonObject3 = jsonObject;
                    final String s3 = "sha256";
                    jsonObject3.getString(s3);
                }
                catch (IllegalArgumentException ex3) {
                    throw new MslCryptoException(MslError.INVALID_CIPHERTEXT, "ciphertext envelope " + jsonObject.toString(), ex3);
                }
            }
            case 2: {
                try {
                    this.version = MslCiphertextEnvelope$Version.valueOf(jsonObject.getInt("version"));
                    if (!MslCiphertextEnvelope$Version.V2.equals(this.version)) {
                        throw new MslCryptoException(MslError.UNIDENTIFIED_CIPHERTEXT_ENVELOPE, "ciphertext envelope " + jsonObject.toString());
                    }
                    goto Label_0338;
                }
                catch (IllegalArgumentException ex4) {
                    throw new MslCryptoException(MslError.UNIDENTIFIED_CIPHERSPEC, "ciphertext envelope " + jsonObject.toString(), ex4);
                }
                catch (JSONException ex6) {
                    final JSONException ex5 = ex6;
                    final MslError mslError = MslError.JSON_PARSE_ERROR;
                    final StringBuilder sb = new StringBuilder();
                    final String s4 = "ciphertext envelope ";
                    final StringBuilder sb2 = sb.append(s4);
                    final JSONObject jsonObject4 = jsonObject;
                    final String s5 = jsonObject4.toString();
                    final StringBuilder sb3 = sb2.append(s5);
                    final String s6 = sb3.toString();
                    final JSONException ex7 = ex5;
                    throw new MslEncodingException(mslError, s6, ex7);
                }
                try {
                    final byte[] decode2;
                    if (jsonObject.has("iv")) {
                        decode2 = Base64.decode(jsonObject.getString("iv"));
                    }
                    this.iv = decode2;
                    try {
                        this.ciphertext = Base64.decode(jsonObject.getString("ciphertext"));
                        return;
                    }
                    catch (IllegalArgumentException ex9) {
                        throw new MslCryptoException(MslError.INVALID_CIPHERTEXT, "ciphertext envelope " + jsonObject.toString(), ex9);
                    }
                    final JSONException ex6;
                    final JSONException ex5 = ex6;
                    final MslError mslError = MslError.JSON_PARSE_ERROR;
                    final StringBuilder sb = new StringBuilder();
                    final String s4 = "ciphertext envelope ";
                    final StringBuilder sb2 = sb.append(s4);
                    final JSONObject jsonObject4 = jsonObject;
                    final String s5 = jsonObject4.toString();
                    final StringBuilder sb3 = sb2.append(s5);
                    final String s6 = sb3.toString();
                    final JSONException ex7 = ex5;
                    throw new MslEncodingException(mslError, s6, ex7);
                }
                catch (IllegalArgumentException ex10) {
                    throw new MslCryptoException(MslError.INVALID_IV, "ciphertext envelope " + jsonObject.toString(), ex10);
                }
                break;
            }
        }
    }
    
    public MslCiphertextEnvelope(final MslConstants$CipherSpec cipherSpec, final byte[] iv, final byte[] ciphertext) {
        this.version = MslCiphertextEnvelope$Version.V2;
        this.keyId = null;
        this.cipherSpec = cipherSpec;
        this.iv = iv;
        this.ciphertext = ciphertext;
    }
    
    public MslCiphertextEnvelope(final String keyId, final byte[] iv, final byte[] ciphertext) {
        this.version = MslCiphertextEnvelope$Version.V1;
        this.keyId = keyId;
        this.cipherSpec = null;
        this.iv = iv;
        this.ciphertext = ciphertext;
    }
    
    private static MslCiphertextEnvelope$Version getVersion(final JSONObject jsonObject) {
        try {
            return MslCiphertextEnvelope$Version.valueOf(jsonObject.getInt("version"));
        }
        catch (JSONException ex2) {
            return MslCiphertextEnvelope$Version.V1;
        }
        catch (IllegalArgumentException ex) {
            throw new MslCryptoException(MslError.UNIDENTIFIED_CIPHERTEXT_ENVELOPE, "ciphertext envelope " + jsonObject.toString(), ex);
        }
    }
    
    public MslConstants$CipherSpec getCipherSpec() {
        return this.cipherSpec;
    }
    
    public byte[] getCiphertext() {
        return this.ciphertext;
    }
    
    public byte[] getIv() {
        return this.iv;
    }
    
    public String getKeyId() {
        return this.keyId;
    }
    
    @Override
    public String toJSONString() {
        JSONObject jsonObject = null;
        while (true) {
            Label_0175: {
                Label_0115: {
                    while (true) {
                        Label_0241: {
                            try {
                                jsonObject = new JSONObject();
                                switch (MslCiphertextEnvelope$1.$SwitchMap$com$netflix$msl$crypto$MslCiphertextEnvelope$Version[this.version.ordinal()]) {
                                    case 1: {
                                        break Label_0115;
                                    }
                                    case 2: {
                                        break Label_0175;
                                    }
                                    default: {
                                        break Label_0241;
                                    }
                                }
                                throw new MslInternalException("Ciphertext envelope version " + this.version + " encoding unsupported.");
                            }
                            catch (JSONException ex) {
                                throw new MslInternalException("Error encoding " + this.getClass().getName() + " JSON.", ex);
                            }
                            break Label_0115;
                        }
                        continue;
                    }
                }
                jsonObject.put("keyid", this.keyId);
                if (this.iv != null) {
                    jsonObject.put("iv", Base64.encode(this.iv));
                }
                jsonObject.put("ciphertext", Base64.encode(this.ciphertext));
                jsonObject.put("sha256", "AA==");
                break;
            }
            jsonObject.put("version", this.version.intValue());
            jsonObject.put("cipherspec", this.cipherSpec.toString());
            if (this.iv != null) {
                jsonObject.put("iv", Base64.encode(this.iv));
            }
            jsonObject.put("ciphertext", Base64.encode(this.ciphertext));
            break;
        }
        return jsonObject.toString();
    }
}
