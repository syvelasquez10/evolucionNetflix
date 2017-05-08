// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import java.lang.reflect.Method;
import com.facebook.react.uimanager.annotations.ReactProp;

class ViewManagersPropertyCache$StringPropSetter extends ViewManagersPropertyCache$PropSetter
{
    public ViewManagersPropertyCache$StringPropSetter(final ReactProp reactProp, final Method method) {
        super(reactProp, "String", method, null);
    }
    
    @Override
    protected Object extractProperty(final ReactStylesDiffMap reactStylesDiffMap) {
        return reactStylesDiffMap.getString(this.mPropName);
    }
}
