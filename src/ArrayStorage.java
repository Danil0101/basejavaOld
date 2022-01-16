import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (get(r.uuid) == null) {
            storage[size++] = r;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(uuid, storage[i].uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int searchIndex = -1;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(uuid, storage[i].uuid)) {
                searchIndex = i;
                break;
            }
        }
        if (searchIndex == -1) {
            return;
        }
        if (searchIndex == size - 1 || searchIndex == storage.length - 1) {
            storage[searchIndex] = null;
        } else {
            System.arraycopy(storage, searchIndex + 1, storage,
                    searchIndex, size - searchIndex - 1);
        }
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResume = new Resume[size];
        System.arraycopy(storage, 0, allResume, 0, size);
        return allResume;
    }

    int size() {
        return size;
    }
}
