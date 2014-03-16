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





    @Override
    protected String getDatabaseName() {
        return Props.mongoDb;
    }

    @Override
    public Mongo mongo() throws Exception {
        logger.debug("Connecting to MongoDB: [" + Props.userName + ":" + Props.password + "@" + Props.mongoHost + ":" + Props.mongoPort + "/" + Props.mongoDb);

        MongoCredential credential = MongoCredential.createMongoCRCredential(Props.userName, Props.mongoDb, Props.password.toCharArray());

        MongoClient mongoClient = new MongoClient(new ServerAddress(Props.mongoHost, Props.mongoPort), Arrays.asList(credential));

        logger.debug("Connected to MongdoDB: " + mongoClient.debugString());
        return mongoClient;
    }
}
