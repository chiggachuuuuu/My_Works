# DSC 30 — PA3

> Queue + string parsing warm-up: a character queue ADT and a toy **protein synthesis** translator using codon mapping. Includes lightweight test drivers.

## Folder Contents
- `src/`
  - `CharQueue.java` — queue of `char`s
  - `CharQueueTest.java` — tests / sanity checks for `CharQueue`
  - `CodonMap.java` — maps RNA **codons** (triplets like `AUG`) → amino acids
  - `ProteinSynthesis.java` — translates RNA strings into amino-acid sequences
  - `ProteinSynthesisTest.java` — tests for translation logic
- `PA3.iml` — IntelliJ/IDE metadata (not needed for terminal runs)

> If names or locations differ in your repo, use those source paths.

---

## Quickstart

### Requirements
- Java JDK **8+** (11+ recommended)
- A terminal or IDE (IntelliJ IDEA / VS Code + Java extension)

### Compile (terminal)
```bash
# from the PA3 folder
cd src
javac *.java
