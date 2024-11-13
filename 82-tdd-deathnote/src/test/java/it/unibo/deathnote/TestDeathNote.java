package it.unibo.deathnote;

import it.unibo.deathnote.impl.DeathNoteImpl;

import org.junit.jupiter.api.BeforeEach;

import it.unibo.deathnote.api.DeathNote;

class TestDeathNote {

    DeathNote deathNode;

    @BeforeEach
    public void SetUp() {
        deathNode = new DeathNoteImpl();
    }
}