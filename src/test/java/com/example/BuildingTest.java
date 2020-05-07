package com.example;

import com.controller.HomeServlet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BuildingTest {
    @Test
    public void testBuild() {
        HomeServlet homeServlet=new HomeServlet();
        homeServlet.init();
        Assertions.assertSame(Building.getInstance(),homeServlet.getBuilding());
    }
}
