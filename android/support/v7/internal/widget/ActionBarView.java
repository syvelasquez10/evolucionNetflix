// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;
import android.widget.FrameLayout$LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.view.CollapsibleActionView;
import android.widget.LinearLayout$LayoutParams;
import android.support.v7.internal.view.menu.ActionMenuView;
import android.support.v7.internal.view.menu.ActionMenuPresenter;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.view.ViewParent;
import android.content.res.Configuration;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.support.v7.appcompat.R;
import android.view.LayoutInflater;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.util.AttributeSet;
import android.view.Window$Callback;
import android.widget.TextView;
import android.widget.SpinnerAdapter;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.ActionMenuItem;
import android.widget.LinearLayout;
import android.graphics.drawable.Drawable;
import android.view.View$OnClickListener;
import android.view.View;
import android.content.Context;
import android.support.v7.app.ActionBar;

public class ActionBarView extends AbsActionBarView
{
    private static final int DEFAULT_CUSTOM_GRAVITY = 19;
    public static final int DISPLAY_DEFAULT = 0;
    private static final int DISPLAY_RELAYOUT_MASK = 31;
    private static final String TAG = "ActionBarView";
    private ActionBar.OnNavigationListener mCallback;
    private Context mContext;
    private ActionBarContextView mContextView;
    private View mCustomNavView;
    private int mDisplayOptions;
    View mExpandedActionView;
    private final View$OnClickListener mExpandedActionViewUpListener;
    private HomeView mExpandedHomeLayout;
    private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
    private HomeView mHomeLayout;
    private Drawable mIcon;
    private boolean mIncludeTabs;
    private int mIndeterminateProgressStyle;
    private ProgressBarICS mIndeterminateProgressView;
    private boolean mIsCollapsable;
    private boolean mIsCollapsed;
    private int mItemPadding;
    private LinearLayout mListNavLayout;
    private Drawable mLogo;
    private ActionMenuItem mLogoNavItem;
    private final AdapterViewICS.OnItemSelectedListener mNavItemSelectedListener;
    private int mNavigationMode;
    private MenuBuilder mOptionsMenu;
    private int mProgressBarPadding;
    private int mProgressStyle;
    private ProgressBarICS mProgressView;
    private SpinnerICS mSpinner;
    private SpinnerAdapter mSpinnerAdapter;
    private CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    private ScrollingTabContainerView mTabScrollView;
    private Runnable mTabSelector;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private int mTitleStyleRes;
    private View mTitleUpView;
    private TextView mTitleView;
    private final View$OnClickListener mUpClickListener;
    private boolean mUserTitle;
    Window$Callback mWindowCallback;
    
    public ActionBarView(final Context p0, final AttributeSet p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: aload_2        
        //     3: invokespecial   android/support/v7/internal/widget/AbsActionBarView.<init>:(Landroid/content/Context;Landroid/util/AttributeSet;)V
        //     6: aload_0        
        //     7: iconst_m1      
        //     8: putfield        android/support/v7/internal/widget/ActionBarView.mDisplayOptions:I
        //    11: aload_0        
        //    12: new             Landroid/support/v7/internal/widget/ActionBarView$1;
        //    15: dup            
        //    16: aload_0        
        //    17: invokespecial   android/support/v7/internal/widget/ActionBarView$1.<init>:(Landroid/support/v7/internal/widget/ActionBarView;)V
        //    20: putfield        android/support/v7/internal/widget/ActionBarView.mNavItemSelectedListener:Landroid/support/v7/internal/widget/AdapterViewICS$OnItemSelectedListener;
        //    23: aload_0        
        //    24: new             Landroid/support/v7/internal/widget/ActionBarView$2;
        //    27: dup            
        //    28: aload_0        
        //    29: invokespecial   android/support/v7/internal/widget/ActionBarView$2.<init>:(Landroid/support/v7/internal/widget/ActionBarView;)V
        //    32: putfield        android/support/v7/internal/widget/ActionBarView.mExpandedActionViewUpListener:Landroid/view/View$OnClickListener;
        //    35: aload_0        
        //    36: new             Landroid/support/v7/internal/widget/ActionBarView$3;
        //    39: dup            
        //    40: aload_0        
        //    41: invokespecial   android/support/v7/internal/widget/ActionBarView$3.<init>:(Landroid/support/v7/internal/widget/ActionBarView;)V
        //    44: putfield        android/support/v7/internal/widget/ActionBarView.mUpClickListener:Landroid/view/View$OnClickListener;
        //    47: aload_0        
        //    48: aload_1        
        //    49: putfield        android/support/v7/internal/widget/ActionBarView.mContext:Landroid/content/Context;
        //    52: aload_0        
        //    53: iconst_0       
        //    54: invokevirtual   android/support/v7/internal/widget/ActionBarView.setBackgroundResource:(I)V
        //    57: aload_1        
        //    58: aload_2        
        //    59: getstatic       android/support/v7/appcompat/R$styleable.ActionBar:[I
        //    62: getstatic       android/support/v7/appcompat/R$attr.actionBarStyle:I
        //    65: iconst_0       
        //    66: invokevirtual   android/content/Context.obtainStyledAttributes:(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;
        //    69: astore_2       
        //    70: aload_1        
        //    71: invokevirtual   android/content/Context.getApplicationInfo:()Landroid/content/pm/ApplicationInfo;
        //    74: astore          4
        //    76: aload_1        
        //    77: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //    80: astore          5
        //    82: aload_0        
        //    83: aload_2        
        //    84: iconst_2       
        //    85: iconst_0       
        //    86: invokevirtual   android/content/res/TypedArray.getInt:(II)I
        //    89: putfield        android/support/v7/internal/widget/ActionBarView.mNavigationMode:I
        //    92: aload_0        
        //    93: aload_2        
        //    94: iconst_0       
        //    95: invokevirtual   android/content/res/TypedArray.getText:(I)Ljava/lang/CharSequence;
        //    98: putfield        android/support/v7/internal/widget/ActionBarView.mTitle:Ljava/lang/CharSequence;
        //   101: aload_0        
        //   102: aload_2        
        //   103: iconst_4       
        //   104: invokevirtual   android/content/res/TypedArray.getText:(I)Ljava/lang/CharSequence;
        //   107: putfield        android/support/v7/internal/widget/ActionBarView.mSubtitle:Ljava/lang/CharSequence;
        //   110: aload_0        
        //   111: aload_2        
        //   112: bipush          8
        //   114: invokevirtual   android/content/res/TypedArray.getDrawable:(I)Landroid/graphics/drawable/Drawable;
        //   117: putfield        android/support/v7/internal/widget/ActionBarView.mLogo:Landroid/graphics/drawable/Drawable;
        //   120: aload_0        
        //   121: getfield        android/support/v7/internal/widget/ActionBarView.mLogo:Landroid/graphics/drawable/Drawable;
        //   124: ifnonnull       176
        //   127: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   130: bipush          9
        //   132: if_icmplt       176
        //   135: aload_1        
        //   136: instanceof      Landroid/app/Activity;
        //   139: ifeq            158
        //   142: aload_0        
        //   143: aload           5
        //   145: aload_1        
        //   146: checkcast       Landroid/app/Activity;
        //   149: invokevirtual   android/app/Activity.getComponentName:()Landroid/content/ComponentName;
        //   152: invokevirtual   android/content/pm/PackageManager.getActivityLogo:(Landroid/content/ComponentName;)Landroid/graphics/drawable/Drawable;
        //   155: putfield        android/support/v7/internal/widget/ActionBarView.mLogo:Landroid/graphics/drawable/Drawable;
        //   158: aload_0        
        //   159: getfield        android/support/v7/internal/widget/ActionBarView.mLogo:Landroid/graphics/drawable/Drawable;
        //   162: ifnonnull       176
        //   165: aload_0        
        //   166: aload           4
        //   168: aload           5
        //   170: invokevirtual   android/content/pm/ApplicationInfo.loadLogo:(Landroid/content/pm/PackageManager;)Landroid/graphics/drawable/Drawable;
        //   173: putfield        android/support/v7/internal/widget/ActionBarView.mLogo:Landroid/graphics/drawable/Drawable;
        //   176: aload_0        
        //   177: aload_2        
        //   178: bipush          7
        //   180: invokevirtual   android/content/res/TypedArray.getDrawable:(I)Landroid/graphics/drawable/Drawable;
        //   183: putfield        android/support/v7/internal/widget/ActionBarView.mIcon:Landroid/graphics/drawable/Drawable;
        //   186: aload_0        
        //   187: getfield        android/support/v7/internal/widget/ActionBarView.mIcon:Landroid/graphics/drawable/Drawable;
        //   190: ifnonnull       234
        //   193: aload_1        
        //   194: instanceof      Landroid/app/Activity;
        //   197: ifeq            216
        //   200: aload_0        
        //   201: aload           5
        //   203: aload_1        
        //   204: checkcast       Landroid/app/Activity;
        //   207: invokevirtual   android/app/Activity.getComponentName:()Landroid/content/ComponentName;
        //   210: invokevirtual   android/content/pm/PackageManager.getActivityIcon:(Landroid/content/ComponentName;)Landroid/graphics/drawable/Drawable;
        //   213: putfield        android/support/v7/internal/widget/ActionBarView.mIcon:Landroid/graphics/drawable/Drawable;
        //   216: aload_0        
        //   217: getfield        android/support/v7/internal/widget/ActionBarView.mIcon:Landroid/graphics/drawable/Drawable;
        //   220: ifnonnull       234
        //   223: aload_0        
        //   224: aload           4
        //   226: aload           5
        //   228: invokevirtual   android/content/pm/ApplicationInfo.loadIcon:(Landroid/content/pm/PackageManager;)Landroid/graphics/drawable/Drawable;
        //   231: putfield        android/support/v7/internal/widget/ActionBarView.mIcon:Landroid/graphics/drawable/Drawable;
        //   234: aload_1        
        //   235: invokestatic    android/view/LayoutInflater.from:(Landroid/content/Context;)Landroid/view/LayoutInflater;
        //   238: astore          4
        //   240: aload_2        
        //   241: bipush          14
        //   243: getstatic       android/support/v7/appcompat/R$layout.abc_action_bar_home:I
        //   246: invokevirtual   android/content/res/TypedArray.getResourceId:(II)I
        //   249: istore_3       
        //   250: aload_0        
        //   251: aload           4
        //   253: iload_3        
        //   254: aload_0        
        //   255: iconst_0       
        //   256: invokevirtual   android/view/LayoutInflater.inflate:(ILandroid/view/ViewGroup;Z)Landroid/view/View;
        //   259: checkcast       Landroid/support/v7/internal/widget/ActionBarView$HomeView;
        //   262: putfield        android/support/v7/internal/widget/ActionBarView.mHomeLayout:Landroid/support/v7/internal/widget/ActionBarView$HomeView;
        //   265: aload_0        
        //   266: aload           4
        //   268: iload_3        
        //   269: aload_0        
        //   270: iconst_0       
        //   271: invokevirtual   android/view/LayoutInflater.inflate:(ILandroid/view/ViewGroup;Z)Landroid/view/View;
        //   274: checkcast       Landroid/support/v7/internal/widget/ActionBarView$HomeView;
        //   277: putfield        android/support/v7/internal/widget/ActionBarView.mExpandedHomeLayout:Landroid/support/v7/internal/widget/ActionBarView$HomeView;
        //   280: aload_0        
        //   281: getfield        android/support/v7/internal/widget/ActionBarView.mExpandedHomeLayout:Landroid/support/v7/internal/widget/ActionBarView$HomeView;
        //   284: iconst_1       
        //   285: invokevirtual   android/support/v7/internal/widget/ActionBarView$HomeView.setUp:(Z)V
        //   288: aload_0        
        //   289: getfield        android/support/v7/internal/widget/ActionBarView.mExpandedHomeLayout:Landroid/support/v7/internal/widget/ActionBarView$HomeView;
        //   292: aload_0        
        //   293: getfield        android/support/v7/internal/widget/ActionBarView.mExpandedActionViewUpListener:Landroid/view/View$OnClickListener;
        //   296: invokevirtual   android/support/v7/internal/widget/ActionBarView$HomeView.setOnClickListener:(Landroid/view/View$OnClickListener;)V
        //   299: aload_0        
        //   300: getfield        android/support/v7/internal/widget/ActionBarView.mExpandedHomeLayout:Landroid/support/v7/internal/widget/ActionBarView$HomeView;
        //   303: aload_0        
        //   304: invokevirtual   android/support/v7/internal/widget/ActionBarView.getResources:()Landroid/content/res/Resources;
        //   307: getstatic       android/support/v7/appcompat/R$string.abc_action_bar_up_description:I
        //   310: invokevirtual   android/content/res/Resources.getText:(I)Ljava/lang/CharSequence;
        //   313: invokevirtual   android/support/v7/internal/widget/ActionBarView$HomeView.setContentDescription:(Ljava/lang/CharSequence;)V
        //   316: aload_0        
        //   317: aload_2        
        //   318: iconst_5       
        //   319: iconst_0       
        //   320: invokevirtual   android/content/res/TypedArray.getResourceId:(II)I
        //   323: putfield        android/support/v7/internal/widget/ActionBarView.mTitleStyleRes:I
        //   326: aload_0        
        //   327: aload_2        
        //   328: bipush          6
        //   330: iconst_0       
        //   331: invokevirtual   android/content/res/TypedArray.getResourceId:(II)I
        //   334: putfield        android/support/v7/internal/widget/ActionBarView.mSubtitleStyleRes:I
        //   337: aload_0        
        //   338: aload_2        
        //   339: bipush          15
        //   341: iconst_0       
        //   342: invokevirtual   android/content/res/TypedArray.getResourceId:(II)I
        //   345: putfield        android/support/v7/internal/widget/ActionBarView.mProgressStyle:I
        //   348: aload_0        
        //   349: aload_2        
        //   350: bipush          16
        //   352: iconst_0       
        //   353: invokevirtual   android/content/res/TypedArray.getResourceId:(II)I
        //   356: putfield        android/support/v7/internal/widget/ActionBarView.mIndeterminateProgressStyle:I
        //   359: aload_0        
        //   360: aload_2        
        //   361: bipush          17
        //   363: iconst_0       
        //   364: invokevirtual   android/content/res/TypedArray.getDimensionPixelOffset:(II)I
        //   367: putfield        android/support/v7/internal/widget/ActionBarView.mProgressBarPadding:I
        //   370: aload_0        
        //   371: aload_2        
        //   372: bipush          18
        //   374: iconst_0       
        //   375: invokevirtual   android/content/res/TypedArray.getDimensionPixelOffset:(II)I
        //   378: putfield        android/support/v7/internal/widget/ActionBarView.mItemPadding:I
        //   381: aload_0        
        //   382: aload_2        
        //   383: iconst_3       
        //   384: iconst_0       
        //   385: invokevirtual   android/content/res/TypedArray.getInt:(II)I
        //   388: invokevirtual   android/support/v7/internal/widget/ActionBarView.setDisplayOptions:(I)V
        //   391: aload_2        
        //   392: bipush          13
        //   394: iconst_0       
        //   395: invokevirtual   android/content/res/TypedArray.getResourceId:(II)I
        //   398: istore_3       
        //   399: iload_3        
        //   400: ifeq            431
        //   403: aload_0        
        //   404: aload           4
        //   406: iload_3        
        //   407: aload_0        
        //   408: iconst_0       
        //   409: invokevirtual   android/view/LayoutInflater.inflate:(ILandroid/view/ViewGroup;Z)Landroid/view/View;
        //   412: putfield        android/support/v7/internal/widget/ActionBarView.mCustomNavView:Landroid/view/View;
        //   415: aload_0        
        //   416: iconst_0       
        //   417: putfield        android/support/v7/internal/widget/ActionBarView.mNavigationMode:I
        //   420: aload_0        
        //   421: aload_0        
        //   422: getfield        android/support/v7/internal/widget/ActionBarView.mDisplayOptions:I
        //   425: bipush          16
        //   427: ior            
        //   428: invokevirtual   android/support/v7/internal/widget/ActionBarView.setDisplayOptions:(I)V
        //   431: aload_0        
        //   432: aload_2        
        //   433: iconst_1       
        //   434: iconst_0       
        //   435: invokevirtual   android/content/res/TypedArray.getLayoutDimension:(II)I
        //   438: putfield        android/support/v7/internal/widget/ActionBarView.mContentHeight:I
        //   441: aload_2        
        //   442: invokevirtual   android/content/res/TypedArray.recycle:()V
        //   445: aload_0        
        //   446: new             Landroid/support/v7/internal/view/menu/ActionMenuItem;
        //   449: dup            
        //   450: aload_1        
        //   451: iconst_0       
        //   452: ldc_w           16908332
        //   455: iconst_0       
        //   456: iconst_0       
        //   457: aload_0        
        //   458: getfield        android/support/v7/internal/widget/ActionBarView.mTitle:Ljava/lang/CharSequence;
        //   461: invokespecial   android/support/v7/internal/view/menu/ActionMenuItem.<init>:(Landroid/content/Context;IIIILjava/lang/CharSequence;)V
        //   464: putfield        android/support/v7/internal/widget/ActionBarView.mLogoNavItem:Landroid/support/v7/internal/view/menu/ActionMenuItem;
        //   467: aload_0        
        //   468: getfield        android/support/v7/internal/widget/ActionBarView.mHomeLayout:Landroid/support/v7/internal/widget/ActionBarView$HomeView;
        //   471: aload_0        
        //   472: getfield        android/support/v7/internal/widget/ActionBarView.mUpClickListener:Landroid/view/View$OnClickListener;
        //   475: invokevirtual   android/support/v7/internal/widget/ActionBarView$HomeView.setOnClickListener:(Landroid/view/View$OnClickListener;)V
        //   478: aload_0        
        //   479: getfield        android/support/v7/internal/widget/ActionBarView.mHomeLayout:Landroid/support/v7/internal/widget/ActionBarView$HomeView;
        //   482: iconst_1       
        //   483: invokevirtual   android/support/v7/internal/widget/ActionBarView$HomeView.setClickable:(Z)V
        //   486: aload_0        
        //   487: getfield        android/support/v7/internal/widget/ActionBarView.mHomeLayout:Landroid/support/v7/internal/widget/ActionBarView$HomeView;
        //   490: iconst_1       
        //   491: invokevirtual   android/support/v7/internal/widget/ActionBarView$HomeView.setFocusable:(Z)V
        //   494: return         
        //   495: astore          6
        //   497: ldc             "ActionBarView"
        //   499: ldc_w           "Activity component name not found!"
        //   502: aload           6
        //   504: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   507: pop            
        //   508: goto            158
        //   511: astore          6
        //   513: ldc             "ActionBarView"
        //   515: ldc_w           "Activity component name not found!"
        //   518: aload           6
        //   520: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   523: pop            
        //   524: goto            216
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  142    158    495    511    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  200    216    511    527    Landroid/content/pm/PackageManager$NameNotFoundException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0216:
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
    
    private void configPresenters(final MenuBuilder menuBuilder) {
        if (menuBuilder != null) {
            menuBuilder.addMenuPresenter(this.mActionMenuPresenter);
            menuBuilder.addMenuPresenter(this.mExpandedMenuPresenter);
        }
        else {
            this.mActionMenuPresenter.initForMenu(this.mContext, null);
            this.mExpandedMenuPresenter.initForMenu(this.mContext, null);
        }
        this.mActionMenuPresenter.updateMenuView(true);
        this.mExpandedMenuPresenter.updateMenuView(true);
    }
    
    private void initTitle() {
        boolean enabled = true;
        if (this.mTitleLayout == null) {
            this.mTitleLayout = (LinearLayout)LayoutInflater.from(this.getContext()).inflate(R.layout.abc_action_bar_title_item, (ViewGroup)this, false);
            this.mTitleView = (TextView)this.mTitleLayout.findViewById(R.id.action_bar_title);
            this.mSubtitleView = (TextView)this.mTitleLayout.findViewById(R.id.action_bar_subtitle);
            this.mTitleUpView = this.mTitleLayout.findViewById(R.id.up);
            this.mTitleLayout.setOnClickListener(this.mUpClickListener);
            if (this.mTitleStyleRes != 0) {
                this.mTitleView.setTextAppearance(this.mContext, this.mTitleStyleRes);
            }
            if (this.mTitle != null) {
                this.mTitleView.setText(this.mTitle);
            }
            if (this.mSubtitleStyleRes != 0) {
                this.mSubtitleView.setTextAppearance(this.mContext, this.mSubtitleStyleRes);
            }
            if (this.mSubtitle != null) {
                this.mSubtitleView.setText(this.mSubtitle);
                this.mSubtitleView.setVisibility(0);
            }
            boolean b;
            if ((this.mDisplayOptions & 0x4) != 0x0) {
                b = true;
            }
            else {
                b = false;
            }
            boolean b2;
            if ((this.mDisplayOptions & 0x2) != 0x0) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            final View mTitleUpView = this.mTitleUpView;
            int visibility;
            if (!b2) {
                if (b) {
                    visibility = 0;
                }
                else {
                    visibility = 4;
                }
            }
            else {
                visibility = 8;
            }
            mTitleUpView.setVisibility(visibility);
            final LinearLayout mTitleLayout = this.mTitleLayout;
            if (!b || b2) {
                enabled = false;
            }
            mTitleLayout.setEnabled(enabled);
        }
        this.addView((View)this.mTitleLayout);
        if (this.mExpandedActionView != null || (TextUtils.isEmpty(this.mTitle) && TextUtils.isEmpty(this.mSubtitle))) {
            this.mTitleLayout.setVisibility(8);
        }
    }
    
    private void setTitleImpl(final CharSequence title) {
        final boolean b = false;
        this.mTitle = title;
        if (this.mTitleView != null) {
            this.mTitleView.setText(title);
            int n;
            if (this.mExpandedActionView == null && (this.mDisplayOptions & 0x8) != 0x0 && (!TextUtils.isEmpty(this.mTitle) || !TextUtils.isEmpty(this.mSubtitle))) {
                n = 1;
            }
            else {
                n = 0;
            }
            final LinearLayout mTitleLayout = this.mTitleLayout;
            int visibility;
            if (n != 0) {
                visibility = (b ? 1 : 0);
            }
            else {
                visibility = 8;
            }
            mTitleLayout.setVisibility(visibility);
        }
        if (this.mLogoNavItem != null) {
            this.mLogoNavItem.setTitle(title);
        }
    }
    
    public void collapseActionView() {
        MenuItemImpl mCurrentExpandedItem;
        if (this.mExpandedMenuPresenter == null) {
            mCurrentExpandedItem = null;
        }
        else {
            mCurrentExpandedItem = this.mExpandedMenuPresenter.mCurrentExpandedItem;
        }
        if (mCurrentExpandedItem != null) {
            mCurrentExpandedItem.collapseActionView();
        }
    }
    
    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return (ViewGroup$LayoutParams)new ActionBar.LayoutParams(19);
    }
    
    public ViewGroup$LayoutParams generateLayoutParams(final AttributeSet set) {
        return (ViewGroup$LayoutParams)new ActionBar.LayoutParams(this.getContext(), set);
    }
    
    public ViewGroup$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        ViewGroup$LayoutParams generateDefaultLayoutParams = viewGroup$LayoutParams;
        if (viewGroup$LayoutParams == null) {
            generateDefaultLayoutParams = this.generateDefaultLayoutParams();
        }
        return generateDefaultLayoutParams;
    }
    
    public View getCustomNavigationView() {
        return this.mCustomNavView;
    }
    
    public int getDisplayOptions() {
        return this.mDisplayOptions;
    }
    
    public SpinnerAdapter getDropdownAdapter() {
        return this.mSpinnerAdapter;
    }
    
    public int getDropdownSelectedPosition() {
        return this.mSpinner.getSelectedItemPosition();
    }
    
    public int getNavigationMode() {
        return this.mNavigationMode;
    }
    
    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }
    
    public CharSequence getTitle() {
        return this.mTitle;
    }
    
    public boolean hasEmbeddedTabs() {
        return this.mIncludeTabs;
    }
    
    public boolean hasExpandedActionView() {
        return this.mExpandedMenuPresenter != null && this.mExpandedMenuPresenter.mCurrentExpandedItem != null;
    }
    
    public void initIndeterminateProgress() {
        (this.mIndeterminateProgressView = new ProgressBarICS(this.mContext, null, 0, this.mIndeterminateProgressStyle)).setId(R.id.progress_circular);
        this.mIndeterminateProgressView.setVisibility(8);
        this.addView((View)this.mIndeterminateProgressView);
    }
    
    public void initProgress() {
        (this.mProgressView = new ProgressBarICS(this.mContext, null, 0, this.mProgressStyle)).setId(R.id.progress_horizontal);
        this.mProgressView.setMax(10000);
        this.mProgressView.setVisibility(8);
        this.addView((View)this.mProgressView);
    }
    
    public boolean isCollapsed() {
        return this.mIsCollapsed;
    }
    
    public boolean isSplitActionBar() {
        return this.mSplitActionBar;
    }
    
    @Override
    protected void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mTitleView = null;
        this.mSubtitleView = null;
        this.mTitleUpView = null;
        if (this.mTitleLayout != null && this.mTitleLayout.getParent() == this) {
            this.removeView((View)this.mTitleLayout);
        }
        this.mTitleLayout = null;
        if ((this.mDisplayOptions & 0x8) != 0x0) {
            this.initTitle();
        }
        if (this.mTabScrollView != null && this.mIncludeTabs) {
            final ViewGroup$LayoutParams layoutParams = this.mTabScrollView.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = -2;
                layoutParams.height = -1;
            }
            this.mTabScrollView.setAllowCollapse(true);
        }
        if (this.mProgressView != null) {
            this.removeView((View)this.mProgressView);
            this.initProgress();
        }
        if (this.mIndeterminateProgressView != null) {
            this.removeView((View)this.mIndeterminateProgressView);
            this.initIndeterminateProgress();
        }
    }
    
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.removeCallbacks(this.mTabSelector);
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.hideOverflowMenu();
            this.mActionMenuPresenter.hideSubMenus();
        }
    }
    
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.addView((View)this.mHomeLayout);
        if (this.mCustomNavView != null && (this.mDisplayOptions & 0x10) != 0x0) {
            final ViewParent parent = this.mCustomNavView.getParent();
            if (parent != this) {
                if (parent instanceof ViewGroup) {
                    ((ActionBarView)parent).removeView(this.mCustomNavView);
                }
                this.addView(this.mCustomNavView);
            }
        }
    }
    
    protected void onLayout(final boolean b, int n, int n2, int measuredWidth, int gravity) {
        final int paddingLeft = this.getPaddingLeft();
        final int paddingTop = this.getPaddingTop();
        final int n3 = gravity - n2 - this.getPaddingTop() - this.getPaddingBottom();
        if (n3 > 0) {
            HomeView homeView;
            if (this.mExpandedActionView != null) {
                homeView = this.mExpandedHomeLayout;
            }
            else {
                homeView = this.mHomeLayout;
            }
            int n4 = paddingLeft;
            if (homeView.getVisibility() != 8) {
                n2 = homeView.getLeftOffset();
                n4 = paddingLeft + (this.positionChild((View)homeView, paddingLeft + n2, paddingTop, n3) + n2);
            }
            n2 = n4;
            if (this.mExpandedActionView == null) {
                boolean b2;
                if (this.mTitleLayout != null && this.mTitleLayout.getVisibility() != 8 && (this.mDisplayOptions & 0x8) != 0x0) {
                    b2 = true;
                }
                else {
                    b2 = false;
                }
                gravity = n4;
                if (b2) {
                    gravity = n4 + this.positionChild((View)this.mTitleLayout, n4, paddingTop, n3);
                }
                n2 = gravity;
                switch (this.mNavigationMode) {
                    default: {
                        n2 = gravity;
                        break;
                    }
                    case 0: {
                        break;
                    }
                    case 1: {
                        n2 = gravity;
                        if (this.mListNavLayout != null) {
                            n2 = gravity;
                            if (b2) {
                                n2 = gravity + this.mItemPadding;
                            }
                            n2 += this.positionChild((View)this.mListNavLayout, n2, paddingTop, n3) + this.mItemPadding;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        n2 = gravity;
                        if (this.mTabScrollView != null) {
                            n2 = gravity;
                            if (b2) {
                                n2 = gravity + this.mItemPadding;
                            }
                            n2 += this.positionChild((View)this.mTabScrollView, n2, paddingTop, n3) + this.mItemPadding;
                            break;
                        }
                        break;
                    }
                }
            }
            n = (measuredWidth = measuredWidth - n - this.getPaddingRight());
            if (this.mMenuView != null) {
                measuredWidth = n;
                if (this.mMenuView.getParent() == this) {
                    this.positionChildInverse((View)this.mMenuView, n, paddingTop, n3);
                    measuredWidth = n - this.mMenuView.getMeasuredWidth();
                }
            }
            n = measuredWidth;
            if (this.mIndeterminateProgressView != null) {
                n = measuredWidth;
                if (this.mIndeterminateProgressView.getVisibility() != 8) {
                    this.positionChildInverse(this.mIndeterminateProgressView, measuredWidth, paddingTop, n3);
                    n = measuredWidth - this.mIndeterminateProgressView.getMeasuredWidth();
                }
            }
            final View view = null;
            View view2;
            if (this.mExpandedActionView != null) {
                view2 = this.mExpandedActionView;
            }
            else {
                view2 = view;
                if ((this.mDisplayOptions & 0x10) != 0x0) {
                    view2 = view;
                    if (this.mCustomNavView != null) {
                        view2 = this.mCustomNavView;
                    }
                }
            }
            if (view2 != null) {
                final ViewGroup$LayoutParams layoutParams = view2.getLayoutParams();
                ActionBar.LayoutParams layoutParams2;
                if (layoutParams instanceof ActionBar.LayoutParams) {
                    layoutParams2 = (ActionBar.LayoutParams)layoutParams;
                }
                else {
                    layoutParams2 = null;
                }
                if (layoutParams2 != null) {
                    gravity = layoutParams2.gravity;
                }
                else {
                    gravity = 19;
                }
                final int measuredWidth2 = view2.getMeasuredWidth();
                int topMargin = 0;
                int bottomMargin = 0;
                int n5 = n;
                measuredWidth = n2;
                if (layoutParams2 != null) {
                    measuredWidth = n2 + layoutParams2.leftMargin;
                    n5 = n - layoutParams2.rightMargin;
                    topMargin = layoutParams2.topMargin;
                    bottomMargin = layoutParams2.bottomMargin;
                }
                n2 = (gravity & 0x7);
                if (n2 == 1) {
                    n = (this.getWidth() - measuredWidth2) / 2;
                    if (n < measuredWidth) {
                        n2 = 3;
                    }
                    else if (n + measuredWidth2 > n5) {
                        n2 = 5;
                    }
                }
                else if (gravity == -1) {
                    n2 = 3;
                }
                final int n6 = n = 0;
                Label_0491: {
                    switch (n2) {
                        default: {
                            n = n6;
                            break Label_0491;
                        }
                        case 5: {
                            n = n5 - measuredWidth2;
                            break Label_0491;
                        }
                        case 3: {
                            n = measuredWidth;
                            break Label_0491;
                        }
                        case 1: {
                            n = (this.getWidth() - measuredWidth2) / 2;
                        }
                        case 2:
                        case 4: {
                            n2 = (gravity & 0x70);
                            if (gravity == -1) {
                                n2 = 16;
                            }
                            measuredWidth = 0;
                            switch (n2) {
                                default: {
                                    n2 = measuredWidth;
                                    break;
                                }
                                case 16: {
                                    n2 = this.getPaddingTop();
                                    n2 = (this.getHeight() - this.getPaddingBottom() - n2 - view2.getMeasuredHeight()) / 2;
                                    break;
                                }
                                case 48: {
                                    n2 = this.getPaddingTop() + topMargin;
                                    break;
                                }
                                case 80: {
                                    n2 = this.getHeight() - this.getPaddingBottom() - view2.getMeasuredHeight() - bottomMargin;
                                    break;
                                }
                            }
                            measuredWidth = view2.getMeasuredWidth();
                            view2.layout(n, n2, n + measuredWidth, view2.getMeasuredHeight() + n2);
                            break;
                        }
                    }
                }
            }
            if (this.mProgressView != null) {
                this.mProgressView.bringToFront();
                n = this.mProgressView.getMeasuredHeight() / 2;
                this.mProgressView.layout(this.mProgressBarPadding, -n, this.mProgressBarPadding + this.mProgressView.getMeasuredWidth(), n);
            }
        }
    }
    
    protected void onMeasure(int i, int n) {
        final int childCount = this.getChildCount();
        Label_0102: {
            if (!this.mIsCollapsable) {
                break Label_0102;
            }
            int n2 = 0;
            int n3;
            for (int j = 0; j < childCount; ++j, n2 = n3) {
                final View child = this.getChildAt(j);
                n3 = n2;
                if (child.getVisibility() != 8) {
                    if (child == this.mMenuView) {
                        n3 = n2;
                        if (this.mMenuView.getChildCount() == 0) {
                            continue;
                        }
                    }
                    n3 = n2 + 1;
                }
            }
            if (n2 != 0) {
                break Label_0102;
            }
            this.setMeasuredDimension(0, 0);
            this.mIsCollapsed = true;
            return;
        }
        this.mIsCollapsed = false;
        if (View$MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used " + "with android:layout_width=\"MATCH_PARENT\" (or fill_parent)");
        }
        if (View$MeasureSpec.getMode(n) != Integer.MIN_VALUE) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used " + "with android:layout_height=\"wrap_content\"");
        }
        final int size = View$MeasureSpec.getSize(i);
        int n4;
        if (this.mContentHeight > 0) {
            n4 = this.mContentHeight;
        }
        else {
            n4 = View$MeasureSpec.getSize(n);
        }
        final int n5 = this.getPaddingTop() + this.getPaddingBottom();
        i = this.getPaddingLeft();
        n = this.getPaddingRight();
        final int n6 = n4 - n5;
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(n6, Integer.MIN_VALUE);
        final int n7 = size - i - n;
        final int n8;
        int max = n8 = n7 / 2;
        HomeView homeView;
        if (this.mExpandedActionView != null) {
            homeView = this.mExpandedHomeLayout;
        }
        else {
            homeView = this.mHomeLayout;
        }
        n = n7;
        if (homeView.getVisibility() != 8) {
            final ViewGroup$LayoutParams layoutParams = homeView.getLayoutParams();
            if (layoutParams.width < 0) {
                i = View$MeasureSpec.makeMeasureSpec(n7, Integer.MIN_VALUE);
            }
            else {
                i = View$MeasureSpec.makeMeasureSpec(layoutParams.width, 1073741824);
            }
            homeView.measure(i, View$MeasureSpec.makeMeasureSpec(n6, 1073741824));
            i = homeView.getMeasuredWidth() + homeView.getLeftOffset();
            n = Math.max(0, n7 - i);
            max = Math.max(0, n - i);
        }
        int measureChildView = n;
        i = n8;
        if (this.mMenuView != null) {
            measureChildView = n;
            i = n8;
            if (this.mMenuView.getParent() == this) {
                measureChildView = this.measureChildView((View)this.mMenuView, n, measureSpec, 0);
                i = Math.max(0, n8 - this.mMenuView.getMeasuredWidth());
            }
        }
        int measureChildView2 = measureChildView;
        int max2 = i;
        if (this.mIndeterminateProgressView != null) {
            measureChildView2 = measureChildView;
            max2 = i;
            if (this.mIndeterminateProgressView.getVisibility() != 8) {
                measureChildView2 = this.measureChildView(this.mIndeterminateProgressView, measureChildView, measureSpec, 0);
                max2 = Math.max(0, i - this.mIndeterminateProgressView.getMeasuredWidth());
            }
        }
        boolean b;
        if (this.mTitleLayout != null && this.mTitleLayout.getVisibility() != 8 && (this.mDisplayOptions & 0x8) != 0x0) {
            b = true;
        }
        else {
            b = false;
        }
        i = measureChildView2;
        n = max;
        if (this.mExpandedActionView == null) {
            switch (this.mNavigationMode) {
                default: {
                    n = max;
                    i = measureChildView2;
                    break;
                }
                case 1: {
                    i = measureChildView2;
                    n = max;
                    if (this.mListNavLayout != null) {
                        if (b) {
                            i = this.mItemPadding * 2;
                        }
                        else {
                            i = this.mItemPadding;
                        }
                        n = Math.max(0, measureChildView2 - i);
                        final int max3 = Math.max(0, max - i);
                        this.mListNavLayout.measure(View$MeasureSpec.makeMeasureSpec(n, Integer.MIN_VALUE), View$MeasureSpec.makeMeasureSpec(n6, 1073741824));
                        final int measuredWidth = this.mListNavLayout.getMeasuredWidth();
                        i = Math.max(0, n - measuredWidth);
                        n = Math.max(0, max3 - measuredWidth);
                        break;
                    }
                    break;
                }
                case 2: {
                    i = measureChildView2;
                    n = max;
                    if (this.mTabScrollView != null) {
                        if (b) {
                            i = this.mItemPadding * 2;
                        }
                        else {
                            i = this.mItemPadding;
                        }
                        n = Math.max(0, measureChildView2 - i);
                        final int max4 = Math.max(0, max - i);
                        this.mTabScrollView.measure(View$MeasureSpec.makeMeasureSpec(n, Integer.MIN_VALUE), View$MeasureSpec.makeMeasureSpec(n6, 1073741824));
                        final int measuredWidth2 = this.mTabScrollView.getMeasuredWidth();
                        i = Math.max(0, n - measuredWidth2);
                        n = Math.max(0, max4 - measuredWidth2);
                        break;
                    }
                    break;
                }
            }
        }
        final View view = null;
        View view2;
        if (this.mExpandedActionView != null) {
            view2 = this.mExpandedActionView;
        }
        else {
            view2 = view;
            if ((this.mDisplayOptions & 0x10) != 0x0) {
                view2 = view;
                if (this.mCustomNavView != null) {
                    view2 = this.mCustomNavView;
                }
            }
        }
        int n9 = i;
        if (view2 != null) {
            final ViewGroup$LayoutParams generateLayoutParams = this.generateLayoutParams(view2.getLayoutParams());
            ActionBar.LayoutParams layoutParams2;
            if (generateLayoutParams instanceof ActionBar.LayoutParams) {
                layoutParams2 = (ActionBar.LayoutParams)generateLayoutParams;
            }
            else {
                layoutParams2 = null;
            }
            int n10 = 0;
            int n11 = 0;
            if (layoutParams2 != null) {
                n10 = layoutParams2.leftMargin + layoutParams2.rightMargin;
                n11 = layoutParams2.topMargin + layoutParams2.bottomMargin;
            }
            int n12;
            if (this.mContentHeight <= 0) {
                n12 = Integer.MIN_VALUE;
            }
            else if (generateLayoutParams.height != -2) {
                n12 = 1073741824;
            }
            else {
                n12 = Integer.MIN_VALUE;
            }
            int min = n6;
            if (generateLayoutParams.height >= 0) {
                min = Math.min(generateLayoutParams.height, n6);
            }
            final int max5 = Math.max(0, min - n11);
            int n13;
            if (generateLayoutParams.width != -2) {
                n13 = 1073741824;
            }
            else {
                n13 = Integer.MIN_VALUE;
            }
            int min2;
            if (generateLayoutParams.width >= 0) {
                min2 = Math.min(generateLayoutParams.width, i);
            }
            else {
                min2 = i;
            }
            final int max6 = Math.max(0, min2 - n10);
            int gravity;
            if (layoutParams2 != null) {
                gravity = layoutParams2.gravity;
            }
            else {
                gravity = 19;
            }
            int n14 = max6;
            if ((gravity & 0x7) == 0x1) {
                n14 = max6;
                if (generateLayoutParams.width == -1) {
                    n14 = Math.min(n, max2) * 2;
                }
            }
            view2.measure(View$MeasureSpec.makeMeasureSpec(n14, n13), View$MeasureSpec.makeMeasureSpec(max5, n12));
            n9 = i - (view2.getMeasuredWidth() + n10);
        }
        if (this.mExpandedActionView == null && b) {
            this.measureChildView((View)this.mTitleLayout, n9, View$MeasureSpec.makeMeasureSpec(this.mContentHeight, 1073741824), 0);
            Math.max(0, n - this.mTitleLayout.getMeasuredWidth());
        }
        if (this.mContentHeight <= 0) {
            n = 0;
            int n15;
            int n16;
            for (i = 0; i < childCount; ++i, n = n16) {
                n15 = this.getChildAt(i).getMeasuredHeight() + n5;
                if (n15 > (n16 = n)) {
                    n16 = n15;
                }
            }
            this.setMeasuredDimension(size, n);
        }
        else {
            this.setMeasuredDimension(size, n4);
        }
        if (this.mContextView != null) {
            this.mContextView.setContentHeight(this.getMeasuredHeight());
        }
        if (this.mProgressView != null && this.mProgressView.getVisibility() != 8) {
            this.mProgressView.measure(View$MeasureSpec.makeMeasureSpec(size - this.mProgressBarPadding * 2, 1073741824), View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), Integer.MIN_VALUE));
        }
    }
    
    public void onRestoreInstanceState(final Parcelable parcelable) {
        final SavedState savedState = (SavedState)parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.expandedMenuItemId != 0 && this.mExpandedMenuPresenter != null && this.mOptionsMenu != null) {
            final SupportMenuItem supportMenuItem = (SupportMenuItem)this.mOptionsMenu.findItem(savedState.expandedMenuItemId);
            if (supportMenuItem != null) {
                supportMenuItem.expandActionView();
            }
        }
        if (savedState.isOverflowOpen) {
            this.postShowOverflowMenu();
        }
    }
    
    public Parcelable onSaveInstanceState() {
        final SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.mExpandedMenuPresenter != null && this.mExpandedMenuPresenter.mCurrentExpandedItem != null) {
            savedState.expandedMenuItemId = this.mExpandedMenuPresenter.mCurrentExpandedItem.getItemId();
        }
        savedState.isOverflowOpen = this.isOverflowMenuShowing();
        return (Parcelable)savedState;
    }
    
    public void setCallback(final ActionBar.OnNavigationListener mCallback) {
        this.mCallback = mCallback;
    }
    
    public void setCollapsable(final boolean mIsCollapsable) {
        this.mIsCollapsable = mIsCollapsable;
    }
    
    public void setContextView(final ActionBarContextView mContextView) {
        this.mContextView = mContextView;
    }
    
    public void setCustomNavigationView(final View mCustomNavView) {
        boolean b;
        if ((this.mDisplayOptions & 0x10) != 0x0) {
            b = true;
        }
        else {
            b = false;
        }
        if (this.mCustomNavView != null && b) {
            this.removeView(this.mCustomNavView);
        }
        this.mCustomNavView = mCustomNavView;
        if (this.mCustomNavView != null && b) {
            this.addView(this.mCustomNavView);
        }
    }
    
    public void setDisplayOptions(final int mDisplayOptions) {
        final int n = 8;
        int n2 = -1;
        final boolean b = true;
        if (this.mDisplayOptions != -1) {
            n2 = (mDisplayOptions ^ this.mDisplayOptions);
        }
        this.mDisplayOptions = mDisplayOptions;
        if ((n2 & 0x1F) != 0x0) {
            boolean b2;
            if ((mDisplayOptions & 0x2) != 0x0) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            int visibility;
            if (b2 && this.mExpandedActionView == null) {
                visibility = 0;
            }
            else {
                visibility = 8;
            }
            this.mHomeLayout.setVisibility(visibility);
            if ((n2 & 0x4) != 0x0) {
                final boolean up = (mDisplayOptions & 0x4) != 0x0;
                this.mHomeLayout.setUp(up);
                if (up) {
                    this.setHomeButtonEnabled(true);
                }
            }
            if ((n2 & 0x1) != 0x0) {
                int n3;
                if (this.mLogo != null && (mDisplayOptions & 0x1) != 0x0) {
                    n3 = 1;
                }
                else {
                    n3 = 0;
                }
                final HomeView mHomeLayout = this.mHomeLayout;
                Drawable icon;
                if (n3 != 0) {
                    icon = this.mLogo;
                }
                else {
                    icon = this.mIcon;
                }
                mHomeLayout.setIcon(icon);
            }
            if ((n2 & 0x8) != 0x0) {
                if ((mDisplayOptions & 0x8) != 0x0) {
                    this.initTitle();
                }
                else {
                    this.removeView((View)this.mTitleLayout);
                }
            }
            if (this.mTitleLayout != null && (n2 & 0x6) != 0x0) {
                boolean b3;
                if ((this.mDisplayOptions & 0x4) != 0x0) {
                    b3 = true;
                }
                else {
                    b3 = false;
                }
                final View mTitleUpView = this.mTitleUpView;
                int visibility2 = n;
                if (!b2) {
                    if (b3) {
                        visibility2 = 0;
                    }
                    else {
                        visibility2 = 4;
                    }
                }
                mTitleUpView.setVisibility(visibility2);
                this.mTitleLayout.setEnabled(!b2 && b3 && b);
            }
            if ((n2 & 0x10) != 0x0 && this.mCustomNavView != null) {
                if ((mDisplayOptions & 0x10) != 0x0) {
                    this.addView(this.mCustomNavView);
                }
                else {
                    this.removeView(this.mCustomNavView);
                }
            }
            this.requestLayout();
        }
        else {
            this.invalidate();
        }
        if (!this.mHomeLayout.isEnabled()) {
            this.mHomeLayout.setContentDescription((CharSequence)null);
            return;
        }
        if ((mDisplayOptions & 0x4) != 0x0) {
            this.mHomeLayout.setContentDescription(this.mContext.getResources().getText(R.string.abc_action_bar_up_description));
            return;
        }
        this.mHomeLayout.setContentDescription(this.mContext.getResources().getText(R.string.abc_action_bar_home_description));
    }
    
    public void setDropdownAdapter(final SpinnerAdapter spinnerAdapter) {
        this.mSpinnerAdapter = spinnerAdapter;
        if (this.mSpinner != null) {
            this.mSpinner.setAdapter(spinnerAdapter);
        }
    }
    
    public void setDropdownSelectedPosition(final int selection) {
        this.mSpinner.setSelection(selection);
    }
    
    public void setEmbeddedTabView(final ScrollingTabContainerView mTabScrollView) {
        if (this.mTabScrollView != null) {
            this.removeView((View)this.mTabScrollView);
        }
        this.mTabScrollView = mTabScrollView;
        this.mIncludeTabs = (mTabScrollView != null);
        if (this.mIncludeTabs && this.mNavigationMode == 2) {
            this.addView((View)this.mTabScrollView);
            final ViewGroup$LayoutParams layoutParams = this.mTabScrollView.getLayoutParams();
            layoutParams.width = -2;
            layoutParams.height = -1;
            mTabScrollView.setAllowCollapse(true);
        }
    }
    
    public void setHomeAsUpIndicator(final int upIndicator) {
        this.mHomeLayout.setUpIndicator(upIndicator);
    }
    
    public void setHomeAsUpIndicator(final Drawable upIndicator) {
        this.mHomeLayout.setUpIndicator(upIndicator);
    }
    
    public void setHomeButtonEnabled(final boolean b) {
        this.mHomeLayout.setEnabled(b);
        this.mHomeLayout.setFocusable(b);
        if (!b) {
            this.mHomeLayout.setContentDescription((CharSequence)null);
            return;
        }
        if ((this.mDisplayOptions & 0x4) != 0x0) {
            this.mHomeLayout.setContentDescription(this.mContext.getResources().getText(R.string.abc_action_bar_up_description));
            return;
        }
        this.mHomeLayout.setContentDescription(this.mContext.getResources().getText(R.string.abc_action_bar_home_description));
    }
    
    public void setIcon(final int n) {
        this.setIcon(this.mContext.getResources().getDrawable(n));
    }
    
    public void setIcon(final Drawable drawable) {
        this.mIcon = drawable;
        if (drawable != null && ((this.mDisplayOptions & 0x1) == 0x0 || this.mLogo == null)) {
            this.mHomeLayout.setIcon(drawable);
        }
        if (this.mExpandedActionView != null) {
            this.mExpandedHomeLayout.setIcon(this.mIcon.getConstantState().newDrawable(this.getResources()));
        }
    }
    
    public void setLogo(final int n) {
        this.setLogo(this.mContext.getResources().getDrawable(n));
    }
    
    public void setLogo(final Drawable drawable) {
        this.mLogo = drawable;
        if (drawable != null && (this.mDisplayOptions & 0x1) != 0x0) {
            this.mHomeLayout.setIcon(drawable);
        }
    }
    
    public void setMenu(final SupportMenu supportMenu, final MenuPresenter.Callback callback) {
        if (supportMenu == this.mOptionsMenu) {
            return;
        }
        if (this.mOptionsMenu != null) {
            this.mOptionsMenu.removeMenuPresenter(this.mActionMenuPresenter);
            this.mOptionsMenu.removeMenuPresenter(this.mExpandedMenuPresenter);
        }
        final MenuBuilder mOptionsMenu = (MenuBuilder)supportMenu;
        this.mOptionsMenu = mOptionsMenu;
        if (this.mMenuView != null) {
            final ViewGroup viewGroup = (ViewGroup)this.mMenuView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView((View)this.mMenuView);
            }
        }
        if (this.mActionMenuPresenter == null) {
            (this.mActionMenuPresenter = new ActionMenuPresenter(this.mContext)).setCallback(callback);
            this.mActionMenuPresenter.setId(R.id.action_menu_presenter);
            this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter();
        }
        final ViewGroup$LayoutParams layoutParams = new ViewGroup$LayoutParams(-2, -1);
        ActionMenuView mMenuView;
        if (!this.mSplitActionBar) {
            this.mActionMenuPresenter.setExpandedActionViewsExclusive(this.getResources().getBoolean(R.bool.abc_action_bar_expanded_action_views_exclusive));
            this.configPresenters(mOptionsMenu);
            mMenuView = (ActionMenuView)this.mActionMenuPresenter.getMenuView(this);
            mMenuView.initialize(mOptionsMenu);
            final ViewGroup viewGroup2 = (ViewGroup)mMenuView.getParent();
            if (viewGroup2 != null && viewGroup2 != this) {
                viewGroup2.removeView((View)mMenuView);
            }
            this.addView((View)mMenuView, layoutParams);
        }
        else {
            this.mActionMenuPresenter.setExpandedActionViewsExclusive(false);
            this.mActionMenuPresenter.setWidthLimit(this.getContext().getResources().getDisplayMetrics().widthPixels, true);
            this.mActionMenuPresenter.setItemLimit(Integer.MAX_VALUE);
            layoutParams.width = -1;
            this.configPresenters(mOptionsMenu);
            mMenuView = (ActionMenuView)this.mActionMenuPresenter.getMenuView(this);
            if (this.mSplitView != null) {
                final ViewGroup viewGroup3 = (ViewGroup)mMenuView.getParent();
                if (viewGroup3 != null && viewGroup3 != this.mSplitView) {
                    viewGroup3.removeView((View)mMenuView);
                }
                mMenuView.setVisibility(this.getAnimatedVisibility());
                this.mSplitView.addView((View)mMenuView, layoutParams);
            }
            else {
                mMenuView.setLayoutParams(layoutParams);
            }
        }
        this.mMenuView = mMenuView;
    }
    
    public void setNavigationMode(final int mNavigationMode) {
        final int mNavigationMode2 = this.mNavigationMode;
        if (mNavigationMode != mNavigationMode2) {
            switch (mNavigationMode2) {
                case 1: {
                    if (this.mListNavLayout != null) {
                        this.removeView((View)this.mListNavLayout);
                        break;
                    }
                    break;
                }
                case 2: {
                    if (this.mTabScrollView != null && this.mIncludeTabs) {
                        this.removeView((View)this.mTabScrollView);
                        break;
                    }
                    break;
                }
            }
            switch (mNavigationMode) {
                case 1: {
                    if (this.mSpinner == null) {
                        this.mSpinner = new SpinnerICS(this.mContext, null, R.attr.actionDropDownStyle);
                        this.mListNavLayout = (LinearLayout)LayoutInflater.from(this.mContext).inflate(R.layout.abc_action_bar_view_list_nav_layout, (ViewGroup)null);
                        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-2, -1);
                        linearLayout$LayoutParams.gravity = 17;
                        this.mListNavLayout.addView((View)this.mSpinner, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
                    }
                    if (this.mSpinner.getAdapter() != this.mSpinnerAdapter) {
                        this.mSpinner.setAdapter(this.mSpinnerAdapter);
                    }
                    this.mSpinner.setOnItemSelectedListener(this.mNavItemSelectedListener);
                    this.addView((View)this.mListNavLayout);
                    break;
                }
                case 2: {
                    if (this.mTabScrollView != null && this.mIncludeTabs) {
                        this.addView((View)this.mTabScrollView);
                        break;
                    }
                    break;
                }
            }
            this.mNavigationMode = mNavigationMode;
            this.requestLayout();
        }
    }
    
    @Override
    public void setSplitActionBar(final boolean splitActionBar) {
        if (this.mSplitActionBar != splitActionBar) {
            if (this.mMenuView != null) {
                final ViewGroup viewGroup = (ViewGroup)this.mMenuView.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView((View)this.mMenuView);
                }
                if (splitActionBar) {
                    if (this.mSplitView != null) {
                        this.mSplitView.addView((View)this.mMenuView);
                    }
                    this.mMenuView.getLayoutParams().width = -1;
                }
                else {
                    this.addView((View)this.mMenuView);
                    this.mMenuView.getLayoutParams().width = -2;
                }
                this.mMenuView.requestLayout();
            }
            if (this.mSplitView != null) {
                final ActionBarContainer mSplitView = this.mSplitView;
                int visibility;
                if (splitActionBar) {
                    visibility = 0;
                }
                else {
                    visibility = 8;
                }
                mSplitView.setVisibility(visibility);
            }
            if (this.mActionMenuPresenter != null) {
                if (!splitActionBar) {
                    this.mActionMenuPresenter.setExpandedActionViewsExclusive(this.getResources().getBoolean(R.bool.abc_action_bar_expanded_action_views_exclusive));
                }
                else {
                    this.mActionMenuPresenter.setExpandedActionViewsExclusive(false);
                    this.mActionMenuPresenter.setWidthLimit(this.getContext().getResources().getDisplayMetrics().widthPixels, true);
                    this.mActionMenuPresenter.setItemLimit(Integer.MAX_VALUE);
                }
            }
            super.setSplitActionBar(splitActionBar);
        }
    }
    
    public void setSubtitle(final CharSequence charSequence) {
        final boolean b = false;
        this.mSubtitle = charSequence;
        if (this.mSubtitleView != null) {
            this.mSubtitleView.setText(charSequence);
            final TextView mSubtitleView = this.mSubtitleView;
            int visibility;
            if (charSequence != null) {
                visibility = 0;
            }
            else {
                visibility = 8;
            }
            mSubtitleView.setVisibility(visibility);
            int n;
            if (this.mExpandedActionView == null && (this.mDisplayOptions & 0x8) != 0x0 && (!TextUtils.isEmpty(this.mTitle) || !TextUtils.isEmpty(this.mSubtitle))) {
                n = 1;
            }
            else {
                n = 0;
            }
            final LinearLayout mTitleLayout = this.mTitleLayout;
            int visibility2;
            if (n != 0) {
                visibility2 = (b ? 1 : 0);
            }
            else {
                visibility2 = 8;
            }
            mTitleLayout.setVisibility(visibility2);
        }
    }
    
    public void setTitle(final CharSequence titleImpl) {
        this.mUserTitle = true;
        this.setTitleImpl(titleImpl);
    }
    
    public void setWindowCallback(final Window$Callback mWindowCallback) {
        this.mWindowCallback = mWindowCallback;
    }
    
    public void setWindowTitle(final CharSequence titleImpl) {
        if (!this.mUserTitle) {
            this.setTitleImpl(titleImpl);
        }
    }
    
    public boolean shouldDelayChildPressedState() {
        return false;
    }
    
    private class ExpandedActionViewMenuPresenter implements MenuPresenter
    {
        MenuItemImpl mCurrentExpandedItem;
        MenuBuilder mMenu;
        
        @Override
        public boolean collapseItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl menuItemImpl) {
            if (ActionBarView.this.mExpandedActionView instanceof CollapsibleActionView) {
                ((CollapsibleActionView)ActionBarView.this.mExpandedActionView).onActionViewCollapsed();
            }
            ActionBarView.this.removeView(ActionBarView.this.mExpandedActionView);
            ActionBarView.this.removeView((View)ActionBarView.this.mExpandedHomeLayout);
            ActionBarView.this.mExpandedActionView = null;
            if ((ActionBarView.this.mDisplayOptions & 0x2) != 0x0) {
                ActionBarView.this.mHomeLayout.setVisibility(0);
            }
            if ((ActionBarView.this.mDisplayOptions & 0x8) != 0x0) {
                if (ActionBarView.this.mTitleLayout == null) {
                    ActionBarView.this.initTitle();
                }
                else {
                    ActionBarView.this.mTitleLayout.setVisibility(0);
                }
            }
            if (ActionBarView.this.mTabScrollView != null && ActionBarView.this.mNavigationMode == 2) {
                ActionBarView.this.mTabScrollView.setVisibility(0);
            }
            if (ActionBarView.this.mSpinner != null && ActionBarView.this.mNavigationMode == 1) {
                ActionBarView.this.mSpinner.setVisibility(0);
            }
            if (ActionBarView.this.mCustomNavView != null && (ActionBarView.this.mDisplayOptions & 0x10) != 0x0) {
                ActionBarView.this.mCustomNavView.setVisibility(0);
            }
            ActionBarView.this.mExpandedHomeLayout.setIcon(null);
            this.mCurrentExpandedItem = null;
            ActionBarView.this.requestLayout();
            menuItemImpl.setActionViewExpanded(false);
            return true;
        }
        
        @Override
        public boolean expandItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl mCurrentExpandedItem) {
            ActionBarView.this.mExpandedActionView = mCurrentExpandedItem.getActionView();
            ActionBarView.this.mExpandedHomeLayout.setIcon(ActionBarView.this.mIcon.getConstantState().newDrawable(ActionBarView.this.getResources()));
            this.mCurrentExpandedItem = mCurrentExpandedItem;
            if (ActionBarView.this.mExpandedActionView.getParent() != ActionBarView.this) {
                ActionBarView.this.addView(ActionBarView.this.mExpandedActionView);
            }
            if (ActionBarView.this.mExpandedHomeLayout.getParent() != ActionBarView.this) {
                ActionBarView.this.addView((View)ActionBarView.this.mExpandedHomeLayout);
            }
            ActionBarView.this.mHomeLayout.setVisibility(8);
            if (ActionBarView.this.mTitleLayout != null) {
                ActionBarView.this.mTitleLayout.setVisibility(8);
            }
            if (ActionBarView.this.mTabScrollView != null) {
                ActionBarView.this.mTabScrollView.setVisibility(8);
            }
            if (ActionBarView.this.mSpinner != null) {
                ActionBarView.this.mSpinner.setVisibility(8);
            }
            if (ActionBarView.this.mCustomNavView != null) {
                ActionBarView.this.mCustomNavView.setVisibility(8);
            }
            ActionBarView.this.requestLayout();
            mCurrentExpandedItem.setActionViewExpanded(true);
            if (ActionBarView.this.mExpandedActionView instanceof CollapsibleActionView) {
                ((CollapsibleActionView)ActionBarView.this.mExpandedActionView).onActionViewExpanded();
            }
            return true;
        }
        
        @Override
        public boolean flagActionItems() {
            return false;
        }
        
        @Override
        public int getId() {
            return 0;
        }
        
        @Override
        public MenuView getMenuView(final ViewGroup viewGroup) {
            return null;
        }
        
        @Override
        public void initForMenu(final Context context, final MenuBuilder mMenu) {
            if (this.mMenu != null && this.mCurrentExpandedItem != null) {
                this.mMenu.collapseItemActionView(this.mCurrentExpandedItem);
            }
            this.mMenu = mMenu;
        }
        
        @Override
        public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
        }
        
        @Override
        public void onRestoreInstanceState(final Parcelable parcelable) {
        }
        
        @Override
        public Parcelable onSaveInstanceState() {
            return null;
        }
        
        @Override
        public boolean onSubMenuSelected(final SubMenuBuilder subMenuBuilder) {
            return false;
        }
        
        @Override
        public void setCallback(final Callback callback) {
        }
        
        @Override
        public void updateMenuView(final boolean b) {
            if (this.mCurrentExpandedItem != null) {
                boolean b2 = false;
                if (this.mMenu != null) {
                    final int size = this.mMenu.size();
                    int n = 0;
                    while (true) {
                        b2 = b2;
                        if (n >= size) {
                            break;
                        }
                        if ((SupportMenuItem)this.mMenu.getItem(n) == this.mCurrentExpandedItem) {
                            b2 = true;
                            break;
                        }
                        ++n;
                    }
                }
                if (!b2) {
                    this.collapseItemActionView(this.mMenu, this.mCurrentExpandedItem);
                }
            }
        }
    }
    
    private static class HomeView extends FrameLayout
    {
        private Drawable mDefaultUpIndicator;
        private ImageView mIconView;
        private int mUpIndicatorRes;
        private ImageView mUpView;
        private int mUpWidth;
        
        public HomeView(final Context context) {
            this(context, null);
        }
        
        public HomeView(final Context context, final AttributeSet set) {
            super(context, set);
        }
        
        public boolean dispatchPopulateAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
            final CharSequence contentDescription = this.getContentDescription();
            if (!TextUtils.isEmpty(contentDescription)) {
                accessibilityEvent.getText().add(contentDescription);
            }
            return true;
        }
        
        public int getLeftOffset() {
            if (this.mUpView.getVisibility() == 8) {
                return this.mUpWidth;
            }
            return 0;
        }
        
        protected void onConfigurationChanged(final Configuration configuration) {
            super.onConfigurationChanged(configuration);
            if (this.mUpIndicatorRes != 0) {
                this.setUpIndicator(this.mUpIndicatorRes);
            }
        }
        
        protected void onFinishInflate() {
            this.mUpView = (ImageView)this.findViewById(R.id.up);
            this.mIconView = (ImageView)this.findViewById(R.id.home);
            this.mDefaultUpIndicator = this.mUpView.getDrawable();
        }
        
        protected void onLayout(final boolean b, int measuredHeight, int measuredHeight2, int max, int measuredWidth) {
            final int n = (measuredWidth - measuredHeight2) / 2;
            measuredHeight2 = 0;
            measuredWidth = measuredHeight;
            if (this.mUpView.getVisibility() != 8) {
                final FrameLayout$LayoutParams frameLayout$LayoutParams = (FrameLayout$LayoutParams)this.mUpView.getLayoutParams();
                measuredHeight2 = this.mUpView.getMeasuredHeight();
                measuredWidth = this.mUpView.getMeasuredWidth();
                final int n2 = n - measuredHeight2 / 2;
                this.mUpView.layout(0, n2, measuredWidth, n2 + measuredHeight2);
                measuredHeight2 = frameLayout$LayoutParams.leftMargin + measuredWidth + frameLayout$LayoutParams.rightMargin;
                measuredWidth = measuredHeight + measuredHeight2;
            }
            final FrameLayout$LayoutParams frameLayout$LayoutParams2 = (FrameLayout$LayoutParams)this.mIconView.getLayoutParams();
            measuredHeight = this.mIconView.getMeasuredHeight();
            final int measuredWidth2 = this.mIconView.getMeasuredWidth();
            max = (max - measuredWidth) / 2;
            measuredHeight2 += Math.max(frameLayout$LayoutParams2.leftMargin, max - measuredWidth2 / 2);
            max = Math.max(frameLayout$LayoutParams2.topMargin, n - measuredHeight / 2);
            this.mIconView.layout(measuredHeight2, max, measuredHeight2 + measuredWidth2, max + measuredHeight);
        }
        
        protected void onMeasure(int n, int n2) {
            this.measureChildWithMargins((View)this.mUpView, n, 0, n2, 0);
            final FrameLayout$LayoutParams frameLayout$LayoutParams = (FrameLayout$LayoutParams)this.mUpView.getLayoutParams();
            this.mUpWidth = frameLayout$LayoutParams.leftMargin + this.mUpView.getMeasuredWidth() + frameLayout$LayoutParams.rightMargin;
            int mUpWidth;
            if (this.mUpView.getVisibility() == 8) {
                mUpWidth = 0;
            }
            else {
                mUpWidth = this.mUpWidth;
            }
            final int topMargin = frameLayout$LayoutParams.topMargin;
            final int measuredHeight = this.mUpView.getMeasuredHeight();
            final int bottomMargin = frameLayout$LayoutParams.bottomMargin;
            this.measureChildWithMargins((View)this.mIconView, n, mUpWidth, n2, 0);
            final FrameLayout$LayoutParams frameLayout$LayoutParams2 = (FrameLayout$LayoutParams)this.mIconView.getLayoutParams();
            final int n3 = mUpWidth + (frameLayout$LayoutParams2.leftMargin + this.mIconView.getMeasuredWidth() + frameLayout$LayoutParams2.rightMargin);
            final int max = Math.max(topMargin + measuredHeight + bottomMargin, frameLayout$LayoutParams2.topMargin + this.mIconView.getMeasuredHeight() + frameLayout$LayoutParams2.bottomMargin);
            final int mode = View$MeasureSpec.getMode(n);
            final int mode2 = View$MeasureSpec.getMode(n2);
            n = View$MeasureSpec.getSize(n);
            n2 = View$MeasureSpec.getSize(n2);
            switch (mode) {
                default: {
                    n = n3;
                    break;
                }
                case Integer.MIN_VALUE: {
                    n = Math.min(n3, n);
                    break;
                }
                case 1073741824: {
                    break;
                }
            }
            switch (mode2) {
                default: {
                    n2 = max;
                    break;
                }
                case Integer.MIN_VALUE: {
                    n2 = Math.min(max, n2);
                    break;
                }
                case 1073741824: {
                    break;
                }
            }
            this.setMeasuredDimension(n, n2);
        }
        
        public void setIcon(final Drawable imageDrawable) {
            this.mIconView.setImageDrawable(imageDrawable);
        }
        
        public void setUp(final boolean b) {
            final ImageView mUpView = this.mUpView;
            int visibility;
            if (b) {
                visibility = 0;
            }
            else {
                visibility = 8;
            }
            mUpView.setVisibility(visibility);
        }
        
        public void setUpIndicator(final int mUpIndicatorRes) {
            this.mUpIndicatorRes = mUpIndicatorRes;
            final ImageView mUpView = this.mUpView;
            Drawable drawable;
            if (mUpIndicatorRes != 0) {
                drawable = this.getResources().getDrawable(mUpIndicatorRes);
            }
            else {
                drawable = null;
            }
            mUpView.setImageDrawable(drawable);
        }
        
        public void setUpIndicator(Drawable mDefaultUpIndicator) {
            final ImageView mUpView = this.mUpView;
            if (mDefaultUpIndicator == null) {
                mDefaultUpIndicator = this.mDefaultUpIndicator;
            }
            mUpView.setImageDrawable(mDefaultUpIndicator);
            this.mUpIndicatorRes = 0;
        }
    }
    
    static class SavedState extends View$BaseSavedState
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        int expandedMenuItemId;
        boolean isOverflowOpen;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<SavedState>() {
                public SavedState createFromParcel(final Parcel parcel) {
                    return new SavedState(parcel);
                }
                
                public SavedState[] newArray(final int n) {
                    return new SavedState[n];
                }
            };
        }
        
        private SavedState(final Parcel parcel) {
            super(parcel);
            this.expandedMenuItemId = parcel.readInt();
            this.isOverflowOpen = (parcel.readInt() != 0);
        }
        
        SavedState(final Parcelable parcelable) {
            super(parcelable);
        }
        
        public void writeToParcel(final Parcel parcel, int n) {
            super.writeToParcel(parcel, n);
            parcel.writeInt(this.expandedMenuItemId);
            if (this.isOverflowOpen) {
                n = 1;
            }
            else {
                n = 0;
            }
            parcel.writeInt(n);
        }
    }
}
