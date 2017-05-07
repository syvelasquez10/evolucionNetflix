// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.diagnostics.UrlNetworkState;
import java.util.List;

public interface IDiagnosis
{
    public static final int SUCCESS = 0;
    
    void abortDiagnosis();
    
    void addListener(final DiagnosisListener p0);
    
    List<UrlNetworkState> getNetworkStateList();
    
    boolean isDiagnosisOngoing();
    
    void removeListener();
    
    void startNetworkDiagnosis();
    
    public interface DiagnosisListener
    {
        void onDiagnosisComplete();
        
        void onDiagnosisListUpdated();
    }
}
