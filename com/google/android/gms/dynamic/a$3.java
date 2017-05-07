// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.app.Activity;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View$OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.LinearLayout;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.widget.FrameLayout;
import java.util.LinkedList;
import android.os.Bundle;

class a$3 implements a$a
{
    final /* synthetic */ a RT;
    final /* synthetic */ Bundle RW;
    
    a$3(final a rt, final Bundle rw) {
        this.RT = rt;
        this.RW = rw;
    }
    
    @Override
    public void b(final LifecycleDelegate lifecycleDelegate) {
        this.RT.RP.onCreate(this.RW);
    }
    
    @Override
    public int getState() {
        return 1;
    }
}
