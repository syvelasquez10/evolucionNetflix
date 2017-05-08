// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.device.yearclass;

import java.io.File;
import java.io.FileFilter;

final class DeviceInfo$1 implements FileFilter
{
    @Override
    public boolean accept(final File file) {
        final String name = file.getName();
        if (name.startsWith("cpu")) {
            for (int i = 3; i < name.length(); ++i) {
                if (!Character.isDigit(name.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
