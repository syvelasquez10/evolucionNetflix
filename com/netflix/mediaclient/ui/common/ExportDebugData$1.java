// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.view.View;
import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap;
import java.io.Closeable;
import com.netflix.mediaclient.util.IoUtil;
import java.io.FileWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import android.os.Process;
import android.content.pm.ResolveInfo;
import java.util.concurrent.TimeUnit;
import android.os.SystemClock;
import java.util.Locale;
import android.os.Build;
import android.os.Build$VERSION;
import android.content.Intent;
import android.net.Uri;
import java.io.FileOutputStream;
import java.util.Map;
import java.io.PrintStream;
import java.util.Properties;
import java.util.Iterator;
import java.io.FilenameFilter;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import com.netflix.mediaclient.Log;
import java.io.File;
import com.netflix.mediaclient.util.AndroidUtils;
import java.util.zip.ZipOutputStream;
import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.netflix.mediaclient.android.activity.NetflixActivity;

final class ExportDebugData$1 implements Runnable
{
    final /* synthetic */ NetflixActivity val$activity;
    final /* synthetic */ Toast val$toast;
    
    ExportDebugData$1(final Toast val$toast, final NetflixActivity val$activity) {
        this.val$toast = val$toast;
        this.val$activity = val$activity;
    }
    
    @Override
    public void run() {
        this.val$toast.cancel();
        Toast.makeText((Context)this.val$activity, (CharSequence)"Enter your JIRA summary and description in the email", 0).show();
        createBugReport(this.val$activity);
    }
}
