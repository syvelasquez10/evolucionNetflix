// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.List;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;

public final class Api<O extends Api$ApiOptions>
{
    private final Api$b<?, O> Ij;
    private final Api$c<?> Ik;
    private final ArrayList<Scope> Il;
    
    public Api(final Api$b<C, O> ij, final Api$c<C> ik, final Scope... array) {
        this.Ij = ij;
        this.Ik = ik;
        this.Il = new ArrayList<Scope>(Arrays.asList(array));
    }
    
    public Api$b<?, O> gd() {
        return this.Ij;
    }
    
    public List<Scope> ge() {
        return this.Il;
    }
    
    public Api$c<?> gf() {
        return this.Ik;
    }
}
