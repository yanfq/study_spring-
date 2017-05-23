package yanfq.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by yanfq on 17-5-22.
 */
public class MultiplexerTimerServer implements Runnable {
    private Selector selector;

    private ServerSocketChannel serverChannel;

    private volatile boolean stop;

    public MultiplexerTimerServer() {
    }

    /**
     * 初始化多路复用器，绑定监听接口
     *
     * @param port
     */
    public MultiplexerTimerServer(int port) {
        try {
            selector = Selector.open();
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("this server port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //多路复用器关闭后，所有注册在上面的Channel和pipe等资源都会被自动关闭，不需要重复关闭
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleInput(SelectionKey key) {
        try {
            if (key.isValid()) {
                //处理新接入的请求信息
                if (key.isAcceptable()) {
                    //accept 新的连接
                    handleAccept(key);
                }
                if (key.isReadable()) {
                    //read the data
                    handleRead(key);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doWrite(SocketChannel socketChannel, String response) {
        try {
            if(response != null && response.trim().length() > 0){
                byte[] bytes = response.getBytes();
                ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
                writeBuffer.put(bytes);
                writeBuffer.flip();
                socketChannel.write(writeBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleAccept(SelectionKey key) throws IOException{
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverChannel.accept();
        socketChannel.configureBlocking(false);
        //添加新连接到selector
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    public void handleRead(SelectionKey key) throws IOException{
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int readBytes = socketChannel.read(readBuffer);
        if (readBytes > 0) {
            readBuffer.flip();
            byte[] bytes = new byte[readBuffer.remaining()];
            readBuffer.get(bytes);
            String body = new String(bytes, "UTF-8");
            System.out.println("======body=" + body);
            String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
            doWrite(socketChannel, currentTime);
        } else if (readBytes < 0) {
            //对端链路关闭
            key.cancel();
            socketChannel.close();
        } else {
            //忽略，0字节
        }
    }

    public static void handleWrite(SelectionKey key) throws IOException{
        ByteBuffer buffer = (ByteBuffer)key.attachment();
        buffer.flip();
        SocketChannel socketChannel = (SocketChannel) key.channel();
        while(buffer.hasRemaining()){
            socketChannel.write(buffer);
        }
        buffer.compact();
    }

}
