// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.experience;

import java.util.HashMap;
import java.io.Serializable;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import android.content.Context;
import com.netflix.mediaclient.ui.search.SearchUtils$SearchExperience;
import com.netflix.mediaclient.ui.kubrick.lolomo.KubrickLoLoMoAdapter;
import com.netflix.mediaclient.ui.kubrick.lolomo.KubrickGenreLoLoMoAdapter;
import com.netflix.mediaclient.ui.lolomo.LoLoMoAdapter;
import com.netflix.mediaclient.ui.lolomo.GenreLoLoMoAdapter;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag$ILoLoMoAdapter;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;

public enum BrowseExperience
{
    KUBRICK("KUBRICK", 1, (BrowseExperience$ExperienceData)new BrowseExperience$3()) {
        BrowseExperience$4(final String s, final int n, final BrowseExperience$ExperienceData browseExperience$ExperienceData) {
        }
        
        @Override
        public LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag loLoMoFrag, final boolean b, final String s) {
            if (b) {
                return new KubrickGenreLoLoMoAdapter(loLoMoFrag, s, true);
            }
            return new KubrickLoLoMoAdapter(loLoMoFrag);
        }
    }, 
    KUBRICK_KIDS("KUBRICK_KIDS", 2, (BrowseExperience$ExperienceData)new BrowseExperience$5()) {
        BrowseExperience$6(final String s, final int n, final BrowseExperience$ExperienceData browseExperience$ExperienceData) {
        }
        
        @Override
        public LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag loLoMoFrag, final boolean b, final String s) {
            if (b) {
                return new KubrickGenreLoLoMoAdapter(loLoMoFrag, s, false);
            }
            return new LoLoMoAdapter(loLoMoFrag);
        }
    };
    
    protected static final String LOMO_FRAG_OFFSET_LEFT_DIMEN_INT = "LOMO_FRAG_OFFSET_LEFT_DIMEN_INT";
    protected static final String LOMO_FRAG_OFFSET_RIGHT_DIMEN_INT = "LOMO_FRAG_OFFSET_RIGHT_DIMEN_INT";
    protected static final String SEARCH_EXPERIENCE = "SEARCH_EXPERIENCE";
    protected static final String SHOULD_INCLUDE_CHARACTER_LEAVES_BOOL = "SHOULD_INCLUDE_CHARACTER_LEAVES_BOOL";
    protected static final String SHOULD_LOAD_KUBRICK_LEAVES_BOOL = "SHOULD_LOAD_KUBRICK_LEAVES_BOOL";
    
    STANDARD("STANDARD", 0, (BrowseExperience$ExperienceData)new BrowseExperience$1()) {
        BrowseExperience$2(final String s, final int n, final BrowseExperience$ExperienceData browseExperience$ExperienceData) {
        }
        
        @Override
        public LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag loLoMoFrag, final boolean b, final String s) {
            if (b) {
                return new GenreLoLoMoAdapter(loLoMoFrag, s);
            }
            return new LoLoMoAdapter(loLoMoFrag);
        }
    };
    
    private static final String TAG = "BrowseExperience";
    private static BrowseExperience currExperience;
    private final BrowseExperience$ExperienceData data;
    
    static {
        BrowseExperience.currExperience = BrowseExperience.STANDARD;
    }
    
    private BrowseExperience(final BrowseExperience$ExperienceData data) {
        this.data = data;
    }
    
    public static BrowseExperience get() {
        return BrowseExperience.currExperience;
    }
    
    public static int getLomoFragOffsetLeftDimenId() {
        return ((HashMap<K, Integer>)BrowseExperience.currExperience.data).get("LOMO_FRAG_OFFSET_LEFT_DIMEN_INT");
    }
    
    public static int getLomoFragOffsetRightDimenId() {
        return ((HashMap<K, Integer>)BrowseExperience.currExperience.data).get("LOMO_FRAG_OFFSET_RIGHT_DIMEN_INT");
    }
    
    public static SearchUtils$SearchExperience getSearchExperience() {
        return ((HashMap<K, SearchUtils$SearchExperience>)BrowseExperience.currExperience.data).get("SEARCH_EXPERIENCE");
    }
    
    public static boolean isKubrick() {
        return BrowseExperience.currExperience == BrowseExperience.KUBRICK;
    }
    
    public static boolean isKubrickKids() {
        return BrowseExperience.currExperience == BrowseExperience.KUBRICK_KIDS;
    }
    
    public static boolean isStandard() {
        return BrowseExperience.currExperience == BrowseExperience.STANDARD;
    }
    
    public static void refresh(final Context context, final UserProfile userProfile) {
        final PersistentExperience value = PersistentExperience.get(context);
        int n;
        if (userProfile != null && userProfile.isKidsProfile()) {
            n = 1;
        }
        else {
            n = 0;
        }
        BrowseExperience currExperience;
        if (value == PersistentExperience.KUBRICK) {
            if (n != 0) {
                currExperience = BrowseExperience.KUBRICK_KIDS;
            }
            else {
                currExperience = BrowseExperience.KUBRICK;
            }
        }
        else {
            currExperience = BrowseExperience.STANDARD;
        }
        BrowseExperience.currExperience = currExperience;
        if (Log.isLoggable()) {
            final BrowseExperience currExperience2 = BrowseExperience.currExperience;
            Serializable value2;
            if (userProfile == null) {
                value2 = "null profile";
            }
            else {
                value2 = userProfile.isKidsProfile();
            }
            Log.v("BrowseExperience", String.format("Experience updated to: %s, profile: %s, is kids profile: %s", currExperience2, userProfile, value2));
        }
    }
    
    public static boolean shouldLoadExtraCharacterLeaves() {
        return ((HashMap<K, Boolean>)BrowseExperience.currExperience.data).get("SHOULD_INCLUDE_CHARACTER_LEAVES_BOOL");
    }
    
    public static boolean shouldLoadKubrickLeaves() {
        return ((HashMap<K, Boolean>)BrowseExperience.currExperience.data).get("SHOULD_LOAD_KUBRICK_LEAVES_BOOL");
    }
    
    public abstract LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag p0, final boolean p1, final String p2);
}
