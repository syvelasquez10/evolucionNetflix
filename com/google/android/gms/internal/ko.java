// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.request.z;
import com.google.android.gms.fitness.request.x;
import com.google.android.gms.fitness.request.v;
import com.google.android.gms.fitness.request.t;
import com.google.android.gms.fitness.request.p;
import com.google.android.gms.fitness.request.n;
import com.google.android.gms.fitness.request.l;
import com.google.android.gms.fitness.request.i;
import com.google.android.gms.fitness.request.b;
import com.google.android.gms.fitness.request.ah;
import com.google.android.gms.fitness.request.ae;
import com.google.android.gms.fitness.request.ac;
import com.google.android.gms.fitness.request.UnclaimBleDeviceRequest;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataInsertRequest;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import android.os.IInterface;

public interface ko extends IInterface
{
    void a(final DataDeleteRequest p0, final ks p1, final String p2);
    
    void a(final DataInsertRequest p0, final ks p1, final String p2);
    
    void a(final DataReadRequest p0, final kl p1, final String p2);
    
    void a(final DataSourcesRequest p0, final km p1, final String p2);
    
    void a(final DataTypeCreateRequest p0, final kn p1, final String p2);
    
    void a(final SessionInsertRequest p0, final ks p1, final String p2);
    
    void a(final SessionReadRequest p0, final kq p1, final String p2);
    
    void a(final StartBleScanRequest p0, final ks p1, final String p2);
    
    void a(final UnclaimBleDeviceRequest p0, final ks p1, final String p2);
    
    void a(final ac p0, final ks p1, final String p2);
    
    void a(final ae p0, final ks p1, final String p2);
    
    void a(final ah p0, final ks p1, final String p2);
    
    void a(final b p0, final ks p1, final String p2);
    
    void a(final i p0, final kn p1, final String p2);
    
    void a(final l p0, final kp p1, final String p2);
    
    void a(final n p0, final ks p1, final String p2);
    
    void a(final p p0, final ks p1, final String p2);
    
    void a(final t p0, final ks p1, final String p2);
    
    void a(final v p0, final ks p1, final String p2);
    
    void a(final x p0, final kr p1, final String p2);
    
    void a(final z p0, final ks p1, final String p2);
    
    void a(final ks p0, final String p1);
    
    void a(final le p0, final String p1);
    
    void b(final ks p0, final String p1);
}
