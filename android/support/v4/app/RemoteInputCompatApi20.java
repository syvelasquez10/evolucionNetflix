// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.RemoteInput$Builder;
import android.app.RemoteInput;

class RemoteInputCompatApi20
{
    static RemoteInput[] fromCompat(final RemoteInputCompatBase$RemoteInput[] array) {
        if (array == null) {
            return null;
        }
        final RemoteInput[] array2 = new RemoteInput[array.length];
        for (int i = 0; i < array.length; ++i) {
            final RemoteInputCompatBase$RemoteInput remoteInputCompatBase$RemoteInput = array[i];
            array2[i] = new RemoteInput$Builder(remoteInputCompatBase$RemoteInput.getResultKey()).setLabel(remoteInputCompatBase$RemoteInput.getLabel()).setChoices(remoteInputCompatBase$RemoteInput.getChoices()).setAllowFreeFormInput(remoteInputCompatBase$RemoteInput.getAllowFreeFormInput()).addExtras(remoteInputCompatBase$RemoteInput.getExtras()).build();
        }
        return array2;
    }
}
