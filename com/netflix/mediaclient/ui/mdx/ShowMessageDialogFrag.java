// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.Log;
import android.content.Context;
import android.support.v7.app.AlertDialog$Builder;
import android.app.Dialog;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class ShowMessageDialogFrag extends NetflixDialogFrag implements MdxMiniPlayerFrag$MdxMiniPlayerDialog
{
    private static final String BUTTON_CODE = "buttonCode";
    private static final String BUTTON_COUNT = "buttonCount";
    private static final String BUTTON_NAME = "buttonName";
    private static final String MESSAGE = "message";
    private static final String TAG = "mdxui";
    private static final String TITLE = "title";
    private final AtomicBoolean mClicked;
    
    public ShowMessageDialogFrag() {
        this.mClicked = new AtomicBoolean(false);
    }
    
    public static ShowMessageDialogFrag newInstance(final RemoteDialog remoteDialog) {
        final ShowMessageDialogFrag showMessageDialogFrag = new ShowMessageDialogFrag();
        final Bundle arguments = new Bundle();
        if (remoteDialog.getTitle() != null) {
            arguments.putString("title", remoteDialog.getTitle());
        }
        if (remoteDialog.getMessage() != null) {
            arguments.putString("message", remoteDialog.getMessage());
        }
        arguments.putInt("buttonCount", remoteDialog.getOptions().length);
        for (int i = 0; i < remoteDialog.getOptions().length; ++i) {
            arguments.putString("buttonName" + i, (String)remoteDialog.getOptions()[i].first);
            arguments.putString("buttonCode" + i, (String)remoteDialog.getOptions()[i].second);
        }
        showMessageDialogFrag.setArguments(arguments);
        return showMessageDialogFrag;
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        final String string = this.getArguments().getString("title");
        final String string2 = this.getArguments().getString("message");
        final int int1 = this.getArguments().getInt("buttonCount", 0);
        final String[] array = new String[int1];
        final String[] array2 = new String[int1];
        for (int i = 0; i < int1; ++i) {
            array[i] = this.getArguments().getString("buttonName" + i);
            array2[i] = this.getArguments().getString("buttonCode" + i);
        }
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.getActivity());
        if (string != null) {
            alertDialog$Builder.setTitle(string);
        }
        else {
            Log.w("mdxui", "No title...");
        }
        if (string2 != null) {
            alertDialog$Builder.setMessage(string2);
        }
        else {
            Log.w("mdxui", "No message...");
        }
        if (int1 < 1) {
            Log.e("mdxui", "We are expecting at least one button!");
        }
        if (int1 > 0) {
            alertDialog$Builder.setPositiveButton(array[0], (DialogInterface$OnClickListener)new ShowMessageDialogFrag$1(this, array2));
        }
        if (int1 > 1) {
            alertDialog$Builder.setNegativeButton(array[1], (DialogInterface$OnClickListener)new ShowMessageDialogFrag$2(this, array2));
        }
        if (int1 > 2) {
            alertDialog$Builder.setNegativeButton(array[2], (DialogInterface$OnClickListener)new ShowMessageDialogFrag$3(this, array2));
        }
        if (int1 > 3) {
            Log.e("mdxui", "We can support up to 3 buttons!");
        }
        return alertDialog$Builder.create();
    }
}
