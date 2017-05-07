// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.model.leafs.Video$Summary;
import com.netflix.mediaclient.servicemgr.interface_.UserRating;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideoListProvider;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.interface_.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayVideosProvider;
import com.netflix.mediaclient.servicemgr.interface_.search.IrisNotificationsList;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.servicemgr.interface_.LoLoMo;
import com.netflix.mediaclient.servicemgr.interface_.details.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.interface_.genre.Genre;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;

class PostToHandlerCallbackWrapper$17 implements Runnable
{
    final /* synthetic */ PostToHandlerCallbackWrapper this$0;
    final /* synthetic */ List val$requestedGenreLists;
    final /* synthetic */ Status val$res;
    
    PostToHandlerCallbackWrapper$17(final PostToHandlerCallbackWrapper this$0, final List val$requestedGenreLists, final Status val$res) {
        this.this$0 = this$0;
        this.val$requestedGenreLists = val$requestedGenreLists;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        ThreadUtils.assertOnMain();
        this.this$0.callback.onGenreListsFetched(this.val$requestedGenreLists, this.val$res);
    }
}
