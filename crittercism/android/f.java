// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import com.crittercism.app.CrittercismConfig;
import android.content.Context;

public final class f
{
    String a;
    int b;
    
    public f(final Context context, final CrittercismConfig crittercismConfig) {
        this.a = "1.0";
        this.b = 0;
        while (true) {
            try {
                final PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                this.a = packageInfo.versionName;
                this.b = packageInfo.versionCode;
                final String customVersionName = crittercismConfig.getCustomVersionName();
                if (customVersionName != null && customVersionName.length() > 0) {
                    this.a = customVersionName;
                }
                if (crittercismConfig.isVersionCodeToBeIncludedInVersionString()) {
                    this.a = this.a + "-" + Integer.toString(this.b);
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                continue;
            }
            break;
        }
    }
}
