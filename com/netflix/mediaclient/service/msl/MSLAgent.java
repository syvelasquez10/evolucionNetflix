// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl;

import com.netflix.mediaclient.service.configuration.crypto.CryptoManager$DrmReadyCallback;
import com.netflix.mediaclient.servicemgr.INetflixService;
import com.netflix.mediaclient.service.configuration.crypto.CryptoManagerRegistry;
import com.android.volley.Request;
import com.netflix.mediaclient.service.msl.volley.MSLVolleyRequest;
import com.netflix.mediaclient.servicemgr.IMSLClient$NetworkRequestInspector;
import com.netflix.msl.MslConstants$ResponseCode;
import com.netflix.msl.msg.ErrorHeader;
import com.netflix.mediaclient.service.msl.client.MslErrorException;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.android.volley.Network;
import com.android.volley.Cache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.BasicNetwork;
import com.netflix.mediaclient.service.msl.volley.MSLSimplelUrlStack;
import com.android.volley.toolbox.NoCache;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.android.org.json.JSONObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.android.volley.RequestQueue;
import com.netflix.mediaclient.service.msl.client.AndroidMslClient;
import com.netflix.mediaclient.servicemgr.IMSLClient;
import com.netflix.mediaclient.service.ServiceAgent;

public class MSLAgent extends ServiceAgent implements IMSLClient
{
    private static boolean DEBUG = false;
    private static final String TAG = "nf_msl";
    private AndroidMslClient mClient;
    private boolean mEnabled;
    private MSLAgent$NetworkRequestInspectorManager mNetworkRequestInspectorManager;
    private RequestQueue mRequestQueue;
    
    static {
        MSLAgent.DEBUG = false;
    }
    
    public MSLAgent() {
        if (MSLAgent.DEBUG) {
            this.mNetworkRequestInspectorManager = new MSLAgent$NetworkRequestInspectorManager(null);
        }
    }
    
    private void doExecuteAppBoot() {
        final JSONObject appbootRequest = this.mClient.appbootRequest(this.mClient.defaultAppbootRequest(null, null));
        if (Log.isLoggable()) {
            Log.d("nf_msl", "MSLAgent::initOk: Response on APP_BOOT_REQUEST: " + appbootRequest);
        }
    }
    
    private void executeAppBoot() {
        if (this.mClient.shouldExecuteAppBotSynchronously()) {
            Log.d("nf_msl", "Execute AppBoot synchronously, first app launch...");
            this.doExecuteAppBoot();
            return;
        }
        Log.d("nf_msl", "Execute AppBoot asynchronously, regular app launch...");
        new BackgroundTask().execute(new MSLAgent$2(this));
    }
    
    private void initializeMsl() {
        while (true) {
            try {
                this.mClient = new AndroidMslClient(this.getContext(), this.getConfigurationAgent(), this.getUserAgent());
                final int dataRequestThreadPoolSize = this.getConfigurationAgent().getDataRequestThreadPoolSize();
                if (Log.isLoggable()) {
                    Log.d("nf_msl", String.format("Creating MSL Volley RequestQueue with threadPoolsize of %d", dataRequestThreadPoolSize));
                }
                (this.mRequestQueue = new RequestQueue(new NoCache(), new BasicNetwork(new MSLSimplelUrlStack(this.mClient, this.getConfigurationAgent().getDataRequestTimeout())), dataRequestThreadPoolSize)).start();
                this.executeAppBoot();
                this.initCompleted(CommonStatus.OK);
                Log.d("nf_msl", "MSLAgent::doInit done.");
            }
            catch (MslErrorException ex) {
                this.mEnabled = false;
                Log.w("nf_msl", ex, "MSLAgent::doInit appboot failed!");
                if (this.isBlacklisted(ex.getErrorHeader())) {
                    ErrorLoggingManager.logHandledException("MSL_ERROR_2: Failed to create MSL, disabled.");
                    continue;
                }
                ErrorLoggingManager.logHandledException("MSL_ERROR_3: Failed to create MSL, disabled. Cause: " + ex.getMessage());
                continue;
            }
            catch (Throwable t) {
                Log.e("nf_msl", t, "MSLAgent::doInit failed!", new Object[0]);
                ErrorLoggingManager.logHandledException("MSL_ERROR_4: Failed to create MSL, disabled. Cause: " + t.getMessage());
                this.mEnabled = false;
                continue;
            }
            break;
        }
    }
    
    private boolean isBlacklisted(final ErrorHeader errorHeader) {
        final boolean b = false;
        if (Log.isLoggable()) {
            Log.d("nf_msl", "Error message: " + errorHeader.getErrorMessage());
            Log.d("nf_msl", "Error code: " + errorHeader.getErrorCode());
            Log.d("nf_msl", "Error recipient: " + errorHeader.getRecipient());
            Log.d("nf_msl", "Error user message: " + errorHeader.getUserMessage());
            Log.d("nf_msl", "Error internal code: " + errorHeader.getInternalCode());
        }
        boolean b2 = b;
        if (errorHeader.getErrorCode() == MslConstants$ResponseCode.TRANSIENT_FAILURE) {
            b2 = b;
            if (errorHeader.getInternalCode() == 109000) {
                Log.d("nf_msl", "Our device is one of black listed, we need to default to legacy crypto and offline is NOT supported!");
                this.mEnabled = false;
                b2 = true;
            }
        }
        return b2;
    }
    
    @Override
    public void addNetworkRequestInspector(final IMSLClient$NetworkRequestInspector imslClient$NetworkRequestInspector, final Class[] array) {
        throw new IllegalAccessError("Trying to add NetworkRequestInspector in release build!");
    }
    
    @Override
    public boolean addRequest(final MSLVolleyRequest mslVolleyRequest) {
        while (true) {
            boolean b = false;
            Label_0045: {
                synchronized (this) {
                    if (!this.mEnabled) {
                        Log.e("nf_msl", "MSL is disabled, and somebody is trying to connect to BR!");
                    }
                    else {
                        if (mslVolleyRequest != null) {
                            break Label_0045;
                        }
                        Log.w("nf_msl", "Request is null!");
                    }
                    return b;
                }
            }
            final MSLVolleyRequest mslVolleyRequest2;
            if (Log.isLoggable()) {
                Log.d("nf_msl", "Adding MSL request " + ((Throwable)mslVolleyRequest2).getClass().getSimpleName() + " to queue...");
            }
            mslVolleyRequest2.setMSLClient(this.mClient);
            mslVolleyRequest2.setConfig(this.getConfigurationAgent());
            mslVolleyRequest2.setUserAgent(this.getUserAgent());
            mslVolleyRequest2.setMSLAgent(this);
            mslVolleyRequest2.setErrorLogger(this.getLoggingAgent().getErrorLogging());
            mslVolleyRequest2.setTimeout(this.getConfigurationAgent().getDataRequestTimeout());
            if (mslVolleyRequest2.getMSLUserCredentialRegistry() == null) {
                mslVolleyRequest2.setMSLUserCredentialRegistry(this.getUserAgent().getMSLUserCredentialRegistry());
            }
            else if (Log.isLoggable()) {
                Log.d("nf_msl", "Request " + mslVolleyRequest2.getClass().getSimpleName() + " is using its own MSLUserCredentialRegistry " + mslVolleyRequest2.getMSLUserCredentialRegistry());
            }
            this.mRequestQueue.add(mslVolleyRequest2);
            b = true;
            return b;
        }
    }
    
    @Override
    public void destroy() {
        super.destroy();
        if (this.mRequestQueue != null) {
            Log.i("nf_msl", "Stopping MSL Volley RequestQueue");
            this.mRequestQueue.stop();
            this.mRequestQueue = null;
        }
    }
    
    @Override
    protected void doInit() {
        Log.d("nf_msl", "MSLAgent::doInit start ");
        Log.d("nf_msl", "Initiate crypto, if Widevine is supported!");
        try {
            CryptoManagerRegistry.createCryptoInstance(this.getContext(), this.getService(), new MSLAgent$1(this));
        }
        catch (Throwable t) {
            Log.e("nf_msl", "Failed to create MSL, disable it!", t);
        }
    }
    
    @Override
    public boolean enabled() {
        return this.mEnabled;
    }
    
    @Override
    public boolean isUserKnown(final String s) {
        return this.mClient.getUserIdToken(s) != null;
    }
    
    @Override
    public void logout() {
        this.mClient.removeUserData();
    }
}
