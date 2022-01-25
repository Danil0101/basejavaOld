package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveAbstract(Resume resume, int index) {
        storage[size++] = resume;
    }

    @Override
    protected void deleteAbstract(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(uuid, storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
