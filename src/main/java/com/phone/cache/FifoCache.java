package com.phone.cache;

import lombok.extern.slf4j.Slf4j;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public abstract class FifoCache<T> {

    private final int capacity;
    private final Map<Long, T> cache;

    protected FifoCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<Long, T>(capacity, 0.75f, false) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Long, T> eldest) {
                boolean shouldRemove = size() > FifoCache.this.capacity;
                if (shouldRemove) {
                    log.info("FIFO Cache: Evicted oldest item. ID: {}", eldest.getKey());
                }
                return shouldRemove;
            }
        };
    }

    public T get(Long id) {
        T value = cache.get(id);
        if (value == null) {
            log.info("FIFO Cache: Item NOT found. ID: {}", id);
        } else {
            log.info("FIFO Cache: Item retrieved. ID: {}", id);
        }
        return value;
    }

    public void put(Long id, T value) {
        cache.put(id, value);
        log.info("FIFO Cache: Item added. ID: {}", id);
    }

    public void remove(Long id) {
        if (cache.remove(id) != null) {
            log.info("FIFO Cache: Removed item. ID: {}", id);
        }
    }

    public void clear() {
        cache.clear();
        log.info("FIFO Cache: Cleared all items.");
    }
}
