// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import java.util.Iterator;
import java.util.Map;

class ViewManagerPropertyUpdater$FallbackShadowNodeSetter<T extends ReactShadowNode> implements ViewManagerPropertyUpdater$ShadowNodeSetter<T>
{
    private final Map<String, ViewManagersPropertyCache$PropSetter> mPropSetters;
    
    private ViewManagerPropertyUpdater$FallbackShadowNodeSetter(final Class<? extends ReactShadowNode> clazz) {
        this.mPropSetters = ViewManagersPropertyCache.getNativePropSettersForShadowNodeClass(clazz);
    }
    
    @Override
    public void getProperties(final Map<String, String> map) {
        for (final ViewManagersPropertyCache$PropSetter viewManagersPropertyCache$PropSetter : this.mPropSetters.values()) {
            map.put(viewManagersPropertyCache$PropSetter.getPropName(), viewManagersPropertyCache$PropSetter.getPropType());
        }
    }
    
    @Override
    public void setProperty(final ReactShadowNode reactShadowNode, final String s, final ReactStylesDiffMap reactStylesDiffMap) {
        final ViewManagersPropertyCache$PropSetter viewManagersPropertyCache$PropSetter = this.mPropSetters.get(s);
        if (viewManagersPropertyCache$PropSetter != null) {
            viewManagersPropertyCache$PropSetter.updateShadowNodeProp(reactShadowNode, reactStylesDiffMap);
        }
    }
}
