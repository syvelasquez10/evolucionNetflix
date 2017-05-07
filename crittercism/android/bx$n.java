// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.os.Debug;
import android.os.Debug$MemoryInfo;

public final class bx$n implements bw
{
    private Integer a;
    
    public bx$n() {
        this.a = null;
        final Debug$MemoryInfo debug$MemoryInfo = new Debug$MemoryInfo();
        Debug.getMemoryInfo(debug$MemoryInfo);
        this.a = (debug$MemoryInfo.otherPss + (debug$MemoryInfo.dalvikPss + debug$MemoryInfo.nativePss)) * 1024;
    }
    
    @Override
    public final String a() {
        return "memory_usage";
    }
}
