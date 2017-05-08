// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.crypto;

import android.annotation.SuppressLint;
import android.media.MediaDrm;
import com.netflix.mediaclient.util.AndroidUtils;
import android.media.NotProvisionedException;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.content.Context;
import com.netflix.mediaclient.util.MediaDrmUtils;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.UUID;
import android.annotation.TargetApi;
import android.media.DeniedByServerException;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.Log;

class MSLWidevineCryptoManager$1 implements MSLWidevineCryptoManager$WidewineProvisionCallback
{
    final /* synthetic */ MSLWidevineCryptoManager this$0;
    final /* synthetic */ String val$url;
    
    MSLWidevineCryptoManager$1(final MSLWidevineCryptoManager this$0, final String val$url) {
        this.this$0 = this$0;
        this.val$url = val$url;
    }
    
    @Override
    public void abort() {
        Log.e("nf_msl", "Blacklisted Widevine plugin? Do NOT use it!");
        this.this$0.handleCryptoFallback();
        this.this$0.mCallback.drmError(CommonStatus.DRM_FAILURE_GOOGLE_DECLINED_PROVISIONING);
        ErrorLoggingManager.logHandledException("15002. Provisiong failed with status code 400 " + this.val$url);
    }
    
    @Override
    public void done(final byte[] array) {
        if (array != null) {
            if (Log.isLoggable()) {
                Log.d("nf_msl", "Got CDM provisiong " + new String(array));
            }
            try {
                this.this$0.mDrm.provideProvisionResponse(array);
                this.this$0.init();
                return;
            }
            catch (DeniedByServerException ex) {
                Log.e("nf_msl", (Throwable)ex, "Server declined Widewine provisioning request. Server URL: " + this.val$url, new Object[0]);
                this.this$0.mCallback.drmError(CommonStatus.DRM_FAILURE_GOOGLE_CDM_PROVISIONG_DENIED);
                return;
            }
            catch (Throwable t) {
                Log.e("nf_msl", t, "Fatal error on seting Widewine provisioning response", new Object[0]);
                if (this.this$0.mCallback != null) {
                    this.this$0.mCallback.drmError(CommonStatus.DRM_FAILURE_CDM);
                }
                return;
            }
        }
        Log.e("nf_msl", "Failed to get provisiong certificate");
        this.this$0.mCallback.drmError(CommonStatus.DRM_FAILURE_CDM);
    }
}
