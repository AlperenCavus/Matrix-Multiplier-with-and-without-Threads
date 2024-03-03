package Main;
import java.util.Arrays;
import java.util.Random; 	

 
//MAHMUT ALPEREN ÇAVUŞ 210408044
public class MatrixMultiplier {
public static void main(String[] args) {
	
	long startTime=System.nanoTime();
	
	int rowAndColNums=500;
	
	int[][] matrix1=genNewMatrix(rowAndColNums);
	
	int[][] matrix2=genNewMatrix(rowAndColNums);
	
	int[][] resultMatrix=matrixMultiplication(matrix1, matrix2);
	
	long endTime=System.nanoTime();
	
	long duration=endTime-startTime;
	print2D(matrix1);
	System.out.println("--------------------------");
	print2D(matrix2);
	System.out.println("--------------------------");
	print2D(resultMatrix);
	System.out.println("");
	System.out.println("Duration time is: "+duration+" nanoseconds");
	
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

}
