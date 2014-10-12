/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

import execute.Server;
import helpers.LogPrinter;
import java.net.Socket;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import processing.ServerProcessorRequest;
import threading.ThreadPerRequestScheduler;
import transmission.common.MessageUtils;
import transmission.common.TransmissionPacket;
import users.User;
import static org.junit.Assert.*;

/**
 *
 * @author JamesFoxes
 */
public class TestServer
{

    private IncomingConnectionsHandler serverConnection;
    private Socket socket;

    private final ThreadPerRequestScheduler serverThreadPool = new ThreadPerRequestScheduler();

    public TestServer() throws Exception
    {
        serverConnection = new IncomingConnectionsHandler();
        ServerProcessorRequest setupServer = new ServerProcessorRequest(serverConnection);
        serverThreadPool.schedule(setupServer);
        
        Thread.sleep(10);
        socket = Server.serverTransmitter.getTestingSocket();
    }

    @Before
    public void initialize() throws Exception
    {
        
    }

    @Test
    public void testSendGetUsers() throws Exception
    {
        int amountToSend = 10;
        int expectedUserIDSum = 0;
        
        for (int i = 0; i < amountToSend; i++)
        {
            User user = new User();
            user.ID = 123 * i;
            expectedUserIDSum += user.ID;
            Server.serverTransmitter.requestUser(user);
        }
        TransmissionPacket returnMessage = MessageUtils.getTransmission(socket);

        // command test
        assertEquals("ERR: wrong command!", TransmissionPacket.Commands.nil, returnMessage.command); //ONLY EXPECT nil BECAUSE WE ARE NOT SERVER
        
        // user count test
        int usersSent = Integer.parseInt(returnMessage.dataString);
        LogPrinter.printTest("Amount of users sent to server: " + usersSent);
        assertEquals("ERR: less users recieved than was sent!", amountToSend, usersSent);
        assertEquals("ERR: users left in array - unsent!", 0, Server.serverTransmitter.getUsersInArray());
        
        // user array test
        ArrayList<User> users = new ArrayList<>();
        try
        {
            users = (ArrayList<User>) returnMessage.dataObject;
        } catch (Exception e)
        {
            LogPrinter.printTestError("Unable to cast array of users", e);
        }
        int userIDSum = 0;
        for (int i = 0; i < amountToSend; i++)
        {
            userIDSum += users.get(i).ID;
        }
        LogPrinter.printTest("UserID recieved: " + Integer.toString(userIDSum));
        assertEquals(expectedUserIDSum, userIDSum);
    }
}
