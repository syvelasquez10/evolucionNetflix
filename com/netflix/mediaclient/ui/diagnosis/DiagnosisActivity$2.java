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
import java.util.List;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.IDiagnosis;
import com.netflix.mediaclient.servicemgr.IDiagnosis$DiagnosisListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.Iterator;
import com.netflix.mediaclient.service.diagnostics.UrlNetworkState;
import android.util.Log;

class DiagnosisActivity$2 implements Runnable
{
    final /* synthetic */ DiagnosisActivity this$0;
    
    DiagnosisActivity$2(final DiagnosisActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("DiagnosisActivity", "DiagnosisUpdated ");
        final Iterator<UrlNetworkState> iterator = this.this$0.mUrlList.iterator();
        while (true) {
            while (iterator.hasNext()) {
                if (iterator.next().getResult() != 0) {
                    final int n = 0;
                    if (n != 0) {
                        this.this$0.updateInfoGroup(DiagnosisActivity$InfoGroupState.SUCCESS);
                    }
                    else {
                        this.this$0.updateInfoGroup(DiagnosisActivity$InfoGroupState.FAILED);
                    }
                    this.this$0.mAdapter.notifyDataSetChanged();
                    return;
                }
            }
            final int n = 1;
            continue;
        }
    }
}
