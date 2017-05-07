// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.diagnosis;

import com.netflix.mediaclient.util.DeviceUtils;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.util.Log;
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
import android.view.View;
import android.view.View$OnClickListener;

class DiagnosisActivity$3$1 implements View$OnClickListener
{
    final /* synthetic */ DiagnosisActivity$3 this$1;
    
    DiagnosisActivity$3$1(final DiagnosisActivity$3 this$1) {
        this.this$1 = this$1;
    }
    
    public void onClick(final View view) {
        this.this$1.this$0.startDiagnosis();
    }
}
