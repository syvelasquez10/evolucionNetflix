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

class FalkorValidationActivity$5$1 implements Runnable
{
    final /* synthetic */ FalkorValidationActivity$5 this$1;
    final /* synthetic */ String val$msg;
    
    FalkorValidationActivity$5$1(final FalkorValidationActivity$5 this$1, final String val$msg) {
        this.this$1 = this$1;
        this.val$msg = val$msg;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.setStatus(this.val$msg);
    }
}
