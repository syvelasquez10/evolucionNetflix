// 
// Decompiled by Procyon v0.5.30
// 

package com.viewpagerindicator.android.osp;

import java.util.Comparator;

final class ViewPager$1 implements Comparator<ViewPager$ItemInfo>
{
    @Override
    public int compare(final ViewPager$ItemInfo viewPager$ItemInfo, final ViewPager$ItemInfo viewPager$ItemInfo2) {
        return viewPager$ItemInfo.position - viewPager$ItemInfo2.position;
    }
}
