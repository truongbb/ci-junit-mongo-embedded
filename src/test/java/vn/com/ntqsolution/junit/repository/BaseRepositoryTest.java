package vn.com.ntqsolution.junit.repository;

import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.data.mongodb.core.MongoTemplate;

class BaseRepositoryTest {

  private MongodExecutable mongodExecutable;
  MongoTemplate mongoTemplate;

  @AfterEach
  void stopEmbeddedMongo() {
    System.out.println("STOP EMBEDDED MONGODB");
    mongodExecutable.stop();
  }

  @BeforeEach
  void startEmbeddedMongo() throws Exception {
    System.out.println("START EMBEDDED MONGODB");
    String ip = "localhost";
    int port = 27017;

    IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.V3_6)
      .net(new Net(ip, port, Network.localhostIsIPv6()))
      .build();

    MongodStarter starter = MongodStarter.getDefaultInstance();
    mongodExecutable = starter.prepare(mongodConfig);
    mongodExecutable.start();
    mongoTemplate = new MongoTemplate(new MongoClient(ip, port), "userdb");
  }
}
