// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import java.util.EnumMap;

final class LoMoViewPagerAdapter$1 extends EnumMap<LoMoType, LoMoViewPagerAdapter$Type>
{
    LoMoViewPagerAdapter$1(final Class clazz) {
        super(clazz);
        this.put(LoMoType.BILLBOARD, LoMoViewPagerAdapter$Type.BILLBOARD);
        this.put(LoMoType.CHARACTERS, LoMoViewPagerAdapter$Type.CHARACTER);
        this.put(LoMoType.CONTINUE_WATCHING, LoMoViewPagerAdapter$Type.CW);
        this.put(LoMoType.INSTANT_QUEUE, LoMoViewPagerAdapter$Type.IQ);
        this.put(LoMoType.FLAT_GENRE, LoMoViewPagerAdapter$Type.STANDARD);
        this.put(LoMoType.SOCIAL_FRIEND, LoMoViewPagerAdapter$Type.STANDARD);
        this.put(LoMoType.SOCIAL_GROUP, LoMoViewPagerAdapter$Type.STANDARD);
        this.put(LoMoType.SOCIAL_POPULAR, LoMoViewPagerAdapter$Type.STANDARD);
        this.put(LoMoType.STANDARD, LoMoViewPagerAdapter$Type.STANDARD);
        this.put(LoMoType.POPULAR_TITLES, LoMoViewPagerAdapter$Type.STANDARD);
        this.put(LoMoType.TOP_TEN, LoMoViewPagerAdapter$Type.STANDARD);
    }
}
