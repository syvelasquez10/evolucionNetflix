// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.os.Parcel;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

class ActionBarActivityDelegateBase$PanelFeatureState$SavedState implements Parcelable
{
    public static final Parcelable$Creator<ActionBarActivityDelegateBase$PanelFeatureState$SavedState> CREATOR;
    int featureId;
    boolean isOpen;
    Bundle menuState;
    
    static {
        CREATOR = (Parcelable$Creator)new ActionBarActivityDelegateBase$PanelFeatureState$SavedState$1();
    }
    
    private static ActionBarActivityDelegateBase$PanelFeatureState$SavedState readFromParcel(final Parcel parcel) {
        boolean isOpen = true;
        final ActionBarActivityDelegateBase$PanelFeatureState$SavedState actionBarActivityDelegateBase$PanelFeatureState$SavedState = new ActionBarActivityDelegateBase$PanelFeatureState$SavedState();
        actionBarActivityDelegateBase$PanelFeatureState$SavedState.featureId = parcel.readInt();
        if (parcel.readInt() != 1) {
            isOpen = false;
        }
        actionBarActivityDelegateBase$PanelFeatureState$SavedState.isOpen = isOpen;
        if (actionBarActivityDelegateBase$PanelFeatureState$SavedState.isOpen) {
            actionBarActivityDelegateBase$PanelFeatureState$SavedState.menuState = parcel.readBundle();
        }
        return actionBarActivityDelegateBase$PanelFeatureState$SavedState;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, int n) {
        parcel.writeInt(this.featureId);
        if (this.isOpen) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        if (this.isOpen) {
            parcel.writeBundle(this.menuState);
        }
    }
}
