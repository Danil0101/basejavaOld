package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        insertElementByIndex(resume, index);
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getElementByIndex(index);
    }

    public final void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        isStorageOverflow(uuid);
        insertElement(resume, index);
        increaseArraySize();
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        fillDeletedElement(index);
        decreaseArraySize();
    }

    protected void increaseArraySize() {}

    protected void decreaseArraySize() {}

    protected void isStorageOverflow(String uuid) {}

    protected abstract void fillDeletedElement(int index);

    protected abstract Resume getElementByIndex(int index);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void insertElementByIndex(Resume resume, int index);

    protected abstract int getIndex(String uuid);
}
