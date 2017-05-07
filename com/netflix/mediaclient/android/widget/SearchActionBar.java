// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.view.View$OnTouchListener;
import android.widget.SearchView$OnQueryTextListener;
import android.view.View$OnFocusChangeListener;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.app.SearchManager;
import com.netflix.mediaclient.Log;
import android.annotation.SuppressLint;
import android.view.View;
import android.support.v7.app.ActionBar$LayoutParams;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.TextView;
import android.widget.SearchView;
import android.widget.ProgressBar;

public class SearchActionBar extends NetflixActionBar
{
    private static final String TAG = "SearchActionBar";
    private ProgressBar progressSpinner;
    protected SearchView searchView;
    private TextView textView;
    
    public SearchActionBar(final NetflixActivity netflixActivity) {
        super(netflixActivity, true);
        this.initViews();
        this.setupSearchView();
        this.setupSearchManager(netflixActivity);
        this.configureSearchViewTextView();
        this.configureSearchViewIcon();
        this.updateBackgroundDrawables();
        this.systemActionBar.setDisplayShowCustomEnabled(true);
        this.systemActionBar.setDisplayUseLogoEnabled(false);
    }
    
    private void configureSearchViewIcon() {
        final ImageView imageView = (ImageView)this.searchView.findViewById(this.getActivity().getResources().getIdentifier("android:id/search_mag_icon", (String)null, (String)null));
        if (imageView != null) {
            imageView.setImageResource(this.getActiveSearchIconResId());
        }
    }
    
    private void configureSearchViewTextView() {
        this.textView = (TextView)this.searchView.findViewById(this.getActivity().getResources().getIdentifier("android:id/search_src_text", (String)null, (String)null));
        if (this.textView != null) {
            final int color = this.searchView.getResources().getColor(this.getSearchViewTextColorResId());
            this.textView.setHintTextColor(color);
            this.textView.setTextColor(color);
            this.textView.setImeOptions(33554432);
        }
    }
    
    private Drawable getDrawableFromSystemId(final String s) {
        final ImageView systemImageView = this.getSystemImageView(s);
        if (systemImageView == null) {
            return null;
        }
        return systemImageView.getDrawable();
    }
    
    private ImageView getSystemImageView(final String s) {
        return (ImageView)this.searchView.findViewById(this.searchView.getContext().getResources().getIdentifier(s, (String)null, (String)null));
    }
    
    @SuppressLint({ "InflateParams" })
    private void initViews() {
        final View inflate = LayoutInflater.from((Context)this.activity).inflate(2130903066, (ViewGroup)null);
        if (inflate != null) {
            this.searchView = (SearchView)inflate.findViewById(2131427444);
            final ActionBar$LayoutParams actionBar$LayoutParams = new ActionBar$LayoutParams(-1, -2, 8388613);
            this.progressSpinner = (ProgressBar)inflate.findViewById(2131427445);
            this.systemActionBar.setCustomView(inflate, actionBar$LayoutParams);
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
    
    private void setupSearchManager(final NetflixActivity netflixActivity) {
        this.searchView.setSearchableInfo(((SearchManager)netflixActivity.getSystemService("search")).getSearchableInfo(netflixActivity.getComponentName()));
    }
    
    private void setupSearchView() {
        if (this.searchView == null) {
            return;
        }
        this.searchView.setImeOptions(33554435);
        this.searchView.setQueryHint((CharSequence)this.getActivity().getString(2131493192));
        this.searchView.setInputType(8192);
        this.searchView.setQueryRefinementEnabled(true);
        this.searchView.setSubmitButtonEnabled(false);
        this.searchView.setIconifiedByDefault(false);
        this.searchView.setIconified(false);
    }
    
    private void updateBackgroundDrawables() {
        this.replaceBackgroundDrawable("android:id/search_plate", this.getSearchViewBgResId());
        this.replaceBackgroundDrawable("android:id/submit_area", this.getSearchViewRightBgResId());
        final Integer searchCloseButtonTint = this.getSearchCloseButtonTint();
        if (searchCloseButtonTint != null) {
            final Drawable drawableFromSystemId = this.getDrawableFromSystemId("android:id/search_close_btn");
            if (drawableFromSystemId != null) {
                DrawableCompat.setTint(drawableFromSystemId, searchCloseButtonTint);
            }
        }
        final Integer searchVoiceButtonTint = this.getSearchVoiceButtonTint();
        if (searchVoiceButtonTint != null) {
            final Drawable drawableFromSystemId2 = this.getDrawableFromSystemId("android:id/search_voice_btn");
            if (drawableFromSystemId2 != null) {
                DrawableCompat.setTint(drawableFromSystemId2, searchVoiceButtonTint);
            }
        }
    }
    
    public void clearFocus() {
        this.searchView.clearFocus();
        final View focus = this.searchView.findFocus();
        if (focus != null) {
            focus.clearFocus();
        }
    }
    
    protected int getActiveSearchIconResId() {
        return 2130837731;
    }
    
    protected Integer getSearchCloseButtonTint() {
        return null;
    }
    
    protected int getSearchViewBgResId() {
        return 2130837889;
    }
    
    protected int getSearchViewRightBgResId() {
        return 2130837891;
    }
    
    protected int getSearchViewTextColorResId() {
        return 2131230840;
    }
    
    protected Integer getSearchVoiceButtonTint() {
        return null;
    }
    
    public void hideProgressSpinner() {
        if (this.progressSpinner != null) {
            this.progressSpinner.setVisibility(4);
        }
    }
    
    public boolean requestFocus() {
        return this.searchView != null && this.searchView.requestFocus();
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
        if (this.progressSpinner != null) {
            this.progressSpinner.setVisibility(0);
        }
    }
}
