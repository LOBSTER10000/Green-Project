package com.sunny.green.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;

@Component
public class ChatHandler extends TextWebSocketHandler {

    private static LinkedHashSet<WebSocketSession> numSet = new LinkedHashSet<>();


    //웹소켓을 통해 받은 메세지 처리
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        boolean isSessionAlive = false;

        for(WebSocketSession sess: numSet) {
            if(sess.getId().equals(session.getId())){
                isSessionAlive = true;
            }
        }
        if(isSessionAlive){
            for(WebSocketSession sess: numSet) {
                sess.sendMessage(message);
            }
        }

/*       String payload = message.getPayload();
        System.out.println("payload " + payload);
        TextMessage greeting = new TextMessage("안녕하세요, 그린토피아입니다. 무엇을 도와드릴까요?");
        session.sendMessage(greeting);*/
    }

    //연결, 클라이언트(session) 접속시 호출되는 메서드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        numSet.add(session);
        System.out.println(session + " 클라이언트 접속");

    }

    //클라이언트 접속 해제시 호출되는 메서드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        numSet.remove(session);
        System.out.println(session + " 클라이언트 접속 해제");
    }
}