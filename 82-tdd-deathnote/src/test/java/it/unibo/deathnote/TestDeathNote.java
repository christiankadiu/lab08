package it.unibo.deathnote;

import it.unibo.deathnote.impl.DeathNoteImpl;
import it.unibo.deathnote.api.DeathNote;
import static it.unibo.deathnote.api.DeathNote.RULES;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.deathnote.api.DeathNote;

class TestDeathNote {

    public static final int AMOUNT = -100;

    DeathNote deathNode;

    @BeforeEach
    public void SetUp() {
        deathNode = new DeathNoteImpl();
    }

    @Test
    public void Number0AndNegativeRulesDoNotExist() {
        List<Integer> l = new LinkedList<>();
        l.add(0);
        l.add(-1);
        // l.add(RULES.size() + 1);
        for (Integer integer : l) {
            try {
                deathNode.getRule(integer);
                Assertions.fail();
            } catch (IllegalArgumentException e) {
                assertNotNull(e.getMessage());
                assertFalse(e.getMessage().isBlank());
                assertFalse(e.getMessage().isEmpty());
            }
        }
    }

}