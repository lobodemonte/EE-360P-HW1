//Erik Lopez(el8779) and Sean Tubbs(smt2436)

import java.util.concurrent.Callable;



public class ParallelSearchTask implements Callable<Integer> {

	int x;
	int[] A;
	int begin = 0;
	int end = 0;
	
	public ParallelSearchTask(int x, int[] A, int begin, int end){
		this.x = x;
		this.A = A;
		this.begin = begin;
		this.end = end;
	}
	
	@Override
	public Integer call() throws Exception {
		for (int i = begin; i < end; i++){
			if (A[i] == x){
				return i;
			}
		}
		return -1;
	}


	

}
