# DSC 30 — PA2

> Array‐based **stack** implementation with a custom exception, plus a simple **WebBrowser** that uses two stacks for Back/Forward navigation. Includes lightweight test drivers.

## Folder Contents
- `src/`
  - `MyStack.java` — stack implementation (push/pop/peek/isEmpty/isFull/size, etc.)
  - `MyStackTest.java` — simple test harness for `MyStack`
  - `WebBrowser.java` — toy browser using two stacks (`back`, `forward`) with `visit()`, `back()`, `forward()`
  - `WebBrowserTest.java` — test harness for the browser behavior
  - `utilities/FullStackException.java` — custom checked/unchecked exception thrown on stack overflow
- `PA2.iml` — IntelliJ project metadata (not required to run via terminal)

> If your repo’s filenames differ slightly, prefer those in your source.

---

## Quickstart

### Requirements
- Java JDK **8+** (11+ recommended)
- Terminal or IDE (IntelliJ IDEA / VS Code with Java extension)

### Compile (terminal)
```bash
# from the PA2 folder
cd src

# Option A: compile each file explicitly
javac utilities/FullStackException.java MyStack.java WebBrowser.java MyStackTest.java WebBrowserTest.java

# Option B (Unix/macOS): compile all Java files recursively
# find . -name "*.java" -print0 | xargs -0 javac
