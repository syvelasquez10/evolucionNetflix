// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import android.view.View$OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.os.Handler;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.service.NetflixService;
import android.view.View;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.Executors;
import com.netflix.mediaclient.servicemgr.model.Video;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import java.util.List;
import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import java.util.concurrent.ExecutorService;
import java.util.Set;
import java.util.Map;

public class FalkorValidationActivity extends NetflixActivity
{
    private static final boolean DO_PREFETCH = false;
    private static final boolean INCLUDE_GENRES_TEST = false;
    private static final Map<Class<?>, Class<?>[]> INTERFACE_MAP;
    private static final Set<String> METHOD_IGNORE_SET;
    private static final ExecutorService SINGLE_THREAD_EXECUTOR;
    private static final String TAG = "FalkorValidationActivity";
    private static final long TASK_TIMEOUT_SECONDS = 60L;
    private static final float TEXT_SIZE = 24.0f;
    private static final int TO_EPISODE = 199;
    private static final int TO_LOLOMO = 19;
    private static final int TO_SEASON = 19;
    private static final int TO_SIMILAR_VIDEO = 49;
    private static final int TO_VIDEO = 9;
    private BrowseAccess browseAgent;
    private FalkorAccess falkorAgent;
    public List<Genre> genres;
    public List<GenreList> listOfGenres;
    public List<LoMo> lomos;
    private ServiceManager manager;
    public ISearchResults searchResults;
    private TextView textView;
    public Map<String, List<? extends Video>> videosMap;
    
    static {
        SINGLE_THREAD_EXECUTOR = Executors.newSingleThreadExecutor();
        INTERFACE_MAP = new FalkorValidationActivity$1();
        METHOD_IGNORE_SET = new FalkorValidationActivity$2();
    }
    
    public FalkorValidationActivity() {
        this.videosMap = Collections.synchronizedMap(new HashMap<String, List<? extends Video>>());
    }
    
    public static String createIgnoreKey(final Class<?> clazz, final String s) {
        return clazz.getSimpleName() + "_" + s;
    }
    
    public static Intent createStartIntent(final Context context) {
        return new Intent(context, (Class)FalkorValidationActivity.class);
    }
    
    private void handleResult(final FalkorValidationActivity$Result falkorValidationActivity$Result) {
        String string;
        if (falkorValidationActivity$Result.isSucces()) {
            string = "Validation successful";
        }
        else {
            string = "Validation FAILED: " + falkorValidationActivity$Result;
        }
        this.setStatus(string);
    }
    
    private boolean isSetupSuccessful() {
        Log.d("FalkorValidationActivity", "Falkor agent disabled");
        return false;
    }
    
    private void setStatus(final String text) {
        Log.d("FalkorValidationActivity", "Setting status message: " + text);
        this.textView.setText((CharSequence)text);
    }
    
    private void startValidation() {
        if (this.manager == null) {
            Log.w("FalkorValidationActivity", "Can't start validation - manager is null");
        }
        this.setContentView((View)this.textView);
        final NetflixService netflixService = (NetflixService)this.manager.getService();
        this.falkorAgent = netflixService.getFalkorAgent();
        this.browseAgent = netflixService.getBrowseAgent();
        if (!this.isSetupSuccessful()) {
            Log.i("FalkorValidationActivity", "Setup failed - can't continue validation");
            return;
        }
        Log.d("FalkorValidationActivity", "Flushing caches...");
        this.falkorAgent.flushCaches();
        this.browseAgent.flushCaches();
        ThreadUtils.assertOnMain();
        new BackgroundTask().execute(new FalkorValidationActivity$5(this, new Handler()));
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new FalkorValidationActivity$4(this);
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return null;
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final Button contentView = new Button((Context)this);
        contentView.setText((CharSequence)"Tap to start validation\nNote that this will clear existing caches");
        contentView.setTextSize(24.0f);
        contentView.setOnClickListener((View$OnClickListener)new FalkorValidationActivity$3(this));
        (this.textView = new TextView((Context)this)).setGravity(17);
        this.textView.setPadding(50, 50, 50, 50);
        this.textView.setTextSize(24.0f);
        this.setContentView((View)contentView);
    }
}
