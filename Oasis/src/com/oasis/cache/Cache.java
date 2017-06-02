package com.oasis.cache;

import java.util.HashMap;

public abstract class Cache<T> {
    private final HashMap<Integer, T> itemHashMap = new HashMap<Integer, T>();
    private boolean itemListChanged = true;

    public HashMap<Integer, T> getItemHashMap() {
        if (isItemListChanged()) {
            updateItemList();
        }

        return itemHashMap;
    }

    public boolean isItemListChanged() {
        return itemListChanged;
    }

    public void setItemListChanged(boolean itemListChanged) {
        this.itemListChanged = itemListChanged;
    }

    private void updateItemList() {
        itemHashMap.clear();
        itemAdder(itemHashMap);
        setItemListChanged(false);
    }

    public abstract void itemAdder(HashMap<Integer, T> itemHashMap);
}
