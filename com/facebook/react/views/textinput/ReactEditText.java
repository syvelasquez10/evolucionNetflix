// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.textinput;

import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.KeyEvent;
import android.graphics.Rect;
import android.os.Build$VERSION;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.text.TextInlineImageSpan;
import android.text.Spanned;
import android.view.View;
import android.text.Editable;
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
import android.text.method.KeyListener;
import android.widget.EditText;

public class ReactEditText extends EditText
{
    private static final KeyListener sKeyListener;
    private boolean mBlurOnSubmit;
    private boolean mContainsImages;
    private ContentSizeWatcher mContentSizeWatcher;
    private int mDefaultGravityHorizontal;
    private int mDefaultGravityVertical;
    private boolean mDetectScrollMovement;
    private boolean mDisableFullscreen;
    private final InputMethodManager mInputMethodManager;
    private boolean mIsJSSettingFocus;
    private boolean mIsSettingTextFromJS;
    private final ReactEditText$InternalKeyListener mKeyListener;
    private ArrayList<TextWatcher> mListeners;
    private int mMostRecentEventCount;
    private int mNativeEventCount;
    private ReactViewBackgroundDrawable mReactBackgroundDrawable;
    private String mReturnKeyType;
    private SelectionWatcher mSelectionWatcher;
    private int mStagedInputType;
    private ReactEditText$TextWatcherDelegator mTextWatcherDelegator;
    
    static {
        sKeyListener = (KeyListener)QwertyKeyListener.getInstanceForFullKeyboard();
    }
    
    public ReactEditText(final Context context) {
        super(context);
        this.setFocusableInTouchMode(this.mDetectScrollMovement = false);
        this.mInputMethodManager = Assertions.assertNotNull(this.getContext().getSystemService("input_method"));
        this.mDefaultGravityHorizontal = (this.getGravity() & 0x800007);
        this.mDefaultGravityVertical = (this.getGravity() & 0x70);
        this.mNativeEventCount = 0;
        this.mMostRecentEventCount = 0;
        this.mIsSettingTextFromJS = false;
        this.mIsJSSettingFocus = false;
        this.mBlurOnSubmit = true;
        this.mDisableFullscreen = false;
        this.mListeners = null;
        this.mTextWatcherDelegator = null;
        this.mStagedInputType = this.getInputType();
        this.mKeyListener = new ReactEditText$InternalKeyListener();
    }
    
    private ReactViewBackgroundDrawable getOrCreateReactViewBackground() {
        if (this.mReactBackgroundDrawable == null) {
            this.mReactBackgroundDrawable = new ReactViewBackgroundDrawable();
            final Drawable background = this.getBackground();
            super.setBackground((Drawable)null);
            if (background == null) {
                super.setBackground((Drawable)this.mReactBackgroundDrawable);
            }
            else {
                super.setBackground((Drawable)new LayerDrawable(new Drawable[] { this.mReactBackgroundDrawable, background }));
            }
        }
        return this.mReactBackgroundDrawable;
    }
    
    private ReactEditText$TextWatcherDelegator getTextWatcherDelegator() {
        if (this.mTextWatcherDelegator == null) {
            this.mTextWatcherDelegator = new ReactEditText$TextWatcherDelegator(this, null);
        }
        return this.mTextWatcherDelegator;
    }
    
    private void hideSoftKeyboard() {
        this.mInputMethodManager.hideSoftInputFromWindow(this.getWindowToken(), 0);
    }
    
    private boolean isMultiline() {
        return (this.getInputType() & 0x20000) != 0x0;
    }
    
    private void manageSpans(final SpannableStringBuilder spannableStringBuilder) {
        int i = 0;
        for (Object[] spans = this.getText().getSpans(0, this.length(), (Class)Object.class); i < spans.length; ++i) {
            if (ForegroundColorSpan.class.isInstance(spans[i]) || BackgroundColorSpan.class.isInstance(spans[i]) || AbsoluteSizeSpan.class.isInstance(spans[i]) || CustomStyleSpan.class.isInstance(spans[i]) || ReactTagSpan.class.isInstance(spans[i])) {
                this.getText().removeSpan(spans[i]);
            }
            if ((this.getText().getSpanFlags(spans[i]) & 0x21) == 0x21) {
                final Object o = spans[i];
                final int spanStart = this.getText().getSpanStart(spans[i]);
                final int spanEnd = this.getText().getSpanEnd(spans[i]);
                final int spanFlags = this.getText().getSpanFlags(spans[i]);
                this.getText().removeSpan(spans[i]);
                if (sameTextForSpan(this.getText(), spannableStringBuilder, spanStart, spanEnd)) {
                    spannableStringBuilder.setSpan(o, spanStart, spanEnd, spanFlags);
                }
            }
        }
    }
    
    private static boolean sameTextForSpan(final Editable editable, final SpannableStringBuilder spannableStringBuilder, int i, final int n) {
        if (i > spannableStringBuilder.length() || n > spannableStringBuilder.length()) {
            return false;
        }
        while (i < n) {
            if (editable.charAt(i) != spannableStringBuilder.charAt(i)) {
                return false;
            }
            ++i;
        }
        return true;
    }
    
    private boolean showSoftKeyboard() {
        return this.mInputMethodManager.showSoftInput((View)this, 0);
    }
    
    private void updateImeOptions() {
        int imeOptions = 2;
        Label_0135: {
            if (this.mReturnKeyType != null) {
                final String mReturnKeyType = this.mReturnKeyType;
                switch (mReturnKeyType) {
                    case "done": {
                        imeOptions = 6;
                        break Label_0135;
                    }
                    case "send": {
                        imeOptions = 4;
                        break Label_0135;
                    }
                    case "search": {
                        imeOptions = 3;
                        break Label_0135;
                    }
                    case "previous": {
                        imeOptions = 7;
                        break Label_0135;
                    }
                    case "none": {
                        imeOptions = 1;
                        break Label_0135;
                    }
                    case "next": {
                        imeOptions = 5;
                    }
                    case "go": {
                        break Label_0135;
                    }
                }
            }
            imeOptions = 6;
        }
        if (this.mDisableFullscreen) {
            this.setImeOptions(imeOptions | 0x2000000);
            return;
        }
        this.setImeOptions(imeOptions);
    }
    
    public void addTextChangedListener(final TextWatcher textWatcher) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<TextWatcher>();
            super.addTextChangedListener((TextWatcher)this.getTextWatcherDelegator());
        }
        this.mListeners.add(textWatcher);
    }
    
    public void clearFocus() {
        this.setFocusableInTouchMode(false);
        super.clearFocus();
        this.hideSoftKeyboard();
    }
    
    void clearFocusFromJS() {
        this.clearFocus();
    }
    
    void commitStagedInputType() {
        if (this.getInputType() != this.mStagedInputType) {
            this.setInputType(this.mStagedInputType);
        }
    }
    
    public boolean getBlurOnSubmit() {
        return this.mBlurOnSubmit;
    }
    
    public boolean getDisableFullscreenUI() {
        return this.mDisableFullscreen;
    }
    
    public String getReturnKeyType() {
        return this.mReturnKeyType;
    }
    
    int getStagedInputType() {
        return this.mStagedInputType;
    }
    
    public int incrementAndGetEventCounter() {
        return ++this.mNativeEventCount;
    }
    
    public void invalidateDrawable(final Drawable drawable) {
        int i = 0;
        if (this.mContainsImages && this.getText() instanceof Spanned) {
            final Editable text = this.getText();
            for (TextInlineImageSpan[] array = (TextInlineImageSpan[])((Spanned)text).getSpans(0, ((Spanned)text).length(), (Class)TextInlineImageSpan.class); i < array.length; ++i) {
                if (array[i].getDrawable() == drawable) {
                    this.invalidate();
                }
            }
        }
        super.invalidateDrawable(drawable);
    }
    
    public boolean isLayoutRequested() {
        return this.mContentSizeWatcher != null && this.isMultiline();
    }
    
    public void maybeSetText(final ReactTextUpdate reactTextUpdate) {
        this.mMostRecentEventCount = reactTextUpdate.getJsEventCounter();
        if (this.mMostRecentEventCount >= this.mNativeEventCount) {
            final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder((CharSequence)reactTextUpdate.getText());
            this.manageSpans(spannableStringBuilder);
            this.mContainsImages = reactTextUpdate.containsImages();
            this.mIsSettingTextFromJS = true;
            this.getText().replace(0, this.length(), (CharSequence)spannableStringBuilder);
            this.mIsSettingTextFromJS = false;
            if (Build$VERSION.SDK_INT >= 23 && this.getBreakStrategy() != reactTextUpdate.getTextBreakStrategy()) {
                this.setBreakStrategy(reactTextUpdate.getTextBreakStrategy());
            }
        }
    }
    
    public void onAttachedToWindow() {
        int i = 0;
        super.onAttachedToWindow();
        if (this.mContainsImages && this.getText() instanceof Spanned) {
            final Editable text = this.getText();
            for (TextInlineImageSpan[] array = (TextInlineImageSpan[])((Spanned)text).getSpans(0, ((Spanned)text).length(), (Class)TextInlineImageSpan.class); i < array.length; ++i) {
                array[i].onAttachedToWindow();
            }
        }
    }
    
    public void onDetachedFromWindow() {
        int i = 0;
        super.onDetachedFromWindow();
        if (this.mContainsImages && this.getText() instanceof Spanned) {
            final Editable text = this.getText();
            for (TextInlineImageSpan[] array = (TextInlineImageSpan[])((Spanned)text).getSpans(0, ((Spanned)text).length(), (Class)TextInlineImageSpan.class); i < array.length; ++i) {
                array[i].onDetachedFromWindow();
            }
        }
    }
    
    public void onFinishTemporaryDetach() {
        int i = 0;
        super.onFinishTemporaryDetach();
        if (this.mContainsImages && this.getText() instanceof Spanned) {
            final Editable text = this.getText();
            for (TextInlineImageSpan[] array = (TextInlineImageSpan[])((Spanned)text).getSpans(0, ((Spanned)text).length(), (Class)TextInlineImageSpan.class); i < array.length; ++i) {
                array[i].onFinishTemporaryDetach();
            }
        }
    }
    
    protected void onFocusChanged(final boolean b, final int n, final Rect rect) {
        super.onFocusChanged(b, n, rect);
        if (b && this.mSelectionWatcher != null) {
            this.mSelectionWatcher.onSelectionChanged(this.getSelectionStart(), this.getSelectionEnd());
        }
    }
    
    public boolean onKeyUp(final int n, final KeyEvent keyEvent) {
        if (n == 66 && !this.isMultiline()) {
            this.hideSoftKeyboard();
            return true;
        }
        return super.onKeyUp(n, keyEvent);
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        if (this.mContentSizeWatcher != null) {
            this.mContentSizeWatcher.onLayout();
        }
    }
    
    protected void onSelectionChanged(final int n, final int n2) {
        super.onSelectionChanged(n, n2);
        if (this.mSelectionWatcher != null && this.hasFocus()) {
            this.mSelectionWatcher.onSelectionChanged(n, n2);
        }
    }
    
    public void onStartTemporaryDetach() {
        int i = 0;
        super.onStartTemporaryDetach();
        if (this.mContainsImages && this.getText() instanceof Spanned) {
            final Editable text = this.getText();
            for (TextInlineImageSpan[] array = (TextInlineImageSpan[])((Spanned)text).getSpans(0, ((Spanned)text).length(), (Class)TextInlineImageSpan.class); i < array.length; ++i) {
                array[i].onStartTemporaryDetach();
            }
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0: {
                this.mDetectScrollMovement = true;
                this.getParent().requestDisallowInterceptTouchEvent(true);
                break;
            }
            case 2: {
                if (this.mDetectScrollMovement) {
                    if (!this.canScrollVertically(-1) && !this.canScrollVertically(1) && !this.canScrollHorizontally(-1) && !this.canScrollHorizontally(1)) {
                        this.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                    this.mDetectScrollMovement = false;
                    break;
                }
                break;
            }
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public void removeTextChangedListener(final TextWatcher textWatcher) {
        if (this.mListeners != null) {
            this.mListeners.remove(textWatcher);
            if (this.mListeners.isEmpty()) {
                this.mListeners = null;
                super.removeTextChangedListener((TextWatcher)this.getTextWatcherDelegator());
            }
        }
    }
    
    public boolean requestFocus(final int n, final Rect rect) {
        if (this.isFocused()) {
            return true;
        }
        if (!this.mIsJSSettingFocus) {
            return false;
        }
        this.setFocusableInTouchMode(true);
        final boolean requestFocus = super.requestFocus(n, rect);
        this.showSoftKeyboard();
        return requestFocus;
    }
    
    public void requestFocusFromJS() {
        this.mIsJSSettingFocus = true;
        this.requestFocus();
        this.mIsJSSettingFocus = false;
    }
    
    public void setBackgroundColor(final int color) {
        if (color == 0 && this.mReactBackgroundDrawable == null) {
            return;
        }
        this.getOrCreateReactViewBackground().setColor(color);
    }
    
    public void setBlurOnSubmit(final boolean mBlurOnSubmit) {
        this.mBlurOnSubmit = mBlurOnSubmit;
    }
    
    public void setBorderColor(final int n, final float n2, final float n3) {
        this.getOrCreateReactViewBackground().setBorderColor(n, n2, n3);
    }
    
    public void setBorderRadius(final float radius) {
        this.getOrCreateReactViewBackground().setRadius(radius);
    }
    
    public void setBorderRadius(final float n, final int n2) {
        this.getOrCreateReactViewBackground().setRadius(n, n2);
    }
    
    public void setBorderStyle(final String borderStyle) {
        this.getOrCreateReactViewBackground().setBorderStyle(borderStyle);
    }
    
    public void setBorderWidth(final int n, final float n2) {
        this.getOrCreateReactViewBackground().setBorderWidth(n, n2);
    }
    
    public void setContentSizeWatcher(final ContentSizeWatcher mContentSizeWatcher) {
        this.mContentSizeWatcher = mContentSizeWatcher;
    }
    
    public void setDisableFullscreenUI(final boolean mDisableFullscreen) {
        this.mDisableFullscreen = mDisableFullscreen;
        this.updateImeOptions();
    }
    
    void setGravityHorizontal(final int n) {
        int mDefaultGravityHorizontal = n;
        if (n == 0) {
            mDefaultGravityHorizontal = this.mDefaultGravityHorizontal;
        }
        this.setGravity((this.getGravity() & 0xFFFFFFF8 & 0xFF7FFFF8) | mDefaultGravityHorizontal);
    }
    
    void setGravityVertical(final int n) {
        int mDefaultGravityVertical = n;
        if (n == 0) {
            mDefaultGravityVertical = this.mDefaultGravityVertical;
        }
        this.setGravity((this.getGravity() & 0xFFFFFF8F) | mDefaultGravityVertical);
    }
    
    public void setInputType(final int inputType) {
        final Typeface typeface = super.getTypeface();
        super.setInputType(inputType);
        this.mStagedInputType = inputType;
        super.setTypeface(typeface);
        this.mKeyListener.setInputType(inputType);
        this.setKeyListener((KeyListener)this.mKeyListener);
    }
    
    public void setReturnKeyType(final String mReturnKeyType) {
        this.mReturnKeyType = mReturnKeyType;
        this.updateImeOptions();
    }
    
    public void setSelection(final int n, final int n2) {
        if (this.mMostRecentEventCount < this.mNativeEventCount) {
            return;
        }
        super.setSelection(n, n2);
    }
    
    public void setSelectionWatcher(final SelectionWatcher mSelectionWatcher) {
        this.mSelectionWatcher = mSelectionWatcher;
    }
    
    void setStagedInputType(final int mStagedInputType) {
        this.mStagedInputType = mStagedInputType;
    }
    
    protected boolean verifyDrawable(final Drawable drawable) {
        int i = 0;
        if (this.mContainsImages && this.getText() instanceof Spanned) {
            final Editable text = this.getText();
            for (TextInlineImageSpan[] array = (TextInlineImageSpan[])((Spanned)text).getSpans(0, ((Spanned)text).length(), (Class)TextInlineImageSpan.class); i < array.length; ++i) {
                if (array[i].getDrawable() == drawable) {
                    return true;
                }
            }
        }
        return super.verifyDrawable(drawable);
    }
}
