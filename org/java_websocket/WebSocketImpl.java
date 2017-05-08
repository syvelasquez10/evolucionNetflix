// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket;

import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import java.net.InetSocketAddress;
import java.io.PrintStream;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import java.util.Collection;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.exceptions.IncompleteHandshakeException;
import org.java_websocket.drafts.Draft$HandshakeState;
import java.util.Iterator;
import org.java_websocket.util.Charsetfunctions;
import org.java_websocket.framing.CloseFrame;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.CloseFrameBuilder;
import org.java_websocket.drafts.Draft$CloseHandshakeType;
import java.util.concurrent.LinkedBlockingQueue;
import org.java_websocket.drafts.Draft_75;
import org.java_websocket.drafts.Draft_76;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.drafts.Draft_17;
import java.util.ArrayList;
import java.nio.channels.SelectionKey;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.framing.Framedata$Opcode;
import java.nio.channels.ByteChannel;
import org.java_websocket.drafts.Draft;
import java.util.List;

public class WebSocketImpl implements WebSocket
{
    static final /* synthetic */ boolean $assertionsDisabled;
    public static boolean DEBUG;
    public static int RCVBUF;
    public static final List<Draft> defaultdraftlist;
    public ByteChannel channel;
    private Integer closecode;
    private Boolean closedremotely;
    private String closemessage;
    private Framedata$Opcode current_continuous_frame_opcode;
    private Draft draft;
    private volatile boolean flushandclosestate;
    private ClientHandshake handshakerequest;
    public final BlockingQueue<ByteBuffer> inQueue;
    public SelectionKey key;
    private List<Draft> knownDrafts;
    public final BlockingQueue<ByteBuffer> outQueue;
    private WebSocket$READYSTATE readystate;
    private WebSocket$Role role;
    private ByteBuffer tmpHandshakeBytes;
    private final WebSocketListener wsl;
    
    static {
        WebSocketImpl.RCVBUF = 16384;
        WebSocketImpl.DEBUG = false;
        (defaultdraftlist = new ArrayList<Draft>(4)).add(new Draft_17());
        WebSocketImpl.defaultdraftlist.add(new Draft_10());
        WebSocketImpl.defaultdraftlist.add(new Draft_76());
        WebSocketImpl.defaultdraftlist.add(new Draft_75());
    }
    
    public WebSocketImpl(final WebSocketListener wsl, final Draft draft) {
        this.flushandclosestate = false;
        this.readystate = WebSocket$READYSTATE.NOT_YET_CONNECTED;
        this.draft = null;
        this.current_continuous_frame_opcode = null;
        this.handshakerequest = null;
        this.closemessage = null;
        this.closecode = null;
        this.closedremotely = null;
        if (wsl == null || (draft == null && this.role == WebSocket$Role.SERVER)) {
            throw new IllegalArgumentException("parameters must not be null");
        }
        this.outQueue = new LinkedBlockingQueue<ByteBuffer>();
        this.inQueue = new LinkedBlockingQueue<ByteBuffer>();
        this.wsl = wsl;
        this.role = WebSocket$Role.CLIENT;
        if (draft != null) {
            this.draft = draft.copyInstance();
        }
    }
    
    private void close(final int n, final String s, final boolean b) {
        if (this.readystate != WebSocket$READYSTATE.CLOSING && this.readystate != WebSocket$READYSTATE.CLOSED) {
            while (true) {
                Label_0190: {
                    if (this.readystate != WebSocket$READYSTATE.OPEN) {
                        break Label_0190;
                    }
                    while (true) {
                        Label_0099: {
                            if (n == 1006) {
                                assert !b;
                                this.readystate = WebSocket$READYSTATE.CLOSING;
                                this.flushAndClose(n, s, false);
                                return;
                            }
                            else {
                                if (this.draft.getCloseHandshakeType() == Draft$CloseHandshakeType.NONE) {
                                    break Label_0112;
                                }
                                if (b) {
                                    break Label_0099;
                                }
                            }
                            try {
                                try {
                                    this.wsl.onWebsocketCloseInitiated(this, n, s);
                                    this.sendFrame(new CloseFrameBuilder(n, s));
                                    this.flushAndClose(n, s, b);
                                    if (n == 1002) {
                                        this.flushAndClose(n, s, b);
                                    }
                                    this.readystate = WebSocket$READYSTATE.CLOSING;
                                    this.tmpHandshakeBytes = null;
                                    return;
                                }
                                catch (RuntimeException ex) {
                                    this.wsl.onWebsocketError(this, ex);
                                }
                            }
                            catch (InvalidDataException ex2) {
                                this.wsl.onWebsocketError(this, ex2);
                                this.flushAndClose(1006, "generated frame is invalid", false);
                                continue;
                            }
                        }
                        break;
                    }
                }
                if (n != -3) {
                    this.flushAndClose(-1, s, false);
                    continue;
                }
                assert b;
                this.flushAndClose(-3, s, true);
                continue;
            }
        }
    }
    
    private void decodeFrames(final ByteBuffer byteBuffer) {
        if (!this.flushandclosestate) {
        Label_0131_Outer:
            while (true) {
                while (true) {
                    Label_0506: {
                        while (true) {
                            Framedata$Opcode opcode = null;
                            boolean fin = false;
                            Label_0202: {
                                int closeCode = 0;
                                String message = null;
                                Label_0169: {
                                    try {
                                        for (final Framedata framedata : this.draft.translateFrame(byteBuffer)) {
                                            if (WebSocketImpl.DEBUG) {
                                                System.out.println("matched frame: " + framedata);
                                            }
                                            if (this.flushandclosestate) {
                                                break;
                                            }
                                            opcode = framedata.getOpcode();
                                            fin = framedata.isFin();
                                            if (opcode != Framedata$Opcode.CLOSING) {
                                                break Label_0202;
                                            }
                                            if (!(framedata instanceof CloseFrame)) {
                                                break Label_0506;
                                            }
                                            final CloseFrame closeFrame = (CloseFrame)framedata;
                                            closeCode = closeFrame.getCloseCode();
                                            message = closeFrame.getMessage();
                                            if (this.readystate != WebSocket$READYSTATE.CLOSING) {
                                                break Label_0169;
                                            }
                                            this.closeConnection(closeCode, message, true);
                                        }
                                        return;
                                    }
                                    catch (InvalidDataException ex) {
                                        this.wsl.onWebsocketError(this, ex);
                                        this.close(ex);
                                        return;
                                    }
                                }
                                if (this.draft.getCloseHandshakeType() == Draft$CloseHandshakeType.TWOWAY) {
                                    this.close(closeCode, message, true);
                                    continue Label_0131_Outer;
                                }
                                this.flushAndClose(closeCode, message, false);
                                continue Label_0131_Outer;
                            }
                            final Framedata framedata;
                            if (opcode == Framedata$Opcode.PING) {
                                this.wsl.onWebsocketPing(this, framedata);
                                continue Label_0131_Outer;
                            }
                            if (opcode == Framedata$Opcode.PONG) {
                                this.wsl.onWebsocketPong(this, framedata);
                                continue Label_0131_Outer;
                            }
                            if (!fin || opcode == Framedata$Opcode.CONTINUOUS) {
                                if (opcode != Framedata$Opcode.CONTINUOUS) {
                                    if (this.current_continuous_frame_opcode != null) {
                                        break;
                                    }
                                    this.current_continuous_frame_opcode = opcode;
                                }
                                else if (fin) {
                                    if (this.current_continuous_frame_opcode == null) {
                                        throw new InvalidDataException(1002, "Continuous frame sequence was not started.");
                                    }
                                    this.current_continuous_frame_opcode = null;
                                }
                                else if (this.current_continuous_frame_opcode == null) {
                                    throw new InvalidDataException(1002, "Continuous frame sequence was not started.");
                                }
                                try {
                                    this.wsl.onWebsocketMessageFragment(this, framedata);
                                    continue Label_0131_Outer;
                                }
                                catch (RuntimeException ex2) {
                                    this.wsl.onWebsocketError(this, ex2);
                                    continue Label_0131_Outer;
                                }
                                continue Label_0131_Outer;
                            }
                            if (this.current_continuous_frame_opcode != null) {
                                throw new InvalidDataException(1002, "Continuous frame sequence not completed.");
                            }
                            if (opcode == Framedata$Opcode.TEXT) {
                                try {
                                    this.wsl.onWebsocketMessage(this, Charsetfunctions.stringUtf8(framedata.getPayloadData()));
                                    continue Label_0131_Outer;
                                }
                                catch (RuntimeException ex3) {
                                    this.wsl.onWebsocketError(this, ex3);
                                    continue Label_0131_Outer;
                                }
                                continue Label_0131_Outer;
                            }
                            if (opcode == Framedata$Opcode.BINARY) {
                                try {
                                    this.wsl.onWebsocketMessage(this, framedata.getPayloadData());
                                    continue Label_0131_Outer;
                                }
                                catch (RuntimeException ex4) {
                                    this.wsl.onWebsocketError(this, ex4);
                                    continue Label_0131_Outer;
                                }
                                continue Label_0131_Outer;
                            }
                            break;
                        }
                        throw new InvalidDataException(1002, "non control or continious frame expected");
                    }
                    String message = "";
                    int closeCode = 1005;
                    continue;
                }
            }
            throw new InvalidDataException(1002, "Previous continuous frame sequence not completed.");
        }
    }
    
    private boolean decodeHandshake(final ByteBuffer p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        org/java_websocket/WebSocketImpl.tmpHandshakeBytes:Ljava/nio/ByteBuffer;
        //     4: ifnonnull       66
        //     7: aload_1        
        //     8: astore          4
        //    10: aload           4
        //    12: invokevirtual   java/nio/ByteBuffer.mark:()Ljava/nio/Buffer;
        //    15: pop            
        //    16: aload_0        
        //    17: getfield        org/java_websocket/WebSocketImpl.draft:Lorg/java_websocket/drafts/Draft;
        //    20: ifnonnull       147
        //    23: aload_0        
        //    24: aload           4
        //    26: invokespecial   org/java_websocket/WebSocketImpl.isFlashEdgeCase:(Ljava/nio/ByteBuffer;)Lorg/java_websocket/drafts/Draft$HandshakeState;
        //    29: getstatic       org/java_websocket/drafts/Draft$HandshakeState.MATCHED:Lorg/java_websocket/drafts/Draft$HandshakeState;
        //    32: if_acmpne       147
        //    35: aload_0        
        //    36: aload_0        
        //    37: getfield        org/java_websocket/WebSocketImpl.wsl:Lorg/java_websocket/WebSocketListener;
        //    40: aload_0        
        //    41: invokeinterface org/java_websocket/WebSocketListener.getFlashPolicy:(Lorg/java_websocket/WebSocket;)Ljava/lang/String;
        //    46: invokestatic    org/java_websocket/util/Charsetfunctions.utf8Bytes:(Ljava/lang/String;)[B
        //    49: invokestatic    java/nio/ByteBuffer.wrap:([B)Ljava/nio/ByteBuffer;
        //    52: invokespecial   org/java_websocket/WebSocketImpl.write:(Ljava/nio/ByteBuffer;)V
        //    55: aload_0        
        //    56: bipush          -3
        //    58: ldc_w           ""
        //    61: invokevirtual   org/java_websocket/WebSocketImpl.close:(ILjava/lang/String;)V
        //    64: iconst_0       
        //    65: ireturn        
        //    66: aload_0        
        //    67: getfield        org/java_websocket/WebSocketImpl.tmpHandshakeBytes:Ljava/nio/ByteBuffer;
        //    70: invokevirtual   java/nio/ByteBuffer.remaining:()I
        //    73: aload_1        
        //    74: invokevirtual   java/nio/ByteBuffer.remaining:()I
        //    77: if_icmpge       121
        //    80: aload_0        
        //    81: getfield        org/java_websocket/WebSocketImpl.tmpHandshakeBytes:Ljava/nio/ByteBuffer;
        //    84: invokevirtual   java/nio/ByteBuffer.capacity:()I
        //    87: aload_1        
        //    88: invokevirtual   java/nio/ByteBuffer.remaining:()I
        //    91: iadd           
        //    92: invokestatic    java/nio/ByteBuffer.allocate:(I)Ljava/nio/ByteBuffer;
        //    95: astore          4
        //    97: aload_0        
        //    98: getfield        org/java_websocket/WebSocketImpl.tmpHandshakeBytes:Ljava/nio/ByteBuffer;
        //   101: invokevirtual   java/nio/ByteBuffer.flip:()Ljava/nio/Buffer;
        //   104: pop            
        //   105: aload           4
        //   107: aload_0        
        //   108: getfield        org/java_websocket/WebSocketImpl.tmpHandshakeBytes:Ljava/nio/ByteBuffer;
        //   111: invokevirtual   java/nio/ByteBuffer.put:(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;
        //   114: pop            
        //   115: aload_0        
        //   116: aload           4
        //   118: putfield        org/java_websocket/WebSocketImpl.tmpHandshakeBytes:Ljava/nio/ByteBuffer;
        //   121: aload_0        
        //   122: getfield        org/java_websocket/WebSocketImpl.tmpHandshakeBytes:Ljava/nio/ByteBuffer;
        //   125: aload_1        
        //   126: invokevirtual   java/nio/ByteBuffer.put:(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;
        //   129: pop            
        //   130: aload_0        
        //   131: getfield        org/java_websocket/WebSocketImpl.tmpHandshakeBytes:Ljava/nio/ByteBuffer;
        //   134: invokevirtual   java/nio/ByteBuffer.flip:()Ljava/nio/Buffer;
        //   137: pop            
        //   138: aload_0        
        //   139: getfield        org/java_websocket/WebSocketImpl.tmpHandshakeBytes:Ljava/nio/ByteBuffer;
        //   142: astore          4
        //   144: goto            10
        //   147: aload_0        
        //   148: getfield        org/java_websocket/WebSocketImpl.role:Lorg/java_websocket/WebSocket$Role;
        //   151: getstatic       org/java_websocket/WebSocket$Role.SERVER:Lorg/java_websocket/WebSocket$Role;
        //   154: if_acmpne       465
        //   157: aload_0        
        //   158: getfield        org/java_websocket/WebSocketImpl.draft:Lorg/java_websocket/drafts/Draft;
        //   161: ifnonnull       391
        //   164: aload_0        
        //   165: getfield        org/java_websocket/WebSocketImpl.knownDrafts:Ljava/util/List;
        //   168: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   173: astore          5
        //   175: aload           5
        //   177: invokeinterface java/util/Iterator.hasNext:()Z
        //   182: ifeq            371
        //   185: aload           5
        //   187: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   192: checkcast       Lorg/java_websocket/drafts/Draft;
        //   195: invokevirtual   org/java_websocket/drafts/Draft.copyInstance:()Lorg/java_websocket/drafts/Draft;
        //   198: astore          6
        //   200: aload           6
        //   202: aload_0        
        //   203: getfield        org/java_websocket/WebSocketImpl.role:Lorg/java_websocket/WebSocket$Role;
        //   206: invokevirtual   org/java_websocket/drafts/Draft.setParseMode:(Lorg/java_websocket/WebSocket$Role;)V
        //   209: aload           4
        //   211: invokevirtual   java/nio/ByteBuffer.reset:()Ljava/nio/Buffer;
        //   214: pop            
        //   215: aload           6
        //   217: aload           4
        //   219: invokevirtual   org/java_websocket/drafts/Draft.translateHandshake:(Ljava/nio/ByteBuffer;)Lorg/java_websocket/handshake/Handshakedata;
        //   222: astore          7
        //   224: aload           7
        //   226: instanceof      Lorg/java_websocket/handshake/ClientHandshake;
        //   229: ifne            245
        //   232: aload_0        
        //   233: sipush          1002
        //   236: ldc_w           "wrong http function"
        //   239: iconst_0       
        //   240: invokevirtual   org/java_websocket/WebSocketImpl.flushAndClose:(ILjava/lang/String;Z)V
        //   243: iconst_0       
        //   244: ireturn        
        //   245: aload           7
        //   247: checkcast       Lorg/java_websocket/handshake/ClientHandshake;
        //   250: astore          7
        //   252: aload           6
        //   254: aload           7
        //   256: invokevirtual   org/java_websocket/drafts/Draft.acceptHandshakeAsServer:(Lorg/java_websocket/handshake/ClientHandshake;)Lorg/java_websocket/drafts/Draft$HandshakeState;
        //   259: astore          8
        //   261: getstatic       org/java_websocket/drafts/Draft$HandshakeState.MATCHED:Lorg/java_websocket/drafts/Draft$HandshakeState;
        //   264: astore          9
        //   266: aload           8
        //   268: aload           9
        //   270: if_acmpne       175
        //   273: aload_0        
        //   274: getfield        org/java_websocket/WebSocketImpl.wsl:Lorg/java_websocket/WebSocketListener;
        //   277: aload_0        
        //   278: aload           6
        //   280: aload           7
        //   282: invokeinterface org/java_websocket/WebSocketListener.onWebsocketHandshakeReceivedAsServer:(Lorg/java_websocket/WebSocket;Lorg/java_websocket/drafts/Draft;Lorg/java_websocket/handshake/ClientHandshake;)Lorg/java_websocket/handshake/ServerHandshakeBuilder;
        //   287: astore          8
        //   289: aload_0        
        //   290: aload           6
        //   292: aload           6
        //   294: aload           7
        //   296: aload           8
        //   298: invokevirtual   org/java_websocket/drafts/Draft.postProcessHandshakeResponseAsServer:(Lorg/java_websocket/handshake/ClientHandshake;Lorg/java_websocket/handshake/ServerHandshakeBuilder;)Lorg/java_websocket/handshake/HandshakeBuilder;
        //   301: aload_0        
        //   302: getfield        org/java_websocket/WebSocketImpl.role:Lorg/java_websocket/WebSocket$Role;
        //   305: invokevirtual   org/java_websocket/drafts/Draft.createHandshake:(Lorg/java_websocket/handshake/Handshakedata;Lorg/java_websocket/WebSocket$Role;)Ljava/util/List;
        //   308: invokespecial   org/java_websocket/WebSocketImpl.write:(Ljava/util/List;)V
        //   311: aload_0        
        //   312: aload           6
        //   314: putfield        org/java_websocket/WebSocketImpl.draft:Lorg/java_websocket/drafts/Draft;
        //   317: aload_0        
        //   318: aload           7
        //   320: invokespecial   org/java_websocket/WebSocketImpl.open:(Lorg/java_websocket/handshake/Handshakedata;)V
        //   323: iconst_1       
        //   324: ireturn        
        //   325: astore          6
        //   327: aload_0        
        //   328: aload           6
        //   330: invokevirtual   org/java_websocket/exceptions/InvalidDataException.getCloseCode:()I
        //   333: aload           6
        //   335: invokevirtual   org/java_websocket/exceptions/InvalidDataException.getMessage:()Ljava/lang/String;
        //   338: iconst_0       
        //   339: invokevirtual   org/java_websocket/WebSocketImpl.flushAndClose:(ILjava/lang/String;Z)V
        //   342: iconst_0       
        //   343: ireturn        
        //   344: astore          6
        //   346: aload_0        
        //   347: getfield        org/java_websocket/WebSocketImpl.wsl:Lorg/java_websocket/WebSocketListener;
        //   350: aload_0        
        //   351: aload           6
        //   353: invokeinterface org/java_websocket/WebSocketListener.onWebsocketError:(Lorg/java_websocket/WebSocket;Ljava/lang/Exception;)V
        //   358: aload_0        
        //   359: iconst_m1      
        //   360: aload           6
        //   362: invokevirtual   java/lang/RuntimeException.getMessage:()Ljava/lang/String;
        //   365: iconst_0       
        //   366: invokevirtual   org/java_websocket/WebSocketImpl.flushAndClose:(ILjava/lang/String;Z)V
        //   369: iconst_0       
        //   370: ireturn        
        //   371: aload_0        
        //   372: getfield        org/java_websocket/WebSocketImpl.draft:Lorg/java_websocket/drafts/Draft;
        //   375: ifnonnull       794
        //   378: aload_0        
        //   379: sipush          1002
        //   382: ldc_w           "no draft matches"
        //   385: invokevirtual   org/java_websocket/WebSocketImpl.close:(ILjava/lang/String;)V
        //   388: goto            794
        //   391: aload_0        
        //   392: getfield        org/java_websocket/WebSocketImpl.draft:Lorg/java_websocket/drafts/Draft;
        //   395: aload           4
        //   397: invokevirtual   org/java_websocket/drafts/Draft.translateHandshake:(Ljava/nio/ByteBuffer;)Lorg/java_websocket/handshake/Handshakedata;
        //   400: astore          5
        //   402: aload           5
        //   404: instanceof      Lorg/java_websocket/handshake/ClientHandshake;
        //   407: ifne            423
        //   410: aload_0        
        //   411: sipush          1002
        //   414: ldc_w           "wrong http function"
        //   417: iconst_0       
        //   418: invokevirtual   org/java_websocket/WebSocketImpl.flushAndClose:(ILjava/lang/String;Z)V
        //   421: iconst_0       
        //   422: ireturn        
        //   423: aload           5
        //   425: checkcast       Lorg/java_websocket/handshake/ClientHandshake;
        //   428: astore          5
        //   430: aload_0        
        //   431: getfield        org/java_websocket/WebSocketImpl.draft:Lorg/java_websocket/drafts/Draft;
        //   434: aload           5
        //   436: invokevirtual   org/java_websocket/drafts/Draft.acceptHandshakeAsServer:(Lorg/java_websocket/handshake/ClientHandshake;)Lorg/java_websocket/drafts/Draft$HandshakeState;
        //   439: getstatic       org/java_websocket/drafts/Draft$HandshakeState.MATCHED:Lorg/java_websocket/drafts/Draft$HandshakeState;
        //   442: if_acmpne       453
        //   445: aload_0        
        //   446: aload           5
        //   448: invokespecial   org/java_websocket/WebSocketImpl.open:(Lorg/java_websocket/handshake/Handshakedata;)V
        //   451: iconst_1       
        //   452: ireturn        
        //   453: aload_0        
        //   454: sipush          1002
        //   457: ldc_w           "the handshake did finaly not match"
        //   460: invokevirtual   org/java_websocket/WebSocketImpl.close:(ILjava/lang/String;)V
        //   463: iconst_0       
        //   464: ireturn        
        //   465: aload_0        
        //   466: getfield        org/java_websocket/WebSocketImpl.role:Lorg/java_websocket/WebSocket$Role;
        //   469: getstatic       org/java_websocket/WebSocket$Role.CLIENT:Lorg/java_websocket/WebSocket$Role;
        //   472: if_acmpne       658
        //   475: aload_0        
        //   476: getfield        org/java_websocket/WebSocketImpl.draft:Lorg/java_websocket/drafts/Draft;
        //   479: aload_0        
        //   480: getfield        org/java_websocket/WebSocketImpl.role:Lorg/java_websocket/WebSocket$Role;
        //   483: invokevirtual   org/java_websocket/drafts/Draft.setParseMode:(Lorg/java_websocket/WebSocket$Role;)V
        //   486: aload_0        
        //   487: getfield        org/java_websocket/WebSocketImpl.draft:Lorg/java_websocket/drafts/Draft;
        //   490: aload           4
        //   492: invokevirtual   org/java_websocket/drafts/Draft.translateHandshake:(Ljava/nio/ByteBuffer;)Lorg/java_websocket/handshake/Handshakedata;
        //   495: astore          5
        //   497: aload           5
        //   499: instanceof      Lorg/java_websocket/handshake/ServerHandshake;
        //   502: ifne            518
        //   505: aload_0        
        //   506: sipush          1002
        //   509: ldc_w           "Wwrong http function"
        //   512: iconst_0       
        //   513: invokevirtual   org/java_websocket/WebSocketImpl.flushAndClose:(ILjava/lang/String;Z)V
        //   516: iconst_0       
        //   517: ireturn        
        //   518: aload           5
        //   520: checkcast       Lorg/java_websocket/handshake/ServerHandshake;
        //   523: astore          5
        //   525: aload_0        
        //   526: getfield        org/java_websocket/WebSocketImpl.draft:Lorg/java_websocket/drafts/Draft;
        //   529: aload_0        
        //   530: getfield        org/java_websocket/WebSocketImpl.handshakerequest:Lorg/java_websocket/handshake/ClientHandshake;
        //   533: aload           5
        //   535: invokevirtual   org/java_websocket/drafts/Draft.acceptHandshakeAsClient:(Lorg/java_websocket/handshake/ClientHandshake;Lorg/java_websocket/handshake/ServerHandshake;)Lorg/java_websocket/drafts/Draft$HandshakeState;
        //   538: astore          6
        //   540: getstatic       org/java_websocket/drafts/Draft$HandshakeState.MATCHED:Lorg/java_websocket/drafts/Draft$HandshakeState;
        //   543: astore          7
        //   545: aload           6
        //   547: aload           7
        //   549: if_acmpne       622
        //   552: aload_0        
        //   553: getfield        org/java_websocket/WebSocketImpl.wsl:Lorg/java_websocket/WebSocketListener;
        //   556: aload_0        
        //   557: aload_0        
        //   558: getfield        org/java_websocket/WebSocketImpl.handshakerequest:Lorg/java_websocket/handshake/ClientHandshake;
        //   561: aload           5
        //   563: invokeinterface org/java_websocket/WebSocketListener.onWebsocketHandshakeReceivedAsClient:(Lorg/java_websocket/WebSocket;Lorg/java_websocket/handshake/ClientHandshake;Lorg/java_websocket/handshake/ServerHandshake;)V
        //   568: aload_0        
        //   569: aload           5
        //   571: invokespecial   org/java_websocket/WebSocketImpl.open:(Lorg/java_websocket/handshake/Handshakedata;)V
        //   574: iconst_1       
        //   575: ireturn        
        //   576: astore          5
        //   578: aload_0        
        //   579: aload           5
        //   581: invokevirtual   org/java_websocket/exceptions/InvalidDataException.getCloseCode:()I
        //   584: aload           5
        //   586: invokevirtual   org/java_websocket/exceptions/InvalidDataException.getMessage:()Ljava/lang/String;
        //   589: iconst_0       
        //   590: invokevirtual   org/java_websocket/WebSocketImpl.flushAndClose:(ILjava/lang/String;Z)V
        //   593: iconst_0       
        //   594: ireturn        
        //   595: astore          5
        //   597: aload_0        
        //   598: getfield        org/java_websocket/WebSocketImpl.wsl:Lorg/java_websocket/WebSocketListener;
        //   601: aload_0        
        //   602: aload           5
        //   604: invokeinterface org/java_websocket/WebSocketListener.onWebsocketError:(Lorg/java_websocket/WebSocket;Ljava/lang/Exception;)V
        //   609: aload_0        
        //   610: iconst_m1      
        //   611: aload           5
        //   613: invokevirtual   java/lang/RuntimeException.getMessage:()Ljava/lang/String;
        //   616: iconst_0       
        //   617: invokevirtual   org/java_websocket/WebSocketImpl.flushAndClose:(ILjava/lang/String;Z)V
        //   620: iconst_0       
        //   621: ireturn        
        //   622: aload_0        
        //   623: sipush          1002
        //   626: new             Ljava/lang/StringBuilder;
        //   629: dup            
        //   630: invokespecial   java/lang/StringBuilder.<init>:()V
        //   633: ldc_w           "draft "
        //   636: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   639: aload_0        
        //   640: getfield        org/java_websocket/WebSocketImpl.draft:Lorg/java_websocket/drafts/Draft;
        //   643: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   646: ldc_w           " refuses handshake"
        //   649: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   652: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   655: invokevirtual   org/java_websocket/WebSocketImpl.close:(ILjava/lang/String;)V
        //   658: iconst_0       
        //   659: ireturn        
        //   660: astore          5
        //   662: aload_0        
        //   663: aload           5
        //   665: invokevirtual   org/java_websocket/WebSocketImpl.close:(Lorg/java_websocket/exceptions/InvalidDataException;)V
        //   668: goto            658
        //   671: astore          5
        //   673: aload_0        
        //   674: getfield        org/java_websocket/WebSocketImpl.tmpHandshakeBytes:Ljava/nio/ByteBuffer;
        //   677: ifnonnull       756
        //   680: aload           4
        //   682: invokevirtual   java/nio/ByteBuffer.reset:()Ljava/nio/Buffer;
        //   685: pop            
        //   686: aload           5
        //   688: invokevirtual   org/java_websocket/exceptions/IncompleteHandshakeException.getPreferedSize:()I
        //   691: istore_3       
        //   692: iload_3        
        //   693: ifne            725
        //   696: aload           4
        //   698: invokevirtual   java/nio/ByteBuffer.capacity:()I
        //   701: bipush          16
        //   703: iadd           
        //   704: istore_2       
        //   705: aload_0        
        //   706: iload_2        
        //   707: invokestatic    java/nio/ByteBuffer.allocate:(I)Ljava/nio/ByteBuffer;
        //   710: putfield        org/java_websocket/WebSocketImpl.tmpHandshakeBytes:Ljava/nio/ByteBuffer;
        //   713: aload_0        
        //   714: getfield        org/java_websocket/WebSocketImpl.tmpHandshakeBytes:Ljava/nio/ByteBuffer;
        //   717: aload_1        
        //   718: invokevirtual   java/nio/ByteBuffer.put:(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;
        //   721: pop            
        //   722: goto            658
        //   725: iload_3        
        //   726: istore_2       
        //   727: getstatic       org/java_websocket/WebSocketImpl.$assertionsDisabled:Z
        //   730: ifne            705
        //   733: iload_3        
        //   734: istore_2       
        //   735: aload           5
        //   737: invokevirtual   org/java_websocket/exceptions/IncompleteHandshakeException.getPreferedSize:()I
        //   740: aload           4
        //   742: invokevirtual   java/nio/ByteBuffer.remaining:()I
        //   745: if_icmpge       705
        //   748: new             Ljava/lang/AssertionError;
        //   751: dup            
        //   752: invokespecial   java/lang/AssertionError.<init>:()V
        //   755: athrow         
        //   756: aload_0        
        //   757: getfield        org/java_websocket/WebSocketImpl.tmpHandshakeBytes:Ljava/nio/ByteBuffer;
        //   760: aload_0        
        //   761: getfield        org/java_websocket/WebSocketImpl.tmpHandshakeBytes:Ljava/nio/ByteBuffer;
        //   764: invokevirtual   java/nio/ByteBuffer.limit:()I
        //   767: invokevirtual   java/nio/ByteBuffer.position:(I)Ljava/nio/Buffer;
        //   770: pop            
        //   771: aload_0        
        //   772: getfield        org/java_websocket/WebSocketImpl.tmpHandshakeBytes:Ljava/nio/ByteBuffer;
        //   775: aload_0        
        //   776: getfield        org/java_websocket/WebSocketImpl.tmpHandshakeBytes:Ljava/nio/ByteBuffer;
        //   779: invokevirtual   java/nio/ByteBuffer.capacity:()I
        //   782: invokevirtual   java/nio/ByteBuffer.limit:(I)Ljava/nio/Buffer;
        //   785: pop            
        //   786: goto            658
        //   789: astore          6
        //   791: goto            175
        //   794: iconst_0       
        //   795: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                        
        //  -----  -----  -----  -----  ------------------------------------------------------------
        //  16     64     671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  147    175    660    671    Lorg/java_websocket/exceptions/InvalidHandshakeException;
        //  147    175    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  175    200    660    671    Lorg/java_websocket/exceptions/InvalidHandshakeException;
        //  175    200    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  200    243    789    794    Lorg/java_websocket/exceptions/InvalidHandshakeException;
        //  200    243    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  245    266    789    794    Lorg/java_websocket/exceptions/InvalidHandshakeException;
        //  245    266    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  273    289    325    344    Lorg/java_websocket/exceptions/InvalidDataException;
        //  273    289    344    371    Ljava/lang/RuntimeException;
        //  273    289    789    794    Lorg/java_websocket/exceptions/InvalidHandshakeException;
        //  273    289    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  289    323    789    794    Lorg/java_websocket/exceptions/InvalidHandshakeException;
        //  289    323    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  327    342    789    794    Lorg/java_websocket/exceptions/InvalidHandshakeException;
        //  327    342    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  346    369    789    794    Lorg/java_websocket/exceptions/InvalidHandshakeException;
        //  346    369    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  371    388    660    671    Lorg/java_websocket/exceptions/InvalidHandshakeException;
        //  371    388    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  391    421    660    671    Lorg/java_websocket/exceptions/InvalidHandshakeException;
        //  391    421    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  423    451    660    671    Lorg/java_websocket/exceptions/InvalidHandshakeException;
        //  423    451    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  453    463    660    671    Lorg/java_websocket/exceptions/InvalidHandshakeException;
        //  453    463    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  465    516    660    671    Lorg/java_websocket/exceptions/InvalidHandshakeException;
        //  465    516    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  518    545    660    671    Lorg/java_websocket/exceptions/InvalidHandshakeException;
        //  518    545    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  552    568    576    595    Lorg/java_websocket/exceptions/InvalidDataException;
        //  552    568    595    622    Ljava/lang/RuntimeException;
        //  552    568    660    671    Lorg/java_websocket/exceptions/InvalidHandshakeException;
        //  552    568    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  568    574    660    671    Lorg/java_websocket/exceptions/InvalidHandshakeException;
        //  568    574    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  578    593    660    671    Lorg/java_websocket/exceptions/InvalidHandshakeException;
        //  578    593    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  597    620    660    671    Lorg/java_websocket/exceptions/InvalidHandshakeException;
        //  597    620    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  622    658    660    671    Lorg/java_websocket/exceptions/InvalidHandshakeException;
        //  622    658    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        //  662    668    671    789    Lorg/java_websocket/exceptions/IncompleteHandshakeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0245:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private Draft$HandshakeState isFlashEdgeCase(final ByteBuffer byteBuffer) {
        byteBuffer.mark();
        if (byteBuffer.limit() > Draft.FLASH_POLICY_REQUEST.length) {
            return Draft$HandshakeState.NOT_MATCHED;
        }
        if (byteBuffer.limit() < Draft.FLASH_POLICY_REQUEST.length) {
            throw new IncompleteHandshakeException(Draft.FLASH_POLICY_REQUEST.length);
        }
        int n = 0;
        while (byteBuffer.hasRemaining()) {
            if (Draft.FLASH_POLICY_REQUEST[n] != byteBuffer.get()) {
                byteBuffer.reset();
                return Draft$HandshakeState.NOT_MATCHED;
            }
            ++n;
        }
        return Draft$HandshakeState.MATCHED;
    }
    
    private void open(final Handshakedata handshakedata) {
        if (WebSocketImpl.DEBUG) {
            System.out.println("open using draft: " + this.draft.getClass().getSimpleName());
        }
        this.readystate = WebSocket$READYSTATE.OPEN;
        try {
            this.wsl.onWebsocketOpen(this, handshakedata);
        }
        catch (RuntimeException ex) {
            this.wsl.onWebsocketError(this, ex);
        }
    }
    
    private void send(final Collection<Framedata> collection) {
        if (!this.isOpen()) {
            throw new WebsocketNotConnectedException();
        }
        final Iterator<Framedata> iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.sendFrame(iterator.next());
        }
    }
    
    private void write(final ByteBuffer byteBuffer) {
        if (WebSocketImpl.DEBUG) {
            final PrintStream out = System.out;
            final StringBuilder append = new StringBuilder().append("write(").append(byteBuffer.remaining()).append("): {");
            String s;
            if (byteBuffer.remaining() > 1000) {
                s = "too big to display";
            }
            else {
                s = new String(byteBuffer.array());
            }
            out.println(append.append(s).append("}").toString());
        }
        this.outQueue.add(byteBuffer);
        this.wsl.onWriteDemand(this);
    }
    
    private void write(final List<ByteBuffer> list) {
        final Iterator<ByteBuffer> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.write(iterator.next());
        }
    }
    
    public void close(final int n) {
        this.close(n, "", false);
    }
    
    public void close(final int n, final String s) {
        this.close(n, s, false);
    }
    
    public void close(final InvalidDataException ex) {
        this.close(ex.getCloseCode(), ex.getMessage(), false);
    }
    
    public void closeConnection(final int n, final String s) {
        this.closeConnection(n, s, false);
    }
    
    protected void closeConnection(final int p0, final String p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        org/java_websocket/WebSocketImpl.readystate:Lorg/java_websocket/WebSocket$READYSTATE;
        //     6: astore          4
        //     8: getstatic       org/java_websocket/WebSocket$READYSTATE.CLOSED:Lorg/java_websocket/WebSocket$READYSTATE;
        //    11: astore          5
        //    13: aload           4
        //    15: aload           5
        //    17: if_acmpne       23
        //    20: aload_0        
        //    21: monitorexit    
        //    22: return         
        //    23: aload_0        
        //    24: getfield        org/java_websocket/WebSocketImpl.key:Ljava/nio/channels/SelectionKey;
        //    27: ifnull          37
        //    30: aload_0        
        //    31: getfield        org/java_websocket/WebSocketImpl.key:Ljava/nio/channels/SelectionKey;
        //    34: invokevirtual   java/nio/channels/SelectionKey.cancel:()V
        //    37: aload_0        
        //    38: getfield        org/java_websocket/WebSocketImpl.channel:Ljava/nio/channels/ByteChannel;
        //    41: astore          4
        //    43: aload           4
        //    45: ifnull          57
        //    48: aload_0        
        //    49: getfield        org/java_websocket/WebSocketImpl.channel:Ljava/nio/channels/ByteChannel;
        //    52: invokeinterface java/nio/channels/ByteChannel.close:()V
        //    57: aload_0        
        //    58: getfield        org/java_websocket/WebSocketImpl.wsl:Lorg/java_websocket/WebSocketListener;
        //    61: aload_0        
        //    62: iload_1        
        //    63: aload_2        
        //    64: iload_3        
        //    65: invokeinterface org/java_websocket/WebSocketListener.onWebsocketClose:(Lorg/java_websocket/WebSocket;ILjava/lang/String;Z)V
        //    70: aload_0        
        //    71: getfield        org/java_websocket/WebSocketImpl.draft:Lorg/java_websocket/drafts/Draft;
        //    74: ifnull          84
        //    77: aload_0        
        //    78: getfield        org/java_websocket/WebSocketImpl.draft:Lorg/java_websocket/drafts/Draft;
        //    81: invokevirtual   org/java_websocket/drafts/Draft.reset:()V
        //    84: aload_0        
        //    85: aconst_null    
        //    86: putfield        org/java_websocket/WebSocketImpl.handshakerequest:Lorg/java_websocket/handshake/ClientHandshake;
        //    89: aload_0        
        //    90: getstatic       org/java_websocket/WebSocket$READYSTATE.CLOSED:Lorg/java_websocket/WebSocket$READYSTATE;
        //    93: putfield        org/java_websocket/WebSocketImpl.readystate:Lorg/java_websocket/WebSocket$READYSTATE;
        //    96: aload_0        
        //    97: getfield        org/java_websocket/WebSocketImpl.outQueue:Ljava/util/concurrent/BlockingQueue;
        //   100: invokeinterface java/util/concurrent/BlockingQueue.clear:()V
        //   105: goto            20
        //   108: astore_2       
        //   109: aload_0        
        //   110: monitorexit    
        //   111: aload_2        
        //   112: athrow         
        //   113: astore          4
        //   115: aload_0        
        //   116: getfield        org/java_websocket/WebSocketImpl.wsl:Lorg/java_websocket/WebSocketListener;
        //   119: aload_0        
        //   120: aload           4
        //   122: invokeinterface org/java_websocket/WebSocketListener.onWebsocketError:(Lorg/java_websocket/WebSocket;Ljava/lang/Exception;)V
        //   127: goto            57
        //   130: astore_2       
        //   131: aload_0        
        //   132: getfield        org/java_websocket/WebSocketImpl.wsl:Lorg/java_websocket/WebSocketListener;
        //   135: aload_0        
        //   136: aload_2        
        //   137: invokeinterface org/java_websocket/WebSocketListener.onWebsocketError:(Lorg/java_websocket/WebSocket;Ljava/lang/Exception;)V
        //   142: goto            70
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  2      13     108    113    Any
        //  23     37     108    113    Any
        //  37     43     108    113    Any
        //  48     57     113    130    Ljava/io/IOException;
        //  48     57     108    113    Any
        //  57     70     130    145    Ljava/lang/RuntimeException;
        //  57     70     108    113    Any
        //  70     84     108    113    Any
        //  84     105    108    113    Any
        //  115    127    108    113    Any
        //  131    142    108    113    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 69, Size: 69
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void closeConnection(final int n, final boolean b) {
        this.closeConnection(n, "", b);
    }
    
    public void decode(final ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining() && !this.flushandclosestate) {
            if (WebSocketImpl.DEBUG) {
                final PrintStream out = System.out;
                final StringBuilder append = new StringBuilder().append("process(").append(byteBuffer.remaining()).append("): {");
                String s;
                if (byteBuffer.remaining() > 1000) {
                    s = "too big to display";
                }
                else {
                    s = new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining());
                }
                out.println(append.append(s).append("}").toString());
            }
            if (this.readystate == WebSocket$READYSTATE.OPEN) {
                this.decodeFrames(byteBuffer);
            }
            else if (this.decodeHandshake(byteBuffer)) {
                this.decodeFrames(byteBuffer);
            }
            if (!WebSocketImpl.$assertionsDisabled && !this.isClosing() && !this.isFlushAndClose() && byteBuffer.hasRemaining()) {
                throw new AssertionError();
            }
        }
    }
    
    public void eot() {
        if (this.getReadyState() == WebSocket$READYSTATE.NOT_YET_CONNECTED) {
            this.closeConnection(-1, true);
            return;
        }
        if (this.flushandclosestate) {
            this.closeConnection(this.closecode, this.closemessage, this.closedremotely);
            return;
        }
        if (this.draft.getCloseHandshakeType() == Draft$CloseHandshakeType.NONE) {
            this.closeConnection(1000, true);
            return;
        }
        if (this.draft.getCloseHandshakeType() != Draft$CloseHandshakeType.ONEWAY) {
            this.closeConnection(1006, true);
            return;
        }
        if (this.role == WebSocket$Role.SERVER) {
            this.closeConnection(1006, true);
            return;
        }
        this.closeConnection(1000, true);
    }
    
    protected void flushAndClose(final int p0, final String p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        org/java_websocket/WebSocketImpl.flushandclosestate:Z
        //     6: istore          4
        //     8: iload           4
        //    10: ifeq            16
        //    13: aload_0        
        //    14: monitorexit    
        //    15: return         
        //    16: aload_0        
        //    17: iload_1        
        //    18: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    21: putfield        org/java_websocket/WebSocketImpl.closecode:Ljava/lang/Integer;
        //    24: aload_0        
        //    25: aload_2        
        //    26: putfield        org/java_websocket/WebSocketImpl.closemessage:Ljava/lang/String;
        //    29: aload_0        
        //    30: iload_3        
        //    31: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    34: putfield        org/java_websocket/WebSocketImpl.closedremotely:Ljava/lang/Boolean;
        //    37: aload_0        
        //    38: iconst_1       
        //    39: putfield        org/java_websocket/WebSocketImpl.flushandclosestate:Z
        //    42: aload_0        
        //    43: getfield        org/java_websocket/WebSocketImpl.wsl:Lorg/java_websocket/WebSocketListener;
        //    46: aload_0        
        //    47: invokeinterface org/java_websocket/WebSocketListener.onWriteDemand:(Lorg/java_websocket/WebSocket;)V
        //    52: aload_0        
        //    53: getfield        org/java_websocket/WebSocketImpl.wsl:Lorg/java_websocket/WebSocketListener;
        //    56: aload_0        
        //    57: iload_1        
        //    58: aload_2        
        //    59: iload_3        
        //    60: invokeinterface org/java_websocket/WebSocketListener.onWebsocketClosing:(Lorg/java_websocket/WebSocket;ILjava/lang/String;Z)V
        //    65: aload_0        
        //    66: getfield        org/java_websocket/WebSocketImpl.draft:Lorg/java_websocket/drafts/Draft;
        //    69: ifnull          79
        //    72: aload_0        
        //    73: getfield        org/java_websocket/WebSocketImpl.draft:Lorg/java_websocket/drafts/Draft;
        //    76: invokevirtual   org/java_websocket/drafts/Draft.reset:()V
        //    79: aload_0        
        //    80: aconst_null    
        //    81: putfield        org/java_websocket/WebSocketImpl.handshakerequest:Lorg/java_websocket/handshake/ClientHandshake;
        //    84: goto            13
        //    87: astore_2       
        //    88: aload_0        
        //    89: monitorexit    
        //    90: aload_2        
        //    91: athrow         
        //    92: astore_2       
        //    93: aload_0        
        //    94: getfield        org/java_websocket/WebSocketImpl.wsl:Lorg/java_websocket/WebSocketListener;
        //    97: aload_0        
        //    98: aload_2        
        //    99: invokeinterface org/java_websocket/WebSocketListener.onWebsocketError:(Lorg/java_websocket/WebSocket;Ljava/lang/Exception;)V
        //   104: goto            65
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  2      8      87     92     Any
        //  16     52     87     92     Any
        //  52     65     92     107    Ljava/lang/RuntimeException;
        //  52     65     87     92     Any
        //  65     79     87     92     Any
        //  79     84     87     92     Any
        //  93     104    87     92     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public InetSocketAddress getLocalSocketAddress() {
        return this.wsl.getLocalSocketAddress(this);
    }
    
    public WebSocket$READYSTATE getReadyState() {
        return this.readystate;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    public boolean isClosing() {
        return this.readystate == WebSocket$READYSTATE.CLOSING;
    }
    
    public boolean isFlushAndClose() {
        return this.flushandclosestate;
    }
    
    public boolean isOpen() {
        if (WebSocketImpl.$assertionsDisabled || this.readystate != WebSocket$READYSTATE.OPEN || !this.flushandclosestate) {
            return this.readystate == WebSocket$READYSTATE.OPEN;
        }
        throw new AssertionError();
    }
    
    public void send(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
        }
        this.send(this.draft.createFrames(s, this.role == WebSocket$Role.CLIENT));
    }
    
    @Override
    public void sendFrame(final Framedata framedata) {
        if (WebSocketImpl.DEBUG) {
            System.out.println("send frame: " + framedata);
        }
        this.write(this.draft.createBinaryFrame(framedata));
    }
    
    public void startHandshake(final ClientHandshakeBuilder clientHandshakeBuilder) {
        assert this.readystate != WebSocket$READYSTATE.CONNECTING : "shall only be called once";
        this.handshakerequest = this.draft.postProcessHandshakeRequestAsClient(clientHandshakeBuilder);
        try {
            this.wsl.onWebsocketHandshakeSentAsClient(this, this.handshakerequest);
            this.write(this.draft.createHandshake(this.handshakerequest, this.role));
        }
        catch (InvalidDataException ex2) {
            throw new InvalidHandshakeException("Handshake data rejected by client.");
        }
        catch (RuntimeException ex) {
            this.wsl.onWebsocketError(this, ex);
            throw new InvalidHandshakeException("rejected because of" + ex);
        }
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
