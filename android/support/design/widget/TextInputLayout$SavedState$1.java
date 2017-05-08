// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

final class TextInputLayout$SavedState$1 implements ParcelableCompatCreatorCallbacks<TextInputLayout$SavedState>
{
    @Override
    public TextInputLayout$SavedState createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
        return new TextInputLayout$SavedState(parcel, classLoader);
    }
    
    @Override
    public TextInputLayout$SavedState[] newArray(final int n) {
        return new TextInputLayout$SavedState[n];
    }
}
