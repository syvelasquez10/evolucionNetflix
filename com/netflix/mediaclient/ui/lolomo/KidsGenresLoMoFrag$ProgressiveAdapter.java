// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;

class KidsGenresLoMoFrag$ProgressiveAdapter extends SimilarItemsGridViewAdapter
{
    private final int BATCH;
    private int endIndex;
    private int startIndex;
    final /* synthetic */ KidsGenresLoMoFrag this$0;
    
    public KidsGenresLoMoFrag$ProgressiveAdapter(final KidsGenresLoMoFrag this$0, final boolean b, final int n, final RecyclerViewHeaderAdapter$IViewCreator recyclerViewHeaderAdapter$IViewCreator) {
        this.this$0 = this$0;
        super(b, n, recyclerViewHeaderAdapter$IViewCreator);
        this.BATCH = 40;
        this.startIndex = 0;
        this.endIndex = 40;
    }
    
    public void fetchData() {
        if (this.this$0.manager != null && this.this$0.manager.isReady()) {
            this.this$0.manager.getBrowse().fetchGenreVideos(new KidsGenresLoMoFrag$FlatGenre(this.this$0, null), this.startIndex, this.endIndex, false, new KidsGenresLoMoFrag$GenresVideoFetchCallback(this.this$0, "KidsGenresLoMoFrag"));
        }
    }
    
    @Override
    protected void onPostItemViewBind(final int n) {
        super.onPostItemViewBind(n);
    }
}
