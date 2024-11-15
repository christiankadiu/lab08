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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.lang.System.*;

import it.unibo.deathnote.api.DeathNote;

class TestDeathNote {

    public static final int AMOUNT = -100;

    DeathNote deathNote;
    private static final String nome = "Mario Rossi";
    private static final String nome2 = "Mario Verdi";
    private static final String causa = "heart attack";
    private static final String causa2 = "Karting Accident";
    private static final String details = "dettagli";

    @BeforeEach
    public void SetUp() {
        deathNote = new DeathNoteImpl();
    }

    @Test
    public void Number0AndNegativeRulesDoNotExist() {
        List<Integer> l = new LinkedList<>();
        l.add(0);
        l.add(-1);
        for (Integer integer : l) {
            try {
                deathNote.getRule(integer);
                Assertions.fail();
            } catch (IllegalArgumentException e) {
                assertNotNull(e.getMessage());
                assertFalse(e.getMessage().isBlank());
                assertFalse(e.getMessage().isEmpty());
            }
        }
    }

    @Test
    public void NoNullOrEmptyRule() {
        for (String string : RULES) {
            assertNotNull(string);
        }
    }

    @Test
    public void TestDie() {
        assertFalse(deathNote.isNameWritten(nome));
        deathNote.writeName(nome);
        assertTrue(deathNote.isNameWritten(nome));
        assertFalse(deathNote.isNameWritten(nome2));
        assertFalse(deathNote.isNameWritten(""));
    }

    @Test
    public void testFour() {
        try {
            deathNote.writeDeathCause(causa);
        } catch (IllegalStateException e) {
        }
        deathNote.writeName(nome);
        assertEquals(causa, deathNote.getDeathCause(nome));
        deathNote.writeName(nome2);
        assertTrue(deathNote.writeDeathCause(causa2));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
            assertFalse(e.getMessage().isEmpty());
        }
        assertFalse(deathNote.writeDeathCause(causa));
    }

    @Test
    public void testFive() {
        // assertTrue(deathNote.writeDetails(details));
        try {
            deathNote.writeDeathCause(causa);
        } catch (IllegalStateException e) {
        }
        deathNote.writeName(nome);
        assertEquals("", deathNote.getDeathDetails(nome));
        assertTrue(deathNote.writeDetails("ran for too long"));
        assertEquals("ran for too long", deathNote.getDeathDetails(nome));
        deathNote.writeName(nome2);
        try {
            Thread.sleep(6100);
        } catch (InterruptedException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
            assertFalse(e.getMessage().isEmpty());
        }
        assertFalse(deathNote.writeDetails("nuovi dettagli"));
    }

}