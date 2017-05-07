// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.trackable;

import com.netflix.model.branches.FalkorValidator;

public interface SearchTrackable extends Trackable, FalkorValidator
{
    String getReferenceId();
}
