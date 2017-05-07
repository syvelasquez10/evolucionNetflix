// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.diagnosis;

import com.netflix.mediaclient.util.DeviceUtils;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.service.diagnostics.UrlNetworkState;
import java.util.List;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.IDiagnosis;
import com.netflix.mediaclient.servicemgr.IDiagnosis$DiagnosisListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.util.Log;

class DiagnosisActivity$1 implements Runnable
{
    final /* synthetic */ DiagnosisActivity this$0;
    
    DiagnosisActivity$1(final DiagnosisActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("DiagnosisActivity", "DiagnosisListUpdated");
        this.this$0.mAdapter.notifyDataSetChanged();
    }
}
