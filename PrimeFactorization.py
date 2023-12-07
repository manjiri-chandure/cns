import math
import time

def getFactors(A,n):
    print("Factorizing.....")
    end = (int)(math.sqrt(A)) + 1
    start_time = time.time()
    for i in range(2, end):
        if A % i == 0:
            print(f"Factors --> {i} , {A // i}")
            break
    end_time = time.time()
    print("Number of Digits: ", n)
    print("Execution Time: ", end_time - start_time)
    print("")
    

semiprimes = 100000000088787877679100000000088787877679100000000088787877679100000000088787877679100000000088787877679
digits = 100

getFactors(semiprimes,digits)