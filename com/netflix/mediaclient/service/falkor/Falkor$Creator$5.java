// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.model.leafs.ListOfMoviesSummary;
import com.netflix.falkor.Ref;
import com.netflix.model.branches.SummarizedList;
import com.netflix.falkor.Func;

final class Falkor$Creator$5 implements Func<SummarizedList<Ref, ListOfMoviesSummary>>
{
    @Override
    public SummarizedList<Ref, ListOfMoviesSummary> call() {
        return (SummarizedList<Ref, ListOfMoviesSummary>)new SummarizedList((Func)Falkor$Creator.Ref, (Func)Falkor$Creator.ListOfMoviesSummary);
    }
}
