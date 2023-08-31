package heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

class HeapGreater<T> {
  private ArrayList<T> heap;
  private HashMap<T, Integer> indexMap;
  private int heapSize;
  private Comparator<? super T> compare;

  public HeapGreater(Comparator<? super T> c) {
    heap = new ArrayList<>();
    indexMap = new HashMap<>();
    heapSize = 0;
    compare = c;
  }

  public boolean isEmpty() {
    return heapSize == 0;
  }

  public int size() {
    return heapSize;
  }

  public boolean contains(T obj) {
    return indexMap.containsKey(obj);
  }

  public T peek() {
    return heap.get(0);
  }

  public void push(T obj) {
    heap.add(obj);
    indexMap.put(obj, heapSize);
    heapInsert(heapSize++);
  }

  public T pop() {
    T ans = heap.get(0);
    swap(0, heapSize - 1);
    indexMap.remove(ans);
    heap.remove(--heapSize);
    heapify(0);
    return ans;
  }

  public void remove(T obj) {
    T replace = heap.get(heapSize - 1);
    int index = indexMap.get(obj);
    indexMap.remove(obj);
    heap.remove(--heapSize);
    if (obj != replace) {
      heap.set(index, replace);
      indexMap.put((replace), index);
      resign(replace);
    }
  }

  public void resign(T obj) {
    heapInsert(indexMap.get(obj));
    heapify(indexMap.get(obj));
  }

  public List<T> getAllElements() {
    List<T> ans = new ArrayList<>();
    for (T c : heap)
      ans.add(c);
    return ans;
  }

  private void heapInsert(int index) {
    while (compare.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
      swap(index, (index - 1) / 2);
      index = (index - 1) / 2;
    }
  }

  private void heapify(int index) {
    int left = index * 2 + 1;
    while (left < heapSize) {
      int best = left + 1 < heapSize && compare.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
      best = compare.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
      if (best == index)
        break;
      swap(best, index);
      index = best;
      left = index * 2 + 1;
    }
  }

  private void swap(int i, int j) {
    T o1 = heap.get(i);
    T o2 = heap.get(j);
    heap.set(i, o2);
    heap.set(j, o1);
    indexMap.put(o2, i);
    indexMap.put(o1, j);
  }
}
