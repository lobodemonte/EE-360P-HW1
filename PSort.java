import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//Erik Lopez and Sean Tubbs(smt2436)


public class PSort implements Runnable {
	
	private static ExecutorService threadPool = Executors.newCachedThreadPool();
	private static ExecutorService es;
	private int[] A;
	private int begin, end;
	
	public static void parallelSort(int[] A, int begin, int end) {
		
		if (A.length <= 0){ throw new IllegalArgumentException("Array can't be empty");}
		if (begin > end){throw new IllegalArgumentException("Begin value is greater than End value");}
		
		
//		es = Executors.newSingleThreadExecutor();
		es = Executors.newCachedThreadPool();
		PSort ps = new PSort(A, begin, end);
		Future<?> f1 = es.submit(ps);
		
		try {
			f1.get();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		es.shutdown();
		ps.threadPool.shutdown();

	}
	
		
		private PSort(int[] A, int begin, int end) {
			this.A = A;
			this.begin = begin;
			this.end = end;
		}
		
		
		@Override
		public void run() {
			//if subsection of array is 1 element or less, it is already sorted
			//(ex: begin = 0, end = 2 is 2 elements, at indices 0 and 1)
			if(end - begin < 2) {
				return;
			}
			
			//rather than (begin + end)/2 to avoid integer overflow for large array sizes
			int midpoint = begin + ((end - begin) / 2);
			
			//sort recursively w/threads
			PSort ps1 = new PSort(A, begin, midpoint);
			PSort ps2 = new PSort(A, midpoint, end);
			
			Future<?> f1 = es.submit(ps1);
			Future<?> f2 = es.submit(ps2);
			
			try {
				f1.get();
				f2.get();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			combineSortedHalves(A, begin, midpoint, end);	
		}
		
		public static void combineSortedHalves(int[] A, int begin, int midpoint, int end) {
			
			//now, two sub arrays are sorted in-place in A. now pull them out into two temporary
			//arrays so we can combine the two, in order, in A
			
			int[] a1 = new int[midpoint - begin];
			int[] a2 = new int[end - midpoint];
			
			for(int i = 0; i < a1.length; i++) {
				a1[i] = A[begin + i];
			}
			for(int i = 0; i < a2.length; i++) {
				a2[i] = A[midpoint + i];
			}
			
			int i1 = 0;
			int i2 = 0;
			int aIndex = begin;
			
			//TODO: sorted ascending or descending????			
			while(i1 < a1.length && i2 < a2.length && aIndex < end) {
				if(a1[i1] < a2[i2]) {
					A[aIndex] = a1[i1];
					i1++;
				}
				else {
					A[aIndex] = a2[i2];
					i2++;
				}
				
				aIndex++;
			}
			
			//after above while loop finishes, only 1 of the arrays, at most should be not copied in.
			//only one of the two following loops will be executed
			while(i1 < a1.length) {
				A[aIndex] = a1[i1];
				i1++;
				aIndex++;
			}
			
			while(i2 < a2.length) {
				A[aIndex] = a2[i2];
				i2++;
				aIndex++;
			}	
		}
				
	}
