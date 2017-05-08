// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.Parcelable;
import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.os.ParcelableCompat;
import android.os.Parcelable$Creator;
import android.support.v4.view.AbsSavedState;

class SearchView$SavedState extends AbsSavedState
{
    public static final Parcelable$Creator<SearchView$SavedState> CREATOR;
    boolean isIconified;
    
    static {
        CREATOR = ParcelableCompat.newCreator((ParcelableCompatCreatorCallbacks<SearchView$SavedState>)new SearchView$SavedState$1());
    }
    
    public SearchView$SavedState(final Parcel parcel, final ClassLoader classLoader) {
        super(parcel, classLoader);
        this.isIconified = (boolean)parcel.readValue((ClassLoader)null);
    }
    
    SearchView$SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    @Override
    public String toString() {
        return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.isIconified + "}";
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeValue((Object)this.isIconified);
    }
}
