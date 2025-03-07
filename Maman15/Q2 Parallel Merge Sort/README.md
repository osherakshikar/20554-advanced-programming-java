# Maman15 Question 2: Parallel Merge Sort

## Overview
This is my solution for Question 2 of Maman15 assignment from the Advanced Programming in Java course (20554), implementing a parallel merge sort algorithm.

## Description
This program demonstrates a multi-threaded approach to the merge sort algorithm. It creates an array of random integers and sorts them using multiple threads that work in parallel, merging arrays from a shared pool until a single sorted array remains.

## Implementation Details
- Multi-threaded merge sort implementation
- Thread-safe shared array pool
- Dynamic workload distribution among threads
- Concurrent merging of sub-arrays

## Key Components
- `Main`: Initializes the arrays, creates threads, and controls program flow
- `ArrayPool`: Thread-safe container for managing arrays being processed
- `MergeThread`: Thread class that handles merging of arrays from the pool

## Features
- User can specify the number of elements (n) in the initial array
- User can specify the number of threads (m) to use for sorting
- Random generation of integer values between 1-100
- Visual display of initial arrays and final sorted result

## How to Run
1. Clone this repository
2. Navigate to the project directory
3. Compile the Java files: `javac *.java`
4. Run the main class: `java Main`
5. Follow the prompts to enter array size and thread count
