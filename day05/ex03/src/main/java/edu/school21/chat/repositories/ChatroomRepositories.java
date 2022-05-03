package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;

import java.util.Optional;

public interface ChatroomRepositories {
    Optional<Chatroom> findById(Long id);
}
