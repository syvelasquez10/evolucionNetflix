// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import java.util.Set;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.Log;
import java.util.List;
import com.netflix.mediaclient.service.browse.cache.SoftCache;
import com.netflix.mediaclient.service.browse.cache.HardCache;

public class LegacyDataDumper
{
    private static final String TAG = "LegacyDataDumper";
    private final HardCache hardCache;
    private final SoftCache softCache;
    
    public LegacyDataDumper(final BrowseWebClient browseWebClient, final HardCache hardCache, final SoftCache softCache) {
        this.hardCache = hardCache;
        this.softCache = softCache;
    }
    
    private void dumpVideoList(final HardCache hardCache, final SoftCache softCache, final String s, int n) {
        final List list = (List)this.getFromCache(hardCache, softCache, s);
        if (list != null) {
            Log.d("LegacyDataDumper", String.format("Videos in key %s", s));
            for (final Video video : list) {
                Log.d("LegacyDataDumper", String.format("(%d) %s, %s, %s", n, video.getId(), video.getType(), video.getTitle()));
                ++n;
            }
        }
    }
    
    private Object getFromCache(final HardCache hardCache, final SoftCache softCache, final String s) {
        Object o;
        if ((o = this.hardCache.get(s)) == null) {
            o = softCache.get(s);
        }
        return o;
    }
    
    private int getVideoStartIndexFromKey(final String s, final String s2) {
        final boolean b = false;
        final String[] split = s.substring(s2.length() + 1).split("_");
        int int1 = b ? 1 : 0;
        if (split.length > 0) {
            int1 = (b ? 1 : 0);
            if (StringUtils.isNotEmpty(split[0])) {
                int1 = Integer.parseInt(split[0]);
            }
        }
        return int1;
    }
    
    public void dumpHomeLoLoMosAndVideosToLog() {
        final Set<?> keySet = this.hardCache.getKeySet();
        final Set<?> keySet2 = this.softCache.getKeySet();
        while (true) {
            for (final String s : keySet) {
                if (s.contains(BrowseAgent.CACHE_KEY_PREFIX_LOMO)) {
                    final List<LoMo> list = (List<LoMo>)this.getFromCache(this.hardCache, this.softCache, s);
                    if (list != null) {
                        for (final LoMo loMo : list) {
                            Log.d("LegacyDataDumper", String.format("%s, %d, %s", loMo.getId(), loMo.getNumVideos(), loMo.getTitle()));
                            final String string = BrowseAgent.CACHE_KEY_PREFIX_VIDEOS + "_" + loMo.getId();
                            for (final String s2 : keySet) {
                                if (s2.contains(string)) {
                                    this.dumpVideoList(this.hardCache, this.softCache, s2, this.getVideoStartIndexFromKey(s2, string));
                                }
                            }
                            for (final String s3 : keySet2) {
                                if (s3.contains(string)) {
                                    this.dumpVideoList(this.hardCache, this.softCache, s3, this.getVideoStartIndexFromKey(s3, string));
                                }
                            }
                        }
                    }
                    return;
                }
            }
            final List<LoMo> list = null;
            continue;
        }
    }
    
    public void dumpHomeLoMos() {
        while (true) {
            for (final String s : this.hardCache.getKeySet()) {
                if (s.contains(BrowseAgent.CACHE_KEY_PREFIX_LOMO)) {
                    final List<LoMo> list = (List<LoMo>)this.getFromCache(this.hardCache, this.softCache, s);
                    if (list != null) {
                        for (final LoMo loMo : list) {
                            Log.d("LegacyDataDumper", String.format("%s, %d, %s", loMo.getId(), loMo.getNumVideos(), loMo.getTitle()));
                        }
                    }
                    return;
                }
            }
            final List<LoMo> list = null;
            continue;
        }
    }
}
