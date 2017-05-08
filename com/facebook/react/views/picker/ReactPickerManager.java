// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.picker;

import android.widget.SpinnerAdapter;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.UIManagerModule;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.SimpleViewManager;

public abstract class ReactPickerManager extends SimpleViewManager<ReactPicker>
{
    protected void addEventEmitters(final ThemedReactContext themedReactContext, final ReactPicker reactPicker) {
        reactPicker.setOnSelectListener(new ReactPickerManager$PickerEventEmitter(reactPicker, themedReactContext.getNativeModule(UIManagerModule.class).getEventDispatcher()));
    }
    
    protected void onAfterUpdateTransaction(final ReactPicker reactPicker) {
        super.onAfterUpdateTransaction((T)reactPicker);
        reactPicker.updateStagedSelection();
    }
    
    @ReactProp(customType = "Color", name = "color")
    public void setColor(final ReactPicker reactPicker, final Integer n) {
        reactPicker.setPrimaryColor(n);
        final ReactPickerManager$ReactPickerAdapter reactPickerManager$ReactPickerAdapter = (ReactPickerManager$ReactPickerAdapter)reactPicker.getAdapter();
        if (reactPickerManager$ReactPickerAdapter != null) {
            reactPickerManager$ReactPickerAdapter.setPrimaryTextColor(n);
        }
    }
    
    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(final ReactPicker reactPicker, final boolean enabled) {
        reactPicker.setEnabled(enabled);
    }
    
    @ReactProp(name = "items")
    public void setItems(final ReactPicker reactPicker, final ReadableArray readableArray) {
        if (readableArray != null) {
            final ReadableMap[] array = new ReadableMap[readableArray.size()];
            for (int i = 0; i < readableArray.size(); ++i) {
                array[i] = readableArray.getMap(i);
            }
            final ReactPickerManager$ReactPickerAdapter adapter = new ReactPickerManager$ReactPickerAdapter(reactPicker.getContext(), array);
            adapter.setPrimaryTextColor(reactPicker.getPrimaryColor());
            reactPicker.setAdapter((SpinnerAdapter)adapter);
            return;
        }
        reactPicker.setAdapter((SpinnerAdapter)null);
    }
    
    @ReactProp(name = "prompt")
    public void setPrompt(final ReactPicker reactPicker, final String prompt) {
        reactPicker.setPrompt((CharSequence)prompt);
    }
    
    @ReactProp(name = "selected")
    public void setSelected(final ReactPicker reactPicker, final int stagedSelection) {
        reactPicker.setStagedSelection(stagedSelection);
    }
}
