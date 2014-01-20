package mc.messages.publisher;


import mc.messages.TextMessage;
import org.apache.log4j.Logger;
import org.wso2.carbon.databridge.agent.thrift.Agent;
import org.wso2.carbon.databridge.agent.thrift.DataPublisher;
import org.wso2.carbon.databridge.agent.thrift.conf.AgentConfiguration;
import org.wso2.carbon.databridge.agent.thrift.exception.AgentException;
import org.wso2.carbon.databridge.commons.Event;
import org.wso2.carbon.databridge.commons.exception.*;

import java.net.MalformedURLException;

public class MessagePublisher {
    private static Logger logger = Logger.getLogger(MessagePublisher.class);
    private static final String MESSAGE_STREAM = "app.mc.messages";
    private static final String VERSION = "1.0.0";
    private String host="localhost";
    private int port=7611;
    private int events=20;
    private String streamId;
    private DataPublisher dataPublisher;

    public MessagePublisher(){
        KeyStoreUtil.setTrustStoreParams();

        AgentConfiguration agentConfiguration = new AgentConfiguration();
        Agent agent = new Agent(agentConfiguration);
        //create data publisher
        try {
            dataPublisher = new DataPublisher("tcp://"+host+":"+port, "admin", "admin", agent);
        } catch (MalformedURLException e) {
            logger.error("Error in creating datapublisher",e);
        } catch (AgentException e) {
            logger.error("Error in creating datapublisher", e);
        } catch (AuthenticationException e) {
            logger.error("Error in creating datapublisher", e);
        } catch (TransportException e) {
            logger.error("Error in creating datapublisher", e);
        }
        streamId = null;

        try {
            streamId = dataPublisher.findStream(MESSAGE_STREAM, VERSION);
            System.out.println("Stream already defined");

        } catch (NoStreamDefinitionExistException e) {
            try {
                streamId = dataPublisher.defineStream("{" +
                        "  'name':'" + MESSAGE_STREAM + "'," +
                        "  'version':'" + VERSION + "'," +
                        "  'nickName': 'Phone_Retail_Shop'," +
                        "  'description': 'Phone Sales'," +
                        "  'payloadData':[" +
                        "          {'name':'id','type':'LONG'}," +
                        "          {'name':'message','type':'STRING'}" +
                        "  ]" +
                        "}");
            } catch (AgentException e1) {
                logger.error("Error in creating stream definition", e);
            } catch (MalformedStreamDefinitionException e1) {
                logger.error("Error in creating stream definition", e);
            } catch (StreamDefinitionException e1) {
                logger.error("Error in creating stream definition", e);
            } catch (DifferentStreamDefinitionAlreadyDefinedException e1) {
                logger.error("Error in creating stream definition", e);
            }

        } catch (StreamDefinitionException e) {
            logger.error("Error in finding stream definition", e);
        } catch (AgentException e) {
            logger.error("Error in finding stream definition", e);
        }
    }

    public void publish(TextMessage message){
        Event eventOne = new Event(streamId, System.currentTimeMillis(), null, null,
                new Object[]{message.getId(), message.getMessage()});
        try {
            dataPublisher.publish(eventOne);
        } catch (AgentException e) {
            logger.error("Error in publishing message "+message.getId(), e);
        }
    }

    public void hault(){
        dataPublisher.stop();
    }
}
