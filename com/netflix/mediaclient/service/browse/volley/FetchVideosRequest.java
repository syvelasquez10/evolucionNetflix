// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import java.util.Collections;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.service.webclient.model.BillboardDetails;
import com.netflix.mediaclient.service.browse.BrowseVideoSentinels;
import com.netflix.mediaclient.util.StringUtils;
import java.util.ArrayList;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.servicemgr.LoMoType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.LoMo;
import com.netflix.mediaclient.servicemgr.Video;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchVideosRequest extends FalcorVolleyWebClientRequest<List<Video>>
{
    private static final String TAG = "nf_service_browse_fetchvideosrequest";
    private final int fromVideo;
    private final String listType;
    private final LoMo mLoMo;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final int toVideo;
    
    public FetchVideosRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final String listType, final LoMo mLoMo, final int fromVideo, final int toVideo, final BrowseAgentCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.responseCallback = responseCallback;
        this.mLoMo = mLoMo;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.listType = listType;
        this.pqlQuery = "['" + listType + "','" + this.mLoMo.getId() + "',{'to':" + toVideo + ",'from':" + fromVideo + "},['summary','billboardDetail']]";
        if (Log.isLoggable("nf_service_browse_fetchvideosrequest", 2)) {
            Log.v("nf_service_browse_fetchvideosrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    public static List<Video> buildVideoList(final LoMoType loMoType, final JsonObject jsonObject, final int n, int n2, final boolean b) {
        final ArrayList<com.netflix.mediaclient.service.webclient.model.branches.Video.Summary> list = new ArrayList<com.netflix.mediaclient.service.webclient.model.branches.Video.Summary>();
        int n3 = 0;
        final int n4 = 0;
        int n5 = 0;
        int i = n2;
        n2 = n4;
        while (i >= n) {
            final String string = Integer.toString(i);
            int n6;
            int n7;
            int n8;
            if (jsonObject.has(string)) {
                final boolean b2 = true;
                final Video summaryByLomoType = getSummaryByLomoType(loMoType, jsonObject.getAsJsonObject(string));
                list.add(0, (com.netflix.mediaclient.service.webclient.model.branches.Video.Summary)summaryByLomoType);
                n6 = (b2 ? 1 : 0);
                n7 = n3;
                n8 = n2;
                if (Log.isLoggable("nf_service_browse_fetchvideosrequest", 2)) {
                    final int n9 = n3 + 1;
                    n6 = (b2 ? 1 : 0);
                    n7 = n9;
                    n8 = n2;
                    if (summaryByLomoType != null) {
                        n6 = (b2 ? 1 : 0);
                        n7 = n9;
                        n8 = n2;
                        if (StringUtils.isNotEmpty(summaryByLomoType.getBoxshotURL())) {
                            n6 = (b2 ? 1 : 0);
                            n7 = n9;
                            n8 = n2;
                            if (summaryByLomoType.getBoxshotURL().contains(".webp")) {
                                n8 = n2 + 1;
                                n7 = n9;
                                n6 = (b2 ? 1 : 0);
                            }
                        }
                    }
                }
            }
            else {
                n6 = n5;
                n7 = n3;
                n8 = n2;
                if (n5 != 0) {
                    n6 = n5;
                    n7 = n3;
                    n8 = n2;
                    if (b) {
                        Log.d("nf_service_browse_fetchvideosrequest", String.format("Adding sentinel at index %s", string));
                        list.add(0, BrowseVideoSentinels.getUnavailableVideoSummary());
                        n6 = n5;
                        n7 = n3;
                        n8 = n2;
                    }
                }
            }
            --i;
            n5 = n6;
            n3 = n7;
            n2 = n8;
        }
        if (Log.isLoggable("nf_service_browse_fetchvideosrequest", 2) && n3 > 0 && n2 > 0) {
            Log.d("nf_service_browse_fetchvideosrequest", String.format("videoCount:%d webpCount %d (%d%%)", n3, n2, n2 * 100 / n3));
        }
        return (List<Video>)list;
    }
    
    private static Video getSummaryByLomoType(final LoMoType loMoType, final JsonObject jsonObject) {
        if (loMoType == LoMoType.BILLBOARD) {
            if (Log.isLoggable("nf_service_browse_fetchvideosrequest", 3)) {
                Log.d("nf_service_browse_fetchvideosrequest", "Found billboard row - getting summary");
            }
            return FalcorParseUtils.getGson().fromJson(jsonObject, BillboardDetails.class);
        }
        return FalcorParseUtils.getPropertyObject(jsonObject, "summary", com.netflix.mediaclient.service.webclient.model.branches.Video.Summary.class);
    }
    
    @Override
    protected String[] getPQLQueries() {
        return new String[] { this.pqlQuery };
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            this.responseCallback.onVideosFetched(Collections.emptyList(), n);
        }
    }
    
    @Override
    protected void onSuccess(final List<Video> list) {
        if (this.responseCallback != null) {
            this.responseCallback.onVideosFetched(list, 0);
        }
    }
    
    @Override
    protected List<Video> parseFalcorResponse(String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_fetchvideosrequest", 2)) {}
        final long nanoTime = System.nanoTime();
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_fetchvideosrequest", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
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
                    throw new FalcorParseException("Does not contain required fields", ex);
                }
                final boolean b = false;
                continue;
            }
        }
    }
}
