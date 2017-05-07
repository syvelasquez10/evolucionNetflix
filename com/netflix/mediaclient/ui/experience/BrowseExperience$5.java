// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.experience;

import java.util.HashMap;
import com.netflix.mediaclient.ui.search.SearchUtils$SearchExperience;

final class BrowseExperience$5 extends BrowseExperience$ExperienceData
{
    BrowseExperience$5() {
        super(null);
        ((HashMap<String, SearchUtils$SearchExperience>)this).put("SEARCH_EXPERIENCE", SearchUtils$SearchExperience.KUBRICK);
        ((HashMap<String, Integer>)this).put("LOMO_FRAG_OFFSET_LEFT_DIMEN_INT", 2131296454);
        ((HashMap<String, Integer>)this).put("LOMO_FRAG_OFFSET_RIGHT_DIMEN_INT", 2131296455);
        ((HashMap<String, Boolean>)this).put("SHOULD_LOAD_KUBRICK_LEAVES_BOOL", true);
        ((HashMap<String, Boolean>)this).put("SHOULD_INCLUDE_CHARACTER_LEAVES_BOOL", true);
    }
}
