// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.textinput;

import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.KeyEvent;
import android.graphics.Rect;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.text.TextInlineImageSpan;
import android.text.Spanned;
import android.view.View;
import com.facebook.react.views.text.ReactTagSpan;
import com.facebook.react.views.text.CustomStyleSpan;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.SpannableStringBuilder;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.infer.annotation.Assertions;
import android.content.Context;
import android.text.method.QwertyKeyListener;
import com.facebook.react.views.view.ReactViewBackgroundDrawable;
import java.util.ArrayList;
import android.view.inputmethod.InputMethodManager;
import android.text.method.KeyListener;
import android.widget.EditText;
import java.util.Iterator;
import android.text.Editable;
import android.text.TextWatcher;

class ReactEditText$TextWatcherDelegator implements TextWatcher
{
    final /* synthetic */ ReactEditText this$0;
    
    private ReactEditText$TextWatcherDelegator(final ReactEditText this$0) {
        this.this$0 = this$0;
    }
    
    public void afterTextChanged(final Editable editable) {
        if (!this.this$0.mIsSettingTextFromJS && this.this$0.mListeners != null) {
            final Iterator<TextWatcher> iterator = this.this$0.mListeners.iterator();
            while (iterator.hasNext()) {
                iterator.next().afterTextChanged(editable);
            }
        }
    }
    
    public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
        if (!this.this$0.mIsSettingTextFromJS && this.this$0.mListeners != null) {
            final Iterator<TextWatcher> iterator = this.this$0.mListeners.iterator();
            while (iterator.hasNext()) {
                iterator.next().beforeTextChanged(charSequence, n, n2, n3);
            }
        }
    }
    
    public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
        if (!this.this$0.mIsSettingTextFromJS && this.this$0.mListeners != null) {
            final Iterator<TextWatcher> iterator = this.this$0.mListeners.iterator();
            while (iterator.hasNext()) {
                iterator.next().onTextChanged(charSequence, n, n2, n3);
            }
        }
    }
}
