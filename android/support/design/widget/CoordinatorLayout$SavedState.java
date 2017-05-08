// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.os.ParcelableCompat;
import android.os.Parcelable;
import android.util.SparseArray;
import android.os.Parcelable$Creator;
import android.support.v4.view.AbsSavedState;

public class CoordinatorLayout$SavedState extends AbsSavedState
{
    public static final Parcelable$Creator<CoordinatorLayout$SavedState> CREATOR;
    SparseArray<Parcelable> behaviorStates;
    
    static {
        CREATOR = ParcelableCompat.newCreator((ParcelableCompatCreatorCallbacks<CoordinatorLayout$SavedState>)new CoordinatorLayout$SavedState$1());
    }
    
    public CoordinatorLayout$SavedState(final Parcel parcel, final ClassLoader classLoader) {
        super(parcel, classLoader);
        final int int1 = parcel.readInt();
        final int[] array = new int[int1];
        parcel.readIntArray(array);
        final Parcelable[] parcelableArray = parcel.readParcelableArray(classLoader);
        this.behaviorStates = (SparseArray<Parcelable>)new SparseArray(int1);
        for (int i = 0; i < int1; ++i) {
            this.behaviorStates.append(array[i], (Object)parcelableArray[i]);
        }
    }
    
    public CoordinatorLayout$SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        int i = 0;
        super.writeToParcel(parcel, n);
        int size;
        if (this.behaviorStates != null) {
            size = this.behaviorStates.size();
        }
        else {
            size = 0;
        }
        parcel.writeInt(size);
        final int[] array = new int[size];
        final Parcelable[] array2 = new Parcelable[size];
        while (i < size) {
            array[i] = this.behaviorStates.keyAt(i);
            array2[i] = (Parcelable)this.behaviorStates.valueAt(i);
            ++i;
        }
        parcel.writeIntArray(array);
        parcel.writeParcelableArray(array2, n);
    }
}
