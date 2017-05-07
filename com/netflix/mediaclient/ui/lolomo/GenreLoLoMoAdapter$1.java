// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class GenreLoLoMoAdapter$1 extends LoggingManagerCallback
{
    final /* synthetic */ GenreLoLoMoAdapter this$0;
    
    GenreLoLoMoAdapter$1(final GenreLoLoMoAdapter this$0, final String s) {
        this.this$0 = this$0;
        super(s);
    }
    
    @Override
    public void onGenreLoLoMoPrefetched(final Status status) {
        super.onGenreLoLoMoPrefetched(status);
        this.this$0.handlePrefetchComplete();
    }
}
