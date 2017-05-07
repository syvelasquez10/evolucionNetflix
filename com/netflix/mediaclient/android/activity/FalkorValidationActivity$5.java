// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.util.StringUtils;
import android.view.View$OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.service.NetflixService;
import android.view.View;
import android.content.Intent;
import android.content.Context;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.Executors;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import java.util.concurrent.ExecutorService;
import java.util.Set;
import java.util.Map;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.util.MathUtils;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.Log;
import android.os.Handler;

class FalkorValidationActivity$5 implements Runnable
{
    final /* synthetic */ FalkorValidationActivity this$0;
    final /* synthetic */ Handler val$handler;
    
    FalkorValidationActivity$5(final FalkorValidationActivity this$0, final Handler val$handler) {
        this.this$0 = this$0;
        this.val$handler = val$handler;
    }
    
    private void flushCaches() {
        Log.d("FalkorValidationActivity", "Flushing caches...");
        this.this$0.falkorAgent.flushCaches();
        this.this$0.browseAgent.flushCaches();
    }
    
    private Video getAnyMovie() {
        final Iterator<List<Video>> iterator = this.this$0.videosMap.values().iterator();
        while (iterator.hasNext()) {
            for (final Video video : iterator.next()) {
                if (video.getType() == VideoType.MOVIE) {
                    return video;
                }
            }
        }
        return null;
    }
    
    private void postResult(final FalkorValidationActivity$Result falkorValidationActivity$Result) {
        this.val$handler.post((Runnable)new FalkorValidationActivity$5$2(this, falkorValidationActivity$Result));
    }
    
    private void postStatus(final String s) {
        this.val$handler.post((Runnable)new FalkorValidationActivity$5$1(this, s));
    }
    
    private FalkorValidationActivity$Result runTask(final FalkorValidationActivity$TestRunnerTask<?> falkorValidationActivity$TestRunnerTask) {
        this.postStatus("Running task: " + falkorValidationActivity$TestRunnerTask.getName());
        final FalkorValidationActivity$Result falkorValidationActivity$Result = FalkorValidationActivity.SINGLE_THREAD_EXECUTOR.submit(falkorValidationActivity$TestRunnerTask).get(60L, TimeUnit.SECONDS);
        if (falkorValidationActivity$Result.isError()) {
            throw falkorValidationActivity$Result.append(falkorValidationActivity$TestRunnerTask.getName());
        }
        return falkorValidationActivity$Result;
    }
    
    private FalkorValidationActivity$Result testAddAndRemoveFromQueue(final boolean b) {
        this.flushCaches();
        if (b) {
            this.runTask(new FalkorValidationActivity$TestPrefetchTask(this.this$0, (FalkorValidationActivity$1)null));
        }
        final VideoType show = VideoType.SHOW;
        final VideoType show2 = VideoType.SHOW;
        this.runTask(new FalkorValidationActivity$TestRemoveFromQueueTask(this.this$0, "70143836", show, "70178217", show2, false));
        this.runTask(new FalkorValidationActivity$TestRemoveFromQueueTask(this.this$0, "70143836", show, "70178217", show2, true));
        this.runTask(new FalkorValidationActivity$TestAddToQueueTask(this.this$0, "70143836", show, "70178217", show2));
        this.runTask(new FalkorValidationActivity$TestAddToQueueTask(this.this$0, "70143836", show, "70178217", show2));
        return this.runTask(new FalkorValidationActivity$TestRemoveFromQueueTask(this.this$0, "70143836", show, "70178217", show2, true));
    }
    
    private FalkorValidationActivity$Result testGenreLists() {
        FalkorValidationActivity$Result falkorValidationActivity$Result = this.runTask(new FalkorValidationActivity$TestFetchGenreListTask(this.this$0, (FalkorValidationActivity$1)null));
        for (final GenreList list : this.this$0.listOfGenres) {
            this.runTask(new FalkorValidationActivity$TestPrefetchGenreTask(this.this$0, list));
            falkorValidationActivity$Result = this.runTask(new FalkorValidationActivity$TestFetchGenresTask(this.this$0, list));
            final Iterator<Genre> iterator2 = this.this$0.genres.iterator();
            while (iterator2.hasNext()) {
                falkorValidationActivity$Result = this.runTask(new FalkorValidationActivity$TestFetchGenreVideosTask(this.this$0, iterator2.next()));
            }
        }
        return falkorValidationActivity$Result;
    }
    
    private FalkorValidationActivity$Result testHomeLolomo() {
        this.flushCaches();
        this.runTask(new FalkorValidationActivity$TestPrefetchTask(this.this$0, (FalkorValidationActivity$1)null));
        this.runTask(new FalkorValidationActivity$TestFetchLomosTask(this.this$0, (FalkorValidationActivity$1)null));
        FalkorValidationActivity$Result falkorValidationActivity$Result = this.runTask(new FalkorValidationActivity$TestFetchCwVideosTask(this.this$0, (FalkorValidationActivity$1)null));
        for (final LoMo loMo : this.this$0.lomos) {
            falkorValidationActivity$Result = this.runTask(new FalkorValidationActivity$TestFetchVideosTask(this.this$0, loMo));
            final String id = loMo.getId();
            Log.v("FalkorValidationActivity", "Getting videos list for lomo key: " + id);
            for (final Video video : this.this$0.videosMap.get(id)) {
                if (video.getType() == VideoType.MOVIE) {
                    this.runTask(new FalkorValidationActivity$TestFetchMovieDetailsTask(this.this$0, video.getId()));
                }
                else if (video.getType() == VideoType.SHOW) {
                    if (MathUtils.isEven(video.getId().hashCode())) {
                        this.runTask(new FalkorValidationActivity$TestFetchShowDetailsAndSeasonsTask(this.this$0, video.getId()));
                    }
                    else {
                        this.runTask(new FalkorValidationActivity$TestFetchShowDetailsTask(this.this$0, video.getId()));
                        this.runTask(new FalkorValidationActivity$TestFetchSeasonsTask(this.this$0, video.getId()));
                    }
                    this.runTask(new FalkorValidationActivity$TestFetchEpisodesTask(this.this$0, video.getId()));
                }
                falkorValidationActivity$Result = this.runTask(new FalkorValidationActivity$TestSetVideoRatingTask(this.this$0, video, loMo));
            }
        }
        return falkorValidationActivity$Result;
    }
    
    private FalkorValidationActivity$Result testNflxHandlers() {
        return this.runTask(new FalkorValidationActivity$TestFetchLolomoSummaryTask(this.this$0, "1492"));
    }
    
    private FalkorValidationActivity$Result testSearch() {
        FalkorValidationActivity$Result falkorValidationActivity$Result = null;
        final String[] array = { "", "julie", "horror", "j", "jo", "joh", "john", "bill", "originals", "netflix originals", "family", "bo", "new", "\\", "it's", "\"killing\"", "    orange        is             the      new           black" };
        for (int length = array.length, i = 0; i < length; ++i) {
            final String s = array[i];
            falkorValidationActivity$Result = this.runTask(new FalkorValidationActivity$TestFetchSearchResultsTask(this.this$0, s));
            for (int j = 0; j < this.this$0.searchResults.getNumResultsPeople(); ++j) {
                falkorValidationActivity$Result = this.runTask(new FalkorValidationActivity$TestFetchSimilarVideosForPersonTask(this.this$0, this.this$0.searchResults.getResultsPeople(j), s));
            }
            for (int k = 0; k < this.this$0.searchResults.getNumResultsSuggestions(); ++k) {
                falkorValidationActivity$Result = this.runTask(new FalkorValidationActivity$TestFetchSimilarVideosForSuggestionTask(this.this$0, this.this$0.searchResults.getResultsSuggestions(k), s));
            }
        }
        return falkorValidationActivity$Result;
    }
    
    private FalkorValidationActivity$Result testSpecificDetails() {
        this.runTask(new FalkorValidationActivity$TestFetchKidsCharacterDetailsTask(this.this$0, "7242"));
        this.runTask(new FalkorValidationActivity$TestFetchKidsCharacterDetailsTask(this.this$0, "395284"));
        this.runTask(new FalkorValidationActivity$TestFetchShowDetailsTask(this.this$0, "70140450"));
        this.runTask(new FalkorValidationActivity$TestFetchSeasonsTask(this.this$0, "70221438"));
        this.runTask(new FalkorValidationActivity$TestFetchEpisodesTask(this.this$0, "70221438"));
        this.runTask(new FalkorValidationActivity$TestFetchShowDetailsTask(this.this$0, "70153382"));
        this.runTask(new FalkorValidationActivity$TestFetchEpisodeDetailsTask(this.this$0, "70140564"));
        this.runTask(new FalkorValidationActivity$TestFetchPostPlayVideosTask(this.this$0, "70140564", VideoType.EPISODE));
        this.runTask(new FalkorValidationActivity$TestFetchMovieDetailsTask(this.this$0, "60022480"));
        return this.runTask(new FalkorValidationActivity$TestFetchPostPlayVideosTask(this.this$0, "60022480", VideoType.MOVIE));
    }
    
    @Override
    public void run() {
        ThreadUtils.assertNotOnMain();
        FalkorValidationActivity$Result access$200;
        FalkorValidationActivity$Result falkorValidationActivity$Result2;
        final FalkorValidationActivity$Result falkorValidationActivity$Result = falkorValidationActivity$Result2 = (access$200 = FalkorValidationActivity$Result.UNKNOWN);
        try {
            this.flushCaches();
            access$200 = falkorValidationActivity$Result;
            falkorValidationActivity$Result2 = falkorValidationActivity$Result;
            access$200 = (falkorValidationActivity$Result2 = this.testNflxHandlers());
            access$200 = (falkorValidationActivity$Result2 = this.testSpecificDetails());
            access$200 = (falkorValidationActivity$Result2 = this.testHomeLolomo());
            access$200 = (falkorValidationActivity$Result2 = this.testAddAndRemoveFromQueue(0 != 0));
            access$200 = (falkorValidationActivity$Result2 = this.testAddAndRemoveFromQueue(1 != 0));
            access$200 = (falkorValidationActivity$Result2 = this.testSearch());
            access$200 = (falkorValidationActivity$Result2 = this.testSpecificDetails());
            access$200 = (falkorValidationActivity$Result2 = this.testGenreLists());
            FalkorValidationActivity$Result.OK;
        }
        catch (Exception ex) {
            falkorValidationActivity$Result2 = access$200;
            Log.handleException("FalkorValidationActivity", ex);
            falkorValidationActivity$Result2 = access$200;
            if (ex instanceof FalkorValidationActivity$Result) {
                falkorValidationActivity$Result2 = access$200;
                falkorValidationActivity$Result2 = (FalkorValidationActivity$Result)ex;
            }
            else {
                falkorValidationActivity$Result2 = access$200;
                falkorValidationActivity$Result2 = FalkorValidationActivity$Result.EXCEPTION.append(ex.getMessage());
            }
        }
        finally {
            this.postResult(falkorValidationActivity$Result2);
        }
    }
}
