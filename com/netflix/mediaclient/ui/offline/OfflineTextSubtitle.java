// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import org.json.JSONObject;
import com.netflix.mediaclient.media.SubtitleUrl;
import com.netflix.mediaclient.media.Subtitle;

public class OfflineTextSubtitle extends OfflineSubtitle
{
    public static final int IMPL_VALUE = 3;
    
    OfflineTextSubtitle(final Subtitle subtitle, final SubtitleUrl subtitleUrl, final String s) {
        super(subtitle, subtitleUrl, s);
    }
    
    public OfflineTextSubtitle(final JSONObject jsonObject) {
        super(jsonObject);
    }
    
    @Override
    protected int getImplementation() {
        return 3;
    }
}
