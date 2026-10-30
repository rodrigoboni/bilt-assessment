package com.rentrewards.challenge.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProcessedEventStoreTest {

    @Test
    void claimSucceedsOncePerEventId() {
        ProcessedEventStore store = new ProcessedEventStore();

        assertTrue(store.claim("evt-1"));
        assertFalse(store.claim("evt-1"));
        assertTrue(store.claim("evt-2"));
    }

    @Test
    void remembersOlderIdsNotJustTheLastOne() {
        ProcessedEventStore store = new ProcessedEventStore();

        assertTrue(store.claim("evt-A"));
        assertTrue(store.claim("evt-B"));
        assertFalse(store.claim("evt-A"));
    }
}
