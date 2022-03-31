package jslozano.thesis.repositories;

import jslozano.thesis.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface MessageRepository extends CrudRepository<Message, Long> {
    Set<Message> findByType(String type);
}
