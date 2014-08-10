package pac.testcase.basic.thread;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Alvez on 14-7-26.
 */
public class Observable {

		public static void main(String[] args) {
			ObservableSet<Integer> set = new ObservableSet<Integer>(
					new HashSet<Integer>());

			// Observer that uses a background thread needlessly
			set.addObserver(new SetObserver<Integer>() {
				public void added(final ObservableSet<Integer> s, Integer e) {
					System.out.println(e);
					if (e == 23) {
						ExecutorService executor = Executors
								.newSingleThreadExecutor();
						final SetObserver<Integer> observer = this;
						try {
							executor.submit(new Runnable() {
								public void run() {
									s.removeObserver(observer);
								}
							}).get();
						} catch (ExecutionException ex) {
							throw new AssertionError(ex.getCause());
						} catch (InterruptedException ex) {
							throw new AssertionError(ex.getCause());
						} finally {
							executor.shutdown();
						}
						
/*						Thread t = new Thread(new Runnable() {
							
							
							@Override
							public void run() {
								s.removeObserver(observer);
								
							}
						});
						t.start();*/
					}
				}
			});

			for (int i = 0; i < 100; i++)
				set.add(i);
		}
		

		/*public static void main(String[] args) {
			ObservableSet<Integer> set = new ObservableSet<Integer>(
					new HashSet<Integer>());

			set.addObserver(new SetObserver<Integer>() {
				public void added(ObservableSet<Integer> s, Integer e) {
					System.out.println(e);
					if (e == 23)
						s.removeObserver(this);
				}
			});

			for (int i = 0; i < 100; i++)
				set.add(i);
		}*/

	
}

class ObservableSet<E> extends ForwardingSet<E> {
	public ObservableSet(Set<E> set) {
		super(set);
	}

	private final List<SetObserver<E>> observers = new ArrayList<SetObserver<E>>();

	public void addObserver(SetObserver<E> observer) {
		synchronized (observers) {
			observers.add(observer);
		}
	}

	public boolean removeObserver(SetObserver<E> observer) {
		synchronized (observers) {
			return observers.remove(observer);
		}
	}

	// This method is the culprit
	private void notifyElementAdded(E element) {
		synchronized (observers) {
			for (SetObserver<E> observer : observers)
				observer.added(this, element);
		}
	}

	// Alien method moved outside of synchronized block - open calls - Page 268
	// private void notifyElementAdded(E element) {
	// List<SetObserver<E>> snapshot = null;
	// synchronized(observers) {
	// snapshot = new ArrayList<SetObserver<E>>(observers);
	// }
	// for (SetObserver<E> observer : snapshot)
	// observer.added(this, element);
	// }

	// Thread-safe observable set with CopyOnWriteArrayList - Page 269
	//
	// private final List<SetObserver<E>> observers =
	// new CopyOnWriteArrayList<SetObserver<E>>();
	//
	// public void addObserver(SetObserver<E> observer) {
	// observers.add(observer);
	// }
	// public boolean removeObserver(SetObserver<E> observer) {
	// return observers.remove(observer);
	// }
	// private void notifyElementAdded(E element) {
	// for (SetObserver<E> observer : observers)
	// observer.added(this, element);
	// }

	@Override
	public boolean add(E element) {
		boolean added = super.add(element);
		if (added)
			notifyElementAdded(element);
		return added;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean result = false;
		for (E element : c)
			result |= add(element); // calls notifyElementAdded
		return result;
	}
}

class ForwardingSet<E> implements Set<E> {
	private final Set<E> s;

	public ForwardingSet(Set<E> s) {
		this.s = s;
	}

	public void clear() {
		s.clear();
	}

	public boolean contains(Object o) {
		return s.contains(o);
	}

	public boolean isEmpty() {
		return s.isEmpty();
	}

	public int size() {
		return s.size();
	}

	public Iterator<E> iterator() {
		return s.iterator();
	}

	public boolean add(E e) {
		return s.add(e);
	}

	public boolean remove(Object o) {
		return s.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return s.containsAll(c);
	}

	public boolean addAll(Collection<? extends E> c) {
		return s.addAll(c);
	}

	public boolean removeAll(Collection<?> c) {
		return s.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return s.retainAll(c);
	}

	public Object[] toArray() {
		return s.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return s.toArray(a);
	}

	@Override
	public boolean equals(Object o) {
		return s.equals(o);
	}

	@Override
	public int hashCode() {
		return s.hashCode();
	}

	@Override
	public String toString() {
		return s.toString();
	}
}

interface SetObserver<E> {
	// Invoked when an element is added to the observable set
	void added(ObservableSet<E> set, E element);
}