// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import java.lang.reflect.Method;
import com.facebook.react.uimanager.annotations.ReactProp;

class ViewManagersPropertyCache$BooleanPropSetter extends ViewManagersPropertyCache$PropSetter
{
    private final boolean mDefaultValue;
    
    public ViewManagersPropertyCache$BooleanPropSetter(final ReactProp reactProp, final Method method, final boolean mDefaultValue) {
        super(reactProp, "boolean", method, null);
        this.mDefaultValue = mDefaultValue;
    }
    
    @Override
    protected Object extractProperty(final ReactStylesDiffMap reactStylesDiffMap) {
        if (reactStylesDiffMap.getBoolean(this.mPropName, this.mDefaultValue)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
