/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Aimo
 */
public interface RMITrainInterface
{
    
    public ArrayList<RMIPassengerInterface> getRMITrainPassengers() throws RemoteException;
    
}
