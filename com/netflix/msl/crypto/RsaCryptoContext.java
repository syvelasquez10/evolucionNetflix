// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import com.netflix.msl.MslInternalException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import java.security.PublicKey;
import java.security.PrivateKey;
import com.netflix.msl.util.MslContext;

public class RsaCryptoContext extends AsymmetricCryptoContext
{
    public RsaCryptoContext(final MslContext mslContext, final String s, final PrivateKey privateKey, final PublicKey publicKey, final RsaCryptoContext$Mode rsaCryptoContext$Mode) {
        String s2;
        if (RsaCryptoContext$Mode.ENCRYPT_DECRYPT_PKCS1.equals(rsaCryptoContext$Mode)) {
            s2 = "RSA/ECB/PKCS1Padding";
        }
        else if (RsaCryptoContext$Mode.ENCRYPT_DECRYPT_OAEP.equals(rsaCryptoContext$Mode)) {
            s2 = "RSA/ECB/OAEPPadding";
        }
        else {
            s2 = "nullOp";
        }
        OAEPParameterSpec default1;
        if (RsaCryptoContext$Mode.ENCRYPT_DECRYPT_OAEP.equals(rsaCryptoContext$Mode)) {
            default1 = OAEPParameterSpec.DEFAULT;
        }
        else {
            default1 = null;
        }
        String s3;
        if (RsaCryptoContext$Mode.SIGN_VERIFY.equals(rsaCryptoContext$Mode)) {
            s3 = "SHA256withRSA";
        }
        else {
            s3 = "nullOp";
        }
        super(s, privateKey, publicKey, s2, default1, s3);
        if (rsaCryptoContext$Mode == RsaCryptoContext$Mode.WRAP_UNWRAP) {
            throw new MslInternalException("Wrap/unwrap unsupported.");
        }
    }
}
