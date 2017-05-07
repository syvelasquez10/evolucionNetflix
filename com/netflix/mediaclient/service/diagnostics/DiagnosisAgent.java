// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.diagnostics;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.event.network.NetworkEvent;
import com.netflix.mediaclient.event.UIEvent;
import java.util.List;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.javabridge.ui.LogArguments;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import com.netflix.mediaclient.javabridge.ui.Nrdp;
import com.netflix.mediaclient.javabridge.ui.NetworkDiagnosis;
import com.netflix.mediaclient.servicemgr.IDiagnosis;
import com.netflix.mediaclient.service.ServiceAgent;

public class DiagnosisAgent extends ServiceAgent implements IDiagnosis
{
    private static final String TAG = "nf_service_diagnosisagent";
    private static final String desc = "desc";
    private static final String name = "name";
    private static final String ntwkDiagnostics = "NetworkDiagnostics";
    private static final String resultArrayString = "resultArray";
    private static final String source = "source";
    private NetworkDiagnosis mDiagnosisTool;
    private int mIndex;
    private boolean mIsDiagnosisOngoing;
    private DiagnosisListener mListener;
    private Nrdp mNrdp;
    private NetworkListener mNtwkListener;
    private ArrayList<UrlNetworkState> mResultList;
    private String[] mUrlList;
    
    public DiagnosisAgent() {
        this.mUrlList = new String[] { "http://android.nccp.netflix.com", "http://ichnaea.netflix.com", "http://www.google.com" };
        this.mIsDiagnosisOngoing = false;
        this.mListener = null;
    }
    
    private String getResultString() {
        JSONObject jsonObject;
        JSONArray jsonArray;
        try {
            jsonObject = new JSONObject();
            jsonArray = new JSONArray();
            final Iterator<UrlNetworkState> iterator = this.mResultList.iterator();
            while (iterator.hasNext()) {
                jsonArray.put((Object)iterator.next().toJson());
            }
        }
        catch (Exception ex) {
            return ex.toString();
        }
        jsonObject.put("name", (Object)"NetworkDiagnostics");
        jsonObject.put("source", (Object)"DiagnosticPage");
        jsonObject.put("resultArray", (Object)jsonArray);
        return jsonObject.toString();
    }
    
    private boolean isTestSuccess() {
        final Iterator<UrlNetworkState> iterator = this.mResultList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getResult() != 0) {
                return false;
            }
        }
        return true;
    }
    
    private void notifyDiagnosisComplete() {
        if (this.mListener != null && this.mIsDiagnosisOngoing) {
            this.mListener.onDiagnosisComplete();
            LogArguments logArguments;
            if (this.isTestSuccess()) {
                logArguments = new LogArguments(LogArguments.LogLevel.DEBUG.getLevelInString(), this.getResultString(), "NetworkDiagnostics", null);
            }
            else {
                logArguments = new LogArguments(LogArguments.LogLevel.ERROR.getLevelInString(), this.getResultString(), "NetworkDiagnostics", null);
            }
            this.mNrdp.getLog().log(logArguments);
        }
        this.mIsDiagnosisOngoing = false;
    }
    
    private void runTestForCurrentIndex() {
        if (this.mIndex >= 0 && this.mIndex < this.mUrlList.length) {
            this.mResultList.get(this.mIndex).setStatus(UrlStatus.TEST_ONGOING);
            this.mDiagnosisTool.get(this.mUrlList[this.mIndex]);
            if (this.mListener != null) {
                this.mListener.onDiagnosisListUpdated();
            }
            return;
        }
        this.notifyDiagnosisComplete();
    }
    
    @Override
    public void abortDiagnosis() {
        if (Log.isLoggable("nf_service_diagnosisagent", 3)) {
            Log.d("nf_service_diagnosisagent", "abortDiagnosis");
        }
        this.mIsDiagnosisOngoing = false;
        this.mResultList.clear();
        this.mIndex = -1;
    }
    
    @Override
    public void addListener(final DiagnosisListener mListener) {
        this.mListener = mListener;
    }
    
    @Override
    protected void doInit() {
        this.mNrdp = this.getNrdController().getNrdp();
        if (this.mNrdp == null || !this.mNrdp.isReady()) {
            this.initCompleted(CommonStatus.NRD_ERROR);
            Log.e("nf_service_diagnosisagent", "NRDP is NOT READY");
            return;
        }
        this.mDiagnosisTool = this.mNrdp.getDiagnosisTool();
        this.mNtwkListener = new NetworkListener();
        this.mDiagnosisTool.addEventListener("INetwork", this.mNtwkListener);
        this.mResultList = new ArrayList<UrlNetworkState>();
        this.initCompleted(CommonStatus.OK);
    }
    
    @Override
    public List<UrlNetworkState> getNetworkStateList() {
        return this.mResultList;
    }
    
    @Override
    public boolean isDiagnosisOngoing() {
        return this.mIsDiagnosisOngoing;
    }
    
    @Override
    public void removeListener() {
        this.mListener = null;
    }
    
    @Override
    public void startNetworkDiagnosis() {
        this.abortDiagnosis();
        this.mIndex = 0;
        this.mIsDiagnosisOngoing = true;
        final String[] mUrlList = this.mUrlList;
        for (int length = mUrlList.length, i = 0; i < length; ++i) {
            this.mResultList.add(new UrlNetworkState(mUrlList[i], UrlStatus.NOT_TESTED));
        }
        this.runTestForCurrentIndex();
    }
    
    public interface NetflixError
    {
        boolean isFatal();
    }
    
    private class NetworkListener implements EventListener
    {
        @Override
        public void received(final UIEvent uiEvent) {
            if (uiEvent instanceof NetworkEvent) {
                final NetworkEvent networkEvent = (NetworkEvent)uiEvent;
                if (DiagnosisAgent.this.mIndex >= 0 && DiagnosisAgent.this.mIndex < DiagnosisAgent.this.mResultList.size()) {
                    final UrlNetworkState urlNetworkState = DiagnosisAgent.this.mResultList.get(DiagnosisAgent.this.mIndex);
                    if (StringUtils.safeEquals(urlNetworkState.getUrl(), networkEvent.getUrl())) {
                        urlNetworkState.setErrorGroup(networkEvent.getErrorGroup());
                        urlNetworkState.setErrorCode(networkEvent.getErrorCode());
                        urlNetworkState.setResult(networkEvent.getResult());
                        urlNetworkState.setStatus(UrlStatus.COMPLETED);
                        if (Log.isLoggable("nf_service_diagnosisagent", 3)) {
                            Log.d("nf_service_diagnosisagent", "URL: " + urlNetworkState.getUrl());
                            Log.d("nf_service_diagnosisagent", "Error Code: " + urlNetworkState.getErrorCode() + " Err Group: " + urlNetworkState.getErrorGroup());
                        }
                        ++DiagnosisAgent.this.mIndex;
                        DiagnosisAgent.this.runTestForCurrentIndex();
                        return;
                    }
                    if (Log.isLoggable("nf_service_diagnosisagent", 3)) {
                        Log.d("nf_service_diagnosisagent", "URL: Ignoring response " + networkEvent.getUrl());
                    }
                }
                else if (Log.isLoggable("nf_service_diagnosisagent", 3)) {
                    Log.d("nf_service_diagnosisagent", "URL: Ignoring Network Event" + networkEvent.getUrl());
                }
            }
        }
    }
    
    public enum UrlStatus
    {
        COMPLETED, 
        NOT_TESTED, 
        TEST_ONGOING;
    }
}
