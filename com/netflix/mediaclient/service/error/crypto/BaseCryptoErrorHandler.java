// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.crypto;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.service.configuration.crypto.WidevineErrorDescriptor;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.StatusCode;
import android.content.Context;

abstract class BaseCryptoErrorHandler implements CryptoErrorHandler
{
    protected static String TAG;
    
    static {
        BaseCryptoErrorHandler.TAG = "nf_crypto_error_handler";
    }
    
    protected int getErrorMessageForFatalError() {
        return CryptoErrorManager.INSTANCE.getErrorMessageForFatalError(this.getErrorSource(), this.getStatusCode());
    }
    
    abstract ErrorSource getErrorSource();
    
    Runnable getForceStopTask(final Context context) {
        return new BaseCryptoErrorHandler$2(this, new BaseCryptoErrorHandler$1(this, context), context);
    }
    
    abstract StatusCode getStatusCode();
    
    protected ErrorDescriptor handleKillApp(final Context context, final Throwable t) {
        this.logHandledException(CryptoErrorManager.createMediaDrmErrorMessage(this.getStatusCode(), t));
        return new WidevineErrorDescriptor(context, this.getStatusCode(), this.getForceStopTask(context), this.getErrorMessageForFatalError());
    }
    
    protected ErrorDescriptor handleLogoutUserAndKillApp(final Context context, final Throwable t) {
        this.logHandledException(CryptoErrorManager.createMediaDrmErrorMessage(this.getStatusCode(), t));
        if (((ServiceAgent)CryptoErrorManager.INSTANCE.getUserAgent()).isReady()) {
            Log.d(BaseCryptoErrorHandler.TAG, "UserAgent is ready, log user out regular way.");
            CryptoErrorManager.INSTANCE.getUserAgent().logoutUser();
        }
        else {
            Log.e(BaseCryptoErrorHandler.TAG, "UserAgent is NOT ready, clear app data brute force");
            CryptoErrorManager.INSTANCE.clearApplicationData();
        }
        return new WidevineErrorDescriptor(context, this.getStatusCode(), this.getForceStopTask(context), this.getErrorMessageForFatalError());
    }
    
    protected void logHandledException(final String s) {
        CryptoErrorManager.INSTANCE.getErrorLogger().logHandledException(s);
    }
    
    protected void logHandledException(final Throwable t) {
        CryptoErrorManager.INSTANCE.getErrorLogger().logHandledException(CryptoErrorManager.createMediaDrmErrorMessage(this.getStatusCode(), t));
    }
}
