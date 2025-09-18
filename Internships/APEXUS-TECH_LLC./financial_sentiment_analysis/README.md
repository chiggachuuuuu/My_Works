# APEXUS-TECH — Financial Sentiment Analysis

> NLP pipeline to score and visualize sentiment from finance-related text (e.g., headlines), with optional price data and simple backtesting demos.

## Overview
This project demonstrates two complementary approaches to financial sentiment:
- **FinBERT (Transformers)** — finance-specific classifier for nuanced polarity.
- **VADER (Rule-based)** — lightweight scorer for short texts.
It includes a Python script and a notebook with examples, plus a final report (DOCX/PDF).

## Folder Contents
- `Lin_Tian_python_financial_sentiment_analysis_package.py` — main demo script (FinBERT + VADER + price data + toy backtests)
- `Lin_Tian_financial_sentiment_analysis_package_and_visualization.ipynb` — notebook with scoring/visualization walkthrough
- `Financial_Sentiment_Analysis_Report.docx` / `Lin_Tian_Financial_Sentiment_Analysis_Report.pdf` — final write-up

## Methods
### Sentiment engines
- **FinBERT** via Hugging Face `transformers` (downloads `ProsusAI/finbert` on first run).
- **VADER** via `vaderSentiment` (no extra downloads).
Both are used to score a list of text inputs (e.g., headlines) and compare results side-by-side.

### Optional market data & demos
- **Yahoo Finance (`yfinance`)** to fetch OHLCV (example uses AAPL).
- **Backtrader**: simple SMA crossover toy strategy for demonstration.
- **Fastquant**: minimal “signal” backtest example from a price series.
> These are **illustrations** for wiring sentiment into downstream workflows; they are not production trading systems.
