// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import java.lang.reflect.Method;
import com.facebook.react.uimanager.annotations.ReactProp;

class ViewManagersPropertyCache$ArrayPropSetter extends ViewManagersPropertyCache$PropSetter
{
    public ViewManagersPropertyCache$ArrayPropSetter(final ReactProp reactProp, final Method method) {
        super(reactProp, "Array", method, null);
    }
    
    @Override
    protected Object extractProperty(final ReactStylesDiffMap reactStylesDiffMap) {
        return reactStylesDiffMap.getArray(this.mPropName);
    }
}
