// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import com.netflix.model.leafs.Video;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.leafs.Episode;

public class FalkorEpisode extends FalkorVideo
{
    public Episode.Detail episodeDetail;
    
    public FalkorEpisode(final ModelProxy<? extends BranchNode> modelProxy) {
        super(modelProxy);
    }
    
    @Override
    protected Episode.Detail getDetail() {
        return this.episodeDetail;
    }
    
    @Override
    public Object getOrCreate(final String s) {
        final Object value = this.get(s);
        if (value != null) {
            return value;
        }
        if ("detail".equals(s)) {
            return this.episodeDetail = new Episode.Detail();
        }
        return super.getOrCreate(s);
    }
    
    @Override
    public void set(final String s, final Object o) {
        if ("detail".equals(s)) {
            this.episodeDetail = (Episode.Detail)o;
            return;
        }
        super.set(s, o);
    }
}
