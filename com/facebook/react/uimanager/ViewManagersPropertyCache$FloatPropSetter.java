// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.uimanager.annotations.ReactPropGroup;
import java.lang.reflect.Method;
import com.facebook.react.uimanager.annotations.ReactProp;

class ViewManagersPropertyCache$FloatPropSetter extends ViewManagersPropertyCache$PropSetter
{
    private final float mDefaultValue;
    
    public ViewManagersPropertyCache$FloatPropSetter(final ReactProp reactProp, final Method method, final float mDefaultValue) {
        super(reactProp, "number", method, null);
        this.mDefaultValue = mDefaultValue;
    }
    
    public ViewManagersPropertyCache$FloatPropSetter(final ReactPropGroup reactPropGroup, final Method method, final int n, final float mDefaultValue) {
        super(reactPropGroup, "number", method, n, null);
        this.mDefaultValue = mDefaultValue;
    }
    
    @Override
    protected Object extractProperty(final ReactStylesDiffMap reactStylesDiffMap) {
        return reactStylesDiffMap.getFloat(this.mPropName, this.mDefaultValue);
    }
}
