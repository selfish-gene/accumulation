package com.selfish.jene.io.nio2.watchservice;

import java.nio.file.*;

/**
 * Created by Administrator on 2017/1/8.
 */
public class WatchServiceTest {
    public static void main(String[] args) throws Exception {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Paths.get("C:/").register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);

        while (true) {
            WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()){
                System.out.println(event.context() + " 发生了 " + event.kind() + "事件");
            }
            boolean valid = key.reset();
            if(!valid){
                break;
            }
        }
    }
}
