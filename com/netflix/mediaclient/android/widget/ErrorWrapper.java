// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.content.Context;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;
import android.widget.TextView;
import android.view.View;

public class ErrorWrapper
{
    private static final String TAG = "ErrorWrapper";
    private ErrorWrapper$Callback callback;
    private final View errorGroup;
    private final TextView errorMsg;
    private final NetflixTextButton retryBtn;
    private final View$OnClickListener retryClickListener;
    private boolean showRetry;
    private final NetflixTextButton viewMyDownloads;
    
    public ErrorWrapper(final View view, final ErrorWrapper$Callback callback) {
        this.showRetry = true;
        this.retryClickListener = (View$OnClickListener)new ErrorWrapper$2(this);
        this.callback = callback;
        (this.errorGroup = view.findViewById(2131689850)).setVisibility(0);
        this.errorMsg = (TextView)view.findViewById(2131689851);
        (this.retryBtn = (NetflixTextButton)this.errorGroup.findViewById(2131689852)).setOnClickListener(this.retryClickListener);
        this.viewMyDownloads = (NetflixTextButton)this.errorGroup.findViewById(2131689853);
        if (this.viewMyDownloads != null) {
            this.viewMyDownloads.setOnClickListener((View$OnClickListener)new ErrorWrapper$1(this));
        }
        final NetflixActivity netflixActivity = AndroidUtils.getContextAs(view.getContext(), NetflixActivity.class);
        if (netflixActivity != null && BrowseExperience.showKidsExperience()) {
            this.configureViewsForKidsExperience(netflixActivity);
        }
        this.setRetryVisibility();
    }
    
    private void configureViewsForKidsExperience(final NetflixActivity netflixActivity) {
        final Resources resources = netflixActivity.getResources();
        this.errorMsg.setTextColor(resources.getColor(2131624006));
        ViewUtils.setTextViewSizeByRes(this.errorMsg, 2131361881);
        ViewUtils.setTextViewToBold(this.errorMsg);
        this.retryBtn.applyFrom(2131427596);
        this.retryBtn.getLayoutParams().height = resources.getDimensionPixelOffset(2131362188);
        this.retryBtn.setAllCaps(false);
        ViewUtils.setTextViewSizeByRes((TextView)this.retryBtn, 2131361875);
        final Drawable drawable = resources.getDrawable(2130837744);
        final int dipToPixels = AndroidUtils.dipToPixels((Context)netflixActivity, 32);
        final int dipToPixels2 = AndroidUtils.dipToPixels((Context)netflixActivity, 8);
        drawable.setBounds(dipToPixels2, 0, dipToPixels + dipToPixels2, dipToPixels);
        this.retryBtn.setCompoundDrawables(null, null, drawable, null);
        this.retryBtn.setCompoundDrawablePadding(AndroidUtils.dipToPixels((Context)netflixActivity, 8));
        this.viewMyDownloads.applyFrom(2131427596);
        this.viewMyDownloads.getLayoutParams().height = resources.getDimensionPixelOffset(2131362188);
        this.viewMyDownloads.setAllCaps(false);
        this.viewMyDownloads.setText(2131231325);
        ViewUtils.setTextViewSizeByRes((TextView)this.viewMyDownloads, 2131361875);
        this.errorGroup.setBackgroundColor(-1);
    }
    
    private void setRetryVisibility() {
        final NetflixTextButton retryBtn = this.retryBtn;
        int visibility;
        if (this.callback == null || !this.showRetry) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        retryBtn.setVisibility(visibility);
    }
    
    public TextView getErrorMessageTextView() {
        return this.errorMsg;
    }
    
    public void hide(final boolean b) {
        AnimationUtils.hideView(this.errorGroup, b);
    }
    
    public void showErrorView(final int text, final boolean showRetry, final boolean b) {
        this.errorMsg.setText(text);
        this.showRetry = showRetry;
        AnimationUtils.showView(this.errorGroup, b);
        this.setRetryVisibility();
    }
    
    public void showErrorView(final CharSequence text, final int text2, final ErrorWrapper$Callback callback) {
        this.showRetry = true;
        this.errorMsg.setText(text);
        this.retryBtn.setText(text2);
        AnimationUtils.showView(this.errorGroup, false);
        this.setRetryVisibility();
        this.callback = callback;
    }
    
    public void showErrorView(final boolean b) {
        AnimationUtils.showView(this.errorGroup, b);
        this.setRetryVisibility();
    }
    
    public void showViewMyDownloadsButton() {
        if (this.viewMyDownloads != null) {
            this.viewMyDownloads.setVisibility(0);
        }
    }
}
