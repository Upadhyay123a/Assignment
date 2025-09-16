package com.speechify.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.speechify.model.Client;

public class ClientRepository {
    private static final String DB_PATH = "src/main/java/com/speechify/db.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<String, Client> clients = new HashMap<>();

    public ClientRepository() {
        loadClients();
    }

    private void loadClients() {
        try {
            JsonNode root = mapper.readTree(new File(DB_PATH));
            JsonNode clientsNode = root.get("clients");
            if (clientsNode != null && clientsNode.isArray()) {
                for (JsonNode clientNode : clientsNode) {
                    Client client = mapper.treeToValue(clientNode, Client.class);
                    clients.put(client.getId(), client);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load clients from db.json", e);
        }
    }

    public Client getById(String id) {
        return clients.get(id);
    }

    public List<Client> getAll() {
        return new ArrayList<>(clients.values());
    }
}
