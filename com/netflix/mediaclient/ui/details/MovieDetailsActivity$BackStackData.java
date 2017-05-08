// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.support.v7.widget.RecyclerView$LayoutManager;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.AndroidUtils;
import android.support.v7.widget.GridLayoutManager;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import android.os.Parcel;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class MovieDetailsActivity$BackStackData implements Parcelable
{
    public static final Parcelable$Creator<MovieDetailsActivity$BackStackData> CREATOR;
    public boolean bIsShow;
    public Parcelable layoutManagerState;
    public final PlayContext playContext;
    public final String videoId;
    
    static {
        CREATOR = (Parcelable$Creator)new MovieDetailsActivity$BackStackData$1();
    }
    
    private MovieDetailsActivity$BackStackData(final Parcel parcel) {
    Label_0051_Outer:
        while (true) {
            this.videoId = parcel.readString();
            this.playContext = (PlayContext)parcel.readParcelable(PlayContextImp.class.getClassLoader());
            while (true) {
                while (true) {
                    try {
                        this.layoutManagerState = parcel.readParcelable(AndroidUtils.getClassLoader(GridLayoutManager.class));
                        if (parcel.readByte() == 1) {
                            final boolean bIsShow = true;
                            this.bIsShow = bIsShow;
                            return;
                        }
                    }
                    catch (Throwable t) {
                        Log.e("MovieDetailsActivity", "SPY-8852: Failed to load layout manager state", t);
                        ErrorLoggingManager.logHandledException(t);
                        continue Label_0051_Outer;
                    }
                    break;
                }
                final boolean bIsShow = false;
                continue;
            }
        }
    }
    
    public MovieDetailsActivity$BackStackData(final String videoId, final PlayContext playContext, final RecyclerView$LayoutManager recyclerView$LayoutManager) {
        this.videoId = videoId;
        this.playContext = playContext;
        if (recyclerView$LayoutManager != null) {
            this.layoutManagerState = recyclerView$LayoutManager.onSaveInstanceState();
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public String toString() {
        return "BackStackData [videoId=" + this.videoId + ", playContext=" + this.playContext + ", layoutManagerState=" + this.layoutManagerState + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        byte b = 0;
        parcel.writeString(this.videoId);
        parcel.writeParcelable((Parcelable)this.playContext, n);
        parcel.writeParcelable(this.layoutManagerState, 0);
        if (this.bIsShow) {
            b = 1;
        }
        parcel.writeByte(b);
    }
}
