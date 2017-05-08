// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import com.netflix.mediaclient.util.CryptoUtils;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import java.util.Arrays;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;
import android.media.MediaDrm$CryptoSession;
import android.util.Base64;
import com.netflix.mediaclient.StatusCode;
import android.media.NotProvisionedException;
import java.util.HashMap;
import android.media.MediaDrm$KeyRequest;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.util.MediaDrmUtils;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.media.MediaDrm;
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
    public void abort() {
        Log.e(WidevineDrmManager.TAG, "Blacklisted Widevine plugin? Do NOT use it!");
        this.this$0.handleCryptoFallback();
        this.this$0.mCallback.drmError(CommonStatus.DRM_FAILURE_GOOGLE_DECLINED_PROVISIONING);
        this.this$0.mErrorLogging.logHandledException("15002. Provisiong failed with status code 400 " + this.val$url);
    }
    
    @Override
    public void done(final byte[] array) {
        if (array != null) {
            if (Log.isLoggable()) {
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
