package com.cms.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {
    private static final AtomicInteger counter = new AtomicInteger(300);

    public Long generateNextSequence(String sequenceName) {
        long nextValue = counter.getAndIncrement();
        return nextValue;
    }
    
}