// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import java.util.HashSet;
import java.util.Set;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.falkor.Ref;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import com.netflix.model.BaseFalkorObject;

public class FalkorIrisNotification extends BaseFalkorObject implements FalkorObject
{
    public IrisNotificationSummary summary;
    public Ref video;
    
    public FalkorIrisNotification(final ModelProxy<? extends BranchNode> modelProxy) {
        super(modelProxy);
    }
    
    @Override
    public Object get(final String s) {
        switch (s) {
            default: {
                return null;
            }
            case "summary": {
                return this.summary;
            }
            case "notificationVideo": {
                return this.video;
            }
        }
    }
    
    @Override
    public Set<String> getKeys() {
        final HashSet<String> set = new HashSet<String>();
        if (this.summary != null) {
            set.add("summary");
        }
        if (this.video != null) {
            set.add("notificationVideo");
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
                return null;
            }
            case "summary": {
                return this.summary = new IrisNotificationSummary();
            }
            case "notificationVideo": {
                return this.video = new Ref();
            }
        }
    }
    
    @Override
    public void remove(final String s) {
        this.set(s, null);
    }
    
    @Override
    public void set(final String s, final Object o) {
        if ("summary".equals(s)) {
            this.summary = (IrisNotificationSummary)o;
            return;
        }
        if ("notificationVideo".equals(s)) {
            this.video = (Ref)o;
            return;
        }
        throw new IllegalStateException("Can't set key: " + s);
    }
}
