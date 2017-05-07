// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.google.gson.annotations.SerializedName;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import java.util.List;

class PreAppAgentDataHandler$DataToDisk
{
    @SerializedName("billboardList")
    public List<Billboard> billboards;
    @SerializedName("continueWatchingList")
    public List<CWVideo> cwVideos;
    @SerializedName("iqList")
    public List<Video> iqVideos;
    @SerializedName("recoList")
    List<Video> recoVideos;
    final /* synthetic */ PreAppAgentDataHandler this$0;
    @SerializedName("urlMap")
    public Map<String, String> urlMap;
    
    PreAppAgentDataHandler$DataToDisk(final PreAppAgentDataHandler this$0) {
        this.this$0 = this$0;
        this.urlMap = new HashMap<String, String>();
    }
    
    public int getUrlFetchCount(final UpdateEventType updateEventType) {
        int n2;
        final int n = n2 = 0;
        if (UpdateEventType.isBBUpdated(updateEventType)) {
            n2 = n;
            if (this.billboards != null) {
                final Iterator<Billboard> iterator = this.billboards.iterator();
                n2 = 0;
                while (iterator.hasNext()) {
                    if (iterator.next().getHorzDispUrl() != null) {
                        ++n2;
                    }
                }
            }
        }
        int n3 = n2;
        if (UpdateEventType.isCWUpdated(updateEventType)) {
            n3 = n2;
            if (this.cwVideos != null) {
                final Iterator<CWVideo> iterator2 = this.cwVideos.iterator();
                while (iterator2.hasNext()) {
                    if (iterator2.next().getHorzDispUrl() != null) {
                        ++n2;
                    }
                }
                n3 = n2;
            }
        }
        int n4 = n3;
        if (UpdateEventType.isIQUpdated(updateEventType)) {
            n4 = n3;
            if (this.iqVideos != null) {
                final Iterator<Video> iterator3 = this.iqVideos.iterator();
                n4 = n3;
                while (iterator3.hasNext()) {
                    if (iterator3.next().getHorzDispUrl() != null) {
                        ++n4;
                    }
                }
            }
        }
        int n5 = n4;
        if (UpdateEventType.isRecoListUpdated(updateEventType)) {
            n5 = n4;
            if (this.recoVideos != null) {
                final Iterator<Video> iterator4 = this.recoVideos.iterator();
                while (iterator4.hasNext()) {
                    if (iterator4.next().getHorzDispUrl() != null) {
                        ++n4;
                    }
                }
                n5 = n4;
            }
        }
        return n5;
    }
    
    public String toJsonString() {
        return FalcorParseUtils.getGson().toJson(this);
    }
}
