// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.widget;

import android.view.View;
import android.text.TextPaint;
import android.text.StaticLayout;
import android.text.Layout$Alignment;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import com.netflix.mediaclient.util.l10n.BidiMarker;
import android.text.style.ImageSpan;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.content.ContextCompat;
import com.netflix.android.widgetry.R$drawable;
import com.netflix.android.widgetry.utils.UiUtils;
import android.support.v4.view.animation.PathInterpolatorCompat;
import com.netflix.android.widgetry.utils.LogUtils;
import android.text.Spannable;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

public class ThumbsToMatchPercentageAnimator
{
    private static final Interpolator CUBIC_BEZIER_DESELECTION;
    private static final Interpolator CUBIC_BEZIER_SELECTION;
    private static final int STATE_FIRST_HALF = 1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_SECOND_HALF = 2;
    private static final String TAG;
    private static final String TEXT_PLACEHOLDER = "X";
    private static final String TEXT_SPACE = " ";
    private final ThumbsToMatchPercentageAnimator$BouncyAnimator mBouncyAnimator;
    private final ViewGroup mContainerToAnimate;
    private final int mIconWidth;
    private String mMatchPercentageText;
    private final TextView mMatchPercentageTextView;
    private final int mNewLabelId;
    private final int mPercentStringId;
    private final boolean mRtl;
    private int mTextHeight;
    private int mTextWidth;
    private final Spannable mThumbDownIcon;
    private final Spannable mThumbUpIcon;
    
    static {
        TAG = LogUtils.getTag(ThumbsToMatchPercentageAnimator.class);
        CUBIC_BEZIER_SELECTION = PathInterpolatorCompat.create(0.175f, 0.885f, 0.32f, 1.275f);
        CUBIC_BEZIER_DESELECTION = PathInterpolatorCompat.create(0.6f, -0.28f, 0.735f, 0.045f);
    }
    
    public ThumbsToMatchPercentageAnimator(final TextView mMatchPercentageTextView, final int mPercentStringId, final int mNewLabelId, final int n) {
        this.mBouncyAnimator = new ThumbsToMatchPercentageAnimator$BouncyAnimator(this, null);
        this.mMatchPercentageTextView = mMatchPercentageTextView;
        this.mContainerToAnimate = (ViewGroup)this.mMatchPercentageTextView.getParent();
        this.mRtl = UiUtils.isCurrentLocaleRTL();
        this.mThumbUpIcon = getDrawableAsSpannable(this.mMatchPercentageTextView, R$drawable.ic_thumbs_up, this.mRtl, n);
        this.mThumbDownIcon = getDrawableAsSpannable(this.mMatchPercentageTextView, R$drawable.ic_thumbs_down, this.mRtl, n);
        this.mPercentStringId = mPercentStringId;
        this.mNewLabelId = mNewLabelId;
        this.mIconWidth = (int)this.mMatchPercentageTextView.getTextSize();
    }
    
    private static Spannable getDrawableAsSpannable(final TextView textView, final int n, final boolean b, final int n2) {
        final Drawable mutate = ContextCompat.getDrawable(textView.getContext(), n).mutate();
        DrawableCompat.setTint(mutate, n2);
        final ImageSpan imageSpan = new ImageSpan(mutate, 0);
        imageSpan.getDrawable().setBounds(0, 0, (int)textView.getTextSize(), (int)textView.getTextSize());
        if (b) {
            final SpannableString spannableString = new SpannableString((CharSequence)UiUtils.prependBidiMarkerIfRtl("X", BidiMarker.FORCED_RTL));
            ((Spannable)spannableString).setSpan((Object)imageSpan, 1, 2, 17);
            return (Spannable)spannableString;
        }
        final SpannableString spannableString2 = new SpannableString((CharSequence)"X");
        ((Spannable)spannableString2).setSpan((Object)imageSpan, 0, 1, 17);
        return (Spannable)spannableString2;
    }
    
    private Resources getResources() {
        return this.mMatchPercentageTextView.getResources();
    }
    
    private int getTranslationDirection() {
        if (this.mRtl) {
            return 1;
        }
        return -1;
    }
    
    private void prepareTextViewLayout(final int n, final int n2, final int n3) {
        float n4 = 1.0f;
        final float n5 = 0.0f;
        StaticLayout staticLayout;
        if (this.mMatchPercentageText == null || n3 != 0) {
            final TextPaint paint = this.mMatchPercentageTextView.getPaint();
            final Layout$Alignment align_NORMAL = Layout$Alignment.ALIGN_NORMAL;
            if (this.mMatchPercentageTextView.getLayout() != null) {
                n4 = this.mMatchPercentageTextView.getLayout().getSpacingMultiplier();
            }
            float spacingAdd;
            if (this.mMatchPercentageTextView.getLayout() == null) {
                spacingAdd = 0.0f;
            }
            else {
                spacingAdd = this.mMatchPercentageTextView.getLayout().getSpacingAdd();
            }
            staticLayout = new StaticLayout((CharSequence)" ", paint, Integer.MAX_VALUE, align_NORMAL, n4, spacingAdd, true);
        }
        else {
            final String mMatchPercentageText = this.mMatchPercentageText;
            final TextPaint paint2 = this.mMatchPercentageTextView.getPaint();
            final Layout$Alignment align_NORMAL2 = Layout$Alignment.ALIGN_NORMAL;
            if (this.mMatchPercentageTextView.getLayout() != null) {
                n4 = this.mMatchPercentageTextView.getLayout().getSpacingMultiplier();
            }
            float spacingAdd2;
            if (this.mMatchPercentageTextView.getLayout() == null) {
                spacingAdd2 = 0.0f;
            }
            else {
                spacingAdd2 = this.mMatchPercentageTextView.getLayout().getSpacingAdd();
            }
            staticLayout = new StaticLayout((CharSequence)mMatchPercentageText, paint2, Integer.MAX_VALUE, align_NORMAL2, n4, spacingAdd2, true);
        }
        final float n6 = this.mIconWidth;
        float measureText;
        if (this.mMatchPercentageText == null) {
            measureText = n5;
        }
        else {
            measureText = this.mMatchPercentageTextView.getPaint().measureText(this.mMatchPercentageText, 0, this.mMatchPercentageText.length());
        }
        this.mTextWidth = (int)Math.max(n6, measureText);
        this.mTextHeight = staticLayout.getHeight() + n2;
        this.mMatchPercentageTextView.getLayoutParams().width = this.mTextWidth + n;
        this.mMatchPercentageTextView.getLayoutParams().height = this.mTextHeight;
        this.mMatchPercentageTextView.requestLayout();
        this.mContainerToAnimate.getLayoutParams().height = this.mTextHeight;
        this.mContainerToAnimate.requestLayout();
    }
    
    private void start(final int n, final CharSequence text, final int n2, final boolean b) {
        if (!b) {
            this.mMatchPercentageTextView.setText(text);
            this.mContainerToAnimate.setTranslationX((float)(this.getTranslationDirection() * n2));
            return;
        }
        this.mBouncyAnimator.start(n, text, n2);
    }
    
    public void update(final int n, int n2, final boolean b, final boolean b2) {
        Object o = null;
        final int n3 = 0;
        final int paddingEnd = this.mMatchPercentageTextView.getPaddingEnd();
        final int paddingStart = this.mMatchPercentageTextView.getPaddingStart();
        final int paddingTop = this.mMatchPercentageTextView.getPaddingTop();
        final int paddingBottom = this.mMatchPercentageTextView.getPaddingBottom();
        if (b) {
            this.mMatchPercentageText = this.getResources().getString(this.mNewLabelId);
        }
        else if (n2 == 0) {
            this.mMatchPercentageText = null;
        }
        else {
            this.mMatchPercentageText = this.getResources().getString(this.mPercentStringId, new Object[] { n2 });
        }
        this.prepareTextViewLayout(paddingEnd + paddingStart, paddingTop + paddingBottom, n);
        switch (n) {
            default: {
                n2 = n3;
                break;
            }
            case 2: {
                o = this.mThumbUpIcon;
                n2 = this.mTextWidth - this.mIconWidth;
                break;
            }
            case 1: {
                o = this.mThumbDownIcon;
                n2 = this.mTextWidth - this.mIconWidth;
                break;
            }
            case 0: {
                final String s = (String)(o = this.mMatchPercentageText);
                n2 = n3;
                if (this.mMatchPercentageText == null) {
                    n2 = this.mIconWidth + UiUtils.getMarginEnd((View)this.mMatchPercentageTextView);
                    o = s;
                    break;
                }
                break;
            }
        }
        this.start(n, (CharSequence)o, n2, b2);
    }
}
