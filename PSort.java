import java.util.concurrent.ForkJoinPool;


public class PSort {
	
	public static void parallelSort(int[] A, int begin, int end) {
		
		if (A.length <= 0){ throw new IllegalArgumentException("Array can't be empty");}
		if (begin > end){throw new IllegalArgumentException("Begin value is greater than End value");}
		
		
		//rather than (begin + end)/2 to avoid integer overflow for large array sizes
		int midpoint = begin + ((end - begin) / 2);		
		
		PSortTask pst1 = new PSortTask(A, begin, midpoint);
		PSortTask pst2 = new PSortTask(A, midpoint, end);
		Thread t1 = new Thread(pst1);
		Thread t2 = new Thread(pst2);
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PSortTask.combineSortedHalves(A, begin, midpoint, end);
		
	}
	
	static private class PSortTask implements Runnable {

		private int[] A;
		private int begin, end;
		
		public PSortTask(int[] A, int begin, int end) {
			this.A = A;
			this.begin = begin;
			this.end = end;
		}
		
		private void recurse() {
			
			//if subsection of array is 1 element or less, it is already sorted
			//(ex: begin = 0, end = 2 is 2 elements, at indices 0 and 1)
			if(end - begin < 2) {
				return;
			}
			
			//rather than (begin + end)/2 to avoid integer overflow for large array sizes
			int midpoint = begin + ((end - begin) / 2);
			
			//sort recursively w/threads
			PSortTask pst1 = new PSortTask(A, begin, midpoint);
			PSortTask pst2 = new PSortTask(A, midpoint, end);
			
			pst1.recurse();
			pst2.recurse();

			combineSortedHalves(A, begin, midpoint, end);	
		}
		
		@Override
		public void run() {
			recurse();
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
	
	

}
