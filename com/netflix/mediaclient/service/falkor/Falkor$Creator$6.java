// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.model.leafs.ListOfMoviesSummary;
import com.netflix.falkor.Func;

final class Falkor$Creator$6 implements Func<ListOfMoviesSummary>
{
    @Override
    public ListOfMoviesSummary call() {
        return new ListOfMoviesSummary();
    }
}
