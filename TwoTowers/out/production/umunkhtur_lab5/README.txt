1. What is the best solution to the 15-block problem?

Target (optimal) height: 20.234598300071305
Best Subset 4 5 6 10 11 12 13
Best Height: 20.23411306140849
Distance from optimal: 4.8523866281513506E-4


2. Solve the 20-block, 21-block, and 22-block problems and list the times taken to solve each
problem. You might want to run each test two or three times and average the results to get
better measurements. What do you notice about the runtimes? Why does this result make
sense given the design of the program?

20: 261 ms
21: 522 ms
22: 1044 ms

Each of the runtimes double/the runtime is exponential. This makes sense because you have to iterate twice as many
combinations/subsets every time the height increases by one (increases exponentially). You add another bit (multiple of 2)



3. Based on your empirical results from the previous question and your understanding of the
time complexity of the program, estimate how long it would take to solve the 50-block problem
(you won’t want to actually run this). Specify your answer in some reasonable time unit (hint:
 it shouldn’t be milliseconds for this scenario)!

Considering the runtime for 22 blocks (668 ms) I took this and used the exponential growth formula.

 f(x) = a(1 + r)^x =
 f(x) = (1024)(2)^(50-22) = around 274,877,906,944 ms
 This translates to about 8.716 years.

