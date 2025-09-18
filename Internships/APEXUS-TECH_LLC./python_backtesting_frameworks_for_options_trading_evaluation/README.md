# APEXUS-TECH — Python Backtesting Frameworks for Options Trading (Evaluation)

> Comparative evaluation of backtesting frameworks for *options* strategies, with a demo notebook and a client-facing report.

## Overview
This project reviews and contrasts four Python-based approaches for **options backtesting**:

- **Backtrader** — flexible, equity-oriented; options require heavy custom work.
- **QuantConnect (Lean)** — first-class options support (chains, Greeks, expirations).
- **Optopsy** — options-focused but early-stage; documentation and stability vary.
- **Custom Pandas Simulation** — maximum flexibility for research prototypes.

It includes a demo notebook for simple strategy PnL and a written report summarizing findings and trade-offs.

## Folder Contents
- `Lin_Tian_Python_Backtesting_Frameworks_for_Options_Trading_Evaluation_Report.docx` — full write-up (Word)
- `Lin_Tian_Python_Backtesting_Frameworks_for_Options_Trading_Evaluation_Report.pdf` — full write-up (PDF)
- `Options_Strategies_With_PnL.ipynb` — demonstration notebook (toy options strategy & PnL)

> Note: The notebook is illustrative (education/portfolio). For production-grade options backtests, see QuantConnect Lean setup.

## Evaluation Criteria
- **Ease of Use & Docs** — how quickly a new user can be productive
- **Options Feature Set** — chains, DTE filters, Greeks, multi-leg support, expirations/rolls
- **Flexibility** — custom data, indicators, and execution modeling
- **Community & Maintenance** — ecosystem health, bug fixes, examples

## Summary of Findings
- **QuantConnect (Lean)**: Best overall for serious options research; rich options API, realistic fills/fees, historical chains. Local setup requires extra steps.
- **Backtrader**: Great architecture and community for equities; **no native options**—you must simulate legs/pricing yourself.
- **Optopsy**: Promising options-native design (IV rank, DTE logic), but version/packaging issues can block onboarding.
- **Custom Pandas**: Ideal for transparent prototypes and EDA; realism (fills, slippage, margin) must be built from scratch.
