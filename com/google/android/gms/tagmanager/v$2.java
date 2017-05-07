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

class v$2 implements Runnable
{
    final /* synthetic */ v aoL;
    final /* synthetic */ DataLayer$c$a aoM;
    
    v$2(final v aoL, final DataLayer$c$a aoM) {
        this.aoL = aoL;
        this.aoM = aoM;
    }
    
    @Override
    public void run() {
        this.aoM.g(this.aoL.og());
    }
}
