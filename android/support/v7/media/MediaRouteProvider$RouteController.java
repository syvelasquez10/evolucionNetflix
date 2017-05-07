// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.support.annotation.Nullable;
import android.content.Intent;

public abstract class MediaRouteProvider$RouteController
{
    public boolean onControlRequest(final Intent intent, @Nullable final MediaRouter$ControlRequestCallback mediaRouter$ControlRequestCallback) {
        return false;
    }
    
    public void onRelease() {
    }
    
    public void onSelect() {
    }
    
    public void onSetVolume(final int n) {
    }
    
    public void onUnselect() {
    }
    
    public void onUpdateVolume(final int n) {
    }
}
