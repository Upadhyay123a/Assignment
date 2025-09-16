package com.speechify.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.speechify.model.User;

public class UserRepository {
    private static final String DB_PATH = "src/main/java/com/speechify/db.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<String, User> users = new HashMap<>();

    public UserRepository() {
        loadUsers();
    }

    private void loadUsers() {
        try {
            JsonNode root = mapper.readTree(new File(DB_PATH));
            JsonNode usersNode = root.get("users");
            if (usersNode != null && usersNode.isArray()) {
                for (JsonNode userNode : usersNode) {
                    User user = mapper.treeToValue(userNode, User.class);
                    users.put(user.getId(), user);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load users from db.json", e);
        }
    }

    public User getById(String id) {
        return users.get(id);
    }

    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    public void save(User user) {
        users.put(user.getId(), user);
        persist();
    }

    private void persist() {
        try {
            // Reconstruct the JSON structure with existing clients + users
            Map<String, Object> dbContent = new HashMap<>();
            dbContent.put("clients", new ClientRepository().getAll());
            dbContent.put("users", users.values());

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(DB_PATH), dbContent);
        } catch (IOException e) {
            throw new RuntimeException("Failed to persist users to db.json", e);
        }
    }
}
