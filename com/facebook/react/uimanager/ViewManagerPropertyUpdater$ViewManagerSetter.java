// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.view.View;

public interface ViewManagerPropertyUpdater$ViewManagerSetter<T extends ViewManager, V extends View> extends ViewManagerPropertyUpdater$Settable
{
    void setProperty(final T p0, final V p1, final String p2, final ReactStylesDiffMap p3);
}
