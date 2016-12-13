##Information
    
>    This system of the actor can count size of all files in a specific directory and its subdirectories.

The main idea that we create ```FileSizeCounterDispatcher``` and pass the ```path``` to the directory which size we want to know.
In this realization   ```FileSizeCounterDispatcher``` responsible for creating ```FileSizeCounter``` and splits directories between them.
```FileSizeCounter``` checks if  given  path is directory or not. If directory, it sends message to ```FileSizeCounterDispatcher```, otherwise sum size of each file in directory.

##Demonstration

You can see how it works starting ```mail.demo.Demo``` class.
Note: if you want to change directory, just simple change value of ```demoDirectoryPath``` key in ```main/resources/demo.properties```

##Test
You can find test for Responsiveness and Elasticity and Resiliency of Actor system in ```test/com/epam/API/actor```
```ElasticityActorTest``` - shows how performance changed when use different various ```Actor``` on the same task.
```ResponsivenessActorTest``` - shows how much time it takes to deliver message to another ```Actor``` and time to receive a response from it.
```Resiliency``` - shows that if exception was occur it handles and create new one thread and continue performing task.
Note: if you want to change directory, just simple change value of ```testDirectoryPath``` key in ```test/java/resources/testcase.properties```
