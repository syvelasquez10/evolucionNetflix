// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.graphics.drawable.LayerDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.graphics.ColorFilter;
import android.view.View;
import android.support.v4.content.ContextCompat;
import android.graphics.drawable.Drawable;
import android.graphics.PorterDuffColorFilter;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$drawable;
import android.os.Build$VERSION;
import android.util.SparseArray;
import android.content.res.ColorStateList;
import java.lang.ref.WeakReference;
import android.content.Context;
import java.util.WeakHashMap;
import android.graphics.PorterDuff$Mode;

public final class TintManager
{
    private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY;
    private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED;
    private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL;
    private static final TintManager$ColorFilterLruCache COLOR_FILTER_CACHE;
    private static final PorterDuff$Mode DEFAULT_MODE;
    private static final WeakHashMap<Context, TintManager> INSTANCE_CACHE;
    public static final boolean SHOULD_BE_USED;
    private static final int[] TINT_CHECKABLE_BUTTON_LIST;
    private static final int[] TINT_COLOR_CONTROL_NORMAL;
    private static final int[] TINT_COLOR_CONTROL_STATE_LIST;
    private final WeakReference<Context> mContextRef;
    private ColorStateList mDefaultColorStateList;
    private SparseArray<ColorStateList> mTintLists;
    
    static {
        SHOULD_BE_USED = (Build$VERSION.SDK_INT < 21);
        DEFAULT_MODE = PorterDuff$Mode.SRC_IN;
        INSTANCE_CACHE = new WeakHashMap<Context, TintManager>();
        COLOR_FILTER_CACHE = new TintManager$ColorFilterLruCache(6);
        COLORFILTER_TINT_COLOR_CONTROL_NORMAL = new int[] { R$drawable.abc_textfield_search_default_mtrl_alpha, R$drawable.abc_textfield_default_mtrl_alpha, R$drawable.abc_ab_share_pack_mtrl_alpha };
        TINT_COLOR_CONTROL_NORMAL = new int[] { R$drawable.abc_ic_ab_back_mtrl_am_alpha, R$drawable.abc_ic_go_search_api_mtrl_alpha, R$drawable.abc_ic_search_api_mtrl_alpha, R$drawable.abc_ic_commit_search_api_mtrl_alpha, R$drawable.abc_ic_clear_mtrl_alpha, R$drawable.abc_ic_menu_share_mtrl_alpha, R$drawable.abc_ic_menu_copy_mtrl_am_alpha, R$drawable.abc_ic_menu_cut_mtrl_alpha, R$drawable.abc_ic_menu_selectall_mtrl_alpha, R$drawable.abc_ic_menu_paste_mtrl_am_alpha, R$drawable.abc_ic_menu_moreoverflow_mtrl_alpha, R$drawable.abc_ic_voice_search_api_mtrl_alpha };
        COLORFILTER_COLOR_CONTROL_ACTIVATED = new int[] { R$drawable.abc_textfield_activated_mtrl_alpha, R$drawable.abc_textfield_search_activated_mtrl_alpha, R$drawable.abc_cab_background_top_mtrl_alpha, R$drawable.abc_text_cursor_material };
        COLORFILTER_COLOR_BACKGROUND_MULTIPLY = new int[] { R$drawable.abc_popup_background_mtrl_mult, R$drawable.abc_cab_background_internal_bg, R$drawable.abc_menu_hardkey_panel_mtrl_mult };
        TINT_COLOR_CONTROL_STATE_LIST = new int[] { R$drawable.abc_edit_text_material, R$drawable.abc_tab_indicator_material, R$drawable.abc_textfield_search_material, R$drawable.abc_spinner_mtrl_am_alpha, R$drawable.abc_spinner_textfield_background_material, R$drawable.abc_ratingbar_full_material, R$drawable.abc_switch_track_mtrl_alpha, R$drawable.abc_switch_thumb_material, R$drawable.abc_btn_default_mtrl_shape, R$drawable.abc_btn_borderless_material };
        TINT_CHECKABLE_BUTTON_LIST = new int[] { R$drawable.abc_btn_check_material, R$drawable.abc_btn_radio_material };
    }
    
    private TintManager(final Context context) {
        this.mContextRef = new WeakReference<Context>(context);
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
    
    private ColorStateList createButtonColorStateList(final Context context, int themeAttrColor) {
        themeAttrColor = ThemeUtils.getThemeAttrColor(context, themeAttrColor);
        final int themeAttrColor2 = ThemeUtils.getThemeAttrColor(context, R$attr.colorControlHighlight);
        return new ColorStateList(new int[][] { ThemeUtils.DISABLED_STATE_SET, ThemeUtils.PRESSED_STATE_SET, ThemeUtils.FOCUSED_STATE_SET, ThemeUtils.EMPTY_STATE_SET }, new int[] { ThemeUtils.getDisabledThemeAttrColor(context, R$attr.colorButtonNormal), ColorUtils.compositeColors(themeAttrColor2, themeAttrColor), ColorUtils.compositeColors(themeAttrColor2, themeAttrColor), themeAttrColor });
    }
    
    private ColorStateList createCheckableButtonColorStateList(final Context context) {
        return new ColorStateList(new int[][] { ThemeUtils.DISABLED_STATE_SET, ThemeUtils.CHECKED_STATE_SET, ThemeUtils.EMPTY_STATE_SET }, new int[] { ThemeUtils.getDisabledThemeAttrColor(context, R$attr.colorControlNormal), ThemeUtils.getThemeAttrColor(context, R$attr.colorControlActivated), ThemeUtils.getThemeAttrColor(context, R$attr.colorControlNormal) });
    }
    
    private ColorStateList createColoredButtonColorStateList(final Context context) {
        return this.createButtonColorStateList(context, R$attr.colorAccent);
    }
    
    private ColorStateList createDefaultButtonColorStateList(final Context context) {
        return this.createButtonColorStateList(context, R$attr.colorButtonNormal);
    }
    
    private ColorStateList createEditTextColorStateList(final Context context) {
        return new ColorStateList(new int[][] { ThemeUtils.DISABLED_STATE_SET, ThemeUtils.NOT_PRESSED_OR_FOCUSED_STATE_SET, ThemeUtils.EMPTY_STATE_SET }, new int[] { ThemeUtils.getDisabledThemeAttrColor(context, R$attr.colorControlNormal), ThemeUtils.getThemeAttrColor(context, R$attr.colorControlNormal), ThemeUtils.getThemeAttrColor(context, R$attr.colorControlActivated) });
    }
    
    private ColorStateList createSpinnerColorStateList(final Context context) {
        return new ColorStateList(new int[][] { ThemeUtils.DISABLED_STATE_SET, ThemeUtils.NOT_PRESSED_OR_FOCUSED_STATE_SET, ThemeUtils.EMPTY_STATE_SET }, new int[] { ThemeUtils.getDisabledThemeAttrColor(context, R$attr.colorControlNormal), ThemeUtils.getThemeAttrColor(context, R$attr.colorControlNormal), ThemeUtils.getThemeAttrColor(context, R$attr.colorControlActivated) });
    }
    
    private ColorStateList createSwitchThumbColorStateList(final Context context) {
        final int[][] array = new int[3][];
        final int[] array2 = new int[3];
        final ColorStateList themeAttrColorStateList = ThemeUtils.getThemeAttrColorStateList(context, R$attr.colorSwitchThumbNormal);
        if (themeAttrColorStateList != null && themeAttrColorStateList.isStateful()) {
            array[0] = ThemeUtils.DISABLED_STATE_SET;
            array2[0] = themeAttrColorStateList.getColorForState(array[0], 0);
            array[1] = ThemeUtils.CHECKED_STATE_SET;
            array2[1] = ThemeUtils.getThemeAttrColor(context, R$attr.colorControlActivated);
            array[2] = ThemeUtils.EMPTY_STATE_SET;
            array2[2] = themeAttrColorStateList.getDefaultColor();
        }
        else {
            array[0] = ThemeUtils.DISABLED_STATE_SET;
            array2[0] = ThemeUtils.getDisabledThemeAttrColor(context, R$attr.colorSwitchThumbNormal);
            array[1] = ThemeUtils.CHECKED_STATE_SET;
            array2[1] = ThemeUtils.getThemeAttrColor(context, R$attr.colorControlActivated);
            array[2] = ThemeUtils.EMPTY_STATE_SET;
            array2[2] = ThemeUtils.getThemeAttrColor(context, R$attr.colorSwitchThumbNormal);
        }
        return new ColorStateList(array, array2);
    }
    
    private ColorStateList createSwitchTrackColorStateList(final Context context) {
        return new ColorStateList(new int[][] { ThemeUtils.DISABLED_STATE_SET, ThemeUtils.CHECKED_STATE_SET, ThemeUtils.EMPTY_STATE_SET }, new int[] { ThemeUtils.getThemeAttrColor(context, 16842800, 0.1f), ThemeUtils.getThemeAttrColor(context, R$attr.colorControlActivated, 0.3f), ThemeUtils.getThemeAttrColor(context, 16842800, 0.3f) });
    }
    
    private static PorterDuffColorFilter createTintFilter(final ColorStateList list, final PorterDuff$Mode porterDuff$Mode, final int[] array) {
        if (list == null || porterDuff$Mode == null) {
            return null;
        }
        return getPorterDuffColorFilter(list.getColorForState(array, 0), porterDuff$Mode);
    }
    
    public static TintManager get(final Context context) {
        TintManager tintManager;
        if ((tintManager = TintManager.INSTANCE_CACHE.get(context)) == null) {
            tintManager = new TintManager(context);
            TintManager.INSTANCE_CACHE.put(context, tintManager);
        }
        return tintManager;
    }
    
    private ColorStateList getDefaultColorStateList(final Context context) {
        if (this.mDefaultColorStateList == null) {
            final int themeAttrColor = ThemeUtils.getThemeAttrColor(context, R$attr.colorControlNormal);
            final int themeAttrColor2 = ThemeUtils.getThemeAttrColor(context, R$attr.colorControlActivated);
            this.mDefaultColorStateList = new ColorStateList(new int[][] { ThemeUtils.DISABLED_STATE_SET, ThemeUtils.FOCUSED_STATE_SET, ThemeUtils.ACTIVATED_STATE_SET, ThemeUtils.PRESSED_STATE_SET, ThemeUtils.CHECKED_STATE_SET, ThemeUtils.SELECTED_STATE_SET, ThemeUtils.EMPTY_STATE_SET }, new int[] { ThemeUtils.getDisabledThemeAttrColor(context, R$attr.colorControlNormal), themeAttrColor2, themeAttrColor2, themeAttrColor2, themeAttrColor2, themeAttrColor2, themeAttrColor });
        }
        return this.mDefaultColorStateList;
    }
    
    public static Drawable getDrawable(final Context context, final int n) {
        if (isInTintList(n)) {
            return get(context).getDrawable(n);
        }
        return ContextCompat.getDrawable(context, n);
    }
    
    private static PorterDuffColorFilter getPorterDuffColorFilter(final int n, final PorterDuff$Mode porterDuff$Mode) {
        PorterDuffColorFilter value;
        if ((value = TintManager.COLOR_FILTER_CACHE.get(n, porterDuff$Mode)) == null) {
            value = new PorterDuffColorFilter(n, porterDuff$Mode);
            TintManager.COLOR_FILTER_CACHE.put(n, porterDuff$Mode, value);
        }
        return value;
    }
    
    private static boolean isInTintList(final int n) {
        return arrayContains(TintManager.TINT_COLOR_CONTROL_NORMAL, n) || arrayContains(TintManager.COLORFILTER_TINT_COLOR_CONTROL_NORMAL, n) || arrayContains(TintManager.COLORFILTER_COLOR_CONTROL_ACTIVATED, n) || arrayContains(TintManager.TINT_COLOR_CONTROL_STATE_LIST, n) || arrayContains(TintManager.COLORFILTER_COLOR_BACKGROUND_MULTIPLY, n) || arrayContains(TintManager.TINT_CHECKABLE_BUTTON_LIST, n) || n == R$drawable.abc_cab_background_top_material;
    }
    
    public static void tintViewBackground(final View view, final TintInfo tintInfo) {
        final Drawable background = view.getBackground();
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
                porterDuff$Mode = TintManager.DEFAULT_MODE;
            }
            background.setColorFilter((ColorFilter)createTintFilter(mTintList, porterDuff$Mode, view.getDrawableState()));
        }
        else {
            background.clearColorFilter();
        }
        if (Build$VERSION.SDK_INT <= 10) {
            view.invalidate();
        }
    }
    
    public Drawable getDrawable(final int n) {
        return this.getDrawable(n, false);
    }
    
    public Drawable getDrawable(final int n, final boolean b) {
        final Context context = this.mContextRef.get();
        if (context == null) {
            return null;
        }
        final Drawable drawable = ContextCompat.getDrawable(context, n);
        Drawable drawable2;
        if ((drawable2 = drawable) != null) {
            Drawable mutate = drawable;
            if (Build$VERSION.SDK_INT >= 8) {
                mutate = drawable.mutate();
            }
            final ColorStateList tintList = this.getTintList(n);
            if (tintList != null) {
                final Drawable wrap = DrawableCompat.wrap(mutate);
                DrawableCompat.setTintList(wrap, tintList);
                final PorterDuff$Mode tintMode = this.getTintMode(n);
                drawable2 = wrap;
                if (tintMode != null) {
                    DrawableCompat.setTintMode(wrap, tintMode);
                    drawable2 = wrap;
                }
            }
            else {
                if (n == R$drawable.abc_cab_background_top_material) {
                    return (Drawable)new LayerDrawable(new Drawable[] { this.getDrawable(R$drawable.abc_cab_background_internal_bg), this.getDrawable(R$drawable.abc_cab_background_top_mtrl_alpha) });
                }
                drawable2 = mutate;
                if (!this.tintDrawableUsingColorFilter(n, mutate)) {
                    drawable2 = mutate;
                    if (b) {
                        drawable2 = null;
                    }
                }
            }
        }
        return drawable2;
    }
    
    public final ColorStateList getTintList(final int n) {
        ColorStateList list = null;
        final Context context = this.mContextRef.get();
        ColorStateList list2;
        if (context == null) {
            list2 = null;
        }
        else {
            if (this.mTintLists != null) {
                list = (ColorStateList)this.mTintLists.get(n);
            }
            if (list != null) {
                return list;
            }
            if (n == R$drawable.abc_edit_text_material) {
                list = this.createEditTextColorStateList(context);
            }
            else if (n == R$drawable.abc_switch_track_mtrl_alpha) {
                list = this.createSwitchTrackColorStateList(context);
            }
            else if (n == R$drawable.abc_switch_thumb_material) {
                list = this.createSwitchThumbColorStateList(context);
            }
            else if (n == R$drawable.abc_btn_default_mtrl_shape || n == R$drawable.abc_btn_borderless_material) {
                list = this.createDefaultButtonColorStateList(context);
            }
            else if (n == R$drawable.abc_btn_colored_material) {
                list = this.createColoredButtonColorStateList(context);
            }
            else if (n == R$drawable.abc_spinner_mtrl_am_alpha || n == R$drawable.abc_spinner_textfield_background_material) {
                list = this.createSpinnerColorStateList(context);
            }
            else if (arrayContains(TintManager.TINT_COLOR_CONTROL_NORMAL, n)) {
                list = ThemeUtils.getThemeAttrColorStateList(context, R$attr.colorControlNormal);
            }
            else if (arrayContains(TintManager.TINT_COLOR_CONTROL_STATE_LIST, n)) {
                list = this.getDefaultColorStateList(context);
            }
            else if (arrayContains(TintManager.TINT_CHECKABLE_BUTTON_LIST, n)) {
                list = this.createCheckableButtonColorStateList(context);
            }
            list2 = list;
            if (list != null) {
                if (this.mTintLists == null) {
                    this.mTintLists = (SparseArray<ColorStateList>)new SparseArray();
                }
                this.mTintLists.append(n, (Object)list);
                return list;
            }
        }
        return list2;
    }
    
    final PorterDuff$Mode getTintMode(final int n) {
        PorterDuff$Mode multiply = null;
        if (n == R$drawable.abc_switch_thumb_material) {
            multiply = PorterDuff$Mode.MULTIPLY;
        }
        return multiply;
    }
    
    public final boolean tintDrawableUsingColorFilter(int round, final Drawable drawable) {
        final Context context = this.mContextRef.get();
        if (context == null) {
            return false;
        }
        PorterDuff$Mode porterDuff$Mode = TintManager.DEFAULT_MODE;
        int n;
        int n2;
        if (arrayContains(TintManager.COLORFILTER_TINT_COLOR_CONTROL_NORMAL, round)) {
            n = R$attr.colorControlNormal;
            n2 = 1;
            round = -1;
        }
        else if (arrayContains(TintManager.COLORFILTER_COLOR_CONTROL_ACTIVATED, round)) {
            n = R$attr.colorControlActivated;
            n2 = 1;
            round = -1;
        }
        else if (arrayContains(TintManager.COLORFILTER_COLOR_BACKGROUND_MULTIPLY, round)) {
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
        else {
            round = -1;
            n = 0;
            n2 = 0;
        }
        if (n2 != 0) {
            drawable.setColorFilter((ColorFilter)getPorterDuffColorFilter(ThemeUtils.getThemeAttrColor(context, n), porterDuff$Mode));
            if (round != -1) {
                drawable.setAlpha(round);
            }
            return true;
        }
        return false;
    }
}
