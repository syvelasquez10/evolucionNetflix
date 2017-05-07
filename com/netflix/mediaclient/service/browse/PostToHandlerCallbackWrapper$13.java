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
import com.netflix.mediaclient.servicemgr.interface_.search.SocialNotificationsList;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.servicemgr.interface_.LoLoMo;
import com.netflix.mediaclient.servicemgr.interface_.details.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.interface_.genre.Genre;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import java.util.List;
import android.os.Handler;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;

class PostToHandlerCallbackWrapper$13 implements Runnable
{
    final /* synthetic */ PostToHandlerCallbackWrapper this$0;
    final /* synthetic */ EpisodeDetails val$episodeDetails;
    final /* synthetic */ Status val$res;
    
    PostToHandlerCallbackWrapper$13(final PostToHandlerCallbackWrapper this$0, final EpisodeDetails val$episodeDetails, final Status val$res) {
        this.this$0 = this$0;
        this.val$episodeDetails = val$episodeDetails;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$0.callback.onEpisodeDetailsFetched(this.val$episodeDetails, this.val$res);
    }
}
