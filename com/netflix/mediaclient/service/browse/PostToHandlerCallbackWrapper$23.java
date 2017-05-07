// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.UserRating;
import com.netflix.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideosProvider;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.LoLoMo;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import java.util.List;
import android.os.Handler;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideoListProvider;
import com.netflix.mediaclient.android.app.Status;

class PostToHandlerCallbackWrapper$23 implements Runnable
{
    final /* synthetic */ PostToHandlerCallbackWrapper this$0;
    final /* synthetic */ Status val$res;
    final /* synthetic */ SearchVideoListProvider val$videoList;
    
    PostToHandlerCallbackWrapper$23(final PostToHandlerCallbackWrapper this$0, final SearchVideoListProvider val$videoList, final Status val$res) {
        this.this$0 = this$0;
        this.val$videoList = val$videoList;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        ThreadUtils.assertOnMain();
        this.this$0.callback.onSimilarVideosFetched(this.val$videoList, this.val$res);
    }
}
