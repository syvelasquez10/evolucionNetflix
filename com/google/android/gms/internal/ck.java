// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.UUID;
import java.math.BigInteger;

public final class ck
{
    private static final Object hC;
    public static final String iu;
    private static BigInteger iv;
    
    static {
        final UUID randomUUID = UUID.randomUUID();
        final byte[] byteArray = BigInteger.valueOf(randomUUID.getLeastSignificantBits()).toByteArray();
        final byte[] byteArray2 = BigInteger.valueOf(randomUUID.getMostSignificantBits()).toByteArray();
        String iu2 = new BigInteger(1, byteArray).toString();
        int n = 0;
    Label_0099_Outer:
        while (true) {
            Label_0106: {
                if (n >= 2) {
                    break Label_0106;
                }
                while (true) {
                    try {
                        final MessageDigest instance = MessageDigest.getInstance("MD5");
                        instance.update(byteArray);
                        instance.update(byteArray2);
                        final byte[] array = new byte[8];
                        System.arraycopy(instance.digest(), 0, array, 0, 8);
                        iu2 = new BigInteger(1, array).toString();
                        ++n;
                        continue Label_0099_Outer;
                        iu = iu2;
                        hC = new Object();
                        ck.iv = BigInteger.ONE;
                    }
                    catch (NoSuchAlgorithmException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    public static String ar() {
        synchronized (ck.hC) {
            final String string = ck.iv.toString();
            ck.iv.add(BigInteger.ONE);
            return string;
        }
    }
}
