// 
// Decompiled by Procyon v0.5.30
// 

package com.viewpagerindicator.android.osp;

import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

final class ViewPager$SavedState$1 implements ParcelableCompatCreatorCallbacks<ViewPager$SavedState>
{
    @Override
    public ViewPager$SavedState createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
        return new ViewPager$SavedState(parcel, classLoader);
    }
    
    @Override
    public ViewPager$SavedState[] newArray(final int n) {
        return new ViewPager$SavedState[n];
    }
}
