// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.security.InvalidParameterException;

public final class WebApiUtils
{
    public static VideoIds extractIsd(String substring, final String episodeIdUrl) throws InvalidParameterException {
        final VideoIds videoIds = new VideoIds();
        videoIds.catalogIdUrl = substring;
        videoIds.episodeIdUrl = episodeIdUrl;
        if (episodeIdUrl != null && !"".equals(episodeIdUrl.trim()) && !episodeIdUrl.equals(substring)) {
            videoIds.episode = true;
            final int lastIndex = episodeIdUrl.lastIndexOf("/");
            if (lastIndex < 0) {
                throw new InvalidParameterException("Wrong episodeID URL " + episodeIdUrl);
            }
            videoIds.episodeId = Integer.parseInt(episodeIdUrl.substring(lastIndex + 1));
            substring = episodeIdUrl.substring(0, lastIndex);
            final int lastIndex2 = substring.lastIndexOf("/");
            if (lastIndex2 < 0) {
                return null;
            }
            videoIds.catalogId = Integer.parseInt(substring.substring(lastIndex2 + 1));
            return videoIds;
        }
        else {
            videoIds.episode = false;
            if (StringUtils.isEmpty(substring)) {
                return null;
            }
            final int lastIndex3 = substring.lastIndexOf("/");
            if (lastIndex3 < 0) {
                throw new InvalidParameterException("Wrong catalogID URL " + substring);
            }
            videoIds.catalogId = Integer.parseInt(substring.substring(lastIndex3 + 1));
            return videoIds;
        }
    }
    
    public static class VideoIds
    {
        public int catalogId;
        public String catalogIdUrl;
        public boolean episode;
        public int episodeId;
        public String episodeIdUrl;
        
        public VideoIds() {
        }
        
        public VideoIds(final boolean episode, final String episodeIdUrl, final String catalogIdUrl, final int episodeId, final int catalogId) {
            this.episode = episode;
            this.episodeIdUrl = episodeIdUrl;
            this.catalogIdUrl = catalogIdUrl;
            this.episodeId = episodeId;
            this.catalogId = catalogId;
        }
    }
}
