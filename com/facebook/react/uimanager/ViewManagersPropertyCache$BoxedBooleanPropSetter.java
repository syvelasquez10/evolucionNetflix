// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import java.lang.reflect.Method;
import com.facebook.react.uimanager.annotations.ReactProp;

class ViewManagersPropertyCache$BoxedBooleanPropSetter extends ViewManagersPropertyCache$PropSetter
{
    public ViewManagersPropertyCache$BoxedBooleanPropSetter(final ReactProp reactProp, final Method method) {
        super(reactProp, "boolean", method, null);
    }
    
    @Override
    protected Object extractProperty(final ReactStylesDiffMap reactStylesDiffMap) {
        if (reactStylesDiffMap.isNull(this.mPropName)) {
            return null;
        }
        if (reactStylesDiffMap.getBoolean(this.mPropName, false)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
