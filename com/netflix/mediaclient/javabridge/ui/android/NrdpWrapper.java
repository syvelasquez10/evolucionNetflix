// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.android;

import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.javabridge.NrdProxy;
import com.netflix.mediaclient.javabridge.ui.Storage;
import com.netflix.mediaclient.javabridge.ui.Registration;
import com.netflix.mediaclient.javabridge.ui.NetworkDiagnosis;
import com.netflix.mediaclient.javabridge.ui.IMedia;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController;
import com.netflix.mediaclient.javabridge.ui.Log;
import com.netflix.mediaclient.javabridge.ui.Device;
import com.netflix.mediaclient.javabridge.ui.Nrdp;

public final class NrdpWrapper implements Nrdp
{
    private Device deviceImpl;
    private Log logImpl;
    private MdxController mdxControllerImpl;
    private IMedia mediaImpl;
    private NetworkDiagnosis networkDiagnosticTool;
    private Nrdp nrdpImpl;
    private Registration regImpl;
    private Storage storageImpl;
    
    public NrdpWrapper(final NrdProxy nrdProxy) {
        this.nrdpImpl = (Nrdp)nrdProxy.findObjectCache("nrdp");
        this.mediaImpl = (IMedia)nrdProxy.findObjectCache("nrdp.media");
        this.storageImpl = (Storage)nrdProxy.findObjectCache("nrdp.storage");
        this.logImpl = (Log)nrdProxy.findObjectCache("nrdp.log");
        this.regImpl = (Registration)nrdProxy.findObjectCache("nrdp.registration");
        this.deviceImpl = (Device)nrdProxy.findObjectCache("nrdp.device");
        this.mdxControllerImpl = (MdxController)nrdProxy.findObjectCache("nrdp.mdx");
        this.networkDiagnosticTool = (NetworkDiagnosis)nrdProxy.findObjectCache("nrdp.network");
    }
    
    @Override
    public void addEventListener(final String s, final EventListener eventListener) {
        this.nrdpImpl.addEventListener(s, eventListener);
    }
    
    @Override
    public boolean debug() {
        return this.nrdpImpl.debug();
    }
    
    @Override
    public void exit() {
        this.nrdpImpl.exit();
    }
    
    @Override
    public void getConfigList() {
        this.nrdpImpl.getConfigList();
    }
    
    @Override
    public Device getDevice() {
        return this.deviceImpl;
    }
    
    @Override
    public NetworkDiagnosis getDiagnosisTool() {
        return this.networkDiagnosticTool;
    }
    
    @Override
    public Log getLog() {
        return this.logImpl;
    }
    
    @Override
    public MdxController getMdxController() {
        return this.mdxControllerImpl;
    }
    
    @Override
    public IMedia getMedia() {
        return this.mediaImpl;
    }
    
    @Override
    public Registration getRegistration() {
        return this.regImpl;
    }
    
    @Override
    public Storage getStorage() {
        return this.storageImpl;
    }
    
    @Override
    public boolean isReady() {
        return this.nrdpImpl.isReady();
    }
    
    @Override
    public long now() {
        return this.nrdpImpl.now();
    }
    
    @Override
    public void removeEventListener(final String s, final EventListener eventListener) {
        this.nrdpImpl.removeEventListener(s, eventListener);
    }
    
    @Override
    public void setConfigData(final String s, final String s2) {
        this.nrdpImpl.setConfigData(s, s2);
    }
}
