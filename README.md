# My Works — Portfolio Repository

This repository is a curated collection of my work from UCSD coursework, class projects, and professional internships. It includes programming assignments, data science projects, and quantitative finance research. Each folder has its own `README.md` (or will soon) describing context, setup, and results.

---

## 🎓 Class Projects

### COGS 108 — *The Role of Music in UCSD Students' Study Habits and GPA*

* Survey design, data collection, and analysis of how music influences academic performance.
* Tools: Jupyter Notebook, Pandas, Matplotlib, Scipy, Sklearn.

### DSC 30 — Programming Assignments (PA1–PA8)

* Java-based data structures and algorithms: stacks, queues, linked lists, trees, heaps, hashing, and bloom filters.
* Includes tests, runtime analysis, and PDF write‑ups.

### DSC 80 — Data Science Projects

* **Recipe Ratings vs Complexity**: exploratory data analysis with interactive visualizations.
* **Gradebook Project**: data cleaning, validation, and visualization.
* **Language Models Project**: text tokenization and probabilistic modeling.

### DSC 140 — Super Homework

* Applied machine learning and classification using Python notebooks.

---

## 💼 Internships

### APEXUS-TECH LLC

* **Credit Spread Analysis**: corporate vs treasury yield spreads, dashboards, and reports.
* **Financial Sentiment Analysis**: NLP sentiment pipeline (FinBERT, VADER) with visualizations.
* **Options Trading Backtesting Frameworks**: evaluation of Python backtesting tools, strategy PnL analysis.
* **US Market Sector Analysis**: sector metrics, company rankings, and correlation dashboards (DJIA, NASDAQ).

### Icarus Funds

* Implemented Python algorithms for stock screening and strategy tasks (20+ task scripts).
* Documented intern tasks with detailed Word reports.

---

## 🛠️ Tech Stack

* **Languages**: Python, R, Java, SQL
* **Libraries**: pandas, numpy, scikit‑learn, matplotlib, plotly, tidyverse
* **Tools**: Jupyter, R Markdown, Git, FRED API, Yahoo Finance

---

## 📄 License & Ethics

* All internship artifacts are sanitized for public sharing. Proprietary data is excluded.
* This repo is under the **MIT License**.

---

## 🚀 Next Steps

* Add project‑level `README.md` files with setup, results, and figures.
* Include screenshots and dashboards in `figs/` or `assets/` folders.
* Keep raw or proprietary data out of version control (use `.gitignore`).

---

# Root `README.md` — Customized for `chiggachuuuuu/my_works`

````md
# Lin Tian — Portfolio Repository

[![Repo](https://img.shields.io/badge/Repo-my_works-black.svg)](#) [![Python](https://img.shields.io/badge/Python-Data%20Science-blue.svg)](#) [![R](https://img.shields.io/badge/R-tidyverse-75aadb.svg)](#) [![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

A curated showcase of my coursework, capstone projects, and internship deliverables. Each subfolder has its own README (work in progress) with setup, usage, and results.

> **Note on confidentiality:** Internship artifacts are sanitized and limited to what is publicly shareable. Proprietary data/code are excluded.

---

## 🔎 Quick Start
```bash
# Clone
git clone https://github.com/chiggachuuuuu/my_works.git
cd my_works

# (Optional) Create a Python env
python -m venv .venv && source .venv/bin/activate  # Windows: .venv\Scripts\activate

# Common tools
pip install -r requirements.txt  # if present in a subproject
````

* Browse top-level folders:

  * `Class_Projects/` — UCSD class projects & assignments (COGS 108, DSC 30, DSC 80, DSC 140)
  * `Internships/` — APEXUS-TECH LLC and Icarus Funds work (reports, notebooks, scripts)

---

## 🗂 Repository Map

| Category            | Project / Folder                                                      | Path                                                                                          | What it is                                                                                 |
| ------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------ |
| **Class\_Projects** | COGS 108 — *The Role of Music in UCSD Students' Study Habits and GPA* | `Class_Projects/COGS_108/The_Role_of_Music_in_UCSD_Students'_Study _Habits_and_GPA/`          | Final project with survey data, proposal & notebook.                                       |
| **Class\_Projects** | DSC 30 — Programming Assignments (PA1–PA8)                            | `Class_Projects/DSC_30/`                                                                      | Java data structures: stacks, queues, linked lists, BST, heaps, hashing, runtime analysis. |
| **Class\_Projects** | DSC 80 — *Exploration of Recipe Rating & Complexity*                  | `Class_Projects/DSC_80/Exploration-the-Relatioinship-Between-Recipe-Rating-Complexity-Level/` | Full EDA & modeling notebooks, utilities, HTML assets.                                     |
| **Class\_Projects** | DSC 80 — Gradebook Project                                            | `Class_Projects/DSC_80/gradebook_project/`                                                    | Data processing, validation, visualizations.                                               |
| **Class\_Projects** | DSC 80 — Language Models Project                                      | `Class_Projects/DSC_80/language_models_project/`                                              | Classical NLP language modeling exercises.                                                 |
| **Class\_Projects** | DSC 140 — Super HW                                                    | `Class_Projects/dsc_140/super_hw/`                                                            | Classification notebook & scripts with provided datasets.                                  |
| **Internships**     | APEXUS-TECH — Credit Spread Analysis                                  | `Internships/APEXUS-TECH_LLC./credit_spread_analysis/`                                        | Report (DOCX/PDF), notebook, script, and raw data CSV.                                     |
| **Internships**     | APEXUS-TECH — Financial Sentiment Analysis                            | `Internships/APEXUS-TECH_LLC./financial_sentiment_analysis/`                                  | Reports and Python package/notebook for sentiment.                                         |
| **Internships**     | APEXUS-TECH — Options Backtesting Frameworks                          | `Internships/APEXUS-TECH_LLC./python_backtesting_frameworks_for_options_trading_evaluation/`  | Evaluation report & PnL notebook.                                                          |
| **Internships**     | APEXUS-TECH — US Market Sector Analysis                               | `Internships/APEXUS-TECH_LLC./us_market_sector_analysis/`                                     | Sector rankings/charts, correlation analyses, reports.                                     |
| **Internships**     | Icarus Funds — Intern Tasks (algos)                                   | `Internships/Icarus_Funds/intern_tasks/algos/`                                                | Python task scripts (sanitized).                                                           |
| **Internships**     | Icarus Funds — Task Briefs                                            | `Internships/Icarus_Funds/intern_tasks/tasks/`                                                | Task descriptions (DOCX).                                                                  |

> If a link 404s on GitHub web view, navigate via the sidebar—GitHub sometimes dislikes spaces/special characters in folder names.

---

## ✨ Highlights

* **COGS 108 — Music & GPA**: Built a full data pipeline and analysis from survey → EDA → insights.
* **DSC 80 — Recipe Ratings**: Exploratory & statistical analysis with interactive HTML artifacts.
* **Credit Spread Analysis (APEXUS-TECH)**: End-to-end fixed-income analytics pipeline and client-ready report.
* **US Market Sector Analysis (APEXUS-TECH)**: Top-5 rankings per sector and correlation studies.
* **Icarus Funds — Algorithms**: Iterative algorithmic research tasks (sanitized scripts & briefs).

---

## 🧭 How to Navigate

* Each subfolder will get its own `README.md` covering: problem, data, methods, results, and how to run.
* Large data (e.g., `*.csv`, model artifacts) may be gitignored; if missing, sub-readmes will include download instructions.

---

## 📄 License & Attribution

* Code & notebooks: **MIT License**.
* Reports (DOCX/PDF) © Lin Tian, for portfolio use. Do not reuse without permission.

## 📬 Contact

* **Email:** [lt3043@columbia.edu](mailto:lt3043@columbia.edu)
* **LinkedIn:** [www.linkedin.com/in/lt703](www.linkedin.com/in/lt703)

```
```
