// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.hardware.fingerprint;

import android.hardware.fingerprint.FingerprintManager$AuthenticationCallback;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.os.Handler;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager$CryptoObject;

public final class FingerprintManagerCompatApi23
{
    public static void authenticate(final Context context, final FingerprintManagerCompatApi23$CryptoObject fingerprintManagerCompatApi23$CryptoObject, final int n, final Object o, final FingerprintManagerCompatApi23$AuthenticationCallback fingerprintManagerCompatApi23$AuthenticationCallback, final Handler handler) {
        getFingerprintManager(context).authenticate(wrapCryptoObject(fingerprintManagerCompatApi23$CryptoObject), (CancellationSignal)o, n, wrapCallback(fingerprintManagerCompatApi23$AuthenticationCallback), handler);
    }
    
    private static FingerprintManager getFingerprintManager(final Context context) {
        return (FingerprintManager)context.getSystemService((Class)FingerprintManager.class);
    }
    
    public static boolean hasEnrolledFingerprints(final Context context) {
        return getFingerprintManager(context).hasEnrolledFingerprints();
    }
    
    public static boolean isHardwareDetected(final Context context) {
        return getFingerprintManager(context).isHardwareDetected();
    }
    
    private static FingerprintManagerCompatApi23$CryptoObject unwrapCryptoObject(final FingerprintManager$CryptoObject fingerprintManager$CryptoObject) {
        if (fingerprintManager$CryptoObject != null) {
            if (fingerprintManager$CryptoObject.getCipher() != null) {
                return new FingerprintManagerCompatApi23$CryptoObject(fingerprintManager$CryptoObject.getCipher());
            }
            if (fingerprintManager$CryptoObject.getSignature() != null) {
                return new FingerprintManagerCompatApi23$CryptoObject(fingerprintManager$CryptoObject.getSignature());
            }
            if (fingerprintManager$CryptoObject.getMac() != null) {
                return new FingerprintManagerCompatApi23$CryptoObject(fingerprintManager$CryptoObject.getMac());
            }
        }
        return null;
    }
    
    private static FingerprintManager$AuthenticationCallback wrapCallback(final FingerprintManagerCompatApi23$AuthenticationCallback fingerprintManagerCompatApi23$AuthenticationCallback) {
        return new FingerprintManagerCompatApi23$1(fingerprintManagerCompatApi23$AuthenticationCallback);
    }
    
    private static FingerprintManager$CryptoObject wrapCryptoObject(final FingerprintManagerCompatApi23$CryptoObject fingerprintManagerCompatApi23$CryptoObject) {
        if (fingerprintManagerCompatApi23$CryptoObject != null) {
            if (fingerprintManagerCompatApi23$CryptoObject.getCipher() != null) {
                return new FingerprintManager$CryptoObject(fingerprintManagerCompatApi23$CryptoObject.getCipher());
            }
            if (fingerprintManagerCompatApi23$CryptoObject.getSignature() != null) {
                return new FingerprintManager$CryptoObject(fingerprintManagerCompatApi23$CryptoObject.getSignature());
            }
            if (fingerprintManagerCompatApi23$CryptoObject.getMac() != null) {
                return new FingerprintManager$CryptoObject(fingerprintManagerCompatApi23$CryptoObject.getMac());
            }
        }
        return null;
    }
}
