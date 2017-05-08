// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.textinput;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.infer.annotation.Assertions;
import android.text.Editable;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.events.EventDispatcher;
import android.text.TextWatcher;

class ReactTextInputManager$ReactTextInputTextWatcher implements TextWatcher
{
    private ReactEditText mEditText;
    private EventDispatcher mEventDispatcher;
    private String mPreviousText;
    final /* synthetic */ ReactTextInputManager this$0;
    
    public ReactTextInputManager$ReactTextInputTextWatcher(final ReactTextInputManager this$0, final ReactContext reactContext, final ReactEditText mEditText) {
        this.this$0 = this$0;
        this.mEventDispatcher = reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher();
        this.mEditText = mEditText;
        this.mPreviousText = null;
    }
    
    public void afterTextChanged(final Editable editable) {
    }
    
    public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
        this.mPreviousText = charSequence.toString();
    }
    
    public void onTextChanged(final CharSequence charSequence, final int n, final int n2, int height) {
        if (height != 0 || n2 != 0) {
            Assertions.assertNotNull(this.mPreviousText);
            final String substring = charSequence.toString().substring(n, n + height);
            final String substring2 = this.mPreviousText.substring(n, n + n2);
            if (height != n2 || !substring.equals(substring2)) {
                int width = this.mEditText.getWidth();
                height = this.mEditText.getHeight();
                if (this.mEditText.getLayout() != null) {
                    final int compoundPaddingLeft = this.mEditText.getCompoundPaddingLeft();
                    final int width2 = this.mEditText.getLayout().getWidth();
                    final int compoundPaddingRight = this.mEditText.getCompoundPaddingRight();
                    height = this.mEditText.getCompoundPaddingTop() + this.mEditText.getLayout().getHeight() + this.mEditText.getCompoundPaddingTop();
                    width = compoundPaddingRight + (compoundPaddingLeft + width2);
                }
                this.mEventDispatcher.dispatchEvent(new ReactTextChangedEvent(this.mEditText.getId(), charSequence.toString(), PixelUtil.toDIPFromPixel(width), PixelUtil.toDIPFromPixel(height), this.mEditText.incrementAndGetEventCounter()));
                this.mEventDispatcher.dispatchEvent(new ReactTextInputEvent(this.mEditText.getId(), substring, substring2, n, n + n2));
            }
        }
    }
}
