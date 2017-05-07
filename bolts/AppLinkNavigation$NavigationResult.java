// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

public enum AppLinkNavigation$NavigationResult
{
    APP("app", true), 
    FAILED("failed", false), 
    WEB("web", true);
    
    private String code;
    private boolean succeeded;
    
    private AppLinkNavigation$NavigationResult(final String code, final boolean succeeded) {
        this.code = code;
        this.succeeded = succeeded;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public boolean isSucceeded() {
        return this.succeeded;
    }
}
