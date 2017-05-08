// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.textinput;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;

class ReactTextInputManager$ReactSelectionWatcher implements SelectionWatcher
{
    private EventDispatcher mEventDispatcher;
    private int mPreviousSelectionEnd;
    private int mPreviousSelectionStart;
    private ReactEditText mReactEditText;
    final /* synthetic */ ReactTextInputManager this$0;
    
    public ReactTextInputManager$ReactSelectionWatcher(final ReactTextInputManager this$0, final ReactEditText mReactEditText) {
        this.this$0 = this$0;
        this.mReactEditText = mReactEditText;
        this.mEventDispatcher = ((ReactContext)mReactEditText.getContext()).getNativeModule(UIManagerModule.class).getEventDispatcher();
    }
    
    @Override
    public void onSelectionChanged(final int mPreviousSelectionStart, final int mPreviousSelectionEnd) {
        if (this.mPreviousSelectionStart != mPreviousSelectionStart || this.mPreviousSelectionEnd != mPreviousSelectionEnd) {
            this.mEventDispatcher.dispatchEvent(new ReactTextInputSelectionEvent(this.mReactEditText.getId(), mPreviousSelectionStart, mPreviousSelectionEnd));
            this.mPreviousSelectionStart = mPreviousSelectionStart;
            this.mPreviousSelectionEnd = mPreviousSelectionEnd;
        }
    }
}
