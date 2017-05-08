// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.netflix.falkor.BranchNodeUtils;
import com.fasterxml.jackson.core.JsonParser;
import java.util.Iterator;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import java.util.ArrayList;

public class ListOfListOfGenres extends ArrayList<GenreList> implements JsonMerger, JsonPopulator
{
    @Override
    public void populate(final JsonElement jsonElement) {
        this.clear();
        for (final JsonElement jsonElement2 : jsonElement.getAsJsonObject().get("value").getAsJsonArray()) {
            final ListOfGenreSummary listOfGenreSummary = new ListOfGenreSummary();
            listOfGenreSummary.populate(jsonElement2);
            ((ArrayList<ListOfGenreSummary>)this).add(listOfGenreSummary);
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        if (s == "length") {
            jsonParser.getIntValue();
            return true;
        }
        final int int1 = Integer.parseInt(s);
        final ListOfGenreSummary listOfGenreSummary = new ListOfGenreSummary();
        ((ArrayList<ListOfGenreSummary>)this).add(int1, listOfGenreSummary);
        BranchNodeUtils.merge(listOfGenreSummary, jsonParser, jsonParser.getCurrentToken(), false, 10);
        return true;
    }
}
