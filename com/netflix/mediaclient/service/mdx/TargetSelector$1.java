// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.Log;
import android.os.Message;
import android.os.Handler;

class TargetSelector$1 extends Handler
{
    final /* synthetic */ TargetSelector this$0;
    
    TargetSelector$1(final TargetSelector this$0) {
        this.this$0 = this$0;
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                Log.e("nf_mdxTargetSelector", "TragetSelector: unknown message");
            }
            case 1: {
                Log.d("nf_mdxTargetSelector", "TragetSelector: target stickiness expired");
                PreferenceUtils.putLongPref(this.this$0.mContext, "mdx_target_lastactive", System.currentTimeMillis());
                PreferenceUtils.putStringPref(this.this$0.mContext, "mdx_target_uuid", new String());
                PreferenceUtils.putStringPref(this.this$0.mContext, "mdx_selected_uuid", new String());
                PreferenceUtils.putStringPref(this.this$0.mContext, "mdx_selected_dialuuid", new String());
                PreferenceUtils.putStringPref(this.this$0.mContext, "mdx_selected_fname", new String());
                this.this$0.mCallback.onStickinessExpired();
            }
            case 2: {
                if (Log.isLoggable("nf_mdxTargetSelector", 3)) {
                    Log.d("nf_mdxTargetSelector", "TragetSelector: new target is selected " + this.this$0.mCurrentTarget);
                }
                PreferenceUtils.putLongPref(this.this$0.mContext, "mdx_target_lastactive", System.currentTimeMillis());
            }
            case 4: {
                if (Log.isLoggable("nf_mdxTargetSelector", 3)) {
                    Log.d("nf_mdxTargetSelector", "TragetSelector: upadte selected target " + this.this$0.mCurrentTarget + " : " + this.this$0.mTaregtUuid + " : " + this.this$0.mTargetDialUuid + " : " + this.this$0.mTargetFriendlyName);
                }
                PreferenceUtils.putStringPref(this.this$0.mContext, "mdx_target_uuid", this.this$0.mCurrentTarget);
                PreferenceUtils.putStringPref(this.this$0.mContext, "mdx_selected_uuid", this.this$0.mTaregtUuid);
                PreferenceUtils.putStringPref(this.this$0.mContext, "mdx_selected_dialuuid", this.this$0.mTargetDialUuid);
                PreferenceUtils.putStringPref(this.this$0.mContext, "mdx_selected_fname", this.this$0.mTargetFriendlyName);
            }
            case 3: {
                PreferenceUtils.putLongPref(this.this$0.mContext, "mdx_target_lastactive", System.currentTimeMillis());
            }
        }
    }
}
