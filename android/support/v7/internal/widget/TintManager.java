// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.graphics.ColorFilter;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$drawable;
import android.util.TypedValue;
import android.content.res.Resources;
import android.content.res.ColorStateList;
import android.content.Context;
import android.graphics.PorterDuff$Mode;

public class TintManager
{
    private static final TintManager$ColorFilterLruCache COLOR_FILTER_CACHE;
    private static final int[] CONTAINERS_WITH_TINT_CHILDREN;
    private static final boolean DEBUG = false;
    static final PorterDuff$Mode DEFAULT_MODE;
    private static final String TAG;
    private static final int[] TINT_COLOR_BACKGROUND_MULTIPLY;
    private static final int[] TINT_COLOR_CONTROL_ACTIVATED;
    private static final int[] TINT_COLOR_CONTROL_NORMAL;
    private static final int[] TINT_COLOR_CONTROL_STATE_LIST;
    private final Context mContext;
    private ColorStateList mDefaultColorStateList;
    private final Resources mResources;
    private ColorStateList mSwitchThumbStateList;
    private ColorStateList mSwitchTrackStateList;
    private final TypedValue mTypedValue;
    
    static {
        TAG = TintManager.class.getSimpleName();
        DEFAULT_MODE = PorterDuff$Mode.SRC_IN;
        COLOR_FILTER_CACHE = new TintManager$ColorFilterLruCache(6);
        TINT_COLOR_CONTROL_NORMAL = new int[] { R$drawable.abc_ic_ab_back_mtrl_am_alpha, R$drawable.abc_ic_go_search_api_mtrl_alpha, R$drawable.abc_ic_search_api_mtrl_alpha, R$drawable.abc_ic_commit_search_api_mtrl_alpha, R$drawable.abc_ic_clear_mtrl_alpha, R$drawable.abc_ic_menu_share_mtrl_alpha, R$drawable.abc_ic_menu_copy_mtrl_am_alpha, R$drawable.abc_ic_menu_cut_mtrl_alpha, R$drawable.abc_ic_menu_selectall_mtrl_alpha, R$drawable.abc_ic_menu_paste_mtrl_am_alpha, R$drawable.abc_ic_menu_moreoverflow_mtrl_alpha, R$drawable.abc_ic_voice_search_api_mtrl_alpha, R$drawable.abc_textfield_search_default_mtrl_alpha, R$drawable.abc_textfield_default_mtrl_alpha };
        TINT_COLOR_CONTROL_ACTIVATED = new int[] { R$drawable.abc_textfield_activated_mtrl_alpha, R$drawable.abc_textfield_search_activated_mtrl_alpha, R$drawable.abc_cab_background_top_mtrl_alpha };
        TINT_COLOR_BACKGROUND_MULTIPLY = new int[] { R$drawable.abc_popup_background_mtrl_mult, R$drawable.abc_cab_background_internal_bg, R$drawable.abc_menu_hardkey_panel_mtrl_mult };
        TINT_COLOR_CONTROL_STATE_LIST = new int[] { R$drawable.abc_edit_text_material, R$drawable.abc_tab_indicator_material, R$drawable.abc_textfield_search_material, R$drawable.abc_spinner_mtrl_am_alpha, R$drawable.abc_btn_check_material, R$drawable.abc_btn_radio_material };
        CONTAINERS_WITH_TINT_CHILDREN = new int[] { R$drawable.abc_cab_background_top_material };
    }
    
    public TintManager(final Context mContext) {
        this.mContext = mContext;
        this.mResources = new TintResources(mContext.getResources(), this);
        this.mTypedValue = new TypedValue();
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
    
    private ColorStateList getDefaultColorStateList() {
        if (this.mDefaultColorStateList == null) {
            final int themeAttrColor = this.getThemeAttrColor(R$attr.colorControlNormal);
            final int themeAttrColor2 = this.getThemeAttrColor(R$attr.colorControlActivated);
            this.mDefaultColorStateList = new ColorStateList(new int[][] { { -16842910 }, { 16842908 }, { 16843518 }, { 16842919 }, { 16842912 }, { 16842913 }, new int[0] }, new int[] { this.getDisabledThemeAttrColor(R$attr.colorControlNormal), themeAttrColor2, themeAttrColor2, themeAttrColor2, themeAttrColor2, themeAttrColor2, themeAttrColor });
        }
        return this.mDefaultColorStateList;
    }
    
    public static Drawable getDrawable(final Context context, final int n) {
        if (isInTintList(n)) {
            return new TintManager(context).getDrawable(n);
        }
        return ContextCompat.getDrawable(context, n);
    }
    
    private ColorStateList getSwitchThumbColorStateList() {
        if (this.mSwitchThumbStateList == null) {
            this.mSwitchThumbStateList = new ColorStateList(new int[][] { { -16842910 }, { 16842912 }, new int[0] }, new int[] { this.getDisabledThemeAttrColor(R$attr.colorSwitchThumbNormal), this.getThemeAttrColor(R$attr.colorControlActivated), this.getThemeAttrColor(R$attr.colorSwitchThumbNormal) });
        }
        return this.mSwitchThumbStateList;
    }
    
    private ColorStateList getSwitchTrackColorStateList() {
        if (this.mSwitchTrackStateList == null) {
            this.mSwitchTrackStateList = new ColorStateList(new int[][] { { -16842910 }, { 16842912 }, new int[0] }, new int[] { this.getThemeAttrColor(16842800, 0.1f), this.getThemeAttrColor(R$attr.colorControlActivated, 0.3f), this.getThemeAttrColor(16842800, 0.3f) });
        }
        return this.mSwitchTrackStateList;
    }
    
    private static boolean isInTintList(final int n) {
        return arrayContains(TintManager.TINT_COLOR_BACKGROUND_MULTIPLY, n) || arrayContains(TintManager.TINT_COLOR_CONTROL_NORMAL, n) || arrayContains(TintManager.TINT_COLOR_CONTROL_ACTIVATED, n) || arrayContains(TintManager.TINT_COLOR_CONTROL_STATE_LIST, n) || arrayContains(TintManager.CONTAINERS_WITH_TINT_CHILDREN, n);
    }
    
    int getDisabledThemeAttrColor(final int n) {
        this.mContext.getTheme().resolveAttribute(16842803, this.mTypedValue, true);
        return this.getThemeAttrColor(n, this.mTypedValue.getFloat());
    }
    
    public Drawable getDrawable(final int n) {
        final Drawable drawable = ContextCompat.getDrawable(this.mContext, n);
        if (drawable != null) {
            if (arrayContains(TintManager.TINT_COLOR_CONTROL_STATE_LIST, n)) {
                return new TintDrawableWrapper(drawable, this.getDefaultColorStateList());
            }
            if (n == R$drawable.abc_switch_track_mtrl_alpha) {
                return new TintDrawableWrapper(drawable, this.getSwitchTrackColorStateList());
            }
            if (n == R$drawable.abc_switch_thumb_material) {
                return new TintDrawableWrapper(drawable, this.getSwitchThumbColorStateList(), PorterDuff$Mode.MULTIPLY);
            }
            if (arrayContains(TintManager.CONTAINERS_WITH_TINT_CHILDREN, n)) {
                return this.mResources.getDrawable(n);
            }
            this.tintDrawable(n, drawable);
        }
        return drawable;
    }
    
    int getThemeAttrColor(final int n) {
        if (this.mContext.getTheme().resolveAttribute(n, this.mTypedValue, true)) {
            if (this.mTypedValue.type >= 16 && this.mTypedValue.type <= 31) {
                return this.mTypedValue.data;
            }
            if (this.mTypedValue.type == 3) {
                return this.mResources.getColor(this.mTypedValue.resourceId);
            }
        }
        return 0;
    }
    
    int getThemeAttrColor(int themeAttrColor, final float n) {
        themeAttrColor = this.getThemeAttrColor(themeAttrColor);
        return (themeAttrColor & 0xFFFFFF) | Math.round(Color.alpha(themeAttrColor) * n) << 24;
    }
    
    void tintDrawable(int n, final Drawable drawable) {
        PorterDuff$Mode multiply;
        int n2;
        int round;
        if (arrayContains(TintManager.TINT_COLOR_CONTROL_NORMAL, n)) {
            n = R$attr.colorControlNormal;
            multiply = null;
            n2 = 1;
            round = -1;
        }
        else if (arrayContains(TintManager.TINT_COLOR_CONTROL_ACTIVATED, n)) {
            n = R$attr.colorControlActivated;
            multiply = null;
            n2 = 1;
            round = -1;
        }
        else if (arrayContains(TintManager.TINT_COLOR_BACKGROUND_MULTIPLY, n)) {
            multiply = PorterDuff$Mode.MULTIPLY;
            n2 = 1;
            round = -1;
            n = 16842801;
        }
        else if (n == R$drawable.abc_list_divider_mtrl_alpha) {
            n = 16842800;
            round = Math.round(40.8f);
            multiply = null;
            n2 = 1;
        }
        else {
            round = -1;
            n = 0;
            multiply = null;
            n2 = 0;
        }
        if (n2 != 0) {
            PorterDuff$Mode default_MODE;
            if ((default_MODE = multiply) == null) {
                default_MODE = TintManager.DEFAULT_MODE;
            }
            n = this.getThemeAttrColor(n);
            PorterDuffColorFilter value = TintManager.COLOR_FILTER_CACHE.get(n, default_MODE);
            if (value == null) {
                value = new PorterDuffColorFilter(n, default_MODE);
                TintManager.COLOR_FILTER_CACHE.put(n, default_MODE, value);
            }
            drawable.setColorFilter((ColorFilter)value);
            if (round != -1) {
                drawable.setAlpha(round);
            }
        }
    }
}
