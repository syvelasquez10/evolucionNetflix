// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import android.util.Base64;
import java.security.KeyFactory;
import java.security.PublicKey;

@ez
public class ef
{
    public static PublicKey F(final String s) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(s, 0)));
        }
        catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
        catch (InvalidKeySpecException ex2) {
            gs.T("Invalid key specification.");
            throw new IllegalArgumentException(ex2);
        }
    }
    
    public static boolean a(final PublicKey publicKey, final String s, final String s2) {
        try {
            final Signature instance = Signature.getInstance("SHA1withRSA");
            instance.initVerify(publicKey);
            instance.update(s.getBytes());
            if (!instance.verify(Base64.decode(s2, 0))) {
                gs.T("Signature verification failed.");
                return false;
            }
            return true;
        }
        catch (NoSuchAlgorithmException ex) {
            gs.T("NoSuchAlgorithmException.");
            return false;
        }
        catch (InvalidKeyException ex2) {
            gs.T("Invalid key specification.");
            return false;
        }
        catch (SignatureException ex3) {
            gs.T("Signature exception.");
            return false;
        }
    }
    
    public static boolean b(final String s, final String s2, final String s3) {
        if (TextUtils.isEmpty((CharSequence)s2) || TextUtils.isEmpty((CharSequence)s) || TextUtils.isEmpty((CharSequence)s3)) {
            gs.T("Purchase verification failed: missing data.");
            return false;
        }
        return a(F(s), s2, s3);
    }
}
