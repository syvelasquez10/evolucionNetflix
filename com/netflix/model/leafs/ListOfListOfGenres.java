// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Iterator;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import java.util.ArrayList;

public class ListOfListOfGenres extends ArrayList<GenreList> implements JsonPopulator
{
    @Override
    public void populate(final JsonElement jsonElement) {
        this.clear();
        for (final JsonElement jsonElement2 : jsonElement.getAsJsonArray()) {
            final ListOfGenreSummary listOfGenreSummary = new ListOfGenreSummary();
            listOfGenreSummary.populate(jsonElement2);
            ((ArrayList<ListOfGenreSummary>)this).add(listOfGenreSummary);
        }
    }
}
