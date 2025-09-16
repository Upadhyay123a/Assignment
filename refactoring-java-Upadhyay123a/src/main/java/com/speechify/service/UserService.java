package com.speechify.service;

import java.util.List;

import com.speechify.cache.CacheLimits;
import com.speechify.cache.LRUCache;
import com.speechify.model.User;
import com.speechify.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;
    private final LRUCache<String, User> userCache;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userCache = new LRUCache<>(CacheLimits.USER_CACHE_SIZE);
    }

    public User getUserById(String id) {
        if (userCache.containsKey(id)) {
            return userCache.get(id);
        }
        User user = userRepository.getById(id);
        if (user != null) {
            userCache.put(id, user);
        }
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
        userCache.put(user.getId(), user);
    }

    public void updateUser(User user) {
        saveUser(user);
    }

    public void clearCache() {
        userCache.clear();
    }
}
