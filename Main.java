import java.util.Random;

//Erik Lopez(el8779) and Sean Tubbs(smt2436)

public class Main{
   public static void main(String args[]){
	   // Test Q1 implementation
	   // you do not need to write this code. 
	   // create and populate array A with values
	   
	   Random x = new Random();
	   
	   
	   int[] hugestArray = new int[10000];
	   int[] hugestArrayRandomized = new int[hugestArray.length];
	   
	   //populate, sorted in REVERSE
	   for(int i = 0; i < hugestArray.length; i++) {
		   hugestArray[i] = hugestArray.length - i;
		   hugestArrayRandomized[i] = hugestArray[i] + x.nextInt();
	   }
	   
	   int Arrays[][] = { 
			   {42,2,9,25,49}
			   //,hugestArray
			   //,hugestArrayRandomized
	   };
	   
	   
	   int A[]; 
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   System.out.println("***********************");
	   System.out.println("Testing PSearch");
	   
	   for(int j = 0; j < Arrays.length; j++) {
		   System.out.println("***********************");
		   System.out.println("Testing array " + (j+1) + " of " + Arrays.length + ":");
		   A = Arrays[j];
		   
		   System.out.println("The array is: ");
		   for(int i = 0; i < A.length; i++) {
			   System.out.print(A[i] + " ");
		   }
		   System.out.println();
		   
		   // Test Q2 implementation with 3 threads
		   int x1 = 42;
		   int result = PSearch.parallelSearch(x1, A, 4);
		   
		   System.out.println("PSearch says the number "+x1+" is at index " + result);
		   
	   }
	   


	   
	 
	   
	  /* 
	   
	   
	   System.out.println("***********************");
	   System.out.println("Testing PSort");
	   
	   for(int j = 0; j < Arrays.length; j++) {
		   System.out.println("***********************");
		   System.out.println("Testing array " + (j+1) + " of " + Arrays.length + ":");
		   A = Arrays[j];
		   
		   System.out.println("Before sorting, array is: ");
		   for(int i = 0; i < A.length; i++) {
			   System.out.print(A[i] + " ");
		   }
		   System.out.println();
		   
		   // call PSort 
		   PSort.parallelSort(A, 0, A.length);
		   
		   boolean flag = false;
		   
		   System.out.println("After sorting, array is: ");
		   for(int i = 0; i < A.length; i++) {
			   System.out.print(A[i] + " ");
			   
			   if(i != A.length-1 && A[i] > A[i+1]) {
				   flag = true;
			   }
		   }
		   
		   if(flag){
			   System.out.print("NOT!!!! ");
		   }
		   
		   System.out.println("sorted correctly");
	   }
	   
	   // verify if A is sorted
	   // ... verification code --- written by us. 
	   // (you do not need to write this code). 
	    */
   }
}
