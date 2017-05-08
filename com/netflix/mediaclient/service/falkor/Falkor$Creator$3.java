// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.falkor.Ref;
import com.netflix.model.branches.UnsummarizedList;
import com.netflix.falkor.Func;

final class Falkor$Creator$3 implements Func<UnsummarizedList<Ref>>
{
    @Override
    public UnsummarizedList<Ref> call() {
        return (UnsummarizedList<Ref>)new UnsummarizedList((Func)Falkor$Creator.Ref);
    }
}
