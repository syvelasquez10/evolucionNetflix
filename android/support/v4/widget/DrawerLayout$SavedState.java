// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;

public class DrawerLayout$SavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator<DrawerLayout$SavedState> CREATOR;
    int lockModeLeft;
    int lockModeRight;
    int openDrawerGravity;
    
    static {
        CREATOR = (Parcelable$Creator)new DrawerLayout$SavedState$1();
    }
    
    public DrawerLayout$SavedState(final Parcel parcel) {
        super(parcel);
        this.openDrawerGravity = 0;
        this.lockModeLeft = 0;
        this.lockModeRight = 0;
        this.openDrawerGravity = parcel.readInt();
    }
    
    public DrawerLayout$SavedState(final Parcelable parcelable) {
        super(parcelable);
        this.openDrawerGravity = 0;
        this.lockModeLeft = 0;
        this.lockModeRight = 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeInt(this.openDrawerGravity);
    }
}
