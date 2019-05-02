package swimple.controllers;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@ServerEndpoint(value = "/ws-comments/{training_id}")
public class CommentUpdatesController {

    private Map<Integer, List<Session>> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void open(Session session, EndpointConfig c, @PathParam("training_id") int trainingId) {
        List<Session> sessionsForTraining = this.sessions.get(trainingId);

        if (sessionsForTraining == null) {
            sessionsForTraining = new ArrayList<>();
        }

        sessionsForTraining.add(session);

        this.sessions.put(trainingId, sessionsForTraining);
    }

    @OnClose
    public void close(Session session, @PathParam("training_id") int trainingId) {
        List<Session> sessionList = sessions.get(trainingId);
        sessionList.remove(session);
    }

    public void sendMessage(int trainingId, String message) {
        try {
            List<Session> sessionList = sessions.get(trainingId);

            for (Session session: sessionList) {
                session.getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
