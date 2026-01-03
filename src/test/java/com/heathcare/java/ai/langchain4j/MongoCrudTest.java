package com.heathcare.java.ai.langchain4j;

import com.healthcare.java.ai.langchain4j.VitaApp;
import com.healthcare.java.ai.langchain4j.bean.ChatMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@SpringBootTest(classes = VitaApp.class)
public class MongoCrudTest {
    @Autowired
    private MongoTemplate mongoTemplate;

//    @Test
//    public void testInsert() {
//        mongoTemplate.insert(new ChatMessages(1L, "chat history"));
//    }

    @Test
    public void testInsert2() {
        ChatMessages m = new ChatMessages();
        m.setContent("wababala");
        mongoTemplate.insert(m);
    }

    @Test
    public void testFindById() {
        ChatMessages chatMessages = mongoTemplate.findById("6958a34250195225ec5c2338", ChatMessages.class);
        System.out.println(chatMessages);
    }

    @Test
    public void testUpdate() {
        Criteria criteria = Criteria.where("content").is("wababala");
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("content", "new waladabuda");

        mongoTemplate.upsert(query, update, ChatMessages.class);
    }

    @Test
    public void testDelete() {
        Criteria criteria = Criteria.where("content").is("wababala");
        Query query = new Query(criteria);
        mongoTemplate.remove(query, ChatMessages.class);
    }
}
