package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListStorage extends AbstractStorage {
    protected final List<Resume> storage = new ArrayList<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    protected void fillDeletedElement(int index) {
        storage.remove(index);
    }

    @Override
    protected Resume getElementByIndex(int index) {
        return storage.get(index);
    }

    @Override
    protected void insertElement(Resume resume, int index) {
        storage.add(resume);
    }

    @Override
    protected void insertElementByIndex(Resume resume, int index) {
        storage.set(index, resume);
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (Objects.equals(uuid, storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
