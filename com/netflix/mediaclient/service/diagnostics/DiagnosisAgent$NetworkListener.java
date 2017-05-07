// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.diagnostics;

import java.util.List;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.javabridge.ui.LogArguments;
import com.netflix.mediaclient.javabridge.ui.LogArguments$LogLevel;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import com.netflix.mediaclient.javabridge.ui.Nrdp;
import com.netflix.mediaclient.servicemgr.IDiagnosis$DiagnosisListener;
import com.netflix.mediaclient.javabridge.ui.NetworkDiagnosis;
import com.netflix.mediaclient.servicemgr.IDiagnosis;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.event.network.NetworkEvent;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.javabridge.ui.EventListener;

class DiagnosisAgent$NetworkListener implements EventListener
{
    final /* synthetic */ DiagnosisAgent this$0;
    
    private DiagnosisAgent$NetworkListener(final DiagnosisAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void received(final UIEvent uiEvent) {
        if (uiEvent instanceof NetworkEvent) {
            final NetworkEvent networkEvent = (NetworkEvent)uiEvent;
            if (this.this$0.mIndex >= 0 && this.this$0.mIndex < this.this$0.mResultList.size()) {
                final UrlNetworkState urlNetworkState = this.this$0.mResultList.get(this.this$0.mIndex);
                if (StringUtils.safeEquals(urlNetworkState.getUrl(), networkEvent.getUrl())) {
                    urlNetworkState.setErrorGroup(networkEvent.getErrorGroup());
                    urlNetworkState.setErrorCode(networkEvent.getErrorCode());
                    urlNetworkState.setResult(networkEvent.getResult());
                    urlNetworkState.setStatus(DiagnosisAgent$UrlStatus.COMPLETED);
                    if (Log.isLoggable()) {
                        Log.d("nf_service_diagnosisagent", "URL: " + urlNetworkState.getUrl());
                        Log.d("nf_service_diagnosisagent", "Error Code: " + urlNetworkState.getErrorCode() + " Err Group: " + urlNetworkState.getErrorGroup());
                    }
                    ++this.this$0.mIndex;
                    this.this$0.runTestForCurrentIndex();
                    return;
                }
                if (Log.isLoggable()) {
                    Log.d("nf_service_diagnosisagent", "URL: Ignoring response " + networkEvent.getUrl());
                }
            }
            else if (Log.isLoggable()) {
                Log.d("nf_service_diagnosisagent", "URL: Ignoring Network Event" + networkEvent.getUrl());
            }
        }
    }
}
