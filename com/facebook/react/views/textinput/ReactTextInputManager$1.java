// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.textinput;

import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.UIManagerModule;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import android.view.View$OnFocusChangeListener;

class ReactTextInputManager$1 implements View$OnFocusChangeListener
{
    final /* synthetic */ ReactTextInputManager this$0;
    final /* synthetic */ ReactEditText val$editText;
    final /* synthetic */ ThemedReactContext val$reactContext;
    
    ReactTextInputManager$1(final ReactTextInputManager this$0, final ThemedReactContext val$reactContext, final ReactEditText val$editText) {
        this.this$0 = this$0;
        this.val$reactContext = val$reactContext;
        this.val$editText = val$editText;
    }
    
    public void onFocusChange(final View view, final boolean b) {
        final EventDispatcher eventDispatcher = this.val$reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher();
        if (b) {
            eventDispatcher.dispatchEvent(new ReactTextInputFocusEvent(this.val$editText.getId()));
            return;
        }
        eventDispatcher.dispatchEvent(new ReactTextInputBlurEvent(this.val$editText.getId()));
        eventDispatcher.dispatchEvent(new ReactTextInputEndEditingEvent(this.val$editText.getId(), this.val$editText.getText().toString()));
    }
}
