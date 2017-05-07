// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.Log;
import android.content.Context;
import android.app.AlertDialog$Builder;
import android.app.Dialog;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class ShowMessageDialogFrag extends NetflixDialogFrag implements MdxMiniPlayerDialog
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
            alertDialog$Builder.setTitle((CharSequence)string);
        }
        else {
            Log.w("mdxui", "No title...");
        }
        if (string2 != null) {
            alertDialog$Builder.setMessage((CharSequence)string2);
        }
        else {
            Log.w("mdxui", "No message...");
        }
        if (int1 < 1) {
            Log.e("mdxui", "We are expecting at least one button!");
        }
        if (int1 > 0) {
            alertDialog$Builder.setPositiveButton((CharSequence)array[0], (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(DialogInterface dialogInterface, final int n) {
                    dialogInterface = (DialogInterface)ShowMessageDialogFrag.this.mClicked;
                    synchronized (dialogInterface) {
                        if (ShowMessageDialogFrag.this.mClicked.get()) {
                            Log.w("mdxui", "Already clicked!");
                            return;
                        }
                        ShowMessageDialogFrag.this.mClicked.set(true);
                        // monitorexit(dialogInterface)
                        ShowMessageDialogFrag.this.dismissAllowingStateLoss();
                        ShowMessageDialogFrag.this.getFragmentManager().beginTransaction().remove((Fragment)ShowMessageDialogFrag.this).commit();
                        dialogInterface = (DialogInterface)ShowMessageDialogFrag.this.getActivity();
                        if (dialogInterface instanceof MessageResponseProvider) {
                            ((MessageResponseProvider)dialogInterface).onResponse(array2[0]);
                        }
                    }
                }
            });
        }
        if (int1 > 1) {
            alertDialog$Builder.setNegativeButton((CharSequence)array[1], (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(DialogInterface dialogInterface, final int n) {
                    dialogInterface = (DialogInterface)ShowMessageDialogFrag.this.mClicked;
                    synchronized (dialogInterface) {
                        if (ShowMessageDialogFrag.this.mClicked.get()) {
                            Log.w("mdxui", "Already clicked!");
                            return;
                        }
                        ShowMessageDialogFrag.this.mClicked.set(true);
                        // monitorexit(dialogInterface)
                        ShowMessageDialogFrag.this.dismissAllowingStateLoss();
                        ShowMessageDialogFrag.this.getFragmentManager().beginTransaction().remove((Fragment)ShowMessageDialogFrag.this).commit();
                        dialogInterface = (DialogInterface)ShowMessageDialogFrag.this.getActivity();
                        if (dialogInterface instanceof MessageResponseProvider) {
                            ((MessageResponseProvider)dialogInterface).onResponse(array2[1]);
                        }
                    }
                }
            });
        }
        if (int1 > 2) {
            alertDialog$Builder.setNegativeButton((CharSequence)array[2], (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(DialogInterface dialogInterface, final int n) {
                    dialogInterface = (DialogInterface)ShowMessageDialogFrag.this.mClicked;
                    synchronized (dialogInterface) {
                        if (ShowMessageDialogFrag.this.mClicked.get()) {
                            Log.w("mdxui", "Already clicked!");
                            return;
                        }
                        ShowMessageDialogFrag.this.mClicked.set(true);
                        // monitorexit(dialogInterface)
                        ShowMessageDialogFrag.this.dismissAllowingStateLoss();
                        ShowMessageDialogFrag.this.getFragmentManager().beginTransaction().remove((Fragment)ShowMessageDialogFrag.this).commit();
                        dialogInterface = (DialogInterface)ShowMessageDialogFrag.this.getActivity();
                        if (dialogInterface instanceof MessageResponseProvider) {
                            ((MessageResponseProvider)dialogInterface).onResponse(array2[2]);
                        }
                    }
                }
            });
        }
        if (int1 > 3) {
            Log.e("mdxui", "We can support up to 3 buttons!");
        }
        return (Dialog)alertDialog$Builder.create();
    }
    
    public interface MessageResponseProvider
    {
        void onResponse(final String p0);
    }
}
