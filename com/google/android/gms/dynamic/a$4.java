// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.view.View$OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.LinearLayout;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.util.LinkedList;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.os.Bundle;

class a$4 implements a$a
{
    final /* synthetic */ a RT;
    final /* synthetic */ Bundle RW;
    final /* synthetic */ FrameLayout RX;
    final /* synthetic */ LayoutInflater RY;
    final /* synthetic */ ViewGroup RZ;
    
    a$4(final a rt, final FrameLayout rx, final LayoutInflater ry, final ViewGroup rz, final Bundle rw) {
        this.RT = rt;
        this.RX = rx;
        this.RY = ry;
        this.RZ = rz;
        this.RW = rw;
    }
    
    @Override
    public void b(final LifecycleDelegate lifecycleDelegate) {
        this.RX.removeAllViews();
        this.RX.addView(this.RT.RP.onCreateView(this.RY, this.RZ, this.RW));
    }
    
    @Override
    public int getState() {
        return 2;
    }
}
