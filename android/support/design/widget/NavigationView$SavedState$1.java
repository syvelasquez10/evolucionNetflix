// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

final class NavigationView$SavedState$1 implements ParcelableCompatCreatorCallbacks<NavigationView$SavedState>
{
    @Override
    public NavigationView$SavedState createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
        return new NavigationView$SavedState(parcel, classLoader);
    }
    
    @Override
    public NavigationView$SavedState[] newArray(final int n) {
        return new NavigationView$SavedState[n];
    }
}
