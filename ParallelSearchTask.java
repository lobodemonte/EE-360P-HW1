import java.util.concurrent.Callable;


public class ParallelSearchTask implements Callable<Integer> {

	int x;
	int[] A;
	
	public ParallelSearchTask(int x, int[] A){
		this.x = x;
		this.A = A;
	}
	
	@Override
	public Integer call() throws Exception {
		for (int i = 0; i < A.length; i++){
			if (A[i] == x){
				return i;
			}
		}
		return -1;
	}


	

}
