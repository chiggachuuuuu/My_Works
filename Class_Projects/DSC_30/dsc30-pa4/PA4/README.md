# DSC 30 — PA4

> Linked lists & queues: implement a **DoublyLinkedList** and a **MyQueue** (with an interface), plus reuse the **ProteinSynthesis** translator from PA3. Includes lightweight test drivers.

## Folder Contents
- `src/`
  - **List / Queue**
    - `DoublyLinkedList.java` — generic doubly linked list
    - `DoublyLinkedListTest.java` — tests for DLL operations
    - `MyQueueInterface.java` — queue ADT interface
    - `MyQueue.java` — queue implementation (backed by DLL or array per spec)
    - `MyQueueTest.java` — tests / sanity checks for the queue
  - **Bio mini-project (carry-over)**
    - `CodonMap.java` — codon ➜ amino-acid mapping
    - `ProteinSynthesis.java` — RNA ➜ amino-acid translator
    - `ProteinSynthesisTest.java` — tests for translation logic
- `PA4.iml` — IntelliJ/IDE metadata (not required to run via terminal)

> If file names differ slightly in your repo, prefer those names.

---

## Quickstart

### Requirements
- Java JDK **8+** (11+ recommended)
- Terminal or IDE (IntelliJ IDEA / VS Code + Java extension)

### Compile (terminal)
```bash
# from the PA4 folder
cd src
javac *.java
