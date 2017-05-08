// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.uimanager.annotations.ReactPropGroup;
import java.lang.reflect.Method;
import com.facebook.react.uimanager.annotations.ReactProp;

class ViewManagersPropertyCache$IntPropSetter extends ViewManagersPropertyCache$PropSetter
{
    private final int mDefaultValue;
    
    public ViewManagersPropertyCache$IntPropSetter(final ReactProp reactProp, final Method method, final int mDefaultValue) {
        super(reactProp, "number", method, null);
        this.mDefaultValue = mDefaultValue;
    }
    
    public ViewManagersPropertyCache$IntPropSetter(final ReactPropGroup reactPropGroup, final Method method, final int n, final int mDefaultValue) {
        super(reactPropGroup, "number", method, n, null);
        this.mDefaultValue = mDefaultValue;
    }
    
    @Override
    protected Object extractProperty(final ReactStylesDiffMap reactStylesDiffMap) {
        return reactStylesDiffMap.getInt(this.mPropName, this.mDefaultValue);
    }
}
