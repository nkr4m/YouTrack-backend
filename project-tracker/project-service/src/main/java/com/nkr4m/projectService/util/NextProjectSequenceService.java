package com.nkr4m.projectService.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


@Service
public class NextProjectSequenceService {
	
	@Autowired private MongoOperations mongo;

    public int getNextSequence(String seqName)
    {
    	CustomProjectSequence counter = mongo.findAndModify(
            query(where("_id").is(seqName)),
            new Update().inc("seq",1),
            options().returnNew(true).upsert(true),
            CustomProjectSequence.class);
        return counter.getSeq();
    }

}
