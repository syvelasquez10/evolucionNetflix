// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.devsupport;

public class DebugServerException extends RuntimeException
{
    public DebugServerException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public static DebugServerException makeGeneric(final String s, final String s2, final Throwable t) {
        return new DebugServerException(s + "\n\nTry the following to fix the issue:\n\u2022 Ensure that the packager server is running\n\u2022 Ensure that your device/emulator is connected to your machine and has USB debugging enabled - run 'adb devices' to see a list of connected devices\n\u2022 Ensure Airplane Mode is disabled\n\u2022 If you're on a physical device connected to the same machine, run 'adb reverse tcp:8081 tcp:8081' to forward requests from your device\n\u2022 If your device is on the same Wi-Fi network, set 'Debug server host & port for device' in 'Dev settings' to your machine's IP address and the port of the local dev server - e.g. 10.0.1.1:8081\n\n" + s2, t);
    }
    
    public static DebugServerException makeGeneric(final String s, final Throwable t) {
        return makeGeneric(s, "", t);
    }
}
