// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class RelatedTitleState implements Parcelable
{
    public static final Parcelable$Creator<RelatedTitleState> CREATOR;
    private static final String TAG = "RelatedTitleState";
    int orientation;
    Parcelable recyclerViewState;
    int seasonSelectIndex;
    String titleId;
    
    static {
        CREATOR = (Parcelable$Creator)new RelatedTitleState$1();
    }
    
    private RelatedTitleState(final Parcel parcel) {
        while (true) {
            try {
                this.recyclerViewState = parcel.readParcelable(Parcelable.class.getClassLoader());
                this.seasonSelectIndex = parcel.readInt();
                this.orientation = parcel.readInt();
                this.titleId = parcel.readString();
            }
            catch (Throwable t) {
                Log.e("RelatedTitleState", "SPY-9006: Failed to load layout manager state", t);
                ErrorLoggingManager.logHandledException(t);
                continue;
            }
            break;
        }
    }
    
    RelatedTitleState(final String titleId, final Parcelable recyclerViewState, final int seasonSelectIndex, final int orientation) {
        this.seasonSelectIndex = seasonSelectIndex;
        this.recyclerViewState = recyclerViewState;
        this.orientation = orientation;
        this.titleId = titleId;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeParcelable(this.recyclerViewState, n);
        parcel.writeInt(this.seasonSelectIndex);
        parcel.writeInt(this.orientation);
        parcel.writeString(this.titleId);
    }
}
