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
        return new AlertDialog$Builder(context).setTitle(2131296918).setMessage(context.getResources().getString(2131296917, new Object[] { s, s2 })).setPositiveButton(2131296861, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$14(dialogInterface$OnClickListener)).setNegativeButton(2131296564, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$13()).create();
    }
    
    static Dialog createDownloadFailedDialog(final Context context, final String s) {
        return new AlertDialog$Builder(context).setTitle(2131296886).setMessage(context.getResources().getString(2131296885, new Object[] { s })).setNegativeButton(2131296564, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$12()).create();
    }
    
    static PopupMenu createDownloadingMenu(final Context context, final DownloadButton downloadButton, final String s, final boolean b) {
        final boolean b2 = true;
        final PopupMenu popupMenu = new PopupMenu(context, (View)downloadButton);
        popupMenu.inflate(2131820544);
        popupMenu.getMenu().findItem(2131755984).setVisible(true);
        popupMenu.getMenu().findItem(2131755987).setVisible(true);
        popupMenu.getMenu().findItem(2131755988).setVisible(!isInMyDownloadsActivity(context) && b && b2);
        popupMenu.setOnMenuItemClickListener((PopupMenu$OnMenuItemClickListener)new DownloadButtonDialogHelper$2(context, s, downloadButton));
        return popupMenu;
    }
    
    public static Dialog createNoInternetDialog(final Context context, final String s, final boolean b) {
        final AlertDialog$Builder setPositiveButton = new AlertDialog$Builder(context).setTitle(2131296900).setMessage(2131296899).setPositiveButton(2131296724, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$7());
        if (b) {
            setPositiveButton.setNegativeButton(2131296860, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$8(context, s));
        }
        return setPositiveButton.create();
    }
    
    static Dialog createNoStorageDialog(final Context context, final boolean b) {
        final AlertDialog$Builder setTitle = new AlertDialog$Builder(context).setTitle(2131296903);
        if (b) {
            setTitle.setMessage(2131296901).setNegativeButton(2131296867, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$5(context)).setPositiveButton(2131296564, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$4());
        }
        else {
            setTitle.setMessage(2131296902).setPositiveButton(2131296724, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$6());
        }
        return setTitle.create();
    }
    
    static Dialog createNoWifiDialog(final Context context, final String s, final VideoType videoType, final boolean b) {
        final AlertDialog$Builder setPositiveButton = new AlertDialog$Builder(context).setTitle(2131296905).setMessage(2131296904).setNeutralButton(2131296724, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$10()).setPositiveButton(2131296864, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$9(context, s, videoType));
        if (b) {
            setPositiveButton.setNegativeButton(2131296860, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$11(context, s));
        }
        return setPositiveButton.create();
    }
    
    public static Dialog createNotAvailableDialog(final Context context) {
        return new AlertDialog$Builder(context).setTitle(2131296907).setMessage(2131296906).setPositiveButton(2131296724, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$16()).setNeutralButton(2131296875, (DialogInterface$OnClickListener)new DownloadButtonDialogHelper$15(context)).create();
    }
    
    static PopupMenu createPausedMenu(final Context context, final DownloadButton downloadButton, final String s, final boolean b, final boolean visible) {
        final boolean b2 = true;
        final PopupMenu popupMenu = new PopupMenu(context, (View)downloadButton);
        popupMenu.inflate(2131820544);
        popupMenu.getMenu().findItem(2131755986).setVisible(visible);
        popupMenu.getMenu().findItem(2131755987).setVisible(true);
        popupMenu.getMenu().findItem(2131755988).setVisible(b && !isInMyDownloadsActivity(context) && b2);
        popupMenu.setOnMenuItemClickListener((PopupMenu$OnMenuItemClickListener)new DownloadButtonDialogHelper$3(context, s));
        return popupMenu;
    }
    
    static PopupMenu createWatchDeleteDialog(final Context context, final DownloadButton downloadButton, final String s, final VideoType videoType, final boolean b, final PlayContext playContext) {
        final boolean b2 = true;
        final PopupMenu popupMenu = new PopupMenu(context, (View)downloadButton);
        popupMenu.inflate(2131820544);
        popupMenu.getMenu().findItem(2131755983).setVisible(true);
        popupMenu.getMenu().findItem(2131755985).setVisible(true);
        popupMenu.getMenu().findItem(2131755988).setVisible(b && !isInMyDownloadsActivity(context) && b2);
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
