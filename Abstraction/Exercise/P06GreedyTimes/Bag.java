package com.company.WorkingWithAbstraction.Exercise.P06GreedyTimes;

import java.util.HashMap;
import java.util.Map;

public class Bag {
    private long capacity;
    private long currWeight;
    private long gold;
    private boolean goldIsAdded;

    private Map<String, Long> gems;
    private long totalGems;
    private Map<String, Long> cash;
    private long totalCash;

    public Bag(long capacity) {
        this.capacity = capacity;
        this.currWeight = 0;
        this.gold = 0;
        this.gems = new HashMap<>();
        this.totalGems = 0;
        this.cash = new HashMap<>();
        this.totalCash = 0;
        this.goldIsAdded = false;
    }

    public void addCash(String item, long quantity) {
        if (this.hasFreeCapacity(quantity) && this.totalGems >= this.totalCash + quantity) {
            if (!this.cash.containsKey(item)) {
                this.cash.put(item, quantity);
            } else {
                this.cash.put(item, this.cash.get(item) + quantity);
            }
            this.totalCash += quantity;
            this.currWeight += quantity;
        }
    }

    private boolean hasFreeCapacity(long weight) {
        return this.currWeight + weight <= this.capacity;
    }

    public void addGems(String item, long quantity) {
        if (this.hasFreeCapacity(quantity) && this.gold >= this.totalGems + quantity) {
            if (!this.gems.containsKey(item)) {
                this.gems.put(item, quantity);
            } else {
                this.gems.put(item, this.gems.get(item) + quantity);
            }
            this.totalGems += quantity;
            this.currWeight += quantity;
        }
    }

    public void addGold(long quantity) {
        if (this.hasFreeCapacity(quantity)) {
            this.gold += quantity;
            this.currWeight += quantity;
            this.goldIsAdded = true;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (goldIsAdded) {
            sb.append(String.format("<Gold> $%d", this.gold)).append(System.lineSeparator());
            sb.append(String.format("##Gold - %d", this.gold)).append(System.lineSeparator());
        }

        if (this.gems.size() > 0) {
            sb.append(String.format("<Gem> $%d", this.totalGems)).append(System.lineSeparator());
            this.gems.entrySet().stream().sorted((f, s) -> {
                int result = s.getKey().compareTo(f.getKey());

                if (result == 0) {
                    result = f.getValue().compareTo(s.getValue());
                }
                return result;
            }).forEach(e -> {
                sb.append(String.format("##%s - %d", e.getKey(), e.getValue()));
                sb.append(System.lineSeparator());
            });
        }

        if (this.cash.size() > 0) {
            sb.append(String.format("<Cash> $%d", this.totalCash)).append(System.lineSeparator());
            this.cash.entrySet().stream().sorted((f, s) -> {
                int result = s.getKey().compareTo(f.getKey());

                if (result == 0) {
                    result = f.getValue().compareTo(s.getValue());
                }
                return result;
            }).forEach(e -> {
                sb.append(String.format("##%s - %d", e.getKey(), e.getValue()));
                sb.append(System.lineSeparator());
            });
        }
        return sb.toString();
    }
}
