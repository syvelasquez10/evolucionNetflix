// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import android.system.StructStat;
import java.io.File;
import com.netflix.mediaclient.NetflixApplication;
import android.system.Os;
import android.os.Process;
import android.os.Build$VERSION;
import com.netflix.mediaclient.Log;
import android.os.Environment;
import android.content.Context;

public class StorageLogblobInfo
{
    private static final String TAG = "StorageLogblobInfo";
    
    public static String buildDeviceExternalStorageLogblobInfo(Context string) {
        final Object o = null;
        final File[] externalFilesDirs = string.getExternalFilesDirs(Environment.DIRECTORY_DOWNLOADS);
        Object o2 = o;
        if (externalFilesDirs != null) {
            final DeviceExternalStorageInfo deviceExternalStorageInfo = new DeviceExternalStorageInfo(externalFilesDirs.length);
        Label_0060:
            for (int i = 0; i < externalFilesDirs.length; ++i) {
                final File file = externalFilesDirs[i];
                if (file != null) {
                    while (true) {
                        Label_0325: {
                            if (file.exists()) {
                                break Label_0325;
                            }
                            Log.i("StorageLogblobInfo", "mkdirsResult=%b", file.mkdirs());
                            if (file.exists()) {
                                break Label_0325;
                            }
                            final boolean b = false;
                            final Object o3 = "";
                            boolean externalStorageRemovable = i != 0;
                            boolean externalStorageEmulated;
                            if (Build$VERSION.SDK_INT >= 21) {
                                externalStorageRemovable = Environment.isExternalStorageRemovable(file);
                                externalStorageEmulated = Environment.isExternalStorageEmulated(file);
                                if (b) {
                                    string = (Context)o3;
                                }
                                else {
                                    string = (Context)o3;
                                    try {
                                        final int myUid = Process.myUid();
                                        string = (Context)o3;
                                        final StructStat stat = Os.stat(file.getAbsolutePath());
                                        string = (Context)o3;
                                        final Object o4 = string = (Context)("appUid=" + myUid + " dirUid=" + stat.st_uid + " dirGid=" + stat.st_gid);
                                        Log.i("StorageLogblobInfo", "dbgInfo=%s", o4);
                                        string = (Context)o4;
                                    }
                                    catch (Exception ex) {}
                                }
                            }
                            else {
                                externalStorageEmulated = false;
                                string = (Context)o3;
                            }
                            deviceExternalStorageInfo.mExternalStorageInfoList.add(new ExternalStorageInfo(externalStorageRemovable, i == 0, b, externalStorageEmulated, (String)string));
                            continue Label_0060;
                        }
                        final boolean b = true;
                        continue;
                    }
                }
                Log.i("StorageLogblobInfo", "externalFilesDirs null, ignore");
            }
            o2 = deviceExternalStorageInfo;
        }
        if (o2 != null) {
            return NetflixApplication.getGson().toJson(o2);
        }
        return "";
    }
}
