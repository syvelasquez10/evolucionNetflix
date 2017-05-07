// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ViewUtils;
import android.widget.ImageButton;
import android.view.View;
import android.app.Activity;
import com.netflix.mediaclient.ui.Section;

public class Social extends Section
{
    private static final String TAG = "playcard";
    protected SocialProviderCallback mCallback;
    private final Activity mContext;
    private boolean mIsShared;
    private boolean mIsSharingDisabled;
    private boolean mMessageVisible;
    private View mNotSharingButton;
    private View mNotSharingLabel;
    protected ImageButton mShareButton;
    
    public Social(final Activity mContext, final SocialProviderCallback mCallback) {
        super(mContext);
        this.mIsShared = true;
        this.mIsSharingDisabled = true;
        this.mContext = mContext;
        this.mCallback = mCallback;
        this.initSocial();
    }
    
    private void doNotShare() {
        this.mIsShared = false;
        this.mCallback.extendTimeoutTimer();
        this.updateSharingStatusPosition(this.mNotSharingButton, false);
        this.updateSharingStatusPosition(this.mNotSharingLabel, true);
        if (this.mShareButton != null) {
            this.setDisableOverlayForImageButton((View)this.mShareButton);
        }
        if (this.mCallback != null) {
            this.mCallback.doNotShare();
        }
    }
    
    private int getMarginLeftForSharingStatus(final View view) {
        final int[] array = new int[2];
        this.mShareButton.getLocationInWindow(array);
        final int n = array[0];
        final int n2 = this.mShareButton.getMeasuredWidth() / 2;
        final int measuredWidth = view.getMeasuredWidth();
        final int n3 = n + n2 - measuredWidth / 2;
        final int x = ViewUtils.getDisplaySize(this.mContext).x;
        if (n3 + measuredWidth > x) {
            Log.d("playcard", "Adjusting position of window");
            return x - measuredWidth - 2;
        }
        Log.d("playcard", "No adjustment to address corner");
        return n3;
    }
    
    private void initSocial() {
        this.mShareButton = (ImageButton)this.mContext.findViewById(2131230975);
        if (this.mShareButton != null) {
            this.mShareButton.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    Log.d("playcard", "Touch facebook!");
                    Social.this.toggleMessageVisibility();
                }
            });
        }
        else {
            Log.e("playcard", "Social button not found!");
        }
        this.mNotSharingButton = this.mContext.findViewById(2131231004);
        this.mNotSharingLabel = this.mContext.findViewById(2131231005);
        if (this.mNotSharingButton != null) {
            this.mNotSharingButton.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
                public boolean onTouch(final View view, final MotionEvent motionEvent) {
                    Social.this.doNotShare();
                    return true;
                }
            });
            return;
        }
        Log.e("playcard", "Not sharing button not found!");
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
            this.enableButton((View)this.mShareButton, enabled);
            if (this.mNotSharingButton != null) {
                this.mNotSharingButton.setEnabled(enabled);
            }
        }
    }
    
    @Override
    public void destroy() {
        if (this.mShareButton != null) {
            this.mShareButton.setOnTouchListener((View$OnTouchListener)null);
        }
        if (this.mNotSharingButton != null) {
            this.mNotSharingButton.setOnTouchListener((View$OnTouchListener)null);
        }
    }
    
    @Override
    public void hide() {
        if (this.mShareButton != null) {
            this.mShareButton.setVisibility(8);
            this.mShareButton.clearAnimation();
        }
        if (this.mNotSharingButton != null) {
            this.mNotSharingButton.setVisibility(4);
        }
        if (this.mNotSharingLabel != null) {
            this.mNotSharingLabel.setVisibility(4);
        }
    }
    
    public boolean isShared() {
        return this.mIsShared;
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
        ViewUtils.setVisibility((View)this.mShareButton, true);
        this.updateSharingStatusPosition(this.mNotSharingButton, this.mMessageVisible);
    }
    
    public interface SocialProviderCallback
    {
        void doNotShare();
        
        void extendTimeoutTimer();
    }
}
