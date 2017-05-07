// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.Log;
import android.os.Message;
import android.os.Handler;
import android.content.Context;

public class TargetSelector
{
    private static final int MSG_NEW_TARGET_SELECTED = 2;
    private static final int MSG_STICKINESS_TIMEOUT = 1;
    private static final int MSG_TARGET_LASTSEEN_ACTIVE = 3;
    private static final int MSG_TARGET_UPDATE_SELECTED = 4;
    private static final String TAG = "nf_mdxTargetSelector";
    private static final long TARGET_STICKINESS_WINDOW = 12600000L;
    private TargetSelectorInterface mCallback;
    private Context mContext;
    private String mCurrentTarget;
    private Handler mHandler;
    private String mTaregtUuid;
    private String mTargetDialUuid;
    private String mTargetFriendlyName;
    
    public TargetSelector(final Context mContext, final TargetSelectorInterface mCallback) {
        this.mContext = mContext;
        this.mCallback = mCallback;
        this.mHandler = new Handler() {
            public void handleMessage(final Message message) {
                switch (message.what) {
                    default: {
                        Log.e("nf_mdxTargetSelector", "TragetSelector: unknown message");
                    }
                    case 1: {
                        Log.d("nf_mdxTargetSelector", "TragetSelector: target stickiness expired");
                        PreferenceUtils.putLongPref(TargetSelector.this.mContext, "mdx_target_lastactive", System.currentTimeMillis());
                        PreferenceUtils.putStringPref(TargetSelector.this.mContext, "mdx_target_uuid", new String());
                        PreferenceUtils.putStringPref(TargetSelector.this.mContext, "mdx_selected_uuid", new String());
                        PreferenceUtils.putStringPref(TargetSelector.this.mContext, "mdx_selected_dialuuid", new String());
                        PreferenceUtils.putStringPref(TargetSelector.this.mContext, "mdx_selected_fname", new String());
                        TargetSelector.this.mCallback.onStickinessExpired();
                    }
                    case 2: {
                        if (Log.isLoggable("nf_mdxTargetSelector", 3)) {
                            Log.d("nf_mdxTargetSelector", "TragetSelector: new target is selected " + TargetSelector.this.mCurrentTarget);
                        }
                        PreferenceUtils.putLongPref(TargetSelector.this.mContext, "mdx_target_lastactive", System.currentTimeMillis());
                    }
                    case 4: {
                        if (Log.isLoggable("nf_mdxTargetSelector", 3)) {
                            Log.d("nf_mdxTargetSelector", "TragetSelector: upadte selected target " + TargetSelector.this.mCurrentTarget + " : " + TargetSelector.this.mTaregtUuid + " : " + TargetSelector.this.mTargetDialUuid + " : " + TargetSelector.this.mTargetFriendlyName);
                        }
                        PreferenceUtils.putStringPref(TargetSelector.this.mContext, "mdx_target_uuid", TargetSelector.this.mCurrentTarget);
                        PreferenceUtils.putStringPref(TargetSelector.this.mContext, "mdx_selected_uuid", TargetSelector.this.mTaregtUuid);
                        PreferenceUtils.putStringPref(TargetSelector.this.mContext, "mdx_selected_dialuuid", TargetSelector.this.mTargetDialUuid);
                        PreferenceUtils.putStringPref(TargetSelector.this.mContext, "mdx_selected_fname", TargetSelector.this.mTargetFriendlyName);
                    }
                    case 3: {
                        PreferenceUtils.putLongPref(TargetSelector.this.mContext, "mdx_target_lastactive", System.currentTimeMillis());
                    }
                }
            }
        };
        final long n = System.currentTimeMillis() - PreferenceUtils.getLongPref(this.mContext, "mdx_target_lastactive", 0L);
        if (n > 12600000L) {
            this.mCurrentTarget = new String();
        }
        else {
            this.mCurrentTarget = PreferenceUtils.getStringPref(this.mContext, "mdx_target_uuid", new String());
            this.mTaregtUuid = PreferenceUtils.getStringPref(this.mContext, "mdx_selected_uuid", new String());
            this.mTargetDialUuid = PreferenceUtils.getStringPref(this.mContext, "mdx_selected_dialuuid", new String());
            this.mTargetFriendlyName = PreferenceUtils.getStringPref(this.mContext, "mdx_selected_fname", new String());
            this.startCountDown(12600000L - n);
        }
        this.mCallback.onTargetSelectorLoaded(this.mCurrentTarget, this.mTaregtUuid, this.mTargetDialUuid, this.mTargetFriendlyName);
        if (Log.isLoggable("nf_mdxTargetSelector", 3)) {
            Log.d("nf_mdxTargetSelector", "TragetSelector: init complete.  mCurrentTarget is " + this.mCurrentTarget + " : " + this.mTaregtUuid + " : " + this.mTargetDialUuid + " : " + this.mTargetFriendlyName);
        }
    }
    
    private void startCountDown(final long n) {
        Log.d("nf_mdxTargetSelector", "TragetSelector: startCountDown " + n);
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, n);
    }
    
    public String getCurrentTarget() {
        return this.mCurrentTarget;
    }
    
    public void selectNewTarget(final String mCurrentTarget, final String mTaregtUuid, final String mTargetDialUuid, final String mTargetFriendlyName) {
        this.mCurrentTarget = mCurrentTarget;
        this.mTaregtUuid = mTaregtUuid;
        this.mTargetDialUuid = mTargetDialUuid;
        this.mTargetFriendlyName = mTargetFriendlyName;
        this.mHandler.sendEmptyMessage(2);
        if (StringUtils.isEmpty(mCurrentTarget)) {
            this.mHandler.removeMessages(1);
            return;
        }
        this.startCountDown(12600000L);
    }
    
    public void targetBecomeActive(final String s) {
        this.mHandler.sendEmptyMessage(3);
        this.mHandler.removeMessages(1);
    }
    
    public void targetBecomeInactive(final String s) {
        this.mHandler.sendEmptyMessage(3);
        this.startCountDown(12600000L);
    }
    
    public void updateSelectedTarget(final String mCurrentTarget, final String mTaregtUuid, final String mTargetDialUuid, final String mTargetFriendlyName) {
        this.mCurrentTarget = mCurrentTarget;
        this.mTaregtUuid = mTaregtUuid;
        this.mTargetDialUuid = mTargetDialUuid;
        this.mTargetFriendlyName = mTargetFriendlyName;
        this.mHandler.sendEmptyMessage(4);
    }
    
    public interface TargetSelectorInterface
    {
        void onStickinessExpired();
        
        void onTargetSelectorLoaded(final String p0, final String p1, final String p2, final String p3);
    }
}
