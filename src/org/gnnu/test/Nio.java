package org.gnnu.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Nio {
    private static long current=System.currentTimeMillis();
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        serverSocketChannel.configureBlocking(false);
        Selector selector=Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        StringBuffer message=new StringBuffer();
        while(true){
            selector.select();
            Set<SelectionKey> selectionKeys=selector.selectedKeys();
            Iterator<SelectionKey> iterator=selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey= iterator.next();
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel server=(ServerSocketChannel)selectionKey.channel();
                    SocketChannel socketChannel=server.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
                    SocketChannel channel=(SocketChannel)selectionKey.channel();
                    ByteBuffer byteBuffer=ByteBuffer.allocate(128);
                    int len = channel.read(byteBuffer);
                    if(len>0){
                        channel.write(byteBuffer.wrap(new String("Hello").getBytes()));
                    }
                }
                iterator.remove();
            }
        }

    }
}
