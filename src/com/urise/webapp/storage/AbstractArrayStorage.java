package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.printf("Resume: '%s' not exist%n", resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.printf("Resume: '%s' not exist%n", uuid);
            return null;
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            System.out.printf("Resume: '%s' already exist%n", resume.getUuid());
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            insert(resume, index);
            size++;
        }
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.printf("Resume: '%s' not exist%n", uuid);
        } else {
            deleteFromIndex(index);
            storage[size - 1] = null;
            size--;
        }
    }

    protected abstract void deleteFromIndex(int index);

    protected abstract void insert(Resume resume, int index);

    protected abstract int getIndex(String uuid);
}