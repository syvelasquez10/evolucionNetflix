// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.SearchVideoList;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideo;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.LoLoMo;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import java.util.List;
import android.os.Handler;

public class PostToHandlerCallbackWrapper implements BrowseAgentCallback
{
    private final BrowseAgentCallback callback;
    private final Handler handler;
    
    public PostToHandlerCallbackWrapper(final Handler handler, final BrowseAgentCallback callback) {
        this.handler = handler;
        this.callback = callback;
    }
    
    @Override
    public void onBBVideosFetched(final List<Billboard> list, final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onBBVideosFetched(list, status);
            }
        });
    }
    
    @Override
    public void onCWListRefresh(final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onCWListRefresh(status);
            }
        });
    }
    
    @Override
    public void onCWVideosFetched(final List<CWVideo> list, final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onCWVideosFetched(list, status);
            }
        });
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onEpisodeDetailsFetched(episodeDetails, status);
            }
        });
    }
    
    @Override
    public void onEpisodesFetched(final List<EpisodeDetails> list, final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onEpisodesFetched(list, status);
            }
        });
    }
    
    @Override
    public void onGenreListsFetched(final List<GenreList> list, final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onGenreListsFetched(list, status);
            }
        });
    }
    
    @Override
    public void onGenreLoLoMoPrefetched(final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onGenreLoLoMoPrefetched(status);
            }
        });
    }
    
    @Override
    public void onGenresFetched(final List<Genre> list, final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onGenresFetched(list, status);
            }
        });
    }
    
    @Override
    public void onIQListRefresh(final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onIQListRefresh(status);
            }
        });
    }
    
    @Override
    public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onKidsCharacterDetailsFetched(kidsCharacterDetails, b, status);
            }
        });
    }
    
    @Override
    public void onLoLoMoPrefetched(final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onLoLoMoPrefetched(status);
            }
        });
    }
    
    @Override
    public void onLoLoMoSummaryFetched(final LoLoMo loLoMo, final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onLoLoMoSummaryFetched(loLoMo, status);
            }
        });
    }
    
    @Override
    public void onLoMosFetched(final List<LoMo> list, final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onLoMosFetched(list, status);
            }
        });
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onMovieDetailsFetched(movieDetails, status);
            }
        });
    }
    
    @Override
    public void onPostPlayVideosFetched(final List<PostPlayVideo> list, final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onPostPlayVideosFetched(list, status);
            }
        });
    }
    
    @Override
    public void onQueueAdd(final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onQueueAdd(status);
            }
        });
    }
    
    @Override
    public void onQueueRemove(final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onQueueRemove(status);
            }
        });
    }
    
    @Override
    public void onSearchResultsFetched(final ISearchResults searchResults, final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onSearchResultsFetched(searchResults, status);
            }
        });
    }
    
    @Override
    public void onSeasonDetailsFetched(final SeasonDetails seasonDetails, final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onSeasonDetailsFetched(seasonDetails, status);
            }
        });
    }
    
    @Override
    public void onSeasonsFetched(final List<SeasonDetails> list, final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onSeasonsFetched(list, status);
            }
        });
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onShowDetailsFetched(showDetails, status);
            }
        });
    }
    
    @Override
    public void onSimilarVideosFetched(final SearchVideoList list, final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onSimilarVideosFetched(list, status);
            }
        });
    }
    
    @Override
    public void onVideoHide(final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onVideoHide(status);
            }
        });
    }
    
    @Override
    public void onVideoRatingSet(final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onVideoRatingSet(status);
            }
        });
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final Status status) {
        this.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                PostToHandlerCallbackWrapper.this.callback.onVideosFetched(list, status);
            }
        });
    }
}
