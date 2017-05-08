// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.picker;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.views.picker.events.PickerItemSelectEvent;
import com.facebook.react.uimanager.events.EventDispatcher;

class ReactPickerManager$PickerEventEmitter implements ReactPicker$OnSelectListener
{
    private final EventDispatcher mEventDispatcher;
    private final ReactPicker mReactPicker;
    
    public ReactPickerManager$PickerEventEmitter(final ReactPicker mReactPicker, final EventDispatcher mEventDispatcher) {
        this.mReactPicker = mReactPicker;
        this.mEventDispatcher = mEventDispatcher;
    }
    
    @Override
    public void onItemSelected(final int n) {
        this.mEventDispatcher.dispatchEvent(new PickerItemSelectEvent(this.mReactPicker.getId(), n));
    }
}
