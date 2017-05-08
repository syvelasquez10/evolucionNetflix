// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.design.R$string;
import android.support.v7.content.res.AppCompatResources;
import android.support.v4.content.ContextCompat;
import android.support.design.R$color;
import android.support.design.R$id;
import android.support.v7.widget.AppCompatTextView;
import android.text.method.TransformationMethod;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.util.SparseArray;
import android.widget.FrameLayout$LayoutParams;
import android.support.v4.widget.TextViewCompat;
import android.graphics.drawable.ColorDrawable;
import android.view.View$OnClickListener;
import android.support.design.R$layout;
import android.view.LayoutInflater;
import android.graphics.ColorFilter;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.text.method.PasswordTransformationMethod;
import android.graphics.drawable.DrawableContainer;
import android.os.Build$VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.support.v4.widget.Space;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.TintTypedArray;
import android.support.design.R$style;
import android.support.design.R$styleable;
import android.view.animation.Interpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.Rect;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.widget.EditText;
import android.content.res.ColorStateList;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.text.Editable;
import android.text.TextWatcher;

class TextInputLayout$1 implements TextWatcher
{
    final /* synthetic */ TextInputLayout this$0;
    
    TextInputLayout$1(final TextInputLayout this$0) {
        this.this$0 = this$0;
    }
    
    public void afterTextChanged(final Editable editable) {
        this.this$0.updateLabelState(!this.this$0.mRestoringSavedState);
        if (this.this$0.mCounterEnabled) {
            this.this$0.updateCounter(editable.length());
        }
    }
    
    public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
    }
    
    public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
    }
}
