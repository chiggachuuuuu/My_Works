# DSC 30 — PA5: Sorting Algorithms & Runtime Analysis

> Implement and compare classic sorting algorithms (Insertion, Quick, Bucket, Counting). Generate runtime data, plot results, and explore best/worst-case tweaks.

## Folder Contents
- `src/`
  - `Sorts.java` — implementations (Insertion, Quick, Bucket, Counting)
  - `SortsTest.java` — sanity tests for sorting correctness
  - `SortsTracing.java` — instrumented tracing/variants (pivot choice, etc.)
  - `RuntimeAnalysis.java` — driver to generate timings and write a CSV
  - `plot_runtimes.py` — Python helper to visualize the CSV (scatter subplots)
  - `Untitled.ipynb` — optional notebook for quick plotting/notes
- `sort_runtimes.csv` — generated timings (size vs time vs algorithm)
- `PA5.docx`, `PA5.pdf` — short write-up discussing results

## Quickstart

### A) Java — build & run experiments
```bash
# from PA5/
cd src
javac *.java

# run the timing driver (adjust class name if different)
java RuntimeAnalysis    # writes ../sort_runtimes.csv (see code)
