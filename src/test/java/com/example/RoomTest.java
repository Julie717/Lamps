package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoomTest {
    @Test
    public void testRoom() {
        Room room=new Room("My room","Беларусь");
        String expected = "Беларусь";
        Assertions.assertEquals(expected, room.getCountry());
    }
}

