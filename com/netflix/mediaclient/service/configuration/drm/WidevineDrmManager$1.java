// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import com.netflix.mediaclient.util.CryptoUtils;
import java.util.Arrays;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.android.app.BackgroundTask;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.media.UnsupportedSchemeException;
import android.media.MediaDrm$CryptoSession;
import android.util.Base64;
import android.media.NotProvisionedException;
import com.netflix.mediaclient.StatusCode;
import java.util.HashMap;
import android.media.MediaDrm$KeyRequest;
import com.netflix.mediaclient.util.PreferenceUtils;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import android.content.Context;
import android.media.MediaDrm;
import java.util.UUID;
import android.annotation.TargetApi;
import android.media.MediaDrm$OnEventListener;
import android.media.DeniedByServerException;
import android.os.Build;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.Log;

class WidevineDrmManager$1 implements WidevineDrmManager$WidewineProvisiongCallback
{
    final /* synthetic */ WidevineDrmManager this$0;
    final /* synthetic */ String val$url;
    
    WidevineDrmManager$1(final WidevineDrmManager this$0, final String val$url) {
        this.this$0 = this$0;
        this.val$url = val$url;
    }
    
    @Override
    public void done(final byte[] array) {
        if (array != null) {
            if (Log.isLoggable(WidevineDrmManager.TAG, 3)) {
                Log.d(WidevineDrmManager.TAG, "Got CDM provisiong " + new String(array));
            }
            try {
                this.this$0.drm.provideProvisionResponse(array);
                this.this$0.init();
                return;
            }
            catch (DeniedByServerException ex) {
                Log.d(WidevineDrmManager.TAG, "Server declined Widewine provisioning request. Server URL: " + this.val$url, (Throwable)ex);
                this.this$0.mCallback.drmError(CommonStatus.DRM_FAILURE_GOOGLE_CDM_PROVISIONG_DENIED);
                this.this$0.mErrorLogging.logHandledException(new Exception("Server declined Widewine provisioning request. Server URL: " + this.val$url + ". Build: " + Build.DISPLAY, (Throwable)ex));
                return;
            }
            catch (Throwable t) {
                Log.d(WidevineDrmManager.TAG, "Fatal error on seting Widewine provisioning response", t);
                this.this$0.mErrorLogging.logHandledException(new Exception("Fatal error on seting Widewine provisioning response received from URL: " + this.val$url + ". Build: " + Build.DISPLAY, t));
                if (this.this$0.mCallback != null) {
                    this.this$0.mCallback.drmError(CommonStatus.DRM_FAILURE_CDM);
                }
                return;
            }
        }
        Log.e(WidevineDrmManager.TAG, "Failed to get provisiong certificate");
        this.this$0.mCallback.drmError(CommonStatus.DRM_FAILURE_CDM);
        this.this$0.mErrorLogging.logHandledException("Failed to get provisiong certificate. Response is null from URL " + this.val$url);
    }
}
