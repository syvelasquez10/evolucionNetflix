// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.content.res.ColorStateList;
import java.util.Iterator;
import java.util.Collection;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.view.MenuItem;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import android.view.View$OnTouchListener;
import android.view.ViewGroup;
import android.view.View$OnLongClickListener;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.netflix.mediaclient.util.api.Api16Util;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.text.Spannable;
import android.text.SpannableString;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.app.DialogFragment;
import android.util.Pair;
import android.widget.ScrollView;
import com.netflix.mediaclient.android.widget.StaticGridView;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.ui.details.NetflixRatingBar$RatingBarDataProvider;
import android.view.ContextThemeWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import com.netflix.mediaclient.Log;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.graphics.Point;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.view.View$OnClickListener;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.ViewParent;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.RelativeLayout;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.TextView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ListView;
import android.content.Context;
import android.app.Activity;
import android.view.View;
import java.util.Comparator;

public class ViewUtils
{
    public static final float ALPHA_OPAQUE = 1.0f;
    public static final float ALPHA_TRANSPARENT = 0.0f;
    public static final float ALPHA_VALUE_ON_CLICK = 0.7f;
    public static final float ALPHA_VALUE_UNSELECTED_TEXT = 0.7f;
    public static final Comparator<ViewUtils$ViewComparator> REVERSE_SORT_BY_BOTTOM;
    private static final String TAG = "ViewUtils";
    
    static {
        REVERSE_SORT_BY_BOTTOM = new ViewUtils$4();
    }
    
    public static boolean activityIsDead(final View view) {
        final Context context = view.getContext();
        return context instanceof Activity && AndroidUtils.isActivityFinishedOrDestroyed(context);
    }
    
    public static void addActionBarPaddingView(final ListView listView) {
        if (listView.getContext() instanceof NetflixActivity) {
            listView.addHeaderView(createActionBarDummyView((NetflixActivity)listView.getContext()));
        }
    }
    
    public static void addWebLinks(final TextView textView) {
        if (textView == null) {
            throw new IllegalArgumentException("Text view is null!");
        }
        InternalLinkify.addWebLinks(textView);
    }
    
    public static void applyPaddingToGridItem(final View view, final int n, final int n2, final int n3) {
        if (view == null) {
            return;
        }
        if (n3 % n2 == 0) {
            view.setPadding(0, n, n, n);
        }
        else if ((n3 + 1) % n2 == 0) {
            view.setPadding(n, n, 0, n);
        }
        else {
            view.setPadding(n, n, n, n);
        }
        view.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, -2));
    }
    
    public static void applyUniformPaddingToGridItem(final View view, final int n, final int n2, final int n3) {
        if (view == null) {
            return;
        }
        if (n3 % n2 == 0) {
            view.setPadding(n, n / 2, n / 2, n / 2);
            return;
        }
        if ((n3 + 1) % n2 == 0) {
            view.setPadding(n / 2, n / 2, n, n / 2);
            return;
        }
        view.setPadding(n / 2, n / 2, n / 2, n / 2);
    }
    
    public static View createActionBarDummyView(final NetflixActivity netflixActivity) {
        return createActionBarDummyView(netflixActivity, netflixActivity.getActionBarHeight());
    }
    
    public static View createActionBarDummyView(final NetflixActivity netflixActivity, final int n) {
        final View view = new View((Context)netflixActivity);
        view.setId(2131689474);
        view.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, n));
        return view;
    }
    
    public static ViewGroup$LayoutParams createLayoutParams(final View view, final int n, final int n2) {
        final ViewParent parent = view.getParent();
        if (parent instanceof FrameLayout) {
            return (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(n, n2);
        }
        if (parent instanceof RelativeLayout) {
            return (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(n, n2);
        }
        if (parent instanceof LinearLayout) {
            return (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(n, n2);
        }
        return new ViewGroup$LayoutParams(n, n2);
    }
    
    public static MenuItem$OnMenuItemClickListener createShowToastMenuClickListener(final Activity activity) {
        return (MenuItem$OnMenuItemClickListener)new ViewUtils$2(activity);
    }
    
    public static View$OnClickListener createShowToastViewClickListener(final Activity activity) {
        return (View$OnClickListener)new ViewUtils$1(activity);
    }
    
    public static Bitmap createSquaredBitmap(final Bitmap bitmap) {
        final int max = Math.max(bitmap.getWidth(), bitmap.getHeight());
        final Bitmap bitmap2 = Bitmap.createBitmap(max, max, Bitmap$Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap2);
        canvas.drawColor(0);
        canvas.drawBitmap(bitmap, (float)((max - bitmap.getWidth()) / 2), (float)((max - bitmap.getHeight()) / 2), (Paint)null);
        return bitmap2;
    }
    
    public static int getDefaultActionBarHeight(final Context context) {
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new TypedValue().data, new int[] { 2130772055 });
        final int n = (int)obtainStyledAttributes.getDimension(0, 0.0f);
        obtainStyledAttributes.recycle();
        return n;
    }
    
    public static Point getDisplaySize(final Activity activity) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }
    
    public static Point getEstimatedTextSize(float measureText, final Typeface typeface, final String s) {
        Typeface default1 = typeface;
        if (typeface == null) {
            default1 = Typeface.DEFAULT;
        }
        if (Log.isLoggable()) {
            Log.d("ViewUtils", "fs: " + measureText);
            Log.d("ViewUtils", "text: " + s);
        }
        final Paint paint = new Paint();
        final Rect rect = new Rect();
        paint.setTypeface(default1);
        paint.setTextSize(measureText);
        paint.getTextBounds(s, 0, s.length(), rect);
        measureText = paint.measureText(s);
        if (Log.isLoggable()) {
            Log.d("ViewUtils", "h: " + rect.height());
            Log.d("ViewUtils", "w: " + rect.width());
            Log.d("ViewUtils", "realW: " + measureText);
        }
        return new Point((int)measureText, rect.height());
    }
    
    public static float getEstimatedTextWidth(float measureText, final Typeface typeface, final String s) {
        Typeface default1 = typeface;
        if (typeface == null) {
            default1 = Typeface.DEFAULT;
        }
        if (Log.isLoggable()) {
            Log.d("ViewUtils", "fs: " + measureText);
            Log.d("ViewUtils", "text: " + s);
        }
        final Paint paint = new Paint();
        paint.setTypeface(default1);
        paint.setTextSize(measureText);
        measureText = paint.measureText(s);
        if (Log.isLoggable()) {
            Log.d("ViewUtils", "realW: " + measureText);
        }
        return measureText;
    }
    
    public static int getHeightIfVisible(final View view) {
        if (isVisible(view)) {
            return view.getHeight();
        }
        return 0;
    }
    
    public static void getHitRect(final View view, final Rect rect) {
        rect.left = (int)view.getX();
        rect.top = (int)view.getY();
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
    }
    
    public static Rect getLocationOnScreen(final View view) {
        final int[] array = new int[2];
        view.getLocationOnScreen(array);
        return new Rect(array[0], array[1], array[0] + view.getWidth(), array[1] + view.getHeight());
    }
    
    public static int getNavigationBarHeight(final Context context, final boolean b) {
        final Resources resources = context.getResources();
        String s;
        if (b) {
            s = "navigation_bar_height";
        }
        else {
            s = "navigation_bar_height_landscape";
        }
        final int identifier = resources.getIdentifier(s, "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        Log.e("ViewUtils", "Nav bar height uknown!");
        return 0;
    }
    
    public static NetflixActivity getNetflixActivitySafely(final View view) {
        if (view == null) {
            return null;
        }
        final Context context = view.getContext();
        if (context instanceof NetflixActivity) {
            return (NetflixActivity)context;
        }
        if (context instanceof ContextThemeWrapper) {
            final Context baseContext = ((ContextThemeWrapper)context).getBaseContext();
            if (baseContext instanceof NetflixActivity) {
                return (NetflixActivity)baseContext;
            }
        }
        return null;
    }
    
    public static NetflixRatingBar$RatingBarDataProvider getRatingBarDataProviderSafely(final View view) {
        if (view == null) {
            return null;
        }
        final Context context = view.getContext();
        if (context instanceof NetflixRatingBar$RatingBarDataProvider) {
            return (NetflixRatingBar$RatingBarDataProvider)context;
        }
        if (context instanceof ContextThemeWrapper) {
            final Context baseContext = ((ContextThemeWrapper)context).getBaseContext();
            if (baseContext instanceof NetflixRatingBar$RatingBarDataProvider) {
                return (NetflixRatingBar$RatingBarDataProvider)baseContext;
            }
        }
        return null;
    }
    
    public static Rect getRect(final View view, final boolean b) {
        Log.d("ViewUtils", "getRect");
        if (!b) {
            final Rect rect = new Rect();
            getHitRect(view, rect);
            return rect;
        }
        final Object tag = view.getTag();
        if (tag instanceof Rect) {
            Log.d("ViewUtils", "Rectangle was known from before, use it");
            return (Rect)tag;
        }
        Log.d("ViewUtils", "Rectangle was NOT known from before, calculate");
        final Rect tag2 = new Rect();
        getHitRect(view, tag2);
        view.setTag((Object)tag2);
        return tag2;
    }
    
    public static int getStatusBarHeight(final Context context) {
        final int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        Log.e("ViewUtils", "Status bar height uknown!");
        return 0;
    }
    
    public static List<View> getViewsById(final View view, final Integer... array) {
        final ArrayList<View> list = new ArrayList<View>();
        for (int length = array.length, i = 0; i < length; ++i) {
            final Integer n = array[i];
            if (n != null) {
                final View viewById = view.findViewById((int)n);
                if (viewById != null) {
                    list.add(viewById);
                }
            }
        }
        return list;
    }
    
    public static String getVisibilityAsString(final int n) {
        switch (n) {
            default: {
                return "UNKNOWN [" + n + "]";
            }
            case 8: {
                return "GONE";
            }
            case 4: {
                return "INVISIBLE";
            }
            case 0: {
                return "VISIBLE";
            }
        }
    }
    
    public static String getVisibilityAsString(final View view) {
        if (view == null) {
            return "null view";
        }
        return getVisibilityAsString(view.getVisibility());
    }
    
    public static Pair<Integer, Integer> getVisiblePositions(final StaticGridView staticGridView, final ScrollView scrollView) {
        if (staticGridView == null || staticGridView.getChildCount() == 0) {
            return null;
        }
        if (scrollView == null || scrollView.getChildCount() == 0) {
            return null;
        }
        final Rect rect = new Rect();
        scrollView.getHitRect(rect);
        final int childCount = staticGridView.getChildCount();
        int i = 0;
        int n = -1;
        while (i < childCount) {
            final boolean localVisibleRect = staticGridView.getChildAt(i).getLocalVisibleRect(rect);
            int n2;
            if (localVisibleRect && n == -1) {
                n2 = i;
            }
            else {
                n2 = n;
                if (!localVisibleRect && (n2 = n) != -1) {
                    break;
                }
            }
            ++i;
            n = n2;
        }
        final int n3 = i - 1;
        if (n != -1 && n3 != -1) {
            return (Pair<Integer, Integer>)new Pair((Object)n, (Object)n3);
        }
        return null;
    }
    
    public static boolean isNavigationBarBelowContent(final Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity can not be null");
        }
        final Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return activity.getResources().getDisplayMetrics().widthPixels == rect.right;
    }
    
    public static boolean isNavigationBarRightOfContent(final Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity can not be null");
        }
        final Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return activity.getResources().getDisplayMetrics().heightPixels == rect.bottom;
    }
    
    public static boolean isVisible(final View view) {
        return view != null && view.getVisibility() == 0;
    }
    
    private static void logSafeShowDialogFragmentError(String format, final DialogFragment dialogFragment) {
        format = String.format("SPY-8702, tried to add %s frag %s twice", dialogFragment.getClass().getName(), format);
        Log.e("ViewUtils", format);
        ErrorLoggingManager.logHandledException(format);
    }
    
    public static void modifyExitingWebLinks(final TextView textView) {
        if (textView == null) {
            throw new IllegalArgumentException("Text view is null!");
        }
        if (textView.getText() instanceof SpannableString) {
            StringUtils.modifyUrlHandling((Spannable)textView.getText());
            return;
        }
        Log.w("ViewUtils", "Not spannable!");
    }
    
    public static void removeGlobalLayoutListener(final View view, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener) {
        if (AndroidUtils.getAndroidVersion() < 16) {
            view.getViewTreeObserver().removeGlobalOnLayoutListener(viewTreeObserver$OnGlobalLayoutListener);
            return;
        }
        Api16Util.removeOnGlobalLayoutListener(view.getViewTreeObserver(), viewTreeObserver$OnGlobalLayoutListener);
    }
    
    public static void removeShadow(final TextView textView) {
        textView.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
    }
    
    public static void resetImageDrawable(final ImageView imageView) {
        if (imageView == null && Log.isLoggable()) {
            Log.d("ViewUtils", "Request to resetImageDrawable with a null ImageView");
            return;
        }
        imageView.setImageDrawable((Drawable)null);
    }
    
    public static void safeShowDialogFragment(final DialogFragment dialogFragment, final FragmentManager fragmentManager, final FragmentTransaction fragmentTransaction, final String s) {
        if (dialogFragment == null || fragmentManager == null || StringUtils.isEmpty(s) || fragmentTransaction == null) {
            return;
        }
        fragmentManager.executePendingTransactions();
        if (fragmentManager.findFragmentByTag(s) == null) {
            dialogFragment.show(fragmentTransaction, s);
            return;
        }
        logSafeShowDialogFragmentError(s, dialogFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
    
    public static void safeShowDialogFragment(final DialogFragment dialogFragment, final FragmentManager fragmentManager, final String s) {
        if (dialogFragment == null || fragmentManager == null || StringUtils.isEmpty(s)) {
            return;
        }
        fragmentManager.executePendingTransactions();
        if (fragmentManager.findFragmentByTag(s) == null) {
            dialogFragment.show(fragmentManager, s);
            return;
        }
        logSafeShowDialogFragmentError(s, dialogFragment);
    }
    
    public static void setDrawableTint(final Drawable drawable, final int n) {
        DrawableCompat.setTint(DrawableCompat.wrap(drawable), n);
    }
    
    public static void setLayerType(final View view, final int n) {
        if (view == null) {
            if (Log.isLoggable()) {
                Log.d("ViewUtils", "Request to set layer type for a view that was null");
            }
        }
        else if (view.getLayerType() != n) {
            view.setLayerType(n, (Paint)null);
        }
    }
    
    public static void setLongTapListenersRecursivelyToShowContentDescriptionToast(final View view) {
        final CharSequence contentDescription = view.getContentDescription();
        if (StringUtils.isNotEmpty(contentDescription)) {
            view.setOnLongClickListener((View$OnLongClickListener)new ViewUtils$3(view, contentDescription));
        }
        if (view instanceof ViewGroup) {
            final ViewGroup viewGroup = (ViewGroup)view;
            for (int i = 0; i < viewGroup.getChildCount(); ++i) {
                setLongTapListenersRecursivelyToShowContentDescriptionToast(viewGroup.getChildAt(i));
            }
        }
    }
    
    public static void setOnTouchListeners(final View$OnTouchListener onTouchListener, final View... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            array[i].setOnTouchListener(onTouchListener);
        }
    }
    
    public static void setPaddingBottom(final View view, final int n) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), n);
    }
    
    public static void setPaddingEnd(final View view, final int n) {
        if (LocalizationUtils.isCurrentLocaleRTL()) {
            view.setPadding(n, view.getPaddingTop(), view.getPaddingLeft(), view.getPaddingBottom());
            return;
        }
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), n, view.getPaddingBottom());
    }
    
    public static void setPaddingStart(final View view, final int n) {
        if (LocalizationUtils.isCurrentLocaleRTL()) {
            view.setPadding(view.getPaddingRight(), view.getPaddingTop(), n, view.getPaddingBottom());
            return;
        }
        view.setPadding(n, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
    }
    
    public static void setPaddingTop(final View view, final int n) {
        view.setPadding(view.getPaddingLeft(), n, view.getPaddingRight(), view.getPaddingBottom());
    }
    
    public static void setTextOpacityToSelected(final TextView textView) {
        textView.setAlpha(1.0f);
    }
    
    public static void setTextOpacityToUnselected(final TextView textView) {
        textView.setAlpha(0.7f);
    }
    
    public static void setTextViewColor(final TextView textView, final int n) {
        textView.setTextColor(textView.getResources().getColor(n));
    }
    
    public static void setTextViewSizeByRes(final TextView textView, final int n) {
        textView.setTextSize(0, (float)textView.getResources().getDimensionPixelOffset(n));
    }
    
    public static void setTextViewToBold(final TextView textView) {
        textView.setTypeface(Typeface.create(textView.getTypeface(), 1));
    }
    
    public static void setTextViewToNormal(final TextView textView) {
        textView.setTypeface(Typeface.create(textView.getTypeface(), 0));
    }
    
    public static void setVisibility(final MenuItem menuItem, final boolean visible) {
        if (menuItem != null) {
            menuItem.setVisible(visible);
        }
    }
    
    public static void setVisibility(final View view, final ViewUtils$Visibility viewUtils$Visibility) {
        if (view == null || viewUtils$Visibility == null) {
            return;
        }
        int visibility = 8;
        if (viewUtils$Visibility == ViewUtils$Visibility.VISIBLE) {
            visibility = 0;
        }
        else if (viewUtils$Visibility == ViewUtils$Visibility.INVISIBLE) {
            visibility = 4;
        }
        view.setVisibility(visibility);
    }
    
    public static void setVisibleOrGone(final View view, final boolean b) {
        ViewUtils$Visibility viewUtils$Visibility;
        if (b) {
            viewUtils$Visibility = ViewUtils$Visibility.VISIBLE;
        }
        else {
            viewUtils$Visibility = ViewUtils$Visibility.GONE;
        }
        setVisibility(view, viewUtils$Visibility);
    }
    
    public static void setVisibleOrGoneAnimation(final View view, final boolean b, final boolean b2) {
        if (b2) {
            AnimationUtils.startViewAppearanceAnimation(view, b);
            return;
        }
        ViewUtils$Visibility viewUtils$Visibility;
        if (b) {
            viewUtils$Visibility = ViewUtils$Visibility.VISIBLE;
        }
        else {
            viewUtils$Visibility = ViewUtils$Visibility.GONE;
        }
        setVisibility(view, viewUtils$Visibility);
    }
    
    public static void setVisibleOrInvisible(final View view, final boolean b) {
        ViewUtils$Visibility viewUtils$Visibility;
        if (b) {
            viewUtils$Visibility = ViewUtils$Visibility.VISIBLE;
        }
        else {
            viewUtils$Visibility = ViewUtils$Visibility.INVISIBLE;
        }
        setVisibility(view, viewUtils$Visibility);
    }
    
    public static void setVisibleOrInvisibleAnimation(final View view, final boolean b, final boolean b2) {
        if (b2) {
            AnimationUtils.startViewAppearanceAnimation(view, b);
            return;
        }
        ViewUtils$Visibility viewUtils$Visibility;
        if (b) {
            viewUtils$Visibility = ViewUtils$Visibility.VISIBLE;
        }
        else {
            viewUtils$Visibility = ViewUtils$Visibility.INVISIBLE;
        }
        setVisibility(view, viewUtils$Visibility);
    }
    
    public static void showViews(final Collection<View> collection) {
        if (collection != null) {
            for (final View view : collection) {
                if (view != null) {
                    view.setVisibility(0);
                }
            }
        }
    }
    
    public static Drawable tintAndGet(Drawable wrap, final int n) {
        if (wrap == null) {
            return null;
        }
        wrap = DrawableCompat.wrap(wrap.mutate());
        DrawableCompat.setTint(wrap, n);
        return wrap;
    }
    
    public static Drawable tintAndGet(Drawable drawable, final ColorStateList list, final int n) {
        if (drawable == null) {
            drawable = null;
        }
        else {
            final Drawable wrap = DrawableCompat.wrap(drawable.mutate());
            if (n > 0) {
                wrap.setBounds(0, 0, n, n);
            }
            drawable = wrap;
            if (list != null) {
                DrawableCompat.setTint(wrap, list.getDefaultColor());
                return wrap;
            }
        }
        return drawable;
    }
}
