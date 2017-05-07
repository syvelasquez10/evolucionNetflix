// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.diagnostics;

import java.util.List;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.Log;
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

public class DiagnosisAgent extends ServiceAgent implements IDiagnosis
{
    private static final String TAG = "nf_service_diagnosisagent";
    private static final String name = "name";
    private static final String ntwkDiagnostics = "NetworkDiagnostics";
    private static final String resultArrayString = "resultArray";
    private static final String source = "source";
    private NetworkDiagnosis mDiagnosisTool;
    private int mIndex;
    private boolean mIsDiagnosisOngoing;
    private IDiagnosis$DiagnosisListener mListener;
    private Nrdp mNrdp;
    private DiagnosisAgent$NetworkListener mNtwkListener;
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
                logArguments = new LogArguments(LogArguments$LogLevel.DEBUG.getLevelInString(), this.getResultString(), "NetworkDiagnostics", null);
            }
            else {
                logArguments = new LogArguments(LogArguments$LogLevel.ERROR.getLevelInString(), this.getResultString(), "NetworkDiagnostics", null);
            }
            this.mNrdp.getLog().log(logArguments);
        }
        this.mIsDiagnosisOngoing = false;
    }
    
    private void runTestForCurrentIndex() {
        if (this.mIndex >= 0 && this.mIndex < this.mUrlList.length) {
            this.mResultList.get(this.mIndex).setStatus(DiagnosisAgent$UrlStatus.TEST_ONGOING);
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
    public void addListener(final IDiagnosis$DiagnosisListener mListener) {
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
        this.mNtwkListener = new DiagnosisAgent$NetworkListener(this, null);
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
        int i = 0;
        this.abortDiagnosis();
        this.mIndex = 0;
        this.mIsDiagnosisOngoing = true;
        for (String[] mUrlList = this.mUrlList; i < mUrlList.length; ++i) {
            this.mResultList.add(new UrlNetworkState(mUrlList[i], DiagnosisAgent$UrlStatus.NOT_TESTED));
        }
        this.runTestForCurrentIndex();
    }
}
