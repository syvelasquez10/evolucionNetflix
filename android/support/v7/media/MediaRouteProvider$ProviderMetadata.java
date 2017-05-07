// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.content.ComponentName;

public final class MediaRouteProvider$ProviderMetadata
{
    private final ComponentName mComponentName;
    
    MediaRouteProvider$ProviderMetadata(final ComponentName mComponentName) {
        if (mComponentName == null) {
            throw new IllegalArgumentException("componentName must not be null");
        }
        this.mComponentName = mComponentName;
    }
    
    public ComponentName getComponentName() {
        return this.mComponentName;
    }
    
    public String getPackageName() {
        return this.mComponentName.getPackageName();
    }
    
    @Override
    public String toString() {
        return "ProviderMetadata{ componentName=" + this.mComponentName.flattenToShortString() + " }";
    }
}
