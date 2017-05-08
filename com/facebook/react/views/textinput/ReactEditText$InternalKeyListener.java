// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.textinput;

import android.graphics.Typeface;
import android.view.MotionEvent;
import android.graphics.Rect;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.text.TextInlineImageSpan;
import android.text.Spanned;
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
import android.text.TextWatcher;
import java.util.ArrayList;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.view.KeyEvent;
import android.text.Editable;
import android.view.View;
import android.text.method.KeyListener;

class ReactEditText$InternalKeyListener implements KeyListener
{
    private int mInputType;
    
    public ReactEditText$InternalKeyListener() {
        this.mInputType = 0;
    }
    
    public void clearMetaKeyState(final View view, final Editable editable, final int n) {
        ReactEditText.sKeyListener.clearMetaKeyState(view, editable, n);
    }
    
    public int getInputType() {
        return this.mInputType;
    }
    
    public boolean onKeyDown(final View view, final Editable editable, final int n, final KeyEvent keyEvent) {
        return ReactEditText.sKeyListener.onKeyDown(view, editable, n, keyEvent);
    }
    
    public boolean onKeyOther(final View view, final Editable editable, final KeyEvent keyEvent) {
        return ReactEditText.sKeyListener.onKeyOther(view, editable, keyEvent);
    }
    
    public boolean onKeyUp(final View view, final Editable editable, final int n, final KeyEvent keyEvent) {
        return ReactEditText.sKeyListener.onKeyUp(view, editable, n, keyEvent);
    }
    
    public void setInputType(final int mInputType) {
        this.mInputType = mInputType;
    }
}
