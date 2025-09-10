# Imports
import requests
from dateutil.relativedelta import relativedelta
import datetime

class Stock:
    def __init__(self, ticker, lastweekclose, todayclose) :
        self.ticker = ticker
        self. lastweekclose = lastweekclose
        self. todayclose = todayclose
        self.difference = (todayclose - lastweekclose) / lastweekclose

def cp_getter(ticker):
    current_date = datetime.date.today()
    #current_date = datetime.date.today() - relativedelta(weeks=1)
    past_date = current_date - relativedelta(weeks=1)
    data = requests.get(f'https://api.polygon.io/v2/aggs/ticker/{ticker}/range/1/day/{past_date}/{current_date}?adjusted=true&sort=asc&apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz').json()
    if 'results' not in data.keys():
        return 'No Result'
    else:
        results = data['results']
        if len(results) < 2:
            return 'No Result'
        else:
            td = results[-1]['c']
            lw = results[0]['c']
            return (lw, td)
with open ("listofthousandtickers.txt", "r") as file:
    tickers = file.read().splitlines()

def top_ten_cp_diff(tickers):
    stocks = []
    for ticker in tickers:
        cp = cp_getter(ticker)
        if cp == 'No Result':
            pass
        else:
            lastweekclose, todayclose = cp
            stocks.append(Stock(ticker, lastweekclose, todayclose))
    stocks.sort(key=lambda x: x.difference, reverse=True)
    tickers_sorted = [stock.ticker for stock in stocks]
    print(tickers_sorted[:10])

top_ten_cp_diff(tickers)
