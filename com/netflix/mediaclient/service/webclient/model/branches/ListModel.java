// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.branches;

import java.util.List;

public abstract class ListModel<S, M> implements List<M>
{
    private S summary;
    
    public S getSummary() {
        return this.summary;
    }
}
