// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.os.StatFs;
import android.os.Environment;
import java.math.BigInteger;

public final class bx$v implements bw
{
    private String a;
    
    public bx$v() {
        this.a = null;
        try {
            BigInteger.valueOf(-1L);
            final StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            this.a = BigInteger.valueOf(statFs.getBlockCount()).multiply(BigInteger.valueOf(statFs.getBlockSize())).toString();
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            this.a = null;
        }
    }
    
    @Override
    public final String a() {
        return "sd_space_total";
    }
}
