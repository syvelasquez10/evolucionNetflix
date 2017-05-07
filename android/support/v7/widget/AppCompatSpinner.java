// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.widget.ListAdapter;
import android.widget.Adapter;
import android.view.MotionEvent;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.View$MeasureSpec;
import android.graphics.drawable.Drawable;
import android.content.res.Resources$Theme;
import android.support.v7.appcompat.R$attr;
import android.util.AttributeSet;
import android.os.Build$VERSION;
import android.support.v7.internal.widget.TintManager;
import android.graphics.Rect;
import android.widget.SpinnerAdapter;
import android.content.Context;
import android.support.v4.view.TintableBackgroundView;
import android.widget.Spinner;

public class AppCompatSpinner extends Spinner implements TintableBackgroundView
{
    private static final int[] ATTRS_ANDROID_SPINNERMODE;
    private static final boolean IS_AT_LEAST_JB;
    private static final boolean IS_AT_LEAST_M;
    private AppCompatBackgroundHelper mBackgroundTintHelper;
    private int mDropDownWidth;
    private ListPopupWindow$ForwardingListener mForwardingListener;
    private AppCompatSpinner$DropdownPopup mPopup;
    private Context mPopupContext;
    private boolean mPopupSet;
    private SpinnerAdapter mTempAdapter;
    private final Rect mTempRect;
    private TintManager mTintManager;
    
    static {
        IS_AT_LEAST_M = (Build$VERSION.SDK_INT >= 23);
        IS_AT_LEAST_JB = (Build$VERSION.SDK_INT >= 16);
        ATTRS_ANDROID_SPINNERMODE = new int[] { 16843505 };
    }
    
    public AppCompatSpinner(final Context context, final AttributeSet set) {
        this(context, set, R$attr.spinnerStyle);
    }
    
    public AppCompatSpinner(final Context context, final AttributeSet set, final int n) {
        this(context, set, n, -1);
    }
    
    public AppCompatSpinner(final Context context, final AttributeSet set, final int n, final int n2) {
        this(context, set, n, n2, null);
    }
    
    public AppCompatSpinner(final Context p0, final AttributeSet p1, final int p2, final int p3, final Resources$Theme p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: aload_2        
        //     3: iload_3        
        //     4: invokespecial   android/widget/Spinner.<init>:(Landroid/content/Context;Landroid/util/AttributeSet;I)V
        //     7: aload_0        
        //     8: new             Landroid/graphics/Rect;
        //    11: dup            
        //    12: invokespecial   android/graphics/Rect.<init>:()V
        //    15: putfield        android/support/v7/widget/AppCompatSpinner.mTempRect:Landroid/graphics/Rect;
        //    18: aload_1        
        //    19: aload_2        
        //    20: getstatic       android/support/v7/appcompat/R$styleable.Spinner:[I
        //    23: iload_3        
        //    24: iconst_0       
        //    25: invokestatic    android/support/v7/internal/widget/TintTypedArray.obtainStyledAttributes:(Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroid/support/v7/internal/widget/TintTypedArray;
        //    28: astore          9
        //    30: aload_0        
        //    31: aload           9
        //    33: invokevirtual   android/support/v7/internal/widget/TintTypedArray.getTintManager:()Landroid/support/v7/internal/widget/TintManager;
        //    36: putfield        android/support/v7/widget/AppCompatSpinner.mTintManager:Landroid/support/v7/internal/widget/TintManager;
        //    39: aload_0        
        //    40: new             Landroid/support/v7/widget/AppCompatBackgroundHelper;
        //    43: dup            
        //    44: aload_0        
        //    45: aload_0        
        //    46: getfield        android/support/v7/widget/AppCompatSpinner.mTintManager:Landroid/support/v7/internal/widget/TintManager;
        //    49: invokespecial   android/support/v7/widget/AppCompatBackgroundHelper.<init>:(Landroid/view/View;Landroid/support/v7/internal/widget/TintManager;)V
        //    52: putfield        android/support/v7/widget/AppCompatSpinner.mBackgroundTintHelper:Landroid/support/v7/widget/AppCompatBackgroundHelper;
        //    55: aload           5
        //    57: ifnull          295
        //    60: aload_0        
        //    61: new             Landroid/support/v7/internal/view/ContextThemeWrapper;
        //    64: dup            
        //    65: aload_1        
        //    66: aload           5
        //    68: invokespecial   android/support/v7/internal/view/ContextThemeWrapper.<init>:(Landroid/content/Context;Landroid/content/res/Resources$Theme;)V
        //    71: putfield        android/support/v7/widget/AppCompatSpinner.mPopupContext:Landroid/content/Context;
        //    74: aload_0        
        //    75: getfield        android/support/v7/widget/AppCompatSpinner.mPopupContext:Landroid/content/Context;
        //    78: ifnull          255
        //    81: iload           4
        //    83: istore          7
        //    85: iload           4
        //    87: iconst_m1      
        //    88: if_icmpne       157
        //    91: getstatic       android/os/Build$VERSION.SDK_INT:I
        //    94: bipush          11
        //    96: if_icmplt       404
        //    99: aload_1        
        //   100: aload_2        
        //   101: getstatic       android/support/v7/widget/AppCompatSpinner.ATTRS_ANDROID_SPINNERMODE:[I
        //   104: iload_3        
        //   105: iconst_0       
        //   106: invokevirtual   android/content/Context.obtainStyledAttributes:(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;
        //   109: astore          5
        //   111: iload           4
        //   113: istore          6
        //   115: aload           5
        //   117: astore_1       
        //   118: aload           5
        //   120: iconst_0       
        //   121: invokevirtual   android/content/res/TypedArray.hasValue:(I)Z
        //   124: ifeq            139
        //   127: aload           5
        //   129: astore_1       
        //   130: aload           5
        //   132: iconst_0       
        //   133: iconst_0       
        //   134: invokevirtual   android/content/res/TypedArray.getInt:(II)I
        //   137: istore          6
        //   139: iload           6
        //   141: istore          7
        //   143: aload           5
        //   145: ifnull          157
        //   148: aload           5
        //   150: invokevirtual   android/content/res/TypedArray.recycle:()V
        //   153: iload           6
        //   155: istore          7
        //   157: iload           7
        //   159: iconst_1       
        //   160: if_icmpne       255
        //   163: new             Landroid/support/v7/widget/AppCompatSpinner$DropdownPopup;
        //   166: dup            
        //   167: aload_0        
        //   168: aload_0        
        //   169: getfield        android/support/v7/widget/AppCompatSpinner.mPopupContext:Landroid/content/Context;
        //   172: aload_2        
        //   173: iload_3        
        //   174: invokespecial   android/support/v7/widget/AppCompatSpinner$DropdownPopup.<init>:(Landroid/support/v7/widget/AppCompatSpinner;Landroid/content/Context;Landroid/util/AttributeSet;I)V
        //   177: astore_1       
        //   178: aload_0        
        //   179: getfield        android/support/v7/widget/AppCompatSpinner.mPopupContext:Landroid/content/Context;
        //   182: aload_2        
        //   183: getstatic       android/support/v7/appcompat/R$styleable.Spinner:[I
        //   186: iload_3        
        //   187: iconst_0       
        //   188: invokestatic    android/support/v7/internal/widget/TintTypedArray.obtainStyledAttributes:(Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroid/support/v7/internal/widget/TintTypedArray;
        //   191: astore          5
        //   193: aload_0        
        //   194: aload           5
        //   196: getstatic       android/support/v7/appcompat/R$styleable.Spinner_android_dropDownWidth:I
        //   199: bipush          -2
        //   201: invokevirtual   android/support/v7/internal/widget/TintTypedArray.getLayoutDimension:(II)I
        //   204: putfield        android/support/v7/widget/AppCompatSpinner.mDropDownWidth:I
        //   207: aload_1        
        //   208: aload           5
        //   210: getstatic       android/support/v7/appcompat/R$styleable.Spinner_android_popupBackground:I
        //   213: invokevirtual   android/support/v7/internal/widget/TintTypedArray.getDrawable:(I)Landroid/graphics/drawable/Drawable;
        //   216: invokevirtual   android/support/v7/widget/AppCompatSpinner$DropdownPopup.setBackgroundDrawable:(Landroid/graphics/drawable/Drawable;)V
        //   219: aload_1        
        //   220: aload           9
        //   222: getstatic       android/support/v7/appcompat/R$styleable.Spinner_android_prompt:I
        //   225: invokevirtual   android/support/v7/internal/widget/TintTypedArray.getString:(I)Ljava/lang/String;
        //   228: invokevirtual   android/support/v7/widget/AppCompatSpinner$DropdownPopup.setPromptText:(Ljava/lang/CharSequence;)V
        //   231: aload           5
        //   233: invokevirtual   android/support/v7/internal/widget/TintTypedArray.recycle:()V
        //   236: aload_0        
        //   237: aload_1        
        //   238: putfield        android/support/v7/widget/AppCompatSpinner.mPopup:Landroid/support/v7/widget/AppCompatSpinner$DropdownPopup;
        //   241: aload_0        
        //   242: new             Landroid/support/v7/widget/AppCompatSpinner$1;
        //   245: dup            
        //   246: aload_0        
        //   247: aload_0        
        //   248: aload_1        
        //   249: invokespecial   android/support/v7/widget/AppCompatSpinner$1.<init>:(Landroid/support/v7/widget/AppCompatSpinner;Landroid/view/View;Landroid/support/v7/widget/AppCompatSpinner$DropdownPopup;)V
        //   252: putfield        android/support/v7/widget/AppCompatSpinner.mForwardingListener:Landroid/support/v7/widget/ListPopupWindow$ForwardingListener;
        //   255: aload           9
        //   257: invokevirtual   android/support/v7/internal/widget/TintTypedArray.recycle:()V
        //   260: aload_0        
        //   261: iconst_1       
        //   262: putfield        android/support/v7/widget/AppCompatSpinner.mPopupSet:Z
        //   265: aload_0        
        //   266: getfield        android/support/v7/widget/AppCompatSpinner.mTempAdapter:Landroid/widget/SpinnerAdapter;
        //   269: ifnull          285
        //   272: aload_0        
        //   273: aload_0        
        //   274: getfield        android/support/v7/widget/AppCompatSpinner.mTempAdapter:Landroid/widget/SpinnerAdapter;
        //   277: invokevirtual   android/support/v7/widget/AppCompatSpinner.setAdapter:(Landroid/widget/SpinnerAdapter;)V
        //   280: aload_0        
        //   281: aconst_null    
        //   282: putfield        android/support/v7/widget/AppCompatSpinner.mTempAdapter:Landroid/widget/SpinnerAdapter;
        //   285: aload_0        
        //   286: getfield        android/support/v7/widget/AppCompatSpinner.mBackgroundTintHelper:Landroid/support/v7/widget/AppCompatBackgroundHelper;
        //   289: aload_2        
        //   290: iload_3        
        //   291: invokevirtual   android/support/v7/widget/AppCompatBackgroundHelper.loadFromAttributes:(Landroid/util/AttributeSet;I)V
        //   294: return         
        //   295: aload           9
        //   297: getstatic       android/support/v7/appcompat/R$styleable.Spinner_popupTheme:I
        //   300: iconst_0       
        //   301: invokevirtual   android/support/v7/internal/widget/TintTypedArray.getResourceId:(II)I
        //   304: istore          6
        //   306: iload           6
        //   308: ifeq            328
        //   311: aload_0        
        //   312: new             Landroid/support/v7/internal/view/ContextThemeWrapper;
        //   315: dup            
        //   316: aload_1        
        //   317: iload           6
        //   319: invokespecial   android/support/v7/internal/view/ContextThemeWrapper.<init>:(Landroid/content/Context;I)V
        //   322: putfield        android/support/v7/widget/AppCompatSpinner.mPopupContext:Landroid/content/Context;
        //   325: goto            74
        //   328: getstatic       android/support/v7/widget/AppCompatSpinner.IS_AT_LEAST_M:Z
        //   331: ifne            346
        //   334: aload_1        
        //   335: astore          5
        //   337: aload_0        
        //   338: aload           5
        //   340: putfield        android/support/v7/widget/AppCompatSpinner.mPopupContext:Landroid/content/Context;
        //   343: goto            74
        //   346: aconst_null    
        //   347: astore          5
        //   349: goto            337
        //   352: astore          8
        //   354: aconst_null    
        //   355: astore          5
        //   357: aload           5
        //   359: astore_1       
        //   360: ldc             "AppCompatSpinner"
        //   362: ldc             "Could not read android:spinnerMode"
        //   364: aload           8
        //   366: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   369: pop            
        //   370: iload           4
        //   372: istore          7
        //   374: aload           5
        //   376: ifnull          157
        //   379: aload           5
        //   381: invokevirtual   android/content/res/TypedArray.recycle:()V
        //   384: iload           4
        //   386: istore          7
        //   388: goto            157
        //   391: astore_2       
        //   392: aconst_null    
        //   393: astore_1       
        //   394: aload_1        
        //   395: ifnull          402
        //   398: aload_1        
        //   399: invokevirtual   android/content/res/TypedArray.recycle:()V
        //   402: aload_2        
        //   403: athrow         
        //   404: iconst_1       
        //   405: istore          7
        //   407: goto            157
        //   410: astore_2       
        //   411: goto            394
        //   414: astore          8
        //   416: goto            357
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  99     111    352    357    Ljava/lang/Exception;
        //  99     111    391    394    Any
        //  118    127    414    419    Ljava/lang/Exception;
        //  118    127    410    414    Any
        //  130    139    414    419    Ljava/lang/Exception;
        //  130    139    410    414    Any
        //  360    370    410    414    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0139:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private int compatMeasureContentWidth(final SpinnerAdapter spinnerAdapter, final Drawable drawable) {
        if (spinnerAdapter == null) {
            return 0;
        }
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 0);
        final int measureSpec2 = View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 0);
        final int max = Math.max(0, this.getSelectedItemPosition());
        final int min = Math.min(spinnerAdapter.getCount(), max + 15);
        int i = Math.max(0, max - (15 - (min - max)));
        View view = null;
        int max2 = 0;
        int n = 0;
        while (i < min) {
            final int itemViewType = spinnerAdapter.getItemViewType(i);
            if (itemViewType != n) {
                view = null;
                n = itemViewType;
            }
            view = spinnerAdapter.getView(i, view, (ViewGroup)this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup$LayoutParams(-2, -2));
            }
            view.measure(measureSpec, measureSpec2);
            max2 = Math.max(max2, view.getMeasuredWidth());
            ++i;
        }
        if (drawable != null) {
            drawable.getPadding(this.mTempRect);
            return this.mTempRect.left + this.mTempRect.right + max2;
        }
        return max2;
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.applySupportBackgroundTint();
        }
    }
    
    public int getDropDownHorizontalOffset() {
        if (this.mPopup != null) {
            return this.mPopup.getHorizontalOffset();
        }
        if (AppCompatSpinner.IS_AT_LEAST_JB) {
            return super.getDropDownHorizontalOffset();
        }
        return 0;
    }
    
    public int getDropDownVerticalOffset() {
        if (this.mPopup != null) {
            return this.mPopup.getVerticalOffset();
        }
        if (AppCompatSpinner.IS_AT_LEAST_JB) {
            return super.getDropDownVerticalOffset();
        }
        return 0;
    }
    
    public int getDropDownWidth() {
        if (this.mPopup != null) {
            return this.mDropDownWidth;
        }
        if (AppCompatSpinner.IS_AT_LEAST_JB) {
            return super.getDropDownWidth();
        }
        return 0;
    }
    
    public Drawable getPopupBackground() {
        if (this.mPopup != null) {
            return this.mPopup.getBackground();
        }
        if (AppCompatSpinner.IS_AT_LEAST_JB) {
            return super.getPopupBackground();
        }
        return null;
    }
    
    public Context getPopupContext() {
        if (this.mPopup != null) {
            return this.mPopupContext;
        }
        if (AppCompatSpinner.IS_AT_LEAST_M) {
            return super.getPopupContext();
        }
        return null;
    }
    
    public CharSequence getPrompt() {
        if (this.mPopup != null) {
            return this.mPopup.getHintText();
        }
        return super.getPrompt();
    }
    
    public ColorStateList getSupportBackgroundTintList() {
        if (this.mBackgroundTintHelper != null) {
            return this.mBackgroundTintHelper.getSupportBackgroundTintList();
        }
        return null;
    }
    
    public PorterDuff$Mode getSupportBackgroundTintMode() {
        if (this.mBackgroundTintHelper != null) {
            return this.mBackgroundTintHelper.getSupportBackgroundTintMode();
        }
        return null;
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mPopup != null && this.mPopup.isShowing()) {
            this.mPopup.dismiss();
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        if (this.mPopup != null && View$MeasureSpec.getMode(n) == Integer.MIN_VALUE) {
            this.setMeasuredDimension(Math.min(Math.max(this.getMeasuredWidth(), this.compatMeasureContentWidth(this.getAdapter(), this.getBackground())), View$MeasureSpec.getSize(n)), this.getMeasuredHeight());
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return (this.mForwardingListener != null && this.mForwardingListener.onTouch((View)this, motionEvent)) || super.onTouchEvent(motionEvent);
    }
    
    public boolean performClick() {
        if (this.mPopup != null && !this.mPopup.isShowing()) {
            this.mPopup.show();
            return true;
        }
        return super.performClick();
    }
    
    public void setAdapter(final SpinnerAdapter spinnerAdapter) {
        if (!this.mPopupSet) {
            this.mTempAdapter = spinnerAdapter;
        }
        else {
            super.setAdapter(spinnerAdapter);
            if (this.mPopup != null) {
                Context context;
                if (this.mPopupContext == null) {
                    context = this.getContext();
                }
                else {
                    context = this.mPopupContext;
                }
                this.mPopup.setAdapter((ListAdapter)new AppCompatSpinner$DropDownAdapter(spinnerAdapter, context.getTheme()));
            }
        }
    }
    
    public void setBackgroundDrawable(final Drawable backgroundDrawable) {
        super.setBackgroundDrawable(backgroundDrawable);
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.onSetBackgroundDrawable(backgroundDrawable);
        }
    }
    
    public void setBackgroundResource(final int backgroundResource) {
        super.setBackgroundResource(backgroundResource);
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.onSetBackgroundResource(backgroundResource);
        }
    }
    
    public void setDropDownHorizontalOffset(final int n) {
        if (this.mPopup != null) {
            this.mPopup.setHorizontalOffset(n);
        }
        else if (AppCompatSpinner.IS_AT_LEAST_JB) {
            super.setDropDownHorizontalOffset(n);
        }
    }
    
    public void setDropDownVerticalOffset(final int n) {
        if (this.mPopup != null) {
            this.mPopup.setVerticalOffset(n);
        }
        else if (AppCompatSpinner.IS_AT_LEAST_JB) {
            super.setDropDownVerticalOffset(n);
        }
    }
    
    public void setDropDownWidth(final int n) {
        if (this.mPopup != null) {
            this.mDropDownWidth = n;
        }
        else if (AppCompatSpinner.IS_AT_LEAST_JB) {
            super.setDropDownWidth(n);
        }
    }
    
    public void setPopupBackgroundDrawable(final Drawable drawable) {
        if (this.mPopup != null) {
            this.mPopup.setBackgroundDrawable(drawable);
        }
        else if (AppCompatSpinner.IS_AT_LEAST_JB) {
            super.setPopupBackgroundDrawable(drawable);
        }
    }
    
    public void setPopupBackgroundResource(final int n) {
        this.setPopupBackgroundDrawable(this.getPopupContext().getDrawable(n));
    }
    
    public void setPrompt(final CharSequence charSequence) {
        if (this.mPopup != null) {
            this.mPopup.setPromptText(charSequence);
            return;
        }
        super.setPrompt(charSequence);
    }
    
    public void setSupportBackgroundTintList(final ColorStateList supportBackgroundTintList) {
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.setSupportBackgroundTintList(supportBackgroundTintList);
        }
    }
    
    public void setSupportBackgroundTintMode(final PorterDuff$Mode supportBackgroundTintMode) {
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.setSupportBackgroundTintMode(supportBackgroundTintMode);
        }
    }
}
