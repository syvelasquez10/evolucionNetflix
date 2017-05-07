// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

public class PostPlayFactory
{
    static PostPlay create(final PlayerActivity playerActivity, final PostPlayType postPlayType) {
        switch (postPlayType) {
            default: {
                return new PostPlayForMovies(playerActivity);
            }
            case EpisodesForMDX: {
                return new PostPlayForMDX(playerActivity);
            }
            case EpisodesForPhone:
            case EpisodesForTablet: {
                return new PostPlayForEpisodes(playerActivity);
            }
        }
    }
    
    public enum PostPlayType
    {
        EpisodesForMDX, 
        EpisodesForPhone, 
        EpisodesForTablet, 
        RecommendationForPhone, 
        RecommendationForTablet;
    }
}
