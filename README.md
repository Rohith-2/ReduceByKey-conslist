# Conslist
## Implementation of ReduceByKey via Conslist <br> 
Using the three methods we compared the time taken for them to run the same text file sample measured in nano seconds(ns) <br>
Using the iterative method we implicitly expolit the immutable nature of the conslist unlike the other two methods.<br> 

### Reduce By Key 
This function merges or sums the values for each key held in a KVPair Object with/without using a Map Function.<br>
A KVPair object hold a key (String) and value (int). Using ReduceByKey we will be creating a set of keys with no repetitions with a merged/summed value corresponding to the key.
In the Scope of this project, given a text file, the Conslist of KVPair object will hold each instance of the words occuring and using ReduceByKey a Set of all the words along will its word count can be computed.<br>
For example,<br>
Apple : 1 , Apple : 1 , Apple : 1, Bat : 1 , Bee : 1 , Bee : 1   --->  Apple : 3, Bat : 1 , Bee : 2 

"<hr style=\"border:1px solid gray\"> </hr>"
Performance of our ReduceByKey via 3 methods measured in nanoseconds:<br>

| Iterative Method | Map method |  Tree Method |
|------------------|------------|--------------|
|    70375         |   127011   |    207619    |

"<hr style=\"border:1px solid gray\"> </hr>"
### Implementation of the Iterative Method:
Via the Iterative Method we will be locally violating immutabilty to achieve the Reduce-By-Key funtion.<br>
This method will call for two functions has() and where().<br>
has() is a boolean function to inspect the presence of a key in any KVPair object existing in a Conslist.<br>
where(string) traverses through the conslist and returns the KVPair whose key matches with the given string.<br>

"<hr style=\"border:1px solid gray\"> </hr>"
### Implementation of the Map Method:
In the map method our aim is recursively check whether a certain word is present in the created conslist from our sample text file<br>
The main functions we use are Hasfilter and Whereall<br>
When we create our conslist for the sample text file,each word gets a default value. This helps us in the counting process. The WhereAll function's aim is to create this default value, which is 1 for all the words.<br>
Since our existing conslist is immutable, we create another conslist that has a KV pair type which is specifically used for extracting the unique words from our sample. The Hasfilter does this using the concept of recursion. It takes a Conslist of type KV pair as input and output.<br>
Now in our function for map we iterate using a while loop in order to skip the repeating words. So we iterate using the tail such that the tail points to only the next unique word in the list and we push it into our new conslist. We do this iteration for every word.<br>
As a result our new conslist will have unique keys and only the updated values for them representing word count.