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

public class DiagnosisActivity extends NetflixActivity implements IDiagnosis$DiagnosisListener
{
    private static final String TAG = "DiagnosisActivity";
    private DiagnosisActivity$ResultsAdapter mAdapter;
    private IDiagnosis mDiagnosis;
    private TextView mNetworkStatus;
    private ProgressBar mProgressBar;
    private ImageView mRadioLogo;
    private ListView mStatutListView;
    private Button mTestButton;
    private TextView mTestInfo;
    List<UrlNetworkState> mUrlList;
    
    public static Intent createStartIntent(final Context context) {
        return new Intent(context, (Class)DiagnosisActivity.class);
    }
    
    private void startDiagnosis() {
        Log.d("DiagnosisActivity", "Diagnosis being attempted");
        this.mDiagnosis.startNetworkDiagnosis();
        this.updateInfoGroup(DiagnosisActivity$InfoGroupState.TEST_ONGOING);
        this.mAdapter.notifyDataSetChanged();
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new DiagnosisActivity$3(this);
    }
    
    protected String getStringForFailure() {
        int i = 0;
        int n = 0;
        int n2 = 0;
        while (i < this.mUrlList.size()) {
            final UrlNetworkState urlNetworkState = this.mUrlList.get(i);
            int n3 = n;
            int n4 = n2;
            if (urlNetworkState.getResult() != 0) {
                if (urlNetworkState.containsNetflix()) {
                    n4 = 1;
                    n3 = n;
                }
                else {
                    n3 = 1;
                    n4 = n2;
                }
            }
            ++i;
            n = n3;
            n2 = n4;
        }
        if (n2 != 0 && n != 0) {
            return this.getString(2131493329);
        }
        if (n != 0) {
            return this.getString(2131493331);
        }
        if (n2 != 0) {
            return this.getString(2131493330);
        }
        return this.getString(2131493326);
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.customerService;
    }
    
    @Override
    protected boolean hasUpAction() {
        return false;
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (DeviceUtils.isTabletByContext((Context)this)) {
            this.setRequestedOrientation(6);
            this.setContentView(2130903088);
            if (Log.isLoggable("DiagnosisActivity", 3)) {
                Log.d("DiagnosisActivity", "Setting orientation to landscape");
            }
        }
        else {
            this.setRequestedOrientation(7);
            this.setContentView(2130903089);
            if (Log.isLoggable("DiagnosisActivity", 3)) {
                Log.d("DiagnosisActivity", "setting orientation to potrait");
            }
        }
        Log.d("DiagnosisActivity", "onCreate");
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mDiagnosis != null) {
            this.mDiagnosis.abortDiagnosis();
            this.mDiagnosis.removeListener();
            this.mDiagnosis = null;
            this.mUrlList = null;
        }
        Log.d("DiagnosisActivity", "onDestroy");
    }
    
    @Override
    public void onDiagnosisComplete() {
        this.runOnUiThread((Runnable)new DiagnosisActivity$2(this));
    }
    
    @Override
    public void onDiagnosisListUpdated() {
        this.runOnUiThread((Runnable)new DiagnosisActivity$1(this));
    }
    
    @Override
    protected boolean showAboutInMenu() {
        return false;
    }
    
    public boolean showMdxInMenu() {
        return false;
    }
    
    @Override
    protected boolean showSettingsInMenu() {
        return false;
    }
    
    @Override
    protected boolean showSignOutInMenu() {
        return false;
    }
    
    protected void updateInfoGroup(final DiagnosisActivity$InfoGroupState diagnosisActivity$InfoGroupState) {
        switch (DiagnosisActivity$4.$SwitchMap$com$netflix$mediaclient$ui$diagnosis$DiagnosisActivity$InfoGroupState[diagnosisActivity$InfoGroupState.ordinal()]) {
            default: {}
            case 1: {
                this.mNetworkStatus.setText(2131493320);
                this.mTestInfo.setText(2131493321);
                this.mTestButton.setText(2131493322);
                this.mTestButton.setVisibility(0);
                this.mProgressBar.setVisibility(4);
            }
            case 2: {
                this.mNetworkStatus.setText(2131493324);
                this.mTestInfo.setVisibility(0);
                this.mTestInfo.setText((CharSequence)this.getStringForFailure());
                this.mTestButton.setVisibility(0);
                this.mTestButton.setText(2131493325);
                this.mProgressBar.setVisibility(4);
            }
            case 3: {
                this.mNetworkStatus.setText(2131493326);
                this.mTestButton.setVisibility(0);
                this.mTestButton.setText(2131493325);
                this.mProgressBar.setVisibility(4);
            }
            case 4: {
                this.mRadioLogo.setVisibility(8);
                this.mStatutListView.setVisibility(0);
                this.mTestButton.setVisibility(4);
                this.mProgressBar.setVisibility(0);
                this.mNetworkStatus.setText(2131493323);
                this.mTestInfo.setVisibility(4);
            }
        }
    }
}
