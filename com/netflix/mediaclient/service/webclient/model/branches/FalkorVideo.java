// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.branches;

import java.util.HashSet;
import java.util.Set;
import com.netflix.mediaclient.service.webclient.model.leafs.SocialEvidence;

public abstract class FalkorVideo extends Video
{
    public Bookmark bookmark;
    public Detail detail;
    public InQueue inQueue;
    public Rating rating;
    public SocialEvidence socialEvidence;
    public Summary summary;
    
    @Override
    public Object get(final String s) {
        if ("summary".equals(s)) {
            return this.summary;
        }
        if ("detail".equals(s)) {
            return this.detail;
        }
        if ("rating".equals(s)) {
            return this.rating;
        }
        if ("inQueue".equals(s)) {
            return this.inQueue;
        }
        if ("bookmark".equals(s)) {
            return this.bookmark;
        }
        if ("socialEvidence".equals(s)) {
            return this.socialEvidence;
        }
        return null;
    }
    
    @Override
    public Set<String> getKeys() {
        final HashSet<String> set = new HashSet<String>();
        if (this.summary != null) {
            set.add("summary");
        }
        if (this.detail != null) {
            set.add("detail");
        }
        if (this.rating != null) {
            set.add("rating");
        }
        if (this.inQueue != null) {
            set.add("inQueue");
        }
        if (this.bookmark != null) {
            set.add("bookmark");
        }
        if (this.socialEvidence != null) {
            set.add("socialEvidence");
        }
        return set;
    }
    
    @Override
    public Object getOrCreate(final String s) {
        final Object value = this.get(s);
        if (value != null) {
            return value;
        }
        if ("summary".equals(s)) {
            return this.summary = new Summary();
        }
        if ("detail".equals(s)) {
            return this.detail = new Detail();
        }
        if ("rating".equals(s)) {
            return this.rating = new Rating();
        }
        if ("inQueue".equals(s)) {
            return this.inQueue = new InQueue();
        }
        if ("bookmark".equals(s)) {
            return this.bookmark = new Bookmark();
        }
        if ("socialEvidence".equals(s)) {
            return this.socialEvidence = new SocialEvidence();
        }
        return null;
    }
    
    @Override
    public void set(final String s, final Object o) {
        if ("summary".equals(s)) {
            this.summary = (Summary)o;
        }
        else {
            if ("detail".equals(s)) {
                this.detail = (Detail)o;
                return;
            }
            if ("rating".equals(s)) {
                this.rating = (Rating)o;
                return;
            }
            if ("inQueue".equals(s)) {
                this.inQueue = (InQueue)o;
                return;
            }
            if ("bookmark".equals(s)) {
                this.bookmark = (Bookmark)o;
                return;
            }
            if ("socialEvidence".equals(s)) {
                this.socialEvidence = (SocialEvidence)o;
            }
        }
    }
}
