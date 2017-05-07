// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.app.Dialog;
import android.os.Bundle;
import android.content.Context;
import android.support.v4.app.DialogFragment;

public class MediaRouteControllerDialogFragment extends DialogFragment
{
    public MediaRouteControllerDialogFragment() {
        this.setCancelable(true);
    }
    
    public MediaRouteControllerDialog onCreateControllerDialog(final Context context, final Bundle bundle) {
        return new MediaRouteControllerDialog(context);
    }
    
    @Override
    public Dialog onCreateDialog(final Bundle bundle) {
        return this.onCreateControllerDialog((Context)this.getActivity(), bundle);
    }
}
