// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import java.util.HashSet;
import java.util.Set;
import com.netflix.mediaclient.Log;
import com.netflix.falkor.BranchNode;

public class MementoVideoSwatch implements BranchNode
{
    private static final String TAG = "MementoVideoSwatch";
    public String boxArtUrl;
    public String collectionId;
    public String collectionName;
    public String firstVideoId;
    public String firstVideoTitle;
    public String firstVideoType;
    public String storyArtUrl;
    
    @Override
    public Object get(final String s) {
        switch (s) {
            default: {
                Log.d("MementoVideoSwatch", "Could not find key: " + s);
                return null;
            }
            case "collectionName": {
                return this.collectionName;
            }
            case "collectionId": {
                return this.collectionId;
            }
            case "firstVideoTitle": {
                return this.firstVideoTitle;
            }
            case "firstVideoId": {
                return this.firstVideoId;
            }
            case "firstVideoType": {
                return this.firstVideoType;
            }
            case "boxArtUrl": {
                return this.boxArtUrl;
            }
            case "storyArtUrl": {
                return this.storyArtUrl;
            }
        }
    }
    
    @Override
    public Set<String> getKeys() {
        final HashSet<String> set = new HashSet<String>();
        if (this.collectionName != null) {
            set.add("collectionName");
        }
        if (this.collectionId != null) {
            set.add("collectionId");
        }
        if (this.firstVideoTitle != null) {
            set.add("firstVideoTitle");
        }
        if (this.firstVideoId != null) {
            set.add("firstVideoId");
        }
        if (this.firstVideoType != null) {
            set.add("firstVideoType");
        }
        if (this.boxArtUrl != null) {
            set.add("boxArtUrl");
        }
        if (this.storyArtUrl != null) {
            set.add("storyArtUrl");
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
            case "collectionName": {
                return new String();
            }
            case "collectionId": {
                return new String();
            }
            case "firstVideoTitle": {
                return new String();
            }
            case "firstVideoId": {
                return new String();
            }
            case "firstVideoType": {
                return new String();
            }
            case "boxArtUrl": {
                return new String();
            }
            case "storyArtUrl": {
                return new String();
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
                Log.d("MementoVideoSwatch", "Don't know how to set key: " + s);
            }
            case "collectionName": {
                this.collectionName = (String)o;
            }
            case "collectionId": {
                this.collectionId = (String)o;
            }
            case "firstVideoTitle": {
                this.firstVideoTitle = (String)o;
            }
            case "firstVideoId": {
                this.firstVideoId = (String)o;
            }
            case "firstVideoType": {
                this.firstVideoType = (String)o;
            }
            case "boxArtUrl": {
                this.boxArtUrl = (String)o;
            }
            case "storyArtUrl": {
                this.storyArtUrl = (String)o;
            }
        }
    }
}
