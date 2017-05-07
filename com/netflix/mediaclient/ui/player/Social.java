// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import android.view.View$OnTouchListener;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.MenuItem;
import android.view.View;
import android.app.Activity;

public class Social extends Section
{
    public static final int LAST_MENU_ID = 2;
    private static final String TAG = "playcard";
    private static final int mShareMenuId = 2;
    protected Social$SocialProviderCallback mCallback;
    private final Activity mContext;
    private boolean mIsShared;
    private boolean mIsSharingDisabled;
    private boolean mMessageVisible;
    private View mNotSharingButton;
    private View mNotSharingLabel;
    protected MenuItem mShareButton;
    
    public Social(final Activity mContext, final Social$SocialProviderCallback mCallback) {
        super(mContext);
        this.mIsShared = true;
        this.mIsSharingDisabled = true;
        this.mContext = mContext;
        this.mCallback = mCallback;
    }
    
    private void doNotShare() {
        this.mIsShared = false;
        this.mCallback.extendTimeoutTimer();
        this.updateSharingStatusPosition(this.mNotSharingButton, false);
        this.updateSharingStatusPosition(this.mNotSharingLabel, true);
        if (this.mCallback != null) {
            this.mCallback.doNotShare();
        }
    }
    
    private int getMarginLeftForSharingStatus(final View view) {
        final View viewById = this.mContext.findViewById(2);
        final int[] array = new int[2];
        viewById.getLocationInWindow(array);
        final int n = array[0];
        final int n2 = viewById.getMeasuredWidth() / 2;
        final int measuredWidth = view.getMeasuredWidth();
        final int n3 = n2 + n - measuredWidth / 2;
        final int x = ViewUtils.getDisplaySize(this.mContext).x;
        if (n3 + measuredWidth > x) {
            Log.d("playcard", "Adjusting position of window");
            return x - measuredWidth - 2;
        }
        Log.d("playcard", "No adjustment to address corner");
        return n3;
    }
    
    private void toggleMessageVisibility() {
        boolean mMessageVisible = true;
        if (!this.mMessageVisible) {
            Log.d("playcard", "Make sharing status visible");
            this.updateSharingStatusPosition(this.mNotSharingButton, true);
            this.updateSharingStatusPosition(this.mNotSharingLabel, false);
        }
        else {
            Log.d("playcard", "Make sharing status gone");
            this.mNotSharingButton.setVisibility(4);
            this.mNotSharingLabel.setVisibility(4);
        }
        if (this.mMessageVisible) {
            mMessageVisible = false;
        }
        this.mMessageVisible = mMessageVisible;
    }
    
    private void updateSharingStatusPosition(final View view, final boolean b) {
        if (view != null) {
            final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)view.getLayoutParams();
            final int marginLeftForSharingStatus = this.getMarginLeftForSharingStatus(view);
            if (marginLeftForSharingStatus != layoutParams.leftMargin) {
                layoutParams.setMargins(marginLeftForSharingStatus, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);
            }
            if (b) {
                view.setVisibility(0);
            }
            else {
                view.setVisibility(4);
            }
            view.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        }
    }
    
    @Override
    public void changeActionState(final boolean enabled) {
        if (!this.mIsShared && enabled) {
            Log.d("playcard", "Ignore changeAction state enabled when user choose not to share!");
        }
        else {
            Log.d("playcard", "Facebook button enable " + enabled);
            this.enableButton(this.mShareButton, enabled);
            if (this.mNotSharingButton != null) {
                this.mNotSharingButton.setEnabled(enabled);
            }
        }
    }
    
    @Override
    public void destroy() {
        if (this.mNotSharingButton != null) {
            this.mNotSharingButton.setOnTouchListener((View$OnTouchListener)null);
        }
    }
    
    @Override
    public void hide() {
        if (this.mIsSharingDisabled || !this.mIsShared) {
            Log.d("playcard", "Facebook is disabled or not shared. Hiding share button!");
            ViewUtils.setVisibility(this.mShareButton, false);
        }
        ViewUtils.setVisibleOrGone(this.mNotSharingButton, false);
        ViewUtils.setVisibleOrGone(this.mNotSharingLabel, false);
        this.mMessageVisible = false;
    }
    
    public void initSocial(final Menu menu) {
        (this.mShareButton = menu.add(0, 2, 0, 2131493152)).setIcon(2130837725);
        this.mShareButton.setVisible(false);
        this.mShareButton.setShowAsAction(2);
        this.mShareButton.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new Social$1(this));
        this.mNotSharingButton = this.mContext.findViewById(2131165590);
        this.mNotSharingLabel = this.mContext.findViewById(2131165591);
        if (this.mNotSharingButton != null) {
            this.mNotSharingButton.setOnTouchListener((View$OnTouchListener)new Social$2(this));
            return;
        }
        Log.e("playcard", "Not sharing button not found!");
    }
    
    public void setSharingDisabled(final boolean mIsSharingDisabled) {
        this.mIsSharingDisabled = mIsSharingDisabled;
        this.changeActionState(!mIsSharingDisabled);
    }
    
    @Override
    public void show() {
        if (this.mIsSharingDisabled || !this.mIsShared) {
            Log.d("playcard", "Facebook is disabled or not shared. Do NOT display!");
            return;
        }
        ViewUtils.setVisibility(this.mShareButton, true);
    }
}
