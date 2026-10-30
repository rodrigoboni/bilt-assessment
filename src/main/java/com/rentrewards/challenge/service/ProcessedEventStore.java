package com.rentrewards.challenge.service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Tracks which webhook events have already been processed, so that the
 * RewardsEngine can ignore duplicate deliveries from the payment processor.
 *
 * <p>Uses an atomic claim so concurrent workers cannot both treat the same
 * {@code eventId} as new.
 */
public class ProcessedEventStore {

    private final Set<String> processedEventIds = ConcurrentHashMap.newKeySet();

    /**
     * Atomically claims {@code eventId} for processing.
     *
     * @return true if this is the first claim for the ID; false if already claimed
     */
    public boolean claim(String eventId) {
        return processedEventIds.add(eventId);
    }
}
