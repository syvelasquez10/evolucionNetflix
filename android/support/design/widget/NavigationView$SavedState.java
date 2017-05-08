// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.os.Parcelable;
import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.os.ParcelableCompat;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import android.support.v4.view.AbsSavedState;

public class NavigationView$SavedState extends AbsSavedState
{
    public static final Parcelable$Creator<NavigationView$SavedState> CREATOR;
    public Bundle menuState;
    
    static {
        CREATOR = ParcelableCompat.newCreator((ParcelableCompatCreatorCallbacks<NavigationView$SavedState>)new NavigationView$SavedState$1());
    }
    
    public NavigationView$SavedState(final Parcel parcel, final ClassLoader classLoader) {
        super(parcel, classLoader);
        this.menuState = parcel.readBundle(classLoader);
    }
    
    public NavigationView$SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeBundle(this.menuState);
    }
}
