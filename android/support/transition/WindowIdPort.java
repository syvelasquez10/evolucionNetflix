// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.view.View;
import android.os.IBinder;
import android.annotation.TargetApi;

@TargetApi(14)
class WindowIdPort
{
    private final IBinder mToken;
    
    private WindowIdPort(final IBinder mToken) {
        this.mToken = mToken;
    }
    
    static WindowIdPort getWindowId(final View view) {
        return new WindowIdPort(view.getWindowToken());
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof WindowIdPort && ((WindowIdPort)o).mToken.equals(this.mToken);
    }
}
