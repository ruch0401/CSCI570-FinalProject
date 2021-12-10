# CSCI570-FinalProject




----------------------  
# Sequence Alignment (Extended)

Refer [SequenceAlignmentMaven](SequenceAlignmentMaven)   

This is an extension to the code written in [1111417799_3695883753](1111417799_3695883753) module. The following options
can be used to extend the modularity of the code -

| Flag                       | Description                                                                                                           |
|----------------------------|-----------------------------------------------------------------------------------------------------------------------|
| ```isCustomEnabled```            | enable this to toggle pass a custom input rather than reading the input from the file, default = false                |
| ```isSpaceOptimizationEnabled``` | enable this to execute the code with space optimization enabled, default = false                                      |
| ```isPrinting2DMatrixEnabled```  | enable this to print the 2D numerical matrix that is generated as a part of the DP table calculation, default = false |
| ```isDivideAndConquerEnabled```  | enable this to perform sequence alignment using Divide and Conquer + Dynamic Programming approach, default = false    |
| ```isLoggingEnabled```           | enable this to display stepwise logs of the application, default = false                                              |
| ```isWriteOutputToFile```        | enable this to write the output to the file 'output.txt', default = false                                             |

## Limitations

- Divide and Conquer + DP can only run with Space Optimized enabled. Please set -isSpaceOptimizationEnabled = true or ```-isDivideAndConquerEnabled = false```
- 2D DP Matrix cannot be printed if Space Optimization is enabled. Please set ```-isPrinting2DMatrixEnabled = false```
- Custom input executions cannot be written to the file. Please set ```-isWriteOutputToFile = false```

## Unit Tests  

The unit tests are written in a manner that inputs will be randomly generated and will be tested for 20 cases.  
###Validation
Calculation of the Needleman Wunsch score for both the basic version and the efficient version. If both of the scores are same, then the test is assumed to have passed.

This is a maven based module and the entire code can be executed using the following command - 
```
 java -cp <jar-name-here>.jar org.algo.SequenceAlignmentMaven -basePath "<base-path-to-input-file-here>" -filename <input-file-name-here> -firstString <first-string-here> -secondString <second-string-here> -isCustomEnabled <true/false> -isSpaceOptimizationEnabled <true/false> -isPrinting2DMatrixEnabled <true/false> -isDivideAndConquerEnabled <true/false> -isLoggingEnabled <true/false>
```

Please adhere to the limitations while keeping setting the true/false options 