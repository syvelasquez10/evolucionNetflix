// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import org.json.JSONArray;
import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleProfile;
import com.netflix.mediaclient.ui.player.NccpSubtitle;
import java.util.HashMap;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.List;
import java.util.Map;

public class SubtitleTrackData
{
    private String id;
    private Map<String, Integer> mCdnToRankMap;
    private int mPosition;
    private Subtitle subtitleInfo;
    private List<SubtitleUrl> urls;
    
    public SubtitleTrackData(final JSONObject jsonObject, int i, final long n) {
        this.urls = new ArrayList<SubtitleUrl>();
        this.mCdnToRankMap = new HashMap<String, Integer>();
        this.mPosition = 0;
        this.subtitleInfo = NccpSubtitle.newInstance(jsonObject, i);
        this.id = jsonObject.getString("id");
        final JSONObject jsonObject2 = jsonObject.getJSONObject("downloadableIds");
        final JSONObject jsonObject3 = jsonObject.getJSONObject("ttDownloadables");
        final ISubtitleDef$SubtitleProfile[] values = ISubtitleDef$SubtitleProfile.values();
        int length;
        ISubtitleDef$SubtitleProfile subtitleDef$SubtitleProfile;
        String optString;
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        long optLong;
        int optInt;
        int optInt2;
        Iterator keys;
        String s;
        String optString2;
        SubtitleUrl subtitleUrl;
        for (length = values.length, i = 0; i < length; ++i) {
            subtitleDef$SubtitleProfile = values[i];
            optString = jsonObject2.optString(subtitleDef$SubtitleProfile.getNccpCode());
            if (!StringUtils.isEmpty(optString)) {
                optJSONObject = jsonObject3.optJSONObject(subtitleDef$SubtitleProfile.getNccpCode());
                if (optJSONObject != null) {
                    optJSONObject2 = optJSONObject.optJSONObject("downloadUrls");
                    if (optJSONObject2 != null) {
                        optLong = optJSONObject.optLong("size", -1L);
                        optInt = optJSONObject.optInt("midxOffset", 0);
                        optInt2 = optJSONObject.optInt("midxSize", 0);
                        keys = optJSONObject2.keys();
                        while (keys.hasNext()) {
                            s = keys.next();
                            optString2 = optJSONObject2.optString(s);
                            if (!StringUtils.isEmpty(s) && !StringUtils.isEmpty(optString2)) {
                                try {
                                    subtitleUrl = new SubtitleUrl(optString2, subtitleDef$SubtitleProfile, n, Long.parseLong(s), optString, optLong);
                                    subtitleUrl.setMasterIndex(optInt2, optInt);
                                    this.urls.add(subtitleUrl);
                                }
                                catch (NumberFormatException ex) {}
                            }
                        }
                    }
                }
            }
        }
        final JSONArray optJSONArray = jsonObject.optJSONArray("cdnlist");
        if (optJSONArray != null) {
            JSONObject jsonObject4;
            int optInt3;
            String optString3;
            for (i = 0; i < optJSONArray.length(); ++i) {
                jsonObject4 = (JSONObject)optJSONArray.opt(i);
                optInt3 = jsonObject4.optInt("rank", 0);
                optString3 = jsonObject4.optString("id", (String)null);
                if (StringUtils.isNotEmpty(optString3)) {
                    this.mCdnToRankMap.put(optString3, optInt3);
                }
            }
        }
    }
    
    public String getDownloadId() {
        if (this.urls.isEmpty()) {
            return null;
        }
        return this.urls.get(0).getDownloadableId();
    }
    
    public String getId() {
        return this.id;
    }
    
    public int getRankForCdn(final String s) {
        final Integer n = this.mCdnToRankMap.get(s);
        if (n != null) {
            return n;
        }
        return 0;
    }
    
    public Subtitle getSubtitleInfo() {
        return this.subtitleInfo;
    }
    
    public List<SubtitleUrl> getUrls() {
        return this.urls;
    }
    
    public SubtitleUrl pop() {
        if (this.mPosition < this.urls.size()) {
            return this.urls.get(this.mPosition++);
        }
        return null;
    }
    
    public void reset() {
        this.mPosition = 0;
    }
    
    @Override
    public String toString() {
        return "SubtitleTrackData{id='" + this.id + '\'' + ", urls=" + this.urls + ", subtitleInfo=" + this.subtitleInfo + ", mPosition=" + this.mPosition + '}';
    }
}
