# DSC 140 — Super Homework

> A collection of ML tasks: a Spanish-vs-French **word classifier**, a **regression & log-likelihood** exercise, and a **decision-tree** problem. Includes a runnable notebook and a plain-Python classifier (no sklearn).

## Overview
This folder contains:
- **Classifier Competition** — build a from-scratch model that predicts whether a word is **Spanish** or **French**.
- **Regression & Log-Likelihood** — compute MSE and Gaussian log-likelihood for a simple linear model on a small dataset.
- **Decision Tree (Rain)** — hand/programmable construction of a depth-2 tree using **Gini** as the split criterion.

The notebook ties everything together; the classifier is also provided as a standalone `classify.py` per the spec.

## Folder Contents
- `Super_hw.ipynb` — main notebook with solutions, plots, and explanations
- `classify.py` — required submission function `classify(train_words, train_labels, test_words)` (no external ML libs)
- Data files:
  - `train.csv`, `test.csv` — word/label data for the classifier
  - `test_1.csv`, `test_2.csv`, `test_3.csv` — extra test splits/examples
  - `data.csv` — small regression dataset for MSE/log-likelihood
- Prompts/handouts:
  - `dsc140asuperhw.pdf`, `homework (1).pdf`
