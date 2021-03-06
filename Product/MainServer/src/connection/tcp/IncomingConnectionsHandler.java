/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.tcp;

import connection.tcp.common.ClientConnection;
import connection.tcp.common.TCPConnection;
import connection.tcp.processing.ConnectionProcessorRequest;
import execute.Server;
import java.io.IOException;

/**
 *
 * @author JamesFoxes
 */
public final class IncomingConnectionsHandler extends TCPConnection
{
    public IncomingConnectionsHandler() throws IOException
    {
        super();
    }

    @Override
    public void startProcessing()
    {
        while (keepProcessing)
        {
            try
            {
                ClientConnection clientConnection = connectionManager.awaitClient();
                ConnectionProcessorRequest requestProcessor = new ConnectionProcessorRequest(clientConnection);
                Server.threadPool.schedule(requestProcessor);
            } catch (IOException e)
            {
            }
        }
        stopProcessing();
    }
}
