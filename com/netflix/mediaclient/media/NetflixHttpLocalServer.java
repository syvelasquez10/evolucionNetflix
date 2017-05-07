// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import java.io.InputStream;
import java.nio.channels.WritableByteChannel;
import java.nio.channels.Channel;
import java.net.ServerSocket;
import java.io.File;
import java.io.BufferedInputStream;
import java.net.Socket;
import java.util.Date;
import java.io.PrintStream;
import java.io.IOException;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.io.OutputStream;

class NetflixHttpLocalServer implements Runnable
{
    private static final int BUF_SIZE = 2048;
    private static final int HTTP_BAD_METHOD = 405;
    private static final int HTTP_OK = 200;
    private static final int STREAM_BUF_SIZE = 262144;
    private static final String TAG = "NF_HttpLocalServer";
    private final byte[] EOL;
    String expectedFile;
    private volatile boolean isPlaying;
    private InputDataListener mInputDataListner;
    int port;
    int timeout;
    
    public NetflixHttpLocalServer(final int port) {
        this.EOL = new byte[] { 13, 10 };
        this.isPlaying = true;
        this.timeout = 5000;
        this.port = port;
    }
    
    private void doStreaming(OutputStream channel) {
        channel = (OutputStream)Channels.newChannel(channel);
        final ByteBuffer allocateDirect = ByteBuffer.allocateDirect(262144);
        while (true) {
            Label_0100: {
                if (this.isPlaying) {
                    int onNetflixHttpLocalServerInputData = 0;
                    if (this.mInputDataListner != null) {
                        onNetflixHttpLocalServerInputData = this.mInputDataListner.onNetflixHttpLocalServerInputData(allocateDirect, 262144L);
                    }
                    if (onNetflixHttpLocalServerInputData != 0) {
                        if (onNetflixHttpLocalServerInputData <= 262144) {
                            break Label_0100;
                        }
                    }
                    try {
                        Thread.sleep(30L);
                        continue;
                    }
                    catch (InterruptedException ex3) {
                        Log.d("NF_HttpLocalServer", "NetflixHttpLocalServer interrupted waiting for input");
                    }
                }
            Label_0069:
                while (true) {
                    Log.v("NF_HttpLocalServer", "===============");
                    Log.v("NF_HttpLocalServer", "Done");
                    Log.v("NF_HttpLocalServer", "===============");
                    try {
                        ((Channel)channel).close();
                        return;
                        // iftrue(Label_0069:, onNetflixHttpLocalServerInputData < 0)
                        while (true) {
                            Block_8: {
                                break Block_8;
                                try {
                                    Thread.sleep(10L);
                                }
                                catch (InterruptedException ex4) {
                                    Log.d("NF_HttpLocalServer", "NetflixHttpLocalServer interrupted while idle");
                                }
                            }
                            try {
                                final int onNetflixHttpLocalServerInputData;
                                allocateDirect.limit(onNetflixHttpLocalServerInputData);
                                allocateDirect.position(0);
                                ((WritableByteChannel)channel).write(allocateDirect);
                                if (!allocateDirect.hasRemaining()) {
                                    continue;
                                }
                                Log.e("NF_HttpLocalServer", "try to write " + onNetflixHttpLocalServerInputData + " bytes,remains " + allocateDirect.remaining());
                            }
                            catch (Exception ex) {
                                Log.d("NF_HttpLocalServer", "NetflixHttpLocalServer error while outputing data");
                                ex.printStackTrace();
                            }
                            break;
                        }
                    }
                    catch (IOException ex2) {
                        ex2.printStackTrace();
                    }
                }
            }
        }
    }
    
    private void printStreamingHeaders(final PrintStream printStream) throws IOException {
        printStream.print("HTTP/1.0 " + 200 + " OK");
        printStream.write(this.EOL);
        printStream.print("Server: Simple java");
        printStream.write(this.EOL);
        printStream.print("Date: " + new Date());
        printStream.write(this.EOL);
        printStream.print("X-SocketTimeout: 2147483647");
        printStream.write(this.EOL);
        printStream.print("Content-Length: 2147483647");
        printStream.write(this.EOL);
        printStream.write(this.EOL);
        printStream.flush();
    }
    
    private boolean verifyClientRequest(final Socket socket) throws IOException {
        Object o = new byte[2048];
        final PrintStream printStream = new PrintStream(socket.getOutputStream());
        Object o2 = new BufferedInputStream(socket.getInputStream(), 2048);
        socket.setSoTimeout(this.timeout);
        socket.setTcpNoDelay(true);
        for (int i = 0; i < 2048; ++i) {
            o[i] = 0;
        }
        int n = 0;
    Label_0072:
        while (true) {
            while (true) {
            Label_0173:
                while (true) {
                    if (n >= 2048) {
                        break Label_0173;
                    }
                Label_0399_Outer:
                    while (true) {
                        int index = 0;
                        int index2 = 0;
                        Label_0541: {
                            try {
                                final int read = ((InputStream)o2).read((byte[])o, n, 2048 - n);
                                if (read == -1) {
                                    Log.e("NF_HttpLocalServer", "NetflixHttpLocalServer CLIENT EOF " + n);
                                    return false;
                                }
                                index = n;
                                index2 = n + read;
                                Log.v("NF_HttpLocalServer", "read CLIENT i=" + index + " r=" + read);
                                break Label_0541;
                                // iftrue(Label_0518:, index2 <= 0)
                                // iftrue(Label_0442:, !o.startsWith(File.separator))
                                // iftrue(Label_0399:, !Log.isLoggable("NF_HttpLocalServer", 3))
                                // iftrue(Label_0303:, index3 >= 0 && index >= 0)
                            Block_12_Outer:
                                while (true) {
                                    int index3 = 0;
                                Block_10:
                                    while (true) {
                                        String s = null;
                                        Label_0442: {
                                            while (true) {
                                                Log.e("NF_HttpLocalServer", "unsupported: indexGet " + index3 + "indexFname " + index);
                                                printStream.print("HTTP/1.0 405 unsupported method type: ");
                                                printStream.write((byte[])o, 0, 5);
                                                printStream.write(this.EOL);
                                                printStream.flush();
                                                return false;
                                                Label_0303: {
                                                    s = "";
                                                }
                                                index2 = ((String)o2).indexOf(" ", index);
                                                Block_9: {
                                                    break Block_9;
                                                    while (true) {
                                                        s = ((String)o).substring(1);
                                                        break Label_0442;
                                                        o = (s = new String((byte[])o, index, index2).replace('/', File.separatorChar));
                                                        continue Label_0399_Outer;
                                                    }
                                                    Log.e("NF_HttpLocalServer", "GET has bad filename");
                                                    printStream.print("HTTP/1.0 405");
                                                    printStream.write(this.EOL);
                                                    printStream.flush();
                                                    return false;
                                                }
                                                index2 -= index;
                                                break Block_10;
                                                o2 = new String((byte[])o);
                                                Log.v("NF_HttpLocalServer", "=== Client request ===\n" + ((String)o2).trim());
                                                index3 = ((String)o2).indexOf("GET ");
                                                index = ((String)o2).indexOf("/");
                                                continue Label_0399_Outer;
                                            }
                                            Label_0518: {
                                                Log.e("NF_HttpLocalServer", "GET not properly formed, no filename");
                                            }
                                        }
                                        Log.d("NF_HttpLocalServer", "===GET FILENAME = |" + s + "|===");
                                        continue;
                                    }
                                    Log.d("NF_HttpLocalServer", "index of GET " + index3);
                                    Log.d("NF_HttpLocalServer", "index of Fname " + index + ", size " + index2);
                                    continue Block_12_Outer;
                                }
                            }
                            // iftrue(Label_0533:, s.compareTo(this.expectedFile) == 0)
                            finally {}
                            break Label_0072;
                        }
                    Block_5:
                        while (true) {
                            n = index2;
                            if (index >= index2) {
                                continue Label_0072;
                            }
                            if (o[index] == 10) {
                                continue Label_0173;
                            }
                            if (o[index] == 13) {
                                break Block_5;
                            }
                            ++index;
                        }
                        continue Label_0173;
                    }
                    break;
                }
            }
            break;
        }
        this.printStreamingHeaders(printStream);
        return true;
    }
    
    @Override
    public void run() {
        while (true) {
            // monitorenter(this)
            int n = 0;
        Label_0179:
            while (true) {
                ServerSocket serverSocket = null;
                Label_0127: {
                    try {
                        this.isPlaying = true;
                        while (this.isPlaying) {
                            try {
                                serverSocket = new ServerSocket(this.port);
                                Log.d("NF_HttpLocalServer", "ServerSocket timeout: " + serverSocket.getSoTimeout());
                                Label_0076: {
                                    if (n != 0) {
                                        break Label_0076;
                                    }
                                    Log.d("NF_HttpLocalServer", "Readying NetflixHttpLocalServer");
                                    try {
                                        this.wait();
                                        if (!this.isPlaying) {
                                            serverSocket.close();
                                            return;
                                        }
                                        break Label_0127;
                                    }
                                    catch (InterruptedException ex) {
                                        Log.d("NF_HttpLocalServer", "NetflixHttpLocalServer interrupted");
                                        serverSocket.close();
                                    }
                                }
                            }
                            catch (Exception serverSocket) {
                                Log.e("NF_HttpLocalServer", "NetflixHttpLocalServer failure", (Throwable)serverSocket);
                            }
                        }
                        break Label_0179;
                    }
                    finally {
                    }
                    // monitorexit(this)
                }
                final Socket accept = serverSocket.accept();
                Log.d("NF_HttpLocalServer", "Accept a Client");
                n = 0;
                if (this.verifyClientRequest(accept)) {
                    this.doStreaming(accept.getOutputStream());
                }
                else {
                    n = 1;
                }
                accept.close();
                serverSocket.close();
                continue;
            }
            Log.d("NF_HttpLocalServer", "NetflixHttpLocalServer stopped");
        }
    }
    
    public void setInputDataListener(final InputDataListener mInputDataListner) {
        this.mInputDataListner = mInputDataListner;
    }
    
    public void start(final String expectedFile) {
        synchronized (this) {
            Log.d("NF_HttpLocalServer", "Starting NetflixHttpLocalServer" + expectedFile);
            this.expectedFile = expectedFile;
            this.notify();
        }
    }
    
    public void stop() {
        synchronized (this) {
            Log.d("NF_HttpLocalServer", "Stopping NetflixHttpLocalServer");
            this.isPlaying = false;
            this.notify();
        }
    }
    
    public interface InputDataListener
    {
        int onNetflixHttpLocalServerInputData(final ByteBuffer p0, final long p1);
    }
}
