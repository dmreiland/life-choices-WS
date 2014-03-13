package edu.sjsu.cmpe283.lifechoices.config;

import com.mongodb.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

/**
 * User: maksim
 * Date: 3/12/14 - 5:52 PM
 */
@Configuration
@EnableMongoRepositories
public class DBConfig extends AbstractMongoConfiguration {

    private static Log logger = LogFactory.getLog(DBConfig.class);


    String mongoHost = "oceanic.mongohq.com";
    int mongoPort = 10032;

    String userName = "lifechoicesuser";
    String password = "zzzzzz";
    String mongoDb = "ifechoices_mdb";


    @Override
    protected String getDatabaseName() {
        return mongoDb;
    }

    @Override
    public Mongo mongo() throws Exception {
        logger.debug("Connecting to MongoDB: [" + userName + ":" + password + "@" + mongoHost + ":" + mongoPort + "/" + mongoDb);

        MongoCredential credential = MongoCredential.createMongoCRCredential(userName, mongoDb, password.toCharArray());

        MongoClient mongoClient = new MongoClient(new ServerAddress(mongoHost, mongoPort), Arrays.asList(credential));

        logger.debug("Connected to MongdoDB: " + mongoClient.debugString());
        return mongoClient;
    }
}
