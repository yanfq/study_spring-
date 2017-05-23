package yanfq.controller;

import org.springframework.stereotype.Component;
import yanfq.service.MultiplexerTimerServer;

/**
 * Created by yanfq on 17-5-22.
 */
@Component
public class TimerServer {


    public void timerHandler(int port){
        MultiplexerTimerServer  timerServer = new MultiplexerTimerServer(port);
        new Thread(timerServer,"NIO-MultiplexerTimerServer-001").start();
    }
}
