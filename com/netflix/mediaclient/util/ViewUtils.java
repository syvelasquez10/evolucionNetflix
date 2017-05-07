// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.util.Iterator;
import java.util.Collection;
import android.view.ViewGroup;
import android.view.View$OnLongClickListener;
import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.netflix.mediaclient.util.api.Api16Util;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import java.util.ArrayList;
import java.util.List;
import android.graphics.Paint;
import com.netflix.mediaclient.Log;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.graphics.Point;
import android.view.View$OnClickListener;
import android.widget.Toast;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;
import android.app.Activity;
import android.view.ViewParent;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.RelativeLayout;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ListView;
import android.graphics.Rect;
import java.util.Comparator;

public class ViewUtils
{
    public static final int DIM_ALPHA = 64;
    public static final int NO_ALPHA = 255;
    public static final Comparator<ViewComparator> REVERSE_SORT_BY_BOTTOM;
    private static final String TAG = "nf_subtitles";
    
    static {
        REVERSE_SORT_BY_BOTTOM = new Comparator<ViewComparator>() {
            @Override
            public int compare(final ViewComparator viewComparator, final ViewComparator viewComparator2) {
                final Rect rect = ViewUtils.getRect(viewComparator.getView(), true);
                final Rect rect2 = ViewUtils.getRect(viewComparator2.getView(), true);
                if (rect.bottom < rect2.bottom) {
                    return 1;
                }
                if (rect.bottom > rect2.bottom) {
                    return -1;
                }
                return 0;
            }
        };
    }
    
    public static void addActionBarPaddingView(final ListView listView) {
        if (listView.getContext() instanceof NetflixActivity) {
            listView.addHeaderView(createActionBarDummyView((NetflixActivity)listView.getContext()));
        }
    }
    
    public static void addActionBarPaddingView(final StickyListHeadersListView stickyListHeadersListView) {
        if (stickyListHeadersListView.getContext() instanceof NetflixActivity) {
            stickyListHeadersListView.addHeaderView(createActionBarDummyView((NetflixActivity)stickyListHeadersListView.getContext()));
        }
    }
    
    public static void clearShadow(final TextView textView) {
        textView.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
    }
    
    public static View createActionBarDummyView(final NetflixActivity netflixActivity) {
        final View view = new View((Context)netflixActivity);
        view.setId(2131165241);
        view.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, netflixActivity.getActionBarHeight()));
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
        return (MenuItem$OnMenuItemClickListener)new MenuItem$OnMenuItemClickListener() {
            public boolean onMenuItemClick(final MenuItem menuItem) {
                final StringBuilder sb = new StringBuilder("Clicked on: ");
                if (StringUtils.isNotEmpty(menuItem.getTitle())) {
                    sb.append(menuItem.getTitle());
                }
                else {
                    sb.append(menuItem.getClass().getSimpleName());
                }
                Toast.makeText((Context)activity, (CharSequence)sb.toString(), 0).show();
                return true;
            }
        };
    }
    
    public static View$OnClickListener createShowToastViewClickListener(final Activity activity) {
        return (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final StringBuilder sb = new StringBuilder("Clicked on: ");
                if (StringUtils.isNotEmpty(view.getContentDescription())) {
                    sb.append(view.getContentDescription());
                }
                else {
                    sb.append(view.getClass().getSimpleName());
                }
                Toast.makeText((Context)activity, (CharSequence)sb.toString(), 0).show();
            }
        };
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
        if (Log.isLoggable("nf_subtitles", 3)) {
            Log.d("nf_subtitles", "fs: " + measureText);
            Log.d("nf_subtitles", "text: " + s);
        }
        final Paint paint = new Paint();
        final Rect rect = new Rect();
        paint.setTypeface(default1);
        paint.setTextSize(measureText);
        paint.getTextBounds(s, 0, s.length(), rect);
        measureText = paint.measureText(s);
        if (Log.isLoggable("nf_subtitles", 3)) {
            Log.d("nf_subtitles", "h: " + rect.height());
            Log.d("nf_subtitles", "w: " + rect.width());
            Log.d("nf_subtitles", "realW: " + measureText);
        }
        return new Point((int)measureText, rect.height());
    }
    
    public static float getEstimatedTextWidth(float measureText, final Typeface typeface, final String s) {
        Typeface default1 = typeface;
        if (typeface == null) {
            default1 = Typeface.DEFAULT;
        }
        if (Log.isLoggable("nf_subtitles", 3)) {
            Log.d("nf_subtitles", "fs: " + measureText);
            Log.d("nf_subtitles", "text: " + s);
        }
        final Paint paint = new Paint();
        paint.setTypeface(default1);
        paint.setTextSize(measureText);
        measureText = paint.measureText(s);
        if (Log.isLoggable("nf_subtitles", 3)) {
            Log.d("nf_subtitles", "realW: " + measureText);
        }
        return measureText;
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
    
    public static Rect getRect(final View view, final boolean b) {
        Log.d("nf_subtitles", "getRect");
        if (!b) {
            final Rect rect = new Rect();
            getHitRect(view, rect);
            return rect;
        }
        final Object tag = view.getTag();
        if (tag instanceof Rect) {
            Log.d("nf_subtitles", "Rectangle was known from before, use it");
            return (Rect)tag;
        }
        Log.d("nf_subtitles", "Rectangle was NOT known from before, calculate");
        final Rect tag2 = new Rect();
        getHitRect(view, tag2);
        view.setTag((Object)tag2);
        return tag2;
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
    
    public static void removeGlobalLayoutListener(final View view, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener) {
        if (AndroidUtils.getAndroidVersion() < 16) {
            view.getViewTreeObserver().removeGlobalOnLayoutListener(viewTreeObserver$OnGlobalLayoutListener);
            return;
        }
        Api16Util.removeOnGlobalLayoutListener(view.getViewTreeObserver(), viewTreeObserver$OnGlobalLayoutListener);
    }
    
    @SuppressLint({ "NewApi" })
    public static void setBackgroundToImageCompat(final ImageView imageView, final Drawable drawable) {
        if (AndroidUtils.getAndroidVersion() >= 16) {
            imageView.setBackground(drawable);
            return;
        }
        imageView.setBackgroundDrawable(drawable);
    }
    
    public static void setLongTapListenersRecursivelyToShowContentDescriptionToast(final View view) {
        final CharSequence contentDescription = view.getContentDescription();
        if (StringUtils.isNotEmpty(contentDescription)) {
            view.setOnLongClickListener((View$OnLongClickListener)new View$OnLongClickListener() {
                public boolean onLongClick(final View view) {
                    Toast.makeText(view.getContext(), contentDescription, 0).show();
                    return true;
                }
            });
        }
        if (view instanceof ViewGroup) {
            final ViewGroup viewGroup = (ViewGroup)view;
            for (int i = 0; i < viewGroup.getChildCount(); ++i) {
                setLongTapListenersRecursivelyToShowContentDescriptionToast(viewGroup.getChildAt(i));
            }
        }
    }
    
    public static void setVisibility(final View view, final Visibility visibility) {
        if (view == null || visibility == null) {
            return;
        }
        int visibility2 = 8;
        if (visibility == Visibility.VISIBLE) {
            visibility2 = 0;
        }
        else if (visibility == Visibility.INVISIBLE) {
            visibility2 = 4;
        }
        view.setVisibility(visibility2);
    }
    
    public static void setVisibility(final View view, final boolean b) {
        Visibility visibility;
        if (b) {
            visibility = Visibility.VISIBLE;
        }
        else {
            visibility = Visibility.GONE;
        }
        setVisibility(view, visibility);
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
    
    public static class ViewComparator implements Comparable<ViewComparator>
    {
        public static final Comparator<ViewComparator> REVERSE_SORT_BY_TOP;
        public static final Comparator<ViewComparator> SORT_BY_BOTTOM;
        public static final Comparator<ViewComparator> SORT_BY_TOP;
        private final View mView;
        
        static {
            REVERSE_SORT_BY_TOP = new Comparator<ViewComparator>() {
                @Override
                public int compare(final ViewComparator viewComparator, final ViewComparator viewComparator2) {
                    final Rect rect = ViewUtils.getRect(viewComparator.getView(), true);
                    final Rect rect2 = ViewUtils.getRect(viewComparator2.getView(), true);
                    if (rect.top < rect2.top) {
                        return 1;
                    }
                    if (rect.top > rect2.top) {
                        return -1;
                    }
                    return 0;
                }
            };
            SORT_BY_TOP = new Comparator<ViewComparator>() {
                @Override
                public int compare(final ViewComparator viewComparator, final ViewComparator viewComparator2) {
                    int n = 1;
                    final Rect rect = ViewUtils.getRect(viewComparator.getView(), true);
                    final Rect rect2 = ViewUtils.getRect(viewComparator2.getView(), true);
                    if (rect.top < rect2.top) {
                        n = -1;
                    }
                    else if (rect.top <= rect2.top) {
                        return 0;
                    }
                    return n;
                }
            };
            SORT_BY_BOTTOM = new Comparator<ViewComparator>() {
                @Override
                public int compare(final ViewComparator viewComparator, final ViewComparator viewComparator2) {
                    int n = 1;
                    final Rect rect = ViewUtils.getRect(viewComparator.getView(), true);
                    final Rect rect2 = ViewUtils.getRect(viewComparator2.getView(), true);
                    if (rect.bottom < rect2.bottom) {
                        n = -1;
                    }
                    else if (rect.bottom <= rect2.bottom) {
                        return 0;
                    }
                    return n;
                }
            };
        }
        
        public ViewComparator(final View mView) {
            if (mView == null) {
                throw new IllegalArgumentException("View can not be null");
            }
            this.mView = mView;
        }
        
        @Override
        public int compareTo(final ViewComparator viewComparator) {
            int n = 1;
            final Rect rect = ViewUtils.getRect(this.mView, true);
            final Rect rect2 = ViewUtils.getRect(viewComparator.getView(), true);
            if (rect.bottom < rect2.bottom) {
                n = -1;
            }
            else if (rect.bottom <= rect2.bottom) {
                return 0;
            }
            return n;
        }
        
        public View getView() {
            return this.mView;
        }
    }
    
    public enum Visibility
    {
        GONE, 
        INVISIBLE, 
        VISIBLE;
    }
}
