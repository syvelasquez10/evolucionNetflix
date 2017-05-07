// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.model.leafs.ListOfMoviesSummary;
import com.netflix.falkor.Ref;
import com.netflix.model.branches.SummarizedList;
import com.netflix.falkor.Func;

final class Falkor$Creator$7 implements Func<SummarizedList<Ref, ListOfMoviesSummary>>
{
    @Override
    public SummarizedList<Ref, ListOfMoviesSummary> call() {
        return new SummarizedList<Ref, ListOfMoviesSummary>(Falkor$Creator.Ref, Falkor$Creator.ListOfMoviesSummary);
    }
}
