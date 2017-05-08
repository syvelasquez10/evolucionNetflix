// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.uimanager.annotations.ReactPropGroup;
import java.lang.reflect.Method;
import com.facebook.react.uimanager.annotations.ReactProp;

class ViewManagersPropertyCache$DoublePropSetter extends ViewManagersPropertyCache$PropSetter
{
    private final double mDefaultValue;
    
    public ViewManagersPropertyCache$DoublePropSetter(final ReactProp reactProp, final Method method, final double mDefaultValue) {
        super(reactProp, "number", method, null);
        this.mDefaultValue = mDefaultValue;
    }
    
    public ViewManagersPropertyCache$DoublePropSetter(final ReactPropGroup reactPropGroup, final Method method, final int n, final double mDefaultValue) {
        super(reactPropGroup, "number", method, n, null);
        this.mDefaultValue = mDefaultValue;
    }
    
    @Override
    protected Object extractProperty(final ReactStylesDiffMap reactStylesDiffMap) {
        return reactStylesDiffMap.getDouble(this.mPropName, this.mDefaultValue);
    }
}
