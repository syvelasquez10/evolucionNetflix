// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pdslogging;

import org.json.JSONObject;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;

public class PdsAgent extends ServiceAgent implements PdsDownloadInterface, PdsPlayInterface
{
    private PdsDownloadSessionManager mDownloadManager;
    private final OfflineAgentInterface mOfflineAgentInterface;
    
    public PdsAgent(final OfflineAgentInterface mOfflineAgentInterface) {
        this.mOfflineAgentInterface = mOfflineAgentInterface;
    }
    
    private void registerWithOfflineAgent() {
        this.getMainHandler().post((Runnable)new PdsAgent$1(this));
    }
    
    private void unregisterWithOfflineAgent() {
        this.getMainHandler().post((Runnable)new PdsAgent$2(this));
    }
    
    @Override
    public PdsPlaySessionInterface createPdsPlaySession(final String s, final String s2, final long n, final PlayContext playContext) {
        return new PdsPlaySession(s, s2, n, this.getLoggingAgent().getApplicationId(), this.getLoggingAgent().getUserSessionId(), this.getLoggingAgent().getPdsLogging(), playContext);
    }
    
    @Override
    public void destroy() {
        this.unregisterWithOfflineAgent();
        this.mDownloadManager.destroy(this.getContext());
        super.destroy();
    }
    
    @Override
    protected void doInit() {
        this.mDownloadManager = new PdsDownloadSessionManager(this.getContext(), this.mOfflineAgentInterface, this.getLoggingAgent());
        this.registerWithOfflineAgent();
        this.initCompleted(CommonStatus.OK);
    }
    
    @Override
    public void onDownloadOfFirstTimeOfflineManifest(final String s, final String s2, final String s3, final DownloadContext downloadContext, final JSONObject jsonObject) {
        this.mDownloadManager.setOfflineManifest(s, s2, s3, downloadContext, jsonObject);
    }
}
