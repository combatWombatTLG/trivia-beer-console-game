package com.combatWombat.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HostTest {
    @Before
    public void setUp() throws Exception {
        Host host = new Host(Category.SPORTS);
        Player player = new Player("nick", 30);
    }

    @Test
    public void checkIfQuestionUsed_shouldReturn() {

    }
}