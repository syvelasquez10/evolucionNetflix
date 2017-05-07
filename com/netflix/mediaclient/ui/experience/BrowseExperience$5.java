// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.experience;

import java.util.HashMap;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.ui.search.SearchUtils$SearchExperience;

final class BrowseExperience$5 extends BrowseExperience$ExperienceMap
{
    BrowseExperience$5() {
        super(null);
        ((HashMap<String, SearchUtils$SearchExperience>)this).put("SEARCH_EXPERIENCE_ENUM", SearchUtils$SearchExperience.KUBRICK);
        ((HashMap<String, Integer>)this).put("LOMO_FRAG_OFFSET_LEFT_DIMEN_INT", 2131296445);
        ((HashMap<String, Integer>)this).put("LOMO_FRAG_OFFSET_RIGHT_DIMEN_INT", 2131296446);
        ((HashMap<String, Boolean>)this).put("SHOULD_LOAD_KUBRICK_LEAVES_IN_LOLOMO_BOOL", true);
        ((HashMap<String, Boolean>)this).put("SHOULD_LOAD_KUBRICK_LEAVES_IN_DETAILS_BOOL", true);
        ((HashMap<String, Boolean>)this).put("SHOULD_INCLUDE_CHARACTER_LEAVES_BOOL", true);
        ((HashMap<String, ImageLoader$StaticImgConfig>)this).put("IMAGE_LOADER_CONFIG_ENUM", ImageLoader$StaticImgConfig.LIGHT);
        ((HashMap<String, ImageLoader$StaticImgConfig>)this).put("IMAGE_LOADER_CONFIG_NO_PLACEHOLDER_ENUM", ImageLoader$StaticImgConfig.LIGHT_NO_PLACEHOLDER);
    }
}
