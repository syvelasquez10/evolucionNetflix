// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.VideoType;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.servicemgr.SeasonDetails;

public class DummySeasonDetails implements SeasonDetails
{
    private final String id;
    private final int seasonNumber;
    
    public DummySeasonDetails(final String id, final int seasonNumber) {
        this.id = id;
        this.seasonNumber = seasonNumber;
    }
    
    public static List<SeasonDetails> createDummyArray() {
        final ArrayList<DummySeasonDetails> list = (ArrayList<DummySeasonDetails>)new ArrayList<SeasonDetails>();
        for (int i = 0; i < 100; ++i) {
            list.add(new DummySeasonDetails(String.valueOf(i), i));
        }
        return (List<SeasonDetails>)list;
    }
    
    @Override
    public int getCurrentEpisodeNumber() {
        return 0;
    }
    
    @Override
    public String getId() {
        return this.id;
    }
    
    @Override
    public int getNumOfEpisodes() {
        return 0;
    }
    
    @Override
    public int getSeasonNumber() {
        return 0;
    }
    
    @Override
    public String getSeasonNumberTitle(final Context context) {
        return context.getString(2131493114, new Object[] { this.seasonNumber });
    }
    
    @Override
    public String getTitle() {
        return null;
    }
    
    @Override
    public VideoType getType() {
        return VideoType.UNKNOWN;
    }
    
    @Override
    public int getYear() {
        return 0;
    }
}
