package ru.atom.cache;



import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * What about Map?
 */
public class ContactListCache extends AbstractCache<Person, List<? extends Person>> {
    private Map<Person, List<? extends Person>> hashMap = new ConcurrentHashMap<>();

    public ContactListCache(int capacity) {
        super(capacity);
    }

    @Override
    public boolean put(Person person, List<? extends Person> people) {
        if(hashMap.size() == capacity){
            removeAny();
        }
        hashMap.put(person, people);
        return true;
    }

    @Override
    public List<? extends Person> get(Person person) {
        return hashMap.get(person);
    }

    @Override
    public int getSize() {
        return hashMap.size();
    }

    private boolean removeAny() {
        Random random = new Random();
        //hashMap.remove(lastPerson);
        Object[] personArray = hashMap.keySet().toArray();
        int stopIndex = random.nextInt(personArray.length);
        Person personForDelete = (Person) personArray[stopIndex];
        hashMap.remove(personForDelete);
        return true;
    }

}
