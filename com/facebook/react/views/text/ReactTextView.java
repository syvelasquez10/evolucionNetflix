// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.text;

import android.text.Layout;
import android.text.Spanned;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.Drawable;
import android.content.Context;
import com.facebook.react.views.view.ReactViewBackgroundDrawable;
import android.text.TextUtils$TruncateAt;
import android.view.ViewGroup$LayoutParams;
import com.facebook.react.uimanager.ReactCompoundView;
import android.widget.TextView;

public class ReactTextView extends TextView implements ReactCompoundView
{
    private static final ViewGroup$LayoutParams EMPTY_LAYOUT_PARAMS;
    private boolean mContainsImages;
    private int mDefaultGravityHorizontal;
    private int mDefaultGravityVertical;
    private TextUtils$TruncateAt mEllipsizeLocation;
    private float mLineHeight;
    private int mNumberOfLines;
    private ReactViewBackgroundDrawable mReactBackgroundDrawable;
    private int mTextAlign;
    private boolean mTextIsSelectable;
    
    static {
        EMPTY_LAYOUT_PARAMS = new ViewGroup$LayoutParams(0, 0);
    }
    
    public ReactTextView(final Context context) {
        super(context);
        this.mLineHeight = Float.NaN;
        this.mTextAlign = 0;
        this.mNumberOfLines = Integer.MAX_VALUE;
        this.mEllipsizeLocation = TextUtils$TruncateAt.END;
        this.mDefaultGravityHorizontal = (this.getGravity() & 0x800007);
        this.mDefaultGravityVertical = (this.getGravity() & 0x70);
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
    
    public void invalidateDrawable(final Drawable drawable) {
        int i = 0;
        if (this.mContainsImages && this.getText() instanceof Spanned) {
            final Spanned spanned = (Spanned)this.getText();
            for (TextInlineImageSpan[] array = (TextInlineImageSpan[])spanned.getSpans(0, spanned.length(), (Class)TextInlineImageSpan.class); i < array.length; ++i) {
                if (array[i].getDrawable() == drawable) {
                    this.invalidate();
                }
            }
        }
        super.invalidateDrawable(drawable);
    }
    
    public void onAttachedToWindow() {
        int i = 0;
        super.onAttachedToWindow();
        if (this.mContainsImages && this.getText() instanceof Spanned) {
            final Spanned spanned = (Spanned)this.getText();
            for (TextInlineImageSpan[] array = (TextInlineImageSpan[])spanned.getSpans(0, spanned.length(), (Class)TextInlineImageSpan.class); i < array.length; ++i) {
                array[i].onAttachedToWindow();
            }
        }
    }
    
    public void onDetachedFromWindow() {
        int i = 0;
        super.onDetachedFromWindow();
        if (this.mContainsImages && this.getText() instanceof Spanned) {
            final Spanned spanned = (Spanned)this.getText();
            for (TextInlineImageSpan[] array = (TextInlineImageSpan[])spanned.getSpans(0, spanned.length(), (Class)TextInlineImageSpan.class); i < array.length; ++i) {
                array[i].onDetachedFromWindow();
            }
        }
    }
    
    public void onFinishTemporaryDetach() {
        int i = 0;
        super.onFinishTemporaryDetach();
        if (this.mContainsImages && this.getText() instanceof Spanned) {
            final Spanned spanned = (Spanned)this.getText();
            for (TextInlineImageSpan[] array = (TextInlineImageSpan[])spanned.getSpans(0, spanned.length(), (Class)TextInlineImageSpan.class); i < array.length; ++i) {
                array[i].onFinishTemporaryDetach();
            }
        }
    }
    
    public void onStartTemporaryDetach() {
        int i = 0;
        super.onStartTemporaryDetach();
        if (this.mContainsImages && this.getText() instanceof Spanned) {
            final Spanned spanned = (Spanned)this.getText();
            for (TextInlineImageSpan[] array = (TextInlineImageSpan[])spanned.getSpans(0, spanned.length(), (Class)TextInlineImageSpan.class); i < array.length; ++i) {
                array[i].onStartTemporaryDetach();
            }
        }
    }
    
    public int reactTagForTouch(final float n, final float n2) {
        final Spanned spanned = (Spanned)this.getText();
        int id = this.getId();
        final int n3 = (int)n;
        final int n4 = (int)n2;
        final Layout layout = this.getLayout();
        int n5;
        if (layout == null) {
            n5 = id;
        }
        else {
            final int lineForVertical = layout.getLineForVertical(n4);
            final int n6 = (int)layout.getLineLeft(lineForVertical);
            final int n7 = (int)layout.getLineRight(lineForVertical);
            n5 = id;
            if (n3 >= n6) {
                n5 = id;
                if (n3 <= n7) {
                    final int offsetForHorizontal = layout.getOffsetForHorizontal(lineForVertical, (float)n3);
                    final ReactTagSpan[] array = (ReactTagSpan[])spanned.getSpans(offsetForHorizontal, offsetForHorizontal, (Class)ReactTagSpan.class);
                    n5 = id;
                    if (array != null) {
                        int length = spanned.length();
                        int n8 = 0;
                        while (true) {
                            n5 = id;
                            if (n8 >= array.length) {
                                break;
                            }
                            final int spanStart = spanned.getSpanStart((Object)array[n8]);
                            final int spanEnd = spanned.getSpanEnd((Object)array[n8]);
                            int n9 = length;
                            int reactTag = id;
                            if (spanEnd > offsetForHorizontal) {
                                n9 = length;
                                reactTag = id;
                                if (spanEnd - spanStart <= length) {
                                    reactTag = array[n8].getReactTag();
                                    n9 = spanEnd - spanStart;
                                }
                            }
                            ++n8;
                            length = n9;
                            id = reactTag;
                        }
                    }
                }
            }
        }
        return n5;
    }
    
    public void setBackgroundColor(final int color) {
        if (color == 0 && this.mReactBackgroundDrawable == null) {
            return;
        }
        this.getOrCreateReactViewBackground().setColor(color);
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
    
    public void setEllipsizeLocation(final TextUtils$TruncateAt mEllipsizeLocation) {
        this.mEllipsizeLocation = mEllipsizeLocation;
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
    
    public void setNumberOfLines(final int n) {
        int mNumberOfLines = n;
        if (n == 0) {
            mNumberOfLines = Integer.MAX_VALUE;
        }
        this.setMaxLines(this.mNumberOfLines = mNumberOfLines);
    }
    
    public void setText(final ReactTextUpdate reactTextUpdate) {
        this.mContainsImages = reactTextUpdate.containsImages();
        if (this.getLayoutParams() == null) {
            this.setLayoutParams(ReactTextView.EMPTY_LAYOUT_PARAMS);
        }
        this.setText((CharSequence)reactTextUpdate.getText());
        this.setPadding((int)Math.floor(reactTextUpdate.getPaddingLeft()), (int)Math.floor(reactTextUpdate.getPaddingTop()), (int)Math.floor(reactTextUpdate.getPaddingRight()), (int)Math.floor(reactTextUpdate.getPaddingBottom()));
        final int textAlign = reactTextUpdate.getTextAlign();
        if (this.mTextAlign != textAlign) {
            this.mTextAlign = textAlign;
        }
        this.setGravityHorizontal(this.mTextAlign);
    }
    
    public void setTextIsSelectable(final boolean mTextIsSelectable) {
        super.setTextIsSelectable(this.mTextIsSelectable = mTextIsSelectable);
    }
    
    public void updateView() {
        TextUtils$TruncateAt mEllipsizeLocation;
        if (this.mNumberOfLines == Integer.MAX_VALUE) {
            mEllipsizeLocation = null;
        }
        else {
            mEllipsizeLocation = this.mEllipsizeLocation;
        }
        this.setEllipsize(mEllipsizeLocation);
    }
    
    protected boolean verifyDrawable(final Drawable drawable) {
        int i = 0;
        if (this.mContainsImages && this.getText() instanceof Spanned) {
            final Spanned spanned = (Spanned)this.getText();
            for (TextInlineImageSpan[] array = (TextInlineImageSpan[])spanned.getSpans(0, spanned.length(), (Class)TextInlineImageSpan.class); i < array.length; ++i) {
                if (array[i].getDrawable() == drawable) {
                    return true;
                }
            }
        }
        return super.verifyDrawable(drawable);
    }
}
