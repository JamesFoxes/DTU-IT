/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aimo & Lukas
 */
public class Assignment1_SortingAlgorithms {
    
    static CreateArrays Array = new CreateArrays();
    static InsertionSorting IS = new InsertionSorting();
    static SelectionSorting SS = new SelectionSorting();
    static SystemQuickSorting SQS = new SystemQuickSorting();
    

    public static void main(String[] args) {
        int arraylength;
        // First run with array size of 1.000
        arraylength = 10000;
        System.out.println("Array length = "+arraylength);
 
        int[] A = Array.createRanArray(arraylength);
        int[] Aasc = Array.createAscArray(arraylength);
        int[] Ades = Array.createDesArray(arraylength);
        
        System.out.println("\n+++Random array+++\n");
        System.out.println("Insertion Sorting: ");
        IS.insertionSort(A);
        System.out.println("\nSelection Sorting: ");
        SS.selectionSort(A);
        System.out.println("\nSystem Quick Sorting: ");
        SQS.systemSort(A);
        
        System.out.println("\n+++Ascending array+++");
        System.out.println("\nInsertion Sorting:");
        IS.insertionSort(Aasc);
        System.out.println("\nSelection Sorting: ");
        SS.selectionSort(Aasc);
        System.out.println("\nSystem Quick Sorting: ");
        SQS.systemSort(Aasc);
        
        System.out.println("\n+++Descending array+++");
        System.out.println("\nInsertion Sorting:");
        IS.insertionSort(Ades);
        System.out.println("\nSelection Sorting: ");
        SS.selectionSort(Ades);
        System.out.println("\nSystem Quick Sorting: ");
        SQS.systemSort(Ades);
        
        // Array length of 10.000
        arraylength = 10000;
        System.out.println("\nArray length = "+arraylength);
 
        A = Array.createRanArray(arraylength);
        Aasc = Array.createAscArray(arraylength);
        Ades = Array.createDesArray(arraylength);
        
        System.out.println("\n+++Random array+++\n");
        System.out.println("Insertion Sorting: ");
        IS.insertionSort(A);
        System.out.println("\nSelection Sorting: ");
        SS.selectionSort(A);
        System.out.println("\nSystem Quick Sorting: ");
        SQS.systemSort(A);
        
        System.out.println("\n+++Ascending array+++");
        System.out.println("\nInsertion Sorting:");
        IS.insertionSort(Aasc);
        System.out.println("\nSelection Sorting: ");
        SS.selectionSort(Aasc);
        System.out.println("\nSystem Quick Sorting: ");
        SQS.systemSort(Aasc);
        
        System.out.println("\n+++Descending array+++");
        System.out.println("\nInsertion Sorting:");
        IS.insertionSort(Ades);
        System.out.println("\nSelection Sorting: ");
        SS.selectionSort(Ades);
        System.out.println("\nSystem Quick Sorting: ");
        SQS.systemSort(Ades);
        
        // Array length of 30.000
        arraylength = 30000;
        System.out.println("\nArray length = "+arraylength);
 
        A = Array.createRanArray(arraylength);
        Aasc = Array.createAscArray(arraylength);
        Ades = Array.createDesArray(arraylength);
        
        System.out.println("\n+++Random array+++");
        System.out.println("\nInsertion Sorting: ");
        IS.insertionSort(A);
        System.out.println("\nSelection Sorting: ");
        SS.selectionSort(A);
        System.out.println("\nSystem Quick Sorting: ");
        SQS.systemSort(A);
        
        System.out.println("\n+++Ascending array+++");
        System.out.println("\nInsertion Sorting:");
        IS.insertionSort(Aasc);
        System.out.println("\nSelection Sorting: ");
        SS.selectionSort(Aasc);
        System.out.println("\nSystem Quick Sorting: ");
        SQS.systemSort(Aasc);
        
        System.out.println("\n+++Descending array+++");
        System.out.println("\nInsertion Sorting:");
        IS.insertionSort(Ades);
        System.out.println("\nSelection Sorting: ");
        SS.selectionSort(Ades);
        System.out.println("\nSystem Quick Sorting: ");
        SQS.systemSort(Ades);
        
        // Array length of 50.000
        arraylength = 50000;
        System.out.println("\nArray length = "+arraylength);
 
        A = Array.createRanArray(arraylength);
        Aasc = Array.createAscArray(arraylength);
        Ades = Array.createDesArray(arraylength);
        
        System.out.println("\n+++Random array+++");
        System.out.println("\nInsertion Sorting: ");
        IS.insertionSort(A);
        System.out.println("\nSelection Sorting: ");
        SS.selectionSort(A);
        System.out.println("\nSystem Quick Sorting: ");
        SQS.systemSort(A);
        
        System.out.println("\n+++Ascending array+++");
        System.out.println("\nInsertion Sorting:");
        IS.insertionSort(Aasc);
        System.out.println("\nSelection Sorting: ");
        SS.selectionSort(Aasc);
        System.out.println("\nSystem Quick Sorting: ");
        SQS.systemSort(Aasc);
        
        System.out.println("\n+++Descending array+++");
        System.out.println("\nInsertion Sorting:");
        IS.insertionSort(Ades);
        System.out.println("\nSelection Sorting: ");
        SS.selectionSort(Ades);
        System.out.println("\nSystem Quick Sorting: ");
        SQS.systemSort(Ades);
        
        // Array length of 100.000
        arraylength = 100000;
        System.out.println("\nArray length = "+arraylength);
 
        A = Array.createRanArray(arraylength);
        Aasc = Array.createAscArray(arraylength);
        Ades = Array.createDesArray(arraylength);
        
        System.out.println("\n+++Random array+++");
        System.out.println("\nInsertion Sorting: ");
        IS.insertionSort(A);
        System.out.println("\nSelection Sorting: ");
        SS.selectionSort(A);
        System.out.println("\nSystem Quick Sorting: ");
        SQS.systemSort(A);
        
        System.out.println("\n+++Ascending array+++");
        System.out.println("\nInsertion Sorting:");
        IS.insertionSort(Aasc);
        System.out.println("\nSelection Sorting: ");
        SS.selectionSort(Aasc);
        System.out.println("\nSystem Quick Sorting: ");
        SQS.systemSort(Aasc);
        
        System.out.println("\n+++Descending array+++");
        System.out.println("\nInsertion Sorting:");
        IS.insertionSort(Ades);
        System.out.println("\nSelection Sorting: ");
        SS.selectionSort(Ades);
        System.out.println("\nSystem Quick Sorting: ");
        SQS.systemSort(Ades);
        
    }
}