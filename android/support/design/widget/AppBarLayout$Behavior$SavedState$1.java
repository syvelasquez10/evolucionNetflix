// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

final class AppBarLayout$Behavior$SavedState$1 implements ParcelableCompatCreatorCallbacks<AppBarLayout$Behavior$SavedState>
{
    @Override
    public AppBarLayout$Behavior$SavedState createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
        return new AppBarLayout$Behavior$SavedState(parcel, classLoader);
    }
    
    @Override
    public AppBarLayout$Behavior$SavedState[] newArray(final int n) {
        return new AppBarLayout$Behavior$SavedState[n];
    }
}
