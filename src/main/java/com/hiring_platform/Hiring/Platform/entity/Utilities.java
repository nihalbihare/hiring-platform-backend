package com.hiring_platform.Hiring.Platform.entity;

import com.hiring_platform.Hiring.Platform.exception.JobPortalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class Utilities  {
    private static MongoOperations mongoOperations;

    @Autowired
 private void setSequence(MongoOperations mongoOperations) {
     Utilities.mongoOperations = mongoOperations;
    }
  public static Long getNextSequence(String key) throws JobPortalException {
      Query query = new Query(Criteria.where("_id").is(key)); // finding the collection where id is the key
      Update update =new Update();
      update.inc("seq" ,1); // now incrementing the sequence by one updation
      FindAndModifyOptions options = new FindAndModifyOptions();
      options.returnNew(true);
      //These options tell Mongo to:
      //Return the updated document (not the old one).
      //This way, you get the new incremented value directly.
      Sequence seq = mongoOperations.findAndModify(query,update,options,Sequence.class);
//      This performs the atomic find-and-modify operation:
//Finds the document with given _id
//Increments seq by 1
//Returns the updated document as a Sequence object
      if(seq == null) throw new JobPortalException("unable to get sequence id for the key :"+ key);
      //If no document was found (i.e., the sequence doesn’t exist), throw a custom exception.

      return seq.getSeq();
  }
  public static String OtpGeneration(){
        StringBuilder otp = new StringBuilder();
      SecureRandom random = new SecureRandom();
      for(int i = 0; i < 6; i++) otp.append(random.nextInt(10));
      return otp.toString();
  }

}
