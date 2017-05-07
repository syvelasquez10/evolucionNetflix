// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import android.content.pm.Signature;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Build;
import android.content.Context;
import java.util.HashSet;

abstract class NativeProtocol$NativeAppInfo
{
    private static final String FBI_HASH = "a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc";
    private static final String FBL_HASH = "5e8f16062ea3cd2c4a0d547876baa6f38cabf625";
    private static final String FBR_HASH = "8a3c4b262d721acd49a4bf97d5213199c86fa2b9";
    private static final HashSet<String> validAppSignatureHashes;
    
    static {
        validAppSignatureHashes = buildAppSignatureHashes();
    }
    
    private static HashSet<String> buildAppSignatureHashes() {
        final HashSet<String> set = new HashSet<String>();
        set.add("8a3c4b262d721acd49a4bf97d5213199c86fa2b9");
        set.add("a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc");
        set.add("5e8f16062ea3cd2c4a0d547876baa6f38cabf625");
        return set;
    }
    
    protected abstract String getPackage();
    
    public boolean validateSignature(final Context context, String sha1hash) {
        final String brand = Build.BRAND;
        final int flags = context.getApplicationInfo().flags;
        if (!brand.startsWith("generic") || (flags & 0x2) == 0x0) {
            try {
                final Signature[] signatures = context.getPackageManager().getPackageInfo(sha1hash, 64).signatures;
                for (int length = signatures.length, i = 0; i < length; ++i) {
                    sha1hash = Utility.sha1hash(signatures[i].toByteArray());
                    if (NativeProtocol$NativeAppInfo.validAppSignatureHashes.contains(sha1hash)) {
                        return true;
                    }
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                return false;
            }
            return false;
        }
        return true;
    }
}
