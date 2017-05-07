// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.error;

public enum Signal
{
    SIGABRT(6, "SIGABRT"), 
    SIGALRM(14, "SIGALRM"), 
    SIGBUS(7, "SIGBUS"), 
    SIGCHLD(17, "SIGCHLD"), 
    SIGCONT(18, "SIGCONT"), 
    SIGFPE(8, "SIGFPE"), 
    SIGHUP(1, "SIGHUP"), 
    SIGILL(4, "SIGILL"), 
    SIGINT(2, "SIGINT"), 
    SIGIO(29, "SIGIO"), 
    SIGKILL(9, "SIGKILL"), 
    SIGPIPE(13, "SIGPIPE"), 
    SIGPROF(27, "SIGPROF"), 
    SIGPWR(30, "SIGPWR"), 
    SIGQUIT(3, "SIGQUIT"), 
    SIGRTMIN(32, "SIGRTMIN"), 
    SIGSEGV(11, "SIGSEGV"), 
    SIGSTKFLT(16, "SIGSTKFLT"), 
    SIGSTOP(19, "SIGSTOP"), 
    SIGSYS(31, "SIGSYS"), 
    SIGTERM(15, "SIGTERM"), 
    SIGTRAP(5, "SIGTRAP"), 
    SIGTSTP(20, "SIGTSTP"), 
    SIGTTIN(21, "SIGTTIN"), 
    SIGTTOU(22, "SIGTTOU"), 
    SIGURG(23, "SIGURG"), 
    SIGUSR1(10, "SIGUSR1"), 
    SIGUSR2(12, "SIGUSR2"), 
    SIGVTALRM(26, "SIGVTALRM"), 
    SIGWINCH(28, "SIGWINCH"), 
    SIGXCPU(24, "SIGXCPU"), 
    SIGXFSZ(25, "SIGXFSZ");
    
    protected String description;
    protected int value;
    
    private Signal(final int value, final String description) {
        this.value = value;
        this.description = description;
    }
    
    public static Signal toSignal(final int n) {
        for (int i = 0; i < values().length; ++i) {
            if (values()[i].value == n) {
                return values()[i];
            }
        }
        return null;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public int getNumber() {
        return this.value;
    }
}
