// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import java.util.HashSet;
import java.util.Set;
import com.netflix.mediaclient.Log;
import com.netflix.falkor.Ref;
import com.netflix.model.leafs.Video$PostPlayContext;
import com.netflix.falkor.BranchNode;

public class PostPlayMap implements BranchNode
{
    private static final String TAG = "PostPlayMap";
    private Video$PostPlayContext postplayContext;
    private Ref videoRef;
    
    @Override
    public Object get(final String s) {
        switch (s) {
            default: {
                Log.d("PostPlayMap", "Could not find key: " + s);
                return null;
            }
            case "postplayContext": {
                return this.postplayContext;
            }
            case "videoRef": {
                return this.videoRef;
            }
        }
    }
    
    @Override
    public Set<String> getKeys() {
        final HashSet<String> set = new HashSet<String>();
        if (this.postplayContext != null) {
            set.add("postplayContext");
        }
        if (this.videoRef != null) {
            set.add("videoRef");
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
            case "postplayContext": {
                return this.postplayContext = new Video$PostPlayContext();
            }
            case "videoRef": {
                return this.videoRef = new Ref();
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
                Log.d("PostPlayMap", "Don't know how to set key: " + s);
            }
            case "postplayContext": {
                this.postplayContext = (Video$PostPlayContext)o;
            }
            case "videoRef": {
                this.videoRef = (Ref)o;
            }
        }
    }
}
