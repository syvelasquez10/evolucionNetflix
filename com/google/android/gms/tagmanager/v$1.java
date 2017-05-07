// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.database.Cursor;
import java.util.Arrays;
import android.text.TextUtils;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;
import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase;
import java.util.concurrent.Executors;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ju;
import android.content.Context;
import java.util.concurrent.Executor;
import java.util.List;

class v$1 implements Runnable
{
    final /* synthetic */ List aoJ;
    final /* synthetic */ long aoK;
    final /* synthetic */ v aoL;
    
    v$1(final v aoL, final List aoJ, final long aoK) {
        this.aoL = aoL;
        this.aoJ = aoJ;
        this.aoK = aoK;
    }
    
    @Override
    public void run() {
        this.aoL.b(this.aoJ, this.aoK);
    }
}
