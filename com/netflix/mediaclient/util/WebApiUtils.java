// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.security.InvalidParameterException;

public final class WebApiUtils
{
    public static WebApiUtils$VideoIds extractIsd(String substring, final String episodeIdUrl) {
        final WebApiUtils$VideoIds webApiUtils$VideoIds = new WebApiUtils$VideoIds();
        webApiUtils$VideoIds.catalogIdUrl = substring;
        webApiUtils$VideoIds.episodeIdUrl = episodeIdUrl;
        if (episodeIdUrl != null && !"".equals(episodeIdUrl.trim()) && !episodeIdUrl.equals(substring)) {
            webApiUtils$VideoIds.episode = true;
            final int lastIndex = episodeIdUrl.lastIndexOf("/");
            if (lastIndex < 0) {
                throw new InvalidParameterException("Wrong episodeID URL " + episodeIdUrl);
            }
            webApiUtils$VideoIds.episodeId = Integer.parseInt(episodeIdUrl.substring(lastIndex + 1));
            substring = episodeIdUrl.substring(0, lastIndex);
            final int lastIndex2 = substring.lastIndexOf("/");
            if (lastIndex2 >= 0) {
                webApiUtils$VideoIds.catalogId = Integer.parseInt(substring.substring(lastIndex2 + 1));
                return webApiUtils$VideoIds;
            }
        }
        else {
            webApiUtils$VideoIds.episode = false;
            if (!StringUtils.isEmpty(substring)) {
                final int lastIndex3 = substring.lastIndexOf("/");
                if (lastIndex3 < 0) {
                    throw new InvalidParameterException("Wrong catalogID URL " + substring);
                }
                webApiUtils$VideoIds.catalogId = Integer.parseInt(substring.substring(lastIndex3 + 1));
                return webApiUtils$VideoIds;
            }
        }
        return null;
    }
}
