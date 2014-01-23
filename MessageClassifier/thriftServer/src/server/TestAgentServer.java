/**
 *
 * Copyright (c) WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package server;

import org.apache.log4j.Logger;
import org.wso2.carbon.databridge.commons.Credentials;
import org.wso2.carbon.databridge.commons.Event;
import org.wso2.carbon.databridge.commons.StreamDefinition;
import org.wso2.carbon.databridge.commons.thrift.utils.HostAddressFinder;
import org.wso2.carbon.databridge.core.AgentCallback;
import org.wso2.carbon.databridge.core.DataBridge;
import org.wso2.carbon.databridge.core.definitionstore.InMemoryStreamDefinitionStore;
import org.wso2.carbon.databridge.core.exception.DataBridgeException;
import org.wso2.carbon.databridge.core.internal.authentication.AuthenticationHandler;
import org.wso2.carbon.databridge.receiver.thrift.internal.ThriftDataReceiver;

import java.net.SocketException;
import java.util.List;

public class TestAgentServer {
    Logger log = Logger.getLogger(TestAgentServer.class);
    ThriftDataReceiver thriftDataReceiver;
    static final TestAgentServer testServer = new TestAgentServer();
    public static void main(String[] args) throws DataBridgeException {
        testServer.start(7661);
        synchronized (testServer) {
            try {
                testServer.wait();
            } catch (InterruptedException ignored) {

            }
        }
    }

    public void start(int receiverPort) throws DataBridgeException {
        KeyStoreUtil.setKeyStoreParams();
        DataBridge databridge = new DataBridge(new AuthenticationHandler() {
            @Override
            public boolean authenticate(String userName,
                                        String password) {
                return true;// always authenticate to true

            }
        }, new InMemoryStreamDefinitionStore());

        thriftDataReceiver = new ThriftDataReceiver(receiverPort, databridge);

        databridge.subscribe(new AgentCallback() {
            int totalSize = 0;

            public void definedStream(StreamDefinition streamDefinition,
                                      Credentials credentials) {
                log.info("StreamDefinition " + streamDefinition);
            }
               public void removeStream(StreamDefinition streamDefinition,
                                     Credentials credentials) {
                log.info("Removed StreamDefinition " + streamDefinition);
            }
            @Override
            public void receive(List<Event> eventList, Credentials credentials) {
           
		for(Event event:eventList){
		String id = (String)event.getPayloadData()[0];
		String message = (String)event.getPayloadData()[1];
		System.out.println(id);
		System.out.println(message);
		}               
            }

        });

        try {
            String address = HostAddressFinder.findAddress("localhost");
            log.info("Thrift test Server starting on " + address);
            thriftDataReceiver.start(address);
            log.info("Thrift test Server Started");
        } catch (SocketException e) {
            log.error("Thrift test Server not started !", e);
        }
    }

    public void stop() {
        thriftDataReceiver.stop();
        log.info("Thrift test Server Stopped");
    }
}
