import yfinance as yf
import pandas as pd
from transformers import AutoTokenizer, AutoModelForSequenceClassification, pipeline
from vaderSentiment.vaderSentiment import SentimentIntensityAnalyzer
import matplotlib.pyplot as plt
import datetime
import backtrader as bt
from fastquant import backtest

# -------- FinBERT Sentiment --------
model = AutoModelForSequenceClassification.from_pretrained("ProsusAI/finbert")
tokenizer = AutoTokenizer.from_pretrained("ProsusAI/finbert")
finbert = pipeline("sentiment-analysis", model=model, tokenizer=tokenizer)

headlines = [
    "Apple stock rises after strong earnings report",
    "Market turmoil leads to a sharp decline in tech shares",
    "Investors worry about inflation and interest rates",
    "Apple launches new iPhone with innovative features"
]

finbert_results = finbert(headlines)
df_finbert = pd.DataFrame(finbert_results)
df_finbert["headline"] = headlines
print("FinBERT Results:")
print(df_finbert)

# -------- VADER Sentiment --------
analyzer = SentimentIntensityAnalyzer()
vader_scores = [analyzer.polarity_scores(text)["compound"] for text in headlines]
df_vader = pd.DataFrame({"headline": headlines, "vader_score": vader_scores})
print("VADER Results:")
print(df_vader)

# -------- Stock Data (AAPL) --------
ticker = "AAPL"
start_date = "2023-01-01"
end_date = "2023-12-31"
df_stock = yf.download(ticker, start=start_date, end=end_date)

# -------- Backtrader Strategy --------
class TestStrategy(bt.Strategy):
    def __init__(self):
        self.sma = bt.indicators.SimpleMovingAverage(period=15)

    def next(self):
        if self.data.close[0] > self.sma[0]:
            self.buy()
        elif self.data.close[0] < self.sma[0]:
            self.sell()

cerebro = bt.Cerebro()
data = bt.feeds.PandasData(dataname=df_stock)
cerebro.adddata(data)
cerebro.addstrategy(TestStrategy)
print("Backtrader Starting Portfolio Value: %.2f" % cerebro.broker.getvalue())
cerebro.run()
print("Backtrader Final Portfolio Value: %.2f" % cerebro.broker.getvalue())

# -------- Fastquant Backtest Example --------
df_fastquant = df_stock.reset_index()[["Date", "Close"]].copy()
df_fastquant.columns = ["date", "close"]
df_fastquant["buy_signal"] = df_fastquant["close"].shift(1) < df_fastquant["close"]
df_fastquant["sell_signal"] = df_fastquant["close"].shift(1) > df_fastquant["close"]

class SignalStrat(bt.Strategy):
    def next(self):
        if self.data.close[0] > self.data.close[-1]:
            self.buy()
        elif self.data.close[0] < self.data.close[-1]:
            self.sell()

backtest(SignalStrat, df_fastquant, plot=False)
