package com.profectusweb.ecommerce.AMQPMessages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.profectusweb.ecommerce.entities.database.DatabaseEntity;
import com.profectusweb.ecommerce.entities.elasticsearch.ElasticSearchEntity;
import com.profectusweb.ecommerce.repositories.database.BaseRepository;
import com.profectusweb.ecommerce.repositories.database.UsersRepository;
import com.profectusweb.ecommerce.repositories.elasticsearch.BaseElasticRepository;
import com.profectusweb.ecommerce.repositories.elasticsearch.UsersElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.concurrent.CountDownLatch;

@Component
public class SaveSubscription {

    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    UsersElasticRepository usersElasticRepository;

    @Autowired
    UsersRepository usersRepository;


    public void subscribe(byte[] message) {
        try {
            String value = new String(message);
            ObjectMapper objectMapper = new ObjectMapper();

            SaveMessage saveMessage = objectMapper.readValue(value, SaveMessage.class);

            System.out.println("Received <" + saveMessage + ">");

            saveEntityIntoElasticsearch(saveMessage);

            latch.countDown();

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    private void saveEntityIntoElasticsearch(SaveMessage saveMessage) throws Throwable {

        BaseRepository repository = chooseDatabaseRepository(saveMessage);

        BigInteger id = saveMessage.getId();

        DatabaseEntity entity = (DatabaseEntity) repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Entity not found for id : %s", id)
                ));

        BaseElasticRepository elasticRepository = chooseElasticRepository(saveMessage);

        Object elasticEntity = elasticRepository.findOneByDatabaseId(id);

        if (elasticEntity != null) {
            elasticRepository.deleteByDatabaseId(id);
        }

        ElasticSearchEntity elasticSearchEntity = entity.toElasticEntity();

        elasticRepository.save(elasticSearchEntity);

    }

    private BaseElasticRepository chooseElasticRepository(SaveMessage saveMessage) {

        return switch (saveMessage.getEntity()) {
            case "users" -> usersElasticRepository;
            default -> throw new IllegalStateException("Unexpected value: " + saveMessage.getEntity());
        };
    }

    private BaseRepository chooseDatabaseRepository(SaveMessage saveMessage) {

        return switch (saveMessage.getEntity()) {
            case "users" -> usersRepository;
            default -> throw new IllegalStateException("Unexpected value: " + saveMessage.getEntity());
        };
    }

}
