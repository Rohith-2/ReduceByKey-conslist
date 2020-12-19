# Conslist

<hr style=\"border:0.5px solid gray\"> </hr>

## AIE203-Data Structures and Algorithms
### Authors:
1. Anirudh Bhaskar      -  CB.EN.U4AIE19007
2. Rohith Ramakrishnan  -  CB.EN.U4AIE19052

<hr style=\"border:0.5px solid gray\"> </hr>

## Implementation of ReduceByKey via Conslist <br> 
Using the three methods mentioned below, a benchmark test consisting of memory and time, was performed via loading the same text file sample. <br>

<hr style=\"border:2px solid gray\"> </hr>

### Reduce By Key 
This function merges or sums the values for each key held in a KVPair Object with/without using a Map Function.<br>
A KVPair object hold a key (String) and value (int). Using ReduceByKey we will be creating a set of keys with no repetitions with a merged/summed value corresponding to the key.
In the Scope of this project, given a text file, the Conslist of KVPair object will hold each instance of the words occuring and using ReduceByKey a Set of all the words along will its word count can be computed.<br>
<br>
For example,<br>
Apple : 1 , Apple : 1 , Apple : 1, Bat : 1 , Bee : 1 , Bee : 1   --->  Apple : 3, Bat : 1 , Bee : 2 

<hr style=\"border:1px solid gray\"> </hr>
For a Benchmark test, each of these functions were measured 50 times and taken of mean of.

Performance of our ReduceByKey via 3 methods measured in nanoseconds and bytes respectively:<br>

|     Metric      | Iterative Method | Map method |  Tree Method Iteration | Tree Method Reccursion |
|-----------------|------------------|------------|------------------------|------------------------|
|      Time       |     64136        |   77013    |         167859         |        106815          |
|     Memory      |    1235976       |  1236440   |         1299376        |       1299376          |

<img src="https://github.com/Rohith-2/conslist/blob/master/MetricPlot.png"
     alt="Metric Chart"
     style="float: left; margin-right: 10px;" />

<hr style=\"border:1px solid gray\"> </hr>

### Implementation of the Iterative Method:
Via the Iterative Method we will be locally violating immutabilty to achieve the Reduce-By-Key funtion.<br>
This method will call for two functions has() and where().<br>
has() is a boolean function to inspect the presence of a key in any KVPair object existing in a Conslist.<br>
where(string) traverses through the conslist and returns the KVPair whose key matches with the given string.<br>
Using a while loop and a if condition, the existance of a given key in the new conslist is examined<br>
  
``` js
while(tail is not Null){
    if(newConslist has tail.head){
        a = newConslist.where(tail.head)
        a.value+=1
    }
    else{
        Create a new KVPair(head.key,head.value);
        Add this to the newConslist
    }
}
```
The newConslist will consist of one instance of the words along with its wordcount.

<hr style=\"border:1px solid gray\"> </hr>

### Implementation of the Map Method:
In the map method our aim is recursively check whether a certain word is present in the created conslist from our sample text file<br>
The main functions we use are Hasfilter and whereall()<br>
When we create our conslist for the sample text file,each word gets a default value. This helps us in the counting process. The WhereAll function's aim is to create this default value, which is 1 for all the words.<br>
Since our existing conslist is immutable, we create another conslist that has a KV pair type which is specifically used for extracting the unique words from our sample. The Hasfilter does this using the concept of recursion. It takes a Conslist of type KV pair as input and output.<br>
In our function for map we iterate using a while loop in order to skip the repeating words such that the tail points to only the next unique word in the list and we push it into our new conslist. This iteration is repeated for every word.<br>
  
```js
funtion Map_Reduce{
 H -> MapFunction
while(tail has head.key){
    tail -> tail.tail; //Keep iterating until we discover a unique element
    return new KVPair(pass the head into the lambda funtion of H , tail.Map_Reduce); //Tail Recurssion 
}
}
```
  
As a result our new conslist will have unique keys and only its updated values that represent our word count.

<hr style=\"border:1px solid gray\"> </hr>

### Implementation of Tree Method via Consset:
Consset is a Binary Tree-Based extension of conslist and Mathematical set.<br>
The property of exlusion of duplicates can be used for our gain. Ater initiallising an Null tree with left and right branches,iteration via a while loop implicitly is used to add elements to the tree. Using the String comparison we can create new leaves either left or right of the root.

#### Iteration Based :
```js
t -> new Consset()
while(tail!=Nil){
    t.add(head) //if head already exists the value of that KVPair will be incremented.
    tail->tail.tail;
}
```
#### Reccursion Based :
```js
function tree_RbK_r(){
	return tree_r(new NonEmpty<>(head),tail);
	}
function tree_r(ConsSet s ,Conslist t){
		n_t -> t.tail();
		if(n_t not Nil)return tree_r(s.add(t.head()),n_t);
		return s;
	}
```
<hr style=\"border:1px solid gray\"> </hr>
