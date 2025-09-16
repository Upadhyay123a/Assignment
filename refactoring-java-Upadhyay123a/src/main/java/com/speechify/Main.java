package com.speechify;

import com.speechify.model.User;
import com.speechify.repository.UserRepository;
import com.speechify.service.UserService;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository); 

        String userId = "PgW92LVLS1EdVBpXFt1nv"; // John Doe from db.json

        System.out.println("=== First Fetch (Cache Miss) ===");
        User user1 = userService.getUserById(userId);
        System.out.println(user1);

        System.out.println("\n=== Second Fetch (Cache Hit) ===");
        User user2 = userService.getUserById(userId);
        System.out.println(user2);

        System.out.println("\n=== Update User (Firstname -> Johnny) ===");
        user1.setFirstname("Johnny");
        userService.updateUser(user1);

        System.out.println("\n=== Fetch After Update (Cache + DB Consistent) ===");
        User user3 = userService.getUserById(userId);
        System.out.println(user3);
    }
}
