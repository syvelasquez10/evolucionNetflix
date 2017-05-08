// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.uimanager.annotations.ReactPropGroup;
import java.lang.reflect.Method;
import com.facebook.react.uimanager.annotations.ReactProp;

class ViewManagersPropertyCache$DynamicPropSetter extends ViewManagersPropertyCache$PropSetter
{
    public ViewManagersPropertyCache$DynamicPropSetter(final ReactProp reactProp, final Method method) {
        super(reactProp, "mixed", method, null);
    }
    
    public ViewManagersPropertyCache$DynamicPropSetter(final ReactPropGroup reactPropGroup, final Method method, final int n) {
        super(reactPropGroup, "mixed", method, n, null);
    }
    
    @Override
    protected Object extractProperty(final ReactStylesDiffMap reactStylesDiffMap) {
        return reactStylesDiffMap.getDynamic(this.mPropName);
    }
}
