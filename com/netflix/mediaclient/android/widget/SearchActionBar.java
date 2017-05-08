// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.widget.SearchView$OnQueryTextListener;
import android.view.View$OnFocusChangeListener;
import com.netflix.mediaclient.util.ViewUtils;
import android.app.SearchManager;
import android.annotation.SuppressLint;
import android.view.View;
import android.support.v7.app.ActionBar$LayoutParams;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.graphics.drawable.Drawable;
import android.view.View$OnTouchListener;
import android.view.GestureDetector$OnGestureListener;
import com.netflix.mediaclient.util.ViewUtils$ClickGestureDetector;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.ui.search.VoiceSearchABTestUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.ProgressBar;
import android.widget.EditText;
import android.view.GestureDetector;

public class SearchActionBar extends NetflixActionBar
{
    private static final String TAG = "SearchActionBar";
    private GestureDetector clickDetector;
    private EditText editTextView;
    private ProgressBar progressSpinner;
    protected SearchView searchView;
    private boolean showVoiceSearch;
    private ImageView voiceSearchBtn;
    
    public SearchActionBar(final NetflixActivity netflixActivity) {
        super(netflixActivity, true);
        this.showVoiceSearch = VoiceSearchABTestUtils.showVoiceSearchInActionBar((Context)netflixActivity);
        this.initViews();
        this.setupSearchView();
        this.setupSearchManager(netflixActivity);
        this.configureSearchViewTextView();
        this.configureSearchViewIcon();
        this.configureVoiceSearchExperience();
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
        this.editTextView = (EditText)this.searchView.findViewById(this.getActivity().getResources().getIdentifier("android:id/search_src_text", (String)null, (String)null));
        if (this.editTextView != null) {
            final int color = this.searchView.getResources().getColor(this.getSearchViewTextColorResId());
            final int color2 = this.searchView.getResources().getColor(2131624178);
            this.editTextView.setTextSize((float)this.activity.getResources().getInteger(2131492889));
            this.editTextView.setHintTextColor(color2);
            this.editTextView.setTextColor(color);
            this.editTextView.setImeOptions(33554432);
        }
    }
    
    private void configureVoiceSearchExperience() {
        this.voiceSearchBtn = (ImageView)this.searchView.findViewById(this.getActivity().getResources().getIdentifier("android:id/search_voice_btn", (String)null, (String)null));
        if (this.voiceSearchBtn == null) {
            Log.w("SearchActionBar", "SPY-8468 - Voice search not available. SearchView doesn't have view with id search_voice_btn");
            ErrorLoggingManager.logHandledException("SPY-8468 - Voice search not available. SearchView doesn't have view with id search_voice_btn");
            return;
        }
        if (this.showVoiceSearch) {
            this.clickDetector = new GestureDetector((Context)this.getActivity(), (GestureDetector$OnGestureListener)new ViewUtils$ClickGestureDetector());
            this.voiceSearchBtn.setOnTouchListener((View$OnTouchListener)new SearchActionBar$1(this));
            return;
        }
        this.voiceSearchBtn.setEnabled(false);
        this.voiceSearchBtn.setImageDrawable((Drawable)null);
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
        final View inflate = LayoutInflater.from((Context)this.activity).inflate(2130903069, (ViewGroup)null);
        if (inflate != null) {
            this.searchView = (SearchView)inflate.findViewById(2131689614);
            final ActionBar$LayoutParams actionBar$LayoutParams = new ActionBar$LayoutParams(-1, -2, 8388613);
            this.progressSpinner = (ProgressBar)inflate.findViewById(2131689615);
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
        this.searchView.setQueryHint((CharSequence)this.getActivity().getString(2131231410));
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
                ViewUtils.setDrawableTint(drawableFromSystemId, searchCloseButtonTint);
            }
        }
        final Integer searchVoiceButtonTint = this.getSearchVoiceButtonTint();
        if (searchVoiceButtonTint != null) {
            final Drawable drawableFromSystemId2 = this.getDrawableFromSystemId("android:id/search_voice_btn");
            if (drawableFromSystemId2 != null) {
                ViewUtils.setDrawableTint(drawableFromSystemId2, searchVoiceButtonTint);
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
        return 2130837815;
    }
    
    protected Integer getSearchCloseButtonTint() {
        return null;
    }
    
    protected int getSearchViewBgResId() {
        return 2130837974;
    }
    
    protected int getSearchViewRightBgResId() {
        if (this.showVoiceSearch) {
            return 2130837976;
        }
        return 2131624173;
    }
    
    protected int getSearchViewTextColorResId() {
        return 2131624117;
    }
    
    protected Integer getSearchVoiceButtonTint() {
        return null;
    }
    
    public ImageView getVoiceSearchBtn() {
        return this.voiceSearchBtn;
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
        if (this.editTextView != null) {
            this.editTextView.setOnFocusChangeListener(onFocusChangeListener);
        }
    }
    
    public void setOnQueryTextListener(final SearchView$OnQueryTextListener onQueryTextListener) {
        this.searchView.setOnQueryTextListener(onQueryTextListener);
    }
    
    public void setOnTouchTextListener(final View$OnTouchListener onTouchListener) {
        if (this.editTextView != null) {
            this.editTextView.setOnTouchListener(onTouchListener);
        }
    }
    
    public void setQuery(final String s, final boolean b) {
        this.searchView.setQuery((CharSequence)s, b);
    }
    
    public void setSearchQueryHint(final String queryHint) {
        if (this.searchView != null) {
            this.searchView.setQueryHint((CharSequence)queryHint);
        }
    }
    
    public void showProgressSpinner() {
        if (this.progressSpinner != null) {
            this.progressSpinner.setVisibility(0);
        }
    }
}
