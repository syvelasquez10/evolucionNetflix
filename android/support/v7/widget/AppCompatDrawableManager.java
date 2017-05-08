// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v7.content.res.AppCompatResources;
import android.support.v7.appcompat.R$color;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.graphics.ColorFilter;
import android.util.AttributeSet;
import android.content.res.XmlResourceParser;
import android.content.res.Resources;
import android.util.Log;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParser;
import android.util.Xml;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.os.Build$VERSION;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.appcompat.R$attr;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R$drawable;
import android.util.TypedValue;
import android.content.res.ColorStateList;
import android.util.SparseArray;
import android.graphics.drawable.Drawable$ConstantState;
import java.lang.ref.WeakReference;
import android.support.v4.util.LongSparseArray;
import android.content.Context;
import java.util.WeakHashMap;
import android.support.v4.util.ArrayMap;
import android.graphics.PorterDuff$Mode;

public final class AppCompatDrawableManager
{
    private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY;
    private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED;
    private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL;
    private static final AppCompatDrawableManager$ColorFilterLruCache COLOR_FILTER_CACHE;
    private static final PorterDuff$Mode DEFAULT_MODE;
    private static AppCompatDrawableManager INSTANCE;
    private static final int[] TINT_CHECKABLE_BUTTON_LIST;
    private static final int[] TINT_COLOR_CONTROL_NORMAL;
    private static final int[] TINT_COLOR_CONTROL_STATE_LIST;
    private ArrayMap<String, AppCompatDrawableManager$InflateDelegate> mDelegates;
    private final Object mDrawableCacheLock;
    private final WeakHashMap<Context, LongSparseArray<WeakReference<Drawable$ConstantState>>> mDrawableCaches;
    private boolean mHasCheckedVectorDrawableSetup;
    private SparseArray<String> mKnownDrawableIdTags;
    private WeakHashMap<Context, SparseArray<ColorStateList>> mTintLists;
    private TypedValue mTypedValue;
    
    static {
        DEFAULT_MODE = PorterDuff$Mode.SRC_IN;
        COLOR_FILTER_CACHE = new AppCompatDrawableManager$ColorFilterLruCache(6);
        COLORFILTER_TINT_COLOR_CONTROL_NORMAL = new int[] { R$drawable.abc_textfield_search_default_mtrl_alpha, R$drawable.abc_textfield_default_mtrl_alpha, R$drawable.abc_ab_share_pack_mtrl_alpha };
        TINT_COLOR_CONTROL_NORMAL = new int[] { R$drawable.abc_ic_commit_search_api_mtrl_alpha, R$drawable.abc_seekbar_tick_mark_material, R$drawable.abc_ic_menu_share_mtrl_alpha, R$drawable.abc_ic_menu_copy_mtrl_am_alpha, R$drawable.abc_ic_menu_cut_mtrl_alpha, R$drawable.abc_ic_menu_selectall_mtrl_alpha, R$drawable.abc_ic_menu_paste_mtrl_am_alpha };
        COLORFILTER_COLOR_CONTROL_ACTIVATED = new int[] { R$drawable.abc_textfield_activated_mtrl_alpha, R$drawable.abc_textfield_search_activated_mtrl_alpha, R$drawable.abc_cab_background_top_mtrl_alpha, R$drawable.abc_text_cursor_material, R$drawable.abc_text_select_handle_left_mtrl_dark, R$drawable.abc_text_select_handle_middle_mtrl_dark, R$drawable.abc_text_select_handle_right_mtrl_dark, R$drawable.abc_text_select_handle_left_mtrl_light, R$drawable.abc_text_select_handle_middle_mtrl_light, R$drawable.abc_text_select_handle_right_mtrl_light };
        COLORFILTER_COLOR_BACKGROUND_MULTIPLY = new int[] { R$drawable.abc_popup_background_mtrl_mult, R$drawable.abc_cab_background_internal_bg, R$drawable.abc_menu_hardkey_panel_mtrl_mult };
        TINT_COLOR_CONTROL_STATE_LIST = new int[] { R$drawable.abc_tab_indicator_material, R$drawable.abc_textfield_search_material };
        TINT_CHECKABLE_BUTTON_LIST = new int[] { R$drawable.abc_btn_check_material, R$drawable.abc_btn_radio_material };
    }
    
    public AppCompatDrawableManager() {
        this.mDrawableCacheLock = new Object();
        this.mDrawableCaches = new WeakHashMap<Context, LongSparseArray<WeakReference<Drawable$ConstantState>>>(0);
    }
    
    private void addDelegate(final String s, final AppCompatDrawableManager$InflateDelegate appCompatDrawableManager$InflateDelegate) {
        if (this.mDelegates == null) {
            this.mDelegates = new ArrayMap<String, AppCompatDrawableManager$InflateDelegate>();
        }
        this.mDelegates.put(s, appCompatDrawableManager$InflateDelegate);
    }
    
    private boolean addDrawableToCache(final Context context, final long n, final Drawable drawable) {
        final Drawable$ConstantState constantState = drawable.getConstantState();
        if (constantState != null) {
            synchronized (this.mDrawableCacheLock) {
                LongSparseArray<WeakReference<Drawable$ConstantState>> longSparseArray;
                if ((longSparseArray = this.mDrawableCaches.get(context)) == null) {
                    longSparseArray = new LongSparseArray<WeakReference<Drawable$ConstantState>>();
                    this.mDrawableCaches.put(context, longSparseArray);
                }
                longSparseArray.put(n, new WeakReference<Drawable$ConstantState>(constantState));
                return true;
            }
        }
        return false;
    }
    
    private void addTintListToCache(final Context context, final int n, final ColorStateList list) {
        if (this.mTintLists == null) {
            this.mTintLists = new WeakHashMap<Context, SparseArray<ColorStateList>>();
        }
        SparseArray sparseArray;
        if ((sparseArray = this.mTintLists.get(context)) == null) {
            sparseArray = new SparseArray();
            this.mTintLists.put(context, (SparseArray<ColorStateList>)sparseArray);
        }
        sparseArray.append(n, (Object)list);
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
    
    private void checkVectorDrawableSetup(final Context context) {
        if (!this.mHasCheckedVectorDrawableSetup) {
            this.mHasCheckedVectorDrawableSetup = true;
            final Drawable drawable = this.getDrawable(context, R$drawable.abc_vector_test);
            if (drawable == null || !isVectorDrawable(drawable)) {
                this.mHasCheckedVectorDrawableSetup = false;
                throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
            }
        }
    }
    
    private ColorStateList createBorderlessButtonColorStateList(final Context context) {
        return this.createButtonColorStateList(context, 0);
    }
    
    private ColorStateList createButtonColorStateList(final Context context, final int n) {
        final int themeAttrColor = ThemeUtils.getThemeAttrColor(context, R$attr.colorControlHighlight);
        return new ColorStateList(new int[][] { ThemeUtils.DISABLED_STATE_SET, ThemeUtils.PRESSED_STATE_SET, ThemeUtils.FOCUSED_STATE_SET, ThemeUtils.EMPTY_STATE_SET }, new int[] { ThemeUtils.getDisabledThemeAttrColor(context, R$attr.colorButtonNormal), ColorUtils.compositeColors(themeAttrColor, n), ColorUtils.compositeColors(themeAttrColor, n), n });
    }
    
    private static long createCacheKey(final TypedValue typedValue) {
        return typedValue.assetCookie << 32 | typedValue.data;
    }
    
    private ColorStateList createColoredButtonColorStateList(final Context context) {
        return this.createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, R$attr.colorAccent));
    }
    
    private ColorStateList createDefaultButtonColorStateList(final Context context) {
        return this.createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, R$attr.colorButtonNormal));
    }
    
    private Drawable createDrawableIfNeeded(final Context context, final int n) {
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        final TypedValue mTypedValue = this.mTypedValue;
        context.getResources().getValue(n, mTypedValue, true);
        final long cacheKey = createCacheKey(mTypedValue);
        Object cachedDrawable = this.getCachedDrawable(context, cacheKey);
        Drawable drawable;
        if (cachedDrawable != null) {
            drawable = (Drawable)cachedDrawable;
        }
        else {
            if (n == R$drawable.abc_cab_background_top_material) {
                cachedDrawable = new LayerDrawable(new Drawable[] { this.getDrawable(context, R$drawable.abc_cab_background_internal_bg), this.getDrawable(context, R$drawable.abc_cab_background_top_mtrl_alpha) });
            }
            if ((drawable = (Drawable)cachedDrawable) != null) {
                ((Drawable)cachedDrawable).setChangingConfigurations(mTypedValue.changingConfigurations);
                this.addDrawableToCache(context, cacheKey, (Drawable)cachedDrawable);
                return (Drawable)cachedDrawable;
            }
        }
        return drawable;
    }
    
    private static PorterDuffColorFilter createTintFilter(final ColorStateList list, final PorterDuff$Mode porterDuff$Mode, final int[] array) {
        if (list == null || porterDuff$Mode == null) {
            return null;
        }
        return getPorterDuffColorFilter(list.getColorForState(array, 0), porterDuff$Mode);
    }
    
    public static AppCompatDrawableManager get() {
        if (AppCompatDrawableManager.INSTANCE == null) {
            installDefaultInflateDelegates(AppCompatDrawableManager.INSTANCE = new AppCompatDrawableManager());
        }
        return AppCompatDrawableManager.INSTANCE;
    }
    
    private Drawable getCachedDrawable(final Context context, final long n) {
        Label_0090: {
            final LongSparseArray<WeakReference<Drawable$ConstantState>> longSparseArray;
            synchronized (this.mDrawableCacheLock) {
                longSparseArray = this.mDrawableCaches.get(context);
                if (longSparseArray == null) {
                    return null;
                }
                final WeakReference<Drawable$ConstantState> weakReference = longSparseArray.get(n);
                if (weakReference == null) {
                    break Label_0090;
                }
                final Drawable$ConstantState drawable$ConstantState = weakReference.get();
                if (drawable$ConstantState != null) {
                    return drawable$ConstantState.newDrawable(context.getResources());
                }
            }
            longSparseArray.delete(n);
        }
        // monitorexit(o)
        return null;
    }
    
    public static PorterDuffColorFilter getPorterDuffColorFilter(final int n, final PorterDuff$Mode porterDuff$Mode) {
        PorterDuffColorFilter value;
        if ((value = AppCompatDrawableManager.COLOR_FILTER_CACHE.get(n, porterDuff$Mode)) == null) {
            value = new PorterDuffColorFilter(n, porterDuff$Mode);
            AppCompatDrawableManager.COLOR_FILTER_CACHE.put(n, porterDuff$Mode, value);
        }
        return value;
    }
    
    private ColorStateList getTintListFromCache(final Context context, final int n) {
        if (this.mTintLists == null) {
            return null;
        }
        final SparseArray<ColorStateList> sparseArray = this.mTintLists.get(context);
        if (sparseArray != null) {
            return (ColorStateList)sparseArray.get(n);
        }
        return null;
    }
    
    static PorterDuff$Mode getTintMode(final int n) {
        PorterDuff$Mode multiply = null;
        if (n == R$drawable.abc_switch_thumb_material) {
            multiply = PorterDuff$Mode.MULTIPLY;
        }
        return multiply;
    }
    
    private static void installDefaultInflateDelegates(final AppCompatDrawableManager appCompatDrawableManager) {
        if (Build$VERSION.SDK_INT < 24) {
            appCompatDrawableManager.addDelegate("vector", new AppCompatDrawableManager$VdcInflateDelegate());
            if (Build$VERSION.SDK_INT >= 11) {
                appCompatDrawableManager.addDelegate("animated-vector", new AppCompatDrawableManager$AvdcInflateDelegate());
            }
        }
    }
    
    private static boolean isVectorDrawable(final Drawable drawable) {
        return drawable instanceof VectorDrawableCompat || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }
    
    private Drawable loadDrawableFromDelegates(final Context context, final int n) {
        if (this.mDelegates == null || this.mDelegates.isEmpty()) {
            return null;
        }
        Label_0082: {
            if (this.mKnownDrawableIdTags == null) {
                this.mKnownDrawableIdTags = (SparseArray<String>)new SparseArray();
                break Label_0082;
            }
            final String s = (String)this.mKnownDrawableIdTags.get(n);
            if (!"appcompat_skip_skip".equals(s) && (s == null || this.mDelegates.get(s) != null)) {
                break Label_0082;
            }
            return null;
        }
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        final TypedValue mTypedValue = this.mTypedValue;
        final Resources resources = context.getResources();
        resources.getValue(n, mTypedValue, true);
        final long cacheKey = createCacheKey(mTypedValue);
        final Drawable cachedDrawable = this.getCachedDrawable(context, cacheKey);
        if (cachedDrawable != null) {
            return cachedDrawable;
        }
        Drawable drawable2 = cachedDrawable;
        Drawable drawable3 = null;
        Label_0260: {
            Label_0257: {
                if (mTypedValue.string != null) {
                    drawable2 = cachedDrawable;
                    if (mTypedValue.string.toString().endsWith(".xml")) {
                        drawable2 = cachedDrawable;
                        XmlResourceParser xml = null;
                        AttributeSet attributeSet = null;
                        Label_0280: {
                            try {
                                xml = resources.getXml(n);
                                drawable2 = cachedDrawable;
                                attributeSet = Xml.asAttributeSet((XmlPullParser)xml);
                                int next;
                                do {
                                    drawable2 = cachedDrawable;
                                    next = ((XmlPullParser)xml).next();
                                } while (next != 2 && next != 1);
                                if (next != 2) {
                                    drawable2 = cachedDrawable;
                                    throw new XmlPullParserException("No start tag found");
                                }
                                break Label_0280;
                            }
                            catch (Exception ex) {
                                Log.e("AppCompatDrawableManager", "Exception while inflating drawable", (Throwable)ex);
                            }
                            break Label_0257;
                        }
                        final String name = ((XmlPullParser)xml).getName();
                        this.mKnownDrawableIdTags.append(n, (Object)name);
                        final AppCompatDrawableManager$InflateDelegate appCompatDrawableManager$InflateDelegate = this.mDelegates.get(name);
                        Drawable fromXmlInner = cachedDrawable;
                        if (appCompatDrawableManager$InflateDelegate != null) {
                            fromXmlInner = appCompatDrawableManager$InflateDelegate.createFromXmlInner(context, (XmlPullParser)xml, attributeSet, context.getTheme());
                        }
                        if (fromXmlInner != null) {
                            fromXmlInner.setChangingConfigurations(mTypedValue.changingConfigurations);
                            if (this.addDrawableToCache(context, cacheKey, fromXmlInner)) {}
                        }
                        drawable3 = fromXmlInner;
                        break Label_0260;
                    }
                }
            }
            drawable3 = drawable2;
        }
        final Drawable drawable = drawable3;
        if (drawable3 == null) {
            this.mKnownDrawableIdTags.append(n, (Object)"appcompat_skip_skip");
            return drawable3;
        }
        return drawable;
    }
    
    private static void setPorterDuffColorFilter(final Drawable drawable, final int n, final PorterDuff$Mode porterDuff$Mode) {
        Drawable mutate = drawable;
        if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
            mutate = drawable.mutate();
        }
        PorterDuff$Mode default_MODE;
        if ((default_MODE = porterDuff$Mode) == null) {
            default_MODE = AppCompatDrawableManager.DEFAULT_MODE;
        }
        mutate.setColorFilter((ColorFilter)getPorterDuffColorFilter(n, default_MODE));
    }
    
    private Drawable tintDrawable(final Context context, final int n, final boolean b, final Drawable drawable) {
        final ColorStateList tintList = this.getTintList(context, n);
        Drawable drawable2;
        if (tintList != null) {
            Drawable mutate = drawable;
            if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
                mutate = drawable.mutate();
            }
            final Drawable wrap = DrawableCompat.wrap(mutate);
            DrawableCompat.setTintList(wrap, tintList);
            final PorterDuff$Mode tintMode = getTintMode(n);
            drawable2 = wrap;
            if (tintMode != null) {
                DrawableCompat.setTintMode(wrap, tintMode);
                drawable2 = wrap;
            }
        }
        else {
            if (n == R$drawable.abc_seekbar_track_material) {
                final LayerDrawable layerDrawable = (LayerDrawable)drawable;
                setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908288), ThemeUtils.getThemeAttrColor(context, R$attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context, R$attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, R$attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                return drawable;
            }
            if (n == R$drawable.abc_ratingbar_material || n == R$drawable.abc_ratingbar_indicator_material || n == R$drawable.abc_ratingbar_small_material) {
                final LayerDrawable layerDrawable2 = (LayerDrawable)drawable;
                setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908288), ThemeUtils.getDisabledThemeAttrColor(context, R$attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context, R$attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, R$attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                return drawable;
            }
            drawable2 = drawable;
            if (!tintDrawableUsingColorFilter(context, n, drawable)) {
                drawable2 = drawable;
                if (b) {
                    return null;
                }
            }
        }
        return drawable2;
    }
    
    static void tintDrawable(final Drawable drawable, final TintInfo tintInfo, final int[] array) {
        if (DrawableUtils.canSafelyMutateDrawable(drawable) && drawable.mutate() != drawable) {
            Log.d("AppCompatDrawableManager", "Mutated drawable is not the same instance as the input.");
        }
        else {
            if (tintInfo.mHasTintList || tintInfo.mHasTintMode) {
                ColorStateList mTintList;
                if (tintInfo.mHasTintList) {
                    mTintList = tintInfo.mTintList;
                }
                else {
                    mTintList = null;
                }
                PorterDuff$Mode porterDuff$Mode;
                if (tintInfo.mHasTintMode) {
                    porterDuff$Mode = tintInfo.mTintMode;
                }
                else {
                    porterDuff$Mode = AppCompatDrawableManager.DEFAULT_MODE;
                }
                drawable.setColorFilter((ColorFilter)createTintFilter(mTintList, porterDuff$Mode, array));
            }
            else {
                drawable.clearColorFilter();
            }
            if (Build$VERSION.SDK_INT <= 23) {
                drawable.invalidateSelf();
            }
        }
    }
    
    static boolean tintDrawableUsingColorFilter(final Context context, int round, final Drawable drawable) {
        PorterDuff$Mode porterDuff$Mode = AppCompatDrawableManager.DEFAULT_MODE;
        int n;
        int n2;
        if (arrayContains(AppCompatDrawableManager.COLORFILTER_TINT_COLOR_CONTROL_NORMAL, round)) {
            n = R$attr.colorControlNormal;
            n2 = 1;
            round = -1;
        }
        else if (arrayContains(AppCompatDrawableManager.COLORFILTER_COLOR_CONTROL_ACTIVATED, round)) {
            n = R$attr.colorControlActivated;
            n2 = 1;
            round = -1;
        }
        else if (arrayContains(AppCompatDrawableManager.COLORFILTER_COLOR_BACKGROUND_MULTIPLY, round)) {
            porterDuff$Mode = PorterDuff$Mode.MULTIPLY;
            n2 = 1;
            n = 16842801;
            round = -1;
        }
        else if (round == R$drawable.abc_list_divider_mtrl_alpha) {
            n = 16842800;
            round = Math.round(40.8f);
            n2 = 1;
        }
        else if (round == R$drawable.abc_dialog_material_background) {
            n = 16842801;
            n2 = 1;
            round = -1;
        }
        else {
            round = -1;
            n = 0;
            n2 = 0;
        }
        if (n2 != 0) {
            Drawable mutate = drawable;
            if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
                mutate = drawable.mutate();
            }
            mutate.setColorFilter((ColorFilter)getPorterDuffColorFilter(ThemeUtils.getThemeAttrColor(context, n), porterDuff$Mode));
            if (round != -1) {
                mutate.setAlpha(round);
            }
            return true;
        }
        return false;
    }
    
    public Drawable getDrawable(final Context context, final int n) {
        return this.getDrawable(context, n, false);
    }
    
    Drawable getDrawable(final Context context, final int n, final boolean b) {
        this.checkVectorDrawableSetup(context);
        Drawable drawable;
        if ((drawable = this.loadDrawableFromDelegates(context, n)) == null) {
            drawable = this.createDrawableIfNeeded(context, n);
        }
        Drawable drawable2;
        if ((drawable2 = drawable) == null) {
            drawable2 = ContextCompat.getDrawable(context, n);
        }
        Drawable tintDrawable;
        if ((tintDrawable = drawable2) != null) {
            tintDrawable = this.tintDrawable(context, n, b, drawable2);
        }
        if (tintDrawable != null) {
            DrawableUtils.fixDrawable(tintDrawable);
        }
        return tintDrawable;
    }
    
    ColorStateList getTintList(final Context context, final int n) {
        ColorStateList tintListFromCache;
        ColorStateList list = tintListFromCache = this.getTintListFromCache(context, n);
        if (list == null) {
            if (n == R$drawable.abc_edit_text_material) {
                list = AppCompatResources.getColorStateList(context, R$color.abc_tint_edittext);
            }
            else if (n == R$drawable.abc_switch_track_mtrl_alpha) {
                list = AppCompatResources.getColorStateList(context, R$color.abc_tint_switch_track);
            }
            else if (n == R$drawable.abc_switch_thumb_material) {
                list = AppCompatResources.getColorStateList(context, R$color.abc_tint_switch_thumb);
            }
            else if (n == R$drawable.abc_btn_default_mtrl_shape) {
                list = this.createDefaultButtonColorStateList(context);
            }
            else if (n == R$drawable.abc_btn_borderless_material) {
                list = this.createBorderlessButtonColorStateList(context);
            }
            else if (n == R$drawable.abc_btn_colored_material) {
                list = this.createColoredButtonColorStateList(context);
            }
            else if (n == R$drawable.abc_spinner_mtrl_am_alpha || n == R$drawable.abc_spinner_textfield_background_material) {
                list = AppCompatResources.getColorStateList(context, R$color.abc_tint_spinner);
            }
            else if (arrayContains(AppCompatDrawableManager.TINT_COLOR_CONTROL_NORMAL, n)) {
                list = ThemeUtils.getThemeAttrColorStateList(context, R$attr.colorControlNormal);
            }
            else if (arrayContains(AppCompatDrawableManager.TINT_COLOR_CONTROL_STATE_LIST, n)) {
                list = AppCompatResources.getColorStateList(context, R$color.abc_tint_default);
            }
            else if (arrayContains(AppCompatDrawableManager.TINT_CHECKABLE_BUTTON_LIST, n)) {
                list = AppCompatResources.getColorStateList(context, R$color.abc_tint_btn_checkable);
            }
            else if (n == R$drawable.abc_seekbar_thumb_material) {
                list = AppCompatResources.getColorStateList(context, R$color.abc_tint_seek_thumb);
            }
            if ((tintListFromCache = list) != null) {
                this.addTintListToCache(context, n, list);
                tintListFromCache = list;
            }
        }
        return tintListFromCache;
    }
    
    public void onConfigurationChanged(final Context context) {
        synchronized (this.mDrawableCacheLock) {
            final LongSparseArray<WeakReference<Drawable$ConstantState>> longSparseArray = this.mDrawableCaches.get(context);
            if (longSparseArray != null) {
                longSparseArray.clear();
            }
        }
    }
    
    Drawable onDrawableLoadedFromResources(final Context context, final VectorEnabledTintResources vectorEnabledTintResources, final int n) {
        Drawable drawable;
        if ((drawable = this.loadDrawableFromDelegates(context, n)) == null) {
            drawable = vectorEnabledTintResources.superGetDrawable(n);
        }
        if (drawable != null) {
            return this.tintDrawable(context, n, false, drawable);
        }
        return null;
    }
}
