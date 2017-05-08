// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.app.AlertDialog$Builder;
import android.app.Dialog;
import android.os.Bundle;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class AccountSettingsUnavailableAlertDialog extends NetflixDialogFrag
{
    protected static AccountSettingsUnavailableAlertDialog createAccountSettingsUnavailableAlertDialog(final NetflixActivity netflixActivity) {
        final AccountSettingsUnavailableAlertDialog accountSettingsUnavailableAlertDialog = new AccountSettingsUnavailableAlertDialog();
        accountSettingsUnavailableAlertDialog.setStyle(1, 2131361916);
        return accountSettingsUnavailableAlertDialog;
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        super.onCreate(bundle);
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.getActivity());
        alertDialog$Builder.setPositiveButton(2131165588, (DialogInterface$OnClickListener)new AccountSettingsUnavailableAlertDialog$1(this));
        alertDialog$Builder.setMessage((CharSequence)this.getString(2131165361));
        final AlertDialog create = alertDialog$Builder.create();
        create.setCanceledOnTouchOutside(false);
        return (Dialog)create;
    }
}
