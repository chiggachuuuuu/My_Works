# DSC 80 — Language Models Project

> Classical **n-gram language modeling** on literary corpora with sampling & sequence probabilities. Includes a notebook, a pure-Python module (`project.py`), and lightweight validation helpers.

## Overview
You’ll build and compare:
- **UniformLM** — uniform probability over the vocabulary
- **UnigramLM** — MLE over token frequencies
- **NGramLM (N ≥ 2)** — n-gram model with conditional probabilities and text sampling

Corpora include Shakespeare, Gogol, and Homer tokens. Utilities are provided to tokenize, train models, compute sequence probabilities, and **sample** text (end-of-sequence sentinel handled for you).

## Folder Contents
- `project.ipynb` — main notebook (exercises, explanations, plots)
- `project.py` — implementation of `UniformLM`, `UnigramLM`, `NGramLM`
- `project-validation.py` — CLI runner used in the class (depends on the course grader; optional in this public repo)
- `data/`
  - `shakespeare.txt`, `gogol.txt`, `test.txt` — raw text corpora
  - `homertokens.txt` — pre-tokenized Homer
- `imgs/chatgpt.png` — illustration
- `__pycache__/` — ignored when committing
