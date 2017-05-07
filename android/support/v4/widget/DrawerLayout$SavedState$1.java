// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class DrawerLayout$SavedState$1 implements Parcelable$Creator<DrawerLayout$SavedState>
{
    public DrawerLayout$SavedState createFromParcel(final Parcel parcel) {
        return new DrawerLayout$SavedState(parcel);
    }
    
    public DrawerLayout$SavedState[] newArray(final int n) {
        return new DrawerLayout$SavedState[n];
    }
}
