package yanfq.controller;

import yanfq.service.MultiplexerTimerServer;

/**
 * Created by yanfq on 17-5-22.
 */
public class TimerServer {


    public static void timerHandler(int port){
        MultiplexerTimerServer  timerServer = new MultiplexerTimerServer(port);
        new Thread(timerServer,"NIO-MultiplexerTimerServer-001").start();

    }
}
