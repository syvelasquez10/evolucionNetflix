// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.crypto;

import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;
import com.netflix.mediaclient.service.configuration.crypto.CryptoManagerRegistry;
import org.json.JSONArray;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.ui.offline.DownloadButton;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import java.util.Iterator;
import android.util.Log;
import com.netflix.mediaclient.StatusCode;
import java.util.ArrayList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;

public enum CryptoErrorManager
{
    private static long DELTA_MS;
    
    INSTANCE;
    
    private static String TAG;
    private Runnable mActionToExecuteOnExitFromContentRemoval;
    private long mAppStartupTime;
    private ServiceAgent$ConfigurationAgentInterface mConfiguration;
    private Context mContext;
    private IErrorHandler mErrorHandler;
    private ErrorLogging mErrorLogger;
    private List<CryptoErrorManager$FatalCryptoError> mFatalErrors;
    private AtomicBoolean mIgnoreFatalError;
    private OfflineAgentInterface mOfflineAgent;
    private AtomicBoolean mRemovingOfflineContentInProgress;
    private ServiceAgent$UserAgentInterface mUserAgent;
    
    static {
        CryptoErrorManager.TAG = "nf_crypto_error";
        CryptoErrorManager.DELTA_MS = 3600000L;
    }
    
    private CryptoErrorManager() {
        this.mIgnoreFatalError = new AtomicBoolean(false);
        this.mRemovingOfflineContentInProgress = new AtomicBoolean(false);
        this.mFatalErrors = new ArrayList<CryptoErrorManager$FatalCryptoError>();
    }
    
    static String createMediaDrmErrorMessage(final StatusCode statusCode, final Throwable t) {
        final StringBuilder sb = new StringBuilder("MediaDrm failure: ");
        sb.append(statusCode.name()).append(". Exception: ");
        if (t == null) {
            sb.append(" init failure: security level changed");
        }
        else {
            sb.append(Log.getStackTraceString(t));
        }
        return sb.toString();
    }
    
    private void dumpErrorState() {
        if (com.netflix.mediaclient.Log.isLoggable()) {
            com.netflix.mediaclient.Log.d(CryptoErrorManager.TAG, "Found %d fatal errors: ", this.mFatalErrors.size());
            final Iterator<CryptoErrorManager$FatalCryptoError> iterator = this.mFatalErrors.iterator();
            int n = 1;
            while (iterator.hasNext()) {
                com.netflix.mediaclient.Log.d(CryptoErrorManager.TAG, "%d, %s: ", n, iterator.next().toString());
                ++n;
            }
        }
    }
    
    private CryptoErrorManager$FatalCryptoError getLastFatalCryptoError() {
        synchronized (this) {
            CryptoErrorManager$FatalCryptoError cryptoErrorManager$FatalCryptoError;
            if (this.mFatalErrors.size() < 1) {
                cryptoErrorManager$FatalCryptoError = null;
            }
            else {
                cryptoErrorManager$FatalCryptoError = this.mFatalErrors.get(this.mFatalErrors.size() - 1);
            }
            return cryptoErrorManager$FatalCryptoError;
        }
    }
    
    private boolean isOfflineContentPresent() {
        return this.mOfflineAgent.getLatestOfflinePlayableList().getTitleCount() > 0;
    }
    
    private void removeOfflineContent() {
        if (this.isOfflineContentPresent()) {
            this.mOfflineAgent.addOfflineAgentListener(new CryptoErrorManager$1(this));
            this.mRemovingOfflineContentInProgress.set(true);
            this.mOfflineAgent.deleteAllOfflineContent();
            DownloadButton.clearPreQueued();
        }
    }
    
    private void resetErrorCounter() {
        this.mFatalErrors.clear();
        PreferenceUtils.removePref(this.mContext, "prefs_crypto_fatal_errors");
    }
    
    private void restoreErrorState() {
        final String stringPref = PreferenceUtils.getStringPref(this.mContext, "prefs_crypto_fatal_errors", null);
        if (StringUtils.isEmpty(stringPref)) {
            return;
        }
        while (true) {
            while (true) {
                int n = 0;
                Label_0126: {
                    try {
                        final JSONArray jsonArray = new JSONArray(stringPref);
                        n = 0;
                        if (n < jsonArray.length()) {
                            final CryptoErrorManager$FatalCryptoError cryptoErrorManager$FatalCryptoError = new CryptoErrorManager$FatalCryptoError(jsonArray.getJSONObject(n));
                            if (cryptoErrorManager$FatalCryptoError.isValid()) {
                                this.mFatalErrors.add(cryptoErrorManager$FatalCryptoError);
                                break Label_0126;
                            }
                            com.netflix.mediaclient.Log.d(CryptoErrorManager.TAG, "Ignore, occured to long ago: %s: ", n, cryptoErrorManager$FatalCryptoError.toString());
                            ++n;
                            break Label_0126;
                        }
                    }
                    catch (Throwable t) {
                        com.netflix.mediaclient.Log.e(CryptoErrorManager.TAG, t, "Fail to restore crypto error state.", new Object[0]);
                    }
                    break;
                }
                ++n;
                continue;
            }
        }
        this.dumpErrorState();
    }
    
    private void save() {
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray();
            final Iterator<CryptoErrorManager$FatalCryptoError> iterator = this.mFatalErrors.iterator();
            while (iterator.hasNext()) {
                jsonArray.put((Object)iterator.next().toJson());
            }
        }
        catch (Throwable t) {
            com.netflix.mediaclient.Log.e(CryptoErrorManager.TAG, t, "Fail to save crypto error state!", new Object[0]);
            return;
        }
        PreferenceUtils.putStringPref(this.mContext, "prefs_crypto_fatal_errors", jsonArray.toString());
    }
    
    ErrorLogging getErrorLogger() {
        return this.mErrorLogger;
    }
    
    int getErrorMessageForFatalError(final ErrorSource errorSource, final StatusCode statusCode) {
        while (true) {
            int n = 2131296641;
            while (true) {
                final CryptoErrorManager$FatalCryptoError lastFatalCryptoError;
                Label_0098: {
                    synchronized (this) {
                        if (this.mIgnoreFatalError.get()) {
                            com.netflix.mediaclient.Log.w(CryptoErrorManager.TAG, "Crypto fallback in progress. We should not see this. Do not add error. Return crypto failback message. Next app start will see different crypto...");
                            n = 2131296638;
                        }
                        else {
                            lastFatalCryptoError = this.getLastFatalCryptoError();
                            if (lastFatalCryptoError != null && lastFatalCryptoError.isValid()) {
                                break Label_0098;
                            }
                            com.netflix.mediaclient.Log.d(CryptoErrorManager.TAG, "Did not had previous valid fatal error, just tell user to start app again");
                            this.mFatalErrors.add(new CryptoErrorManager$FatalCryptoError(errorSource, statusCode, this.mAppStartupTime));
                            this.save();
                        }
                        return n;
                    }
                }
                if (this.mFatalErrors.size() < 1) {
                    com.netflix.mediaclient.Log.d(CryptoErrorManager.TAG, "Did not had previous valid fatal error, just tell user to start app again");
                    continue;
                }
                if (this.mFatalErrors.size() == 1) {
                    if (lastFatalCryptoError.belongToApplicationInstance(this.mAppStartupTime)) {
                        com.netflix.mediaclient.Log.w(CryptoErrorManager.TAG, "Found previous valid fatal error, but it from this same app instance, do not add it again. It should NOT happen. Return message to start app again.");
                        return n;
                    }
                    com.netflix.mediaclient.Log.w(CryptoErrorManager.TAG, "Found previous valid fatal error, app was restarted and we failed again, Tell user to restart device.");
                    n = 2131296642;
                    continue;
                }
                else {
                    if (this.mFatalErrors.size() < 2) {
                        continue;
                    }
                    if (lastFatalCryptoError.belongToApplicationInstance(this.mAppStartupTime)) {
                        com.netflix.mediaclient.Log.w(CryptoErrorManager.TAG, "Found previous valid fatal error, but it from this same app instance, do not add it again. It should NOT happen. Return message to start app again.");
                        n = 2131296642;
                        return n;
                    }
                    com.netflix.mediaclient.Log.w(CryptoErrorManager.TAG, "Found previous valid fatal error, app was restarted and than rebooted and each time we failed again, Fallback...");
                    if (this.handleCryptoFallback()) {
                        com.netflix.mediaclient.Log.d(CryptoErrorManager.TAG, "Failback to legacy crypto...");
                        n = 2131296639;
                        return n;
                    }
                    com.netflix.mediaclient.Log.d(CryptoErrorManager.TAG, "Failback to Widevine L3.");
                    n = 2131296640;
                    return n;
                }
                break;
            }
        }
    }
    
    ServiceAgent$UserAgentInterface getUserAgent() {
        return this.mUserAgent;
    }
    
    public boolean handleCryptoFallback() {
        boolean shouldForceLegacyCrypto = true;
        final CryptoProvider cryptoProvider = CryptoManagerRegistry.getCryptoProvider();
        String s;
        if (cryptoProvider == CryptoProvider.WIDEVINE_L1) {
            s = "MediaDrm failed for Widevine L1, fail back to legacy crypto scheme " + this.mConfiguration.shouldForceLegacyCrypto();
            com.netflix.mediaclient.Log.d(CryptoErrorManager.TAG, s);
            PreferenceUtils.putBooleanPref(this.mContext, "disable_widevine", true);
            this.resetErrorCounter();
            shouldForceLegacyCrypto = this.mConfiguration.shouldForceLegacyCrypto();
            this.removeOfflineContent();
        }
        else if (cryptoProvider == CryptoProvider.WIDEVINE_L3) {
            s = "MediaDrm failed for Widevine L3, fail back to legacy crypto scheme ";
            com.netflix.mediaclient.Log.d(CryptoErrorManager.TAG, "MediaDrm failed for Widevine L3, fail back to legacy crypto scheme ");
            PreferenceUtils.putBooleanPref(this.mContext, "nf_disable_widevine_l3", true);
            this.resetErrorCounter();
            this.removeOfflineContent();
        }
        else {
            s = "Crypto provider was not supported for this error " + cryptoProvider;
            com.netflix.mediaclient.Log.e(CryptoErrorManager.TAG, s);
            shouldForceLegacyCrypto = false;
        }
        this.mErrorLogger.logHandledException(s);
        return shouldForceLegacyCrypto;
    }
    
    public void init(final Context mContext, final long mAppStartupTime, final ServiceAgent$UserAgentInterface mUserAgent, final ServiceAgent$ConfigurationAgentInterface mConfiguration, final OfflineAgentInterface mOfflineAgent, final IErrorHandler mErrorHandler, final ErrorLogging mErrorLogger) {
        // monitorenter(this)
        if (mContext == null) {
            try {
                throw new IllegalArgumentException("CryptoErrorManager can not be initialized with null context!");
            }
            finally {
            }
            // monitorexit(this)
        }
        if (mUserAgent == null) {
            throw new IllegalArgumentException("CryptoErrorManager can not be initialized with null user agent!");
        }
        if (mConfiguration == null) {
            throw new IllegalArgumentException("CryptoErrorManager can not be initialized with null configuration!");
        }
        if (mOfflineAgent == null) {
            throw new IllegalArgumentException("CryptoErrorManager can not be initialized with null offline agent!");
        }
        if (mErrorHandler == null) {
            throw new IllegalArgumentException("CryptoErrorManager can not be initialized with null error handler!");
        }
        if (mErrorLogger == null) {
            throw new IllegalArgumentException("CryptoErrorManager can not be initialized with null error logger!");
        }
        this.mContext = mContext;
        this.mUserAgent = mUserAgent;
        this.mConfiguration = mConfiguration;
        this.mErrorHandler = mErrorHandler;
        this.mErrorLogger = mErrorLogger;
        this.mAppStartupTime = mAppStartupTime;
        this.mOfflineAgent = mOfflineAgent;
        this.restoreErrorState();
    }
    // monitorexit(this)
    
    public boolean isRemovingOfflineContentInProgress() {
        return this.mRemovingOfflineContentInProgress.get();
    }
    
    public void mediaDrmFailure(final ErrorSource errorSource, final StatusCode statusCode, Throwable handle) {
        while (true) {
            Label_0148: {
                synchronized (this) {
                    final CryptoErrorHandler cryptoErrorHandler = CryptoErrorHandlerFactory.getCryptoErrorHandler(errorSource, statusCode);
                    if (cryptoErrorHandler == null) {
                        if (com.netflix.mediaclient.Log.isLoggable()) {
                            com.netflix.mediaclient.Log.e(CryptoErrorManager.TAG, "Unhandled failure type " + statusCode + " for error source " + errorSource);
                        }
                        this.mErrorLogger.logHandledException(createMediaDrmErrorMessage(statusCode, handle));
                    }
                    else {
                        handle = (Throwable)cryptoErrorHandler.handle(this.mContext, handle);
                        if (handle != null) {
                            break Label_0148;
                        }
                        if (com.netflix.mediaclient.Log.isLoggable()) {
                            com.netflix.mediaclient.Log.d(CryptoErrorManager.TAG, "Handled failure type " + statusCode + " for error source " + errorSource + ", but no need to report to user...");
                        }
                    }
                    return;
                }
            }
            if (this.mErrorHandler != null) {
                this.mErrorHandler.addError((ErrorDescriptor)handle);
                return;
            }
            if (com.netflix.mediaclient.Log.isLoggable()) {
                final Throwable t;
                com.netflix.mediaclient.Log.e(CryptoErrorManager.TAG, "Handled failure type " + statusCode + " for error source " + t + ", but unable to report to user because error handler is null! It should NOT happen!");
            }
        }
    }
    
    boolean setActionToExecuteOnExitIfContentRemovalIsInProgress(final Runnable mActionToExecuteOnExitFromContentRemoval) {
        synchronized (this.mRemovingOfflineContentInProgress) {
            if (this.mRemovingOfflineContentInProgress.get()) {
                this.mActionToExecuteOnExitFromContentRemoval = mActionToExecuteOnExitFromContentRemoval;
            }
            // monitorexit(this.mRemovingOfflineContentInProgress)
            return this.mRemovingOfflineContentInProgress.get();
        }
    }
}
