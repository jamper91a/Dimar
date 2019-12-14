package com.sbi.dimar.visitaoficialarribooffline.app.utilities.network;

/**
 * Created by Jenny Galindo
 * <p>
 * The NetworkChangeReceiver class defines the method used to change the state of network
 */
public interface NetworkListener {
    void onNetworkStateChanges(boolean status);
}
