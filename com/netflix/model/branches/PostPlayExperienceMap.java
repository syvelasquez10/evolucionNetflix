// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import com.netflix.falkor.Func;
import java.util.HashSet;
import java.util.Set;
import com.netflix.mediaclient.Log;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.leafs.PostPlayExperience;
import com.netflix.falkor.Ref;
import com.netflix.falkor.BranchMap;
import com.netflix.falkor.BranchNode;

public class PostPlayExperienceMap implements BranchNode
{
    private static final String TAG = "PostPlayExperienceMap";
    private BranchMap<BranchMap<Ref>> playbackVideos;
    private PostPlayExperience postPlayExperience;
    private ModelProxy<? extends BranchNode> proxy;
    
    public PostPlayExperienceMap(final ModelProxy<? extends BranchNode> proxy) {
        this.proxy = proxy;
    }
    
    @Override
    public Object get(final String s) {
        switch (s) {
            default: {
                Log.d("PostPlayExperienceMap", "Could not find key: " + s);
                return null;
            }
            case "experienceData": {
                return this.postPlayExperience;
            }
            case "playbackVideos": {
                return this.playbackVideos;
            }
        }
    }
    
    @Override
    public Set<String> getKeys() {
        final HashSet<String> set = new HashSet<String>();
        if (this.postPlayExperience != null) {
            set.add("experienceData");
        }
        if (this.playbackVideos != null) {
            set.add("playbackVideos");
        }
        return set;
    }
    
    @Override
    public Object getOrCreate(final String s) {
        final Object value = this.get(s);
        if (value != null) {
            return value;
        }
        switch (s) {
            default: {
                throw new IllegalArgumentException("Unknown key: " + s);
            }
            case "experienceData": {
                return this.postPlayExperience = new PostPlayExperience(this.proxy);
            }
            case "playbackVideos": {
                return this.playbackVideos = new BranchMap<BranchMap<Ref>>(new PostPlayExperienceMap$1(this));
            }
        }
    }
    
    @Override
    public void remove(final String s) {
        this.set(s, null);
    }
    
    @Override
    public void set(final String s, final Object o) {
        switch (s) {
            default: {
                Log.d("PostPlayExperienceMap", "Don't know how to set key: " + s);
            }
            case "experienceData": {
                this.postPlayExperience = (PostPlayExperience)o;
            }
            case "playbackVideos": {
                BranchMap<BranchMap<Ref>> playbackVideos;
                if (o instanceof BranchMap) {
                    playbackVideos = (BranchMap<BranchMap<Ref>>)o;
                }
                else {
                    playbackVideos = null;
                }
                this.playbackVideos = playbackVideos;
            }
        }
    }
}
