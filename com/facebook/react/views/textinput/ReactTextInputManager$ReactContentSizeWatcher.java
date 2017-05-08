// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.textinput;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;

class ReactTextInputManager$ReactContentSizeWatcher implements ContentSizeWatcher
{
    private ReactEditText mEditText;
    private EventDispatcher mEventDispatcher;
    private int mPreviousContentHeight;
    private int mPreviousContentWidth;
    final /* synthetic */ ReactTextInputManager this$0;
    
    public ReactTextInputManager$ReactContentSizeWatcher(final ReactTextInputManager this$0, final ReactEditText mEditText) {
        this.this$0 = this$0;
        this.mPreviousContentWidth = 0;
        this.mPreviousContentHeight = 0;
        this.mEditText = mEditText;
        this.mEventDispatcher = ((ReactContext)mEditText.getContext()).getNativeModule(UIManagerModule.class).getEventDispatcher();
    }
    
    @Override
    public void onLayout() {
        int width = this.mEditText.getWidth();
        int height = this.mEditText.getHeight();
        if (this.mEditText.getLayout() != null) {
            width = this.mEditText.getCompoundPaddingRight() + (this.mEditText.getCompoundPaddingLeft() + this.mEditText.getLayout().getWidth());
            height = this.mEditText.getCompoundPaddingTop() + this.mEditText.getLayout().getHeight() + this.mEditText.getCompoundPaddingBottom();
        }
        if (width != this.mPreviousContentWidth || height != this.mPreviousContentHeight) {
            this.mPreviousContentHeight = height;
            this.mPreviousContentWidth = width;
            this.mEventDispatcher.dispatchEvent(new ReactContentSizeChangedEvent(this.mEditText.getId(), PixelUtil.toDIPFromPixel(width), PixelUtil.toDIPFromPixel(height)));
        }
    }
}
