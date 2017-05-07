// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.os.StatFs;
import android.os.Environment;
import java.math.BigInteger;

public final class ah
{
    public static BigInteger a() {
        final BigInteger value = BigInteger.valueOf(-1L);
        try {
            final StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return BigInteger.valueOf(statFs.getAvailableBlocks()).multiply(BigInteger.valueOf(statFs.getBlockSize()));
        }
        catch (Exception ex) {
            return value;
        }
    }
    
    public static BigInteger b() {
        final BigInteger value = BigInteger.valueOf(-1L);
        try {
            final StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return BigInteger.valueOf(statFs.getBlockCount()).multiply(BigInteger.valueOf(statFs.getBlockSize()));
        }
        catch (Exception ex) {
            return value;
        }
    }
    
    public static BigInteger c() {
        final BigInteger value = BigInteger.valueOf(-1L);
        try {
            final StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return BigInteger.valueOf(statFs.getAvailableBlocks()).multiply(BigInteger.valueOf(statFs.getBlockSize()));
        }
        catch (Exception ex) {
            return value;
        }
    }
    
    public static BigInteger d() {
        final BigInteger value = BigInteger.valueOf(-1L);
        try {
            final StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return BigInteger.valueOf(statFs.getBlockCount()).multiply(BigInteger.valueOf(statFs.getBlockSize()));
        }
        catch (Exception ex) {
            return value;
        }
    }
}
