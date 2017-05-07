// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.Rect;
import android.view.View$MeasureSpec;
import android.graphics.drawable.Drawable;
import android.view.inputmethod.InputMethodManager;
import android.text.Editable;
import android.text.TextUtils;
import android.content.Context;
import android.support.v7.appcompat.R$dimen;
import android.widget.AutoCompleteTextView;
import android.content.Intent;
import android.net.Uri;
import android.os.Build$VERSION;
import android.app.SearchableInfo;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.ImageView;
import android.os.Bundle;
import android.support.v7.view.CollapsibleActionView;

public class SearchView extends LinearLayoutCompat implements CollapsibleActionView
{
    static final SearchView$AutoCompleteTextViewReflector HIDDEN_METHOD_INVOKER;
    private static final boolean IS_AT_LEAST_FROYO;
    private Bundle mAppSearchData;
    private boolean mClearingFocus;
    private final ImageView mCloseButton;
    private int mCollapsedImeOptions;
    private boolean mExpandedInActionView;
    private boolean mIconified;
    private boolean mIconifiedByDefault;
    private int mMaxWidth;
    private SearchView$OnCloseListener mOnCloseListener;
    private SearchView$OnQueryTextListener mOnQueryChangeListener;
    private View$OnClickListener mOnSearchClickListener;
    private final SearchView$SearchAutoComplete mQueryTextView;
    private Runnable mReleaseCursorRunnable;
    private final ImageView mSearchButton;
    private final View mSearchEditFrame;
    private final ImageView mSearchHintIcon;
    private SearchableInfo mSearchable;
    private Runnable mShowImeRunnable;
    private final View mSubmitArea;
    private final ImageView mSubmitButton;
    private boolean mSubmitButtonEnabled;
    private final Runnable mUpdateDrawableStateRunnable;
    private CharSequence mUserQuery;
    private final ImageView mVoiceButton;
    private boolean mVoiceButtonEnabled;
    
    static {
        IS_AT_LEAST_FROYO = (Build$VERSION.SDK_INT >= 8);
        HIDDEN_METHOD_INVOKER = new SearchView$AutoCompleteTextViewReflector();
    }
    
    private Intent createIntent(final String s, final Uri data, final String s2, final String s3, final int n, final String s4) {
        final Intent intent = new Intent(s);
        intent.addFlags(268435456);
        if (data != null) {
            intent.setData(data);
        }
        intent.putExtra("user_query", this.mUserQuery);
        if (s3 != null) {
            intent.putExtra("query", s3);
        }
        if (s2 != null) {
            intent.putExtra("intent_extra_data_key", s2);
        }
        if (this.mAppSearchData != null) {
            intent.putExtra("app_data", this.mAppSearchData);
        }
        if (n != 0) {
            intent.putExtra("action_key", n);
            intent.putExtra("action_msg", s4);
        }
        if (SearchView.IS_AT_LEAST_FROYO) {
            intent.setComponent(this.mSearchable.getSearchActivity());
        }
        return intent;
    }
    
    private void dismissSuggestions() {
        this.mQueryTextView.dismissDropDown();
    }
    
    private void forceSuggestionQuery() {
        SearchView.HIDDEN_METHOD_INVOKER.doBeforeTextChanged(this.mQueryTextView);
        SearchView.HIDDEN_METHOD_INVOKER.doAfterTextChanged(this.mQueryTextView);
    }
    
    private int getPreferredWidth() {
        return this.getContext().getResources().getDimensionPixelSize(R$dimen.abc_search_view_preferred_width);
    }
    
    static boolean isLandscapeMode(final Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }
    
    private boolean isSubmitAreaEnabled() {
        return (this.mSubmitButtonEnabled || this.mVoiceButtonEnabled) && !this.isIconified();
    }
    
    private void launchQuerySearch(final int n, final String s, final String s2) {
        this.getContext().startActivity(this.createIntent("android.intent.action.SEARCH", null, null, s2, n, s));
    }
    
    private void onCloseClicked() {
        if (TextUtils.isEmpty((CharSequence)this.mQueryTextView.getText())) {
            if (this.mIconifiedByDefault && (this.mOnCloseListener == null || !this.mOnCloseListener.onClose())) {
                this.clearFocus();
                this.updateViewsVisibility(true);
            }
            return;
        }
        this.mQueryTextView.setText((CharSequence)"");
        this.mQueryTextView.requestFocus();
        this.setImeVisibility(true);
    }
    
    private void onSearchClicked() {
        this.updateViewsVisibility(false);
        this.mQueryTextView.requestFocus();
        this.setImeVisibility(true);
        if (this.mOnSearchClickListener != null) {
            this.mOnSearchClickListener.onClick((View)this);
        }
    }
    
    private void onSubmitQuery() {
        final Editable text = this.mQueryTextView.getText();
        if (text != null && TextUtils.getTrimmedLength((CharSequence)text) > 0 && (this.mOnQueryChangeListener == null || !this.mOnQueryChangeListener.onQueryTextSubmit(((CharSequence)text).toString()))) {
            if (this.mSearchable != null) {
                this.launchQuerySearch(0, null, ((CharSequence)text).toString());
            }
            this.setImeVisibility(false);
            this.dismissSuggestions();
        }
    }
    
    private void postUpdateFocusedState() {
        this.post(this.mUpdateDrawableStateRunnable);
    }
    
    private void setImeVisibility(final boolean b) {
        if (b) {
            this.post(this.mShowImeRunnable);
        }
        else {
            this.removeCallbacks(this.mShowImeRunnable);
            final InputMethodManager inputMethodManager = (InputMethodManager)this.getContext().getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(this.getWindowToken(), 0);
            }
        }
    }
    
    private void updateCloseButton() {
        final boolean b = true;
        final boolean b2 = false;
        boolean b3;
        if (!TextUtils.isEmpty((CharSequence)this.mQueryTextView.getText())) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        boolean b4 = b;
        if (!b3) {
            b4 = (this.mIconifiedByDefault && !this.mExpandedInActionView && b);
        }
        final ImageView mCloseButton = this.mCloseButton;
        int visibility;
        if (b4) {
            visibility = (b2 ? 1 : 0);
        }
        else {
            visibility = 8;
        }
        mCloseButton.setVisibility(visibility);
        final Drawable drawable = this.mCloseButton.getDrawable();
        int[] state;
        if (b3) {
            state = SearchView.ENABLED_STATE_SET;
        }
        else {
            state = SearchView.EMPTY_STATE_SET;
        }
        drawable.setState(state);
    }
    
    private void updateSubmitArea() {
        int visibility = 8;
        Label_0036: {
            if (this.isSubmitAreaEnabled()) {
                if (this.mSubmitButton.getVisibility() != 0) {
                    visibility = visibility;
                    if (this.mVoiceButton.getVisibility() != 0) {
                        break Label_0036;
                    }
                }
                visibility = 0;
            }
        }
        this.mSubmitArea.setVisibility(visibility);
    }
    
    private void updateSubmitButton(final boolean b) {
        int visibility;
        final int n = visibility = 8;
        Label_0045: {
            if (this.mSubmitButtonEnabled) {
                visibility = n;
                if (this.isSubmitAreaEnabled()) {
                    visibility = n;
                    if (this.hasFocus()) {
                        if (!b) {
                            visibility = n;
                            if (this.mVoiceButtonEnabled) {
                                break Label_0045;
                            }
                        }
                        visibility = 0;
                    }
                }
            }
        }
        this.mSubmitButton.setVisibility(visibility);
    }
    
    private void updateViewsVisibility(final boolean mIconified) {
        final boolean b = true;
        final int n = 8;
        this.mIconified = mIconified;
        int visibility;
        if (mIconified) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        final boolean b2 = !TextUtils.isEmpty((CharSequence)this.mQueryTextView.getText());
        this.mSearchButton.setVisibility(visibility);
        this.updateSubmitButton(b2);
        final View mSearchEditFrame = this.mSearchEditFrame;
        int visibility2;
        if (mIconified) {
            visibility2 = 8;
        }
        else {
            visibility2 = 0;
        }
        mSearchEditFrame.setVisibility(visibility2);
        final ImageView mSearchHintIcon = this.mSearchHintIcon;
        int visibility3;
        if (this.mIconifiedByDefault) {
            visibility3 = n;
        }
        else {
            visibility3 = 0;
        }
        mSearchHintIcon.setVisibility(visibility3);
        this.updateCloseButton();
        this.updateVoiceButton(!b2 && b);
        this.updateSubmitArea();
    }
    
    private void updateVoiceButton(final boolean b) {
        int visibility;
        if (this.mVoiceButtonEnabled && !this.isIconified() && b) {
            visibility = 0;
            this.mSubmitButton.setVisibility(8);
        }
        else {
            visibility = 8;
        }
        this.mVoiceButton.setVisibility(visibility);
    }
    
    public void clearFocus() {
        this.mClearingFocus = true;
        this.setImeVisibility(false);
        super.clearFocus();
        this.mQueryTextView.clearFocus();
        this.mClearingFocus = false;
    }
    
    public boolean isIconified() {
        return this.mIconified;
    }
    
    @Override
    public void onActionViewCollapsed() {
        this.setQuery("", false);
        this.clearFocus();
        this.updateViewsVisibility(true);
        this.mQueryTextView.setImeOptions(this.mCollapsedImeOptions);
        this.mExpandedInActionView = false;
    }
    
    @Override
    public void onActionViewExpanded() {
        if (this.mExpandedInActionView) {
            return;
        }
        this.mExpandedInActionView = true;
        this.mCollapsedImeOptions = this.mQueryTextView.getImeOptions();
        this.mQueryTextView.setImeOptions(this.mCollapsedImeOptions | 0x2000000);
        this.mQueryTextView.setText((CharSequence)"");
        this.setIconified(false);
    }
    
    protected void onDetachedFromWindow() {
        this.removeCallbacks(this.mUpdateDrawableStateRunnable);
        this.post(this.mReleaseCursorRunnable);
        super.onDetachedFromWindow();
    }
    
    @Override
    protected void onMeasure(int n, final int n2) {
        if (this.isIconified()) {
            super.onMeasure(n, n2);
            return;
        }
        final int mode = View$MeasureSpec.getMode(n);
        final int size = View$MeasureSpec.getSize(n);
        switch (mode) {
            default: {
                n = size;
                break;
            }
            case Integer.MIN_VALUE: {
                if (this.mMaxWidth > 0) {
                    n = Math.min(this.mMaxWidth, size);
                    break;
                }
                n = Math.min(this.getPreferredWidth(), size);
                break;
            }
            case 1073741824: {
                n = size;
                if (this.mMaxWidth > 0) {
                    n = Math.min(this.mMaxWidth, size);
                    break;
                }
                break;
            }
            case 0: {
                if (this.mMaxWidth > 0) {
                    n = this.mMaxWidth;
                    break;
                }
                n = this.getPreferredWidth();
                break;
            }
        }
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(n, 1073741824), n2);
    }
    
    void onTextFocusChanged() {
        this.updateViewsVisibility(this.isIconified());
        this.postUpdateFocusedState();
        if (this.mQueryTextView.hasFocus()) {
            this.forceSuggestionQuery();
        }
    }
    
    public void onWindowFocusChanged(final boolean b) {
        super.onWindowFocusChanged(b);
        this.postUpdateFocusedState();
    }
    
    public boolean requestFocus(final int n, final Rect rect) {
        if (this.mClearingFocus || !this.isFocusable()) {
            return false;
        }
        if (!this.isIconified()) {
            final boolean requestFocus = this.mQueryTextView.requestFocus(n, rect);
            if (requestFocus) {
                this.updateViewsVisibility(false);
            }
            return requestFocus;
        }
        return super.requestFocus(n, rect);
    }
    
    public void setIconified(final boolean b) {
        if (b) {
            this.onCloseClicked();
            return;
        }
        this.onSearchClicked();
    }
    
    public void setQuery(final CharSequence charSequence, final boolean b) {
        this.mQueryTextView.setText(charSequence);
        if (charSequence != null) {
            this.mQueryTextView.setSelection(this.mQueryTextView.length());
            this.mUserQuery = charSequence;
        }
        if (b && !TextUtils.isEmpty(charSequence)) {
            this.onSubmitQuery();
        }
    }
}
