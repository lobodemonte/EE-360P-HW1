//Erik Lopez(el8779) and Sean Tubbs(smt2436)

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PSearch {

	public static int parallelSearch(int x, int[] A, int numThreads){
		
		//Check for dumb inputs and throw them back at the User for being dumb
		if (A.length <= 0){ throw new IllegalArgumentException("Array can't be empty.\n");}
		
		//TODO: are we certain numThreads > A.length should throw an exception? Maybe
		//just not use some of the threads?
		if (numThreads <= 0 || numThreads > A.length){throw new IllegalArgumentException("The number of threads has to be greater than zero and no more than the size of the array.\n");}
		
		//Let's get the size of the average chunk
		int chunkSize = (int)Math.ceil((double)A.length / numThreads);
		
		//Lets give each chunk to a Thread
		ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<Integer>> list = new ArrayList<Future<Integer>>();
        int begin = 0;
        int end = 0;
        for(int i=0; i< numThreads; i++){
        	end = end + chunkSize;
        	if (end > A.length){ end = A.length;}
        	Callable<Integer> callable = new ParallelSearchTask(x, A, begin, end);
        	begin += chunkSize;
            Future<Integer> future = executor.submit(callable);
            list.add(future);
        }
        
       //Check the results
        int result = -1;
        for(int i=0; i< numThreads; i++){
            try {
                result = list.get(i).get();
                if (result != -1){
                	//result = result + (chunkSize * i);
                	break;
                }
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Exception Thrown by Future");
            	e.printStackTrace();
            }
        }
        executor.shutdown();
		return result;
	}

}
