// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.branches;

import com.netflix.mediaclient.service.webclient.model.leafs.ListSummary;
import java.util.List;

public abstract class ListModel<M> implements List<M>
{
    private ListSummary summary;
    
    public ListSummary getSummary() {
        return this.summary;
    }
}
