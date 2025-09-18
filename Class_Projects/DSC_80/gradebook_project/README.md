# DSC 80 — Gradebook Project

> Data cleaning, validation, and visualization on course gradebooks. Includes a runnable notebook, a pure-Python module, validation script, and example datasets.

## Overview
This project ingests raw gradebook exports (labs, discussions, projects, final exam breakdown), cleans and aggregates them, and produces summary tables and visuals (e.g., section-level performance and a grade heatmap). The functions live in `project.py`; the notebook demonstrates the full workflow.

## Folder Contents
- `project.ipynb` — main analysis notebook (EDA, cleaning, plots, and checks)
- `project.py` — implementation of gradebook utilities (see Function Index below)
- `project-validation.py` — optional CLI to run notebook checks (Otter grader)
- `data/`
  - `grades.csv` — master gradebook export (labs, discussions, projects, final, lateness)
  - `final_exam_breakdown.csv` — per-question final points (PID + Q1..Q12)
  - `heatmap-example.png` — sample visualization
- Extras
  - `tricky_1.csv`, `tricky 1.csv` — small edge-case CSVs for testing
  - `__pycache__/` — bytecode cache (ignored)
