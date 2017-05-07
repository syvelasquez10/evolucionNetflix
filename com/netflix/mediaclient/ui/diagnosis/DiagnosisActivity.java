// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.diagnosis;

import com.netflix.mediaclient.service.diagnostics.DiagnosisAgent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.Iterator;
import com.netflix.mediaclient.util.DeviceUtils;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.ListAdapter;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
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
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class DiagnosisActivity extends NetflixActivity implements DiagnosisListener
{
    private static final String TAG = "DiagnosisActivity";
    private ResultsAdapter mAdapter;
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
        this.updateInfoGroup(InfoGroupState.TEST_ONGOING);
        this.mAdapter.notifyDataSetChanged();
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ManagerStatusListener() {
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final Status status) {
                DiagnosisActivity.this.getNetflixActionBar().setDisplayHomeAsUpEnabled(serviceManager.isUserLoggedIn());
                DiagnosisActivity.this.mDiagnosis = DiagnosisActivity.this.getServiceManager().getDiagnosis();
                DiagnosisActivity.this.mDiagnosis.addListener((IDiagnosis.DiagnosisListener)DiagnosisActivity.this);
                DiagnosisActivity.this.mUrlList = DiagnosisActivity.this.mDiagnosis.getNetworkStateList();
                DiagnosisActivity.this.mStatutListView = (ListView)DiagnosisActivity.this.findViewById(2131165345);
                DiagnosisActivity.this.mAdapter = new ResultsAdapter((Context)DiagnosisActivity.this);
                DiagnosisActivity.this.mStatutListView.setAdapter((ListAdapter)DiagnosisActivity.this.mAdapter);
                DiagnosisActivity.this.mNetworkStatus = (TextView)DiagnosisActivity.this.findViewById(2131165338);
                DiagnosisActivity.this.mTestInfo = (TextView)DiagnosisActivity.this.findViewById(2131165339);
                DiagnosisActivity.this.mTestButton = (Button)DiagnosisActivity.this.findViewById(2131165341);
                DiagnosisActivity.this.mRadioLogo = (ImageView)DiagnosisActivity.this.findViewById(2131165344);
                DiagnosisActivity.this.mProgressBar = (ProgressBar)DiagnosisActivity.this.findViewById(2131165340);
                DiagnosisActivity.this.updateInfoGroup(InfoGroupState.INITIAL);
                DiagnosisActivity.this.findViewById(2131165341).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        DiagnosisActivity.this.startDiagnosis();
                    }
                });
                if (Log.isLoggable("DiagnosisActivity", 3)) {
                    Log.d("DiagnosisActivity", "onManagaerReady complete");
                }
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
            }
        };
    }
    
    protected String getStringForFailure() {
        int n = 0;
        int n2 = 0;
        int n3;
        int n4;
        for (int i = 0; i < this.mUrlList.size(); ++i, n2 = n3, n = n4) {
            final UrlNetworkState urlNetworkState = this.mUrlList.get(i);
            n3 = n2;
            n4 = n;
            if (urlNetworkState.getResult() != 0) {
                if (urlNetworkState.containsNetflix()) {
                    n4 = 1;
                    n3 = n2;
                }
                else {
                    n3 = 1;
                    n4 = n;
                }
            }
        }
        if (n != 0 && n2 != 0) {
            return this.getString(2131493367);
        }
        if (n2 != 0) {
            return this.getString(2131493369);
        }
        if (n != 0) {
            return this.getString(2131493368);
        }
        return this.getString(2131493364);
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.customerService;
    }
    
    @Override
    protected boolean hasUpAction() {
        return false;
    }
    
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (DeviceUtils.isTabletByContext((Context)this)) {
            this.setRequestedOrientation(6);
            this.setContentView(2130903085);
            if (Log.isLoggable("DiagnosisActivity", 3)) {
                Log.d("DiagnosisActivity", "Setting orientation to landscape");
            }
        }
        else {
            this.setRequestedOrientation(1);
            this.setContentView(2130903086);
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
        this.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                Log.d("DiagnosisActivity", "DiagnosisUpdated ");
                final boolean b = true;
                final Iterator<UrlNetworkState> iterator = DiagnosisActivity.this.mUrlList.iterator();
                while (true) {
                    do {
                        final boolean b2 = b;
                        if (iterator.hasNext()) {
                            continue;
                        }
                        if (b2) {
                            DiagnosisActivity.this.updateInfoGroup(InfoGroupState.SUCCESS);
                        }
                        else {
                            DiagnosisActivity.this.updateInfoGroup(InfoGroupState.FAILED);
                        }
                        DiagnosisActivity.this.mAdapter.notifyDataSetChanged();
                        return;
                    } while (iterator.next().getResult() == 0);
                    final boolean b2 = false;
                    continue;
                }
            }
        });
    }
    
    @Override
    public void onDiagnosisListUpdated() {
        this.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                Log.d("DiagnosisActivity", "DiagnosisListUpdated");
                DiagnosisActivity.this.mAdapter.notifyDataSetChanged();
            }
        });
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
    
    protected void updateInfoGroup(final InfoGroupState infoGroupState) {
        switch (infoGroupState) {
            default: {}
            case INITIAL: {
                this.mNetworkStatus.setText(2131493358);
                this.mTestInfo.setText(2131493359);
                this.mTestButton.setText(2131493360);
                this.mTestButton.setVisibility(0);
                this.mProgressBar.setVisibility(4);
            }
            case FAILED: {
                this.mNetworkStatus.setText(2131493362);
                this.mTestInfo.setVisibility(0);
                this.mTestInfo.setText((CharSequence)this.getStringForFailure());
                this.mTestButton.setVisibility(0);
                this.mTestButton.setText(2131493363);
                this.mProgressBar.setVisibility(4);
            }
            case SUCCESS: {
                this.mNetworkStatus.setText(2131493364);
                this.mTestButton.setVisibility(0);
                this.mTestButton.setText(2131493363);
                this.mProgressBar.setVisibility(4);
            }
            case TEST_ONGOING: {
                this.mRadioLogo.setVisibility(8);
                this.mStatutListView.setVisibility(0);
                this.mTestButton.setVisibility(4);
                this.mProgressBar.setVisibility(0);
                this.mNetworkStatus.setText(2131493361);
                this.mTestInfo.setVisibility(4);
            }
        }
    }
    
    public enum InfoGroupState
    {
        FAILED, 
        INITIAL, 
        SUCCESS, 
        TEST_ONGOING;
    }
    
    public class ResultsAdapter extends ArrayAdapter<String>
    {
        Context context;
        
        public ResultsAdapter(final Context context) {
            super(context, 2130903087, 2131165345);
            this.context = context;
        }
        
        public boolean areAllItemsEnabled() {
            return false;
        }
        
        public int getCount() {
            if (DiagnosisActivity.this.mUrlList == null) {
                Log.d("DiagnosisActivity", "urlList is null");
                return 0;
            }
            if (Log.isLoggable("DiagnosisActivity", 3)) {
                Log.d("DiagnosisActivity", "urlList size: " + DiagnosisActivity.this.mUrlList.size());
            }
            return DiagnosisActivity.this.mUrlList.size();
        }
        
        public View getView(final int n, View inflate, final ViewGroup viewGroup) {
            inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903087, viewGroup, false);
            final TextView textView = (TextView)inflate.findViewById(2131165347);
            final TextView textView2 = (TextView)inflate.findViewById(2131165348);
            final ImageView imageView = (ImageView)inflate.findViewById(2131165346);
            imageView.setImageResource(2130837735);
            final UrlNetworkState urlNetworkState = DiagnosisActivity.this.mUrlList.get(n);
            this.setTitleText(textView, urlNetworkState.getUrl(), n);
            if (urlNetworkState.getStatus().equals(DiagnosisAgent.UrlStatus.COMPLETED)) {
                if (urlNetworkState.getResult() != 0) {
                    textView2.setText((CharSequence)("nw-" + urlNetworkState.getErrorGroup() + "-" + urlNetworkState.getErrorCode()));
                    imageView.setImageResource(2130837734);
                    return inflate;
                }
                imageView.setImageResource(2130837735);
                textView2.setVisibility(4);
            }
            else {
                if (urlNetworkState.getStatus().equals(DiagnosisAgent.UrlStatus.TEST_ONGOING)) {
                    imageView.setVisibility(4);
                    textView2.setVisibility(4);
                    return inflate;
                }
                if (urlNetworkState.getStatus().equals(DiagnosisAgent.UrlStatus.NOT_TESTED)) {
                    imageView.setVisibility(4);
                    textView2.setVisibility(4);
                    textView.setVisibility(4);
                    return inflate;
                }
            }
            return inflate;
        }
        
        public void setTitleText(final TextView textView, final String s, final int n) {
            if (s != null && s.contains("netflix")) {
                textView.setText((CharSequence)this.context.getString(2131493365, new Object[] { n + 1 }));
                return;
            }
            textView.setText(2131493366);
        }
    }
}
