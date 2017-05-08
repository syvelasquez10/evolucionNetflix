// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import java.util.Iterator;
import java.util.Map;
import android.view.View;

class ViewManagerPropertyUpdater$FallbackViewManagerSetter<T extends ViewManager, V extends View> implements ViewManagerPropertyUpdater$ViewManagerSetter<T, V>
{
    private final Map<String, ViewManagersPropertyCache$PropSetter> mPropSetters;
    
    private ViewManagerPropertyUpdater$FallbackViewManagerSetter(final Class<? extends ViewManager> clazz) {
        this.mPropSetters = ViewManagersPropertyCache.getNativePropSettersForViewManagerClass(clazz);
    }
    
    @Override
    public void getProperties(final Map<String, String> map) {
        for (final ViewManagersPropertyCache$PropSetter viewManagersPropertyCache$PropSetter : this.mPropSetters.values()) {
            map.put(viewManagersPropertyCache$PropSetter.getPropName(), viewManagersPropertyCache$PropSetter.getPropType());
        }
    }
    
    @Override
    public void setProperty(final T t, final V v, final String s, final ReactStylesDiffMap reactStylesDiffMap) {
        final ViewManagersPropertyCache$PropSetter viewManagersPropertyCache$PropSetter = this.mPropSetters.get(s);
        if (viewManagersPropertyCache$PropSetter != null) {
            viewManagersPropertyCache$PropSetter.updateViewProp(t, v, reactStylesDiffMap);
        }
    }
}
