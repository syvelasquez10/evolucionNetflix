// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.text.method.TransformationMethod;
import android.support.v7.internal.text.AllCapsTransformationMethod;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.Context;
import android.support.v7.internal.widget.ThemeUtils;
import android.os.Build$VERSION;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.support.v7.appcompat.R$attr;
import android.widget.TextView;

class AppCompatTextHelper
{
    private static final int[] TEXT_APPEARANCE_ATTRS;
    private static final int[] VIEW_ATTRS;
    private final TextView mView;
    
    static {
        VIEW_ATTRS = new int[] { 16842804 };
        TEXT_APPEARANCE_ATTRS = new int[] { R$attr.textAllCaps };
    }
    
    AppCompatTextHelper(final TextView mView) {
        this.mView = mView;
    }
    
    void loadFromAttributes(final AttributeSet set, int n) {
        final Context context = this.mView.getContext();
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, AppCompatTextHelper.VIEW_ATTRS, n, 0);
        final int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        if (resourceId != -1) {
            final TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, R$styleable.TextAppearance);
            if (obtainStyledAttributes2.hasValue(R$styleable.TextAppearance_textAllCaps)) {
                this.setAllCaps(obtainStyledAttributes2.getBoolean(R$styleable.TextAppearance_textAllCaps, false));
            }
            obtainStyledAttributes2.recycle();
        }
        final TypedArray obtainStyledAttributes3 = context.obtainStyledAttributes(set, AppCompatTextHelper.TEXT_APPEARANCE_ATTRS, n, 0);
        if (obtainStyledAttributes3.hasValue(0)) {
            this.setAllCaps(obtainStyledAttributes3.getBoolean(0, false));
        }
        obtainStyledAttributes3.recycle();
        final ColorStateList textColors = this.mView.getTextColors();
        if (textColors != null && !textColors.isStateful()) {
            if (Build$VERSION.SDK_INT < 21) {
                n = ThemeUtils.getDisabledThemeAttrColor(context, 16842808);
            }
            else {
                n = ThemeUtils.getThemeAttrColor(context, 16842808);
            }
            this.mView.setTextColor(ThemeUtils.createDisabledStateList(textColors.getDefaultColor(), n));
        }
    }
    
    void onSetTextAppearance(final Context context, final int n) {
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(n, AppCompatTextHelper.TEXT_APPEARANCE_ATTRS);
        if (obtainStyledAttributes.hasValue(0)) {
            this.setAllCaps(obtainStyledAttributes.getBoolean(0, false));
        }
        obtainStyledAttributes.recycle();
    }
    
    void setAllCaps(final boolean b) {
        final TextView mView = this.mView;
        Object transformationMethod;
        if (b) {
            transformationMethod = new AllCapsTransformationMethod(this.mView.getContext());
        }
        else {
            transformationMethod = null;
        }
        mView.setTransformationMethod((TransformationMethod)transformationMethod);
    }
}
