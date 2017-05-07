// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.media.MediaRouteSelector;
import android.support.v4.app.DialogFragment;

public class MediaRouteChooserDialogFragment extends DialogFragment
{
    private final String ARGUMENT_SELECTOR;
    private MediaRouteSelector mSelector;
    
    public MediaRouteChooserDialogFragment() {
        this.ARGUMENT_SELECTOR = "selector";
        this.setCancelable(true);
    }
    
    private void ensureRouteSelector() {
        if (this.mSelector == null) {
            final Bundle arguments = this.getArguments();
            if (arguments != null) {
                this.mSelector = MediaRouteSelector.fromBundle(arguments.getBundle("selector"));
            }
            if (this.mSelector == null) {
                this.mSelector = MediaRouteSelector.EMPTY;
            }
        }
    }
    
    public MediaRouteSelector getRouteSelector() {
        this.ensureRouteSelector();
        return this.mSelector;
    }
    
    public MediaRouteChooserDialog onCreateChooserDialog(final Context context, final Bundle bundle) {
        return new MediaRouteChooserDialog(context);
    }
    
    @Override
    public Dialog onCreateDialog(final Bundle bundle) {
        final MediaRouteChooserDialog onCreateChooserDialog = this.onCreateChooserDialog((Context)this.getActivity(), bundle);
        onCreateChooserDialog.setRouteSelector(this.getRouteSelector());
        return onCreateChooserDialog;
    }
    
    public void setRouteSelector(final MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        this.ensureRouteSelector();
        if (!this.mSelector.equals(mediaRouteSelector)) {
            this.mSelector = mediaRouteSelector;
            Bundle arguments;
            if ((arguments = this.getArguments()) == null) {
                arguments = new Bundle();
            }
            arguments.putBundle("selector", mediaRouteSelector.asBundle());
            this.setArguments(arguments);
            final MediaRouteChooserDialog mediaRouteChooserDialog = (MediaRouteChooserDialog)this.getDialog();
            if (mediaRouteChooserDialog != null) {
                mediaRouteChooserDialog.setRouteSelector(mediaRouteSelector);
            }
        }
    }
}
