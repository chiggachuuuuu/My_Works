import requests

class stock:
    def __init__(self, ticker, openprice):
        self.ticker = ticker
        self.openprice = openprice

def open_p_getter(ticker):
    data = requests.get(f'https://api.polygon.io/v1/open-close/{ticker}/2024-06-24?adjusted=true&apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz').json()
    if len(data) == 0:
        return 'No Result'
    else:
        if 'open' not in data.keys():
            return 'No Result'
        else:
            op = data['open']
            return op

with open ("listofthousandtickers.txt", "r") as file:
    tickers = file.read().splitlines()

def top_ten_op(tickers):
    stocks = []
    for ticker in tickers:
        op = open_p_getter(ticker)
        if op == 'No Result':
            pass
        else:
            stocks.append(stock(ticker, op))
    stocks.sort(key=lambda x: x.openprice, reverse=True)
    tickers_sorted = [stock.ticker for stock in stocks]
    print(tickers_sorted[:10])

top_ten_op(tickers)
