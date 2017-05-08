// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.toolbar;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import java.util.Map;
import com.facebook.react.uimanager.events.EventDispatcher;
import android.support.v7.widget.Toolbar$OnMenuItemClickListener;
import android.view.View$OnClickListener;
import com.facebook.react.uimanager.UIManagerModule;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import android.content.res.Resources$Theme;
import android.content.res.TypedArray;
import com.facebook.react.R$attr;
import android.content.Context;
import com.facebook.react.uimanager.ViewGroupManager;

public class ReactToolbarManager extends ViewGroupManager<ReactToolbar>
{
    private static final String REACT_CLASS = "ToolbarAndroid";
    
    private static int[] getDefaultColors(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: aconst_null    
        //     3: astore          6
        //     5: aload_0        
        //     6: invokevirtual   android/content/Context.getTheme:()Landroid/content/res/Resources$Theme;
        //     9: astore_0       
        //    10: aload_0        
        //    11: iconst_1       
        //    12: newarray        I
        //    14: dup            
        //    15: iconst_0       
        //    16: getstatic       com/facebook/react/R$attr.toolbarStyle:I
        //    19: iastore        
        //    20: invokevirtual   android/content/res/Resources$Theme.obtainStyledAttributes:([I)Landroid/content/res/TypedArray;
        //    23: astore          5
        //    25: aload_0        
        //    26: aload           5
        //    28: iconst_0       
        //    29: iconst_0       
        //    30: invokevirtual   android/content/res/TypedArray.getResourceId:(II)I
        //    33: iconst_2       
        //    34: newarray        I
        //    36: dup            
        //    37: iconst_0       
        //    38: getstatic       com/facebook/react/R$attr.titleTextAppearance:I
        //    41: iastore        
        //    42: dup            
        //    43: iconst_1       
        //    44: getstatic       com/facebook/react/R$attr.subtitleTextAppearance:I
        //    47: iastore        
        //    48: invokevirtual   android/content/res/Resources$Theme.obtainStyledAttributes:(I[I)Landroid/content/res/TypedArray;
        //    51: astore          4
        //    53: aload           4
        //    55: iconst_0       
        //    56: iconst_0       
        //    57: invokevirtual   android/content/res/TypedArray.getResourceId:(II)I
        //    60: istore_1       
        //    61: aload           4
        //    63: iconst_1       
        //    64: iconst_0       
        //    65: invokevirtual   android/content/res/TypedArray.getResourceId:(II)I
        //    68: istore_2       
        //    69: aload_0        
        //    70: iload_1        
        //    71: iconst_1       
        //    72: newarray        I
        //    74: dup            
        //    75: iconst_0       
        //    76: ldc             16842904
        //    78: iastore        
        //    79: invokevirtual   android/content/res/Resources$Theme.obtainStyledAttributes:(I[I)Landroid/content/res/TypedArray;
        //    82: astore          7
        //    84: aload_0        
        //    85: iload_2        
        //    86: iconst_1       
        //    87: newarray        I
        //    89: dup            
        //    90: iconst_0       
        //    91: ldc             16842904
        //    93: iastore        
        //    94: invokevirtual   android/content/res/Resources$Theme.obtainStyledAttributes:(I[I)Landroid/content/res/TypedArray;
        //    97: astore_0       
        //    98: aload_0        
        //    99: astore_3       
        //   100: aload           7
        //   102: iconst_0       
        //   103: ldc             -16777216
        //   105: invokevirtual   android/content/res/TypedArray.getColor:(II)I
        //   108: istore_1       
        //   109: aload_0        
        //   110: astore_3       
        //   111: aload_0        
        //   112: iconst_0       
        //   113: ldc             -16777216
        //   115: invokevirtual   android/content/res/TypedArray.getColor:(II)I
        //   118: istore_2       
        //   119: aload           5
        //   121: invokestatic    com/facebook/react/views/toolbar/ReactToolbarManager.recycleQuietly:(Landroid/content/res/TypedArray;)V
        //   124: aload           4
        //   126: invokestatic    com/facebook/react/views/toolbar/ReactToolbarManager.recycleQuietly:(Landroid/content/res/TypedArray;)V
        //   129: aload           7
        //   131: invokestatic    com/facebook/react/views/toolbar/ReactToolbarManager.recycleQuietly:(Landroid/content/res/TypedArray;)V
        //   134: aload_0        
        //   135: invokestatic    com/facebook/react/views/toolbar/ReactToolbarManager.recycleQuietly:(Landroid/content/res/TypedArray;)V
        //   138: iconst_2       
        //   139: newarray        I
        //   141: dup            
        //   142: iconst_0       
        //   143: iload_1        
        //   144: iastore        
        //   145: dup            
        //   146: iconst_1       
        //   147: iload_2        
        //   148: iastore        
        //   149: areturn        
        //   150: astore_0       
        //   151: aconst_null    
        //   152: astore_3       
        //   153: aconst_null    
        //   154: astore          4
        //   156: aconst_null    
        //   157: astore          5
        //   159: aload           5
        //   161: invokestatic    com/facebook/react/views/toolbar/ReactToolbarManager.recycleQuietly:(Landroid/content/res/TypedArray;)V
        //   164: aload           4
        //   166: invokestatic    com/facebook/react/views/toolbar/ReactToolbarManager.recycleQuietly:(Landroid/content/res/TypedArray;)V
        //   169: aload_3        
        //   170: invokestatic    com/facebook/react/views/toolbar/ReactToolbarManager.recycleQuietly:(Landroid/content/res/TypedArray;)V
        //   173: aload           6
        //   175: invokestatic    com/facebook/react/views/toolbar/ReactToolbarManager.recycleQuietly:(Landroid/content/res/TypedArray;)V
        //   178: aload_0        
        //   179: athrow         
        //   180: astore_0       
        //   181: aconst_null    
        //   182: astore_3       
        //   183: aconst_null    
        //   184: astore          4
        //   186: goto            159
        //   189: astore_0       
        //   190: aconst_null    
        //   191: astore_3       
        //   192: goto            159
        //   195: astore_0       
        //   196: aload_3        
        //   197: astore          6
        //   199: aload           7
        //   201: astore_3       
        //   202: goto            159
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  10     25     150    159    Any
        //  25     53     180    189    Any
        //  53     84     189    195    Any
        //  84     98     195    205    Any
        //  100    109    195    205    Any
        //  111    119    195    205    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 129, Size: 129
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
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
    
    private int[] getDefaultContentInsets(Context obtainStyledAttributes) {
        final Context context = null;
        final Context context2 = null;
        final Resources$Theme theme = obtainStyledAttributes.getTheme();
        TypedArray obtainStyledAttributes2;
        try {
            obtainStyledAttributes2 = theme.obtainStyledAttributes(new int[] { R$attr.toolbarStyle });
            obtainStyledAttributes = context;
            final Resources$Theme resources$Theme = theme;
            final TypedArray typedArray = obtainStyledAttributes2;
            final int n = 0;
            final int n2 = 0;
            final int n3 = typedArray.getResourceId(n, n2);
            final int n4 = 2;
            final int[] array = new int[n4];
            final int n5 = 0;
            final int n6 = R$attr.contentInsetStart;
            array[n5] = n6;
            final int n7 = 1;
            final int n8 = R$attr.contentInsetEnd;
            array[n7] = n8;
            final Object obtainStyledAttributes3 = resources$Theme.obtainStyledAttributes(n3, array);
            obtainStyledAttributes = (Context)obtainStyledAttributes3;
            final TypedArray typedArray2 = (TypedArray)obtainStyledAttributes3;
            final int n9 = 0;
            final int n10 = 0;
            final int n11 = typedArray2.getDimensionPixelSize(n9, n10);
            obtainStyledAttributes = (Context)obtainStyledAttributes3;
            final Context context3 = (Context)obtainStyledAttributes3;
            final int n12 = 1;
            final int n13 = 0;
            final int n14 = ((TypedArray)context3).getDimensionPixelSize(n12, n13);
            final TypedArray typedArray3 = obtainStyledAttributes2;
            recycleQuietly(typedArray3);
            final Context context4 = (Context)obtainStyledAttributes3;
            recycleQuietly((TypedArray)context4);
            final int n15 = 2;
            final int[] array2 = new int[n15];
            final int n16 = 0;
            final int n17 = n11;
            array2[n16] = n17;
            final int n18 = 1;
            final int n19 = n14;
            array2[n18] = n19;
            return array2;
        }
        finally {
            final Object o2;
            final Object o = o2;
            obtainStyledAttributes2 = null;
            obtainStyledAttributes = context2;
            final Object o3 = o;
        }
        while (true) {
            try {
                final Resources$Theme resources$Theme = theme;
                final TypedArray typedArray = obtainStyledAttributes2;
                final int n = 0;
                final int n2 = 0;
                final int n3 = typedArray.getResourceId(n, n2);
                final int n4 = 2;
                final int[] array = new int[n4];
                final int n5 = 0;
                final int n6 = R$attr.contentInsetStart;
                array[n5] = n6;
                final int n7 = 1;
                final int n8 = R$attr.contentInsetEnd;
                array[n7] = n8;
                final TypedArray typedArray2;
                final Object obtainStyledAttributes3 = typedArray2 = (TypedArray)(obtainStyledAttributes = (Context)resources$Theme.obtainStyledAttributes(n3, array));
                final int n9 = 0;
                final int n10 = 0;
                final int n11 = typedArray2.getDimensionPixelSize(n9, n10);
                obtainStyledAttributes = (Context)obtainStyledAttributes3;
                final Context context3 = (Context)obtainStyledAttributes3;
                final int n12 = 1;
                final int n13 = 0;
                final int n14 = ((TypedArray)context3).getDimensionPixelSize(n12, n13);
                final TypedArray typedArray3 = obtainStyledAttributes2;
                recycleQuietly(typedArray3);
                final Context context4 = (Context)obtainStyledAttributes3;
                recycleQuietly((TypedArray)context4);
                final int n15 = 2;
                final int[] array2 = new int[n15];
                final int n16 = 0;
                final int n17 = n11;
                array2[n16] = n17;
                final int n18 = 1;
                final int n19 = n14;
                array2[n18] = n19;
                return array2;
                recycleQuietly(obtainStyledAttributes2);
                recycleQuietly((TypedArray)obtainStyledAttributes);
                throw;
            }
            finally {
                continue;
            }
            break;
        }
    }
    
    private static void recycleQuietly(final TypedArray typedArray) {
        if (typedArray != null) {
            typedArray.recycle();
        }
    }
    
    protected void addEventEmitters(final ThemedReactContext themedReactContext, final ReactToolbar reactToolbar) {
        final EventDispatcher eventDispatcher = themedReactContext.getNativeModule(UIManagerModule.class).getEventDispatcher();
        reactToolbar.setNavigationOnClickListener((View$OnClickListener)new ReactToolbarManager$1(this, eventDispatcher, reactToolbar));
        reactToolbar.setOnMenuItemClickListener(new ReactToolbarManager$2(this, eventDispatcher, reactToolbar));
    }
    
    @Override
    protected ReactToolbar createViewInstance(final ThemedReactContext themedReactContext) {
        return new ReactToolbar((Context)themedReactContext);
    }
    
    @Override
    public Map<String, Object> getExportedViewConstants() {
        return (Map<String, Object>)MapBuilder.of("ShowAsAction", MapBuilder.of("never", 0, "always", 2, "ifRoom", 1));
    }
    
    @Override
    public String getName() {
        return "ToolbarAndroid";
    }
    
    @Override
    public boolean needsCustomLayoutForChildren() {
        return true;
    }
    
    @ReactProp(name = "nativeActions")
    public void setActions(final ReactToolbar reactToolbar, final ReadableArray actions) {
        reactToolbar.setActions(actions);
    }
    
    @ReactProp(defaultFloat = Float.NaN, name = "contentInsetEnd")
    public void setContentInsetEnd(final ReactToolbar reactToolbar, final float n) {
        int round;
        if (Float.isNaN(n)) {
            round = this.getDefaultContentInsets(reactToolbar.getContext())[1];
        }
        else {
            round = Math.round(PixelUtil.toPixelFromDIP(n));
        }
        reactToolbar.setContentInsetsRelative(reactToolbar.getContentInsetStart(), round);
    }
    
    @ReactProp(defaultFloat = Float.NaN, name = "contentInsetStart")
    public void setContentInsetStart(final ReactToolbar reactToolbar, final float n) {
        int round;
        if (Float.isNaN(n)) {
            round = this.getDefaultContentInsets(reactToolbar.getContext())[0];
        }
        else {
            round = Math.round(PixelUtil.toPixelFromDIP(n));
        }
        reactToolbar.setContentInsetsRelative(round, reactToolbar.getContentInsetEnd());
    }
    
    @ReactProp(name = "logo")
    public void setLogo(final ReactToolbar reactToolbar, final ReadableMap logoSource) {
        reactToolbar.setLogoSource(logoSource);
    }
    
    @ReactProp(name = "navIcon")
    public void setNavIcon(final ReactToolbar reactToolbar, final ReadableMap navIconSource) {
        reactToolbar.setNavIconSource(navIconSource);
    }
    
    @ReactProp(name = "overflowIcon")
    public void setOverflowIcon(final ReactToolbar reactToolbar, final ReadableMap overflowIconSource) {
        reactToolbar.setOverflowIconSource(overflowIconSource);
    }
    
    @ReactProp(name = "rtl")
    public void setRtl(final ReactToolbar reactToolbar, final boolean b) {
        int layoutDirection;
        if (b) {
            layoutDirection = 1;
        }
        else {
            layoutDirection = 0;
        }
        reactToolbar.setLayoutDirection(layoutDirection);
    }
    
    @ReactProp(name = "subtitle")
    public void setSubtitle(final ReactToolbar reactToolbar, final String subtitle) {
        reactToolbar.setSubtitle(subtitle);
    }
    
    @ReactProp(customType = "Color", name = "subtitleColor")
    public void setSubtitleColor(final ReactToolbar reactToolbar, final Integer n) {
        final int[] defaultColors = getDefaultColors(reactToolbar.getContext());
        if (n != null) {
            reactToolbar.setSubtitleTextColor(n);
            return;
        }
        reactToolbar.setSubtitleTextColor(defaultColors[1]);
    }
    
    @ReactProp(name = "title")
    public void setTitle(final ReactToolbar reactToolbar, final String title) {
        reactToolbar.setTitle(title);
    }
    
    @ReactProp(customType = "Color", name = "titleColor")
    public void setTitleColor(final ReactToolbar reactToolbar, final Integer n) {
        final int[] defaultColors = getDefaultColors(reactToolbar.getContext());
        if (n != null) {
            reactToolbar.setTitleTextColor(n);
            return;
        }
        reactToolbar.setTitleTextColor(defaultColors[0]);
    }
}
