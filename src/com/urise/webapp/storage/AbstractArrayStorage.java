package com.urise.webapp.storage;

import com.urise.webapp.exeption.*;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected Resume getElementByIndex(int index) {
        return storage[index];
    }

    @Override
    protected void updateElement(Resume resume, int index) {
        storage[index] = resume;
        size++;
    }

    protected void isStorageOverflow(String uuid) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", uuid);
        }
    }

    @Override
    protected void decreaseArraySize() {
        storage[size--] = null;
    }
}
