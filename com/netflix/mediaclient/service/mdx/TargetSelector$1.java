// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.NetflixPreference;
import android.os.Message;
import android.os.Handler;

class TargetSelector$1 extends Handler
{
    final /* synthetic */ TargetSelector this$0;
    
    TargetSelector$1(final TargetSelector this$0) {
        this.this$0 = this$0;
    }
    
    public void handleMessage(final Message message) {
        final NetflixPreference netflixPreference = new NetflixPreference(this.this$0.mContext);
        switch (message.what) {
            default: {
                Log.e("nf_mdxTargetSelector", "TargetSelector: unknown message");
            }
            case 1: {
                Log.d("nf_mdxTargetSelector", "TargetSelector: target stickiness expired");
                netflixPreference.putLongPref("mdx_target_lastactive", System.currentTimeMillis());
                netflixPreference.putStringPref("mdx_target_uuid", new String());
                netflixPreference.putStringPref("mdx_selected_uuid", new String());
                netflixPreference.putStringPref("mdx_selected_dialuuid", new String());
                netflixPreference.putStringPref("mdx_selected_fname", new String());
                netflixPreference.commit();
                this.this$0.mCallback.onStickinessExpired();
            }
            case 2: {
                if (Log.isLoggable()) {
                    Log.d("nf_mdxTargetSelector", "TargetSelector: new target is selected " + this.this$0.mCurrentTarget);
                }
                netflixPreference.putLongPref("mdx_target_lastactive", System.currentTimeMillis());
                netflixPreference.commit();
            }
            case 4: {
                if (Log.isLoggable()) {
                    Log.d("nf_mdxTargetSelector", "TargetSelector: upadte selected target " + this.this$0.mCurrentTarget + " : " + this.this$0.mTaregtUuid + " : " + this.this$0.mTargetDialUuid + " : " + this.this$0.mTargetFriendlyName);
                }
                netflixPreference.putStringPref("mdx_target_uuid", this.this$0.mCurrentTarget);
                netflixPreference.putStringPref("mdx_selected_uuid", this.this$0.mTaregtUuid);
                netflixPreference.putStringPref("mdx_selected_dialuuid", this.this$0.mTargetDialUuid);
                netflixPreference.putStringPref("mdx_selected_fname", this.this$0.mTargetFriendlyName);
                netflixPreference.commit();
            }
            case 3: {
                netflixPreference.putLongPref("mdx_target_lastactive", System.currentTimeMillis());
                netflixPreference.commit();
            }
        }
    }
}
