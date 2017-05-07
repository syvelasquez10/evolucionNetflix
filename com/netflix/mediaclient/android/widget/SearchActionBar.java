// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.widget.SearchView$OnQueryTextListener;
import android.widget.TextView;
import android.widget.ImageView;
import android.app.SearchManager;
import com.netflix.mediaclient.Log;
import android.graphics.Rect;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.app.ActionBar;
import android.app.Activity;
import android.widget.SearchView;
import android.widget.ProgressBar;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

public class SearchActionBar extends NetflixActionBar
{
    private static final String TAG = "SearchActionBar";
    private final ViewTreeObserver$OnGlobalLayoutListener globalLayoutListener;
    private final ProgressBar progressSpinner;
    private final SearchView searchView;
    
    public SearchActionBar(final Activity activity, final ActionBar actionBar, final AccessibilityRunnable accessibilityRunnable) {
        super(activity, actionBar, accessibilityRunnable);
        this.globalLayoutListener = (ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                ViewUtils.removeGlobalLayoutListener((View)SearchActionBar.this.searchView, (ViewTreeObserver$OnGlobalLayoutListener)this);
                if (Rect.intersects(ViewUtils.getLocationOnScreen((View)SearchActionBar.this.searchView), ViewUtils.getLocationOnScreen(SearchActionBar.this.getLogo()))) {
                    Log.v("SearchActionBar", "Logo overlaps with search view - hiding logo");
                    SearchActionBar.this.getLogo().setVisibility(8);
                }
            }
        };
        this.progressSpinner = (ProgressBar)this.getContentView().findViewById(2131230801);
        (this.searchView = (SearchView)this.getContentView().findViewById(2131230800)).setIconified(false);
        this.searchView.setIconifiedByDefault(false);
        this.searchView.setImeOptions(33554435);
        this.searchView.setInputType(8192);
        this.searchView.setQueryRefinementEnabled(true);
        this.searchView.setSubmitButtonEnabled(false);
        this.searchView.getViewTreeObserver().addOnGlobalLayoutListener(this.globalLayoutListener);
        this.searchView.setSearchableInfo(((SearchManager)activity.getSystemService("search")).getSearchableInfo(activity.getComponentName()));
        this.configureSearchViewTextView();
        this.configureSearchViewIcon();
        this.replaceBackgroundDrawables();
    }
    
    private void configureSearchViewIcon() {
        final ImageView imageView = (ImageView)this.searchView.findViewById(this.getActivity().getResources().getIdentifier("android:id/search_mag_icon", (String)null, (String)null));
        if (imageView != null) {
            imageView.setImageResource(2130837713);
        }
    }
    
    private void configureSearchViewTextView() {
        final TextView textView = (TextView)this.searchView.findViewById(this.getActivity().getResources().getIdentifier("android:id/search_src_text", (String)null, (String)null));
        if (textView != null) {
            textView.setHintTextColor(this.searchView.getResources().getColor(2131165229));
            textView.setImeOptions(33554432);
        }
    }
    
    private void replaceBackgroundDrawable(final String s, final int backgroundResource) {
        final View viewById = this.searchView.findViewById(this.searchView.getContext().getResources().getIdentifier(s, (String)null, (String)null));
        if (viewById == null) {
            Log.w("SearchActionBar", "Couldn't find view for: " + s);
            return;
        }
        viewById.setBackgroundResource(backgroundResource);
    }
    
    private void replaceBackgroundDrawables() {
        this.replaceBackgroundDrawable("android:id/search_plate", 2130837823);
        this.replaceBackgroundDrawable("android:id/submit_area", 2130837824);
    }
    
    public void clearFocus() {
        this.searchView.clearFocus();
        final View focus = this.searchView.findFocus();
        if (focus != null) {
            focus.clearFocus();
        }
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903065;
    }
    
    public void hideProgressSpinner() {
        this.progressSpinner.setVisibility(4);
    }
    
    public void setOnQueryTextListener(final SearchView$OnQueryTextListener onQueryTextListener) {
        this.searchView.setOnQueryTextListener(onQueryTextListener);
    }
    
    public void setQuery(final String s, final boolean b) {
        this.searchView.setQuery((CharSequence)s, b);
    }
    
    public void showProgressSpinner() {
        this.progressSpinner.setVisibility(0);
    }
}
