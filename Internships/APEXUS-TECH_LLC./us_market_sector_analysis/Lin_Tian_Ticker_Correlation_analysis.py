import pandas as pd
import yfinance as yf
import matplotlib.pyplot as plt
import seaborn as sns
from datetime import datetime, timedelta

LOOKBACK_YEARS = 3
END_DATE = datetime.today()
START_DATE = END_DATE - timedelta(days=LOOKBACK_YEARS * 365)

def get_djia_tickers():
    url = 'https://en.wikipedia.org/wiki/Dow_Jones_Industrial_Average'
    tables = pd.read_html(url)
    df = tables[2]  # 2nd table has the tickers
    tickers = df['Symbol'].tolist()
    tickers = [ticker.replace('.', '-') for ticker in tickers]  # e.g., BRK.B → BRK-B
    return tickers

def get_nasdaq100_tickers():
    url = 'https://en.wikipedia.org/wiki/NASDAQ-100'
    tables = pd.read_html(url)
    df = tables[4]  # 5th table has the tickers
    tickers = df['Ticker'].tolist()
    tickers = [ticker.replace('.', '-') for ticker in tickers]
    return tickers

def download_price_data(tickers, start, end):
    return yf.download(tickers, start=start, end=end)['Close']
    
def compute_high_corr_stocks(price_df, benchmark_symbol, threshold=0.7):
    returns = price_df.pct_change().dropna()
    benchmark = returns[benchmark_symbol]
    corr = returns.corrwith(benchmark)
    return corr[(corr > threshold) & (corr.index != benchmark_symbol)].sort_values(ascending=False)

def plot_top_correlation_bars(corr_series, title, filename):
    plt.figure(figsize=(10, 6))
    corr_series.sort_values().plot(kind='barh')
    plt.title(title)
    plt.xlabel("Correlation")
    plt.grid(True)
    plt.tight_layout()
    plt.savefig(filename)
    plt.close()

def plot_heatmap(price_df, tickers, title, filename):
    returns = price_df[tickers].pct_change().dropna()
    corr_matrix = returns.corr()
    plt.figure(figsize=(12, 10))
    sns.heatmap(corr_matrix, annot=False, cmap='coolwarm', linewidths=0.5)
    plt.title(title)
    plt.tight_layout()
    plt.savefig(filename)
    plt.close()

def main():
    print("Fetching tickers from Wikipedia...")
    djia_tickers = get_djia_tickers()
    nasdaq_tickers = get_nasdaq100_tickers()
    print("Downloading DJIA data...")
    djia_data = download_price_data(djia_tickers + ['^DJI'], START_DATE, END_DATE)
    print("Calculating DJIA correlations...")
    djia_corr = compute_high_corr_stocks(djia_data, '^DJI')
    djia_corr.to_csv("djia_high_correlation.csv")
    plot_top_correlation_bars(djia_corr, "Top Correlated DJIA Stocks with DJIA Index", "djia_bar.png")
    plot_heatmap(djia_data, djia_corr.index.tolist(), "DJIA Correlation Heatmap", "djia_heatmap.png")
    print("Downloading Nasdaq 100 data...")
    nasdaq_data = download_price_data(nasdaq_tickers + ['^NDX'], START_DATE, END_DATE)
    print("Calculating Nasdaq 100 correlations...")
    nasdaq_corr = compute_high_corr_stocks(nasdaq_data, '^NDX')
    nasdaq_corr.to_csv("nasdaq_high_correlation.csv")
    plot_top_correlation_bars(nasdaq_corr, "Top Correlated Nasdaq 100 Stocks with NDX Index", "nasdaq_bar.png")
    plot_heatmap(nasdaq_data, nasdaq_corr.index.tolist(), "Nasdaq 100 Correlation Heatmap", "nasdaq_heatmap.png")
    print("All results saved!")

if __name__ == "__main__":
    main()
