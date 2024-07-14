package edu.ezip.ing1.pds.client;

import java.io.IOException;
import java.util.UUID;

import edu.ezip.ing1.pds.business.dto.Recipes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import edu.ezip.ing1.pds.business.dto.Clients;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

public class SelectRecipe {

    private final static String LoggingLabel = "S e l e c t - R e c i p e";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";

    public static Recipes getValue(String requestOrder, String requestConditions) {
        Recipes recettes = new Recipes();
        try {
            final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);

            logger.debug("Load Network config file : {}", networkConfig.toString());
            int birthdate = 0;
            final ObjectMapper objectMapper = new ObjectMapper();

            final String requestId = UUID.randomUUID().toString();
            final Request request = new Request();
            request.setRequestId(requestId);
            request.setRequestOrder(requestOrder);
            request.setRequestContent(requestConditions);

            logger.info("new request : " + request.toString());

            objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
            final byte[] requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);

            SelectAllClientRequest clientRequest = new SelectAllClientRequest(
                    networkConfig, birthdate++, request, requestConditions, requestBytes);
            clientRequest.join();

            recettes = objectMapper.convertValue(clientRequest.getResult(), Recipes.class);
            logger.info("data requested : " + recettes.toString());
        }
        catch (IOException | InterruptedException ioe) {
            logger.warn(ioe.getMessage());
        }
        return recettes;
    }

    public static void main(String[] args) {
        Recipes recettes = SelectRecipe.getValue("SELECT_SPECIFIC_RECIPES_NOT_BELOW", String.valueOf(2200));
        System.out.println(recettes);
    }
}
