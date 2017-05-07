// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

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
import android.app.Activity;

class a$2 implements a$a
{
    final /* synthetic */ a RT;
    final /* synthetic */ Activity RU;
    final /* synthetic */ Bundle RV;
    final /* synthetic */ Bundle RW;
    
    a$2(final a rt, final Activity ru, final Bundle rv, final Bundle rw) {
        this.RT = rt;
        this.RU = ru;
        this.RV = rv;
        this.RW = rw;
    }
    
    @Override
    public void b(final LifecycleDelegate lifecycleDelegate) {
        this.RT.RP.onInflate(this.RU, this.RV, this.RW);
    }
    
    @Override
    public int getState() {
        return 0;
    }
}
