// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.os;

import android.os.CancellationSignal;
import android.annotation.TargetApi;

@TargetApi(16)
class CancellationSignalCompatJellybean
{
    public static void cancel(final Object o) {
        ((CancellationSignal)o).cancel();
    }
    
    public static Object create() {
        return new CancellationSignal();
    }
}
