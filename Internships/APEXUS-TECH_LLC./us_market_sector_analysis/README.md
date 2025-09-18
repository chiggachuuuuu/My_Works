# APEXUS-TECH — US Market Sector Analysis

> Rank S&P 500 companies by sector using key metrics, and analyze correlations within DJIA and Nasdaq 100. Includes ready-to-share sector charts and correlation dashboards.

## Overview
This project builds **top-5 rankings** for each of the **11 GICS sectors** of the S&P 500 and generates sector charts. It also computes **3-year rolling correlations** of index constituents with their benchmarks (DJIA, Nasdaq 100), exporting CSVs and visualization PNGs.

**Deliverables**
- Sector rankings & visuals (PNG)
- Correlation CSVs and heatmaps for DJIA and Nasdaq 100
- A client-ready write-up (DOCX/PDF)

## Folder Contents
- `US_Market_Sector_Analysis_LinTian_FINAL.docx` — final report (Word)
- `Lin_Tian_US_Market_Sector_Analysis.pdf` — final report (PDF)
- `Lin_Tian_S&P 500 companies ranking and visual by sectors.ipynb` — main notebook
- `Lin_Tian_Ticker_Correlation_analysis.py` — correlation script (DJIA & Nasdaq 100)
- `sector_charts/` — exported bar charts:  
  `Communication_Services_ranking.png`, `Consumer_Discretionary_ranking.png`, …, `Utilities_ranking.png`
- `sp500_companies.csv` — S&P 500 universe snapshot

## Data Sources
- **Tickers:** Wikipedia S&P 500 constituents (or `sp500_companies.csv`)
- **Prices/metrics:** Yahoo Finance via `yfinance`
- **Sectors:** GICS sector classification (11 sectors)

## Methods
### Sector Rankings
- Choose **sector-appropriate metrics** (e.g., IT: P/E, ROE; Financials: ROE, D/E; Staples: Div. Yield, ROA).
- Rank each sector’s companies by the chosen metrics and select **Top 5**.
- Export charts to `sector_charts/*.png`.

### Correlations (DJIA & Nasdaq 100)
- Fetch 3 years of daily closes.
- Compute daily returns, then **correlation vs index**.
- Save:
  - `djia_high_correlation.csv`, `nasdaq_high_correlation.csv`
  - `djia_bar.png`, `nasdaq_bar.png` (top correlated bars)
  - `djia_heatmap.png`, `nasdaq_heatmap.png` (constituent heatmaps)

