// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.uimanager.annotations.ReactPropGroup;
import java.lang.reflect.Method;
import com.facebook.react.uimanager.annotations.ReactProp;

class ViewManagersPropertyCache$BoxedIntPropSetter extends ViewManagersPropertyCache$PropSetter
{
    public ViewManagersPropertyCache$BoxedIntPropSetter(final ReactProp reactProp, final Method method) {
        super(reactProp, "number", method, null);
    }
    
    public ViewManagersPropertyCache$BoxedIntPropSetter(final ReactPropGroup reactPropGroup, final Method method, final int n) {
        super(reactPropGroup, "number", method, n, null);
    }
    
    @Override
    protected Object extractProperty(final ReactStylesDiffMap reactStylesDiffMap) {
        if (!reactStylesDiffMap.isNull(this.mPropName)) {
            return reactStylesDiffMap.getInt(this.mPropName, 0);
        }
        return null;
    }
}
