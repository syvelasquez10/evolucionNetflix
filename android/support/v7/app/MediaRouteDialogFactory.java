// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.annotation.NonNull;

public class MediaRouteDialogFactory
{
    private static final MediaRouteDialogFactory sDefault;
    
    static {
        sDefault = new MediaRouteDialogFactory();
    }
    
    @NonNull
    public static MediaRouteDialogFactory getDefault() {
        return MediaRouteDialogFactory.sDefault;
    }
    
    @NonNull
    public MediaRouteChooserDialogFragment onCreateChooserDialogFragment() {
        return new MediaRouteChooserDialogFragment();
    }
    
    @NonNull
    public MediaRouteControllerDialogFragment onCreateControllerDialogFragment() {
        return new MediaRouteControllerDialogFragment();
    }
}
