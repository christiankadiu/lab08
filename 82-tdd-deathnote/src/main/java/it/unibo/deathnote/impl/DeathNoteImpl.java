package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import it.unibo.deathnote.impl.*;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote {

    private String lastName;
    Map<String, persona> mappa;
    long current;

    public DeathNoteImpl() {
        mappa = new HashMap<>();
    }

    @Override
    public String getRule(int ruleNumber) {
        if (ruleNumber <= 0 || ruleNumber > RULES.size()) {
            throw new IllegalArgumentException("input sbagliato");
        }
        int i = 1;
        for (String string : RULES) {
            if (i == ruleNumber) {
                return string;
            }
            i++;
        }
        throw new NoSuchElementException();
    }

    @Override
    public void writeName(String name) {
        Objects.requireNonNull(name);
        this.lastName = name;
        mappa.put(name, new persona());
        current = System.currentTimeMillis();
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if (cause == null || mappa.isEmpty()) {
            throw new IllegalStateException();
        }
        mappa.get(lastName).deathCause = cause;
        long t2 = System.currentTimeMillis();
        current = t2 - current;
        if (current <= 40) {
            return true;
        }
        return false;
    }

    @Override
    public boolean writeDetails(String details) {
        if (details == null || mappa.isEmpty()) {
            throw new IllegalStateException();
        }
        mappa.get(lastName).deathDetails = details;
        long t2 = System.currentTimeMillis();
        if (t2 - current <= 6040) {
            return true;
        }
        return false;
    }

    @Override
    public String getDeathCause(String name) {
        if (!mappa.containsKey(name)) {
            throw new IllegalArgumentException("non c'è questo nome");
        }
        if (isSpecified(mappa.get(name).deathCause)) {
            return mappa.get(name).deathCause;
        } else {
            return "heart attack";
        }
    }

    @Override
    public String getDeathDetails(String name) {
        if (!mappa.containsKey(name)) {
            throw new IllegalArgumentException("non c'è questo nome");
        }
        if (isSpecified(mappa.get(name).deathDetails)) {
            return mappa.get(name).deathDetails;
        } else {
            return "";
        }
    }

    @Override
    public boolean isNameWritten(String name) {
        for (String s : mappa.keySet()) {
            if (s.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSpecified(String s) {
        if (s != null && !s.isEmpty()) {
            return true;
        }
        return false;
    }
}
