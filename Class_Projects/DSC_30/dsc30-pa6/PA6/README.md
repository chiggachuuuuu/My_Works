# DSC 30 — PA6: Binary Search Trees & Mini “Search Engine”

> Implement a generic **BST** and use it to index a tiny movie corpus (actors, studios, ratings) for simple lookups—then test via driver programs.

## Overview
This assignment has two parts:
1) **BST implementation & tests** — build/verify core BST operations.  
2) **SearchEngine** — parse a small movie dataset and answer lookups using BST-backed indices.

See the included prompt PDF for the original specification. *(“PA6_1.pdf” in this folder).*  

## Folder Contents
- `src/`
  - `BSTree.java` — BST implementation (insert/search/traversal…)
  - `BSTreeTester.java` — primary test harness for BST
  - `BSTreeTestEC.java` — extra-credit BST tests
  - `BSTManual.java` — small manual tests / examples
  - `SearchEngine.java` — parses dataset, builds indices, answers queries
  - `moviedata.txt` — toy corpus used by `SearchEngine`
  - `PA6_InputOutput.txt` — example queries & expected outputs
- `PA6_1.pdf` — assignment handout (spec/details)
- `PA6.iml` — IDE metadata (IntelliJ)

## Data Format (`moviedata.txt`)
The dataset is a sequence of **movie records**, each separated by a single line containing just `-`.  
Within each record:
1. **Title** (one line)  
2. **Cast** — space-separated list of actors (kebab-case)  
3. **Studio(s)** — one or more tokens  
4. **MPAA rating** — `PG`, `PG13`, or `R`  
5. `-` (record separator)

This record structure is consistent across the file. :contentReference[oaicite:0]{index=0}

## Query Types (as used by `SearchEngine`)
The driver supports multiple **query modes** (identified by a *Query Number* in the sample I/O file). The examples below summarize the behavior:

- **Type 0 — Actor lookups:**  
  Input terms are actor names; output is the list of **movie titles** containing the actor(s).  
  Example:  
  `jon-favreau` → `[chef, iron-man-2, spider-man-ffh]`  
  `emma-stone jesse-eisenberg` →  
  `zombieland`, and also a separate per-actor breakdown is shown in the sample output. :contentReference[oaicite:1]{index=1}

- **Type 1 — Studio lookups:**  
  Input terms are studio names; output is **movie titles** produced by those studios, plus per-studio fallbacks if the joint query yields none.  
  Examples:  
  `paramount` → `[iron-man-2, terminator, tropic-thunder]`  
  `dreamworks paramount` → `[tropic-thunder]` (joint match). :contentReference[oaicite:2]{index=2}

- **Type 2 — Rating lookups (by people):**  
  Input terms are names; output is the set of **MPAA ratings** seen in movies involving those names (joint first, then per-name).  
  Examples:  
  `chris-evans scarlett-johansson` → `[PG13]`  
  `robert-downey-jr octavia-spencer` → joint `[PG]`; per-name `[PG13, R]` also listed. :contentReference[oaicite:3]{index=3}

> The full example session (queries and expected text) is in `PA6_InputOutput.txt`. Use it to verify your run matches the format. :contentReference[oaicite:4]{index=4}

## Quickstart

### 1) Compile
```bash
# from the PA6 folder
cd src
javac *.java
