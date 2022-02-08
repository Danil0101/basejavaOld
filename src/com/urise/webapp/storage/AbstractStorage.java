package com.urise.webapp.storage;

import com.urise.webapp.exeption.*;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        updateElement(resume, index);
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
        insertElement(resume, index);
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        fillDeletedElement(index);
        decreaseArraySize();
    }

    protected void decreaseArraySize() {
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract Resume getElementByIndex(int index);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void updateElement(Resume resume, int index);

    protected abstract int getIndex(String uuid);
}
