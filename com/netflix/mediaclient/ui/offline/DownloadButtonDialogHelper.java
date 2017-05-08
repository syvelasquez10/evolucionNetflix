// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.widget.PopupMenu$OnMenuItemClickListener;
import android.view.View;
import android.widget.PopupMenu;
import android.support.v7.app.AlertDialog$Builder;
import android.app.Dialog;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import android.content.Context;

public class DownloadButtonDialogHelper
{
    public static Dialog createDownloadDeleteAllDialog(final Context context, final DialogInterface$OnClickListener dialogInterface$OnClickListener, final String s, final String s2) {
        return new AlertDialog$Builder(context).setTitle(2131231357).setMessage(context.getResources().getString(2131231356, new Object[] { s, s2 })).setPositiveButton(2131231305, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$15(dialogInterface$OnClickListener)).setNegativeButton(2131231008, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$14()).create();
    }
    
    static Dialog createDownloadFailedDialog(final Context context, final String s) {
        return new AlertDialog$Builder(context).setTitle(2131231330).setMessage(context.getResources().getString(2131231329, new Object[] { s })).setNegativeButton(2131231008, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$13()).setPositiveButton(2131231144, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$12()).create();
    }
    
    static PopupMenu createDownloadingMenu(final Context context, final DownloadButton downloadButton, final String s, final boolean b) {
        final boolean b2 = true;
        final PopupMenu popupMenu = new PopupMenu(context, (View)downloadButton);
        popupMenu.inflate(2131755008);
        popupMenu.getMenu().findItem(2131690414).setVisible(true);
        popupMenu.getMenu().findItem(2131690417).setVisible(true);
        popupMenu.getMenu().findItem(2131690418).setVisible(!isInMyDownloadsActivity(context) && b && b2);
        popupMenu.setOnMenuItemClickListener((PopupMenu$OnMenuItemClickListener)new DownloadButtonDialogHelper$2(context, s, downloadButton));
        return popupMenu;
    }
    
    public static Dialog createNoInternetDialog(final Context context, final String s, final boolean b) {
        final AlertDialog$Builder setPositiveButton = new AlertDialog$Builder(context).setTitle(2131231341).setMessage(2131231340).setPositiveButton(2131231168, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$7());
        if (b) {
            setPositiveButton.setNegativeButton(2131231304, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$8(context, s));
        }
        return setPositiveButton.create();
    }
    
    static Dialog createNoStorageDialog(final Context context, final boolean b) {
        final AlertDialog$Builder setTitle = new AlertDialog$Builder(context).setTitle(2131231344);
        if (b) {
            setTitle.setMessage(2131231342).setNegativeButton(2131231311, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$5(context)).setPositiveButton(2131231008, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$4());
        }
        else {
            setTitle.setMessage(2131231343).setPositiveButton(2131231168, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$6());
        }
        return setTitle.create();
    }
    
    static Dialog createNoWifiDialog(final Context context, final String s, final VideoType videoType, final boolean b) {
        final AlertDialog$Builder setPositiveButton = new AlertDialog$Builder(context).setTitle(2131231346).setMessage(2131231345).setNeutralButton(2131231168, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$10()).setPositiveButton(2131231308, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$9(context, s, videoType));
        if (b) {
            setPositiveButton.setNegativeButton(2131231304, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$11(context, s));
        }
        return setPositiveButton.create();
    }
    
    public static Dialog createNotAvailableDialog(final Context context) {
        return new AlertDialog$Builder(context).setTitle(2131231348).setMessage(2131231347).setPositiveButton(2131231168, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$17()).setNeutralButton(2131231319, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$16(context)).create();
    }
    
    static PopupMenu createPausedMenu(final Context context, final DownloadButton downloadButton, final String s, final boolean b, final boolean visible) {
        final boolean b2 = true;
        final PopupMenu popupMenu = new PopupMenu(context, (View)downloadButton);
        popupMenu.inflate(2131755008);
        popupMenu.getMenu().findItem(2131690416).setVisible(visible);
        popupMenu.getMenu().findItem(2131690417).setVisible(true);
        popupMenu.getMenu().findItem(2131690418).setVisible(b && !isInMyDownloadsActivity(context) && b2);
        popupMenu.setOnMenuItemClickListener((PopupMenu$OnMenuItemClickListener)new DownloadButtonDialogHelper$3(context, s));
        return popupMenu;
    }
    
    static PopupMenu createWatchDeleteDialog(final Context context, final DownloadButton downloadButton, final String s, final VideoType videoType, final boolean b, final PlayContext playContext) {
        final boolean b2 = true;
        final PopupMenu popupMenu = new PopupMenu(context, (View)downloadButton);
        popupMenu.inflate(2131755008);
        popupMenu.getMenu().findItem(2131690413).setVisible(true);
        popupMenu.getMenu().findItem(2131690415).setVisible(true);
        popupMenu.getMenu().findItem(2131690418).setVisible(b && !isInMyDownloadsActivity(context) && b2);
        popupMenu.setOnMenuItemClickListener((PopupMenu$OnMenuItemClickListener)new DownloadButtonDialogHelper$1(context, s, videoType, playContext));
        return popupMenu;
    }
    
    private static OfflineAgentInterface getOfflineAgent(final Context context) {
        if (context != null && context instanceof NetflixActivity) {
            final ServiceManager serviceManager = ServiceManager.getServiceManager((NetflixActivity)context);
            if (serviceManager != null) {
                return serviceManager.getOfflineAgent();
            }
        }
        return null;
    }
    
    private static boolean isInMyDownloadsActivity(final Context context) {
        return AndroidUtils.getContextAs(context, OfflineActivity.class) != null;
    }
}
