# APEXUS-TECH — Credit Spread Analysis

> Analyze corporate credit spreads vs U.S. Treasuries (AAA, BAA, High Yield vs 10Y), with static plots and an interactive dashboard.

## Overview
This project pulls daily yields from **FRED**, computes credit spreads (e.g., `BAA - 10Y`), and visualizes trends across investment-grade and high-yield credit. It includes a Python script, a notebook, a cached CSV, and a client-ready report (DOCX/PDF). Tailored to the exact series and workflow used in this project. 

## Folder Contents
- `Lin_Tian_credit_spread_analysis.py` — main script (data fetch → clean → compute spreads → plots)
- `Lin_Tian_credit_spread_analysis.ipynb` — notebook version
- `credit_spread_raw_data.csv` — cached data (if you want to skip re-download)
- `Lin_Tian_Credit_Spread_Analysis_Report.docx` / `.pdf` — final write-up & visuals

## Data Sources (FRED series)
- **Treasuries:** `DGS1`, `DGS5`, `DGS10`, `DGS30`
- **Corporate:** `AAA` (Moody’s Aaa), `BAA` (Moody’s Baa)
- **High Yield:** `BAMLH0A0HYM2` (ICE BofA US High Yield Master II)  
Spreads in this project are computed against **10-Year Treasury** (`DGS10`).

## Methods
1. Fetch series from FRED for a defined date range (2015-01-01 → today).
2. Align by date, **forward-fill** missing days to keep continuity.
3. Compute spreads:
   - `AAA_10Y_Spread = AAA - DGS10`
   - `BAA_10Y_Spread = BAA - DGS10`
   - `HY_10Y_Spread  = BAMLH0A0HYM2 - DGS10`
4. Plot with **Matplotlib** (static) and **Plotly** (interactive hover, combined view).
