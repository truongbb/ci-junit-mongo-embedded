package vn.com.ntqsolution.junit.repository.user;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.com.ntqsolution.junit.entity.UserEntity;
import vn.com.ntqsolution.junit.util.DateTimeUtil;

import java.util.List;

@Slf4j
@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRepositoryImpl implements UserRepository {

  @Autowired
  MongoClient mongoClient;

  @Override
  public List<UserEntity> findByAgeGte(int age) {
    String birMax = DateTimeUtil.calculateDateFromAge(age);
    BasicDBObject findObj = new BasicDBObject("$lte", birMax);
    MongoCursor<Document> cursor = mongoClient.getDatabase("userdb").getCollection("user").find(findObj).cursor();
    return null;
  }
}
