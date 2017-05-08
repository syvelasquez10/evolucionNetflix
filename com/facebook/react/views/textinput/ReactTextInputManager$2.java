// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.textinput;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.UIManagerModule;
import android.view.KeyEvent;
import android.widget.TextView;
import com.facebook.react.uimanager.ThemedReactContext;
import android.widget.TextView$OnEditorActionListener;

class ReactTextInputManager$2 implements TextView$OnEditorActionListener
{
    final /* synthetic */ ReactTextInputManager this$0;
    final /* synthetic */ ReactEditText val$editText;
    final /* synthetic */ ThemedReactContext val$reactContext;
    
    ReactTextInputManager$2(final ReactTextInputManager this$0, final ThemedReactContext val$reactContext, final ReactEditText val$editText) {
        this.this$0 = this$0;
        this.val$reactContext = val$reactContext;
        this.val$editText = val$editText;
    }
    
    public boolean onEditorAction(final TextView textView, final int n, final KeyEvent keyEvent) {
        if ((n & 0xFF) > 0 || n == 0) {
            this.val$reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher().dispatchEvent(new ReactTextInputSubmitEditingEvent(this.val$editText.getId(), this.val$editText.getText().toString()));
        }
        if (n == 5 || n == 7) {
            if (this.val$editText.getBlurOnSubmit()) {
                this.val$editText.clearFocus();
            }
            return true;
        }
        return !this.val$editText.getBlurOnSubmit();
    }
}
