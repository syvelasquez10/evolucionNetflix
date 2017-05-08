// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.os.Parcelable;
import android.text.TextUtils;
import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.os.ParcelableCompat;
import android.os.Parcelable$Creator;
import android.support.v4.view.AbsSavedState;

class TextInputLayout$SavedState extends AbsSavedState
{
    public static final Parcelable$Creator<TextInputLayout$SavedState> CREATOR;
    CharSequence error;
    
    static {
        CREATOR = ParcelableCompat.newCreator((ParcelableCompatCreatorCallbacks<TextInputLayout$SavedState>)new TextInputLayout$SavedState$1());
    }
    
    TextInputLayout$SavedState(final Parcel parcel, final ClassLoader classLoader) {
        super(parcel, classLoader);
        this.error = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
    }
    
    TextInputLayout$SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    @Override
    public String toString() {
        return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + (Object)this.error + "}";
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        TextUtils.writeToParcel(this.error, parcel, n);
    }
}
