// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.design.R$string;
import android.support.v7.content.res.AppCompatResources;
import android.support.v4.content.ContextCompat;
import android.support.design.R$color;
import android.text.method.TransformationMethod;
import android.os.Parcelable;
import android.graphics.Typeface;
import android.graphics.Canvas;
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
import android.text.TextWatcher;
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
import android.graphics.Rect;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.widget.EditText;
import android.content.res.ColorStateList;
import android.widget.TextView;
import android.widget.LinearLayout;

public class TextInputLayout extends LinearLayout
{
    private static final int ANIMATION_DURATION = 200;
    private static final int INVALID_MAX_LENGTH = -1;
    private static final String LOG_TAG = "TextInputLayout";
    private ValueAnimatorCompat mAnimator;
    final CollapsingTextHelper mCollapsingTextHelper;
    boolean mCounterEnabled;
    private int mCounterMaxLength;
    private int mCounterOverflowTextAppearance;
    private boolean mCounterOverflowed;
    private int mCounterTextAppearance;
    private TextView mCounterView;
    private ColorStateList mDefaultTextColor;
    EditText mEditText;
    private CharSequence mError;
    private boolean mErrorEnabled;
    private boolean mErrorShown;
    private int mErrorTextAppearance;
    TextView mErrorView;
    private ColorStateList mFocusedTextColor;
    private boolean mHasPasswordToggleTintList;
    private boolean mHasPasswordToggleTintMode;
    private boolean mHasReconstructedEditTextBackground;
    private CharSequence mHint;
    private boolean mHintAnimationEnabled;
    private boolean mHintEnabled;
    private boolean mHintExpanded;
    private boolean mInDrawableStateChanged;
    private LinearLayout mIndicatorArea;
    private int mIndicatorsAdded;
    private final FrameLayout mInputFrame;
    private Drawable mOriginalEditTextEndDrawable;
    private CharSequence mPasswordToggleContentDesc;
    private Drawable mPasswordToggleDrawable;
    private Drawable mPasswordToggleDummyDrawable;
    private boolean mPasswordToggleEnabled;
    private ColorStateList mPasswordToggleTintList;
    private PorterDuff$Mode mPasswordToggleTintMode;
    private CheckableImageButton mPasswordToggleView;
    private boolean mPasswordToggledVisible;
    private Paint mTmpPaint;
    private final Rect mTmpRect;
    
    public TextInputLayout(final Context context) {
        this(context, null);
    }
    
    public TextInputLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public TextInputLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set);
        this.mTmpRect = new Rect();
        this.mCollapsingTextHelper = new CollapsingTextHelper((View)this);
        ThemeUtils.checkAppCompatTheme(context);
        this.setOrientation(1);
        this.setWillNotDraw(false);
        this.setAddStatesFromChildren(true);
        (this.mInputFrame = new FrameLayout(context)).setAddStatesFromChildren(true);
        this.addView((View)this.mInputFrame);
        this.mCollapsingTextHelper.setTextSizeInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        this.mCollapsingTextHelper.setPositionInterpolator((Interpolator)new AccelerateInterpolator());
        this.mCollapsingTextHelper.setCollapsedTextGravity(8388659);
        this.mHintExpanded = (this.mCollapsingTextHelper.getExpansionFraction() == 1.0f);
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, R$styleable.TextInputLayout, n, R$style.Widget_Design_TextInputLayout);
        this.mHintEnabled = obtainStyledAttributes.getBoolean(R$styleable.TextInputLayout_hintEnabled, true);
        this.setHint(obtainStyledAttributes.getText(R$styleable.TextInputLayout_android_hint));
        this.mHintAnimationEnabled = obtainStyledAttributes.getBoolean(R$styleable.TextInputLayout_hintAnimationEnabled, true);
        if (obtainStyledAttributes.hasValue(R$styleable.TextInputLayout_android_textColorHint)) {
            final ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R$styleable.TextInputLayout_android_textColorHint);
            this.mFocusedTextColor = colorStateList;
            this.mDefaultTextColor = colorStateList;
        }
        if (obtainStyledAttributes.getResourceId(R$styleable.TextInputLayout_hintTextAppearance, -1) != -1) {
            this.setHintTextAppearance(obtainStyledAttributes.getResourceId(R$styleable.TextInputLayout_hintTextAppearance, 0));
        }
        this.mErrorTextAppearance = obtainStyledAttributes.getResourceId(R$styleable.TextInputLayout_errorTextAppearance, 0);
        final boolean boolean1 = obtainStyledAttributes.getBoolean(R$styleable.TextInputLayout_errorEnabled, false);
        final boolean boolean2 = obtainStyledAttributes.getBoolean(R$styleable.TextInputLayout_counterEnabled, false);
        this.setCounterMaxLength(obtainStyledAttributes.getInt(R$styleable.TextInputLayout_counterMaxLength, -1));
        this.mCounterTextAppearance = obtainStyledAttributes.getResourceId(R$styleable.TextInputLayout_counterTextAppearance, 0);
        this.mCounterOverflowTextAppearance = obtainStyledAttributes.getResourceId(R$styleable.TextInputLayout_counterOverflowTextAppearance, 0);
        this.mPasswordToggleEnabled = obtainStyledAttributes.getBoolean(R$styleable.TextInputLayout_passwordToggleEnabled, true);
        this.mPasswordToggleDrawable = obtainStyledAttributes.getDrawable(R$styleable.TextInputLayout_passwordToggleDrawable);
        this.mPasswordToggleContentDesc = obtainStyledAttributes.getText(R$styleable.TextInputLayout_passwordToggleContentDescription);
        if (obtainStyledAttributes.hasValue(R$styleable.TextInputLayout_passwordToggleTint)) {
            this.mHasPasswordToggleTintList = true;
            this.mPasswordToggleTintList = obtainStyledAttributes.getColorStateList(R$styleable.TextInputLayout_passwordToggleTint);
        }
        if (obtainStyledAttributes.hasValue(R$styleable.TextInputLayout_passwordToggleTintMode)) {
            this.mHasPasswordToggleTintMode = true;
            this.mPasswordToggleTintMode = ViewUtils.parseTintMode(obtainStyledAttributes.getInt(R$styleable.TextInputLayout_passwordToggleTintMode, -1), null);
        }
        obtainStyledAttributes.recycle();
        this.setErrorEnabled(boolean1);
        this.setCounterEnabled(boolean2);
        this.applyPasswordToggleTint();
        if (ViewCompat.getImportantForAccessibility((View)this) == 0) {
            ViewCompat.setImportantForAccessibility((View)this, 1);
        }
        ViewCompat.setAccessibilityDelegate((View)this, new TextInputLayout$TextInputAccessibilityDelegate(this));
    }
    
    private void addIndicator(final TextView textView, final int n) {
        if (this.mIndicatorArea == null) {
            (this.mIndicatorArea = new LinearLayout(this.getContext())).setOrientation(0);
            this.addView((View)this.mIndicatorArea, -1, -2);
            this.mIndicatorArea.addView((View)new Space(this.getContext()), (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(0, 0, 1.0f));
            if (this.mEditText != null) {
                this.adjustIndicatorPadding();
            }
        }
        this.mIndicatorArea.setVisibility(0);
        this.mIndicatorArea.addView((View)textView, n);
        ++this.mIndicatorsAdded;
    }
    
    private void adjustIndicatorPadding() {
        ViewCompat.setPaddingRelative((View)this.mIndicatorArea, ViewCompat.getPaddingStart((View)this.mEditText), 0, ViewCompat.getPaddingEnd((View)this.mEditText), this.mEditText.getPaddingBottom());
    }
    
    private void animateToExpansionFraction(final float n) {
        if (this.mCollapsingTextHelper.getExpansionFraction() == n) {
            return;
        }
        if (this.mAnimator == null) {
            (this.mAnimator = ViewUtils.createAnimator()).setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
            this.mAnimator.setDuration(200L);
            this.mAnimator.addUpdateListener(new TextInputLayout$5(this));
        }
        this.mAnimator.setFloatValues(this.mCollapsingTextHelper.getExpansionFraction(), n);
        this.mAnimator.start();
    }
    
    private void applyPasswordToggleTint() {
        if (this.mPasswordToggleDrawable != null && (this.mHasPasswordToggleTintList || this.mHasPasswordToggleTintMode)) {
            this.mPasswordToggleDrawable = DrawableCompat.wrap(this.mPasswordToggleDrawable).mutate();
            if (this.mHasPasswordToggleTintList) {
                DrawableCompat.setTintList(this.mPasswordToggleDrawable, this.mPasswordToggleTintList);
            }
            if (this.mHasPasswordToggleTintMode) {
                DrawableCompat.setTintMode(this.mPasswordToggleDrawable, this.mPasswordToggleTintMode);
            }
            if (this.mPasswordToggleView != null && this.mPasswordToggleView.getDrawable() != this.mPasswordToggleDrawable) {
                this.mPasswordToggleView.setImageDrawable(this.mPasswordToggleDrawable);
            }
        }
    }
    
    private static boolean arrayContains(final int[] array, final int n) {
        final boolean b = false;
        final int length = array.length;
        int n2 = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n2 >= length) {
                break;
            }
            if (array[n2] == n) {
                b2 = true;
                break;
            }
            ++n2;
        }
        return b2;
    }
    
    private void collapseHint(final boolean b) {
        if (this.mAnimator != null && this.mAnimator.isRunning()) {
            this.mAnimator.cancel();
        }
        if (b && this.mHintAnimationEnabled) {
            this.animateToExpansionFraction(1.0f);
        }
        else {
            this.mCollapsingTextHelper.setExpansionFraction(1.0f);
        }
        this.mHintExpanded = false;
    }
    
    private void ensureBackgroundDrawableStateWorkaround() {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT == 21 || sdk_INT == 22) {
            final Drawable background = this.mEditText.getBackground();
            if (background != null && !this.mHasReconstructedEditTextBackground) {
                final Drawable drawable = background.getConstantState().newDrawable();
                if (background instanceof DrawableContainer) {
                    this.mHasReconstructedEditTextBackground = DrawableUtils.setContainerConstantState((DrawableContainer)background, drawable.getConstantState());
                }
                if (!this.mHasReconstructedEditTextBackground) {
                    this.mEditText.setBackgroundDrawable(drawable);
                    this.mHasReconstructedEditTextBackground = true;
                }
            }
        }
    }
    
    private void expandHint(final boolean b) {
        if (this.mAnimator != null && this.mAnimator.isRunning()) {
            this.mAnimator.cancel();
        }
        if (b && this.mHintAnimationEnabled) {
            this.animateToExpansionFraction(0.0f);
        }
        else {
            this.mCollapsingTextHelper.setExpansionFraction(0.0f);
        }
        this.mHintExpanded = true;
    }
    
    private boolean hasPasswordTransformation() {
        return this.mEditText != null && this.mEditText.getTransformationMethod() instanceof PasswordTransformationMethod;
    }
    
    private static void recursiveSetEnabled(final ViewGroup viewGroup, final boolean enabled) {
        for (int childCount = viewGroup.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = viewGroup.getChildAt(i);
            child.setEnabled(enabled);
            if (child instanceof ViewGroup) {
                recursiveSetEnabled((ViewGroup)child, enabled);
            }
        }
    }
    
    private void removeIndicator(final TextView textView) {
        if (this.mIndicatorArea != null) {
            this.mIndicatorArea.removeView((View)textView);
            if (--this.mIndicatorsAdded == 0) {
                this.mIndicatorArea.setVisibility(8);
            }
        }
    }
    
    private void setEditText(final EditText mEditText) {
        if (this.mEditText != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (!(mEditText instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.mEditText = mEditText;
        if (!this.hasPasswordTransformation()) {
            this.mCollapsingTextHelper.setTypefaces(this.mEditText.getTypeface());
        }
        this.mCollapsingTextHelper.setExpandedTextSize(this.mEditText.getTextSize());
        final int gravity = this.mEditText.getGravity();
        this.mCollapsingTextHelper.setCollapsedTextGravity((0x800007 & gravity) | 0x30);
        this.mCollapsingTextHelper.setExpandedTextGravity(gravity);
        this.mEditText.addTextChangedListener((TextWatcher)new TextInputLayout$1(this));
        if (this.mDefaultTextColor == null) {
            this.mDefaultTextColor = this.mEditText.getHintTextColors();
        }
        if (this.mHintEnabled && TextUtils.isEmpty(this.mHint)) {
            this.setHint(this.mEditText.getHint());
            this.mEditText.setHint((CharSequence)null);
        }
        if (this.mCounterView != null) {
            this.updateCounter(this.mEditText.getText().length());
        }
        if (this.mIndicatorArea != null) {
            this.adjustIndicatorPadding();
        }
        this.updatePasswordToggleView();
        this.updateLabelState(false);
    }
    
    private void setError(final CharSequence text, final boolean b) {
        boolean mErrorShown = true;
        this.mError = text;
        if (!this.mErrorEnabled) {
            if (TextUtils.isEmpty(text)) {
                return;
            }
            this.setErrorEnabled(true);
        }
        if (TextUtils.isEmpty(text)) {
            mErrorShown = false;
        }
        this.mErrorShown = mErrorShown;
        ViewCompat.animate((View)this.mErrorView).cancel();
        if (this.mErrorShown) {
            this.mErrorView.setText(text);
            this.mErrorView.setVisibility(0);
            if (b) {
                if (ViewCompat.getAlpha((View)this.mErrorView) == 1.0f) {
                    ViewCompat.setAlpha((View)this.mErrorView, 0.0f);
                }
                ViewCompat.animate((View)this.mErrorView).alpha(1.0f).setDuration(200L).setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setListener(new TextInputLayout$2(this)).start();
            }
            else {
                ViewCompat.setAlpha((View)this.mErrorView, 1.0f);
            }
        }
        else if (this.mErrorView.getVisibility() == 0) {
            if (b) {
                ViewCompat.animate((View)this.mErrorView).alpha(0.0f).setDuration(200L).setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setListener(new TextInputLayout$3(this, text)).start();
            }
            else {
                this.mErrorView.setText(text);
                this.mErrorView.setVisibility(4);
            }
        }
        this.updateEditTextBackground();
        this.updateLabelState(b);
    }
    
    private void setHintInternal(final CharSequence charSequence) {
        this.mHint = charSequence;
        this.mCollapsingTextHelper.setText(charSequence);
    }
    
    private boolean shouldShowPasswordIcon() {
        return this.mPasswordToggleEnabled && (this.hasPasswordTransformation() || this.mPasswordToggledVisible);
    }
    
    private void updateEditTextBackground() {
        if (this.mEditText != null) {
            final Drawable background = this.mEditText.getBackground();
            if (background != null) {
                this.ensureBackgroundDrawableStateWorkaround();
                Drawable mutate = background;
                if (android.support.v7.widget.DrawableUtils.canSafelyMutateDrawable(background)) {
                    mutate = background.mutate();
                }
                if (this.mErrorShown && this.mErrorView != null) {
                    mutate.setColorFilter((ColorFilter)AppCompatDrawableManager.getPorterDuffColorFilter(this.mErrorView.getCurrentTextColor(), PorterDuff$Mode.SRC_IN));
                    return;
                }
                if (this.mCounterOverflowed && this.mCounterView != null) {
                    mutate.setColorFilter((ColorFilter)AppCompatDrawableManager.getPorterDuffColorFilter(this.mCounterView.getCurrentTextColor(), PorterDuff$Mode.SRC_IN));
                    return;
                }
                DrawableCompat.clearColorFilter(mutate);
                this.mEditText.refreshDrawableState();
            }
        }
    }
    
    private void updateInputLayoutMargins() {
        final LinearLayout$LayoutParams linearLayout$LayoutParams = (LinearLayout$LayoutParams)this.mInputFrame.getLayoutParams();
        int topMargin;
        if (this.mHintEnabled) {
            if (this.mTmpPaint == null) {
                this.mTmpPaint = new Paint();
            }
            this.mTmpPaint.setTypeface(this.mCollapsingTextHelper.getCollapsedTypeface());
            this.mTmpPaint.setTextSize(this.mCollapsingTextHelper.getCollapsedTextSize());
            topMargin = (int)(-this.mTmpPaint.ascent());
        }
        else {
            topMargin = 0;
        }
        if (topMargin != linearLayout$LayoutParams.topMargin) {
            linearLayout$LayoutParams.topMargin = topMargin;
            this.mInputFrame.requestLayout();
        }
    }
    
    private void updatePasswordToggleView() {
        if (this.mEditText != null) {
            if (this.shouldShowPasswordIcon()) {
                if (this.mPasswordToggleView == null) {
                    (this.mPasswordToggleView = (CheckableImageButton)LayoutInflater.from(this.getContext()).inflate(R$layout.design_text_input_password_icon, (ViewGroup)this.mInputFrame, false)).setImageDrawable(this.mPasswordToggleDrawable);
                    this.mPasswordToggleView.setContentDescription(this.mPasswordToggleContentDesc);
                    this.mInputFrame.addView((View)this.mPasswordToggleView);
                    this.mPasswordToggleView.setOnClickListener((View$OnClickListener)new TextInputLayout$4(this));
                }
                this.mPasswordToggleView.setVisibility(0);
                if (this.mPasswordToggleDummyDrawable == null) {
                    this.mPasswordToggleDummyDrawable = (Drawable)new ColorDrawable();
                }
                this.mPasswordToggleDummyDrawable.setBounds(0, 0, this.mPasswordToggleView.getMeasuredWidth(), 1);
                final Drawable[] compoundDrawablesRelative = TextViewCompat.getCompoundDrawablesRelative((TextView)this.mEditText);
                if (compoundDrawablesRelative[2] != this.mPasswordToggleDummyDrawable) {
                    this.mOriginalEditTextEndDrawable = compoundDrawablesRelative[2];
                }
                TextViewCompat.setCompoundDrawablesRelative((TextView)this.mEditText, compoundDrawablesRelative[0], compoundDrawablesRelative[1], this.mPasswordToggleDummyDrawable, compoundDrawablesRelative[3]);
                this.mPasswordToggleView.setPadding(this.mEditText.getPaddingLeft(), this.mEditText.getPaddingTop(), this.mEditText.getPaddingRight(), this.mEditText.getPaddingBottom());
                return;
            }
            if (this.mPasswordToggleView != null && this.mPasswordToggleView.getVisibility() == 0) {
                this.mPasswordToggleView.setVisibility(8);
            }
            final Drawable[] compoundDrawablesRelative2 = TextViewCompat.getCompoundDrawablesRelative((TextView)this.mEditText);
            if (compoundDrawablesRelative2[2] == this.mPasswordToggleDummyDrawable) {
                TextViewCompat.setCompoundDrawablesRelative((TextView)this.mEditText, compoundDrawablesRelative2[0], compoundDrawablesRelative2[1], this.mOriginalEditTextEndDrawable, compoundDrawablesRelative2[3]);
            }
        }
    }
    
    public void addView(final View view, final int n, final ViewGroup$LayoutParams layoutParams) {
        if (view instanceof EditText) {
            this.mInputFrame.addView(view, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(layoutParams));
            this.mInputFrame.setLayoutParams(layoutParams);
            this.updateInputLayoutMargins();
            this.setEditText((EditText)view);
            return;
        }
        super.addView(view, n, layoutParams);
    }
    
    public void draw(final Canvas canvas) {
        super.draw(canvas);
        if (this.mHintEnabled) {
            this.mCollapsingTextHelper.draw(canvas);
        }
    }
    
    protected void drawableStateChanged() {
        boolean b = true;
        if (this.mInDrawableStateChanged) {
            return;
        }
        this.mInDrawableStateChanged = true;
        super.drawableStateChanged();
        final int[] drawableState = this.getDrawableState();
        if (!ViewCompat.isLaidOut((View)this) || !this.isEnabled()) {
            b = false;
        }
        this.updateLabelState(b);
        this.updateEditTextBackground();
        if (this.mCollapsingTextHelper != null && (this.mCollapsingTextHelper.setState(drawableState) | false)) {
            this.invalidate();
        }
        this.mInDrawableStateChanged = false;
    }
    
    public int getCounterMaxLength() {
        return this.mCounterMaxLength;
    }
    
    public EditText getEditText() {
        return this.mEditText;
    }
    
    public CharSequence getError() {
        if (this.mErrorEnabled) {
            return this.mError;
        }
        return null;
    }
    
    public CharSequence getHint() {
        if (this.mHintEnabled) {
            return this.mHint;
        }
        return null;
    }
    
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.mPasswordToggleContentDesc;
    }
    
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.mPasswordToggleDrawable;
    }
    
    public Typeface getTypeface() {
        return this.mCollapsingTextHelper.getCollapsedTypeface();
    }
    
    public boolean isCounterEnabled() {
        return this.mCounterEnabled;
    }
    
    public boolean isErrorEnabled() {
        return this.mErrorEnabled;
    }
    
    public boolean isHintAnimationEnabled() {
        return this.mHintAnimationEnabled;
    }
    
    public boolean isHintEnabled() {
        return this.mHintEnabled;
    }
    
    final boolean isHintExpanded() {
        return this.mHintExpanded;
    }
    
    public boolean isPasswordVisibilityToggleEnabled() {
        return this.mPasswordToggleEnabled;
    }
    
    protected void onLayout(final boolean b, int n, final int n2, int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        if (this.mHintEnabled && this.mEditText != null) {
            final Rect mTmpRect = this.mTmpRect;
            ViewGroupUtils.getDescendantRect((ViewGroup)this, (View)this.mEditText, mTmpRect);
            n = mTmpRect.left + this.mEditText.getCompoundPaddingLeft();
            n3 = mTmpRect.right - this.mEditText.getCompoundPaddingRight();
            this.mCollapsingTextHelper.setExpandedBounds(n, mTmpRect.top + this.mEditText.getCompoundPaddingTop(), n3, mTmpRect.bottom - this.mEditText.getCompoundPaddingBottom());
            this.mCollapsingTextHelper.setCollapsedBounds(n, this.getPaddingTop(), n3, n4 - n2 - this.getPaddingBottom());
            this.mCollapsingTextHelper.recalculate();
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        this.updatePasswordToggleView();
        super.onMeasure(n, n2);
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        if (!(parcelable instanceof TextInputLayout$SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        final TextInputLayout$SavedState textInputLayout$SavedState = (TextInputLayout$SavedState)parcelable;
        super.onRestoreInstanceState(textInputLayout$SavedState.getSuperState());
        this.setError(textInputLayout$SavedState.error);
        this.requestLayout();
    }
    
    public Parcelable onSaveInstanceState() {
        final TextInputLayout$SavedState textInputLayout$SavedState = new TextInputLayout$SavedState(super.onSaveInstanceState());
        if (this.mErrorShown) {
            textInputLayout$SavedState.error = this.getError();
        }
        return (Parcelable)textInputLayout$SavedState;
    }
    
    void passwordVisibilityToggleRequested() {
        if (this.mPasswordToggleEnabled) {
            final int selectionEnd = this.mEditText.getSelectionEnd();
            if (this.hasPasswordTransformation()) {
                this.mEditText.setTransformationMethod((TransformationMethod)null);
                this.mPasswordToggledVisible = true;
            }
            else {
                this.mEditText.setTransformationMethod((TransformationMethod)PasswordTransformationMethod.getInstance());
                this.mPasswordToggledVisible = false;
            }
            this.mPasswordToggleView.setChecked(this.mPasswordToggledVisible);
            this.mEditText.setSelection(selectionEnd);
        }
    }
    
    public void setCounterEnabled(final boolean mCounterEnabled) {
        if (this.mCounterEnabled == mCounterEnabled) {
            return;
        }
    Label_0071:
        while (true) {
            if (!mCounterEnabled) {
                this.removeIndicator(this.mCounterView);
                this.mCounterView = null;
                break Label_0071;
            }
            while (true) {
                (this.mCounterView = new TextView(this.getContext())).setMaxLines(1);
                while (true) {
                    try {
                        this.mCounterView.setTextAppearance(this.getContext(), this.mCounterTextAppearance);
                        this.addIndicator(this.mCounterView, -1);
                        if (this.mEditText == null) {
                            this.updateCounter(0);
                            this.mCounterEnabled = mCounterEnabled;
                            return;
                        }
                    }
                    catch (Exception ex) {
                        this.mCounterView.setTextAppearance(this.getContext(), android.support.v7.appcompat.R$style.TextAppearance_AppCompat_Caption);
                        this.mCounterView.setTextColor(ContextCompat.getColor(this.getContext(), R$color.design_textinput_error_color_light));
                        continue;
                    }
                    break;
                }
                this.updateCounter(this.mEditText.getText().length());
                continue Label_0071;
            }
            break;
        }
    }
    
    public void setCounterMaxLength(int length) {
        if (this.mCounterMaxLength != length) {
            if (length > 0) {
                this.mCounterMaxLength = length;
            }
            else {
                this.mCounterMaxLength = -1;
            }
            if (this.mCounterEnabled) {
                if (this.mEditText == null) {
                    length = 0;
                }
                else {
                    length = this.mEditText.getText().length();
                }
                this.updateCounter(length);
            }
        }
    }
    
    public void setEnabled(final boolean enabled) {
        recursiveSetEnabled((ViewGroup)this, enabled);
        super.setEnabled(enabled);
    }
    
    public void setError(final CharSequence charSequence) {
        this.setError(charSequence, ViewCompat.isLaidOut((View)this) && this.isEnabled() && (this.mErrorView == null || !TextUtils.equals(this.mErrorView.getText(), charSequence)));
    }
    
    public void setErrorEnabled(final boolean mErrorEnabled) {
        if (this.mErrorEnabled == mErrorEnabled) {
            return;
        }
        if (this.mErrorView != null) {
            ViewCompat.animate((View)this.mErrorView).cancel();
        }
        while (true) {
            Label_0159: {
                if (!mErrorEnabled) {
                    break Label_0159;
                }
                while (true) {
                    this.mErrorView = new TextView(this.getContext());
                    while (true) {
                        Label_0184: {
                            try {
                                this.mErrorView.setTextAppearance(this.getContext(), this.mErrorTextAppearance);
                                if (Build$VERSION.SDK_INT >= 23 && this.mErrorView.getTextColors().getDefaultColor() == -65281) {
                                    final int n = 1;
                                    if (n != 0) {
                                        this.mErrorView.setTextAppearance(this.getContext(), android.support.v7.appcompat.R$style.TextAppearance_AppCompat_Caption);
                                        this.mErrorView.setTextColor(ContextCompat.getColor(this.getContext(), R$color.design_textinput_error_color_light));
                                    }
                                    this.mErrorView.setVisibility(4);
                                    ViewCompat.setAccessibilityLiveRegion((View)this.mErrorView, 1);
                                    this.addIndicator(this.mErrorView, 0);
                                    this.mErrorEnabled = mErrorEnabled;
                                    return;
                                }
                                break Label_0184;
                            }
                            catch (Exception ex) {
                                final int n = 1;
                                continue;
                            }
                            break;
                        }
                        final int n = 0;
                        continue;
                    }
                }
            }
            this.mErrorShown = false;
            this.updateEditTextBackground();
            this.removeIndicator(this.mErrorView);
            this.mErrorView = null;
            continue;
        }
    }
    
    public void setHint(final CharSequence hintInternal) {
        if (this.mHintEnabled) {
            this.setHintInternal(hintInternal);
            this.sendAccessibilityEvent(2048);
        }
    }
    
    public void setHintAnimationEnabled(final boolean mHintAnimationEnabled) {
        this.mHintAnimationEnabled = mHintAnimationEnabled;
    }
    
    public void setHintEnabled(final boolean mHintEnabled) {
        if (mHintEnabled != this.mHintEnabled) {
            this.mHintEnabled = mHintEnabled;
            final CharSequence hint = this.mEditText.getHint();
            if (!this.mHintEnabled) {
                if (!TextUtils.isEmpty(this.mHint) && TextUtils.isEmpty(hint)) {
                    this.mEditText.setHint(this.mHint);
                }
                this.setHintInternal(null);
            }
            else if (!TextUtils.isEmpty(hint)) {
                if (TextUtils.isEmpty(this.mHint)) {
                    this.setHint(hint);
                }
                this.mEditText.setHint((CharSequence)null);
            }
            if (this.mEditText != null) {
                this.updateInputLayoutMargins();
            }
        }
    }
    
    public void setHintTextAppearance(final int collapsedTextAppearance) {
        this.mCollapsingTextHelper.setCollapsedTextAppearance(collapsedTextAppearance);
        this.mFocusedTextColor = this.mCollapsingTextHelper.getCollapsedTextColor();
        if (this.mEditText != null) {
            this.updateLabelState(false);
            this.updateInputLayoutMargins();
        }
    }
    
    public void setPasswordVisibilityToggleContentDescription(final int n) {
        CharSequence text;
        if (n != 0) {
            text = this.getResources().getText(n);
        }
        else {
            text = null;
        }
        this.setPasswordVisibilityToggleContentDescription(text);
    }
    
    public void setPasswordVisibilityToggleContentDescription(final CharSequence charSequence) {
        this.mPasswordToggleContentDesc = charSequence;
        if (this.mPasswordToggleView != null) {
            this.mPasswordToggleView.setContentDescription(charSequence);
        }
    }
    
    public void setPasswordVisibilityToggleDrawable(final int n) {
        Drawable drawable;
        if (n != 0) {
            drawable = AppCompatResources.getDrawable(this.getContext(), n);
        }
        else {
            drawable = null;
        }
        this.setPasswordVisibilityToggleDrawable(drawable);
    }
    
    public void setPasswordVisibilityToggleDrawable(final Drawable drawable) {
        this.mPasswordToggleDrawable = drawable;
        if (this.mPasswordToggleView != null) {
            this.mPasswordToggleView.setImageDrawable(drawable);
        }
    }
    
    public void setPasswordVisibilityToggleEnabled(final boolean mPasswordToggleEnabled) {
        if (this.mPasswordToggleEnabled != mPasswordToggleEnabled) {
            this.mPasswordToggleEnabled = mPasswordToggleEnabled;
            if (!mPasswordToggleEnabled && this.mPasswordToggledVisible && this.mEditText != null) {
                this.mEditText.setTransformationMethod((TransformationMethod)PasswordTransformationMethod.getInstance());
            }
            this.mPasswordToggledVisible = false;
            this.updatePasswordToggleView();
        }
    }
    
    public void setPasswordVisibilityToggleTintList(final ColorStateList mPasswordToggleTintList) {
        this.mPasswordToggleTintList = mPasswordToggleTintList;
        this.mHasPasswordToggleTintList = true;
        this.applyPasswordToggleTint();
    }
    
    public void setPasswordVisibilityToggleTintMode(final PorterDuff$Mode mPasswordToggleTintMode) {
        this.mPasswordToggleTintMode = mPasswordToggleTintMode;
        this.mHasPasswordToggleTintMode = true;
        this.applyPasswordToggleTint();
    }
    
    public void setTypeface(final Typeface typefaces) {
        this.mCollapsingTextHelper.setTypefaces(typefaces);
    }
    
    void updateCounter(final int n) {
        final boolean mCounterOverflowed = this.mCounterOverflowed;
        if (this.mCounterMaxLength == -1) {
            this.mCounterView.setText((CharSequence)String.valueOf(n));
            this.mCounterOverflowed = false;
        }
        else {
            this.mCounterOverflowed = (n > this.mCounterMaxLength);
            if (mCounterOverflowed != this.mCounterOverflowed) {
                final TextView mCounterView = this.mCounterView;
                final Context context = this.getContext();
                int n2;
                if (this.mCounterOverflowed) {
                    n2 = this.mCounterOverflowTextAppearance;
                }
                else {
                    n2 = this.mCounterTextAppearance;
                }
                mCounterView.setTextAppearance(context, n2);
            }
            this.mCounterView.setText((CharSequence)this.getContext().getString(R$string.character_counter_pattern, new Object[] { n, this.mCounterMaxLength }));
        }
        if (this.mEditText != null && mCounterOverflowed != this.mCounterOverflowed) {
            this.updateLabelState(false);
            this.updateEditTextBackground();
        }
    }
    
    void updateLabelState(final boolean b) {
        boolean b2 = true;
        final boolean enabled = this.isEnabled();
        boolean b3;
        if (this.mEditText != null && !TextUtils.isEmpty((CharSequence)this.mEditText.getText())) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        final boolean arrayContains = arrayContains(this.getDrawableState(), 16842908);
        if (TextUtils.isEmpty(this.getError())) {
            b2 = false;
        }
        if (this.mDefaultTextColor != null) {
            this.mCollapsingTextHelper.setExpandedTextColor(this.mDefaultTextColor);
        }
        if (enabled && this.mCounterOverflowed && this.mCounterView != null) {
            this.mCollapsingTextHelper.setCollapsedTextColor(this.mCounterView.getTextColors());
        }
        else if (enabled && arrayContains && this.mFocusedTextColor != null) {
            this.mCollapsingTextHelper.setCollapsedTextColor(this.mFocusedTextColor);
        }
        else if (this.mDefaultTextColor != null) {
            this.mCollapsingTextHelper.setCollapsedTextColor(this.mDefaultTextColor);
        }
        if (b3 || (this.isEnabled() && (arrayContains || b2))) {
            this.collapseHint(b);
            return;
        }
        this.expandHint(b);
    }
}
