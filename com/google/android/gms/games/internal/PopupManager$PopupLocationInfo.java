// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.Bundle;
import android.os.IBinder;

public final class PopupManager$PopupLocationInfo
{
    public IBinder XQ;
    public int XR;
    public int bottom;
    public int gravity;
    public int left;
    public int right;
    public int top;
    
    private PopupManager$PopupLocationInfo(final int gravity, final IBinder xq) {
        this.XR = -1;
        this.left = 0;
        this.top = 0;
        this.right = 0;
        this.bottom = 0;
        this.gravity = gravity;
        this.XQ = xq;
    }
    
    public Bundle kM() {
        final Bundle bundle = new Bundle();
        bundle.putInt("popupLocationInfo.gravity", this.gravity);
        bundle.putInt("popupLocationInfo.displayId", this.XR);
        bundle.putInt("popupLocationInfo.left", this.left);
        bundle.putInt("popupLocationInfo.top", this.top);
        bundle.putInt("popupLocationInfo.right", this.right);
        bundle.putInt("popupLocationInfo.bottom", this.bottom);
        return bundle;
    }
}
