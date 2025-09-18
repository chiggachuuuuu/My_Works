# DSC 30 — PA7: Heaps, Priority Queues & MNIST Rendering

> Implement a generic **d-ary heap** and priority queue, then apply them to rendering images from the MNIST dataset.

## Overview
This assignment has two parts:
1) **Heap / Priority Queue implementation & tests** — build and verify a generic d-ary heap and a heap-backed priority queue.  
2) **MNIST ImageRenderer** — load digit images from the MNIST dataset and display them using heap-controlled ordering.

See the included prompt PDF for the original specification (not included in this repo snapshot).  

## Folder Contents
- `src/`
  - `HeapInterface.java` — interface defining heap operations  
  - `dHeap.java` — d-ary heap implementation  
  - `MyPriorityQueue.java` — priority queue wrapper built on heaps  
  - `dHeapTester.java` — primary test harness for heap & priority queue  
  - `ImageRenderer.java` — visualizes MNIST digits using a heap to manage order  
  - `MNIST.java` — parser/loader for the MNIST dataset (gzipped IDX files)  
  - `Worksheet.java` — supplemental worksheet/example code  
  - `stdlib.jar` — support library  
  - `data/` — MNIST dataset files  
    - `train-images-idx3-ubyte.gz`  
    - `train-labels-idx1-ubyte.gz`  
    - `t10k-images-idx3-ubyte.gz`  
    - `t10k-labels-idx1-ubyte.gz`  
- `PA7.iml` — IDE metadata (IntelliJ)  

## Data Format (MNIST)
The MNIST dataset consists of **handwritten digit images** and their corresponding labels:

- **Image files (`*-images-idx3-ubyte.gz`)** — contain 28×28 grayscale pixel values for each digit.  
- **Label files (`*-labels-idx1-ubyte.gz`)** — contain the digit class (0–9) for each image.  

These are stored in a binary IDX format, compressed with gzip, and are parsed by `MNIST.java`.  

## Renderer Behavior (`ImageRenderer`)
The `ImageRenderer` demonstrates heap usage by scheduling how images are drawn:

- **Load** — fetches images & labels from the MNIST files.  
- **Enqueue** — pushes image tasks into a priority queue.  
- **Render** — dequeues and draws images in heap-defined order (e.g., based on priority values).  

This showcases practical use of a priority queue in managing task order.  

## Quickstart

### 1) Compile
```bash
# from the PA7 folder
cd src
javac -cp .:stdlib.jar *.java
```

### 2) Run Tests
```bash
java -cp .:stdlib.jar dHeapTester
```

### 3) Run Renderer
```bash
java -cp .:stdlib.jar ImageRenderer
```
