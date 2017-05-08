// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import java.lang.reflect.Method;
import com.facebook.react.uimanager.annotations.ReactProp;

class ViewManagersPropertyCache$MapPropSetter extends ViewManagersPropertyCache$PropSetter
{
    public ViewManagersPropertyCache$MapPropSetter(final ReactProp reactProp, final Method method) {
        super(reactProp, "Map", method, null);
    }
    
    @Override
    protected Object extractProperty(final ReactStylesDiffMap reactStylesDiffMap) {
        return reactStylesDiffMap.getMap(this.mPropName);
    }
}
