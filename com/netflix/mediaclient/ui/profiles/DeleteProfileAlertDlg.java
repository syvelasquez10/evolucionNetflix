// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.profiles;

import com.netflix.mediaclient.android.app.Status;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.view.View;
import android.app.AlertDialog$Builder;
import android.view.ViewGroup;
import android.app.Dialog;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.content.Context;
import android.os.Bundle;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class DeleteProfileAlertDlg extends NetflixDialogFrag
{
    private static final String AVATAR_URL = "url";
    private static final String PROFILE_NAME = "name";
    private static final String TAG = "nf_deleteprofilealertdlg";
    private AdvancedImageView mProfileAvatar;
    private TextView mProfileName;
    
    protected static DeleteProfileAlertDlg createDeleteProfileDialog(final NetflixActivity netflixActivity, final String s, final String s2) {
        final DeleteProfileAlertDlg deleteProfileAlertDlg = new DeleteProfileAlertDlg();
        final Bundle arguments = new Bundle();
        arguments.putString("name", s2);
        arguments.putString("url", s);
        deleteProfileAlertDlg.setArguments(arguments);
        deleteProfileAlertDlg.setStyle(1, 2131558710);
        return deleteProfileAlertDlg;
    }
    
    private void updateUIIfPossible() {
        final ServiceManager serviceManager = ((NetflixActivity)this.getActivity()).getServiceManager();
        if (serviceManager != null && serviceManager.isReady()) {
            final String string = this.getArguments().getString("name");
            this.mProfileName.setText((CharSequence)string);
            NetflixActivity.getImageLoader((Context)this.getActivity()).showImg(this.mProfileAvatar, this.getArguments().getString("url"), IClientLogging.AssetType.profileAvatar, string, true, true);
            return;
        }
        Log.i("nf_deleteprofilealertdlg", "Profile manager is not ready when updateUIIfPossible() was called!");
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        final DialogInterface$OnClickListener dialogInterface$OnClickListener = (DialogInterface$OnClickListener)this.getActivity();
        if (dialogInterface$OnClickListener != null) {
            dialogInterface$OnClickListener.onClick(dialogInterface, -2);
        }
    }
    
    @SuppressLint({ "InflateParams" })
    public Dialog onCreateDialog(final Bundle bundle) {
        super.onCreate(bundle);
        final View inflate = this.getActivity().getLayoutInflater().inflate(2130903083, (ViewGroup)null);
        this.mProfileName = (TextView)inflate.findViewById(2131165339);
        this.mProfileAvatar = (AdvancedImageView)inflate.findViewById(2131165299);
        this.updateUIIfPossible();
        final DialogInterface$OnClickListener dialogInterface$OnClickListener = (DialogInterface$OnClickListener)this.getActivity();
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.getActivity());
        alertDialog$Builder.setNegativeButton(2131493355, dialogInterface$OnClickListener);
        alertDialog$Builder.setPositiveButton(2131493356, dialogInterface$OnClickListener);
        alertDialog$Builder.setView(inflate);
        final AlertDialog create = alertDialog$Builder.create();
        create.setCanceledOnTouchOutside(false);
        return (Dialog)create;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        super.onManagerReady(serviceManager, status);
        this.updateUIIfPossible();
    }
}
