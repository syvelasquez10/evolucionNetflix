// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.branches;

import com.netflix.mediaclient.service.webclient.model.leafs.ListSummary;

public class Season
{
    public Detail detail;
    public ListModel<ListSummary, ListOfEpisodes> episodes;
    
    public static class Detail extends Summary
    {
        public int currentEpisodeNumber;
        private int episodeCount;
        private int number;
        private int year;
        
        public int getCurrentEpisodeNumber() {
            return this.currentEpisodeNumber;
        }
        
        public int getEpisodeCount() {
            return this.episodeCount;
        }
        
        public int getNumber() {
            return this.number;
        }
        
        public int getYear() {
            return this.year;
        }
        
        public Detail setId(final String id) {
            this.id = id;
            return this;
        }
    }
}
