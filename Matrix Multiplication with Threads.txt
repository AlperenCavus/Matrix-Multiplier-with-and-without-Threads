package Main;

import java.util.Arrays;
import java.util.Random;



public class MatrixMultiplier {
 
  //MAHMUT ALPEREN ÇAVUŞ 210408044
	
  /*My results are: num_threads= 1 -> Duration time is: 159490400 nanoseconds
                    num_threads= 2 -> Duration time is: 93016700 nanoseconds
                    num_threads= 3 -> Duration time is: 73601800 nanoseconds
                    num_threads= 4 -> Duration time is: 62255800 nanoseconds
                    num_threads= 5 -> Duration time is: 62462701 nanoseconds
                    num_threads= 6 -> Duration time is: 57630800 nanoseconds
                    num_threads= 7 -> Duration time is: 60726600 nanoseconds 
                    num_threads= 8 -> Duration time is: 58668601 nanoseconds 
                    num_threads= 9 -> Duration time is: 60463599 nanoseconds
                    num_threads= 10 -> Duration time is: 60105500 nanoseconds
                    num_threads= 12 -> Duration time is: 58099900 nanoseconds
                    
	*/

  public static void main(String[] args) {
	  int num_threads = 12;
	
	  long startTime=System.nanoTime();
		
		int rowAndColNums=500;
		
		int[][] matrix1=genNewMatrix(rowAndColNums);
		
		int[][] matrix2=genNewMatrix(rowAndColNums);
		
		int[][] resultMatrix=new int[rowAndColNums][rowAndColNums];
		
		
		
		

    Thread[] threads = new Thread[num_threads];
    int segmentSize = rowAndColNums / num_threads;

    for (int i = 0; i < num_threads; i++) {
      int startIndex = i * segmentSize;
      int endIndex = (i == num_threads - 1) ? rowAndColNums - 1 : (startIndex + segmentSize - 1);
      threads[i] = new Thread(new MultiplicationTask(matrix1, matrix2, resultMatrix, startIndex, endIndex));
      threads[i].start();
    }

    for (Thread thread: threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    long endTime=System.nanoTime();
	
	long duration=endTime-startTime;
	
	print2D(matrix1);
	System.out.println("----------------------------------------");
	print2D(matrix2);
	System.out.println("----------------------------------------");
	print2D(resultMatrix);
	System.out.println("----------------------------------------");
    System.out.println("Duration time is: "+duration+" nanoseconds");
    System.out.println("Duration time is: " + (duration / 1_000_000) + " milliseconds");
   
  }
  public static int[][] genNewMatrix(int n) { 		
		int [][]A=new int[n][n]; 	
		Random R=new Random(); 	
		
		
		int i,j; 	
		for( i=0; i < n ; i++ ) {
			for( j=0; j < n ; j++ ) {
				A[i][j]=R.nextInt(10); 	
			}	
		}	
		return A; 	
		

	}
	public static int[][] matrixMultiplication(int[][] matrix1,int[][] matrix2){
		int[][] result=new int[matrix1.length][matrix1[0].length];
		for(int i=0;i<matrix1.length;i++){    
			for(int j=0;j<matrix1.length;j++){    
			result[i][j]=0;      
			for(int k=0;k<matrix1.length;k++)      
			{      
			result[i][j]+=matrix1[i][k]*matrix2[k][j];      
			}//end of k loop 
	}
		 
	}
		return result;
	}
	public static void print2D(int[][] result) {
		 
		    
		        // Loop through all rows
		        for (int[] row : result) {
		            System.out.println(Arrays.toString(row));
		            
		            }
		        
		    }

  static class MultiplicationTask implements Runnable {
    private int[][] matrix1;
    private int[][] matrix2;
    private int[][] result;
    private int startIndex;
    private int endIndex;

    public MultiplicationTask(int[][] matrix1, int[][] matrix2, int[][] result, int startIndex, int endIndex) {
      this.matrix1 = matrix1;
      this.matrix2 = matrix2;
      this.result = result;
      this.startIndex = startIndex;
      this.endIndex = endIndex;
    }

    @Override
    public void run() {
      int cols = matrix2[0].length;

      for (int i = startIndex; i <= endIndex; i++) {
        for (int j = 0; j < cols; j++) {
          for (int k = 0; k < matrix1.length; k++) {
            result[i][j] += matrix1[i][k] * matrix2[k][j];
          }
        }
      }
    }
  }
}
