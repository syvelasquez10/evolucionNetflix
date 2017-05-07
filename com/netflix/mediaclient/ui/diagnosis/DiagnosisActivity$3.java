// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.diagnosis;

import com.netflix.mediaclient.util.DeviceUtils;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.content.Intent;
import com.netflix.mediaclient.service.diagnostics.UrlNetworkState;
import java.util.List;
import com.netflix.mediaclient.servicemgr.IDiagnosis;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.util.Log;
import android.view.View$OnClickListener;
import android.widget.ProgressBar;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListAdapter;
import android.content.Context;
import android.widget.ListView;
import com.netflix.mediaclient.servicemgr.IDiagnosis$DiagnosisListener;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class DiagnosisActivity$3 implements ManagerStatusListener
{
    final /* synthetic */ DiagnosisActivity this$0;
    
    DiagnosisActivity$3(final DiagnosisActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        this.this$0.getNetflixActionBar().setDisplayHomeAsUpEnabled(serviceManager.isUserLoggedIn());
        this.this$0.mDiagnosis = this.this$0.getServiceManager().getDiagnosis();
        this.this$0.mDiagnosis.addListener(this.this$0);
        this.this$0.mUrlList = this.this$0.mDiagnosis.getNetworkStateList();
        this.this$0.mStatutListView = (ListView)this.this$0.findViewById(2131165347);
        this.this$0.mAdapter = new DiagnosisActivity$ResultsAdapter(this.this$0, (Context)this.this$0);
        this.this$0.mStatutListView.setAdapter((ListAdapter)this.this$0.mAdapter);
        this.this$0.mNetworkStatus = (TextView)this.this$0.findViewById(2131165340);
        this.this$0.mTestInfo = (TextView)this.this$0.findViewById(2131165341);
        this.this$0.mTestButton = (Button)this.this$0.findViewById(2131165343);
        this.this$0.mRadioLogo = (ImageView)this.this$0.findViewById(2131165346);
        this.this$0.mProgressBar = (ProgressBar)this.this$0.findViewById(2131165342);
        this.this$0.updateInfoGroup(DiagnosisActivity$InfoGroupState.INITIAL);
        this.this$0.findViewById(2131165343).setOnClickListener((View$OnClickListener)new DiagnosisActivity$3$1(this));
        if (Log.isLoggable("DiagnosisActivity", 3)) {
            Log.d("DiagnosisActivity", "onManagaerReady complete");
        }
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
    }
}
