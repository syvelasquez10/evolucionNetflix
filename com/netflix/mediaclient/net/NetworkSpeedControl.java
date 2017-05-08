// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.net;

import android.view.Window;
import android.view.View$OnClickListener;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import android.content.DialogInterface$OnClickListener;
import android.support.v7.app.AlertDialog$Builder;
import android.app.Activity;
import com.android.volley.toolbox.ControlledInputStream;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.os.Handler;

public class NetworkSpeedControl
{
    private static final double[] gNetworkHistorySeconds;
    private static final double[] gNetworkSpeedHistory;
    private static final int gNumberOfHistorySeconds = 60;
    private final Handler mMainHandler;
    private final NetflixActivity mNetflixActivity;
    
    static {
        gNetworkSpeedHistory = new double[60];
        gNetworkHistorySeconds = new double[60];
    }
    
    public NetworkSpeedControl(final NetflixActivity mNetflixActivity) {
        this.mNetflixActivity = mNetflixActivity;
        this.mMainHandler = new Handler();
    }
    
    public static void applySelectedSpeed(final Context context) {
        ControlledInputStream.setNetworkSpeedInBitsPerSecond(NetworkSpeedType.getByKeyValue(PreferenceUtils.getIntPref(context, "debug_network_speed_Key", NetworkSpeedType.NoLimit.getKeyValue())).getSpeedInBitsPerSecond());
    }
    
    private static int getSelectedSpeedIndex(final Context context) {
        return NetworkSpeedType.getByKeyValue(PreferenceUtils.getIntPref(context, "debug_network_speed_Key", NetworkSpeedType.NoLimit.getKeyValue())).getKeyValue();
    }
    
    private static String[] getStringsForDebugMenu() {
        final NetworkSpeedType[] values = NetworkSpeedType.values();
        final String[] array = new String[values.length];
        for (int i = 0; i < values.length; ++i) {
            array[i] = values[i].getStringForDebugMenu();
        }
        return array;
    }
    
    private void killChart(final AlertDialog alertDialog) {
        this.mMainHandler.removeCallbacksAndMessages((Object)null);
        alertDialog.dismiss();
    }
    
    private static void leftShitByOne(final double[] array) {
        for (int i = 0; i < array.length - 1; ++i) {
            array[i] = array[i + 1];
        }
    }
    
    private static void setSelectedSpeedIndex(final int n, final Context context) {
        PreferenceUtils.putIntPref(context, "debug_network_speed_Key", n);
    }
    
    private void showChartAfter5Seconds() {
        this.mMainHandler.postDelayed((Runnable)new NetworkSpeedControl$3(this), 5000L);
    }
    
    private static void showErrorDialog(final Activity activity) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)activity);
        alertDialog$Builder.setPositiveButton("OK", (DialogInterface$OnClickListener)new NetworkSpeedControl$4());
        alertDialog$Builder.setMessage("You must first control the network speed using the Network Speed Controller.");
        alertDialog$Builder.create().show();
    }
    
    public static void showNetworkSpeedController(final NetflixActivity netflixActivity) {
        final String[] stringsForDebugMenu = getStringsForDebugMenu();
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)netflixActivity);
        alertDialog$Builder.setTitle(2131297132);
        alertDialog$Builder.setSingleChoiceItems(stringsForDebugMenu, getSelectedSpeedIndex((Context)netflixActivity), (DialogInterface$OnClickListener)new NetworkSpeedControl$5(netflixActivity));
        alertDialog$Builder.create().show();
    }
    
    public void showSpeedChart() {
        final long targetNetworkSpeedBytesPerSecond = ControlledInputStream.getTargetNetworkSpeedBytesPerSecond();
        if (targetNetworkSpeedBytesPerSecond == -1L) {
            showErrorDialog(this.mNetflixActivity);
            return;
        }
        for (int i = 0; i < NetworkSpeedControl.gNetworkHistorySeconds.length; ++i) {
            NetworkSpeedControl.gNetworkHistorySeconds[i] = i;
        }
        final AlertDialog create = new AlertDialog$Builder((Context)this.mNetflixActivity).create();
        final Window window = create.getWindow();
        if (window != null) {
            window.setLayout(-1, -1);
            window.setFormat(-2);
            window.setBackgroundDrawable((Drawable)new ColorDrawable(Color.argb(0, 200, 200, 200)));
        }
        final NetworkSpeedChart view = new NetworkSpeedChart((Context)this.mNetflixActivity);
        view.setXAxisLabel("Time");
        view.setYAxisLabel("Controlled Speed");
        view.setMinValueX(0.0);
        view.setMinValueY(0.0);
        view.setMaxValueX(59.0);
        view.setMaxValueY(2L * targetNetworkSpeedBytesPerSecond);
        final double[] array = new double[3];
        final String[] array2 = new String[3];
        for (int j = 0; j < array.length; ++j) {
            array[j] = (new int[] { 50, 100, 150 })[j] * targetNetworkSpeedBytesPerSecond / 100L;
            final double n = array[j] * 8.0 / 1024.0;
            if (n < 1024.0) {
                array2[j] = n + "kbps";
            }
            else {
                array2[j] = n / 1024.0 + "mbps";
            }
        }
        view.addHorizontalLinesWithLabel(array, array2);
        view.updateData(NetworkSpeedControl.gNetworkHistorySeconds, NetworkSpeedControl.gNetworkSpeedHistory);
        this.mMainHandler.postDelayed((Runnable)new NetworkSpeedControl$1(this, view), 1000L);
        create.setView(view);
        create.show();
        view.setVisibility(0);
        view.setOnClickListener((View$OnClickListener)new NetworkSpeedControl$2(this, create));
    }
}
