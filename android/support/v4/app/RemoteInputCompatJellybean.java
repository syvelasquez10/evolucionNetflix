// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Bundle;

class RemoteInputCompatJellybean
{
    static Bundle toBundle(final RemoteInputCompatBase$RemoteInput remoteInputCompatBase$RemoteInput) {
        final Bundle bundle = new Bundle();
        bundle.putString("resultKey", remoteInputCompatBase$RemoteInput.getResultKey());
        bundle.putCharSequence("label", remoteInputCompatBase$RemoteInput.getLabel());
        bundle.putCharSequenceArray("choices", remoteInputCompatBase$RemoteInput.getChoices());
        bundle.putBoolean("allowFreeFormInput", remoteInputCompatBase$RemoteInput.getAllowFreeFormInput());
        bundle.putBundle("extras", remoteInputCompatBase$RemoteInput.getExtras());
        return bundle;
    }
    
    static Bundle[] toBundleArray(final RemoteInputCompatBase$RemoteInput[] array) {
        if (array == null) {
            return null;
        }
        final Bundle[] array2 = new Bundle[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = toBundle(array[i]);
        }
        return array2;
    }
}
