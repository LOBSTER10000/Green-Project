package com.sunny.green.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;

@Log4j2
@Component
public class ChatHandler extends TextWebSocketHandler {

    /*
     WebSocketSession 은 클라이언트와 서버 간의 통신을 위한 세션 객체
     채팅방에 접속한 WebSocketSession 들을 LinkedHashSet 에 저장
    */
    private static LinkedHashSet<WebSocketSession> numSet = new LinkedHashSet<>();


    //웹소켓을 통해 받은 메세지 처리
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        boolean isSessionAlive = false;

        for (WebSocketSession sess : numSet) {
            if (sess.getId().equals(session.getId())) {
                isSessionAlive = true;
            }
        }
        if (isSessionAlive) {
            for (WebSocketSession sess : numSet) {
                sess.sendMessage(message);
            }
        }
    }

    //연결, 클라이언트(session) 접속시 호출되는 메서드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        numSet.add(session);
        log.info(session + " 클라이언트 접속");
    }

    //클라이언트 접속 해제시 호출되는 메서드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        numSet.remove(session);
        log.info(session + " 클라이언트 접속 해제");
    }
}