// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import com.netflix.mediaclient.service.browse.BrowseVideoSentinels;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Summary;
import java.util.ArrayList;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class FetchVideosRequest extends FalkorVolleyWebClientRequest<List<Video>>
{
    private static final String TAG = "nf_service_browse_fetchvideosrequest";
    private final int fromVideo;
    private final String listType;
    private final LoMo mLoMo;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final int toVideo;
    
    public FetchVideosRequest(final Context context, final String listType, final LoMo mLoMo, final int fromVideo, final int toVideo, final boolean b, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.mLoMo = mLoMo;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.listType = listType;
        final String id = this.mLoMo.getId();
        String s;
        if (b) {
            s = "['summary', 'kubrick']";
        }
        else {
            s = "'summary'";
        }
        this.pqlQuery = String.format("['%s', '%s', {'from':%d,'to':%d}, %s]", listType, id, fromVideo, toVideo, s);
        if (Log.isLoggable("nf_service_browse_fetchvideosrequest", 2)) {
            Log.v("nf_service_browse_fetchvideosrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    public static List<Video> buildVideoList(final LoMoType loMoType, final JsonObject jsonObject, final int n, int i, final boolean b) {
        final ArrayList<Video$Summary> list = new ArrayList<Video$Summary>();
        int n2 = 0;
        while (i >= n) {
            final String string = Integer.toString(i);
            int n3;
            if (jsonObject.has(string)) {
                list.add(0, (Video$Summary)FalkorParseUtils.createVideoSummaryObject(jsonObject.getAsJsonObject(string)));
                n3 = 1;
            }
            else if ((n3 = n2) != 0) {
                n3 = n2;
                if (b) {
                    Log.d("nf_service_browse_fetchvideosrequest", String.format("Adding sentinel at index %s", string));
                    list.add(0, BrowseVideoSentinels.getUnavailableVideoSummary());
                    n3 = n2;
                }
            }
            --i;
            n2 = n3;
        }
        return (List<Video>)list;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onVideosFetched(Collections.emptyList(), status);
        }
    }
    
    @Override
    protected void onSuccess(final List<Video> list) {
        if (this.responseCallback != null) {
            this.responseCallback.onVideosFetched(list, CommonStatus.OK);
        }
    }
    
    @Override
    protected List<Video> parseFalkorResponse(String s) {
        if (Log.isLoggable("nf_service_browse_fetchvideosrequest", 2)) {}
        final long nanoTime = System.nanoTime();
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_service_browse_fetchvideosrequest", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            return new ArrayList<Video>();
        }
        while (true) {
            while (true) {
                try {
                    final JsonObject asJsonObject = dataObj.getAsJsonObject(this.listType).getAsJsonObject(this.mLoMo.getId());
                    s = (String)this.mLoMo.getType();
                    final int fromVideo = this.fromVideo;
                    final int toVideo = this.toVideo;
                    if (!this.mLoMo.isBillboard()) {
                        final boolean b = true;
                        s = (String)buildVideoList((LoMoType)s, asJsonObject, fromVideo, toVideo, b);
                        final long nanoTime2 = System.nanoTime();
                        final int n = this.toVideo - this.fromVideo + 1;
                        final long n2 = 0L + TimeUnit.MILLISECONDS.convert(nanoTime2 - nanoTime, TimeUnit.NANOSECONDS);
                        Log.d("nf_service_browse_fetchvideosrequest", String.format(" parsing took %d ms for %d videos - average %d ms", n2, n, n2 / n));
                        return (List<Video>)s;
                    }
                }
                catch (Exception ex) {
                    Log.v("nf_service_browse_fetchvideosrequest", "String response to parse = " + s);
                    throw new FalkorParseException("Does not contain required fields", ex);
                }
                final boolean b = false;
                continue;
            }
        }
    }
}
