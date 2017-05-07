// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.view.View$OnTouchListener;
import android.widget.SearchView$OnQueryTextListener;
import android.view.View$OnFocusChangeListener;
import android.view.View;
import com.netflix.mediaclient.Log;
import android.widget.ImageView;
import android.app.SearchManager;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.TextView;
import android.widget.SearchView;
import android.widget.ProgressBar;

public class SearchActionBar extends NetflixActionBar
{
    private static final String TAG = "SearchActionBar";
    private final ProgressBar progressSpinner;
    protected final SearchView searchView;
    private TextView textView;
    
    public SearchActionBar(final NetflixActivity netflixActivity) {
        super(netflixActivity, true);
        this.progressSpinner = (ProgressBar)this.getContentView().findViewById(2131165286);
        (this.searchView = (SearchView)this.getContentView().findViewById(2131165285)).setIconified(false);
        this.searchView.setIconifiedByDefault(false);
        this.searchView.setImeOptions(33554435);
        this.searchView.setInputType(8192);
        this.searchView.setQueryRefinementEnabled(true);
        this.searchView.setSubmitButtonEnabled(false);
        this.searchView.setSearchableInfo(((SearchManager)netflixActivity.getSystemService("search")).getSearchableInfo(netflixActivity.getComponentName()));
        this.configureSearchViewTextView();
        this.configureSearchViewIcon();
        this.replaceBackgroundDrawables();
    }
    
    private void configureSearchViewIcon() {
        final ImageView imageView = (ImageView)this.searchView.findViewById(this.getActivity().getResources().getIdentifier("android:id/search_mag_icon", (String)null, (String)null));
        if (imageView != null) {
            imageView.setImageResource(2130837727);
        }
    }
    
    private void configureSearchViewTextView() {
        this.textView = (TextView)this.searchView.findViewById(this.getActivity().getResources().getIdentifier("android:id/search_src_text", (String)null, (String)null));
        if (this.textView != null) {
            this.textView.setHintTextColor(this.searchView.getResources().getColor(2131296315));
            this.textView.setImeOptions(33554432);
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
        this.replaceBackgroundDrawable("android:id/search_plate", 2130837868);
        this.replaceBackgroundDrawable("android:id/submit_area", 2130837869);
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
        return 2130903064;
    }
    
    public void hideProgressSpinner() {
        this.progressSpinner.setVisibility(4);
    }
    
    public void setOnFocusChangeListener(final View$OnFocusChangeListener onFocusChangeListener) {
        if (this.textView != null) {
            this.textView.setOnFocusChangeListener(onFocusChangeListener);
        }
    }
    
    public void setOnQueryTextListener(final SearchView$OnQueryTextListener onQueryTextListener) {
        this.searchView.setOnQueryTextListener(onQueryTextListener);
    }
    
    public void setOnTouchTextListener(final View$OnTouchListener onTouchListener) {
        if (this.textView != null) {
            this.textView.setOnTouchListener(onTouchListener);
        }
    }
    
    public void setQuery(final String s, final boolean b) {
        this.searchView.setQuery((CharSequence)s, b);
    }
    
    public void showProgressSpinner() {
        this.progressSpinner.setVisibility(0);
    }
}
