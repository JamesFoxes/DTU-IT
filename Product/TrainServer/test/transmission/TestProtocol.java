/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmission;

import static common.ServerData.TCP_LOCAL_ADDRESS;
import static common.ServerData.TCP_PORT;
import execute.Server;
import helpers.LogPrinter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import processing.ServerProcessorRequest;
import threading.ThreadPerRequestScheduler;
import static org.junit.Assert.*;
import transmission.common.MessageUtils;
import transmission.common.TransmissionPacket;
import transmission.common.TransmissionPacket.Commands;
import users.User;

/**sFoxes
 */
public class TestProtocol
{

    private static final int TIMEOUT = 5000;

    IncomingConnectionsHandler serverConnection;
    ExecutorService scheduler;

    private ThreadPerRequestScheduler serverThreadPool;

    public TestProtocol()
    {
        serverThreadPool = new ThreadPerRequestScheduler();
    }

    @Before
    public void createServer() throws Exception
    {
        scheduler = Executors.newCachedThreadPool();
        serverConnection = new IncomingConnectionsHandler();
        ServerProcessorRequest setupServer = new ServerProcessorRequest(serverConnection);
        serverThreadPool.schedule(setupServer);
    }

    @After
    public void shutdownServer() throws InterruptedException
    {
        if (serverConnection != null)
        {
            serverConnection.stopProcessing();
        }
    }

    @Test(timeout = TIMEOUT)
    public void clientGETINFO() throws Exception
    {
        TransmissionPacket toSend = new TransmissionPacket();
        TransmissionPacket toRecieve = new TransmissionPacket();

        toSend.command = TransmissionPacket.Commands.GETINFORMATION;
        toRecieve.command = TransmissionPacket.Commands.INFORMATION;

        testCommand(toSend, toRecieve);

        toSend.dataString = "This does nothing";
        toRecieve.dataString = null;

        testDataString(toSend, toRecieve);
    }

    @Test(timeout = TIMEOUT)
    public void clientGETJOURNEY() throws Exception
    {
        TransmissionPacket toSend = new TransmissionPacket();
        TransmissionPacket toRecieve = new TransmissionPacket();

        toSend.command = TransmissionPacket.Commands.GETJOURNEY;
        toRecieve.command = TransmissionPacket.Commands.JOURNEY;

        testCommand(toSend, toRecieve);

        toSend.dataString = "This does nothing";
        toRecieve.dataString = null;

        testDataString(toSend, toRecieve);
    }

    @Test(timeout = TIMEOUT)
    public void clientLOGOUT() throws Exception
    {
        TransmissionPacket toSend = new TransmissionPacket();
        TransmissionPacket toRecieve = new TransmissionPacket();

        toSend.command = TransmissionPacket.Commands.LOGOUT;
        toRecieve.command = TransmissionPacket.Commands.ACKLOGOUT;

        testCommand(toSend, toRecieve);

        toSend.dataString = "This does nothing";
        toRecieve.dataString = null;

        testDataString(toSend, toRecieve);
    }

    @Test(timeout = TIMEOUT)
    public void clientConnect() throws Exception
    {
        TransmissionPacket toSend = new TransmissionPacket();
        TransmissionPacket toRecieve = new TransmissionPacket();

        toSend.command = TransmissionPacket.Commands.USERCONNECTION;
        toRecieve.command = TransmissionPacket.Commands.ACKNOWLEDGE;
        toSend.dataString = Integer.toString(123456); //User ID
        toRecieve.dataString = Integer.toString(123456);

        testCommand(toSend, toRecieve);

        testDataString(toSend, toRecieve);
    }

    private void testCommand(TransmissionPacket sendMessage, TransmissionPacket expectedReturn) throws Exception
    {
        TransmissionPacket[] returnedMessage = getReturnPacket(sendMessage, expectedReturn);

        for (TransmissionPacket returned : returnedMessage)
        {
            assertEquals(expectedReturn.command, returned.command);
        }
    }

    private void testDataString(TransmissionPacket sendMessage, TransmissionPacket expectedReturn) throws Exception
    {
        TransmissionPacket[] returnedMessage = getReturnPacket(sendMessage, expectedReturn);

        for (TransmissionPacket returned : returnedMessage)
        {
            assertEquals(expectedReturn.dataString, returned.dataString);
        }
    }

    private TransmissionPacket[] getReturnPacket(TransmissionPacket sendMessage, TransmissionPacket expectedReturn) throws Exception
    {
        int numberOfClients = 20;
        SendAndReturnPacketClient[] clients = new SendAndReturnPacketClient[numberOfClients];
        ArrayList<Future<TransmissionPacket>> returnPackets = new ArrayList<>();

        for (int i = 0; i < clients.length; ++i)
        {
            clients[i] = new SendAndReturnPacketClient(sendMessage);
            returnPackets.add(scheduler.submit(clients[i]));
        }
        
        TransmissionPacket[] returnedMessage = new TransmissionPacket[numberOfClients];
        for (int i = 0; i < clients.length; i++)
        {
            returnedMessage[i] = new TransmissionPacket();
            returnedMessage[i] = returnPackets.get(i).get();
        }
        return returnedMessage;
    }
    
    private ArrayList<TransmissionPacket> createUniqueDataClientRequests(TransmissionPacket.Commands command, int amount)
    {
        ArrayList<TransmissionPacket> toSend = new ArrayList<>();
        for (int i = 0; i < amount; ++i)
        {
            TransmissionPacket packet = new TransmissionPacket();
            packet.command = command;
            packet.dataString = Integer.toString(1234 * i);
            toSend.add(packet);
        }
        return toSend;
    }
    
    private ArrayList<Future<TransmissionPacket>> createClientFutureArray(ArrayList<TransmissionPacket> toSend) throws IOException
    {
        ArrayList<Future<TransmissionPacket>> returnPackets = new ArrayList<>();
        
        for (TransmissionPacket packetToSend : toSend)
        {
            SendAndReturnPacketClient client = new SendAndReturnPacketClient(packetToSend);
            returnPackets.add(scheduler.submit(client));
        }
        return returnPackets;
    }

    @Test
    public void testGetUserConnectionWithArrayChecking() throws Exception
    {
        ArrayList<TransmissionPacket> requests = new ArrayList<>();
        ArrayList<SendAndReturnPacketClient> clients = new ArrayList<>();

        int numberOfAttempts = 100;

        ArrayList<Future<TransmissionPacket>> returnPackets = new ArrayList<>();
        for (int i = 0; i < numberOfAttempts; ++i)
        {
            //Create request
            TransmissionPacket request = new TransmissionPacket();
            request.command = TransmissionPacket.Commands.USERCONNECTION;
            request.dataString = Integer.toString(1234 * i);
            requests.add(request);
            
            //Start client
            clients.add(new SendAndReturnPacketClient(request));
            returnPackets.add(scheduler.submit(clients.get(i)));
        }
        
        //Listen to Main server socket
        Socket serverSocket = Server.serverTransmitter.getTestingSocket();
        TransmissionPacket sentToMainServer = MessageUtils.getTransmission(serverSocket);

        for (int i = 0; i < numberOfAttempts; ++i)
        {
            TransmissionPacket toCompare = returnPackets.get(i).get();
            
            // Test return message to connecting client. ACKNOWLEDGE + user ID string.
            assertEquals("Err: client didn't get acknowledge reply" ,TransmissionPacket.Commands.ACKNOWLEDGE, toCompare.command);
            assertEquals("Err: client got incorrect datastring reply" ,requests.get(i).dataString, toCompare.dataString);
        }
        
        //Get user request from main server socket.
        ArrayList<User> users = (ArrayList<User>) sentToMainServer.dataObject;
        
        //FURTHER DEV: add get user functionality. For now, same objects are returned.
        
        //Test add users from server.
        TransmissionPacket sendFromMainServer = new TransmissionPacket();
        sendFromMainServer.command = Commands.RETURNUSERS;
        sendFromMainServer.dataObject = users;
        
        //send reply
        SendAndReturnPacketClient server = new SendAndReturnPacketClient(sendFromMainServer);
        Future<TransmissionPacket> serverReply = scheduler.submit(server);
        
        
        
        //Wait for ACK
        assertEquals("Err: Server reply was not acknowledge" ,TransmissionPacket.Commands.ACKNOWLEDGE, serverReply.get().command);
        
        //Test that users have been added to potentialUsersArray
        for (User user : users)
        {
            assertTrue("Err: Users were not added to potential users array" ,Server.potentialUsers.testIfContainsUser(user));
        }
        
        printUserArrays();
        
        //Try to send the same users again. They should now be moved to Active users array.
        clients = new ArrayList<>();
        returnPackets = new ArrayList<>();
        for (int i = 0; i < numberOfAttempts; ++i)
        {
            //Create request
            TransmissionPacket request = new TransmissionPacket();
            request.command = TransmissionPacket.Commands.USERCONNECTION;
            request.dataString = Integer.toString(1234 * i);
            requests.add(request);
            
            //Start client
            clients.add(new SendAndReturnPacketClient(request));
            returnPackets.add(scheduler.submit((clients.get(i))));
        }
        
        //wait to complete send
        for (Future<TransmissionPacket> packet : returnPackets)
        {
            packet.get();
        }
        
        for (User user : users)
        {
            assertTrue("Err: Users were not added to active users array" ,Server.activeUsers.testIfContainsUser(user));
            assertEquals("Err: users were not removed from potential users array", 0, Server.potentialUsers.getArraySize());
        }
        printUserArrays();
    }
    
    private void printUserArrays()
    {
        LogPrinter.print("----------- Potential Users -----------");
        for (int i = 0; i < Server.potentialUsers.getArraySize(); i++)
        {
            LogPrinter.print("Potential User: " + Server.potentialUsers.getUserByIndex(i).ID);
        }
        
        LogPrinter.print("----------- Active Users -----------");
        for (int i = 0; i < Server.activeUsers.getArraySize(); i++)
        {
            LogPrinter.print("Active User: " + Server.activeUsers.getUserByIndex(i).ID);
        }
        LogPrinter.print("----------------------");
    }
}
