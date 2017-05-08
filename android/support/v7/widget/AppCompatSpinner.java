// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v7.content.res.AppCompatResources;
import android.widget.ListAdapter;
import android.widget.Adapter;
import android.view.MotionEvent;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.os.Build$VERSION;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.View$MeasureSpec;
import android.graphics.drawable.Drawable;
import android.content.res.Resources$Theme;
import android.support.v7.appcompat.R$attr;
import android.util.AttributeSet;
import android.graphics.Rect;
import android.widget.SpinnerAdapter;
import android.content.Context;
import android.support.v4.view.TintableBackgroundView;
import android.widget.Spinner;

public class AppCompatSpinner extends Spinner implements TintableBackgroundView
{
    private static final int[] ATTRS_ANDROID_SPINNERMODE;
    private static final int MAX_ITEMS_MEASURED = 15;
    private static final int MODE_DIALOG = 0;
    private static final int MODE_DROPDOWN = 1;
    private static final int MODE_THEME = -1;
    private static final String TAG = "AppCompatSpinner";
    private AppCompatBackgroundHelper mBackgroundTintHelper;
    int mDropDownWidth;
    private ForwardingListener mForwardingListener;
    AppCompatSpinner$DropdownPopup mPopup;
    private Context mPopupContext;
    private boolean mPopupSet;
    private SpinnerAdapter mTempAdapter;
    final Rect mTempRect;
    
    static {
        ATTRS_ANDROID_SPINNERMODE = new int[] { 16843505 };
    }
    
    public AppCompatSpinner(final Context context) {
        this(context, null);
    }
    
    public AppCompatSpinner(final Context context, final int n) {
        this(context, null, R$attr.spinnerStyle, n);
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
        //    25: invokestatic    android/support/v7/widget/TintTypedArray.obtainStyledAttributes:(Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroid/support/v7/widget/TintTypedArray;
        //    28: astore          10
        //    30: aload_0        
        //    31: new             Landroid/support/v7/widget/AppCompatBackgroundHelper;
        //    34: dup            
        //    35: aload_0        
        //    36: invokespecial   android/support/v7/widget/AppCompatBackgroundHelper.<init>:(Landroid/view/View;)V
        //    39: putfield        android/support/v7/widget/AppCompatSpinner.mBackgroundTintHelper:Landroid/support/v7/widget/AppCompatBackgroundHelper;
        //    42: aload           5
        //    44: ifnull          329
        //    47: aload_0        
        //    48: new             Landroid/support/v7/view/ContextThemeWrapper;
        //    51: dup            
        //    52: aload_1        
        //    53: aload           5
        //    55: invokespecial   android/support/v7/view/ContextThemeWrapper.<init>:(Landroid/content/Context;Landroid/content/res/Resources$Theme;)V
        //    58: putfield        android/support/v7/widget/AppCompatSpinner.mPopupContext:Landroid/content/Context;
        //    61: aload_0        
        //    62: getfield        android/support/v7/widget/AppCompatSpinner.mPopupContext:Landroid/content/Context;
        //    65: ifnull          249
        //    68: iload           4
        //    70: istore          7
        //    72: iload           4
        //    74: iconst_m1      
        //    75: if_icmpne       146
        //    78: getstatic       android/os/Build$VERSION.SDK_INT:I
        //    81: bipush          11
        //    83: if_icmplt       444
        //    86: aload_1        
        //    87: aload_2        
        //    88: getstatic       android/support/v7/widget/AppCompatSpinner.ATTRS_ANDROID_SPINNERMODE:[I
        //    91: iload_3        
        //    92: iconst_0       
        //    93: invokevirtual   android/content/Context.obtainStyledAttributes:(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;
        //    96: astore          8
        //    98: iload           4
        //   100: istore          6
        //   102: aload           8
        //   104: astore          5
        //   106: aload           8
        //   108: iconst_0       
        //   109: invokevirtual   android/content/res/TypedArray.hasValue:(I)Z
        //   112: ifeq            128
        //   115: aload           8
        //   117: astore          5
        //   119: aload           8
        //   121: iconst_0       
        //   122: iconst_0       
        //   123: invokevirtual   android/content/res/TypedArray.getInt:(II)I
        //   126: istore          6
        //   128: iload           6
        //   130: istore          7
        //   132: aload           8
        //   134: ifnull          146
        //   137: aload           8
        //   139: invokevirtual   android/content/res/TypedArray.recycle:()V
        //   142: iload           6
        //   144: istore          7
        //   146: iload           7
        //   148: iconst_1       
        //   149: if_icmpne       249
        //   152: new             Landroid/support/v7/widget/AppCompatSpinner$DropdownPopup;
        //   155: dup            
        //   156: aload_0        
        //   157: aload_0        
        //   158: getfield        android/support/v7/widget/AppCompatSpinner.mPopupContext:Landroid/content/Context;
        //   161: aload_2        
        //   162: iload_3        
        //   163: invokespecial   android/support/v7/widget/AppCompatSpinner$DropdownPopup.<init>:(Landroid/support/v7/widget/AppCompatSpinner;Landroid/content/Context;Landroid/util/AttributeSet;I)V
        //   166: astore          5
        //   168: aload_0        
        //   169: getfield        android/support/v7/widget/AppCompatSpinner.mPopupContext:Landroid/content/Context;
        //   172: aload_2        
        //   173: getstatic       android/support/v7/appcompat/R$styleable.Spinner:[I
        //   176: iload_3        
        //   177: iconst_0       
        //   178: invokestatic    android/support/v7/widget/TintTypedArray.obtainStyledAttributes:(Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroid/support/v7/widget/TintTypedArray;
        //   181: astore          8
        //   183: aload_0        
        //   184: aload           8
        //   186: getstatic       android/support/v7/appcompat/R$styleable.Spinner_android_dropDownWidth:I
        //   189: bipush          -2
        //   191: invokevirtual   android/support/v7/widget/TintTypedArray.getLayoutDimension:(II)I
        //   194: putfield        android/support/v7/widget/AppCompatSpinner.mDropDownWidth:I
        //   197: aload           5
        //   199: aload           8
        //   201: getstatic       android/support/v7/appcompat/R$styleable.Spinner_android_popupBackground:I
        //   204: invokevirtual   android/support/v7/widget/TintTypedArray.getDrawable:(I)Landroid/graphics/drawable/Drawable;
        //   207: invokevirtual   android/support/v7/widget/AppCompatSpinner$DropdownPopup.setBackgroundDrawable:(Landroid/graphics/drawable/Drawable;)V
        //   210: aload           5
        //   212: aload           10
        //   214: getstatic       android/support/v7/appcompat/R$styleable.Spinner_android_prompt:I
        //   217: invokevirtual   android/support/v7/widget/TintTypedArray.getString:(I)Ljava/lang/String;
        //   220: invokevirtual   android/support/v7/widget/AppCompatSpinner$DropdownPopup.setPromptText:(Ljava/lang/CharSequence;)V
        //   223: aload           8
        //   225: invokevirtual   android/support/v7/widget/TintTypedArray.recycle:()V
        //   228: aload_0        
        //   229: aload           5
        //   231: putfield        android/support/v7/widget/AppCompatSpinner.mPopup:Landroid/support/v7/widget/AppCompatSpinner$DropdownPopup;
        //   234: aload_0        
        //   235: new             Landroid/support/v7/widget/AppCompatSpinner$1;
        //   238: dup            
        //   239: aload_0        
        //   240: aload_0        
        //   241: aload           5
        //   243: invokespecial   android/support/v7/widget/AppCompatSpinner$1.<init>:(Landroid/support/v7/widget/AppCompatSpinner;Landroid/view/View;Landroid/support/v7/widget/AppCompatSpinner$DropdownPopup;)V
        //   246: putfield        android/support/v7/widget/AppCompatSpinner.mForwardingListener:Landroid/support/v7/widget/ForwardingListener;
        //   249: aload           10
        //   251: getstatic       android/support/v7/appcompat/R$styleable.Spinner_android_entries:I
        //   254: invokevirtual   android/support/v7/widget/TintTypedArray.getTextArray:(I)[Ljava/lang/CharSequence;
        //   257: astore          5
        //   259: aload           5
        //   261: ifnull          289
        //   264: new             Landroid/widget/ArrayAdapter;
        //   267: dup            
        //   268: aload_1        
        //   269: ldc             17367048
        //   271: aload           5
        //   273: invokespecial   android/widget/ArrayAdapter.<init>:(Landroid/content/Context;I[Ljava/lang/Object;)V
        //   276: astore_1       
        //   277: aload_1        
        //   278: getstatic       android/support/v7/appcompat/R$layout.support_simple_spinner_dropdown_item:I
        //   281: invokevirtual   android/widget/ArrayAdapter.setDropDownViewResource:(I)V
        //   284: aload_0        
        //   285: aload_1        
        //   286: invokevirtual   android/support/v7/widget/AppCompatSpinner.setAdapter:(Landroid/widget/SpinnerAdapter;)V
        //   289: aload           10
        //   291: invokevirtual   android/support/v7/widget/TintTypedArray.recycle:()V
        //   294: aload_0        
        //   295: iconst_1       
        //   296: putfield        android/support/v7/widget/AppCompatSpinner.mPopupSet:Z
        //   299: aload_0        
        //   300: getfield        android/support/v7/widget/AppCompatSpinner.mTempAdapter:Landroid/widget/SpinnerAdapter;
        //   303: ifnull          319
        //   306: aload_0        
        //   307: aload_0        
        //   308: getfield        android/support/v7/widget/AppCompatSpinner.mTempAdapter:Landroid/widget/SpinnerAdapter;
        //   311: invokevirtual   android/support/v7/widget/AppCompatSpinner.setAdapter:(Landroid/widget/SpinnerAdapter;)V
        //   314: aload_0        
        //   315: aconst_null    
        //   316: putfield        android/support/v7/widget/AppCompatSpinner.mTempAdapter:Landroid/widget/SpinnerAdapter;
        //   319: aload_0        
        //   320: getfield        android/support/v7/widget/AppCompatSpinner.mBackgroundTintHelper:Landroid/support/v7/widget/AppCompatBackgroundHelper;
        //   323: aload_2        
        //   324: iload_3        
        //   325: invokevirtual   android/support/v7/widget/AppCompatBackgroundHelper.loadFromAttributes:(Landroid/util/AttributeSet;I)V
        //   328: return         
        //   329: aload           10
        //   331: getstatic       android/support/v7/appcompat/R$styleable.Spinner_popupTheme:I
        //   334: iconst_0       
        //   335: invokevirtual   android/support/v7/widget/TintTypedArray.getResourceId:(II)I
        //   338: istore          6
        //   340: iload           6
        //   342: ifeq            362
        //   345: aload_0        
        //   346: new             Landroid/support/v7/view/ContextThemeWrapper;
        //   349: dup            
        //   350: aload_1        
        //   351: iload           6
        //   353: invokespecial   android/support/v7/view/ContextThemeWrapper.<init>:(Landroid/content/Context;I)V
        //   356: putfield        android/support/v7/widget/AppCompatSpinner.mPopupContext:Landroid/content/Context;
        //   359: goto            61
        //   362: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   365: bipush          23
        //   367: if_icmpge       382
        //   370: aload_1        
        //   371: astore          5
        //   373: aload_0        
        //   374: aload           5
        //   376: putfield        android/support/v7/widget/AppCompatSpinner.mPopupContext:Landroid/content/Context;
        //   379: goto            61
        //   382: aconst_null    
        //   383: astore          5
        //   385: goto            373
        //   388: astore          9
        //   390: aconst_null    
        //   391: astore          8
        //   393: aload           8
        //   395: astore          5
        //   397: ldc             "AppCompatSpinner"
        //   399: ldc             "Could not read android:spinnerMode"
        //   401: aload           9
        //   403: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   406: pop            
        //   407: iload           4
        //   409: istore          7
        //   411: aload           8
        //   413: ifnull          146
        //   416: aload           8
        //   418: invokevirtual   android/content/res/TypedArray.recycle:()V
        //   421: iload           4
        //   423: istore          7
        //   425: goto            146
        //   428: astore_1       
        //   429: aconst_null    
        //   430: astore          5
        //   432: aload           5
        //   434: ifnull          442
        //   437: aload           5
        //   439: invokevirtual   android/content/res/TypedArray.recycle:()V
        //   442: aload_1        
        //   443: athrow         
        //   444: iconst_1       
        //   445: istore          7
        //   447: goto            146
        //   450: astore_1       
        //   451: goto            432
        //   454: astore          9
        //   456: goto            393
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  86     98     388    393    Ljava/lang/Exception;
        //  86     98     428    432    Any
        //  106    115    454    459    Ljava/lang/Exception;
        //  106    115    450    454    Any
        //  119    128    454    459    Ljava/lang/Exception;
        //  119    128    450    454    Any
        //  397    407    450    454    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0128:
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
    
    int compatMeasureContentWidth(final SpinnerAdapter spinnerAdapter, final Drawable drawable) {
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
        if (Build$VERSION.SDK_INT >= 16) {
            return super.getDropDownHorizontalOffset();
        }
        return 0;
    }
    
    public int getDropDownVerticalOffset() {
        if (this.mPopup != null) {
            return this.mPopup.getVerticalOffset();
        }
        if (Build$VERSION.SDK_INT >= 16) {
            return super.getDropDownVerticalOffset();
        }
        return 0;
    }
    
    public int getDropDownWidth() {
        if (this.mPopup != null) {
            return this.mDropDownWidth;
        }
        if (Build$VERSION.SDK_INT >= 16) {
            return super.getDropDownWidth();
        }
        return 0;
    }
    
    public Drawable getPopupBackground() {
        if (this.mPopup != null) {
            return this.mPopup.getBackground();
        }
        if (Build$VERSION.SDK_INT >= 16) {
            return super.getPopupBackground();
        }
        return null;
    }
    
    public Context getPopupContext() {
        if (this.mPopup != null) {
            return this.mPopupContext;
        }
        if (Build$VERSION.SDK_INT >= 23) {
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
        if (this.mPopup != null) {
            if (!this.mPopup.isShowing()) {
                this.mPopup.show();
            }
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
        else if (Build$VERSION.SDK_INT >= 16) {
            super.setDropDownHorizontalOffset(n);
        }
    }
    
    public void setDropDownVerticalOffset(final int n) {
        if (this.mPopup != null) {
            this.mPopup.setVerticalOffset(n);
        }
        else if (Build$VERSION.SDK_INT >= 16) {
            super.setDropDownVerticalOffset(n);
        }
    }
    
    public void setDropDownWidth(final int n) {
        if (this.mPopup != null) {
            this.mDropDownWidth = n;
        }
        else if (Build$VERSION.SDK_INT >= 16) {
            super.setDropDownWidth(n);
        }
    }
    
    public void setPopupBackgroundDrawable(final Drawable drawable) {
        if (this.mPopup != null) {
            this.mPopup.setBackgroundDrawable(drawable);
        }
        else if (Build$VERSION.SDK_INT >= 16) {
            super.setPopupBackgroundDrawable(drawable);
        }
    }
    
    public void setPopupBackgroundResource(final int n) {
        this.setPopupBackgroundDrawable(AppCompatResources.getDrawable(this.getPopupContext(), n));
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
