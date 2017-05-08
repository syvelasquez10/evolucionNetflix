// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.picker;

import android.content.Context;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;

public class ReactDialogPickerManager extends ReactPickerManager
{
    protected static final String REACT_CLASS = "AndroidDialogPicker";
    
    protected ReactPicker createViewInstance(final ThemedReactContext themedReactContext) {
        return new ReactPicker((Context)themedReactContext, 0);
    }
    
    @Override
    public String getName() {
        return "AndroidDialogPicker";
    }
}
