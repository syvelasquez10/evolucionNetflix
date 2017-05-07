// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Parcelable;
import android.os.Bundle;
import android.content.DialogInterface;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import android.util.Log;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.common.internal.zzx;
import android.support.v4.app.FragmentActivity;
import android.os.Looper;
import android.util.SparseArray;
import android.os.Handler;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import android.content.DialogInterface$OnCancelListener;
import android.support.v4.app.Fragment;
import android.app.Dialog;

class zzlp$zzb$1 extends zzll
{
    final /* synthetic */ Dialog zzacW;
    final /* synthetic */ zzlp$zzb zzacX;
    
    zzlp$zzb$1(final zzlp$zzb zzacX, final Dialog zzacW) {
        this.zzacX = zzacX;
        this.zzacW = zzacW;
    }
    
    @Override
    protected void zzoi() {
        this.zzacX.zzacT.zzok();
        this.zzacW.dismiss();
    }
}
