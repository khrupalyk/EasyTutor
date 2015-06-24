package com.easytutor.utils;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by root on 20.06.15.
 */

public class TemporaryTestStorage {

    private Set<String> tempTestIds;

    void init() {
        tempTestIds = new TreeSet<String>();
    }

    public void putTestId(UUID uuid) {
        tempTestIds.add(uuid.toString());
    }

    public boolean isIdExist(UUID uuid) {
        return tempTestIds.contains(uuid.toString());
    }
    public void remove(UUID uuid) {
        tempTestIds.remove(uuid.toString());
    }

}

