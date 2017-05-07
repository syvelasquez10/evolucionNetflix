// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

public final class Season
{
    public static final class Detail
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
    }
}
