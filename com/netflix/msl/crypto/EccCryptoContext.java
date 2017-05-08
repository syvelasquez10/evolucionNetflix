// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import java.security.spec.AlgorithmParameterSpec;
import java.security.PublicKey;
import java.security.PrivateKey;

public class EccCryptoContext extends AsymmetricCryptoContext
{
    public EccCryptoContext(final String s, final PrivateKey privateKey, final PublicKey publicKey, final EccCryptoContext$Mode eccCryptoContext$Mode) {
        String s2;
        if (EccCryptoContext$Mode.ENCRYPT_DECRYPT.equals(eccCryptoContext$Mode)) {
            s2 = "ECIES";
        }
        else {
            s2 = "nullOp";
        }
        String s3;
        if (EccCryptoContext$Mode.SIGN_VERIFY.equals(eccCryptoContext$Mode)) {
            s3 = "SHA256withECDSA";
        }
        else {
            s3 = "nullOp";
        }
        super(s, privateKey, publicKey, s2, null, s3);
    }
}
